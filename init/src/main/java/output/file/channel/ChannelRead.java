package output.file.channel;

import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/*
 * 1.通道（channel）：用于源节点于目标节点的连接，在Java NIO中负责缓冲区数据的传输。
 * channel本身不存储数据的，因此需要配合缓冲区传输‘
 * 2.通道的主要实现类
 *  java.nio.channels.Channel接口
 *     |FileChannel    本地的文件通道
 *     |SocketChannel   网络 TCP
 *     |ServerSocketChannel  网络TCP
 *     |DatagramChannel    网络 UDP
 *
 * 3.获取通道
 *       ①.java针对支持通道的类提供了getChannel()方法
 *
 *                 本地：
 *                       FileInputStream/FileOutputStream
 *                       RandomAccessFile//随机存储文件流
 *
 *                 网络IO：
 *                       Socket
 *                       ServerSocket
 *                       DatagremSocket
 *
 *       ②.jdk1.7NIO.2针对各个通道提供静态方法open()
 *       ③.jdk1.7中的NIO.2的Files工具类的newByteChannel()
 *  4.通道之间的数据传输
 *    transferFrom()
 *    transferTo()
 *
 *  5.
 *  分散(Scatter)与聚集(Gather)
 *  分散读取(Scattering Reads):将通道中的数据分散到多个缓冲区中
 *  聚集写入(Gathering Writes):将多个缓冲区中的数据聚集到通道中
 *
 *  6.字符集：Charset
 *  编码：字符串->字节数组
 *  解码：字节数组->字符串
 */
public class ChannelRead {
    static  FileChannel channelIn = null;
    static  ByteBuffer[] bufs = null;


    public static void main(String[] args) throws IOException {
        ChannelRead channelRead = new ChannelRead();
        channelRead.getChannel("");

        channelRead.readChannel(bufs,8,6);
    }


    public FileChannel getChannel(String filePath) throws FileNotFoundException {
        RandomAccessFile raf = new RandomAccessFile("D:\\360极速浏览器下载\\心理学资源\\人格心理学.txt", "rw");
        // 分散读取，通道中数据分散到多个缓冲区中
        // 1.获取通道
        channelIn = raf.getChannel();
        // 2.分配通道大小

        try {
//            int mbs = (int)raf.length()/1024*1024;
            int mbs = (int)raf.length()/1024;
            if(mbs<=2){
                mbs = 2;
            }else {
                mbs = 10;
            }
            bufs = new ByteBuffer[10];

            for (int i = 0;i<mbs;i++) {
                bufs[i] = ByteBuffer.allocate(24*8);
//                bufs[i] = ByteBuffer.allocate(24*1024);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return channelIn;

    }
    public FileChannel readChannel(ByteBuffer[] bufs,int start,int length) throws IOException {


        // 3.将通道中的数据存入缓冲区中
        while ((channelIn.read(bufs,start,length)) != -1) {
            for (ByteBuffer by : bufs) {
                by.flip();
            }

            for (int i = 0;i<bufs.length;i++) {
                System.out.print(i+" ==  ");
                System.out.println(new String(bufs[i].array(), 0, bufs[i].limit(),"GBK"));
            }

            for (ByteBuffer by : bufs) {
                by.clear();
            }
        }
        return channelIn;

    }


    // 分散和聚集 分批read
    @Test
    public void test5() throws IOException {
        RandomAccessFile raf = new RandomAccessFile("D:\\360极速浏览器下载\\心理学资源\\人格心理学.txt", "rw");
        // 分散读取，通道中数据分散到多个缓冲区中
        // 1.获取通道
        FileChannel channel = raf.getChannel();
        // 2.分配通道大小
        ByteBuffer buf1 = ByteBuffer.allocate(240);
        ByteBuffer buf2 = ByteBuffer.allocate(240);

        // 3.分散读取
        ByteBuffer[] bufs = { buf1, buf2 };

        // 4.聚集写入
        RandomAccessFile raf2 = new RandomAccessFile("D:\\360极速浏览器下载\\心理学资源\\人格心理学2.txt", "rw");
        FileChannel channel2 = raf2.getChannel();


        // 3.将通道中的数据存入缓冲区中
        while ((channel.read(bufs)) != -1) {
            for (ByteBuffer by : bufs) {
                by.flip();
            }
            System.out.println(new String(bufs[0].array(), 0, bufs[0].limit(),"GBK"));
            System.out.println("=================");
            System.out.println(new String(bufs[1].array(), 0, bufs[1].limit(),"GBK"));
            channel2.write(bufs);

            for (ByteBuffer by : bufs) {
                by.clear();
            }
        }

    }

}