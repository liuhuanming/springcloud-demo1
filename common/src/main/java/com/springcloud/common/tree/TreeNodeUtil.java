package com.springcloud.common.tree;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

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

    /**
     * 获取当前节点及所有子节点的label标签集合
     * @param node 集合
     * @param <T> 类型
     * @return List<String>
     */
    public static <T extends Node> List<String> getAllLabel(T node) {
        List<String> labels = new ArrayList<>();
        // 当前节点的label
        labels.add(node.getLabel());
        // 下属子节点的label
        for (Node tempNode : node.getChildNodeList()) {
            if (!CollectionUtils.isEmpty(tempNode.getChildNodeList())) { // 叶子节点，添加到结果中
                labels.add(tempNode.getLabel());
            } else { // 非叶子节点
                List<String> childList = getAllLabel(node);
                labels.addAll(childList);
            }
        }
        return labels;
    }

    /**
     * 构造多个平级树节点
     * @param list 树数据
     * @param <T> 类型
     * @return 树
     */
    public static <T extends Node> List<T> buildMultiTree(List<T> list) {
        List<T> treeNodes = new ArrayList<>();
        List<T> rootNodes = getRootNodes(list);
        T t = null;
        for (int i = 0; i < rootNodes.size(); i++) {
//            t = rootNodes.get(i);
            t = buildChildTree(rootNodes.get(i), 0, list);
            treeNodes.add(t);
        }
        return treeNodes;
    }

    /**
     * 获取根节点，可以有N个根，parentId == null|| 等于0
     * @param list 集合
     * @param <T> 类型
     * @return 返回集合中的根节点
     */
    public static <T extends Node> List<T> getRootNodes(List<T> list) {
        List<T> rootNodes = new ArrayList<>();
        for (T t : list) {
            if (StringUtils.isEmpty(t.getParentId())||"0".equals(t.getParentId())) {
                rootNodes.add(t);
            }
        }
        return rootNodes;
    }

    public static <T extends Node> void clearNodeCount(T node) {
        if (node!=null) {
            node.setChildNodeList(null);
            node.setChildNumCount(0);
        }
        if (CollectionUtils.isEmpty(node.getChildNodeList())) {
            for (Node tempNode : node.getChildNodeList()) {
                clearNodeCount(tempNode);
            }
        }
    }
}
