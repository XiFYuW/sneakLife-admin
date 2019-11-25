package com.sneaklife.pms.entity.modal;

import com.sneaklife.pms.entity.OperaIn;
import com.sneaklife.pms.entity.OperaSb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Opera implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Map<String,Object>> sb = new ArrayList<>(0);

    private List<List<OperaIn>> in = new ArrayList<>(0);

    public Opera() { super();}

    public Opera(List<Map<String,Object>> sb, List<List<OperaIn>> in) {
        this.sb = sb;
        this.in = in;
    }

    public List<Map<String,Object>> getSb() {
        return sb;
    }

    public void setSb(List<Map<String,Object>> sb) {
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
