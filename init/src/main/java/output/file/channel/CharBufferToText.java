package output.file.channel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class CharBufferToText {

    public static void main(String[] args) {
        try {
            //--以系统默认编码方式写文件
            FileChannel fc = new FileOutputStream("data2.txt").getChannel();
            fc.write(ByteBuffer.wrap("测试字符".getBytes()));
            fc.close();

            //--读文本
            fc = new FileInputStream("data2.txt").getChannel();
            ByteBuffer buff = ByteBuffer.allocate(1024);
            fc.read(buff);
            buff.flip();
            //显示乱码，采用默认的编码方式（UTF-16BE）将ByteBuffer转换成CharBuffer
            System.out.println(buff.asCharBuffer());
            buff.rewind();//准备重读

            //当前系统默认编码方式
            String encoding = System.getProperty("file.encoding");
            //下面我们使用系统默认的编码方式（GBK）将ByteBuffer转换成CharBuffer
            System.out.println("Decoded using " + encoding + ": "
                    + Charset.forName(encoding).decode(buff));//显示正常，因为写入与读出时采用相同编码方式

            //--或者，先以UTF-16BE编码后再写文件
            fc = new FileOutputStream("data2.txt").getChannel();
            fc.write(ByteBuffer.wrap("测试字符".getBytes("UTF-16BE")));
            fc.close();
            // 再尝试读
            fc = new FileInputStream("data2.txt").getChannel();
            buff.clear();
            fc.read(buff);
            buff.flip();
            //显示正常，可见asCharBuffer()方式是以UTF-16BE解码的
            System.out.println(buff.asCharBuffer());

            //--也可直接通过CharBuffer写也是可以的
            fc = new FileOutputStream("data2.txt").getChannel();
            buff = ByteBuffer.allocate(8);//UTF-16编码时每个字符占二字节，所以需四个
            //将ByteBuffer转换成CharBuffer后再写
            buff.asCharBuffer().put("测试字符");
            fc.write(buff);
            fc.close();
            //读显示
            fc = new FileInputStream("data2.txt").getChannel();
            buff.clear();
            fc.read(buff);
            buff.flip();
            //同时也采用默认的转换方式asCharBuffer将ByteBuffer转换成CharBuffer
            System.out.println(buff.asCharBuffer());//显示正常
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
