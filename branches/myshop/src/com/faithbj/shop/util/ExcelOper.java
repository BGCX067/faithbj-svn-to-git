package com.faithbj.shop.util;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelOper {

	private static final Log log = LogFactory.getLog(ExcelOper.class);
	
	/**
	 * 创建样式
	 * @param workbook
	 * @return
	 */
	@SuppressWarnings("unused")
	private static HSSFCellStyle createStyle(HSSFWorkbook workbook){
		HSSFFont font = workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		HSSFCellStyle cellStyle= workbook.createCellStyle();
		cellStyle.setFont(font);
		return cellStyle;
	}
	
	/**
	 * 创建标题
	 * @param workbook
	 * @param sheet
	 * @param title
	 */
	private static void createTitle(HSSFWorkbook workbook,HSSFSheet sheet,Map<String, String> title){
		HSSFRow titleRow = sheet.createRow(0);
		HSSFCellStyle cellStyle = ExcelOper.createStyle(workbook);//创建样式
		if (title!=null && title.size()>0) {
			int i = 0;
			Iterator<String> it = title.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next();
				String value = title.get(key);
				HSSFCell cell = titleRow.createCell(i++);
				cell.setCellStyle(cellStyle);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellValue(value);
			}
		}
	}
	/**
	 * 导出excle表
	 * @param sheetName 工作溥名称
	 * @param source	数据源
	 * @param title		列标题
	 * @param response	
	 */
	@SuppressWarnings("unchecked")
	public static void export(String sheetName,List source,Map<String, String> title,HttpServletResponse response){
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		workbook.setSheetName(0, sheetName);
		ExcelOper.createTitle(workbook,sheet, title);//创建标题
		try {
			if (source!=null && source.size()>0) {
				for (int i=0;i<source.size();i++) {
					HSSFRow row = sheet.createRow(i+1);
					Object obj = source.get(i);
					if (title!=null && title.size()>0) {
						int j = 0;
						for (Object key : title.keySet()) {
							HSSFCell cell = row.createCell(j++);
							Field field = Class.forName(obj.getClass().getName()).getDeclaredField(key.toString());
							if (field!=null && !field.equals("")) {
								field.setAccessible(true);
								if (field.getType().equals(String.class)) {
									cell.setCellType(HSSFCell.CELL_TYPE_STRING);
									cell.setCellValue(field.get(obj).toString());
								}else if (field.getType().equals(int.class)) {
									cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
									cell.setCellValue(field.getInt(obj));
								}else {
									cell.setCellType(HSSFCell.CELL_TYPE_STRING);
									cell.setCellValue(field.get(obj).toString());
								}
							}
							
						}
					}
				}
			}else {
				log.info("没有数据");
			}
			response.addHeader("Content-Disposition","attachment; filename=results.xls");
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/vnd.ms-excel");
			workbook.write(response.getOutputStream());
			response.getOutputStream().flush();
		} catch (SecurityException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}finally{
			try {
				response.getOutputStream().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
