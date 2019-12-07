package com.sneaklife.ut.check;

import com.sneaklife.ut.exception.SneakLifeFailureException;

import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/12/7 11:47
 */
public class SneakLifeCheckStateContext {

    private SneakLifeCheckState sneakLifeState;

    private SneakLifeCheckStateContext() {}

    public void change(SneakLifeCheckState sneakLifeState) {
        this.sneakLifeState = sneakLifeState;
    }

    public void handle(String value, Map<String, Object> rule) throws SneakLifeFailureException {
        sneakLifeState.handle(value, rule);
    }

    private static class Singleton {
        private static final SneakLifeCheckStateContext sneakLifeCheckStateContext = new SneakLifeCheckStateContext();
    }

    public static SneakLifeCheckStateContext singleton(CheckState checkState) {
        Singleton.sneakLifeCheckStateContext.change(checkState.toSneakLifeCheckState());
        return Singleton.sneakLifeCheckStateContext;
    }

    public static SneakLifeCheckStateContext singleton() {
        return Singleton.sneakLifeCheckStateContext;
    }
}

