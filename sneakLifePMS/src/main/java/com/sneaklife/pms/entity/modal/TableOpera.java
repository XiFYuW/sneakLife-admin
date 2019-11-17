package com.sneaklife.pms.entity.modal;

import java.io.Serializable;
import java.util.Objects;


public class TableOpera implements Serializable {

    private static final long serialVersionUID = 1L;

    private Opera opera =  new Opera();

    private Table table = new Table();

    public TableOpera() { super();}

    public TableOpera(Opera opera, Table table) {
        this.opera = opera;
        this.table = table;
    }

    public Opera getOpera() {
        return opera;
    }

    public void setOpera(Opera opera) {
        this.opera = opera;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"opera\":")
                .append(opera);
        sb.append(",\"table\":")
                .append(table);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if(!(obj instanceof TableOpera)){
            return false;
        }
        TableOpera tableOpera = (TableOpera) obj;
        return this.opera.equals(tableOpera.opera) && this.table.equals(tableOpera.table);
    }

    @Override
    public int hashCode() {
        return Objects.hash(opera,table);
    }
}
