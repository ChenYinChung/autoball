### AutoBall 搖彩機API串接測試


### 流程說明　###
新版AutoBallController，多了二個功能
1.Socket
2.Http

Socket推送內容只有二個，由外部向內部溝通
port:5566
1. 設定天線
   ant,1,1,1,1,1,1
   ant固定指定是天線
   後面有六個值，１是開，０是關

2. startGame,局號,共有幾局,間隔多久
　　startGame,1234,1,0 <-只開一局
　　startGame,1234,5,30 <-開5局,每三十分鐘開一局

http推送內容給localhost:8008
  startGame結束後，會指定http://localhsot:8008
  contentType=application/x-www-form-urlencoded
  http para gameNum=1234
  http para drawResult=1,2,3,4,5_0;9;8;7;6
  說明：
  　　gameNum=1234指定期號回覆
  　　drawResult＝1,2,3,4,5_0;9;8;7;6
    　桶號_球號
  　　1,2,3,4,5 <- 桶號，以逗號區隔
  　　0;9;8;7;6 <- 開獎結果，以分號區隔