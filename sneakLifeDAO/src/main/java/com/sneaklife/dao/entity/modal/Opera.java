package com.sneaklife.dao.entity.modal;

import com.sneaklife.dao.entity.OperaIn;
import com.sneaklife.dao.entity.OperaSb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Opera implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<OperaSb> sb = new ArrayList<>(0);

    private List<List<OperaIn>> in = new ArrayList<>(0);

    public Opera() { super();}

    public Opera(List<OperaSb> sb, List<List<OperaIn>> in) {
        this.sb = sb;
        this.in = in;
    }

    public List<OperaSb> getSb() {
        return sb;
    }

    public void setSb(List<OperaSb> sb) {
        this.sb = sb;
    }

    public List<List<OperaIn>> getIn() {
        return in;
    }

    public void setIn(List<List<OperaIn>> in) {
        this.in = in;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"sb\":")
                .append(sb);
        sb.append(",\"in\":")
                .append(in);
        sb.append('}');
        return sb.toString();
    }
}
