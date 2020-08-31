package utils.sigar;

import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.*;

/**
 * @author  文件
 */
public class SigarUtil {
    private static Logger log = LoggerFactory.getLogger(SigarUtil.class);
    public static Sigar getSigar() {

        SigarLoader loader = new SigarLoader(Sigar.class);
        String lib = null;
        try {
            lib = loader.getLibraryName();
            ResourceLoader resourceLoader = new DefaultResourceLoader();
            Resource resource = resourceLoader.getResource("classpath:/sigar/" + lib);

            if (resource.exists()) {
                InputStream is = resource.getInputStream();
                File tempDir = createTempDirectory();
                BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(new File(tempDir, lib), false));
                copy(is, os);

                is.close();
                os.close();
                System.setProperty("org.hyperic.sigar.path", tempDir.getCanonicalPath());
            }
            Sigar sigar = new Sigar();
            return sigar;

        } catch (Exception e) {
            log.error("", e);
            return null;
        }

    }

    public static File createTempDirectory()
            throws IOException {
        final File temp;

        temp = File.createTempFile("temp", Long.toString(System.nanoTime()));

        if (!(temp.delete())) {
            throw new IOException("Could not delete temp file: " + temp.getAbsolutePath());
        }

        if (!(temp.mkdir())) {
            throw new IOException("Could not create temp directory: " + temp.getAbsolutePath());
        }

        return (temp);
    }

    public static void copy(InputStream is, BufferedOutputStream bos) throws Exception {
        byte[] b = new byte[1024];
        int i = -1;//读取到的字节个数
        while ((i = is.read(b)) != -1) {
            bos.write(b, 0, i);
            bos.flush();
        }
        bos.close();
        bos.close();
    }
}

