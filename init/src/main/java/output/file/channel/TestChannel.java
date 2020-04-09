package output.file.channel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.junit.Test;

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
public class TestChannel {

    // 分散和聚集
    @Test
    public void test4() throws IOException {
        RandomAccessFile raf = new RandomAccessFile("D:\\360极速浏览器下载\\心理学资源\\人格心理学.txt", "rw");
        // 分散读取，通道中数据分散到多个缓冲区中
        // 1.获取通道
        FileChannel channel = raf.getChannel();
        // 2.分配通道大小
        ByteBuffer buf1 = ByteBuffer.allocate(100);
        ByteBuffer buf2 = ByteBuffer.allocate(500);
        // 3.分散读取
        ByteBuffer[] bufs = { buf1, buf2 };
        channel.read(bufs);
        for (ByteBuffer by : bufs) {
            by.flip();
        }
        System.out.println(new String(bufs[0].array(), 0, bufs[0].limit()));
        System.out.println("=================");
        System.out.println(new String(bufs[1].array(), 0, bufs[1].limit()));

        // 4.聚集写入
        RandomAccessFile raf2 = new RandomAccessFile("D:\\360极速浏览器下载\\心理学资源\\人格心理学.txt", "rw");
        FileChannel channel2 = raf2.getChannel();
        channel2.write(bufs);
    }

    // 通道之间的数据传输(直接缓冲区)
    @Test
    public void test3() throws IOException {
        FileChannel inChannel = FileChannel.open(Paths.get("1.jpg"),
                StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("4.jpg"),
                StandardOpenOption.WRITE,
                StandardOpenOption.READ,StandardOpenOption.CREATE);

        inChannel.transferTo(0, inChannel.size(), outChannel);
        // 从inchannel来到outchannel中去
        // inChannel.transferTo(position, count, target)
        // outChannel.transferFrom(inChannel, 0, inChannel.size());
        // outChannel.transferFrom(src, position, count)

    }

    // 使用直接缓冲区完成文件的复制(内存映射文件的方)
    @Test
    public void test2() throws Exception {
        long start = System.currentTimeMillis();

        FileChannel inChannel = FileChannel.open(Paths.get("1.jpg"),
                StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("3.jpg"),
                StandardOpenOption.WRITE,StandardOpenOption.READ,
                StandardOpenOption.CREATE);
        // 内存映射文件
        MappedByteBuffer inMappedByteBuffer = inChannel.map(MapMode.READ_ONLY, 0,
                inChannel.size());
        MappedByteBuffer outMappedByteBuffer = outChannel.map(MapMode.READ_WRITE, 0,
                inChannel.size());
        // 直接对缓冲区进行数据的读写操作
        byte[] dst = new byte[inMappedByteBuffer.limit()];
        inMappedByteBuffer.get(dst);
        outMappedByteBuffer.put(dst);
        inChannel.close();
        outChannel.close();

        long end = System.currentTimeMillis();
        System.out.println("时间为：" + (end - start));// 7
    }

    // 使用通道完成文件的复制（非直接缓冲区）
    @Test
    public void test1() throws FileNotFoundException {

        long start = System.currentTimeMillis();
        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel inchannel = null;
        FileChannel outchannel = null;
        try {
            fis = new FileInputStream("1.jpg");
            fos = new FileOutputStream("2.jpg");

            // 1.获取通道
            inchannel = fis.getChannel();
            outchannel = fos.getChannel();
            // 2.分配指定大小的缓冲区
            ByteBuffer buf = ByteBuffer.allocate(1024);
            // 3.将通道中的数据存入缓冲区中
            while ((inchannel.read(buf)) != -1) {
                buf.flip(); // 然后切换成读取模式，再输出
                // 4.将缓冲区的数据写入通道中
                outchannel.write(buf);
                buf.clear();// 清空缓冲区
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outchannel != null) {
                try {
                    outchannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inchannel != null) {
                try {
                    inchannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("时间为：" + (end - start));// 146
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