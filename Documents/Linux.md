# Linux

账号（root）：MiddlewareServices

xiaohuixiang（user）

密码：xk19990707

## VIM编辑器

- 在命令行下： #vi xxx 进入一般模式， 主要操作（删除，复制，粘贴）
- 在一般模式下：按i、a、o进入编辑模式，主要操作（编辑文本），按ESC退出编辑模式回到一般模式
- 在一般模式下：按**<u>"："</u>**或者**<u>"/"</u>**进入命令模式，按ESC回到一般模式

在命令模式中

| 命令 | 作用     |
| ---- | -------- |
| :w   | 写入     |
| u    | 撤回操作 |
| :q   | 退出     |

在一般模式中

| 语法      | 功能                             |
| --------- | -------------------------------- |
| yy        | 复制光标当前一行                 |
| y 数字 y  | 复制一段，从光标当前行到后n行    |
| p         | 箭头移动到目的行粘贴             |
| u         | 撤销上一步                       |
| dd        | 删除光标当前行                   |
| d 数字 d  | 删除光标（含）后多少行           |
| x         | 剪切一个字符（当前光标）         |
| X         | 剪切一个字符（当前光标的前一个） |
| yw        | 复制一个词                       |
| dw        | 删除一个词                       |
| shift + 6 | 移动到行头                       |
| shift + 4 | 移动到行尾                       |
| w         | 移动到下一个词                   |
| e         | 移动到当前词尾                   |
| 1 + G     | 移动到页头，数字                 |
| G         | 移动到页尾                       |
| 数字N + G | 移动到目标行                     |

## 网络配置和系统管理操作

### 一、 查看虚拟网络编辑器

VMware环境下：点击编辑 -> 虚拟网络编辑器 

VMware提供三种网络连接方式

- 桥接模式

	虚拟机直接连接外部物理网络的模式，主机起到了网桥的作用。这种模式下，虚拟机能直接连接外部网络，并且对外部网络是可见的

- NAT模式

	虚拟机和主机公用一个专用网络，并通过虚拟网络地址转换（NAT）设备对IP进行转换。虚拟机通过共享主机IP可以访问外部网络，但外部网络无法访问虚拟机

- 仅主机模式

	虚拟机与主机共享一个专用网络，与外部网络无法通信

### 二、 网络配置

#### 配置主机名

```shell
hostnamectl set-hostname xxxxx
```

#### 配置通讯录

- 在虚拟机中配置通讯录

	```shell
	vim /etc/hosts
	
	192.168.93.100 hadoop100
	192.168.93.101 hadoop101
	192.168.93.102 hadoop102
	192.168.93.103 hadoop103
	192.168.93.104 hadoop104
	```

- 在主机中修改host文件。文件位置`C:\Windows\System32\drivers\etc\host`不能直接修改，只能替换

## 系统管理

### Linux中的进程和服务

1. 基本语法

	systemctl start | stop | restart | status 服务名

2. 经验技巧

	查看服务的方法

	```shell
	/usr/lib/systemd/system
	```

### 配置关闭防火墙

```shell
# 查看防火墙状态
systemctl status firewalld.service

# 关闭防火墙
systemctl stop/start firewalld.service

# 关闭防火墙开机自启动
systemctl disable firewalld.service
```

### 关机重启命令

| 语法     | 作用                        |
| -------- | --------------------------- |
| sync     | 将数据由内存同步到硬盘      |
| halt     | 停机、关闭系统但不断电      |
| poweroff | 关机，断电                  |
| reboot   | 重启，等价于shutdown -r now |

```shell
# 将数据从内存读取到硬盘
sync

# 重启
reboot

# 停机（不断电）
halt

# 计算机将在一分钟后关机，并且会显示在用户的当前屏幕中
shutdown -h 1 'This server will shutdown after 1 mins'

# 立马关机
shutdown -h now

# 系统立马重启
shutdown -r now
```

## 常见命令

### 帮助命令

- 获取帮助命令

	基本语法：`man[命令或配置文件]` 获取帮助信息

- 显示说明

	| 语法        | 作用                     |
	| ----------- | ------------------------ |
	| NAME        | 命令的名称和单行描述     |
	| SYNOPSIS    | 怎样使用命令             |
	| DESCRIPTION | 命令功能的深入讨论       |
	| EXAMPLE     | 怎样使用命令的例子       |
	| SEE ALSE    | 相关主题（通常是手册页） |

### 常用快捷键

| 快捷键        | 功能             |
| ------------- | ---------------- |
| ctrl + c      | 停止进程         |
| ctrl + l      | 清屏             |
| 善于用 tab 键 | 提示             |
| 上下键        | 查找执行过的命令 |

### 文件目录类

- pwd 显示当前工作目录的绝对路径
- ls 列出目录的内容
- cd 切换到目标目录
- mkdir 创建目录
- rmdir 删除目录
- touch 创建新文件
- cp source dest     复制source文件到dest
- rm deleteFile        递归删除目录中所有内容
- mv oldNameFile newNameFile 重命名
- mv /tempmoveFile/targetFile  移动文件
- cat 查看文件
- echo > 覆写  >> 追加
- In  -s [源目录或目标] [软链接名]    给文件创建一个软链接
