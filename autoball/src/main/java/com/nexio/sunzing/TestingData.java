package com.nexio.sunzing;

import autoball.AutoBallLibrary;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nexio.autoball.check.CheckGameInfo;
import com.nexio.autoball.model.GameInfo;
import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

public class TestingData {
    private static final Logger logger = LoggerFactory.getLogger(TestingData.class);

    public static AutoBallLibrary.BallCode getBallCode(int a) {
        AutoBallLibrary.BallCode ballCode = new AutoBallLibrary.BallCode.ByReference();
        byte[] bytes = new byte[8];
        for (int i = 0; i < 8; i++) {
            bytes[i] = (byte) ((a + i) % 10 + 48);

        }
        ballCode.bCodeByte = bytes;
        return ballCode;
    }

    public static AutoBallLibrary.BarrelStruct getBarrelStruct(int ballCount) {
        AutoBallLibrary.BarrelStruct barrelStruct = new AutoBallLibrary.BarrelStruct.ByReference();
        barrelStruct.nBallCount = ballCount;
        for (int i = 0; i < ballCount; i++) {
            barrelStruct.bcArray[i] = getBallCode(i);
        }
        return barrelStruct;
    }

    public static AutoBallLibrary.GameInfoStruct gameInfoStruct(int gameNum, int antenllaCount) {
        AutoBallLibrary.GameInfoStruct gameInfoStruct = new AutoBallLibrary.GameInfoStruct.ByReference();

//        gameInfoStruct.nGameNum = gameNum;
//        gameInfoStruct.dwGameTime = System.currentTimeMillis();

        for (int i = 0; i < antenllaCount; i++) {
            gameInfoStruct.bsArray[i] = getBarrelStruct(32);
        }

        return gameInfoStruct;
    }

    public static Pointer asPointer(String charArray) {
        // The code is from com.sun.jna.NativeString.NativeString(java.lang.String, java.lang.String)
        byte[] data = Native.toByteArray(charArray);
        Pointer pointer = new Memory(data.length + 1);
        pointer.write(0, data, 0, data.length);
        pointer.setByte(data.length, (byte) 0);
        return pointer;
    }

    public static void getAntenna() {
        String dir = System.getProperty("user.dir");
        File ant = new File(dir + "/ANTENNA.DAT");
        byte[] data = new byte[0];
        try {
            data = FileUtils.readFileToByteArray(ant);
            Pointer pointer = new Memory(data.length + 1);
            pointer.write(0, data, 0, data.length);
            pointer.setByte(data.length, (byte) 0);
            AutoBallLibrary.AntennaSet antennaSet = new AutoBallLibrary.AntennaSet(pointer);
            antennaSet.read();
            System.out.println(antennaSet.aiAntennaItem.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getData() {
        String dir = System.getProperty("user.dir");
        File test = new File(dir + "/Data/Ball20201016.txt");
        try {

            List<String> jsons = FileUtils.readLines(test, Charset.forName("UTF-8"));
            for(String json : jsons){
                ObjectMapper objectMapper =  new ObjectMapper();
                CheckGameInfo gameInfo = objectMapper.readValue(json,CheckGameInfo.class);
                // if check in db status in running ,apply
                // call DrawService call back to SLE-CMS service
                logger.info("自動排程－檢核檔案開獎結果 {}", json);
            }

//            byte[] data = FileUtils.readFileToByteArray(test);
//            Pointer pointer = new Memory(data.length + 1);
//            pointer.write(0, data, 0, data.length);
//            pointer.setByte(data.length, (byte) 0);
//
//
//            AutoBallLibrary.GameInfoStruct gameInfoStruct = new AutoBallLibrary.GameInfoStruct(pointer);
//            gameInfoStruct.read();
//
//            AutoBallLibrary.GameInfoStruct[] gameInfoStructs = gameInfoStruct.castToArray();
//
//            for (int i = 0; i < gameInfoStruct.bsArray.length; i++) {
//                AutoBallLibrary.BarrelStruct barrelStruct = gameInfoStruct.bsArray[i];
//
//                for (int j = 0; j < barrelStruct.bcArray.length; j++) {
//                    StringBuffer stringBuffer = new StringBuffer();
//                    AutoBallLibrary.BallCode ballCode = barrelStruct.bcArray[j];
//                    for (byte b : ballCode.bCodeByte) {
//                        if (b==0) break;
//                        if (b != 0) {
//                            char c = (char) b;
//                            stringBuffer.append((char) b);
//                        }
//                    }
//
//                    if (stringBuffer.toString().length() > 0)
//                        logger.info("BS {},{} string[{}]", i, j, stringBuffer.toString());
////                        System.out.println("bs...."+stringBuffer.toString());
//                }
//            }


//            for (AutoBallLibrary.BarrelStruct barrelStruct : gameInfoStruct.bsArray) {
//                barrelStruct.read();

//                for(int i=0 ; i<barrelStruct.nBallCount ;i++){
//                    StringBuffer stringBuffer = new StringBuffer();
//                    AutoBallLibrary.BallCode ballCodes = barrelStruct.bcArray[i];
//                    ballCodes.read();
//                    for(byte b : ballCodes.bCodeByte){
//                        if(b!=0){
//                            stringBuffer.append((char)b);
//                        }
//                    }
//                    System.out.println(stringBuffer.toString());
//                }

//                for (AutoBallLibrary.BallCode ballCode : barrelStruct.bcArray) {
//                    ballCode.read();
//                    StringBuffer stringBuffer = new StringBuffer();
//                    for (byte b : ballCode.bCodeByte) {
//                        if (b == 0) break;
//                        if (b != 0) {
//                            System.out.println("b...." + (char) b);
//                            stringBuffer.append((char) b);
//                        }
//                    }
//
//                    if (stringBuffer.toString().length() > 0)
//                        System.out.println("bs...." + stringBuffer.toString());
//                }
//            }
//
//
//            System.out.println(gameInfoStruct.bsArray.length);
//
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String arg[]) {
//        getAntenna();
        getData();





//        AutoBallLibrary.GameInfoStruct gameInfoStruct = gameInfoStruct(100,12);

    }

}
