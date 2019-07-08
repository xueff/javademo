package javabase.base;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * @author xuefei
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/10/299:28
 */
public class Serlializeation {
    @Test
    public void a() throws IOException {
        //内存中的一个字节数组
        byte[] buf="11111".getBytes();
        ByteArrayInputStream by = new ByteArrayInputStream(buf);

        byte []pos=new byte[buf.length];
        //通过字节数组输入流向该内存中输入字节
        while (by.read(pos)!=-1);
        by.close();
        System.out.println(new String(pos));
    }
}
