package com.nexio.sunzing;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public Client(String message) {

        try(Socket socket =  new Socket(InetAddress.getByName("127.0.0.1"), 5566);
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());) {
            byte[] b = message.getBytes();
            output.write(b,0,b.length);

            byte[] bytes = new byte[1024];
            input.read(bytes);
            String inputMessage = new String(bytes);
            System.out.println("Message from Server: " + inputMessage);
        }
        catch(Throwable e) {
            e.printStackTrace();
        }
    }
    public static void main(String args[]) {
        new Client("startGame,101,1,0");
        new Client("ant,1,1,1,1,1,1,1");
    }
}