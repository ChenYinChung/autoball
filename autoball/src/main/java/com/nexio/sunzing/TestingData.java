package com.nexio.sunzing;

import autoball.AutoBallLibrary;
import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

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
        //各天線開出球號，預設6個
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



    public static void main (String arg[]){


        AutoBallLibrary.BallCode ballCode = getBallCode(2);



//
//        AutoBallLibrary.BarrelStruct barrelStruct = getBarrelStruct(2);

//        AutoBallLibrary INSTANCE = (AutoBallLibrary) Native.load("AutoBall", AutoBallLibrary.class);
//
//        AutoBallLibrary.GameInfoStruct gameInfoStruct = gameInfoStruct(100,12);

//        System.out.println("test");
    }

}
