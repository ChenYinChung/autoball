package com.nexio.sunzing;
import java.net.*;
import java.io.*;
import java.util.*;

public class Server {
    private ServerSocket server;
    private Socket connection;
    private DataOutputStream outputStream;
    private DataInputStream inputStream;
    private Date date;
    private String inputMessage;

    public Server() {
        try {
            server = new ServerSocket(5566);

            System.out.println("Server Created.");
            System.out.println("Wating for client to connect…");
            while(true) {
                date = new Date();
                connection = server.accept();
                System.out.println("Connected from Client " + connection.getInetAddress().getHostAddress());
                System.out.println("Client Name: " + connection.getInetAddress().getHostName());
                System.out.println("Date: " + date);
                inputStream = new DataInputStream(connection.getInputStream());
                inputMessage = inputStream.readUTF();
                System.out.println("Message from Client: " + inputMessage);
                outputStream = new DataOutputStream(connection.getOutputStream());

                StringBuilder sb = new StringBuilder();
                sb.append("Hello").append("\r\n").append("Weclome :").append(inputMessage);


                outputStream.writeUTF(sb.toString());
            }
        }
        catch(IOException ioException) {
            ioException.printStackTrace();
            System.exit(1);
        }
    }
    public static void main(String args[]) {
        new Server();
    }
}