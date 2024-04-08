const grpc = require("grpc");
const protoLoader = require("@grpc/proto-loader");

const PROTO_PATH = __dirname + "/datanode.proto";

var packageDefinition = protoLoader.loadSync(PROTO_PATH, {
  keepCase: true,
  longs: String,
  enums: String,
  arrays: true,
});

const DataNode = grpc.loadPackageDefinition(packageDefinition).Checking;
const getClient = (ip) => {
  return new DataNode(ip + ":3000", grpc.credentials.createInsecure());
}

module.exports = getClient;