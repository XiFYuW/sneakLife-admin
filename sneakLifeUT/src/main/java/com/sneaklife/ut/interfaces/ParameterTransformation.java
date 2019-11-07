package com.sneaklife.ut.interfaces;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/8/9 14:09
 */
public interface ParameterTransformation<S, M, L> {

    M paramTrans(M m, S s);

    L fixedParamTrans(L l, M m);
}
