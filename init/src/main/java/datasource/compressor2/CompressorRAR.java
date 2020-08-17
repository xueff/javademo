/*
 * 文件名：		Compressor.java
 * 创建日期：	2013-7-24
 * 最近修改：	2013-7-24
 * 作者：		徐犇
 */
package datasource.compressor2;

import com.github.junrar.Archive;
import com.github.junrar.exception.RarException;
import com.github.junrar.rarfile.FileHeader;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.io.*;

/**
 * 压缩解压的基类 RAR5不支持
 * @author ben
 *
 */
@Slf4j
public class CompressorRAR implements Compressor {
	/**
	 * 解压缩7z文件
	 * @param file 压缩包文件
	 * @param targetPath 目标文件夹
	 * @param delete 解压后是否删除原压缩包文件
	 */
	@Override
	public void decompress(File file, String targetPath,  boolean delete){
		Archive archive = null;
		OutputStream outputStream = null;
		try {
			archive = new Archive(new FileInputStream(file));
			FileHeader fileHeader;
			// 创建输出目录
			Compressor.createDirectory(targetPath, null);
			while( (fileHeader = archive.nextFileHeader()) != null){
				if(fileHeader.getFileNameString().contains("code") || fileHeader.getFileNameString().contains("idea")){
					continue;
				}



				if(fileHeader.isDirectory()){
					Compressor.createDirectory(targetPath, StringUtils.isEmpty(fileHeader.getFileNameW().trim())?fileHeader.getFileNameString().trim():fileHeader.getFileNameW().trim()); // 创建子目录
				}else{
					File fileOut = null;
					if(StringUtils.isEmpty(fileHeader.getFileNameW())){
						fileOut = new File(targetPath + File.separator + fileHeader.getFileNameString().trim());
					}else {
						fileOut = new File(targetPath + File.separator + fileHeader.getFileNameW().trim());
					}
					if(!fileOut.getParentFile().exists()){
						fileOut.getParentFile().mkdirs();
					}
					outputStream = new FileOutputStream(new File(fileOut.getAbsolutePath()));
					archive.extractFile(fileHeader, outputStream);
				}
			}
		} catch (RarException | IOException e) {
			if(e.getMessage().contains("unsupportedRarArchive")){
				log.error("该RAR版本不支持！");
			}
			e.printStackTrace();
		}finally {
			try {
				if(archive != null){
					archive.close();
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
