# 技术栈

**前端：**Vue3 + vite + socketIO

> 采用的播放器以及弹幕插件<a href="https://nplayer.js.org/">NPlayer</a>

**后端：**SpringBoot、MyBatis-plus、Redis、MySql、RabbitMq、Netty-SocketIO

# 实现思路

## 技术难点与优化方案

### 技术难点

**1. 请求压力**

当发送弹幕的用户变多，服务器将会处理较多的弹幕，1条弹幕将要推送给所有在线的客户端，对服务器端的处理压力较大。

**2. 锁瓶颈**

- 需要维护在线用户集合
- 推送消息即遍历整个集合，顺序发送消息，耗时极长 
- 推送期间，客户端仍旧正常的上下线，集合面临不停的修改，修改需要遍历，所以集合需要上锁

**3. 数据库查询压力**

弹幕查询是一个范围查询，不好利用数据库中的索引优化，往往需要遍历，这涉及到大量的磁盘IO，占用较多时间。

### 优化思路

**1.削峰处理**

用户发送一条弹幕可以先将该弹幕异步保存到MQ中，再由消费者进行弹幕的推送处理，避免过多弹幕写请求造成服务宕机。

> 另外，对于请求压力常见的做法是分布式负载均衡进行处理，但是本架构是基于单机的，所以利用消息队列来避免瞬间的请求压力造成服务宕机。

**2.多线程优化**

在弹幕推送的时候需要遍历在线的用户集合，可以将集合拆分成多个小集合，一个集合由一个线程负责。这样就减小了锁的粒度，优化了弹幕推送速度。

**3.Redis查询优化**

弹幕查询属于较高频率的数据查询，可以将当天的弹幕数据量保存到Redis中，利用Redis的zset数据结构进行保存完成范围查询。避免走MySql涉及到IO查询速度较慢。

## 架构设计

<img src="https://lian-tuchuang.oss-cn-beijing.aliyuncs.com/image-20230209232829760.png" alt="image-20230209232829760" style="zoom:67%;" />





## 具体实现

1. 视频重新播放，前台发送Http请求从Redis中获取该视频固定条数弹幕。

2. 当用户加载到视频播放页的时候，建立WebSocket全双工通信。服务端维护多集合的客户端会话。

3. 当某端用户发送一条弹幕到服务器时

   2.1 服务器异步将该消息保存到MQ中

   2.2 服务器消费方收到消息，使用线程池并发遍历客户端会话集合推送该条弹幕。

   2.3 使用一个专门的线程将消费的消息写入Redis中。

   （注意：弹幕消息的保存，弹幕发送的时间原本例如为：2023-02-10 0:28，需要将其转换为时间戳作为zset的分数，这样才好便于范围查找）

4. 当Redis内存要满或者定时将Redis中的弹幕数据异步写入MySql中。

# 实现效果

# 参考文献

<a href="https://blog.csdn.net/Wing_93/article/details/81587809?spm=1001.2101.3001.6650.4&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-4-81587809-blog-125664457.pc_relevant_3mothn_strategy_recovery&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-4-81587809-blog-125664457.pc_relevant_3mothn_strategy_recovery&utm_relevant_index=9">csdn千万级弹幕系统设计</a>

<a href="https://blog.csdn.net/sj15814963053/article/details/125664457">仿bilibili弹幕系统设计</a>