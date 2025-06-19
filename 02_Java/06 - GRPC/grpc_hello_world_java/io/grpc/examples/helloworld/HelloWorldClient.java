package io.grpc.examples.helloworld;

import io.grpc.Channel;
import io.grpc.Grpc;
import io.grpc.InsecureChannelCredentials;
import io.grpc.ManagedChannel;
import io.grpc.StatusRuntimeException;
import java.util.concurrent.TimeUnit;

public class HelloWorldClient {
  
  private final GreeterGrpc.GreeterBlockingStub blockingStub;

  //si poteva fare anche tutto nel main direttamente senza creare il costruttore
  /** Construct client for accessing HelloWorld server using the existing channel. */
  public HelloWorldClient(Channel channel) {
    // 'channel' here is a Channel, not a ManagedChannel, so it is not this code's responsibility to
    // shut it down.

    // Passing Channels to code makes code easier to test and makes it easier to reuse Channels.
    blockingStub = GreeterGrpc.newBlockingStub(channel);
  }

  /** Say hello to server. */
  public void greet(String name) {
    System.out.println("Will try to greet " + name + " ...");
    //creo la richiesta come nella rispota nella parte implementativa
    HelloRequest request = HelloRequest.newBuilder().setName(name).build();
    HelloReply response;
    try { //invoco il metodo e prendo la risposta
      response = blockingStub.sayHello(request);
    } catch (StatusRuntimeException e) {
      System.out.println("RPC failed: {0} " + e.getStatus());
      return;
    }
    System.out.println("Greeting: " + response.getMessage());
  }

  

  public static void main(String[] args) throws Exception {
    String user = "world";
    String target = "localhost:"+args[0];

    //creo il canale
    ManagedChannel channel = Grpc.newChannelBuilder(target, InsecureChannelCredentials.create()).build();

    //creo il client, ho bisogno del costruttore
    //si poteva fare anche tutto qua senza creare il costruttore
    try {
      HelloWorldClient client = new HelloWorldClient(channel);
      client.greet(user);
    } finally {
      channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
    }
  }
}
