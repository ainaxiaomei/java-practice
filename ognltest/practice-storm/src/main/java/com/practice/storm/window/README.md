##storm自带的WindowBolt
* window可以基于时间也可以基于数量，window分为window长度和windwo计算间隔
* IWinowBolt的execute参数不是Tuple是TupleWindow,TupleWindow中存储了。当前的，新到的，过期的tuple
       最终是把配置传递给IComponent的实现getComponentConfiguration。有点类似storm的tick机制
       
     private final List<Tuple> tuples;
     private final List<Tuple> newTuples;
     private final List<Tuple> expiredTuples;
    
* WindowBolt的发出的bolt会自动锚定,自动ack。
* windwow bolt的三个list，当前的list固定为窗口大小,超时的list为本次淘汰的tuple,先来的list为本次到来的tuple
* WindowBolt的timestamp和watermarker

