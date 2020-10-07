### 還沒好

AutoBall 搖彩機API串接測試

接口使用DLL串接，先行測試API是否能正常運作

 curl http://localhost:8008/startGame -X POST -i -H "Content-Type:application/json" -H "Accept:application/json" -d {"nGameCount":1,"nTimeSpan":0}
 
 curl http://localhost:8008/hasGamePlayed -X POST -i -H "Content-Type:application/json" -H "Accept:application/json"
 
 curl http://localhost:8008/getGameInfo -X POST -i -H "Content-Type:application/json" -H "Accept:application/json"
 
 
 terminateGame
 suspendGame
 resumeGame
 
 
 terminateGame