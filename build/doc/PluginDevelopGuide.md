# SmartCanServer V2.1.0 插件开发手册

#### 杨兴锋

#### 2018-7-17

#### V1.0

____



## 1. 为什么要插件化开发:

### 1. 将系统核心与业务代码解耦，降低开发，维护成本。

## 2. 插件可以做什么:
### 1. 比如做异常处理：告警推送，异常记录等等...
### 2. 比如做实时数据分析：故障预测，流计算等等...

## 3. 脚手架工程

### 参考该目录内 testPlugin 项目
## 4. 插件开发示例
### 由于现在插件是同步调用，如果有高延时操作请自行做异步处理
### 插件包名规范
#### 包名：com.iot.nero.smartcan.plugin.impl
### 插件类名规范
#### 类名：MessageReceivedListener   (收到消息)
#### 示例：
```java
public class MessageReceivedListener implements OnMessageReceivedListener {
    @Override
    public void OnMessageReceived(Protocol protocol) {
        
        System.out.println("this from plugin2 message received");
        
    }
}
```
#### 类名：SmartFaultListener        (异常)
#### 示例：

```java
public class SmartFaultListener implements OnSmartFaultListener {
    @Override
    public void onFault(SmartFaultRequestMessage smartFaultRequestMessage) {
        System.out.println("this from plugin2 smart fault");
    }
}
```

## 部署
### 插件读取目录在config/config.properties中配置，默认读取plugin目录

###  将打包好的jar包直接丢在plugin读取目录即可