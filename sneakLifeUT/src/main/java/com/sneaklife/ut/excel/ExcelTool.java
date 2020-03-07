package com.sneaklife.ut.excel;

import com.sneaklife.ut.file.FileUtil;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.CellFormat;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * jxl生成文件 poi读取文件
 */
public class ExcelTool {

	private WritableWorkbook writableWorkbook;
	private List<WritableSheet> listSheet = new LinkedList<>();
	public Map<String, ExcelStyle> style = new ConcurrentHashMap<>();
	private int sheetCount;
	private static final int MAXROWS = 65535;

	public ExcelTool() {
		setDefaultStyle();
	}

	public ExcelTool(OutputStream outputStream) throws IOException {
		setWritableWorkbook(outputStream);
		setDefaultStyle();
	}

	public WritableWorkbook getWritableWorkbook() {
		return writableWorkbook;
	}

	public void setWritableWorkbook(OutputStream outputStream) throws IOException {
		this.writableWorkbook = Workbook.createWorkbook(outputStream);
	}

	public void setWritableWorkbook(WritableWorkbook writableWorkbook) throws IOException {
		this.writableWorkbook = writableWorkbook;
	}

	public Workbook getWorkbook(File file) throws BiffException, IOException {
		return Workbook.getWorkbook(file);
	}

	public Workbook getWorkbook(InputStream is) throws BiffException, IOException {
		return Workbook.getWorkbook(is);
	}

	public Sheet[] getSheet(File file) throws BiffException, IOException {
		return getWorkbook(file).getSheets();
	}

	public List<WritableSheet> getListSheet() {
		return this.listSheet;
	}

	public Map<String, ExcelStyle> getStyle() {
		return this.style;
	}

	public void createSheet(String sheetName, double sheetCount, double rows) {
		this.sheetCount = (int) sheetCount;
		int sheetCounts = (int) Math.ceil(rows / sheetCount);
		for (int i = 0; i < sheetCounts; i++) {
			addWritableSheet(this.writableWorkbook.createSheet(sheetName + "-" + i, i));
		}
	}

	public void createSheet(String sheetName, double rows) {
		createSheet(sheetName, MAXROWS, rows);
	}

	public WritableSheet getWritableSheet(int index) {
		return this.listSheet.get(index);
	}

	public void removeWritableSheet(int index) {
		this.listSheet.remove(index);
	}

	public void addWritableSheet(WritableSheet e) {
		this.listSheet.add(e);
	}

	public void mergeCells(int index, int x1, int y1, int x2, int y2) throws WriteException {
		getWritableSheet(index).mergeCells(x1, y1, x2, y2);
	}

	public void mergeCells(WritableSheet sheet, int x1, int y1, int x2, int y2) throws WriteException {
		sheet.mergeCells(x1, y1, x2, y2);
	}

	public void mergeCells(int x1, int y1, int x2, int y2) throws WriteException {
		for (int i = 0; i < getListSheet().size(); i++) {
			mergeCells(i, x1, y1, x2, y2);
		}
	}

	public void mergeCells(int start, int end, int x1, int y1, int x2, int y2) throws WriteException {
		for (int i = start; i < end + 1; i++) {
			mergeCells(i, x1, y1, x2, y2);
		}
	}

	public void addCell(int index, int c, int r, String cont, CellFormat st) throws WriteException {
		getWritableSheet(index).addCell(new Label(c, r, cont, st));
	}

	public void addCell(WritableSheet sheet, int c, int r, String cont, CellFormat st) throws WriteException {
		sheet.addCell(new Label(c, r, cont, st));
	}

	public void addCell(int c, int r, String cont, CellFormat st) throws WriteException {
		for (int i = 0; i < getListSheet().size(); i++) {
			addCell(i, c, r, cont, st);
		}
	}

	public void addCell(int start, int end, int c, int r, String cont, CellFormat st) throws WriteException {
		for (int i = start; i < end + 1; i++) {
			addCell(i, c, r, cont, st);
		}
	}

	public void setRowView(int index, int i, int h) throws RowsExceededException {
		getWritableSheet(index).setRowView(i, h);
	}

	public void setRowView(int k, int h) throws WriteException {
		for (int i = 0; i < getListSheet().size(); i++) {
			setRowView(i, k, h);
		}
	}

	public void setRowView(int start, int end, int k, int h) throws WriteException {
		for (int i = start; i < end + 1; i++) {
			setRowView(i, k, h);
		}
	}

	public void setColumnView(int index, int i, int h) {
		getWritableSheet(index).setColumnView(i, h);
	}

