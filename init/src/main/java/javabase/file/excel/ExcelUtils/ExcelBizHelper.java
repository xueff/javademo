package javabase.file.excel.ExcelUtils;


import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

import com.alibaba.fastjson.JSON;

import cn.hutool.core.io.FileUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;

/**
 * excel业务封装
 * @author zhengkai
 */
public class ExcelBizHelper {

    public static String abc="#ABCDEFGHIJKLNMOPQRSTUVWXYZ";

    public Map<String,String> readExcel() {
        //模板处理 sheetIndex=0代表第一个
        ExcelReader readerModel = ExcelUtil.getReader(FileUtil.file("C:\\Users\\Administrator\\Desktop\\体检数据\\20180418发样检测条目筛选.xlsx"),0);
        Map<String,String> excelMapModel=new LinkedHashMap<String,String>();
        addCellToMapPlus(readerModel, excelMapModel, 1, 50, 2);
        System.out.println(JSON.toJSONString(excelMapModel));
        //数据处理 sheetName页签名称
        ExcelReader reader = ExcelUtil.getReader(FileUtil.file("C:\\Users\\Administrator\\Desktop\\体检数据\\匿名.xls"), "1");
        Map<String,String> excelMap=new LinkedHashMap<String,String>();
        //遍历模板excel数据,处理数据excel
        for (String modelkey:excelMapModel.keySet()) {
            //获取坐标值
            String valuePath = excelMapModel.get(modelkey);
            if(!StringUtils.isBlank(valuePath)) {
                valuePath=valuePath.replace("(","").replace(")", "");
                String[] rowcell = valuePath.split(",");
                //根据坐标,获取对应的数据excel的值
                addCellToMap3(reader, excelMap, modelkey, Integer.valueOf(rowcell[0]) , abc.indexOf(rowcell[1]));
            }
        }
        System.out.println(JSON.toJSONString(excelMap));
        return excelMap;
    }
    /*public static void main(String[] args) {
        ExcelBizHelper helper=new ExcelBizHelper();
        helper.readExcel();
    }*/
    /**
     * 将excel坐标数据添加到map(key,value)
     * 例如数据(1,A)='姓名'，(1,B)='XXX'
     * 只需调用addCellToMap(row=1,cell=1,。。。)
     * cell参数,A=1,B=2,C=3以此类推
     * 即可获取map('姓名','XXX')
     * @author zhengkai
     */
    public void addCellToMap(ExcelReader reader,Map<String,String> map,int row,int cell) {
        map.put(getValue(reader.getSheet().getRow(row-1).getCell(cell-1)), getValue(reader.getSheet().getRow(row-1).getCell(cell)));
    }
    /**
     * 将excel坐标数据添加到map(str+key,value)
     * 例如数据(1,A)='姓名'，(1,B)='XXX'
     * 只需调用addCellToMap(str='用户信息',row=1,cell=1,。。。)
     * cell参数,A=1,B=2,C=3以此类推
     * 即可获取map('用户信息-姓名','XXX')
     * @author zhengkai
     */
    public void addCellToMap2(ExcelReader reader,Map<String,String> map,String str,int row,int cell) {
        map.put(str+"-"+getValue(reader.getSheet().getRow(row-1).getCell(cell-1)), getValue(reader.getSheet().getRow(row-1).getCell(cell)));
    }
    /**
     * 循环遍历将excel坐标数据添加到map(key,value)
     * 例如数据(1,B)='姓名'，(1,C)='XXX'
     * 例如数据(2,B)='性别'，(2,C)='YYY'
     * 只需调用addCellToMapPlus(row_start=1,row_end=2,cell=1,。。。)
     * cell参数,A=1,B=2,C=3以此类推
     * 即可获取map{('姓名','XXX')+map('性别','YYY')
     * @author zhengkai
     */
    public void addCellToMapPlus(ExcelReader reader,Map<String,String> map,int row_start,int row_end,int cell) {
        for(int r=row_start;r<=row_end;r++) {
            addCellToMap(reader, map, r, cell);
        }
    }
    /**
     * 循环遍历将excel坐标数据添加到map(str+key,value)
     * 例如数据(1,B)='姓名'，(1,C)='XXX'
     * 例如数据(2,B)='性别'，(2,C)='YYY'
     * 只需调用addCellToMapPlus2(row_start=1,row_end=2,cell=1,str='用户信息'。。。)
     * cell参数,A=1,B=2,C=3以此类推
     * 即可获取map{('用户信息-姓名','XXX')+map('用户信息-性别','YYY')
     * @author zhengkai
     */
    public void addCellToMap2Plus(ExcelReader reader,String str,Map<String,String> map,int row_start,int row_end,int cell) {
        for(int r=row_start;r<=row_end;r++) {
            addCellToMap2(reader, map, str, r, cell);
        }
    }
    /**
     * 将excel坐标数据添加到map(str+key,value)
     * 例如数据(1,B)='XXX'
     * 只需调用addCellToMap3(namestr='姓名',row=1,cell=2,。。。)
     * cell参数,A=1,B=2,C=3以此类推
     * 即可获取map('姓名','XXX')
     * @author zhengkai
     */
    public void addCellToMap3(ExcelReader reader,Map<String,String> map,String namestr,int row,int cell) {
        map.put(namestr, getValue(reader.getSheet().getRow(row-1).getCell(cell-1)));
    }

    /**
     * 获取excel的值
     * @author zhengkai
     */
    public String getValue(Cell c) {
        try {
            if(CellType.NUMERIC.equals(c.getCellTypeEnum())) {
                return c.getNumericCellValue()+"";
            }else{
                return c.getStringCellValue()+"";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
