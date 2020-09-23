package javabase.io.stream;

import output.file.FileUtils;

import java.io.*;

public class StreamUtils {

    /**
     * 读取流并重置流    流重置
     * @param BufferedInputStream
     * @return
     * @throws IOException
     */
    public static ByteArrayOutputStream getByteArrayOutputStreamAndReset(InputStream fileStream) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
        byte[] buffer = new byte[1024];
        fileStream.mark(10240);
        int len;
        while ((len = fileStream.read(buffer)) > -1) {
            if(baos.size() >= 1024){
                break;
            }
            baos.write(buffer, 0, len);
        }
        fileStream.reset();
        baos.flush();
        return baos;
    }
    /**
     * 读取流并重置流    流重置
     * @param BufferedInputStream
     * @return
     * @throws IOException
     */
    public static byte[] getBytesStreamAndReset(InputStream fileStream) throws IOException {
        byte[] buffer = new byte[1024];
        fileStream.mark(10240);
        fileStream.read(buffer);
        fileStream.reset();
        return buffer;
    }

    public static void main(String[] args) {
        try {
            InputStream in = new FileInputStream(new File("C:\\Users\\admin\\Desktop\\1.wsdl"));
            BufferedInputStream buffInputStream = new BufferedInputStream(in);
            System.out.println(new String(getBytesStreamAndReset(buffInputStream)));
            System.out.println("\n\n\n\n\n\n");
            System.out.println(FileUtils.readFile(buffInputStream));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
