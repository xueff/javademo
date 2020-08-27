package javabase.file.lock;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
public class FileLockUtils {

        public static void main(String[] arg) throws Exception {
            writeProcess();

        }
    public static void writeProcess()  throws Exception{
            RandomAccessFile randomAccessFile = null;
            FileChannel channel = null;

            try {
                randomAccessFile = new RandomAccessFile("D:\\project\\idr-holmes-copy\\processor_java\\tmp\\decompress\\11.txt", "rd");
                channel = randomAccessFile.getChannel();
                FileLock lock = null;

                while (null == lock) {
                    try {
                        lock = channel.lock();
                    } catch (Exception e) {
                        System.out.println("Write Process : get lock failed");
                    }
                }

                System.out.println("Write Process : get lock");


                Thread.sleep(100000000);

                lock.release();
                System.out.println("Write Process : release lock");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (null != randomAccessFile) {
                    randomAccessFile.close();
                }
                if (null != channel) {
                    channel.close();
                }
            }
        }


        public static void readProcess() throws Exception {
            RandomAccessFile randomAccessFile = null;
            FileChannel channel = null;

            try {
                Thread.sleep(10000);

                randomAccessFile = new RandomAccessFile("test.txt", "rw");
                channel = randomAccessFile.getChannel();
                FileLock lock = null;

                while (true) {
                    lock = channel.tryLock();

                    if (null == lock) {
                        System.out.println("Read Process : get lock failed");
                        Thread.sleep(1000);
                    } else {
                        break;
                    }
                }

                System.out.println("Read Process : get lock");

                System.out.println("Read Process : get " + randomAccessFile.length() + " numbers");

                lock.release();
                System.out.println("Read Process : release lock");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (null != randomAccessFile) {
                    randomAccessFile.close();
                }
                if (null != channel) {
                    channel.close();
                }
            }
        }
    }
