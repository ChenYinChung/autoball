package com.nexio.autoball.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.RetryException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
@EnableRetry
public class AutoBallService {
    private static final Logger logger = LoggerFactory.getLogger(AutoBallService.class);

    /**
     * 開始開球。參數一 nGameCount表示要連續開球的次數，如果不設置默認為1，即只開一盤；參數nTimeSpan表示下一次開球與上一次開球的時間間隔，默認為0，即開完一盤接著開下一盤
     * @param nGameCount
     * @param nTimeSpan
     * @return
     * @throws Exception
     */
    @Retryable(value = {RetryException.class}, maxAttempts = 3, backoff = @Backoff(value = 2000))
    public boolean startGame(int nGameCount, int nTimeSpan) throws Exception {

        //StartGame
        return true;
    }

    /**
     * 查看是否已經開出一盤球。返回True表示已經開出，false表示還未開出
     * @return
     * @throws Exception
     */
    @Retryable(value = {RetryException.class}, maxAttempts = 3, backoff = @Backoff(value = 2000))
    public boolean hasGamePlayed() throws Exception {
        //HasGamePlayed
        return true;
    }

    /**
     * 獲取開出的一局球的完整信息。返回True表示取到，false表示沒取到
     * @return
     * @throws Exception
     */
    @Retryable(value = {RetryException.class}, maxAttempts = 3, backoff = @Backoff(value = 2000))
    public boolean getGameInfo() throws Exception {
        //GetGameInfo(GameInfoStruct &GameInfo)
        return true;
    }

    /**
     * 打斷并終止一局開球
     * @return
     * @throws Exception
     */
    @Retryable(value = {RetryException.class}, maxAttempts = 3, backoff = @Backoff(value = 2000))
    public int terminateGame() throws Exception {
        //TerminateGame
        return 1;
    }

    /**
     * SuspendGame
     * @return
     * @throws Exception
     */
    @Retryable(value = {RetryException.class}, maxAttempts = 3, backoff = @Backoff(value = 2000))
    public int suspendGame() throws Exception {
        //SuspendGame
        return 1;
    }

    /**
     * 繼續掛起的開球進程
     * @return
     * @throws Exception
     */
    @Retryable(value = {RetryException.class}, maxAttempts = 3, backoff = @Backoff(value = 2000))
    public int resumeGame() throws Exception {
        //SuspendGame
        return 1;
    }

    /**
     * 連接Reader2000設備。參數一nCommNum是連接Reader2000的串口號，l audrate是串口波特率
     * @param nCommNum
     * @param laudrate
     * @return
     * @throws Exception
     */
    @Retryable(value = {RetryException.class}, maxAttempts = 3, backoff = @Backoff(value = 2000))
    public boolean connectReader(int nCommNum, long laudrate) throws Exception {
        //ConnectReader(int nCommNum, long l audrate);
        return true;
    }

    /**
     * 連接第一臺RD-1000設備。參數一nCommNum是連接第一臺RD-1000的串口號，l audrate是串口波特率
     * @param nCommNum
     * @param laudrate
     * @return
     * @throws Exception
     */
    @Retryable(value = {RetryException.class}, maxAttempts = 3, backoff = @Backoff(value = 2000))
    public boolean connectRD1(int nCommNum, long laudrate) throws Exception {
        //ConnectRD1(int nCommNum, long l audrate);
        return true;
    }

    /**
     * 連接第二臺RD-1000設備。參數一nCommNum是連接第二臺RD-1000的串口號，l audrate是串口波特率
     * @param nCommNum
     * @param laudrate
     * @return
     * @throws Exception
     */
    @Retryable(value = {RetryException.class}, maxAttempts = 3, backoff = @Backoff(value = 2000))
    public boolean connectRD2(int nCommNum, long laudrate) throws Exception {
        //ConnectRD2(int nCommNum, long l audrate);
        return true;
    }

    /**
     * 斷開與Reader2000設備的連接，釋放串口
     * @return
     * @throws Exception
     */
    @Retryable(value = {RetryException.class}, maxAttempts = 3, backoff = @Backoff(value = 2000))
    public boolean disconnectReader() throws Exception {
        //DisconnectReader (void);
        return true;
    }

    /**
     * 斷開與第二臺RD-1000設備的連接，釋放串口
     * @return
     * @throws Exception
     */
    @Retryable(value = {RetryException.class}, maxAttempts = 3, backoff = @Backoff(value = 2000))
    public boolean disconnectRD1() throws Exception {
        //DisconnectRD1 (void);
        return true;
    }

    /**
     * 斷開與第二臺RD-1000設備的連接，釋放串口
     * @return
     * @throws Exception
     */
    @Retryable(value = {RetryException.class}, maxAttempts = 3, backoff = @Backoff(value = 2000))
    public boolean disconnectRD2() throws Exception {
        //DisconnectRD2 (void);
        return true;
    }

    /**
     * 獲取最近的錯誤信息。參數strErrorMessage是錯誤的詳細說明。沒有錯誤返回0，其它表示錯誤編號
     * @return
     * @throws Exception
     */
    @Retryable(value = {RetryException.class}, maxAttempts = 3, backoff = @Backoff(value = 2000))
    public int getLastError() throws Exception {
        //GetLastError(LPSTR strErrorMessage);
        return 1;
    }

    /**
     * 獲取系统当前的天線设置信息。參數strAntennaPara是保存當前天線設置信息的指針。沒有錯誤返回ture，有錯誤返回false
     * @return
     * @throws Exception
     */
    @Retryable(value = {RetryException.class}, maxAttempts = 3, backoff = @Backoff(value = 2000))
    public boolean getAntennaPara() throws Exception {
        //GetAntennaPara(LPSTR strErrorMessage);
        return true;
    }

    /**
     * 修改系统当前的天線设置信息。參數strAntennaPara是存有天線設置信息的指針。沒有錯誤返回ture，有錯誤返回false
     * @return
     * @throws Exception
     */
    @Retryable(value = {RetryException.class}, maxAttempts = 3, backoff = @Backoff(value = 2000))
    public boolean setAntennaPara() throws Exception {
        //SetAntennaPara(LPSTR strAntennaPara);
        return true;
    }

    /**
     * 獲取系统当前的開球控制流程設置。參數strContorlProcess是保存當前開球控制流程設置的指針。沒有錯誤返回ture，有錯誤返回false。
     * @return
     * @throws Exception
     */
    @Retryable(value = {RetryException.class}, maxAttempts = 3, backoff = @Backoff(value = 2000))
    public boolean getControlProcess() throws Exception {
        //GetControlProcess(LPSTR strContorlProcess);
        return true;
    }

    /**
     * 修改系统当前的開球控制流程設置。參數strContorlProcess是存有開球控制流程設置的指針。沒有錯誤返回ture，有錯誤返回false。
     * @return
     * @throws Exception
     */
    @Retryable(value = {RetryException.class}, maxAttempts = 3, backoff = @Backoff(value = 2000))
    public boolean setControlProcess() throws Exception {
        //SetControlProcess(LPSTR strContorlProcess);
        return true;
    }

    /**
     * 修改系统当前開球流程的控制方式。參數nControlStyle是開球流程控制方式，同時開時為1，輪流開時為2，開完一個開開一個時為3。沒有錯誤返回ture，有錯誤返回false。
     * @param nControlStyle
     * @return
     * @throws Exception
     */
    @Retryable(value = {RetryException.class}, maxAttempts = 3, backoff = @Backoff(value = 2000))
    public boolean setControlStyle(int nControlStyle) throws Exception {
        //SetControlStyle(int nControlStyle);
        return true;
    }



}