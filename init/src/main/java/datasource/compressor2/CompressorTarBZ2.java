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
public class CompressorTarBZ2 implements Compressor {



	/**
	 * 解压缩tar.bz2文件
	 * @param file 压缩包文件
	 * @param targetPath 目标文件夹
	 * @param delete 解压后是否删除原压缩包文件
	 */
	@Override
	public void decompress(File file, String targetPath, boolean delete){
		FileInputStream fis = null;
		OutputStream fos = null;
		BZip2CompressorInputStream bis = null;
		TarInputStream tis = null;
		try {
			fis = new FileInputStream(file);
			bis = new BZip2CompressorInputStream(fis);
			tis = new TarInputStream(bis, 1024 * 2);
			// 创建输出目录
			Compressor.createDirectory(targetPath, null);
			TarEntry entry;
			while((entry = tis.getNextEntry()) != null){
				if(entry.isDirectory()){
					Compressor.createDirectory(targetPath, entry.getName()); // 创建子目录
				}else{
					fos = new FileOutputStream(new File(targetPath + File.separator + entry.getName()));
					int count;
					byte data[] = new byte[2048];
					while ((count = tis.read(data)) != -1) {
						fos.write(data, 0, count);
					}
					fos.flush();
				}
			}
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
				if(tis != null){
					tis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
