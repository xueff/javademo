package output.file.pdf;

import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.common.PDMetadata;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.text.PDFTextStripper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class PDFOperation  {
    private PDDocument doc;



    public void init(String path) {
        Path p = Paths.get(path);
        try (InputStream is = Files.newInputStream(p);) {
            doc = PDDocument.load(is);

        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("path not exist");
        }
    }

    public String getText() {
        try {
            PDFTextStripper pdfTextStripper = new PDFTextStripper();
            pdfTextStripper.setSortByPosition(true);
            return pdfTextStripper.getText(doc);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("文件读取失败");
        }
    }

    public List<String> getTabText() {
        List<String> list = new ArrayList<>();
        return list;
    }

    public String getMetaData() {
        PDDocumentInformation documentInformation = doc.getDocumentInformation();
        Map<String, String> infos = new HashMap<>();
        infos.put("author", documentInformation.getAuthor());
        infos.put("creator", documentInformation.getCreator());
        infos.put("Keywords", documentInformation.getKeywords());
        infos.put("Subject", documentInformation.getSubject());
        infos.put("Producer", documentInformation.getProducer());
        infos.put("Title", documentInformation.getTitle());
        infos.put("Trapped", documentInformation.getTrapped());
        infos.put("MetadataKeys", documentInformation.getMetadataKeys().toString());
        return infos.toString();
    }

    public Iterator<String> iterator() {
        return new PDFItr(0);
    }

    public void extractPicture(String path) throws IOException {
        PDDocumentCatalog catalog = doc.getDocumentCatalog();
        PDPageTree pages = catalog.getPages();
        Iterator<PDPage> iterator = pages.iterator();
        int j = 0;
        while (iterator.hasNext()) {
            PDPage page = iterator.next();
            PDResources res = page.getResources();
            Iterator<COSName> cosNames = res.getXObjectNames().iterator();
            if (j > 10) {
                break;
            }
            j++;
            while (cosNames.hasNext()) {
                COSName cosName = cosNames.next();
                if (res.isImageXObject(cosName)) {
                    PDImageXObject imgObj = (PDImageXObject) res.getXObject(cosName);
                    BufferedImage image = imgObj.getImage();
                    PDMetadata metadata = imgObj.getMetadata();
                    String newestName = path + File.separator + UUID.randomUUID() + ".png";
                    try (FileOutputStream out = new FileOutputStream(newestName);) {
                        ImageIO.write(image, "png", out);
                    }
                }
            }
        }
    }

    public void close() {
        if (this.doc != null) {
            try {
                doc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        try {
            doc.save("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(OutputStream outputStream) {
        try {
            doc.save(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateMetaInfo(String  info) {
        PDDocumentInformation documentInformation = doc.getDocumentInformation();
        documentInformation.setAuthor("自由作者");
        documentInformation.setCustomMetadataValue("comment", info);
    }


    private class PDFItr implements Iterator<String> {
        int index;

        public PDFItr(int index) {
            this.index = index;
        }

        @Override
        public boolean hasNext() {
            int pageNums = PDFOperation.this.doc.getNumberOfPages();
            System.out.println(pageNums);
            return index < pageNums;
        }

        @Override
        public String next() {
            try {
                PDFTextStripper stripper = new PDFTextStripper();
                stripper.setSortByPosition(true);
                stripper.setStartPage(index);
                stripper.setEndPage(index+1);
                String text = stripper.getText(PDFOperation.this.doc);
                return text;
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                index++;
            }
            return "";
        }
    }
}
