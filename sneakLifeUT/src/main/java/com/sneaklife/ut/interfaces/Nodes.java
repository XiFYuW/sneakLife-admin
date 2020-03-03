package com.sneaklife.ut.interfaces;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/8/9 15:05
 */
public interface Nodes<S, M, L>{

    /**
     * 查找节点的子节点
     * @param node 父节点
     * @param list 节点列表
     * @param parent =true: 当前节点是父节点  =false: 当前节点不是父节点
     * @return 父节点以及子节点
     */
    M findChildNode(S node, L list, boolean parent);

    /**
     * 去重
     * @param keyExtractor 去重关键字段
     * @param <T> 对象类型
     * @return 去重之后的数据
     */
    default <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Set<Object> set = ConcurrentHashMap.newKeySet();
        return t -> set.add(keyExtractor.apply(t));
    }
}
