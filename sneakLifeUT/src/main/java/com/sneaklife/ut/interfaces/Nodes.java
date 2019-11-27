package com.sneaklife.ut.interfaces;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/8/9 15:05
 */
public interface Nodes<S, M, L>{

    /**
     * Find node
     * @param node The parent node
     * @param list All the nodes
     * @param parent =true: You can find the parent node  =false: You cannot find a parent node
     * @return Parent node tape node
     */
     M findNode(S node, L list, boolean parent);

    /**
     * Find child node
     * @param parent The parent node
     * @param list All the nodes
     * @return Parent node tape node
     */
     M findChildNode(S parent, L list);

    /**
     * Remove duplicate nodes from all nodes
     * @param parentNode Delete the item
     * @param list All the nodes
     * @param size The size of all nodes, can change the list length, do not need to pass 0
     * @return Residual size of all nodes
     */
     int removeNode(M parentNode, L list, int size);
}
