package com.sneaklife.ut.excel;

import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableFont.FontName;
import jxl.write.WriteException;

public class ExcelStyle {

	private WritableCellFormat writableCellFormat;
	private WritableFont writableFont;

	public ExcelStyle() {
		super();
	}

	public ExcelStyle(FontName fn, Integer ps, boolean noB, Boolean it, UnderlineStyle us, Colour c) {
		if (noB) {
			this.writableFont = new WritableFont(fn, ps, WritableFont.NO_BOLD, it, us, c);
		} else {
			this.writableFont = new WritableFont(fn, ps, WritableFont.BOLD, it, us, c);
		}
		try {
			setWritableCellFormat(this.writableFont);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ExcelStyle(WritableFont writableFont) throws WriteException {
		setWritableCellFormat(writableFont);
	}

	public WritableFont getWritableFont() {
		return this.writableFont;
	}

	public WritableCellFormat getWritableCellFormat() {
		return writableCellFormat;
	}

	public void setWritableCellFormat(WritableFont writableFont) throws WriteException {
		this.writableCellFormat = new WritableCellFormat(writableFont);
		this.writableCellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		this.writableCellFormat.setAlignment(Alignment.CENTRE);
		this.writableCellFormat.setWrap(false);
	}

}
