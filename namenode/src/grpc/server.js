const {
  login,
  upload,
  download,
} = require("./controllers/namenode.controller");

const {
  getLoadedPackageDefinition,
  getServer,
  bindServer,
} = require("../utils/grpcServer");

const NAMENODE_PROTO_PATH = __dirname + "/namenode.proto";
const DATANODE_PROTO_PATH = __dirname + "/datanode.proto";

const nameNodeProto = getLoadedPackageDefinition(NAMENODE_PROTO_PATH);
const dataNodeProto = getLoadedPackageDefinition(DATANODE_PROTO_PATH);

const server = getServer();
server.addService(nameNodeProto.FileTransfer.service, {
  upload: (call, callback) => {
    const blocksInfo = upload(call);
    callback(null, {blocksInfo: blocksInfo});
  },
  download: (call, callback) => {
    const blocksInfo = download(call);
    callback(null, { blocksInfo: blocksInfo });
  },
});

server.addService(dataNodeProto.Logging.service, {
  login: (call, callback) => {
    const message = login(call);
    callback(null, { message: message });
  }
});

bindServer(server, `localhost:5000`);
module.exports = server;