package com.sneaklife.ut.interfaces;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/8/9 14:09
 */
public interface ParameterTransformation<S, M, L> {

    /**
     * 字段转换
     * @param m 转换之后
     * @param s 转换之前
     * @return 转换之后
     */
    M paramTrans(M m, S s);

    /**
     * 字段转换(List)
     * @param l 转换之后
     * @param m 转换之前
     * @return 转换之后
     */
    L fixedParamTrans(L l, M m);
}
