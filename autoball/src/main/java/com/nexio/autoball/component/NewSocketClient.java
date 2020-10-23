package com.nexio.autoball.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class NewSocketClient implements Runnable{
    private static final Logger logger = LoggerFactory.getLogger(NewSocketClient.class);

    String message;
    String host;
    int port;
    public NewSocketClient(String host, int port,String message) {
        this.host = host;
        this.port = port;
        this.message = message;
    }

    @Override
    public void run() {
        try(Socket socket =  new Socket(InetAddress.getByName(host), port);
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
}