package com.nexio.sunzing;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nexio.autoball.model.GameInfo;

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

    static String s0 ="{\"nGameNum\":100,\"dwGameTime\":1602821747,\"bsArray\":[{\"nBallCount\":1,\"BallCode\":[{\"code\":[\"Q\"]}]},{\"nBallCount\":1,\"BallCode\":[{\"code\":[\"Q\"]}]},{\"nBallCount\":1,\"BallCode\":[{\"code\":[\"J\"]}]},{\"nBallCount\":1,\"BallCode\":[{\"code\":[\"0\",\"0\"]}]},{\"nBallCount\":1,\"BallCode\":[{\"code\":[\"4\"]}]},{\"nBallCount\":1,\"BallCode\":[{\"code\":[\"3\"]}]}]}\n";
    static String s1 ="{\"nGameNum\":101,\"dwGameTime\":1602821807,\"bsArray\":[{\"nBallCount\":1,\"BallCode\":[{\"code\":[\"8\"]}]},{\"nBallCount\":1,\"BallCode\":[{\"code\":[\"7\"]}]},{\"nBallCount\":1,\"BallCode\":[{\"code\":[\"J\"]}]},{\"nBallCount\":1,\"BallCode\":[{\"code\":[\"4\"]}]},{\"nBallCount\":1,\"BallCode\":[{\"code\":[\"4\"]}]},{\"nBallCount\":1,\"BallCode\":[{\"code\":[\"3\"]}]}]}";
    static String s2 ="{\"nGameNum\":102,\"dwGameTime\":1602821867,\"bsArray\":[{\"nBallCount\":1,\"BallCode\":[{\"code\":[\"0\"]}]},{\"nBallCount\":1,\"BallCode\":[{\"code\":[\"Q\"]}]},{\"nBallCount\":1,\"BallCode\":[{\"code\":[\"0\"]}]},{\"nBallCount\":1,\"BallCode\":[{\"code\":[\"4\"]}]},{\"nBallCount\":1,\"BallCode\":[{\"code\":[\"J\"]}]},{\"nBallCount\":1,\"BallCode\":[{\"code\":[\"0\"]}]}]}";
    static String s3 ="{\"nGameNum\":103,\"dwGameTime\":1602821962,\"bsArray\":[{\"nBallCount\":1,\"BallCode\":[{\"code\":[\"J\"]}]},{\"nBallCount\":1,\"BallCode\":[{\"code\":[\"0\"]}]},{\"nBallCount\":1,\"BallCode\":[{\"code\":[\"Q\"]}]},{\"nBallCount\":1,\"BallCode\":[{\"code\":[\"0\",\"0\"]}]},{\"nBallCount\":1,\"BallCode\":[{\"code\":[\"A\"]}]},{\"nBallCount\":1,\"BallCode\":[{\"code\":[\"5\"]}]}]}\n";
    static String s4 ="{\"nGameNum\":104,\"dwGameTime\":1602822022,\"bsArray\":[{\"nBallCount\":1,\"BallCode\":[{\"code\":[\"J\"]}]},{\"nBallCount\":1,\"BallCode\":[{\"code\":[\"J\"]}]},{\"nBallCount\":1,\"BallCode\":[{\"code\":[\"Q\"]}]},{\"nBallCount\":1,\"BallCode\":[{\"code\":[\"5\"]}]},{\"nBallCount\":1,\"BallCode\":[{\"code\":[\"Q\"]}]},{\"nBallCount\":1,\"BallCode\":[{\"code\":[\"A\",\"B\",\"C\",\"D\"]}]}]}\n";
    static String s5 ="{\"nGameNum\":105,\"dwGameTime\":1602822082,\"bsArray\":[{\"nBallCount\":1,\"BallCode\":[{\"code\":[\"1\",\"0\"]}]},{\"nBallCount\":1,\"BallCode\":[{\"code\":[\"0\"]}]},{\"nBallCount\":1,\"BallCode\":[{\"code\":[\"A\",\"B\",\"C\",\"D\"]}]},{\"nBallCount\":1,\"BallCode\":[{\"code\":[\"5\"]}]},{\"nBallCount\":1,\"BallCode\":[{\"code\":[\"1\",\"0\"]}]},{\"nBallCount\":1,\"BallCode\":[{\"code\":[\"3\"]}]}]}";
    static String s6 ="{\"nGameNum\":106,\"dwGameTime\":1602822142,\"bsArray\":[{\"nBallCount\":1,\"BallCode\":[{\"code\":[\"K\"]}]},{\"nBallCount\":1,\"BallCode\":[{\"code\":[\"A\"]}]},{\"nBallCount\":1,\"BallCode\":[{\"code\":[\"A\",\"B\",\"C\",\"D\"]}]},{\"nBallCount\":1,\"BallCode\":[{\"code\":[\"A\"]}]},{\"nBallCount\":1,\"BallCode\":[{\"code\":[\"J\"]}]},{\"nBallCount\":1,\"BallCode\":[{\"code\":[\"K\"]}]}]}";
    static String s7 ="{\"nGameNum\":107,\"dwGameTime\":1602822202,\"bsArray\":[{\"nBallCount\":1,\"BallCode\":[{\"code\":[\"1\",\"0\"]}]},{\"nBallCount\":1,\"BallCode\":[{\"code\":[\"0\"]}]},{\"nBallCount\":1,\"BallCode\":[{\"code\":[\"0\"]}]},{\"nBallCount\":1,\"BallCode\":[{\"code\":[\"A\"]}]},{\"nBallCount\":1,\"BallCode\":[{\"code\":[\"1\"]}]},{\"nBallCount\":1,\"BallCode\":[{\"code\":[\"0\"]}]}]}";
    static String s8 ="{\"nGameNum\":108,\"dwGameTime\":1602822262,\"bsArray\":[{\"nBallCount\":1,\"BallCode\":[{\"code\":[\"0\"]}]},{\"nBallCount\":1,\"BallCode\":[{\"code\":[\"4\"]}]},{\"nBallCount\":1,\"BallCode\":[{\"code\":[\"J\"]}]},{\"nBallCount\":1,\"BallCode\":[{\"code\":[\"5\"]}]},{\"nBallCount\":1,\"BallCode\":[{\"code\":[\"7\"]}]},{\"nBallCount\":1,\"BallCode\":[{\"code\":[\"A\",\"B\",\"C\",\"D\"]}]}]}";
    static String s9 ="{\"nGameNum\":109,\"dwGameTime\":1602822436,\"bsArray\":[{\"nBallCount\":1,\"BallCode\":[{\"code\":[\"8\"]}]},{\"nBallCount\":1,\"BallCode\":[{\"code\":[\"7\"]}]},{\"nBallCount\":1,\"BallCode\":[{\"code\":[\"0\"]}]},{\"nBallCount\":1,\"BallCode\":[{\"code\":[\"A\"]}]},{\"nBallCount\":1,\"BallCode\":[{\"code\":[\"K\"]}]},{\"nBallCount\":1,\"BallCode\":[{\"code\":[\"5\"]}]}]}";
    static String s10 ="{\"nGameNum\":110,\"dwGameTime\":1602822838,\"bsArray\":[{\"nBallCount\":1,\"BallCode\":[{\"code\":[\"J\"]}]},{\"nBallCount\":1,\"BallCode\":[{\"code\":[\"4\"]}]},{\"nBallCount\":1,\"BallCode\":[{\"code\":[\"A\"]}]},{\"nBallCount\":1,\"BallCode\":[{\"code\":[\"9\",\"W\",\"X\"]}]},{\"nBallCount\":1,\"BallCode\":[{\"code\":[\"2\"]}]},{\"nBallCount\":1,\"BallCode\":[{\"code\":[\"5\"]}]}]}";
    static ArrayList<String> list = new ArrayList<>();
    static{
        list.add(s0);
        list.add(s1);
        list.add(s2);
        list.add(s3);
        list.add(s4);
        list.add(s5);
        list.add(s6);
        list.add(s7);
        list.add(s8);
        list.add(s9);
        list.add(s10);
    }

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

                String[] sp = inputMessage.split(",");

                String json = testData(Integer.parseInt(sp[1]));

                outputStream.writeUTF(json);
            }
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static String testData(int nGameNum) throws JsonProcessingException {
        Random random = new Random();
        int mod = random.nextInt(10);
        String src = list.get(mod);

        ObjectMapper objectMapper =  new ObjectMapper();

        GameInfo g = objectMapper.readValue(src,GameInfo.class);
        g.nGameNum = nGameNum;

        String json = objectMapper.writeValueAsString(g);
        return json;
    }

    public static void main(String args[]) throws JsonProcessingException {
//        Server.testData(1);
        new Server();
    }
}