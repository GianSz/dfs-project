const server = require("./grpc/server");
const { checkHealth } = require("./utils/sincronization");

try {
  server.start();
  console.log("NameNode server running");

  setInterval(()=>{
    console.log("Checking health");
    checkHealth();
  }, 2000);
} catch (error) {
  console.error(error);
  process.exit(1);
}
