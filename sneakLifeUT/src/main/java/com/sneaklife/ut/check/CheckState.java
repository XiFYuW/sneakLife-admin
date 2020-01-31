package com.sneaklife.ut.check;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/12/7 11:30
 */
public enum CheckState {

    IS_NULL("IS_NULL", new SneakLifeStateCheckISNULL()),

    IS_NOT_NULL("IS_NOT_NULL", new SneakLifeStateCheckISNOTNULL()),

    DATE_RANGE("DATE_RANGE", new SneakLifeStateCheckDATERANGE());

    private final String cs;

    private final SneakLifeCheckState sneakLifeCheckState;

    CheckState(String cs, SneakLifeCheckState sneakLifeCheckState) {
        this.cs = cs;
        this.sneakLifeCheckState = sneakLifeCheckState;
    }

    public String toCs() {
        return this.cs;
    }

    public SneakLifeCheckState toSneakLifeCheckState() {
        return sneakLifeCheckState;
    }
}
