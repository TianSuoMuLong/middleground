package com.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
/**
 * 新核心接口xml报文生成
 * @author hanrm
 *
 */
public class Testpoi {
	//	sheet页名称
	private String sheetName="CBS.300000090.01"; 
	//	接口文档路径
	private String filePath = "C:\\Users\\hanrm\\Desktop\\核心对外发布接口标准清单-V2.7.xlsx";

	@Test
	public void testMain() throws Exception {
		
		String fileName=sheetName.replace(".", "_").substring(0, sheetName.lastIndexOf("."))+"01";
		//指定生成xml文件的位置
		File file = new File("C:\\Users\\hanrm\\Desktop\\xhx\\"+fileName+".xml");
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file);
//写公共头		
		fw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
				"<beans xmlns=\"http://www.springframework.org/schema/beans\"\r\n" + 
				"	xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:gw=\"http://www.ynet.com/gw\"\r\n" + 
				"	xsi:schemaLocation=\"http://www.springframework.org/schema/beans \r\n" + 
				"	http://www.springframework.org/schema/beans/spring-beans.xsd \r\n" + 
				"	http://www.ynet.com/gw http://www.ynet.com/gw.xsd\">\r\n" + 
				"	<gw:message id=\""+fileName+"_send\" name=\"transaction\">\r\n" + 
				"		<gw:group name=\"header\">\r\n" + 
				"			<gw:field name=\"ver\" refName=\"ver\"  need=\"false\" value=\"1.0\" />\r\n" + 
				"			<gw:group name=\"msg\">\r\n" + 
				"				<gw:field name=\"rcvAppCd\" refName=\"rcvAppCd\"  need=\"false\" value=\"CBS\" />\r\n" + 
				"				<gw:field name=\"callTyp\" refName=\"callTyp\"  need=\"false\" value=\"SYN\"/>\r\n" + 
				"				<gw:field name=\"msgCd\" refName=\"msgCd\"  need=\"false\" value=\""+sheetName+"\"/>\r\n" + 
				"				<gw:field name=\"seqNb\" refName=\"seqNb\"  need=\"false\" />\r\n" + 
				"				<gw:field name=\"sndTm\" refName=\"sndTm\"  need=\"false\" />\r\n" + 
				"				<gw:field name=\"sndDt\" refName=\"sndDt\"  need=\"false\" />\r\n" + 
				"				<gw:field name=\"sndAppCd\" refName=\"sndAppCd\"  need=\"false\" />\r\n" + 
				"			</gw:group>\r\n" + 
				"		</gw:group>\r\n" + 
				"		<gw:group name=\"body\">\r\n" + 
				"			<gw:group name=\"request\">\r\n" + 
				"				<gw:group name=\"ReqBizHdr\">\r\n" + 
				"					<gw:field name=\"ChnlCD\" refName=\"ChnlCD\"  need=\"false\" />\r\n" + 
				"					<gw:field name=\"InsNo\" refName=\"InsNo\"  need=\"false\" />\r\n" + 
				"					<gw:field name=\"TlrNo\" refName=\"TlrNo\"  need=\"false\" />\r\n" + 
				"					<gw:field name=\"BrNo\" refName=\"BrNo\"  need=\"false\" />\r\n" + 
				"					<gw:field name=\"LglPsnID\" refName=\"LglPsnID\"  need=\"false\" />\r\n" + 
				"					<gw:field name=\"LglPsnInsNo\" refName=\"LglPsnInsNo\"  need=\"false\" />\r\n" + 
				"					<gw:field name=\"ExtInsNo\" refName=\"ExtInsNo\"  need=\"false\" />\r\n" + 
				"					<gw:field name=\"RvwAudr\" refName=\"RvwAudr\"  need=\"false\" />\r\n" + 
				"					<gw:field name=\"ExtSvcCD\" refName=\"ExtSvcCD\"  need=\"false\" />\r\n" + 
				"					<gw:field name=\"TrdPtTm\" refName=\"TrdPtTm\"  need=\"false\" />\r\n" + 
				"					<gw:field name=\"ExtStmDt\" refName=\"ExtStmDt\"  need=\"false\" />\r\n" + 
				"					<gw:field name=\"TraRefNo\" refName=\"TraRefNo\"  need=\"false\" />\r\n" + 
				"					<gw:field name=\"StmJrnlNo\" refName=\"StmJrnlNo\"  need=\"false\" />\r\n" + 
				"					<gw:field name=\"StmID\" refName=\"StmID\"  need=\"false\" />\r\n" + 
				"					<gw:field name=\"SubStmID\" refName=\"SubStmID\"  need=\"false\" />\r\n" + 
				"					<gw:field name=\"BsnTp\" refName=\"BsnTp\"  need=\"false\" />\r\n" + 
				"					<gw:field name=\"BsnSubTp\" refName=\"BsnSubTp\"  need=\"false\" />\r\n" + 
				"					<gw:field name=\"Arcs\" refName=\"Arcs\"  need=\"false\" />\r\n" + 
				"					<gw:field name=\"CtyRgonCD\" refName=\"CtyRgonCD\"  need=\"false\" />\r\n" + 
				"					<gw:field name=\"RgonCD\" refName=\"RgonCD\"  need=\"false\" />\r\n" + 
				"					<gw:field name=\"FeeChkInd\" refName=\"FeeChkInd\"  need=\"false\" />\r\n" + 
				"					<gw:field name=\"CmsnCur\" refName=\"CmsnCur\"  need=\"false\" />\r\n" + 
				"					<gw:field name=\"CmsnChrgTraAmt\" refName=\"CmsnChrgTraAmt\"  need=\"false\" />\r\n" + 
				"					<gw:field name=\"CmsnAcctNo\" refName=\"CmsnAcctNo\"  need=\"false\" />\r\n" + 
				"					<gw:field name=\"FeeAccAccNm\" refName=\"FeeAccAccNm\"  need=\"false\" />\r\n" + 
				"					<gw:field name=\"CmsnCshexInd\" refName=\"CmsnCshexInd\"  need=\"false\" />\r\n" + 
				"					<gw:field name=\"CmsnJrnlNo\" refName=\"CmsnJrnlNo\"  need=\"false\" />\r\n" + 
				"					<gw:field name=\"CmsnTAmt\" refName=\"CmsnTAmt\"  need=\"false\" />\r\n" + 
				"					<gw:field name=\"FeeCd1\" refName=\"FeeCd1\"  need=\"false\" />\r\n" + 
				"					<gw:field name=\"FeeCd2\" refName=\"FeeCd2\"  need=\"false\" />\r\n" + 
				"					<gw:field name=\"FeeCd3\" refName=\"FeeCd3\"  need=\"false\" />\r\n" + 
				"					<gw:field name=\"FeeCd4\" refName=\"FeeCd4\"  need=\"false\" />\r\n" + 
				"					<gw:field name=\"FeeCd5\" refName=\"FeeCd5\"  need=\"false\" />\r\n" + 
				"				</gw:group>\r\n");
		Workbook wb = null;
		Sheet sheet = null;
		Row row = null;
		List<Map<String, String>> list = null;
		wb = readExcel(filePath);
		if (wb != null) {
			// 用来存放表中数据
			list = new ArrayList<Map<String, String>>();
			// 获取第一个sheet
			sheet = wb.getSheet(sheetName);
			// 获取最大行数
			int rownum = sheet.getPhysicalNumberOfRows();
			// 获取第一行
			row = sheet.getRow(0);
			// 获取最大列数
			int colnum = row.getPhysicalNumberOfCells();
			int num = 0;
			// 遍历sheet页
			aa: for (int i = 5; i < rownum; i++) {
				row = sheet.getRow(i);
				if (row != null) {
					String chaneseName = (String) getCellFormatValue(row.getCell(0));// 中文名称
					String eName = (String) getCellFormatValue(row.getCell(1));// 字段名称
					String flag = ((String) getCellFormatValue(row.getCell(2)));// 是否必输
					String type = (String) getCellFormatValue(row.getCell(3));// 字段类型
					String menuValue = (String) getCellFormatValue(row.getCell(9));// 字段类型
					
					if(menuValue.trim().length()>0) {
						chaneseName=chaneseName +" "+menuValue;
					}
					
					if (flag.equals("O") || flag.equals("M")) {// 取字段
						flag = flag.equals("O") ? "false" : "true";
						if (type.equals("AM-A&M节点")) {
							num++;
							if (eName.startsWith(".")) {
								eName = eName.substring(eName.lastIndexOf(".") + 1);
							}
							fw.write("			<gw:list name=\"" + eName + "\" refName=\"" + eName + "\"  need=\"" + flag + "\"><!--" + chaneseName + " -->\r\t");
//							System.out.println("<gw:list name=\"" + eName + "\" refName=\"" + eName + "\"  need=\"" + flag + "\"><!--" + chaneseName + " -->");
						} else {
							if (eName.startsWith(".")) {
								eName = eName.substring(eName.lastIndexOf(".") + 1);
								fw.write("				<gw:field name=\"" + eName + "\" refName=\"" + eName + "\"  need=\"" + flag + "\"><!--" + chaneseName + " -->\r\t");
//								System.out.println("<gw:field name=\"" + eName + "\" refName=\"" + eName + "\"  need=\"" + flag + "\"><!--" + chaneseName + " -->");
							} else {
								if (num > 0) {
									num--;
									fw.write("			</gw:list>\r\t");
//									System.out.println("</gw:list>");
								}
								fw.write("			<gw:field name=\"" + eName + "\" refName=\"" + eName + "\"  need=\"" + flag + "\"><!--" + chaneseName + " -->\r\t");
//								System.out.println("<gw:field name=\"" + eName + "\" refName=\"" + eName + "\"  need=\"" + flag + "\"><!--" + chaneseName + " -->");
							}
						}
					} else {
						if (num > 0) {
							num--;
							fw.write("			</gw:list>\r\t");
//							System.out.println("</gw:list>");
						}
						if (chaneseName.equals("中文名称 枚举列表")) {
							fw.write("		</gw:group>\r\n" + 
									"		</gw:group>\r\n" + 
									"	</gw:message>\r\n" + 
									"\r\n" + 
									"	<gw:message id=\""+fileName+"_receive\" name='transaction'>\r\n" + 
									"		<gw:group name=\"header\">\r\n" + 
									"			<gw:group name=\"status\">\r\n" + 
									"				<gw:field name=\"desc\" refName=\"errorMsg\"  need=\"false\" />\r\n" + 
									"				<gw:field name=\"appCd\" refName=\"appCd\"  need=\"false\" />\r\n" + 
									"				<gw:field name=\"retCd\" refName=\"errorCode\"  need=\"false\" />\r\n" + 
									"			</gw:group>\r\n" + 
									"			<gw:field name=\"ver\" refName=\"ver\"  need=\"false\"/>\r\n" + 
									"			<gw:group name=\"msg\">\r\n" + 
									"				<gw:field name=\"rcvAppCd\" refName=\"rcvAppCd\"  need=\"false\" />\r\n" + 
									"				<gw:field name=\"callTyp\" refName=\"callTyp\"  need=\"false\" />\r\n" + 
									"				<gw:field name=\"msgCd\" refName=\"msgCd\"  need=\"false\" />\r\n" + 
									"				<gw:field name=\"refSndAppCd\" refName=\"refSndAppCd\"  need=\"false\" />\r\n" + 
									"				<gw:field name=\"refSndDt\" refName=\"refSndDt\"  need=\"false\" />\r\n" + 
									"				<gw:field name=\"seqNb\" refName=\"seqNb\"  need=\"false\" />\r\n" + 
									"				<gw:field name=\"refMsgCd\" refName=\"refMsgCd\"  need=\"false\" />\r\n" + 
									"				<gw:field name=\"sndTm\" refName=\"sndTm\"  need=\"false\" />\r\n" + 
									"				<gw:field name=\"sndDt\" refName=\"sndDt\"  need=\"false\" />\r\n" + 
									"				<gw:field name=\"sndAppCd\" refName=\"sndAppCd\"  need=\"false\" />\r\n" + 
									"				<gw:field name=\"refSeqNb\" refName=\"refSeqNb\"  need=\"false\" />\r\n" + 
									"			</gw:group>\r\n" + 
									"		</gw:group>\r\n" + 
									"		<gw:group name=\"body\">\r\n" + 
									"			<gw:group name=\"response\">\r\n" + 
									"				 <gw:group name=\"RspBizHdr \">\r\n" + 
									"					<gw:field name=\"SvcPsRltCD\" refName=\"SvcPsRltCD\"  need=\"false\" />\r\n" + 
									"					<gw:field name=\"SvcPsRltInf\" refName=\"SvcPsRltInf\"  need=\"false\" />\r\n" + 
									"					<gw:field name=\"OprgDayPrd\" refName=\"OprgDayPrd\"  need=\"false\" />\r\n" + 
									"					<gw:field name=\"JrnlNo\" refName=\"JrnlNo\"  need=\"false\" />\r\n" + 
									"					<gw:field name=\"RetTm\" refName=\"RetTm\"  need=\"false\" />\r\n" + 
									"					<gw:field name=\"CmsnTAmt\" refName=\"CmsnTAmt\"  need=\"false\" />\r\n" + 
									"					<gw:field name=\"CollCmsnInd\" refName=\"CollCmsnInd\"  need=\"false\" />\r\n" + 
									"					<gw:field name=\"CmsnCshexInd\" refName=\"CmsnCshexInd\"  need=\"false\" />\r\n" + 
									"					<gw:field name=\"CmsnCur\" refName=\"CmsnCur\"  need=\"false\" />\r\n" + 
									"					<gw:field name=\"CmsnTDnum\" refName=\"CmsnTDnum\"  need=\"false\" />\r\n" + 
									"					<gw:field name=\"CmsnAcctNo\" refName=\"CmsnAcctNo\"  need=\"false\" />\r\n" + 
									"					<gw:field name=\"CmsnChrgTraAmt\" refName=\"CmsnChrgTraAmt\"  need=\"false\" />\r\n" + 
									"					<gw:field name=\"ChnlCD\" refName=\"ChnlCD\"  need=\"false\" />\r\n" + 
									"					<gw:field name=\"InsNo\" refName=\"InsNo\"  need=\"false\" />\r\n" + 
									"					<gw:field name=\"TlrNo\" refName=\"TlrNo\"  need=\"false\" />\r\n" + 
									"					<gw:field name=\"BrNo\" refName=\"BrNo\"  need=\"false\" />\r\n" + 
									"					<gw:field name=\"LglPsnID\" refName=\"LglPsnID\"  need=\"false\" />\r\n" + 
									"				</gw:group>\r\t");
//							System.out.println("========================");
						}
					}
				}
			}
			while (num > 0) {
				num--;
				fw.write("			</gw:list>\r\t");
//				System.out.println("</gw:list>");
			}
			fw.write("		</gw:group>\r\n" + 
					"		</gw:group>\r\n" + 
					"	</gw:message>\r\n" + 
					"</beans>");
		}
		fw.flush();
	}

	// 读取excel
	public static Workbook readExcel(String filePath) {
		Workbook wb = null;
		if (filePath == null) {
			return null;
		}
		String extString = filePath.substring(filePath.lastIndexOf("."));
		InputStream is = null;
		try {
			is = new FileInputStream(filePath);
			if (".xls".equals(extString)) {
				return wb = new HSSFWorkbook(is);
			} else if (".xlsx".equals(extString)) {
				return wb = new XSSFWorkbook(is);
			} else {
				return wb = null;
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return wb;
	}

	public static Object getCellFormatValue(Cell cell) {
		Object cellValue = null;
		if (cell != null) {
			// 判断cell类型
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_NUMERIC: {
				cellValue = String.valueOf(cell.getNumericCellValue());
				break;
			}
			case Cell.CELL_TYPE_FORMULA: {
				// 判断cell是否为日期格式
				if (DateUtil.isCellDateFormatted(cell)) {
					// 转换为日期格式YYYY-mm-dd
					cellValue = cell.getDateCellValue();
				} else {
					// 数字
					cellValue = String.valueOf(cell.getNumericCellValue());
				}
				break;
			}
			case Cell.CELL_TYPE_STRING: {
				cellValue = cell.getRichStringCellValue().getString();
				break;
			}
			default:
				cellValue = "";
			}
		} else {
			cellValue = "";
		}
		return cellValue;
	}
}
