const {
  activeDataNodes,
  fileInformation,
  dataNodesInformation,
} = require("../data/data");

const getClient = require("../grpc/datanodeClient");

const checkHealth = async () => {
  for (const dataNode of activeDataNodes) {
    new Promise((resolve, reject) => {
      const client = getClient(dataNode);
      client.checkHealth({}, (err, response) => {
        if (err) {
          replicateBlocks(dataNode);
          resolve(err);
        } else {
          resolve(response);
        }
      });
    });
  }
};

const replicateBlocks = (dataNode) => {
  const blocks = dataNodesInformation[dataNode];

  removeFromArray(activeDataNodes, dataNode);
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

    const replicationClient = getClient(replicationDataNode);
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