	public void setColumnView(int k, int h) throws WriteException {
		for (int i = 0; i < getListSheet().size(); i++) {
			setColumnView(i, k, h);
		}
	}

	public void setColumnView(int[] k, int[] h) throws WriteException {
		for (int i = 0; i < getListSheet().size(); i++) {
			for (int j = 0; j < k.length; j++) {
				try {
					setColumnView(i, j, h[j]);
				} catch (ArrayIndexOutOfBoundsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					setColumnView(i, j, h[0]);
				}
			}
		}
	}

	public void setColumnView(int start, int end, int k, int h) {
		for (int i = start; i < end + 1; i++) {
			setColumnView(i, k, h);
		}
	}

	public void putStyle(String key, ExcelStyle style) {
		this.style.put(key, style);
	}

	public void removeStyle(String key) {
		this.style.remove(key);
	}

	public void close() throws IOException, WriteException {
		writableWorkbook.write();
		writableWorkbook.close();
	}

	public void setDefaultStyle() {
		putStyle(ExcelBody.EXCEL_CONTENT.toBody(), ExcelBody.EXCEL_CONTENT.toExcelStyle());
		putStyle(ExcelBody.EXCEL_TITLE.toBody(), ExcelBody.EXCEL_TITLE.toExcelStyle());
		putStyle(ExcelBody.EXCEL_COLUMN_HEADER.toBody(), ExcelBody.EXCEL_COLUMN_HEADER.toExcelStyle());
		putStyle(ExcelBody.EXCEL_SUBHEAD.toBody(), ExcelBody.EXCEL_SUBHEAD.toExcelStyle());
	}

	public void fillcontent(List<Map<String, Object>> data, int sj) throws WriteException {
		int si = 0, m = 0, n = this.sheetCount;
		for (WritableSheet sheet : listSheet) {
			if (n >= data.size()) {
				n = data.size();
			}
			for (int i = m; i < n; i++) {
				Map<String, Object> cloumn = data.get(i);
				for (String key : cloumn.keySet()) {
					addCell(sheet, si++, sj, String.valueOf(cloumn.get(key)),
							style.get(ExcelBody.EXCEL_CONTENT.toBody()).getWritableCellFormat());
				}
				si -= cloumn.size();
				sj++;
			}
			sj -= this.sheetCount;
			m += this.sheetCount;
			n += this.sheetCount;
		}
	}

	public void fillTitle(List<Map<String, Object>> data, String title, int startx, int starty, int endy) throws WriteException {
		for (WritableSheet sheet : listSheet) {
			mergeCells(sheet, startx, starty, data.get(0).keySet().size() - 1, endy);
			addCell(sheet, 0, 0, title, style.get(ExcelBody.EXCEL_TITLE.toBody()).getWritableCellFormat());
		}
	}

	public void fillTitle(List<Map<String, Object>> data, String title, int endy) throws WriteException {
		fillTitle(data, title, 0, 0, endy);
	}

	public void fillHeader(List<Map<String, Object>> data, int startx, int starty) throws WriteException {
		for (WritableSheet sheet : listSheet) {
			Map<String, Object> cloumn = data.get(0);
			for (String key : cloumn.keySet()) {
				addCell(sheet, startx++, starty, key, style.get(ExcelBody.EXCEL_COLUMN_HEADER.toBody()).getWritableCellFormat());
			}
		}
	}

	public void fillHeader(List<Map<String, Object>> data, int starty) throws WriteException {
		fillHeader(data, 0, starty);
	}

