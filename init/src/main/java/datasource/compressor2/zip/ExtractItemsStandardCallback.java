package datasource.compressor2.zip;

import net.sf.sevenzipjbinding.IInArchive;
import net.sf.sevenzipjbinding.SevenZip;
import net.sf.sevenzipjbinding.impl.RandomAccessFileInStream;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Collections;
import java.util.Map;
import java.util.stream.IntStream;

public class ExtractItemsStandardCallback {

    private static final Logger LOG = Logger.getLogger(ExtractItemsStandardCallback.class.getName());
    private static final String READ = "r";

    private ExtractItemsStandardCallback() {
    }

    public static Map<String, byte[]> extract(final File file, final boolean ignoreFolder)
            throws IOException {
        if (file == null || !file.canRead()) {
            LOG.info("Usage: java ExtractItemsStandard <arch-name>");
            return Collections.EMPTY_MAP;
        }

        try (
                final RandomAccessFile randomAccessFile = new RandomAccessFile(
                        file,
                        READ);
                final IInArchive inArchive = SevenZip.openInArchive(
                        null,
                        new RandomAccessFileInStream(randomAccessFile))
        ) {

            LOG.info("Hash\t|\tSize\t|\tFilename");
            LOG.info("----------+------------+---------");

            final IntStream range = IntStream.range(0,
                    inArchive.getNumberOfItems());
            final ExtractCallback extractCallback = new ExtractCallback(
                    inArchive,
                    true);
            inArchive.extract(range.toArray(),
                    false,
                    extractCallback);
            return extractCallback.getFilesExtracteds();

        }
    }
}
