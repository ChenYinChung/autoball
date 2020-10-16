package com.nexio.sunzing;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nexio.autoball.model.BallCode;
import com.nexio.autoball.model.BsArray;
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

    private String testData(int nGameNum) throws JsonProcessingException {
        GameInfo gameInfo = new GameInfo();
        gameInfo.nGameNum = nGameNum;
        gameInfo.dwGameTime = System.currentTimeMillis();


        BsArray bsArray1 = new BsArray();
        BallCode ballCode11 = new BallCode();
        ballCode11.code = new ArrayList<>();
        ballCode11.code.add("1");
        bsArray1.nBallCount= 1;
        bsArray1.ballCode.add(ballCode11);

        BsArray bsArray2 = new BsArray();
        BallCode ballCode21 = new BallCode();
        ballCode21.code = new ArrayList<>();
        ballCode21.code.add("2");
        bsArray2.nBallCount= 1;
        bsArray2.ballCode.add(ballCode21);

        BsArray bsArray3 = new BsArray();
        BallCode ballCode31 = new BallCode();
        ballCode31.code = new ArrayList<>();
        ballCode31.code.add("3");
        bsArray3.nBallCount= 1;
        bsArray3.ballCode.add(ballCode31);

        BsArray bsArray4 = new BsArray();
        BallCode ballCode41 = new BallCode();
        ballCode41.code = new ArrayList<>();
        ballCode41.code.add("4");
        bsArray4.nBallCount= 1;
        bsArray4.ballCode.add(ballCode41);

        BsArray bsArray5 = new BsArray();
        BallCode ballCode51 = new BallCode();
        ballCode51.code = new ArrayList<>();
        ballCode51.code.add("5");
        bsArray5.nBallCount= 1;
        bsArray5.ballCode.add(ballCode51);

        BsArray bsArray6 = new BsArray();
        BallCode ballCode61 = new BallCode();
        ballCode61.code = new ArrayList<>();
        ballCode61.code.add("6");
        bsArray6.nBallCount= 2;

        BallCode ballCode62 = new BallCode();
        ballCode62.code = new ArrayList<>();
        ballCode62.code.add("7");

        bsArray6.ballCode.add(ballCode61);
        bsArray6.ballCode.add(ballCode62);

        gameInfo.bsArray = new ArrayList<>();
        gameInfo.bsArray.add(bsArray1);
        gameInfo.bsArray.add(bsArray2);
        gameInfo.bsArray.add(bsArray3);
        gameInfo.bsArray.add(bsArray4);
        gameInfo.bsArray.add(bsArray5);
        gameInfo.bsArray.add(bsArray6);

        ObjectMapper objectMapper =  new ObjectMapper();
        String json = objectMapper.writeValueAsString(gameInfo);

        return json;
    }

    public static void main(String args[]) {
        new Server();
    }
}