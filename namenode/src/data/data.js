const FRACTION_SIZE = (process.env.FRACTION_SIZE || 5) * 1024;
const activeDataNodes = [];
const fileInformation = {};
const dataNodesInformation = {};

module.exports = {
    FRACTION_SIZE,
    activeDataNodes,
    fileInformation,
    dataNodesInformation,
}