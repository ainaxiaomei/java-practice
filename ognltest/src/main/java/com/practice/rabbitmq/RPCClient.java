package com.practice.rabbitmq;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeoutException;

public class RPCClient implements AutoCloseable {

    private Connection connection;
    private Channel channel;
    private String requestQueueName = "rpc";

    public RPCClient() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.2.5");
        factory.setUsername("sunqi");
        factory.setPassword("123456");

        connection = factory.newConnection();
        channel = connection.createChannel();
    }

    public static void main(String[] argv) {
        long start = System.currentTimeMillis();
    	System.out.println("--- start rpc ");
    	
        try (RPCClient fibonacciRpc = new RPCClient()) {
        	System.out.println(" [x] Requesting fib(" + "10" + ")");
            fibonacciRpc.call("10");
            long end = System.currentTimeMillis();
            System.out.println("--- end rpc ,time cause "  +  (end - start));
        } catch (IOException | TimeoutException | InterruptedException e) {
            e.printStackTrace();
        }
        
       
    }

    public String call(String message) throws IOException, InterruptedException {
        final String corrId = UUID.randomUUID().toString();

        String replyQueueName = channel.queueDeclare().getQueue();
        AMQP.BasicProperties props = new AMQP.BasicProperties
                .Builder()
                .correlationId(corrId)
                .replyTo(replyQueueName)
                .build();

        channel.basicPublish("rpc-exchange1", requestQueueName, props, message.getBytes("UTF-8"));

        final BlockingQueue<String> response = new ArrayBlockingQueue<>(1);

        String ctag = channel.basicConsume(replyQueueName, true, (consumerTag, delivery) -> {
            if (delivery.getProperties().getCorrelationId().equals(corrId)) {
            	
            	String res = new String(delivery.getBody(), "UTF-8");
            	System.out.println("[.] Got " + res);
                response.offer(res);
            }
        }, consumerTag -> {
        });

        String result = response.take();
        channel.basicCancel(ctag);
        return result;
    }

    public void close() throws IOException {
        connection.close();
    }
}
