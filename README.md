# JAVA_ChatRoom

> **written by lijian in 2018.12.14**

**Java实现聊天室，`mvc设计模式、socket通讯、文件传输、swingui等`**

**数据库sql：**

```sql
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for register
-- ----------------------------
DROP TABLE IF EXISTS `register`;
CREATE TABLE `register`  (
  `mail` varchar(20) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NOT NULL,
  `pass` varchar(20) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NOT NULL,
  `name` varchar(20) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NOT NULL,
  `icon` varchar(255) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NULL DEFAULT NULL,
  PRIMARY KEY (`mail`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = gb2312 COLLATE = gb2312_chinese_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

```

# 1   设计要求

本软件是一个实用聊天程序。项目要实现以下目标：

1)允许用户注册；

2)允许注册用户登录聊天室；

3)允许登录的用户修改密码；

3)登录用户之间进行聊天文件传输；

4)用户可以更改背景音乐（说明：考虑到实用性，本程序的背景音乐为通知铃声）；

## 1.1   总体要求

1.问题的描述与程序将要实现的具体功能。

2.程序功能实现的具体设计思路或框架，并检查流程设计。

3.代码实现。

4.程序测试（如程序代码、功能测试、可靠性测试和效率测试等）。

5.设计小结（包括）

## 1.2   对性能的规定

由于本软件只是一个聊天室程序，程序只是提供用户之间的聊天和文件传输，故对网络传输数据要求不是很高，只要正常的传输速度就可以了。

需要管理的数据：

用户的信息如下：用户名，密码，头像，电子邮件，地址。

 

# 2   设计思路

(1)客户端

