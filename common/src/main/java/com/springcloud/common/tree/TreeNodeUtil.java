package com.springcloud.common.tree;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: Think
 * @date: 2020-09-20 23:11
 */
public class TreeNodeUtil {
    // 生成单一树结构
    public static <T extends Node> T buildNode(List<T> list) {
        T tree = null;
        for (int i = 0; i < list.size(); i++) {
            if (null == list.get(i).getParentId()
                || "0".equals(list.get(i).getParentId())) {
                tree = buildChildTree(list.get(i), 0, list);
            }
        }
        return tree;
    }

    /**
     * 建立树型结构
     * @param parentNode 根节点尝试为1的父节点
     * @param deptLevel 层次深度
     * @param list 各节点
     * @param <T> 类型
     * @return Node
     */
    private static <T extends Node> T buildChildTree(T parentNode,
                                                     int deptLevel, List<? extends Node> list) {
        List<Node> childrenTree = new ArrayList<>();
        deptLevel += 1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getParentId() != null
                    && list.get(i).getParentId().equals(parentNode.getId())) {
                childrenTree.add(buildChildTree(list.get(i), deptLevel, list));
            }
        }
        parentNode.setDeptLevel(deptLevel);
        parentNode.setChildNodeList(childrenTree);
        return parentNode;
    }

    /**
     * 在树结构中根据label快速
     * @param label
     * @param node
     * @param <T>
     * @return
     */
    public static <T extends Node> T getNodeByLabel(String label, T node) {
        T resultNode = null;
        if (node.getLabel().equals(label)) {
            return node;
        }
        if (CollectionUtils.isEmpty(node.getChildNodeList())) {
            return null;
        }
        for (int i = 0; i < node.getChildNodeList().size(); i++) {
            resultNode = getNodeByLabel(label, (T)node.getChildNodeList().get(i));
            if (resultNode == null) {
                break;
            }
        }
        return resultNode;
    }
}
