package io.grpc.examples.helloworld;

import io.grpc.Grpc;
import io.grpc.InsecureServerCredentials;
import io.grpc.Server;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HelloWorldServer {
  
  //server grpc
  private Server server;
  
  private void start() throws IOException {
    /* The port on which the server should run */
    int port = 0;
    
    ExecutorService executor = Executors.newFixedThreadPool(10);
    server = Grpc.newServerBuilderForPort(port, InsecureServerCredentials.create())
        .executor(executor)
        .addService(new GreeterImpl())
        .build()
        .start();
    System.out.println("[HelloWorldServer] Server started, listening on " + server.getPort());
    
    /*
    Runtime.getRuntime().addShutdownHook(new Thread() {
      @Override
      public void run() {
      
        try {
          System.out.println("Starting to shutdown the server...");
          server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        } finally {
          System.out.println("Executor shutdown...");
          executor.shutdown();
        }
        System.out.println("[SERVER JAVA GRPC] server shutdown...");
      }
    });
     */
    
    try {
      System.out.println("[HelloWorldServer] Await for termination...");
      server.awaitTermination();
    } catch (InterruptedException e) {
      // HANDLE
      e.printStackTrace();
    }
  }

  
  public static void main(String[] args) throws IOException, InterruptedException {
    //creo il server
    final HelloWorldServer hello_server = new HelloWorldServer();
    hello_server.start();
    //hello_server.getServer().awaitTermination();
  } 
  
}
