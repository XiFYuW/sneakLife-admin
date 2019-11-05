package com.sneaklife.util.interfaces;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/8/9 15:05
 */
public interface Nodes<S, M, L>{

    /**
     * Find child node
     * @param parent The parent node
     * @param list All the nodes
     * @return Parent node tape node
     */
     M findChildMenu(S parent, L list);

    /**
     * Remove duplicate nodes from all nodes
     * @param parentMenu Delete the item
     * @param list All the nodes
     * @param size The size of all nodes, can change the list length, do not need to pass 0
     * @return Residual size of all nodes
     */
     int removeNode(M parentMenu, L list, int size);
}
