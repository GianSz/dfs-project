const {
  FRACTION_SIZE,
  activeDataNodes,
  fileInformation,
  dataNodesInformation,
} = require("../../data/data");
const getClient = require("../datanodeClient");

const login = (call) => {
  const [, ip] = call.getPeer().split(":");
  activeDataNodes.push(ip);

  return "Logged in successfully";
};

const upload = (call) => {
  const { fileName, fileSize } = call.request;

  if (fileInformation[fileName] !== undefined) {
    return "File already exists";
  }

  if (activeDataNodes.length < 1) {
    return "No active data nodes";
  }

  const blocksInfo = {};
  const blockAmount = Math.ceil(fileSize / FRACTION_SIZE);

  for (let i = 0; i < blockAmount; i++) {
    const availableDataNodes = [...activeDataNodes];
    const dataNodes = [];
    const blockName = `${fileName}_${i}`;

    for (let j = 0; j < 2; j++) {
      if (availableDataNodes.length < 1) {
        break;
      }

      const randomIndex = Math.floor(Math.random() * availableDataNodes.length);
      const dataNode = availableDataNodes[randomIndex];

      if (dataNodesInformation[dataNode]) {
        dataNodesInformation[dataNode].push(blockName);
      } else {
        dataNodesInformation[dataNode] = [blockName];
      }

      dataNodes.push(dataNode);
      availableDataNodes.splice(randomIndex, 1);
    }

    blocksInfo[blockName] = {
      dataNodes: dataNodes,
    };
  }
  fileInformation[fileName] = blocksInfo;
  return blocksInfo;
};

const download = (call) => {
  const { fileName } = call.request;
  if (fileInformation[fileName] === undefined) {
    return "File not found";
  }
  return fileInformation[fileName];
};

// const checkHealth = async () => {
//   const promises = [];

//   for (let dataNode of activeDataNodes) {
//     promises.push(
//       new Promise((resolve, reject) => {
//         const client = getClient(dataNode);
//         client.checkHealth({}, (err, response) => {
//           if (err) {
//             resolve("FAILED");
//           }
//           resolve("OK");
//         });
//       })
//     );
//   }

//   Promise.all(promises).then((values) => { console.log(values) });
// };

module.exports = {
  login,
  upload,
  download,
};
