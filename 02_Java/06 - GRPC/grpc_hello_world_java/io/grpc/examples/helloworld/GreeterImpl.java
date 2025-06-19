package io.grpc.examples.helloworld;

import io.grpc.stub.StreamObserver;

public class GreeterImpl extends GreeterGrpc.GreeterImplBase {
                        // vedere il nome delle richieste e reisposte nel file proto
    @Override           //  richiesta    --  risposta che però non è solo HelloReplay ma uno StreamObserver e HelloReplay come tipo
    public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
        System.out.println("[SERVER JAVA GRPC] Invoked sayHello");
        //                            costruisco il msg risposta-setto il msg         costruisco la risposta
        HelloReply reply = HelloReply.newBuilder().setMessage("Hello " + req.getName()).build();
        System.out.println("[SERVER JAVA GRPC] Set reply and send...");
        //invio la risposta
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
    