![img](file:///C:/Users/lijian/AppData/Local/Temp/msohtmlclip1/01/clip_image002.gif)

(2)服务端

![img](file:///C:/Users/lijian/AppData/Local/Temp/msohtmlclip1/01/clip_image004.gif)

## 2.1   登录注册过程

![img](file:///C:/Users/lijian/AppData/Local/Temp/msohtmlclip1/01/clip_image006.gif)

 

## 2.2   聊天功能实现过程

![img](file:///C:/Users/lijian/AppData/Local/Temp/msohtmlclip1/01/clip_image008.gif)

# 3   程序详细设计

程序主要功能模块有登录注册模块、聊天模块、文件传输模块、显示用户信息和在线列表、更换头像背景和音乐。

## 3.1   程序的构成

JavaChatRoom(客户端)

| 包名   | 组成和功能                                                   |
| ------ | ------------------------------------------------------------ |
| handel | Client.java客户端类，与服务端连接  Reading.java读取服务端发来的数据包  WriteLogin.java 发送登录信息到服务端  WriteMessage.java发送聊天信息到服务端  WriteRegister.java发送注册信息到服务端  WriteRevisePass.java发送修改密码到服务端  WriteFile.java发送文件数据包到服务端  Bellmusic.java播放音乐的类 |
| model  | Main.java主类                                                |
| main   | DataBag.java 数据包  ListDataModel.java  在线列表模型        |
| panel  | Chatroom.java主界面  InfmPanel.java主界面用户信息部分  LoginPanel.java 登录界面  Mesgpanel.java主界面聊天部分  PriChatPanel.java私聊界面  RegisterDialog.java注册对话框  RevisePassDialog.java修改密码对话框  BackgDialog.java选择背景头像对话框  BellmDialog.java选择音乐对话框  FileDialog.java选择文件对话框  HelpDialog.java帮助对话框 |

JavaChatRoomServer (服务端)

| 包名   | 组成和功能                                                   |
| ------ | ------------------------------------------------------------ |
| handel | HandleIcon.java处理更换头像，地址插入数据库  HandleLogin.java处理登录，查询数据库  HandleRegister.java处理注册，插入信息到数据库  HandleRevisePass.java处理改密码，修改数据库密码  HandleToLineJDBC.java连接数据库  WriteallMessg.java返回群聊信息到客户端  WriteFile.java返回文件数据包到客户端  WriteLoginedInfm.java返回登录信息  WriteNameList.java返回在线列表  WritepriMessg.java返回私聊信息 |
| model  | DataBag.java数据包  Login.java登录模型  Register.java注册模型 |
| main   | Main.java主类                                                |
| server | Server.java服务端，接收客户端发来的数据包                    |



## 3.2   登录注册模块设计

用户打开程序就进入登陆界面，通过登录界面的注册按钮打开注册对话框。

登录的实现：用户输入邮箱和密码后点击登录，WriteLogin类把信息打包发送给服务端，服务端Server接收到“login”类型的数据包，解析数据包，通过HandleLogin类查询数据库是否存在该用户或密码是否正确并向客户端发送判断信息，服务器Server判断是否已经登录过，登录过就迫使已登录的下线，服务器Server把该用户用户名和输出流加到HashMap里，返回客户端用户信息和在线列表，客户端接收到判断信息，正确就显示主界面，接收到用户信息和在线列表并显示。

注册的实现：用户输入注册信息，WriteRegister类把信息打包发送给服务端，服务端Server接收到“Register”类型的数据包，解析数据包，通过HandleRegister类插入到数据库中并并向客户端发送判断信息，客户端接收判断信息判断注册成败。



## 3.3   聊天模块设计

用户可以选择群聊或者私聊。

群聊设计：通过主界面的输入框输入信息并点击发送，WriteMessage把信息打包发送到服务端，服务端接收到"allmessage"的数据包，通过writeallMessg类遍历HashMap在线列表得到所有在线的用户的输出流，并把信息发送给所有用户（除了发送者），客户端通过Reading接收数据包。

私聊设计：通过主界面的在线列表双击想要私聊的用户，进入私聊输入框输入信息并点击发送，WritepriMessg把信息和接收者打包发送到服务端，服务端接收到"primessage"的数据包，通过WritepriMessg类遍历HashMap在线列表得到接收者用户的输出流，并把信息发送给接收者用户，客户端通过Reading接收数据包。



## 3.4   文件传输设计

点击发送按钮通过FileDialog对话框选择文件，返回文件地址到WriteFile类，WriteFile类把文件数据读到一个字节数组里，并用这个数组创建数据包向服务端发送，服务端接收到类型是"file"开头的数据包并将数据包文件的数据写到服务端的硬盘上，并判断是"fileall"或者"filepri"类型的数据包，通过WriteFile类把服务端接收到的文件读到数据包，并发送到所有用户或者特定用户，客户端通过Reading接收数据包并把文件数据写在客户端硬盘上。



## 3.5   显示用户信息和在线列表

登录成功后，服务器通过HandleLogin类向数据库查询到用户名和头像地址，传给WriteLoginedInfm类，WriteLoginedInfm类向客户端返回用户名，客户端通过Reading接收"loginedname"数据包，把用户名显示；服务器通过WriteNameList类遍历HashMap在线列表得到在线名单和所有用户的输出流，并向所有用户发送在线名单数据包，客户端通过Reading接收"namelist"数据包，创建ListDataModel模型，并向界面的JList加入模型，显示列表；服务器通过WriteFile类把头像数据包返回客户端，客户端接收"fileicon"数据包，把头像数据保存在硬盘，并显示。



## 3.6   更换头像背景和音乐播放

用户可以通过菜单可以选择更换头像背景和音乐（这里的音乐为通知铃声），选择更换头像，通过BackgDialog对话框选择头像文件，通过WriteFile类打包并发送给服务端，服务端接收到类型是"file"开头的数据包并将数据包文件的数据写到服务端的硬盘上，并判断是"fileicon"类型，通过HandleIcon类把硬盘上的头像地址保存到数据库，HandleIcon类通过WriteFile类向客户端发送头像数据包，客户端通过Reading接收"fileicon"数据包，保存到硬盘是，并显示。选择更换背景或更换音乐，通过BackgDialog对话框选择背景文件，直接显示出来，通过BellmDialog对话框选择音乐文件。

音乐播放:这里的音乐为登录铃声，消息通知铃声，好友登录通知铃声，通过Bellmusic类开启线程来播放音乐，其中只有消息通知铃声能够更换。





# 4   程序运行截图

登录注册

![img](file:///C:/Users/lijian/AppData/Local/Temp/msohtmlclip1/01/clip_image010.jpg)![img](file:///C:/Users/lijian/AppData/Local/Temp/msohtmlclip1/01/clip_image012.jpg)

聊天室主界面

![img](file:///C:/Users/lijian/AppData/Local/Temp/msohtmlclip1/01/clip_image014.jpg)

群聊

![img](file:///C:/Users/lijian/AppData/Local/Temp/msohtmlclip1/01/clip_image016.jpg)

私聊

![img](file:///C:/Users/lijian/AppData/Local/Temp/msohtmlclip1/01/clip_image018.jpg)

文件传输

![img](file:///C:/Users/lijian/AppData/Local/Temp/msohtmlclip1/01/clip_image020.jpg)

更换头像和背景

![img](file:///C:/Users/lijian/AppData/Local/Temp/msohtmlclip1/01/clip_image022.jpg)

服务端

![img](file:///C:/Users/lijian/AppData/Local/Temp/msohtmlclip1/01/clip_image024.jpg)

 