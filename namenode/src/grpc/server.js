const grpc = require("grpc");
const protoLoader = require("@grpc/proto-loader");

const {
  login,
  upload,
  download,
} = require("./controllers/namenode.controller");

const PROTO_PATH = __dirname + "/namenode.proto";

const packageDefinition = protoLoader.loadSync(PROTO_PATH, {
  keepCase: true,
  longs: String,
  enums: String,
  arrays: true,
});

const nameNodeProto = grpc.loadPackageDefinition(packageDefinition);

const server = new grpc.Server();
server.addService(nameNodeProto.NameNode.service, {
  login: (call, callback) => {
    const message = login(call);
    callback(null, { message: message });
  },
  upload: (call, callback) => {
    const blocksInfo = upload(call);

    callback(null, {blocksInfo: blocksInfo});
  },
  download: (call, callback) => {
    const blocksInfo = download(call);
    callback(null, { blocksInfo: blocksInfo });
  },
});

server.bind(`localhost:5000`, grpc.ServerCredentials.createInsecure());
server.start();
