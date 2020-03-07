package com.sneaklife.ut.excel;

import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.WritableFont;

public enum ExcelBody {

	EXCEL_CONTENT("正文", new ExcelStyle(WritableFont.createFont("宋体"), 12, true, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK)),

	EXCEL_TITLE("标题", new ExcelStyle(WritableFont.createFont("宋体"), 20, false, false, UnderlineStyle.NO_UNDERLINE, Colour.TEAL)),

	EXCEL_SUBHEAD("副标题", new ExcelStyle(WritableFont.createFont("宋体"), 18, false, false, UnderlineStyle.NO_UNDERLINE, Colour.BLUE)),

	EXCEL_COLUMN_HEADER("列头", new ExcelStyle(WritableFont.createFont("宋体"), 14, false, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK));

	private final String body;
	private final ExcelStyle excelStyle;

	ExcelBody(String body, ExcelStyle excelStyle) {
		this.body = body;
		this.excelStyle = excelStyle;
	}

	public String toBody() {
		return this.body;
	}

	public ExcelStyle toExcelStyle() {
		return this.excelStyle;
	}
}
