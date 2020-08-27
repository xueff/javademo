/*
 * 文件名：		Compressor.java
 * 创建日期：	2013-7-24
 * 最近修改：	2013-7-24
 * 作者：		徐犇
 */
package datasource.compressor2;

import org.apache.tools.tar.TarEntry;
import org.apache.tools.tar.TarInputStream;

import java.io.*;
import java.util.zip.GZIPInputStream;

/**
 * 压缩解压的基类
 * @author ben
 *
 */
public class CompressorGz implements Compressor {

	/**
	 * 解压缩gz文件
	 * @param file 压缩包文件
	 * @param targetPath 目标文件夹
	 * @param delete 解压后是否删除原压缩包文件
	 */
	@Override
	public void decompress(File file, String targetPath,  boolean delete){
		FileInputStream  fileInputStream = null;
		GZIPInputStream gzipIn = null;
		OutputStream out = null;
		String suffix = ".gz";
		try {
			fileInputStream = new FileInputStream(file);
			gzipIn = new GZIPInputStream(fileInputStream);
			// 创建输出目录
			Compressor.createDirectory(targetPath, null);

			File tempFile = new File(targetPath + File.separator + file.getName().replace(suffix, ""));
			out = new FileOutputStream(tempFile);
			int count;
			byte data[] = new byte[2048];
			while ((count = gzipIn.read(data)) != -1) {
				out.write(data, 0, count);
			}
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(out != null){
					out.close();
				}
				if(gzipIn != null){
					gzipIn.close();
				}
				if(fileInputStream != null){
					fileInputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void decompress(InputStream stream, String targetPath, boolean delete) {

	}
}
