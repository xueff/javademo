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
import datasource.compressor2.zip.ExtractItemsStandardCallback;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.regex.Pattern;


/**
 * 压缩解压的基类 RAR5不支持
 * @author ben
 *
 */
@Slf4j
public class CompressorRAR implements Compressor {

    private static final String RAR5_MAGIC_NUMBER = "526172211A070100";
//    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    private static final Pattern PATTERN = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");

	/**
	 * 解压缩7z文件
	 * @param file 压缩包文件
	 * @param targetPath 目标文件夹
	 * @param delete 解压后是否删除原压缩包文件
	 */
	@Override
	public void decompress(File file, String targetPath,  boolean delete){
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(file);
			if (CompressorRAR.isRar5(inputStream)) {
                rar5(file,targetPath,delete);
            } else {
				rar(inputStream,targetPath,delete);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void decompress(InputStream stream, String targetPath, boolean delete) {

	}


	/**
	 * 解压缩rar文件
	 * @param targetPath 目标文件夹
	 * @param delete 解压后是否删除原压缩包文件
	 */
	public void rar5(File file, String targetPath,  boolean delete){
		try {

            final Map<String, byte[]> extract = ExtractItemsStandardCallback.extract(file, true);
            extract.forEach((fileName, bytes) -> {
                final Path path = Paths.get(targetPath, fileName);
                try {
                    File fileOut = new File(path.toUri().getPath());
                    if(!fileOut.getParentFile().exists()){
                        fileOut.getParentFile().mkdirs();
                    }
                    Files.write(path, bytes);
                } catch (IOException e) {
                    System.out.print(e.getMessage());
                }
            });
		} catch (IOException e) {
			if(e.getMessage().contains("unsupportedRarArchive")){
				log.error("该RAR版本不支持！");
			}
			e.printStackTrace();
		}
	}


	/**
	 * 解压缩rar文件
	 * @param targetPath 目标文件夹
	 * @param delete 解压后是否删除原压缩包文件
	 */
	public void rar(InputStream inputStream, String targetPath,  boolean delete){
		Archive archive = null;
		OutputStream outputStream = null;
		try {
			archive = new Archive(inputStream);
			FileHeader fileHeader;
			// 创建输出目录
			Compressor.createDirectory(targetPath, null);
			while( (fileHeader = archive.nextFileHeader()) != null){
				if(fileHeader.getFileNameString().contains("code") || fileHeader.getFileNameString().contains("idea")){
					continue;
				}



				if(fileHeader.isDirectory()){
					Compressor.createDirectory(targetPath, fileHeader.getFileNameW().trim().isEmpty()?fileHeader.getFileNameString().trim():fileHeader.getFileNameW().trim()); // 创建子目录
				}else{
					File fileOut = null;
					if(fileHeader.getFileNameW().trim().isEmpty()){
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




	private static boolean isRar5(final InputStream inputStream) {
		final byte[] magicNumber = new byte[8];
        try {
            inputStream.read(magicNumber, 0, 8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return RAR5_MAGIC_NUMBER.equalsIgnoreCase(bytesToHex(magicNumber));
	}

	private static String bytesToHex(byte[] bytes) {
		final StringBuilder sb = new StringBuilder();
		for (byte aByte : bytes) {
			sb.append(String.format("%02x", aByte));
		}
		return sb.toString();
	}

    protected File getTempDir(String... prefixes) throws IOException {
        final String prefix = prefixes.length == 0 ? "E:/tmp/" : prefixes[0];
        final Path path = Files.createTempDirectory(prefix + System.currentTimeMillis());
        return path.toFile();
    }

    protected File getTempFile(final File tempDir, final String extension) throws IOException {
        final Path path = Files.createTempFile(Paths.get(tempDir.getPath()), null, extension);
        final File file = path.toFile();
        file.deleteOnExit();
        return file;
    }
}
