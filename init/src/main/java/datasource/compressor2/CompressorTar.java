/*
 * 文件名：		Compressor.java
 * 创建日期：	2013-7-24
 * 最近修改：	2013-7-24
 * 作者：		徐犇
 */
package datasource.compressor2;

import org.apache.tools.tar.TarEntry;
import org.apache.tools.tar.TarInputStream;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;

/**
 * 压缩解压的基类
 * @author ben
 *
 */
public class CompressorTar implements Compressor {

	/**
	 * 解压缩tar文件
	 * @param file 压缩包文件
	 * @param targetPath 目标文件夹
	 * @param delete 解压后是否删除原压缩包文件
	 */
	@Override
	public void decompress(File file, String targetPath, boolean delete){
		FileInputStream fis = null;
		OutputStream fos = null;
		TarInputStream tarInputStream = null;
		try {
			fis = new FileInputStream(file);
			tarInputStream = new TarInputStream(fis, 1024 * 2);
			// 创建输出目录
			Compressor.createDirectory(targetPath, null);

			TarEntry entry = null;
			while(true){
				entry = tarInputStream.getNextEntry();
				if( entry == null){
					break;
				}
				if(entry.isDirectory()){
					Compressor.createDirectory(targetPath, entry.getName()); // 创建子目录
				}else{
					fos = new FileOutputStream(new File(targetPath + File.separator + entry.getName()));
					int count;
					byte data[] = new byte[2048];
					while ((count = tarInputStream.read(data)) != -1) {
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
				if(tarInputStream != null){
					tarInputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
