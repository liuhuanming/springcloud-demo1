package com.springcloud.common.tree;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: Think
 * @date: 2020-09-20 22:33
 */
@Data
public class Node {
    private String id; // 当前节点
    private String parentId; // 父级节点
    private Integer deptLevel; // 深度层级
    private String nodeName; // 当前节点名称
    private String label; // 标签
    // 节点数量
    private Integer currNum = 0;
    private Integer childNumCount = 0; // 下级节点数量
    // 子节点
    private List<? extends Node> childNodeList = new ArrayList<>();
}
