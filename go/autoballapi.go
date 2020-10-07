package main

import "C"
import (
	"fmt"
	"time"
)

// 一個球號，由_NUMBEROFBALLCODE_個數字或字母組成
//球號結構
type BallCode struct {
	bCodeByte [8]byte
}

// 一個天線（桶）上的所有球號的信息，_NUMBEROFBARRELBALL_是一個天線上可開的球號的最大數目
type BarrelStruct struct {
	nBallCount int          // 此天線上開出的球的個數
	bcArray    [32]BallCode // 此天線上開出的球號
}

// 一局開球的完整信息
type GameInfoStruct struct {
	nGameNum   int              // 局號
	dwGameTime int              // 開球時間
	bsArray    [12]BarrelStruct // 各天線上開出的球號
}

// 開始開球。參數一 nGameCount表示要連續開球的次數，如果不設置默認為1，即只開一盤；參數nTimeSpan表示下一次開球與上一次開球的時間間隔，默認為0，即開完一盤接著開下一盤。
//export StartGame
func StartGame(nGameCount int, nTimeSpan int) {
	fmt.Println("nGameCount:%d nTimeSpan:%d ", nGameCount, nTimeSpan)
}

// 查看是否已經開出一盤球。返回True表示已經開出，false表示還未開出。
//export HasGamePlayed
func HasGamePlayed() bool {

	fmt.Println("Current Unix Time: %v\n", time.Now().Unix())
	time.Sleep(2 * time.Second)
	fmt.Println("Current Unix Time: %v\n", time.Now().Unix())
	return true
}

// 獲取開出的一局球的完整信息。返回True表示取到，false表示沒取到。
//export GetGameInfo
func GetGameInfo(gameInfoStruct string) bool {

	return true
}

// 打斷并終止一局開球。
//export TerminateGame
func TerminateGame() int {
	return 1
}

// 掛起一局開球，即暫停開球。
//export SuspendGame
func SuspendGame() int {
	return 2
}

// 繼續掛起的開球進程。
//export ResumeGame
func ResumeGame() int {
	return 3
}

// 連接Reader2000設備。參數一nCommNum是連接Reader2000的串口號，l audrate是串口波特率。
//export ConnectReader
func ConnectReader(nCommNum int, laudrate int64) bool {
	return true
}

// 連接第一臺RD-1000設備。參數一nCommNum是連接第一臺RD-1000的串口號，l audrate是串口波特率。
//export ConnectRD1
func ConnectRD1(nCommNum int, laudrate int64) bool {
	return true
}

// 連接第二臺RD-1000設備。參數一nCommNum是連接第二臺RD-1000的串口號，l audrate是串口波特率。
//export ConnectRD2
func ConnectRD2(nCommNum int64, laudrate int64) bool {
	return true
}

// 斷開與Reader2000設備的連接，釋放串口
//export DisconnectReader
func DisconnectReader() bool {
	return true
}

// 斷開與第二臺RD-1000設備的連接，釋放串口
//export DisconnectRD1
func DisconnectRD1() bool {
	return true
}

// 斷開與第二臺RD-1000設備的連接，釋放串口
//export DisconnectRD2
func DisconnectRD2() bool {
	return true
}

// 獲取最近的錯誤信息。參數strErrorMessage是錯誤的詳細說明。沒有錯誤返回0，其它表示錯誤編號。
//export GetLastError
func GetLastError(strErrorMessage string) int {
	str1 := "GetLastError"
	strErrorMessage += str1
	return 9
}

// 獲取系统当前的天線设置信息。參數strAntennaPara是保存當前天線設置信息的指針。沒有錯誤返回ture，有錯誤返回false。
//export GetAntennaPara
func GetAntennaPara(strAntennaPara *string) bool {
	str1 := new(string)
	*str1 = "Hello GetAntennaPara"
	//strAntennaPara += str1
	strAntennaPara = str1

	return true
}

// 修改系统当前的天線设置信息。參數strAntennaPara是存有天線設置信息的指針。沒有錯誤返回ture，有錯誤返回false。
//export SetAntennaPara
func SetAntennaPara(strAntennaPara string) bool {
	str1 := "SetAntennaPara"
	strAntennaPara += str1
	return true
}

// 獲取系统当前的開球控制流程設置。參數strContorlProcess是保存當前開球控制流程設置的指針。沒有錯誤返回ture，有錯誤返回false。
//export GetControlProcess
func GetControlProcess(strContorlProcess string) bool {
	str1 := "GetControlProcess"
	strContorlProcess += str1

	return true
}

// 修改系统当前的開球控制流程設置。參數strContorlProcess是存有開球控制流程設置的指針。沒有錯誤返回ture，有錯誤返回false。
//export SetControlProcess
func SetControlProcess(strContorlProcess string) bool {
	str1 := "SetControlProcess"
	strContorlProcess += str1

	return true
}

// 修改系统当前開球流程的控制方式。參數nControlStyle是開球流程控制方式，同時開時為1，輪流開時為2，開完一個開開一個時為3。沒有錯誤返回ture，有錯誤返回false。
//export SetControlStyle
func SetControlStyle(nControlStyle int) bool {
	return true
}

//go build -o autoballapi.so -buildmode=c-shared autoballapi.go
func main() {}
