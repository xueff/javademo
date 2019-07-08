package net;

import com.jcraft.jsch.*;

import java.util.Properties;

/**
 * @create 2019/5/6 13:48
 * IntelliJ IDEA
 */
public class SftpConnFactory {
    /**
     * SFTP 登录用户名
     */
    private static String username="root";
    /**
     * SFTP 登录密码
     */
    private static String password="123456";
    /**
     * 私钥
     */
    private static String privateKey;
    /**
     * SFTP 服务器地址IP地址
     */
    private static String ip = "192.168.163.132";
    /**
     * SFTP 端口
     */
    private static int port;

    private static final SftpConnFactory FACTORY = new SftpConnFactory();

    private SftpConnFactory() {

    }

    public static SftpConnFactory getInstance() {
        return FACTORY;
    }

    public ChannelSftp makeConnection() {
        ChannelSftp client = null;
        try {
            JSch jsch = new JSch();
            if (privateKey != null) {
                // 设置私钥
                jsch.addIdentity(privateKey);
            }
            Session session = jsch.getSession(username, ip, port);
            if (password != null) {
                session.setPassword(password);
            }
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();
            Channel channel = session.openChannel("sftp");
            channel.connect();
            client = (ChannelSftp) channel;
//            log.info("sftp服务器连接成功");
        } catch (JSchException e) {
//            log.error("sftp登录失败，检测登录ip，端口号，用户名密码是否正确，错误信息为" + e.getMessage()+"用户名："+username+"  ip："+ip);
            e.printStackTrace();
        }

        return client;
    }

    /**
     * 关闭连接 server
     */
    public void logout(ChannelSftp client) {
        if (client != null) {
            if (client.isConnected()) {
                client.disconnect();
            }
            try {
                Session session = client.getSession();
                session.disconnect();
            } catch (JSchException e) {
                e.printStackTrace();
            }
        }
    }
}
