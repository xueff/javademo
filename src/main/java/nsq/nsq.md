# Nsq-docker 单机

2018年11月6日 11:53

| ①拉镜像                    | docker pull   nsqio/nsq                                      |                                             |
| -------------------------- | ------------------------------------------------------------ | ------------------------------------------- |
| ②   运行docker容器lookupd  | docker run -d   --name lookupd -p 4160:4160 -p 4161:4161 nsqio/nsq /nsqlookupd |                                             |
| ③   运行docker容器nsqd     | docker inspect -f '{{ .NetworkSettings.IPAddress   }}' lookupd | 172.17.0.2   本地为本地ip   192.168.121.107 |
| ④运行docker容器nsqd        | docker run -d --name nsqd -p 4150:4150 -p   4151:4151 nsqio/nsq /nsqd --broadcast-address=192.168.121.107   --lookupd-tcp-address=192.168.121.107:4160 |                                             |
| ⑤   运行docker容器nsqadmin | docker run -d --name nsqadmin -p 4171:4171   nsqio/nsq /nsqadmin    --lookupd-http-address=192.168.121.107:4161 |                                             |

 <http://blog.51cto.com/nosmoking/1624975>

    

# 集群

2018年11月6日 11:53

| ①②③相同单机                | A，B两台机器同时执行                                         |      |
| -------------------------- | ------------------------------------------------------------ | ---- |
| ④                          | A机执行相同 
-B机(broadcast-address:本机，lookupd-tcp-address：主机A)：

docker run --name nsqd -p 4150:4150 -p 4151:4151     nsqio/nsq /nsqd     --broadcast-address=192.168.121.226     --lookupd-tcp-address=192.168.121.107:4160 |      |
|                            |                                                              |      |
|                            |                                                              |      |
| ⑤   运行docker容器nsqadmin | A机执行，B不执行                                             |      |
| 查看集群                   | http://192.168.121.107:4171/nodes                            |      |





https://www.cnblogs.com/li-peng/p/7729174.html