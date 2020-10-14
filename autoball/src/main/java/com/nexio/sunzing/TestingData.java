package com.nexio.sunzing;

import autoball.AutoBallLibrary;
import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class TestingData {

    public static AutoBallLibrary.BallCode getBallCode(int a){
        AutoBallLibrary.BallCode ballCode = new AutoBallLibrary.BallCode.ByReference();
        byte[] bytes = new byte[8];
        for(int i = 0;i<8;i++){
            bytes[i] = (byte) ((a+i)%10+48);

        }
        ballCode.bCodeByte = bytes;
        return ballCode;
    }

    public static AutoBallLibrary.BarrelStruct getBarrelStruct(int ballCount ){
        AutoBallLibrary.BarrelStruct barrelStruct = new AutoBallLibrary.BarrelStruct.ByReference();
        barrelStruct.nBallCount = ballCount;
        for(int i=0 ; i< ballCount;i++){
            barrelStruct.bcArray[i] = getBallCode(i);
        }
        return  barrelStruct;
    }

    public static AutoBallLibrary.GameInfoStruct gameInfoStruct(int gameNum, int antenllaCount){
        AutoBallLibrary.GameInfoStruct gameInfoStruct = new AutoBallLibrary.GameInfoStruct.ByReference();

//        gameInfoStruct.nGameNum = gameNum;
//        gameInfoStruct.dwGameTime = System.currentTimeMillis();
        //??????????6?
        for (int i=0 ; i<antenllaCount ;i++){
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



    public static void main (String arg[]) {

        String dir = System.getProperty("user.dir");
        File test = new File(dir+"/Data/Ball2020101411.dat");
        try {

//            byte[] data = FileUtils.readFileToByteArray(test);
           String dataStr =  FileUtils.readFileToString(test, Charset.forName("x-windows-950"));
            byte[] data = Native.toByteArray(dataStr);
            Pointer pointer = new Memory(data.length + 1);
            pointer.write(0, data, 0, data.length);
            pointer.setByte(data.length, (byte) 0);

            AutoBallLibrary.GameInfoStruct gameInfoStruct = new AutoBallLibrary.GameInfoStruct(pointer);

            int len = gameInfoStruct.bsArray.length;

        } catch (IOException e) {
            e.printStackTrace();
        }




        AutoBallLibrary.BallCode ballCode = getBallCode(2);



//
//        AutoBallLibrary.BarrelStruct barrelStruct = getBarrelStruct(2);

//        AutoBallLibrary INSTANCE = (AutoBallLibrary) Native.load("AutoBall", AutoBallLibrary.class);
//
//        AutoBallLibrary.GameInfoStruct gameInfoStruct = gameInfoStruct(100,12);

//        System.out.println("test");
    }

}
