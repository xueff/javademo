package file_system.file.tika;

import java.io.Writer;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class TikaWriter extends Writer {
        String fileName = "";
        //0:未开始  1：进行中 2：结束  -1：失败
        int status = 0;
        //控制队列长度   所有文件通用
        BlockingQueue<char[]> queue = new ArrayBlockingQueue<char[]>(5000);

        public TikaWriter() {
        }

        public void write(int idx) {

        }

        public void write(char[] chr) {
            try {
                // TODO  lock 控制速度
                //无用字符
                queue.put(chr);
                System.out.println(new String(chr));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void write(char[] chr, int st, int end) {
            //TODO line or
            write(chr);
        }

        public void write(String str) {
            write(str.toCharArray());
        }

        public void write(String str, int st, int end) {
            write(str.toCharArray());
        }

        public void flush() {
        }

        public void close() {
        }
    }

