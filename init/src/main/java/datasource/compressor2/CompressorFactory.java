/*
 * 文件名：		Compressor.java
 * 创建日期：	2013-7-24
 * 最近修改：	2013-7-24
 * 作者：		徐犇
 */
package datasource.compressor2;

import java.io.File;
import java.io.InputStream;

/**
 * 压缩解压的基类
 * @author ben
 *
 */
public class CompressorFactory {

    public static void decompress(String filePath, String tag) {

        File file = new File(filePath);
        if (filePath.toUpperCase().endsWith(Compressor.ZIP)) {
             new CompressorZip().decompress(file,"./tmp/compress/"+tag+"/",true);
        } else if(filePath.toUpperCase().endsWith(Compressor.TARBZ2)) {
             new CompressorTarBZ2().decompress(file,"./tmp/compress/"+tag+"/",true);
        } else if(filePath.toUpperCase().endsWith(Compressor.TARGZ)) {
             new CompressorTarGz().decompress(file, "./tmp/compress/" + tag + "/", true);
        }else if(filePath.toUpperCase().endsWith(Compressor.TAR)) {
             new CompressorTar().decompress(file,"./tmp/compress/"+tag+"/",true);
        } else if(filePath.toUpperCase().endsWith(Compressor._7Z)) {
             new Compressor7z().decompress(file,"./tmp/compress/"+tag+"/",true);
        } else if(filePath.toUpperCase().endsWith(Compressor.BZ2)) {
             new CompressorBZ2().decompress(file,"./tmp/compress/"+tag+"/",true);
        } else if(filePath.toUpperCase().endsWith(Compressor.GZ)) {
             new CompressorGz().decompress(file,"./tmp/compress/"+tag+"/",true);
        } else if(filePath.toUpperCase().endsWith(Compressor.RAR)) {
             new CompressorRAR().decompress(file,"./tmp/compress/"+tag+"/",true);
        }
    }

    public static void decompress(InputStream stream,String filePath, String tag) {
        if (filePath.toUpperCase().endsWith(Compressor.ZIP)) {
             new CompressorZip().decompress(stream,"./tmp/compress/"+tag+"/",true);
        } else if(filePath.toUpperCase().endsWith(Compressor.TARBZ2)) {
             new CompressorTarBZ2().decompress(stream,"./tmp/compress/"+tag+"/",true);
        } else if(filePath.toUpperCase().endsWith(Compressor.TARGZ)) {
             new CompressorTarGz().decompress(stream, "./tmp/compress/" + tag + "/", true);
        }else if(filePath.toUpperCase().endsWith(Compressor.TAR)) {
             new CompressorTar().decompress(stream,"./tmp/compress/"+tag+"/",true);
        } else if(filePath.toUpperCase().endsWith(Compressor._7Z)) {
             new Compressor7z().decompress(stream,"./tmp/compress/"+tag+"/",true);
        } else if(filePath.toUpperCase().endsWith(Compressor.BZ2)) {
             new CompressorBZ2().decompress(stream,"./tmp/compress/"+tag+"/",true);
        } else if(filePath.toUpperCase().endsWith(Compressor.GZ)) {
             new CompressorGz().decompress(stream,"./tmp/compress/"+tag+"/",true);
        } else if(filePath.toUpperCase().endsWith(Compressor.RAR)) {
             new CompressorRAR().decompress(stream,"./tmp/compress/"+tag+"/",true);
        }
    }
}
