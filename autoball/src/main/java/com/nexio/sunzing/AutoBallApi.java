package com.nexio.sunzing;

import autoball.AutoBallLibrary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AutoBallApi {
    static AutoBallLibrary autoBollLibrary = AutoBallLibrary.INSTANCE;
    private static final Logger logger = LoggerFactory.getLogger(AutoBallApi.class);

    /**
     * 模擬一局六個球號開始到結束
     */
    public static void startGame(boolean isTesting) {
        //開始開球。參數一 nGameCount表示要連續開球的次數，如果不設置默認為1，即只開一盤
        autoBollLibrary.StartGame(1,0);

        // 查看是否已經開出一盤球。返回True表示已經開出，false表示還未開出。
        while(!autoBollLibrary.HasGamePlayed()){
        }

        System.out.println("HasGamePlayed done");

        //取回完整訊息
        AutoBallLibrary.GameInfoStruct gameInfoStruct = new AutoBallLibrary.GameInfoStruct.ByReference();

        if(isTesting){
            //測試用
            gameInfoStruct =  TestingData.gameInfoStruct(100,6);
        }


        while(!autoBollLibrary.GetGameInfo(gameInfoStruct)){
        }

        //局號
        int nGameNum = gameInfoStruct.nGameNum;
        //開球時間
        long dwGameTime = gameInfoStruct.dwGameTime;
        //各天線上開出的球號
        AutoBallLibrary.BarrelStruct[] barrelStructs = gameInfoStruct.bsArray;

        for (AutoBallLibrary.BarrelStruct barrelStruct:barrelStructs){
            //此天線上開出的球的個數
            int nBallCount = barrelStruct.nBallCount;
            // 此天線上開出的球號
            AutoBallLibrary.BallCode[] ballCodes = barrelStruct.bcArray;

            for (AutoBallLibrary.BallCode ballCode:ballCodes){
                byte[] bCodeByte = ballCode.bCodeByte;
                System.out.println(new String(bCodeByte));
            }
        }
    }


    public static void sleep(long time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    static public void main(String argv[]) {
        startGame(true);
    }
}
