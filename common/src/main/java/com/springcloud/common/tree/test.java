package com.springcloud.common.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: Think
 * @date: 2020-09-20 23:37
 */
public class test {
    public static void main(String[] args) {
        // 单树
//        List<Organization> organizations = new ArrayList<Organization>(){{
//            Organization org1 = new Organization();
//            org1.setId("1");
//            org1.setName("民族");
//            org1.setParentId("0");
//            this.add(org1);
//
//            Organization org4 = new Organization();
//            org4.setId("4");
//            org4.setName("汉");
//            org4.setParentId("1");
//            this.add(org4);
//            Organization org5 = new Organization();
//            org5.setId("5");
//            org5.setName("唐");
//            org5.setParentId("1");
//            this.add(org5);
//        }};
//        Organization organization = TreeNodeUtil.buildNode(organizations);
//        System.out.println(organization);

        // 构造多数
        List<Organization> organizations = new ArrayList<Organization>(){{
            Organization org1 = new Organization();
            org1.setId("1");
            org1.setName("民族");
            org1.setParentId("0");
            this.add(org1);

            Organization org2 = new Organization();
            org2.setId("2");
            org2.setName("国家");
            org2.setParentId("0");
            this.add(org2);

            Organization org3 = new Organization();
            org3.setId("3");
            org3.setName("宇宙");
            org3.setParentId("0");
            this.add(org3);

            Organization org4 = new Organization();
            org4.setId("4");
            org4.setName("汉");
            org4.setParentId("1");
            this.add(org4);
            Organization org5 = new Organization();
            org5.setId("5");
            org5.setName("唐");
            org5.setParentId("1");
            this.add(org5);
        }};
        Organization organization = TreeNodeUtil.buildNode(organizations);
        System.out.println(organization);
    }
}
