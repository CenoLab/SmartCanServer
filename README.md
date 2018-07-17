# SmartCanServer
Server for smart can

### 网联车数据采集服务器 
### asn.1 协议


# 配置
``` properties
server.host = localhost
# 服务端口
server.port = 1080

# 日志表名称
sys.log.tablename = SmartCanServerLog

# sql数据库配置
db.sql.driver = com.mysql.jdbc.Driver
db.sql.url = jdbc:mysql://localhost:3306/sso_iv
db.sql.username = root
db.sql.pwd = baby..520587

# 数据库心跳间隔 毫秒
db.sql.tickInterval = 5000


# 采集频率
sys.can.collectFrequency = 1000
# 发送频率
sys.can.sendFrequency = 1000

# 插件地址
plugin.path = plugin
```

# 插件开发
  
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
  #### 收到消息扩展点 类名：MessageReceivedListener   (收到消息)
  #### 示例：
  ```java
  public class MessageReceivedListener implements OnMessageReceivedListener {
      @Override
      public void OnMessageReceived(Protocol protocol) {
          
          System.out.println("this from plugin2 message received");
          
      }
  }
  ```
  #### 异常扩展点 类名：SmartFaultListener        (异常)
  #### 示例：
  
  ```java
  public class SmartFaultListener implements OnSmartFaultListener {
      @Override
      public void onFault(SmartFaultRequestMessage smartFaultRequestMessage) {
          System.out.println("this from plugin2 smart fault");
      }
  }
  ```
  
  #### 解码扩展点 类名：MessageDecodeListener        (解码)
  #### 示例：
    
  ```java
    public class MessageDecodeListener implements OnMessageDecodeListener {
    
        @Override
        public void onLogin(LoginRequestMessage loginRequestMessage, SocketChannel socketChannel) throws IOException {
    
        }
    
        @Override
        public void onLogout(LogoutRequestMessage logoutRequestMessage, SocketChannel socketChannel) throws IOException {
    
        }
    
        @Override
        public void onHeartBeat(HeartbeatMessage heartbeatMessage, SocketChannel socketChannel) throws IOException {
    
        }
    
        @Override
        public void onSmartCan(SmartCanRequestBody smartCanRequestBody, SocketChannel socketChannel) throws IOException {
    
        }
    
        @Override
        public void onSmartRecogrize(SmartRecognizeRequestMessage smartRecognizeRequestMessage, SocketChannel socketChannel) throws IOException {
    
        }
    
        @Override
        public void onSmartStrategy(SmartStrategyRequestMessage smartStrategyRequestMessage, SocketChannel socketChannel) throws IOException {
    
        }
    
        @Override
        public void onSmartControl(SmartControlRequestMessage smartControlRequestMessage, SocketChannel socketChannel) throws IOException {
    
        }
    
        @Override
        public void onSmartControlFeed(SmartCtrlFeedBackRequestMessage smartCtrlFeedBackRequestMessage, SocketChannel socketChannel) throws IOException {
    
        }
    
        @Override
        public void onSmartFault(SmartFaultRequestMessage smartFaultRequestMessage, SocketChannel socketChannel) throws IOException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
    
        }
    
        @Override
        public void onSmartFormATeam(SmartFromATeamRequestMessage smartFromATeamRequestMessage, SocketChannel socketChannel) throws IOException {
    
        }
    
        @Override
        public void onSmartFTeam(SmartFTeamSuccessRequestMessage smartFTeamSuccessRequestMessage, SocketChannel socketChannel) throws IOException {
    
        }
    
        @Override
        public void onSmartDissolveTeam(SmartDissolveRequestMessage smartDissolveRequestMessage, SocketChannel socketChannel) throws IOException {
    
        }
    
        @Override
        public void onSmartTeam(SmartTeamRequestMessage smartTeamRequestMessage, SocketChannel socketChannel) throws IOException {
    
        }
    
        @Override
        public void onSmartPlatonning(SmartPlatonningRequestMessage smartPlatonningRequestMessage, SocketChannel socketChannel) throws IOException {
    
        }
    }

  ```
  #### 此扩展点可以在后期做一些客户端身份识别，同步决策或者数据分析，故障预测等...
  
  
  ## 部署
  ### 插件读取目录在config/config.properties中配置，默认读取plugin目录
  
  ###  将打包好的jar包直接丢在plugin读取目录即可