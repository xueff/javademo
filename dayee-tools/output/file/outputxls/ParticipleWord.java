//
//package output.file.outputxls;
//
//import com.baidu.aip.nlp.AipNlp;
//import com.dayee.wintalent.framework.utils.StringUtils;
//
//import org.apache.commons.io.FileUtils;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.jboss.util.collection.ConcurrentSet;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.*;
//import java.util.concurrent.ConcurrentHashMap;
//
///**
// * @Author: dayee_yangkai
// * @Description:
// * @Date: Created in 15:03 2017/11/21
// * @Modified By:
// * @Version
// */
//public class ParticipleWord {
//
//    // 设置APPID/AK/SK
//    public static final String APP_ID     = "10412427";
//
//    public static final String API_KEY    = "e12XSWEvw3BpYBTelByrHTHt";
//
//    public static final String SECRET_KEY = "j8sgkkxU8aOPt3onCLyzX9DVY0eG0oj2";
//
//    public static void main(String[] args) {
//
//        Map<String,List<String>> jobMap = readFromSheetColumn("D:\\职位信息.xls",
//                                                       1, 1, 1);
//        Map<String,List<ParticipleResult>> typeMap = new LinkedHashMap<String,List<ParticipleResult>>();
//        int num = jobMap.size();
//        int threadnum = 0;
//        if(num>10){
//            threadnum = num/10;
//        }
//        
//        for (int i = 0; i <= threadnum; i++) {
//            ParticipleWord pw = new ParticipleWord();
//        }
//        // 初始化一个AipNlp
//        AipNlp client = new AipNlp(APP_ID, API_KEY, SECRET_KEY);
//
//        // 可选：设置网络连接参数
//        client.setConnectionTimeoutInMillis(2000);
//        client.setSocketTimeoutInMillis(60000);
//
//        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//        // client.setHttpProxy("proxy_host", proxy_port); // 设置http代理
//        // client.setSocketProxy("proxy_host", proxy_port); // 设置socket代理
//        int countjobtypeSet = 0;
//        System.out.println("工作类型长度："+jobMap.size());
//        int threeline = 0;
//        for (String jobType : jobMap.keySet()) {
//            List<ParticipleResult> results = new ArrayList<ParticipleResult>();
//            System.out.println("\n工作类型==："+countjobtypeSet++);
////            if(countjobtypeSet == 3)
////                break;
//            List<String> jobTypeList = jobMap.get(jobType);
//            String sendString = "";  //要发送的String
//            String outOfLengthString = "";  //加入sendString要超出500的字符，，，一次发送不能超过500
//            
//            ////
//            //测试3行
////            if(threeline == 2)
////                break;
//            for (String typeString : jobTypeList) {
//                if(typeString.length()>=500){
//                    System.out.println("\n超过500的："+typeString.length());
//                    String[] stringArr = SplitWord(typeString);
//                    for (int i = 0; i < stringArr.length; i++) {
//                        List<ParticipleResult> parList = getResults(client, stringArr[i]);
//                        if(parList != null && parList.size() >= 0)
//                            results.addAll(parList);
//                    }
//                }else{
//                    List<ParticipleResult> parList = getResults(client, typeString);
//                    if(!parList.isEmpty())
//                        results.addAll(parList);
//                }
////                results = new ArrayList<ParticipleResult>(); 
//            }
//            System.out.println("\n=============="+results.size());
//            typeMap.put(jobType,results);
//            threeline++;
//        }
//        adjustResults(typeMap);
//    }
//    /**
//     * 指定位置截取字符串，，统计频率不好
//     * @param typeString
//     * @return
//     */
//    private static String[] SplitWord(String typeString) {
//        int multiples = (typeString.length()/500)+1;
//        String[] stringArr = new String[multiples];
//        
//        for (int i = 0; i < multiples; i++) {
//            int end = (i+1)*500;
//            if(end>=typeString.length())
//                end = typeString.length();
//            stringArr[i] = typeString.substring(i*500, end);
//        }
//        
//       return stringArr;
//        
//    }
//
//    private static List<ParticipleResult> getResults(AipNlp client,
//                                                     String text) {
//        // 调用接口
//        JSONObject res = client.lexer(StringUtils.deleteWhitespace(text));
//        System.out.print(res);
//        JSONArray itemList;
//        try {
//            itemList = res.getJSONArray("items");
//        } catch (JSONException e) {
//           return null;
//        }
//
//        List<ParticipleResult> results = new ArrayList<>();
//        for (int i=0;i<itemList.length();i++) {
//            JSONObject _item = itemList.getJSONObject(i);
//            if (_item != null) {
//                ParticipleResult result = new ParticipleResult();
//                result.setWord(String.valueOf(_item.get("item")));
//                result.setNe(String.valueOf(_item.get("ne")));
//                result.setPos(String.valueOf(_item.get("pos")));
//                result.setFormal(String.valueOf(_item.get("formal")));
//                result.setFrequency(1);
//                results.add(result);
//            }
//        }
//        return results;
//    }
//
//    /* 处理结果，封装成特定map */
//    private static void adjustResults(Map<String,List<ParticipleResult>> typeMap) {
//        Map<String,List<ParticipleResult>> resultMap = new LinkedHashMap<String,List<ParticipleResult>>();
////        Iterator<List<ParticipleResult>> it = typeMap.keySet();
//        int maxColumn = 0;
//        for (String key : typeMap.keySet()) {
//            if(maxColumn<typeMap.get(key).size())
//                maxColumn = typeMap.get(key).size();
//            
//            System.out.println("[][][][]["+typeMap.get(key).size());
//            Map<String, ParticipleResult> result_map = new HashMap<>();
//            for (ParticipleResult result : typeMap.get(key)) {
//                String word = result.getWord();
//                if (result_map.containsKey(result.getWord())) {
//                    result_map.get(word)
//                    .setFrequency(result_map.get(word).getFrequency() + result.getFrequency());
//                } else {
//                    String type;
//                    String ne = result.getNe();
//                    if (StringUtils.isNotEmpty(ne)) {
//                        type = DescUtils.proper_map.get(ne);
//                    } else {
//                        type = DescUtils.speech_map.get(result.getPos());
//                    }
//                    result.setType(type);
//                    result_map.put(word, result);
//                }
//            }
//            List<ParticipleResult> _results = new ArrayList<>(result_map.values());
//            Collections.sort(_results, new Comparator<ParticipleResult>() {
//                
//                @Override
//                public int compare(ParticipleResult o1, ParticipleResult o2) {
//                    
//                    return o2.getFrequency() - o1.getFrequency();
//                }
//            });
//            resultMap.put(key,_results);
//        }
//        writeFile("D:\\proxy\\1212\\result-my.csv", resultMap,maxColumn);
//    }
//
//    private static  Map<String,List<String>> readFromSheetColumn(String path,
//                                                    int sheetPage,
//                                                    int column,
//                                                    int rowNum) {
//
////        List<String> contentList = new ArrayList<>();
//        
//        Map<String,List<String>> jobTypeMap = new HashMap<String,List<String>>();
//        try {
//            // 同时支持Excel 2003、2007
//            File excelFile = new File(path); // 创建文件对象
//            FileInputStream is = new FileInputStream(excelFile); // 文件流
//            Workbook workbook = new HSSFWorkbook(is);
//            // Workbook workbook = WorkbookFactory.create(is); // 这种方式
//            // Excel2003/2007/2010都是可以处理的
//
//            // int sheetCount = workbook.getNumberOfSheets(); // Sheet的数量
//            /**
//             * 设置当前excel中sheet的下标：0开始
//             */
//            Sheet sheet = workbook.getSheetAt(sheetPage - 1); // 遍历第一个Sheet
//
//            int rowCount = sheet.getLastRowNum();
//
////            int blankColumn = 0;  //统计空行，超过连续3个结束遍历
//            for (int i = 1; i <= rowCount; i++) {
//                String content = "";
//                //职位
//                String jobType = sheet.getRow(i).getCell(0)
//                        .toString();
//                String cellValue = sheet.getRow(i).getCell(1)
//                        .toString();
//                cellValue +=  sheet.getRow(i).getCell(2).toString();
//                cellValue = new String((cellValue).getBytes("GB2312"), "GBK");
//                cellValue = cellValue.replace("/", ",");
//                content = content + cellValue;
//                
//                if(jobTypeMap.containsKey(jobType)){
//                    List<String> contentList = jobTypeMap.get(jobType);
//                    contentList.add(content);
//                    jobTypeMap.put(jobType, contentList);
//                    
//                }else{
//                    List<String> contentList = new ArrayList<String>();
//                    contentList.add(content);
//                    jobTypeMap.put(jobType,contentList);
//                }
//                
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return jobTypeMap;
//    }
//
//    private static void writeFile(String filePath,Map<String,List<ParticipleResult>> resultMap,int maxColumn) {
//        
//        resultMap = merge(resultMap);
//        
//        StringBuffer content = new StringBuffer("");
//        StringBuffer title = new StringBuffer("");
//        
//        for (int i = 0; i < resultMap.size(); i++) {
//            content.append("word,participle,frequency,");
//        }
//        if(content.length()>0){
//            content = content.deleteCharAt(content.length()-1);
//            System.out.println(content);
//            content.append("\n");
//        }
//        
//        List<StringBuffer> eachLineList = new ArrayList<StringBuffer>(maxColumn);
//        for (int i = 0; i < maxColumn; i++) {
//            eachLineList.add(i,new StringBuffer(""));
//        }
//        System.out.println("maxColumn="+maxColumn);;
//        for (String key : resultMap.keySet()) {
//            title.append(key+" , , ,");
//                boolean flag = true;
//                for (int i = 0; i < maxColumn;) {
//                    if(flag){
//                        for (ParticipleResult result : resultMap.get(key)) {
//                            StringBuffer nowLine = new StringBuffer("");;
//                            if (!StringUtils.equalsAny(result.getWord(),
//                                                       new String[] { ",", "","\"" })) {
//                                if(StringUtils.isEmpty(result.getWord(),true))
//                                    nowLine.append(" ");
//                                else
//                                    nowLine.append(result.getWord());
//                                nowLine.append(",");
//                                if(StringUtils.isEmpty(result.getType(),true))
//                                    nowLine.append(" ");
//                                else
//                                    nowLine.append(result.getType());
//                                nowLine.append(",");
//                                nowLine.append(result.getFrequency());
//                                nowLine.append(",");
//                                eachLineList.get(i).append(nowLine);
//                                System.out.println("====****==="+eachLineList.get(i));
//                                i++;
//        //                        continue continueFlag;
//                            }
//                        }
//                    }
//                        flag = false;
//                        if(i==maxColumn)
//                            continue;
//                        eachLineList.get(i).append(new StringBuffer(" , , ,"));
//                        i++;
////                        System.out.println("+++++++"+eachLineList.get(i));
//                }
//           
//        }
//        System.out.println("hangshu"+eachLineList.size());
//        for (StringBuffer eachline : eachLineList) {
//            try {
//                eachline = eachline.deleteCharAt(eachline.length()-1).append("\n");
//            } catch (Exception e) {
//            }
//            System.out.println("*****"+eachline);
//            content  = content.append(eachline);
//        }
//        for (StringBuffer eachline : eachLineList) {
//            System.out.println("1111111*****"+eachline);
//        }
//
//        
//        
//        File file = new File(filePath);
//        try {
//            title = title.deleteCharAt(title.length()-1).append("\n");
//            content = title.append(content);
//            FileUtils.write(file, content, "UTF-8", true);
//            System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
////            System.out.println(content);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    private static Map<String, List<ParticipleResult>> merge(Map<String, List<ParticipleResult>> resultMap) {
//        Map<String,List<ParticipleResult>> resultMapNew = new LinkedHashMap<String,List<ParticipleResult>>();
//        Map<String,ParticipleResult> wordMap = new LinkedHashMap<String,ParticipleResult>();
//        for (String key : resultMap.keySet()) {
//            List<ParticipleResult> list = resultMap.get(key);
//            System.out.println("onekey长度"+list.size());
//            List<ParticipleResult> listNew = new LinkedList<ParticipleResult>();
//            for (ParticipleResult participleResult : list) {
//                System.out.println("word"+participleResult.getWord());
//                if(!wordMap.containsKey(participleResult.getWord()))
//                    wordMap.put(participleResult.getWord(), participleResult);
//                else{
//                    participleResult.setFrequency(participleResult.getFrequency()+participleResult.getFrequency());
//                    System.out.println("hzhzhzhzh==="+participleResult.getFrequency());
//                    wordMap.put(participleResult.getWord(),participleResult);
//                }
//            }
//            for (String word : wordMap.keySet()) {
//                listNew.add(wordMap.get(word));
//            }
//            resultMapNew.put(key, listNew);
//            wordMap = new LinkedHashMap<String, ParticipleResult>();
//        }
//        return resultMapNew;
//        
//    }
//}