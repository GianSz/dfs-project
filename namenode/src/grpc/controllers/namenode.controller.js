const {
    FRACTION_SIZE,
    dataNodeIterator,
    activeDataNodes,
    fileInformation,
    dataNodesInformation,
} = require("../../data/data");

const login = (call) => {
    const [, ip] = call.getPeer().split(":");
    activeDataNodes.push(ip);

    return "Logged in successfully";
}

const upload = (call) => {
    const { filename, size } = call.request;
    const [, ip] = call.getPeer().split(":");

    if (fileInformation.has(filename)) {
        return "File already exists";
    }

    const blockAmount = Math.ceil(size / FRACTION_SIZE);
    const blocksInfo = new Map();

    for (let i = 0; i < blockAmount; i++) {
        const availableDataNodes = activeDataNodes;
        const dataNodes = [];

        if(availableDataNodes.length >= 1) {
            for(let j = 0; j < 2; j++) {
                const randomIndex = Math.round(Math.random() * availableDataNodes.length);
                const dataNode = availableDataNodes[randomIndex];

                dataNodes.push(dataNode);
                availableDataNodes.splice(randomIndex, 1);
            }
        }

        const blockName = `${filename}_${i}`;
        blocksInfo.set(blockName, dataNodes);
    }

    fileInformation.set(filename, blocksInfo);
    console.log(fileInformation);
    
    return blocksInfo;
}

const download = (call) => {
    const { filename } = call.request;

    if (!fileInformation.has(filename)) {
        return "File not found";
    }
    console.log(fileInformation);
    return fileInformation.get(filename);
}

module.exports = {
    login,
    upload,
    download,
}