	public static void main(String[] args) {
		ExcelTool excelTool = new ExcelTool();
		try {
			Map<String, List<Map<String, Object>>> data = excelTool.analyToListT("E:\\05.xlsx", 0, 0);
			FileUtil.outFile("D:\\A01\\list.txt", data);
		} catch (IOException | InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Map<String, List<Map<String, Object>>> analyToList(String path, int r, int c) throws IOException, InvalidFormatException {
		org.apache.poi.ss.usermodel.Workbook wb = WorkbookFactory.create(new File(path));
		int count = wb.getNumberOfSheets();
		Map<String, List<Map<String, Object>>> data = readExcel(wb, count, r, c);
		return data;
	}

	public Map<String, List<Map<String, Object>>> readExcel(org.apache.poi.ss.usermodel.Workbook wb, int count, int r,
			int c) {
		Map<String, List<Map<String, Object>>> data = new HashMap<>();
		int n = 0;
		while (n < count) {
			org.apache.poi.ss.usermodel.Sheet sheet = wb.getSheetAt(n);
			int rows = sheet.getPhysicalNumberOfRows();
			org.apache.poi.ss.usermodel.Row title = sheet.getRow(r);
			int columnNum = title.getPhysicalNumberOfCells();
			List<Map<String, Object>> list = new ArrayList<>();
			int x = r + 1;
			while (x < rows) {
				Map<String, Object> map = new HashMap<>();
				org.apache.poi.ss.usermodel.Row row = sheet.getRow(x);
				int y = c;
				while (y < columnNum) {
					String context = checkType(row.getCell(y));
					String rKey = checkType(title.getCell(y));
					map.put(rKey, context);
					y++;
				}
				list.add(map);
				x++;
			}
			data.put(sheet.getSheetName(), list);
			n++;
		}
		return data;
	}

	public Map<String, List<Map<String, Object>>> analyToListT(String path, int r, int c) throws IOException, InvalidFormatException {
		org.apache.poi.ss.usermodel.Workbook wb = WorkbookFactory.create(new File(path));
		int count = wb.getNumberOfSheets();
		Map<String, List<Map<String, Object>>> data = readExcelT(wb, count, r, c);
		return data;
	}

	public Map<String, List<Map<String, Object>>> readExcelT(org.apache.poi.ss.usermodel.Workbook wb, int count, int r,
			int c) {
		Map<String, List<Map<String, Object>>> data = new HashMap<>();
		int n = 0;
		while (n < count) {
			org.apache.poi.ss.usermodel.Sheet sheet = wb.getSheetAt(n);
			int rows = sheet.getPhysicalNumberOfRows();
			org.apache.poi.ss.usermodel.Row title = sheet.getRow(r);
			int columnNum = title.getPhysicalNumberOfCells();
			List<Map<String, Object>> list = new ArrayList<>();
			readExcelT1(rows, columnNum, r, c, sheet, title, list);
			data.put(sheet.getSheetName(), list);
			n++;
		}
		return data;
	}

	private void readExcelT1(int rows, int columnNum, int r, int c, org.apache.poi.ss.usermodel.Sheet sheet,
			org.apache.poi.ss.usermodel.Row title, List<Map<String, Object>> list) {
		int x = r + 1;
		if (x < rows) {
			Map<String, Object> map = new HashMap<>();
			org.apache.poi.ss.usermodel.Row row = sheet.getRow(x);
			// 一列的内容
			readExcelT2(columnNum, c, map, row, title);
			list.add(map);
			// 一列行的内容
			readExcelT1(rows, columnNum, ++x, c, sheet, title, list);
		}
	}

	private void readExcelT2(int columnNum, int c, Map<String, Object> map, org.apache.poi.ss.usermodel.Row row,
			org.apache.poi.ss.usermodel.Row title) {
		int y = c;
		if (y < columnNum) {
			String context = checkType(row.getCell(y));
			String rKey = checkType(title.getCell(y));
			map.put(rKey, context);
			readExcelT2(columnNum, ++y, map, row, title);
		}
	}

	private String checkType(org.apache.poi.ss.usermodel.Cell cell) {
		int cellType = -1;
		if (cell != null) {
			cellType = cell.getCellType();
		}
		String cellValue = null;
		switch (cellType) {
		case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING: // 文本
			cellValue = cell.getStringCellValue();
			break;
		case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC: // 数字、日期
			if (org.apache.poi.hssf.usermodel.HSSFDateUtil.isCellDateFormatted(cell)) {
				cellValue = new SimpleDateFormat("yyyy-MM-dd").format(cell.getDateCellValue()); // 日期型
			} else {
				cell.setCellType(org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING);// 将数字转成字符串
				cellValue = cell.getStringCellValue();
			}
			break;
		case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BOOLEAN: // 布尔型
			cellValue = String.valueOf(cell.getBooleanCellValue());
			break;
		case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BLANK: // 空白
			cellValue = cell.getStringCellValue();
			break;
		case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_ERROR: // 错误
			cellValue = String.valueOf(cell.getErrorCellValue());
			break;
		case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_FORMULA: // 公式
			try {
				if (org.apache.poi.hssf.usermodel.HSSFDateUtil.isCellDateFormatted(cell)) {
					cellValue = new SimpleDateFormat("yyyy-MM-dd").format(cell.getDateCellValue());
					break;
				} else {
					cellValue = String.valueOf(cell.getNumericCellValue());
				}
			} catch (IllegalStateException e) {
				cellValue = String.valueOf(cell.getRichStringCellValue());
			}
			break;
		default:
			cellValue = "";
		}
		return cellValue;
	}
}
