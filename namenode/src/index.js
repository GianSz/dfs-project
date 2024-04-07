const server = require("./grpc/server");
const {
  checkHealth,
} = require("./grpc/controllers/namenode.controller");

try {
  server.start();
  console.log("NameNode server running");

  setInterval(checkHealth, 2000);
} catch (error) {
  console.error(error);
  process.exit(1);
}