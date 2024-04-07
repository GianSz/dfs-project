const server = require("./grpc/server");

try {
  server.start();
  console.log("NameNode server running");
} catch (error) {
  console.error(error);
  process.exit(1);
}