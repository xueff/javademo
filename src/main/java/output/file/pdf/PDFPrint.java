//package output.file.pdf;
//
//import java.util.ArrayList;
//import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
//import com.itextpdf.text.pdf.PdfPTable;
//import com.vgtech.clients.wmxt.contract.dao.IContractSalesCommodityDao;
//import com.vgtech.clients.wmxt.contract.dao.IContractSalesContractDao;
//import com.vgtech.clients.wmxt.contract.model.ContractSalesCommodityModel;
//import com.vgtech.clients.wmxt.contract.model.ContractSalesContractModel;
//import com.vgtech.clients.wmxt.customer.dto.CustomerInfoDto;
//import com.vgtech.clients.wmxt.customer.service.ICustomerService;
//import com.vgtech.platform.api.pdf.PDFUtility;
//import com.vgtech.platform.api.pdf.item.StringItem;
//import com.vgtech.platform.api.pdf.item.TableCellItem;
//import com.vgtech.platform.common.web.utility.VGUtility;
//
//public class PDFPrint  {
//    @Autowired
//    private IContractSalesContractDao salesContractDao;
//    @Autowired
//    private IContractSalesCommodityDao salesCommodityDao;
//    @Autowired
//    private ICustomerService customerService;
//
//
//
//    /***
//     * 打印购销合同
//     */
//    public byte[] printSalesContract(String salesContractId) {
//
//
//        ContractSalesContractModel salesModel = salesContractDao.findByPrimaryKey(salesContractId);
//        // 块一
//        List<TableCellItem> tableBlockOne = new ArrayList<TableCellItem>();
//
//        TableCellItem blockItem = new TableCellItem("购 销 合 同");
//        blockItem.setSize(16);
//        blockItem.setAlign(TableCellItem.ALIGN.居中对齐);
//        blockItem.setColSpan(2);
//        blockItem.setBorderWidth(0f);
//        tableBlockOne.add(blockItem);
//
//        blockItem = new TableCellItem(" ");
//        blockItem.setSize(9);
//        blockItem.setColSpan(2);
//        blockItem.setBorderWidth(0f);
//        tableBlockOne.add(blockItem);
//
//        String firstParty = "";
//
//
//        blockItem = new TableCellItem("甲方:" + firstParty);
//        blockItem.setSize(9);
//        blockItem.setAlign(TableCellItem.ALIGN.左对齐);
//        blockItem.setBorderWidth(0f);
//        tableBlockOne.add(blockItem);
//
//        String tempStr = "";
//
//
//        blockItem = new TableCellItem("合同编号:" + tempStr);
//        blockItem.setSize(9);
//        blockItem.setAlign(TableCellItem.ALIGN.左对齐);
//        blockItem.setColSpan(1);
//        blockItem.setBorderWidth(0f);
//        tableBlockOne.add(blockItem);
//
//
////块二
//        List<TableCellItem> tableBlockTwo = new ArrayList<TableCellItem>();
//        blockItem = new TableCellItem("货  号");
//        blockItem.setSize(9);
//        blockItem.setAlign(TableCellItem.ALIGN.居中对齐);
//        blockItem.setBorderWidth(0f);
//        blockItem.setTopBorderWidth(1f);
//        blockItem.setRightBorderWidth(1f);
//        blockItem.setBottomBorderWidth(1f);
//        tableBlockTwo.add(blockItem);
//
//
//        List<ContractSalesCommodityModel> commodityModelList = salesCommodityDao
//                .getListBySalesContractId(salesContractId);
//
//        String shipMarkStr = "";
//        String[] shipMarkStrList = shipMarkStr.split("\n");
//        if (shipMarkStrList.length < 8 && commodityModelList.size() < 8) {
//
//            //一行6 排
//
//        }
//        //块三
//        List<TableCellItem> tableBlockTrd = new ArrayList<TableCellItem>();
//
//
//        String[] tempStrList = tempStr.split("\n");
//
//// 有换行符  打印
//
////换行
//
//        blockItem = new TableCellItem("\n\n");
//        blockItem.setSize(9);
//        blockItem.setBorderWidth(0f);
//        tableBlockTrd.add(blockItem);
//
//
//
//        List<PdfPTable> tableList = new ArrayList<PdfPTable>();
//        tableList.add(PDFUtility.CreateTable(new float[] { 1f, 1f }, tableBlockOne, 560f, null, 0));
//        tableList.add(PDFUtility.CreateTable(new float[] { 1.5f, 4f, 1f, 2f, 2f, 2f }, tableBlockTwo, 560f, null, 0));
//        tableList.add(PDFUtility.CreateTable(new float[] { 1f }, tableBlockTrd, 560f, null, 0));
//
//        List<byte[]> pageList = new ArrayList<byte[]>();
//        pageList.add(PDFUtility.CreateTable(tableList, 0f));
//        if (commodityModelList.size() > 8 || shipMarkStrList.length > 8)
//            pageList.add(printSalesContractNextPage(salesModel, commodityModelList));
//        StringItem pageItem = new StringItem("Page: %s/%s");
//        pageItem.setX(559);
//        pageItem.setY(5);
//        pageItem.setAlign(StringItem.ALIGN.右对齐);
//        return PDFUtility.AddPageNumber(PDFUtility.MergeFile(pageList), pageItem);
//    }
//
//
//
//    // 打印购销合同第二页
//    private byte[] printSalesContractNextPage(ContractSalesContractModel salesContractModel,
//                                              List<ContractSalesCommodityModel> salesCommodityModelList) {
//        try {
//            List<TableCellItem> dataList = new ArrayList<TableCellItem>();
//
//            // ====================
//            // Title Table
//            TableCellItem titleItem = new TableCellItem(" ");
//
////			。。。。。。。。。。。。。。
//
//            // footer
//            List<TableCellItem> footerList = new ArrayList<TableCellItem>();
//
//
//            if (!VGUtility.isEmpty(salesContractModel.getSuppUnitId())) {
//                CustomerInfoDto customerInfoDto = customerService.getCustomerById(salesContractModel.getSuppUnitId());
//                if (!VGUtility.isEmpty(customerInfoDto)) {
//                    TableCellItem footerItem = new TableCellItem("乙方:" + customerInfoDto.getCustomerFullName());
//                    titleItem.setSize(9);
//                    footerItem.setAlign(TableCellItem.ALIGN.左对齐);
//                    footerItem.setBorderWidth(0f);
//                    footerItem.setColSpan(3);
//                    footerList.add(footerItem);
//                }
//            }
//
//            PdfPTable footerTable = PDFUtility.CreateTable(new float[] { 1.5f, 4f, 1f, 2f, 2f, 2f }, footerList, 560f,
//                    null, 0);
//            TableCellItem footerTableCell = new TableCellItem(footerTable);
//            footerTableCell.setColSpan(6);
//            footerTableCell.setBorderWidth(0f);
//            List<TableCellItem> footerDataList = new ArrayList<TableCellItem>();
//            footerDataList.add(footerTableCell);
//
//            List<PdfPTable> tableList = new ArrayList<PdfPTable>();
//            tableList.add(PDFUtility.CreateTable(new float[] { 1.5f, 4f, 1f, 2f, 2f, 2f }, dataList, 560f,
//                    footerDataList, 2));
//
//            return PDFUtility.CreateTable(tableList, 0f);
//        } catch (Exception e) {
//            throw new RuntimeException("打印出错！");
//        }
//    }
//
//
//}
//
