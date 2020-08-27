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
import output.file.filecode.FileCodeUtils;

import java.io.*;
import java.util.zip.GZIPInputStream;

/**
 * 压缩解压的基类
 * @author ben
 *
 */
public class CompressorTarGz implements Compressor {

	/**
	 * 解压缩tar.gz文件
	 * @param file 压缩包文件
	 * @param targetPath 目标文件夹
	 * @param delete 解压后是否删除原压缩包文件
	 */
	@Override
	public void decompress(File file, String targetPath,  boolean delete){
		FileInputStream  fileInputStream = null;
		BufferedInputStream bufferedInputStream = null;
		GZIPInputStream gzipIn = null;
		TarInputStream tarIn = null;
		OutputStream out = null;
		try {
			fileInputStream = new FileInputStream(file);
			bufferedInputStream = new BufferedInputStream(fileInputStream);
			gzipIn = new GZIPInputStream(bufferedInputStream);
			tarIn = new TarInputStream(gzipIn, 1024 * 2,512,"gb2312");

			// 创建输出目录
			Compressor.createDirectory(targetPath, null);

			TarEntry entry = null;
			while((entry = tarIn.getNextEntry()) != null){
				if(entry.isDirectory()){ // 是目录
					Compressor.createDirectory(targetPath, entry.getName()); // 创建子目录
				}else{ // 是文件
					File tempFIle = new File(targetPath + File.separator + entry.getName());
					Compressor.createDirectory(tempFIle.getParent() + File.separator, null);
					out = new FileOutputStream(tempFIle);
					int len =0;
					byte[] b = new byte[2048];

					while ((len = tarIn.read(b)) != -1){
						out.write(b, 0, len);
					}
					out.flush();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(out != null){
					out.close();
				}
				if(tarIn != null){
					tarIn.close();
				}
				if(gzipIn != null){
					gzipIn.close();
				}
				if(bufferedInputStream != null){
					bufferedInputStream.close();
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
