# 在Spring Boot中使用RSocket

RSocket是一种新兴的通信协议，它是一种二进制的异步的通信协议，主要由Facebook、Netifi和Pivotal等工程师联合开发。

> 基于 Reactive Streams 规范具有**异步**、**背压支持**、**多路复用**、**基于消息通讯**、**可扩展等特性**，同时提供了 Java、JavaScript、Python、C ++、Golang、Rust 各种语言 SDK 实现，方便应用快速接入 RSocket 协议。

关于Reactive Streams规范，可以参考:

+ [reactive-streams-jvm](https://github.com/reactive-streams/reactive-streams-jvm)
+ [Reactive Streams 规范示例代码分析](https://www.jianshu.com/p/32b982d21b1c)
+ [[Reactive Streams规范及常见库](https://my.oschina.net/u/4769248/blog/4700139)](https://my.oschina.net/u/4769248/blog/4700139)
+ [如何形象的描述反应式编程中的背压(Backpressure)机制？](https://www.zhihu.com/question/49618581/answer/237078934)


RSocket支持四种不同的通讯方式：

+ Request/Response 模型：发出一个请求，就必须等待一个响应。
+ Request/Stream 模型：发出单个请求，可以接受多个响应。(pub/sub就算其中的一种[http://rsocketbyexample.info/request-stream/index.html](http://rsocketbyexample.info/request-stream/index.html))
+ Fire-and-Forget 模型：单向请求，发出一个请求后，不接受响应(打点采集、日志传输、metrics上报等)。
+ Channel(Bi-direction)模型：双向通信，可以发出多个请求，也可以接受多个请求(类似在线聊天)

目前，Spring framework 5.2版本内置了RSocket，Spring boot 2.2.0版本也支持了RSocket。
但是目前商业应用产品还很少，如：Netifi Broker。阿里开源了一款基于RSocket协议的中间件产品：[Alibaba RSocket Broker](https://github.com/alibaba/alibaba-rsocket-broker/wiki)。
