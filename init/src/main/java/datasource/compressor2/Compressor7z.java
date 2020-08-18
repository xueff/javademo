/*
 * 文件名：		Compressor.java
 * 创建日期：	2013-7-24
 * 最近修改：	2013-7-24
 * 作者：		徐犇
 */
package datasource.compressor2;

import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;

import java.io.*;
import java.util.zip.GZIPInputStream;

/**
 * 压缩解压的基类
 * @author ben
 *
 */
public class Compressor7z implements Compressor {
	/**
	 * 解压缩7z文件
	 * @param file 压缩包文件
	 * @param targetPath 目标文件夹
	 * @param delete 解压后是否删除原压缩包文件
	 */
	@Override
	public void decompress(File file, String targetPath,  boolean delete){
		SevenZFile sevenZFile = null;
		OutputStream outputStream = null;
		try {
			sevenZFile = new SevenZFile(file);
			// 创建输出目录
			Compressor.createDirectory(targetPath, null);
			SevenZArchiveEntry entry;

			while((entry = sevenZFile.getNextEntry()) != null){
				if(entry.isDirectory()){
					Compressor.createDirectory(targetPath, entry.getName()); // 创建子目录
				}else{

					File fileOut = new File(targetPath + File.separator + entry.getName());
					if(!fileOut.getParentFile().exists()){
						fileOut.getParentFile().mkdirs();
					}
					outputStream = new FileOutputStream(new File(targetPath + File.separator + entry.getName()));
					int len = 0;
					byte[] b = new byte[2048];
					while((len = sevenZFile.read(b)) != -1){
						outputStream.write(b, 0, len);
					}
					outputStream.flush();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(sevenZFile != null){
					sevenZFile.close();
				}
				if(outputStream != null){
					outputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
