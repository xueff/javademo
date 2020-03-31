package output.file.ppt;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hslf.usermodel.HSLFSlideShow;
import org.apache.poi.hslf.usermodel.HSLFTable;
import org.apache.poi.hslf.usermodel.HSLFTableCell;
import org.apache.poi.hslf.usermodel.HSLFTextShape;
import org.apache.poi.sl.extractor.SlideShowExtractor;
import org.apache.poi.sl.usermodel.*;
import org.apache.poi.xslf.usermodel.*;

import java.io.*;
import java.util.List;
import java.util.UUID;


@Slf4j
public class PPTUtils {

    public static void main(String[] args) {
//        String ppt = PptUtils.getTextFromPPT("E:\\qywx\\WXWork\\1688852589482509\\Cache\\File\\2020-03\\曹波_车牌识别.pptx");
        PPTUtils.testPpt("D:\\OneDrive\\我的本地同步\\work\\观安\\培训\\黑白盒工具\\nessus.pptx");
        System.out.println("");
    }

    /**
     * @Title: getTextFromPPT(兼容ppt、pptx)
     * @Description: 获取指定位置ppt的文件内容
     * @param @param filePath
     * @param @return
     * @param @throws IOException
     * @return String 返回类型
     * @throws
     */
    @SuppressWarnings({ "unchecked", "rawtypes", "static-access", "resource" })
    public static String getTextFromPPT(String filePath) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(filePath);
            SlideShowExtractor extractor = new SlideShowExtractor(SlideShowFactory.create(fis));
//            XMLSlideShow slideShow = new XMLSlideShow(fis);
//            List<XSLFSlide> slideList =  slideShow.getSlides();
//            slideList.forEach(it->{
//                System.out.println(extractor.getText(it));
//            });
            return extractor.getText();
        } catch (FileNotFoundException e) {
            log.error("加载文档失败，请检查文档路径！",e);
        } catch (IOException e) {
            log.error("读取文档失败，请检查文档格式!",e);
        } finally {
            PPTUtils.close(fis);
        }
        return "";
    }
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void testPpt(String path) {
        // String path = "D:\\temp\\temp\\test.pptx";
        File file = new File(path);
        InputStream is = null;
        SlideShow slideShow = null;
        try {
            is = new FileInputStream(file);
            if (path.endsWith(".ppt")) {
                slideShow = new HSLFSlideShow(is);
            } else if (path.endsWith(".pptx")) {
                slideShow = new XMLSlideShow(is);
            }
            if (slideShow != null) {
                // 文本内容
                StringBuilder content = new StringBuilder();
                // 一页一页读取
                for (Slide slide : (List<Slide>) slideShow.getSlides()) {
                    List shapes = slide.getShapes();
                    if (shapes != null) {
                        for (int i = 0; i < shapes.size(); i++) {
                            Shape shape = (Shape) shapes.get(i);
                            if (shape instanceof HSLFTextShape) {// 文本框
                                String text = ((HSLFTextShape) shape).getText();
                                content.append(text);
                            }
                            if (shape instanceof XSLFTextShape) {// 文本框
                                String text = ((XSLFTextShape) shape).getText();
                                content.append(text);
                            }
                            if (shape instanceof HSLFTable) {// 表格
                                int rowSize = ((HSLFTable) shape).getNumberOfRows();
                                int columnSize = ((HSLFTable) shape).getNumberOfColumns();
                                for (int rowNum = 0; rowNum < rowSize; rowNum++) {
                                    for (int columnNum = 0; columnNum < columnSize; columnNum++) {
                                        HSLFTableCell cell = ((HSLFTable) shape).getCell(rowNum, columnNum);
                                        if (cell != null) {
                                            String text = cell.getText();
                                            content.append(text);
                                        }
                                    }
                                }
                            }
                            if (shape instanceof XSLFTable) {// 表格
                                int rowSize = ((XSLFTable) shape).getNumberOfRows();
                                int columnSize = ((XSLFTable) shape).getNumberOfColumns();
                                for (int rowNum = 0; rowNum < rowSize; rowNum++) {
                                    for (int columnNum = 0; columnNum < columnSize; columnNum++) {
                                        XSLFTableCell cell = ((XSLFTable) shape).getCell(rowNum, columnNum);
                                        if (cell != null) {
                                            String text = cell.getText();
                                            content.append(text);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (content.length() > 0) {
                        System.out.println(content);
                        content.delete(0, content.length());
                    }
                }

                // 图片内容
                List pictures = slideShow.getPictureData();
                for (int i = 0; i < pictures.size(); i++) {
                    PictureData picture = (PictureData) pictures.get(i);
                    byte[] data = picture.getData();
                    FileOutputStream out = new FileOutputStream("D:\\temp\\temp\\" + UUID.randomUUID() + ".jpg");
                    out.write(data);
                    out.close();
                }
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } finally {
            try {
                if (slideShow != null) {
                    slideShow.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
            }
        }
    }

    private static void close(FileInputStream fis) {
        if (fis != null) {
            try {
                fis.close();
                fis = null;
            } catch (IOException e) {
                log.error("流关闭异常！",e);
            }
        }
    }
}
