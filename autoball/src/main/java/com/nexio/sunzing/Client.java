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
            output.writeUTF("startGame:108,1,0");
            String inputMessage = input.readUTF();
            System.out.println("Message from Server: " + inputMessage);
        }
        catch(IOException ioException) {
            ioException.printStackTrace();
        }
    }
    public static void main(String args[]) {
        new Client();
    }
}