package com.sneaklife.dao.entity.modal;

import com.sneaklife.dao.entity.Columns;

import java.io.Serializable;
import java.util.List;

public class Table implements Serializable {
    private static final long serialVersionUID = 1L;

    private String dataUrl;

    private List<Columns> columns;

    public Table() { super();}

    public Table(String dataUrl, List<Columns> columns) {
        this.dataUrl = dataUrl;
        this.columns = columns;
    }

    public String getDataUrl() {
        return dataUrl;
    }

    public void setDataUrl(String dataUrl) {
        this.dataUrl = dataUrl;
    }

    public List<Columns> getColumns() {
        return columns;
    }

    public void setColumns(List<Columns> columns) {
        this.columns = columns;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"url\":\"")
                .append(dataUrl).append('\"');
        sb.append(",\"columns\":")
                .append(columns);
        sb.append('}');
        return sb.toString();
    }
}
