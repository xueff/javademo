/*
 * 文件名：		Compressor.java
 * 创建日期：	2013-7-24
 * 最近修改：	2013-7-24
 * 作者：		徐犇
 */
package datasource.compressor2;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

/**
 * 压缩解压的基类 RAR5不支持
 * @author ben
 *
 */
@Slf4j
public class RAR5 implements Compressor {
	/**
	 * 解压缩7z文件
	 * @param file 压缩包文件
	 * @param targetPath 目标文件夹
	 * @param delete 解压后是否删除原压缩包文件
	 */
	@Override
	public void decompress(File file, String targetPath,  boolean delete){
		File fi = new File("C:\\java-workspace\\td-master.rar");//rar5源文件


	}

	@Override
	public void decompress(InputStream stream, String targetPath, boolean delete) {

	}
}
