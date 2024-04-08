const grpc = require("grpc");
const protoLoader = require("@grpc/proto-loader");

const getLoadedPackageDefinition = (path) => {
    const packageDefinton = protoLoader.loadSync(path, {
        keepCase: true,
        longs: String,
        enums: String,
        arrays: true,
    });

    const proto = grpc.loadPackageDefinition(packageDefinton);
    return proto;
}

const getServer = () => {
    return new grpc.Server();
};
const bindServer = (server, address) => {
    server.bind(address, grpc.ServerCredentials.createInsecure());
};

module.exports = { 
    getLoadedPackageDefinition,
    getServer,
    bindServer,
};