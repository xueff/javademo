package ocr;

import io.vertx.core.json.JsonObject;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.Word;

import javax.imageio.ImageIO;
import javax.swing.filechooser.FileSystemView;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;

public class TessdOcrDemo {
    public static void main(String[] args) throws Exception {
        System.out.println(ocrResult("C:\\Users\\admin\\Desktop\\企业微信截图_20200914161415.png"));
    }
    /**
     * 图片识别
     * @author wangy
     * @date 2019-08-26
     */
    public static  String  ocrResult(String path ) throws Exception {
        String result = "";
        FileSystemView fsv = FileSystemView.getFileSystemView();
        File com=fsv.getHomeDirectory();    //这便是读取桌面路径的方法了
        String os = System.getProperty("os.name");
        //识别系统，找不同的语言包路径

        //获取元素截图的路径
        File imageFile = new File(path);
        //要对图片处理
        Tesseract instance = new Tesseract();
        //读取语言包的路径地址
        instance.setDatapath("C:\\Users\\admin\\Desktop\\shengfenzheng\\");
        // 默认是英文（识别字母和数字），如果要识别中文(数字 + 中文），需要制定语言包，这里是数字，所以没用语言包
        instance.setLanguage("chi_sim");
//为了防止没截完图片就识别，做了一个简单的循环
        try{
//            String ocrResult=instance.doOCR(imageFile);
            List<Word>  ocrResult=instance.getWords(ImageIO.read(new FileInputStream(imageFile)),1);
            if(ocrResult.size()>0){
                ocrResult.forEach(it->{
                    System.out.println(it.getText());
//                    System.out.println(JsonObject.mapFrom(it));
                });
            }
//            if(imageFile.exists()&&ocrResult!=""){
//                result=ocrResult;
//            }else {
//                while(true){
//                    Thread.sleep(1000);
//                    if(imageFile.exists()&&ocrResult!=""){
//                        result=ocrResult;
//                        break;
//                    }
//                }
//            }

        }catch(Exception e){
//        }catch(TesseractException e){
            System.out.println(e.getMessage());
        }
        return result;
    }
}
