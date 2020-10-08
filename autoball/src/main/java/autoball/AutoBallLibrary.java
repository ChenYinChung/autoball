package autoball;

import com.ochafik.lang.jnaerator.runtime.Structure;
import com.sun.jna.*;
import com.sun.jna.platform.win32.WTypes;

import java.util.Arrays;
import java.util.List;

/**
 * JNA Wrapper for library <b>AutoBoll</b><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> , <a href="http://rococoa.dev.java.net/">Rococoa</a>, or <a href="http://jna.dev.java.net/">JNA</a>.
 */
public interface AutoBallLibrary extends Library {
    public static final int _NUMBEROFBALLCODE_ = (int)8;
    public static final int _NUMBEROFBARRELBALL_ = (int)32;
    public static class BallCode extends Structure<BallCode, BallCode.ByValue, BallCode.ByReference > {
        /** C type : BYTE[8] */
        public byte[] bCodeByte = new byte[8];
        public BallCode() {
            super();
        }
        protected List<String> getFieldOrder() {
            return Arrays.asList("bCodeByte");
        }
        /** @param bCodeByte C type : BYTE[8] */
        public BallCode(byte bCodeByte[]) {
            super();
            if ((bCodeByte.length != this.bCodeByte.length))
                throw new IllegalArgumentException("Wrong array size !");
            this.bCodeByte = bCodeByte;
        }
        public BallCode(Pointer peer) {
            super(peer);
        }
        protected ByReference newByReference() { return new ByReference(); }
        protected ByValue newByValue() { return new ByValue(); }
        protected BallCode newInstance() { return new BallCode(); }
        public static BallCode[] newArray(int arrayLength) {
            return Structure.newArray(BallCode.class, arrayLength);
        }
        public static class ByReference extends BallCode implements Structure.ByReference {

        };
        public static class ByValue extends BallCode implements Structure.ByValue {

        };
    };
    public static class BarrelStruct extends Structure<BarrelStruct, BarrelStruct.ByValue, BarrelStruct.ByReference > {
        /** 此天線上開出的球的個數 */
        public int nBallCount;
        /**
         * 此天線上開出的球號<br>
         * C type : BallCode[32]
         */
        public BallCode[] bcArray = new BallCode[32];
        public BarrelStruct() {
            super();
        }
        protected List<String> getFieldOrder() {
            return Arrays.asList("nBallCount", "bcArray");
        }
        /**
         * @param nBallCount 此天線上開出的球的個數<br>
         * @param bcArray 此天線上開出的球號<br>
         * C type : BallCode[32]
         */
        public BarrelStruct(int nBallCount, BallCode bcArray[]) {
            super();
            this.nBallCount = nBallCount;
            if ((bcArray.length != this.bcArray.length))
                throw new IllegalArgumentException("Wrong array size !");
            this.bcArray = bcArray;
        }
        public BarrelStruct(Pointer peer) {
            super(peer);
        }
        protected ByReference newByReference() { return new ByReference(); }
        protected ByValue newByValue() { return new ByValue(); }
        protected BarrelStruct newInstance() { return new BarrelStruct(); }
        public static BarrelStruct[] newArray(int arrayLength) {
            return Structure.newArray(BarrelStruct.class, arrayLength);
        }
        public static class ByReference extends BarrelStruct implements Structure.ByReference {

        };
        public static class ByValue extends BarrelStruct implements Structure.ByValue {

        };
    };
    public static class GameInfoStruct extends Structure<GameInfoStruct, GameInfoStruct.ByValue, GameInfoStruct.ByReference > {
        /** 局號 */
        public int nGameNum;
        /** 開球時間 */
        public long dwGameTime;
        /**
         * 各天線上開出的球號<br>
         * C type : BarrelStruct[12]
         */
        public BarrelStruct[] bsArray = new BarrelStruct[12];
        public GameInfoStruct() {
            super();
        }
        protected List<String> getFieldOrder() {
            return Arrays.asList("nGameNum", "dwGameTime", "bsArray");
        }
        /**
         * @param nGameNum 局號<br>
         * @param dwGameTime 開球時間<br>
         * @param bsArray 各天線上開出的球號<br>
         * C type : BarrelStruct[12]
         */
        public GameInfoStruct(int nGameNum, int dwGameTime, BarrelStruct bsArray[]) {
            super();
            this.nGameNum = nGameNum;
            this.dwGameTime = dwGameTime;
            if ((bsArray.length != this.bsArray.length))
                throw new IllegalArgumentException("Wrong array size !");
            this.bsArray = bsArray;
        }
        public GameInfoStruct(Pointer peer) {
            super(peer);
        }
        protected ByReference newByReference() { return new ByReference(); }
        protected ByValue newByValue() { return new ByValue(); }
        protected GameInfoStruct newInstance() { return new GameInfoStruct(); }
        public static GameInfoStruct[] newArray(int arrayLength) {
            return Structure.newArray(GameInfoStruct.class, arrayLength);
        }
        public static class ByReference extends GameInfoStruct implements Structure.ByReference {

        };
        public static class ByValue extends GameInfoStruct implements Structure.ByValue {

        };
    };
    /**
     * 開始開球。參數一 nGameCount表示要連續開球的次數，如果不設置默認為1，即只開一盤；參數nTimeSpan表示下一次開球與上一次開球的時間間隔，默認為0，即開完一盤接著開下一盤。<br>
     * Original signature : <code>void StartGame(int, int)</code><br>
     * <i>native declaration : line 41</i>
     */
    void StartGame(int nGameCount, int nTimeSpan);
    /**
     * 查看是否已經開出一盤球。返回True表示已經開出，false表示還未開出。<br>
     * Original signature : <code>bool HasGamePlayed()</code><br>
     * <i>native declaration : line 44</i>
     */
    boolean HasGamePlayed();
    /**
     * 獲取開出的一局球的完整信息。返回True表示取到，false表示沒取到。<br>
     * Original signature : <code>bool GetGameInfo(GameInfoStruct&)</code><br>
     * <i>native declaration : line 47</i>
     */
    boolean GetGameInfo(GameInfoStruct GameInfo);
    /**
     * 打斷并終止一局開球。<br>
     * Original signature : <code>int TerminateGame()</code><br>
     * <i>native declaration : line 50</i>
     */
    int TerminateGame();
    /**
     * 掛起一局開球，即暫停開球。<br>
     * Original signature : <code>int SuspendGame()</code><br>
     * <i>native declaration : line 53</i>
     */
    int SuspendGame();
    /**
     * 繼續掛起的開球進程。<br>
     * Original signature : <code>int ResumeGame()</code><br>
     * <i>native declaration : line 56</i>
     */
    int ResumeGame();
    /**
     * 連接Reader2000設備。參數一nCommNum是連接Reader2000的串口號，l audrate是串口波特率。<br>
     * Original signature : <code>bool ConnectReader(int, long)</code><br>
     * <i>native declaration : line 59</i>
     */
    boolean ConnectReader(int nCommNum, NativeLong laudrate);
    /**
     * 連接第一臺RD-1000設備。參數一nCommNum是連接第一臺RD-1000的串口號，l audrate是串口波特率。<br>
     * Original signature : <code>bool ConnectRD1(int, long)</code><br>
     * <i>native declaration : line 62</i>
     */
    boolean ConnectRD1(int nCommNum, NativeLong laudrate);
    /**
     * 連接第二臺RD-1000設備。參數一nCommNum是連接第二臺RD-1000的串口號，l audrate是串口波特率。<br>
     * Original signature : <code>bool ConnectRD2(int, long)</code><br>
     * <i>native declaration : line 65</i>
     */
    boolean ConnectRD2(int nCommNum, NativeLong laudrate);
    /**
     * 斷開與Reader2000設備的連接，釋放串口<br>
     * Original signature : <code>bool DisconnectReader()</code><br>
     * <i>native declaration : line 68</i>
     */
    boolean DisconnectReader();
    /**
     * 斷開與第二臺RD-1000設備的連接，釋放串口<br>
     * Original signature : <code>bool DisconnectRD1()</code><br>
     * <i>native declaration : line 71</i>
     */
    boolean DisconnectRD1();
    /**
     * 斷開與第二臺RD-1000設備的連接，釋放串口<br>
     * Original signature : <code>bool DisconnectRD2()</code><br>
     * <i>native declaration : line 74</i>
     */
    boolean DisconnectRD2();
    /**
     * 獲取最近的錯誤信息。參數strErrorMessage是錯誤的詳細說明。沒有錯誤返回0，其它表示錯誤編號。<br>
     * Original signature : <code>int GetLastError(LPSTR)</code><br>
     * <i>native declaration : line 77</i>
     */
    int GetLastError(WTypes.LPSTR strErrorMessage);
    /**
     * 獲取系统当前的天線设置信息。參數strAntennaPara是保存當前天線設置信息的指針。沒有錯誤返回ture，有錯誤返回false。<br>
     * Original signature : <code>bool GetAntennaPara(LPSTR)</code><br>
     * <i>native declaration : line 80</i>
     */
    boolean GetAntennaPara(WTypes.LPSTR strAntennaPara);
    /**
     * 修改系统当前的天線设置信息。參數strAntennaPara是存有天線設置信息的指針。沒有錯誤返回ture，有錯誤返回false。<br>
     * Original signature : <code>bool SetAntennaPara(LPSTR)</code><br>
     * <i>native declaration : line 83</i>
     */
    boolean SetAntennaPara(WTypes.LPSTR strAntennaPara);
    /**
     * 獲取系统当前的開球控制流程設置。參數strContorlProcess是保存當前開球控制流程設置的指針。沒有錯誤返回ture，有錯誤返回false。<br>
     * Original signature : <code>bool GetControlProcess(LPSTR)</code><br>
     * <i>native declaration : line 86</i>
     */
    boolean GetControlProcess(WTypes.LPSTR strContorlProcess);
    /**
     * 修改系统当前的開球控制流程設置。參數strContorlProcess是存有開球控制流程設置的指針。沒有錯誤返回ture，有錯誤返回false。<br>
     * Original signature : <code>bool SetControlProcess(LPSTR)</code><br>
     * <i>native declaration : line 89</i>
     */
    boolean SetControlProcess(WTypes.LPSTR strContorlProcess);
    /**
     * 修改系统当前開球流程的控制方式。參數nControlStyle是開球流程控制方式，同時開時為1，輪流開時為2，開完一個開開一個時為3。沒有錯誤返回ture，有錯誤返回false。<br>
     * Original signature : <code>bool SetControlStyle(int)</code><br>
     * <i>native declaration : line 92</i>
     */
    byte SetControlStyle(int nControlStyle);
}
