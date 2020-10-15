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
    public static final int _ANTENNACOUNT_ = (int)12;
    public static final int _NUMBEROFCPITEMS_ = (int)40;
    public static final int _SPANBETWEENTWOCOMM_115200 = (int)30;
    public static final int _SPANBETWEENTWOCOMM_9600 = (int)50;
    public static final int _COMSENDMAXRETRY_ = (int)5;
    public static final int _COMSENDTIMEOUT_ = (int)500;
    public static final int _MAXGAMES_ = (int)256;

    public static class AntennaItem extends Structure<AntennaItem, AntennaItem.ByValue, AntennaItem.ByReference > {
        /** 天線號 */
        public int nAntennaID;
        /** 掃描狀態： true 掃描中， false 未開啟掃描 */
        public byte bScanState;
        /** 開球次數 */
        public int nOpenTimes;
        /** 開球節點 */
        public int nOpenBallNode;
        /** 開球1節點 */
        public int nOpenBallNode1;
        /** 開球2節點 */
        public int nOpenBallNode2;
        /** 上球感應節點（RD1000輸入節點） */
        public int nInductionNode;
        /** 鼓風機節點 */
        public int nAirBlowerNode;
        /** 天線功率 取值在13至48之間 */
        public int nAntennaPower;
        public AntennaItem() {
            super();
        }
        protected List<String> getFieldOrder() {
            return Arrays.asList("nAntennaID", "bScanState", "nOpenTimes", "nOpenBallNode", "nOpenBallNode1", "nOpenBallNode2", "nInductionNode", "nAirBlowerNode", "nAntennaPower");
        }
        /**
         * @param nAntennaID 天線號<br>
         * @param bScanState 掃描狀態： true 掃描中， false 未開啟掃描<br>
         * @param nOpenTimes 開球次數<br>
         * @param nOpenBallNode 開球節點<br>
         * @param nOpenBallNode1 開球1節點<br>
         * @param nOpenBallNode2 開球2節點<br>
         * @param nInductionNode 上球感應節點（RD1000輸入節點）<br>
         * @param nAirBlowerNode 鼓風機節點<br>
         * @param nAntennaPower 天線功率 取值在13至48之間
         */
        public AntennaItem(int nAntennaID, byte bScanState, int nOpenTimes, int nOpenBallNode, int nOpenBallNode1, int nOpenBallNode2, int nInductionNode, int nAirBlowerNode, int nAntennaPower) {
            super();
            this.nAntennaID = nAntennaID;
            this.bScanState = bScanState;
            this.nOpenTimes = nOpenTimes;
            this.nOpenBallNode = nOpenBallNode;
            this.nOpenBallNode1 = nOpenBallNode1;
            this.nOpenBallNode2 = nOpenBallNode2;
            this.nInductionNode = nInductionNode;
            this.nAirBlowerNode = nAirBlowerNode;
            this.nAntennaPower = nAntennaPower;
        }
        public AntennaItem(Pointer peer) {
            super(peer);
        }
        protected ByReference newByReference() { return new ByReference(); }
        protected ByValue newByValue() { return new ByValue(); }
        protected AntennaItem newInstance() { return new AntennaItem(); }
        public static AntennaItem[] newArray(int arrayLength) {
            return Structure.newArray(AntennaItem.class, arrayLength);
        }
        public static class ByReference extends AntennaItem implements Structure.ByReference {

        };
        public static class ByValue extends AntennaItem implements Structure.ByValue {

        };
    };
    public static class AntennaSet extends Structure<AntennaSet, AntennaSet.ByValue, AntennaSet.ByReference > {
        /** 【落球/回球】節點 */
        public int nFillBallNode;
        /** C type : AntennaItem[12] */
        public AutoBallLibrary.AntennaItem[] aiAntennaItem = new AutoBallLibrary.AntennaItem[12];
        public AntennaSet() {
            super();
        }
        protected List<String> getFieldOrder() {
            return Arrays.asList("nFillBallNode", "aiAntennaItem");
        }
        /**
         * @param nFillBallNode 【落球/回球】節點<br>
         * @param aiAntennaItem C type : AntennaItem[12]
         */
        public AntennaSet(int nFillBallNode, AutoBallLibrary.AntennaItem aiAntennaItem[]) {
            super();
            this.nFillBallNode = nFillBallNode;
            if ((aiAntennaItem.length != this.aiAntennaItem.length))
                throw new IllegalArgumentException("Wrong array size !");
            this.aiAntennaItem = aiAntennaItem;
        }
        public AntennaSet(Pointer peer) {
            super(peer);
        }
        protected ByReference newByReference() { return new ByReference(); }
        protected ByValue newByValue() { return new ByValue(); }
        protected AntennaSet newInstance() { return new AntennaSet(); }
        public static AntennaSet[] newArray(int arrayLength) {
            return Structure.newArray(AntennaSet.class, arrayLength);
        }
        public static class ByReference extends AntennaSet implements Structure.ByReference {

        };
        public static class ByValue extends AntennaSet implements Structure.ByValue {

        };
    };
    public static class ProcessFlowItem extends Structure<ProcessFlowItem, ProcessFlowItem.ByValue, ProcessFlowItem.ByReference > {
        /** 操作 */
        public int nOperation;
        /** 條件 */
        public int nCondition;
        /** 持續時間 單位是100ms */
        public int nTime;
        public ProcessFlowItem() {
            super();
        }
        protected List<String> getFieldOrder() {
            return Arrays.asList("nOperation", "nCondition", "nTime");
        }
        /**
         * @param nOperation 操作<br>
         * @param nCondition 條件<br>
         * @param nTime 持續時間 單位是100ms
         */
        public ProcessFlowItem(int nOperation, int nCondition, int nTime) {
            super();
            this.nOperation = nOperation;
            this.nCondition = nCondition;
            this.nTime = nTime;
        }
        public ProcessFlowItem(Pointer peer) {
            super(peer);
        }
        protected ByReference newByReference() { return new ByReference(); }
        protected ByValue newByValue() { return new ByValue(); }
        protected ProcessFlowItem newInstance() { return new ProcessFlowItem(); }
        public static ProcessFlowItem[] newArray(int arrayLength) {
            return Structure.newArray(ProcessFlowItem.class, arrayLength);
        }
        public static class ByReference extends ProcessFlowItem implements Structure.ByReference {

        };
        public static class ByValue extends ProcessFlowItem implements Structure.ByValue {

        };
    };
    public static class ProcessFlow extends Structure<ProcessFlow, ProcessFlow.ByValue, ProcessFlow.ByReference > {
        /** 流程控制類型 三種： 0 同時開，	1 輪流開，	2 開完一個再開一個 */
        public int nControlStyle;
        /**
         * 流程控制項<br>
         * C type : ProcessFlowItem[_NUMBEROFCPITEMS_]
         */
        public AutoBallLibrary.ProcessFlowItem[] pfItem = new AutoBallLibrary.ProcessFlowItem[_NUMBEROFCPITEMS_];
        public ProcessFlow() {
            super();
        }
        protected List<String> getFieldOrder() {
            return Arrays.asList("nControlStyle", "pfItem");
        }
        /**
         * @param nControlStyle 流程控制類型 三種： 0 同時開，	1 輪流開，	2 開完一個再開一個<br>
         * @param pfItem 流程控制項<br>
         * C type : ProcessFlowItem[_NUMBEROFCPITEMS_]
         */
        public ProcessFlow(int nControlStyle, AutoBallLibrary.ProcessFlowItem pfItem[]) {
            super();
            this.nControlStyle = nControlStyle;
            if ((pfItem.length != this.pfItem.length))
                throw new IllegalArgumentException("Wrong array size !");
            this.pfItem = pfItem;
        }
        public ProcessFlow(Pointer peer) {
            super(peer);
        }
        protected ByReference newByReference() { return new ByReference(); }
        protected ByValue newByValue() { return new ByValue(); }
        protected ProcessFlow newInstance() { return new ProcessFlow(); }
        public static ProcessFlow[] newArray(int arrayLength) {
            return Structure.newArray(ProcessFlow.class, arrayLength);
        }
        public static class ByReference extends ProcessFlow implements Structure.ByReference {

        };
        public static class ByValue extends ProcessFlow implements Structure.ByValue {

        };
    };

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


    boolean ABDll_Init();
    /**
     * 開始開球。參數一 nGameCount表示要連續開球的次數，如果不設置默認為1，即只開一盤；參數nTimeSpan表示下一次開球與上一次開球的時間間隔，默認為0，即開完一盤接著開下一盤。<br>
     * Original signature : <code>void StartGame(int, int)</code><br>
     * <i>native declaration : line 41</i>
     */
    boolean StartGame(int nGameCount, int nTimeSpan,int nCurGameNum);
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
    boolean GetGameInfo(byte[] GameInfo);

    boolean GetGameInfoStr(byte[] bytes);
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
    int AB_GetLastError(byte[] bytes);
    /**
     * 獲取系统当前的天線设置信息。參數strAntennaPara是保存當前天線設置信息的指針。沒有錯誤返回ture，有錯誤返回false。<br>
     * Original signature : <code>bool GetAntennaPara(LPSTR)</code><br>
     * <i>native declaration : line 80</i>
     */
    boolean GetAntennaPara(byte[] strAntennaPara);
    /**
     * 修改系统当前的天線设置信息。參數strAntennaPara是存有天線設置信息的指針。沒有錯誤返回ture，有錯誤返回false。<br>
     * Original signature : <code>bool SetAntennaPara(LPSTR)</code><br>
     * <i>native declaration : line 83</i>
     */
    boolean SetAntennaPara(byte[] strAntennaPara);
    /**
     * 獲取系统当前的開球控制流程設置。參數strContorlProcess是保存當前開球控制流程設置的指針。沒有錯誤返回ture，有錯誤返回false。<br>
     * Original signature : <code>bool GetControlProcess(LPSTR)</code><br>
     * <i>native declaration : line 86</i>
     */
    boolean GetControlProcess(byte[] strContorlProcess);
    /**
     * 修改系统当前的開球控制流程設置。參數strContorlProcess是存有開球控制流程設置的指針。沒有錯誤返回ture，有錯誤返回false。<br>
     * Original signature : <code>bool SetControlProcess(LPSTR)</code><br>
     * <i>native declaration : line 89</i>
     */
    boolean SetControlProcess(byte[] strContorlProcess);
    /**
     * 修改系统当前開球流程的控制方式。參數nControlStyle是開球流程控制方式，同時開時為1，輪流開時為2，開完一個開開一個時為3。沒有錯誤返回ture，有錯誤返回false。<br>
     * Original signature : <code>bool SetControlStyle(int)</code><br>
     * <i>native declaration : line 92</i>
     */
    byte SetControlStyle(int nControlStyle);
}
