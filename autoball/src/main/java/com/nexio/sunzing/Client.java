package com.nexio.sunzing;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public Client() {

        try(Socket socket =  new Socket(InetAddress.getByName("127.0.0.1"), 5566);
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());) {
            String s = "startGame,108,1,0";
            output.write(s.getBytes(),0,s.getBytes().length);

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
        new Client();
    }
}