package com.nexio.autoball.service;

import autoball.AutoBallLibrary;
import com.nexio.autoball.component.SocketClient;
import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.RetryException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Service
@EnableRetry
public class AutoBallService {
    private static final Logger logger = LoggerFactory.getLogger(AutoBallService.class);

    @Autowired
    AutoBallLibrary autoBallLibrary;

    @Autowired
    SocketClient socketClient;

    @Retryable(value = {RetryException.class}, maxAttempts = 3, backoff = @Backoff(value = 2000))
    public Boolean init(){
        boolean isError = autoBallLibrary.ABDll_Init();
        logger.info("init dll.........");

        return isError;
    }

    public String gameStr(){
        byte[] bytes = new byte[4096];
        boolean  s = autoBallLibrary.GetGameInfoStr(bytes);
        logger.info("s....{}",s);
        logger.info("string....{}",new String(bytes));

        return new String(bytes);
    }



    /**
     * @param nGameCount
     * @param nTimeSpan
     * @return
     * @throws Exception
     */
    @Retryable(value = {RetryException.class}, maxAttempts = 3, backoff = @Backoff(value = 2000))
    public Boolean startGame(int nGameCount, int nTimeSpan,int nCurGameNum){
        boolean isError = autoBallLibrary.StartGame(nGameCount,nTimeSpan,nCurGameNum);
        logger.info("startGame...{}",isError);
        return isError;
    }

    /**
     *
     * @return
     * @throws Exception
     */
    @Retryable(value = {RetryException.class}, maxAttempts = 3, backoff = @Backoff(value = 2000))
    public boolean hasGamePlayed() {
        //HasGamePlayed
        boolean isGamePlayed = autoBallLibrary.HasGamePlayed();
        logger.info("isGamePlayed={}",isGamePlayed);
        return isGamePlayed;
    }

    /**
     *
     * @return
     * @throws Exception
     */
    @Retryable(value = {RetryException.class}, maxAttempts = 3, backoff = @Backoff(value = 2000))
    public AutoBallLibrary.GameInfoStruct  getGameInfo() {
        //GetGameInfo(GameInfoStruct &GameInfo)

        byte[] bytes = new byte[4096];
        boolean isError = autoBallLibrary.GetGameInfo(bytes);
        Pointer pointer = asPointer(bytes);
        AutoBallLibrary.GameInfoStruct gameInfoStruct = new AutoBallLibrary.GameInfoStruct(pointer);
        gameInfoStruct.read();

        logger.info("isError={}",isError);
        logger.info("getGameInfo={}",gameInfoStruct);
        return gameInfoStruct;
    }

    /**
     *
     * @return
     * @throws Exception
     */
    @Retryable(value = {RetryException.class}, maxAttempts = 3, backoff = @Backoff(value = 2000))
    public int terminateGame() {
        //TerminateGame
        int isTerrmateGame = autoBallLibrary.TerminateGame();
        logger.info("terminateGame={}",isTerrmateGame);
        return isTerrmateGame;
    }

    /**
     * SuspendGame
     * @return
     * @throws Exception
     */
    @Retryable(value = {RetryException.class}, maxAttempts = 3, backoff = @Backoff(value = 2000))
    public int suspendGame() {
        //SuspendGame
        int isSuspendGame = autoBallLibrary.SuspendGame();
        logger.info("suspendGame={}",isSuspendGame);
        return isSuspendGame;
    }

    /**
     *
     * @return
     * @throws Exception
     */
    @Retryable(value = {RetryException.class}, maxAttempts = 3, backoff = @Backoff(value = 2000))
    public int resumeGame() {
        //ResumeGame
        int isResumeGame = autoBallLibrary.ResumeGame();
        logger.info("ResumeGame={}",isResumeGame);
        return isResumeGame;
    }

    /**
     *
     * @param nCommNum
     * @param laudrate
     * @return
     * @throws Exception
     */
    @Retryable(value = {RetryException.class}, maxAttempts = 3, backoff = @Backoff(value = 2000))
    public boolean connectReader(int nCommNum, long laudrate) {
        //ConnectReader(int nCommNum, long l audrate);
        NativeLong nativeLong =  new NativeLong(laudrate);
        boolean isError = autoBallLibrary.ConnectReader(nCommNum,nativeLong);
        logger.info("ConnectReader={}",isError);
        if(!isError){
            getLastError();
        }
        return isError;
    }

    /**
     *
     * @param nCommNum
     * @param laudrate
     * @return
     * @throws Exception
     */
    @Retryable(value = {RetryException.class}, maxAttempts = 3, backoff = @Backoff(value = 2000))
    public boolean connectRD1(int nCommNum, long laudrate) {
        //ConnectRD1(int nCommNum, long l audrate);
        NativeLong nativeLong =  new NativeLong(laudrate);
        boolean isError = autoBallLibrary.ConnectRD1(nCommNum,nativeLong);
        logger.info("ConnectRD1={}",isError);
        if(!isError){
            getLastError();
        }
        return isError;
    }

    /**
     *
     * @param nCommNum
     * @param laudrate
     * @return
     * @throws Exception
     */
    @Retryable(value = {RetryException.class}, maxAttempts = 3, backoff = @Backoff(value = 2000))
    public boolean connectRD2(int nCommNum, long laudrate) {
        //ConnectRD2(int nCommNum, long l audrate);
        NativeLong nativeLong =  new NativeLong(laudrate);
        boolean isError = autoBallLibrary.ConnectRD2(nCommNum,nativeLong);
        logger.info("ConnectRD2={}",isError);
        if(!isError){
            getLastError();
        }
        return isError;
    }

