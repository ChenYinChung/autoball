package com.nexio.autoball.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class NewSocketClient {
    private static final Logger logger = LoggerFactory.getLogger(NewSocketClient.class);

    public NewSocketClient(String message) {
        try(Socket socket =  new Socket(InetAddress.getByName("127.0.0.1"), 5566);
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());) {
            byte[] b = message.getBytes();
            output.write(b,0,b.length);

            byte[] bytes = new byte[1024];
            input.read(bytes);
            String inputMessage = new String(bytes);
            logger.info("Message from Server: " + inputMessage);
        }
        catch(Throwable e) {
            e.printStackTrace();
        }
    }
    public static void main(String args[]) {
        new NewSocketClient("startGame,101,1,0");
        new NewSocketClient("ant,1,1,1,1,1,1,1");
    }
}