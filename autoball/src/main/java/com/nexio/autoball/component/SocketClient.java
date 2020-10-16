package com.nexio.autoball.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

@Component
public class SocketClient {
    private static final Logger logger = LoggerFactory.getLogger(SocketClient.class);

    @Value("${socket.endpoint}")
    String endPoint;


    @Value("${socket.port}")
    int port;

    public String send(String message) throws IOException{
        try(Socket socket =  new Socket(InetAddress.getByName(endPoint), port);
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());) {

            byte[] sendBytes = message.getBytes();
            output.write(sendBytes);
            output.flush();

            byte[] bytes = new byte[1024];
            input.read(bytes);
            String inputMessage = new String(bytes);
            System.out.println("Message from Server: " + inputMessage);
            return inputMessage;
        }
        catch(IOException ioException) {
            throw ioException;
        }

    }
}
