## storm消息消息可靠性保证
* spout发出的消息如果超过指定时间没有ack,就会触发spout的fail方法
* 普通的spout不会重发消息，重发消息需要特殊spout比如kafka-spout