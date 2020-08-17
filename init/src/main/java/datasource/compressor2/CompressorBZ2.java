/*
 * 文件名：		Compressor.java
 * 创建日期：	2013-7-24
 * 最近修改：	2013-7-24
 * 作者：		徐犇
 */
package datasource.compressor2;

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.tools.tar.TarEntry;
import org.apache.tools.tar.TarInputStream;

import java.io.*;

/**
 * 压缩解压的基类
 * @author ben
 *
 */
public class CompressorBZ2 implements Compressor {

	/**
	 * 解压缩bz2文件
	 * @param file 压缩包文件
	 * @param targetPath 目标文件夹
	 * @param delete 解压后是否删除原压缩包文件
	 */
	@Override
	public void decompress(File file, String targetPath, boolean delete){
		FileInputStream fis = null;
		OutputStream fos = null;
		BZip2CompressorInputStream bis = null;
		String suffix = ".bz2";
		try {
			fis = new FileInputStream(file);
			bis = new BZip2CompressorInputStream(fis);
			// 创建输出目录
			Compressor.createDirectory(targetPath, null);
			File tempFile = new File(targetPath + File.separator + file.getName().replace(suffix, ""));
			fos = new FileOutputStream(tempFile);

			int count;
			byte data[] = new byte[2048];
			while ((count = bis.read(data)) != -1) {
				fos.write(data, 0, count);
			}
			fos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(fis != null){
					fis.close();
				}
				if(fos != null){
					fos.close();
				}
				if(bis != null){
					bis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