    /**
     *
     * @return
     * @throws Exception
     */
    @Retryable(value = {RetryException.class}, maxAttempts = 3, backoff = @Backoff(value = 2000))
    public boolean disconnectReader() {
        //DisconnectReader (void);
        boolean isError = autoBallLibrary.DisconnectReader();
        logger.info("DisconnectReader={}",isError);
        return isError;
    }

    /**
     *
     * @return
     * @throws Exception
     */
    @Retryable(value = {RetryException.class}, maxAttempts = 3, backoff = @Backoff(value = 2000))
    public boolean disconnectRD1() {
        //DisconnectRD1 (void);
        boolean isError = autoBallLibrary.DisconnectRD1();
        logger.info("DisconnectRD1={}",isError);
        return isError;
    }

    /**
     *
     * @return
     * @throws Exception
     */
    @Retryable(value = {RetryException.class}, maxAttempts = 3, backoff = @Backoff(value = 2000))
    public boolean disconnectRD2() {
        //DisconnectRD2 (void);
        boolean isError = autoBallLibrary.DisconnectRD2();
        logger.info("DisconnectRD2={}",isError);
        return isError;
    }

    /**
     *
     * @return
     * @throws Exception
     */
    @Retryable(value = {RetryException.class}, maxAttempts = 3, backoff = @Backoff(value = 2000))
    public int getLastError() {
        //GetLastError(LPSTR strErrorMessage);

        WTypes.LPSTR lpstr = new WTypes.LPSTR(new Pointer(4096));


        byte[] bytes = new byte[4096];
        int isError =  autoBallLibrary.AB_GetLastError(bytes);
        logger.info("GetLastError={}",isError);

        String ms950 = new String(bytes, Charset.forName("x-windows-950"));
        logger.info("GetLastError MSG {}",ms950);
        logger.info("GetLastError MSG {}",new String(ms950.getBytes(Charset.forName("UTF-8"))));

        return isError;
    }

    /**
     *
     * @return
     * @throws Exception
     */
    @Retryable(value = {RetryException.class}, maxAttempts = 3, backoff = @Backoff(value = 2000))
    public boolean getAntennaPara() {
        //GetAntennaPara(LPSTR strErrorMessage);

        byte[] bytes = new byte[4096];
        boolean isError =  autoBallLibrary.GetAntennaPara(bytes);
        Pointer pointer = asPointer(bytes);
        AutoBallLibrary.AntennaSet antennaSet = new AutoBallLibrary.AntennaSet(pointer);
        antennaSet.read();

        logger.info("GetAntennaPara isError ={}",isError);
        logger.info("GetAntennaPara={}",antennaSet.aiAntennaItem);
        return isError;
    }

    /**
     *
     * @return
     * @throws Exception
     */
    @Retryable(value = {RetryException.class}, maxAttempts = 3, backoff = @Backoff(value = 2000))
    public boolean setAntennaPara() {
        //SetAntennaPara(LPSTR strAntennaPara);
        AutoBallLibrary.AntennaSet antennaSet = new AutoBallLibrary.AntennaSet.ByReference();
        byte[] bytes = new byte[4096];

        boolean isError =  autoBallLibrary.SetAntennaPara(bytes);
        logger.info("SetAntennaPara={}",isError);
        return isError;
    }

    /**
     *
     * @throws Exception
     */
    @Retryable(value = {RetryException.class}, maxAttempts = 3, backoff = @Backoff(value = 2000))
    public boolean getControlProcess() {
        //GetControlProcess(LPSTR strContorlProcess);

        byte[] bytes = new byte[4096];
        boolean isError =  autoBallLibrary.GetControlProcess(bytes);
        Pointer pointer = asPointer(bytes);
        AutoBallLibrary.ProcessFlow processFlow = new AutoBallLibrary.ProcessFlow(pointer);
        processFlow.read();

        logger.info("getControlProcess isError ={}",isError);
        logger.info("getControlProcess={}",processFlow.pfItem);

        logger.info("GetControlProcess={}",isError);
        return isError;
    }

    /**
     *
     * @throws Exception
     */
    @Retryable(value = {RetryException.class}, maxAttempts = 3, backoff = @Backoff(value = 2000))
    public boolean setControlProcess() {
        //SetControlProcess(LPSTR strContorlProcess);

        AutoBallLibrary.ProcessFlow processFlow = new AutoBallLibrary.ProcessFlow();
        byte[] bytes = new byte[4096];

        boolean isError =  autoBallLibrary.SetControlProcess(bytes);
        logger.info("SetControlProcess={}",isError);
        return isError;
    }

    /**
     *
     * @return
     * @throws Exception
     */
    @Retryable(value = {RetryException.class}, maxAttempts = 3, backoff = @Backoff(value = 2000))
    public boolean setControlStyle(int nControlStyle) {
        byte isError =  autoBallLibrary.SetControlStyle(nControlStyle);
        logger.info("SetControlStyle={}",isError);

        return isError == 1 ? true:false;
    }

    @Retryable(value = {RetryException.class}, maxAttempts = 3, backoff = @Backoff(value = 2000))
    public String socketStartGame(int nGameCount, int nTimeSpan,int nCurGameNum) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("startGame").append(",").append(nCurGameNum).append(",").append(nGameCount).append(",").append(nTimeSpan);


        return socketClient.send(sb.toString());
    }


    private Pointer asPointer(String charArray) {
        byte[] data = Native.toByteArray(charArray);
        return asPointer(data);
    }

    private Pointer asPointer(byte[] bytesArray) {
        Pointer pointer = new Memory(bytesArray.length + 1);
        pointer.write(0, bytesArray, 0, bytesArray.length);
        pointer.setByte(bytesArray.length, (byte) 0);
        return pointer;
    }

}