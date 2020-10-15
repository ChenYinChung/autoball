package com.nexio.autoball.service;

import autoball.AutoBallLibrary;
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

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Service
@EnableRetry
public class AutoBallService {
    private static final Logger logger = LoggerFactory.getLogger(AutoBallService.class);

    @Autowired
    AutoBallLibrary autoBallLibrary;

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
     * 開始開球。參數一 nGameCount表示要連續開球的次數，如果不設置默認為1，即只開一盤；參數nTimeSpan表示下一次開球與上一次開球的時間間隔，默認為0，即開完一盤接著開下一盤
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
     * 查看是否已經開出一盤球。返回True表示已經開出，false表示還未開出
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
     * 獲取開出的一局球的完整信息。返回True表示取到，false表示沒取到
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
     * 打斷并終止一局開球
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
     * 繼續掛起的開球進程
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
     * 連接Reader2000設備。參數一nCommNum是連接Reader2000的串口號，l audrate是串口波特率
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
     * 連接第一臺RD-1000設備。參數一nCommNum是連接第一臺RD-1000的串口號，l audrate是串口波特率
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
     * 連接第二臺RD-1000設備。參數一nCommNum是連接第二臺RD-1000的串口號，l audrate是串口波特率
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
     * 斷開與Reader2000設備的連接，釋放串口
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
     * 斷開與第二臺RD-1000設備的連接，釋放串口
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
     * 斷開與第二臺RD-1000設備的連接，釋放串口
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
     * 獲取最近的錯誤信息。參數strErrorMessage是錯誤的詳細說明。沒有錯誤返回0，其它表示錯誤編號
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
     * 獲取系统当前的天線设置信息。參數strAntennaPara是保存當前天線設置信息的指針。沒有錯誤返回ture，有錯誤返回false
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
     * 修改系统当前的天線设置信息。參數strAntennaPara是存有天線設置信息的指針。沒有錯誤返回ture，有錯誤返回false
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
     * 獲取系统当前的開球控制流程設置。參數strContorlProcess是保存當前開球控制流程設置的指針。沒有錯誤返回ture，有錯誤返回false。
     * @return
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
     * 修改系统当前的開球控制流程設置。參數strContorlProcess是存有開球控制流程設置的指針。沒有錯誤返回ture，有錯誤返回false。
     * @return
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
     * 修改系统当前開球流程的控制方式。參數nControlStyle是開球流程控制方式，同時開時為1，輪流開時為2，開完一個開開一個時為3。沒有錯誤返回ture，有錯誤返回false。
     * @param nControlStyle
     * @return
     * @throws Exception
     */
    @Retryable(value = {RetryException.class}, maxAttempts = 3, backoff = @Backoff(value = 2000))
    public boolean setControlStyle(int nControlStyle) {
        byte isError =  autoBallLibrary.SetControlStyle(nControlStyle);
        logger.info("SetControlStyle={}",isError);

        return isError == 1 ? true:false;
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