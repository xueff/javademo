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
//        new CompressorZip().decompress(new File("E:\\黑马58\\讲义+笔记+资料\\Java基础\\01.会员版(2.0)-就业课(2.0)-Java基础语法\\01.【前言、入门程序、常量、变量】.zip"),"E:\\tmp",false);
//        new CompressorRAR().decompress(new File("E:\\software\\linux\\NAT类型测试工具.rar"),"E:\\tmp",false);
        new CompressorRAR().decompress(new File("E:\\BaiduNetdiskDownload\\尚硅谷前端\\webpack\\code\\project build.rar"),"E:\\tmp",false);
    }
}
