const {
  activeDataNodes,
  fileInformation,
  dataNodesInformation,
} = require("../data/data");

const getClient = require("../grpc/datanodeClient");

let isCheckingHealth = false;

const checkHealth = async () => {
  if (isCheckingHealth) {
    console.log("Waiting for previous health check to finish");
    return;
  }

  console.log("Checking heatlh of nodes: ", activeDataNodes);
  isCheckingHealth = true;

  const promises = [];
  for (const dataNode of activeDataNodes) {
    promises.push(
      new Promise((resolve, reject) => {
        const client = getClient(dataNode);
        client.checkHealth({}, (err, response) => {
          if (err) reject(err);
          else resolve(response);
        });
      })
    );
  }

  const results = await Promise.allSettled(promises);

  results.forEach((result, index) => {
    if (result.status === "rejected") {
      console.log("Datanode broken: ", activeDataNodes[index]);
      replicateBlocks(activeDataNodes[index]);
    }
  });

  isCheckingHealth = false;
};

const replicateBlocks = (dataNode) => {
  if(activeDataNodes.indexOf(dataNode) === -1) return;

  removeFromArray(activeDataNodes, dataNode);
  const blocks = dataNodesInformation[dataNode];
  delete dataNodesInformation[dataNode];

  if (blocks === undefined) {
    return;
  }

  for (const block of blocks) {
    const fileName = block.split("_")[0];

    if (!fileInformation[fileName]) {
      continue;
    }

    const blockHolders = fileInformation[fileName][block].dataNodes;
    removeFromArray(blockHolders, dataNode);

    const replicationDataNode = getReplicationDataNode(fileName, block);

    if (replicationDataNode === undefined) {
      continue;
    }

    blockHolders.push(replicationDataNode);
    dataNodesInformation[replicationDataNode].push(block);

    const replicationClient = getClient(blockHolders[0]);
    replicationClient.replicateBlock(
      { blockName: block, dataNode: replicationDataNode },
      (err, response) => {}
    );
  }
};

const getReplicationDataNode = (fileName, block) => {
  const blockHolder = fileInformation[fileName][block].dataNodes[0];

  if (!blockHolder) {
    delete fileInformation[fileName];
  }

  const availableDataNodes = [...activeDataNodes];
  removeFromArray(availableDataNodes, blockHolder);

  const replicationDataNode = getRandomDataNode(availableDataNodes);
  return replicationDataNode;
};

const removeFromArray = (array, element) => {
  array.splice(array.indexOf(element), 1);
};

const getRandomDataNode = (dataNodes) => {
  const randomIndex = Math.floor(Math.random() * dataNodes.length);
  return dataNodes[randomIndex];
};

module.exports = {
  checkHealth,
};
