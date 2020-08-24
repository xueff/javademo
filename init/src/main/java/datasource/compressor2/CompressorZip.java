/*
 * 文件名：		Compressor.java
 * 创建日期：	2013-7-24
 * 最近修改：	2013-7-24
 * 作者：		徐犇
 */
package datasource.compressor2;

import lombok.extern.slf4j.Slf4j;
import org.apache.tools.tar.TarEntry;
import org.apache.tools.tar.TarInputStream;

import java.io.*;
import java.nio.charset.Charset;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipInputStream;

/**
 * 压缩解压的基类
 * @author ben
 *
 */
@Slf4j
public class CompressorZip implements Compressor {
	/**
	 * 解压缩7z文件
	 *
	 * @param file       压缩包文件
	 * @param targetPath 目标文件夹
	 * @param delete     解压后是否删除原压缩包文件
	 */
	@Override
	public void decompress(File file, String targetPath, boolean delete) {
		ZipInputStream zis = null;
		OutputStream outputStream = null;
		try {
			FileInputStream fis = new FileInputStream(file);

			BufferedInputStream bis = new BufferedInputStream(fis);
			zis = new ZipInputStream(bis, Charset.forName("GBK"));
			ZipEntry entry = null;

			while ((entry = zis.getNextEntry()) != null) {
				if (entry.isDirectory()) {
					Compressor.createDirectory(targetPath, entry.getName()); // 创建子目录
				} else {
					File fileOut = new File(targetPath + File.separator + entry.getName());
					if(!fileOut.getParentFile().exists()){
						fileOut.getParentFile().mkdirs();
					}
					outputStream = new FileOutputStream(new File(targetPath + File.separator + entry.getName()));
					int len = 0;
					byte[] b = new byte[2048];
					while ((len = zis.read(b)) != -1) {
						outputStream.write(b, 0, len);
					}
					outputStream.flush();
				}
			}
		} catch (ZipException e) {
			if(e.getMessage().contains("encrypted ZIP entry not supported")){
				log.error("zip已加密");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (zis != null) {
					zis.close();
				}
				if (outputStream != null) {
					outputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
