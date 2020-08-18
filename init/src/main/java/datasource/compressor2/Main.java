/*
 * 文件名：		Compressor.java
 * 创建日期：	2013-7-24
 * 最近修改：	2013-7-24
 * 作者：		徐犇
 */
package datasource.compressor2;

import java.io.File;

/**
 * 压缩解压的基类
 * @author ben
 *
 */
public class Main {

    public static void main(String[] args) {
//        new CompressorZip().decompress(new File("C:\\Users\\admin\\Desktop\\aa.zip"),"E:\\tmp",false);
//        new CompressorRAR().decompress(new File("E:\\software\\linux\\NAT类型测试工具.rar"),"E:\\tmp",false);
//        new CompressorRAR().decompress(new File("E:\\BaiduNetdiskDownload\\尚硅谷前端\\webpack\\code\\project build.rar"),"E:\\tmp",false);
//        System.setProperty("file.encoding","gb2312");
//        new CompressorTar().decompress(new File("E:\\software\\linux\\npf数据库嗅探工具.tar"),"E:\\tmp",false);
//        new CompressorTarGz().decompress(new File("E:\\software\\linux\\npf数据库嗅探工具.tar.gz"),"E:\\tmp",false);
        new Compressor7z().decompress(new File("E:\\software\\linux\\npf数据库嗅探工具.7z"),"E:\\tmp",false);
    }
}
