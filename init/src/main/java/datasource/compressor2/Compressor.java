/*
 * 文件名：		Compressor.java
 * 创建日期：	2013-7-24
 * 最近修改：	2013-7-24
 * 作者：		徐犇
 */
package datasource.compressor2;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * 压缩解压的基类
 * @author ben
 *
 */
public interface Compressor {

    void decompress(File file, String targetPath, boolean delete);

    /**
     *  构建目录
     * @param outputDir 输出目录
     * @param subDir 子目录
     */
    static void createDirectory(String outputDir, String subDir) {
        File file = new File(outputDir);
        if (!(subDir == null || subDir.trim().equals(""))) {//子目录不为空
            file = new File(outputDir + File.separator + subDir);
        }
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            file.mkdirs();
        }
    }
}
