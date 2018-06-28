package com.practice.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMqTest {
	
	public static void main(String[] args) throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("192.168.2.4");
		factory.setUsername("sunqi");
		factory.setPassword("sunqi");
		Connection connection = factory.newConnection();
		connection.isOpen();
		
		Channel chennel = connection.createChannel();
		chennel.basicPublish("dns.direct", "dns.private.192.168.2.3", null, "123".getBytes());
		
	}

}
