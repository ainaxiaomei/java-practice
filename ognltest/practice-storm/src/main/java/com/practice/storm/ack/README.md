## storm的ack与锚定机制
* tuple如果不ack会超时并触发spout的fail(),tuple从一个bolt到下一bolt需要锚定。如果不锚定，tuple的追踪到此结束，下一个bolt的ack不会触发spout的ack方法
* 如果spout发出的tuple没有mesageid,tuple不会被追踪
* 发送一个tuple由三要素，(msgid,stramid,value)
* ack机制与messgeId有关,使用异或算法来计算消息
* [Storm ACK及消息超时机制介绍](http://mp.weixin.qq.com/s/eANmHVScMOeJv-jUZ8RJpg##)

## 原理
    spout每发出一个tuple会伴随一个rootId:boltId，同时会将该id发送给acker保存。bolt收到一个tuple后，如果没有anchor,bolt执行ack()会把rootId:boltId发送给acker.acker收到id后会与
    保存的id做异或操作。如果结果为0说明tuple已经执行完成。如果bolt收到tuple后执行anchor操作，此时会生成新的bolt2Id。bolt执行ack()会把rootId:boltId^bolt2Id发送给acker。acker收到消息
    执行异或操作。rootId:boltId^boltId^bolt2Id=rootId:bolt2Id
        
    
    
    
    
  
    
    





