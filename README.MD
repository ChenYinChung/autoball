### AutoBall 搖彩機API串接測試


### 流程說明　###
透過socket自動觸發AutoBallController

1:JackPot每個整點執行
　-> 先搖出第六筒比例的球號數字,透過http url /draw呼叫本地website
　-> 本地website
