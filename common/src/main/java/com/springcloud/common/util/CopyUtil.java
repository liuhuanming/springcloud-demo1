package com.springcloud.common.util;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @description: Bean相关的拷贝
 * @author: Administrator
 * @date: 2021-02-28 8:37
 */
public class CopyUtil {

    /*
     * 类型转换：实体Vo <->实体  例如：List<UserVo> <-> List<User>
     */
    public static <T> List<T> copyList(List<T> srcList, Class<T> targetType) {
        if (CollectionUtils.isEmpty(srcList)) {
            return Collections.emptyList();
        }
        List<T> newList = new ArrayList<>();
        for (Object src : srcList) {
            BeanUtils.copyProperties(src, targetType);
            srcList.add((T) targetType);
        }
        return newList;
    }

    /**
     * 类型转换：Object[]转Vo
     * 当使用自定义SQL查询，查询字段跟实体对应不上时，可以使用Object[]接值
     * em.createNativeQuery(sql.toString())，第二个参数不传时，默认就是用Object[]来接值
     * 因为是Object[]转Vo，是按顺序来取值、设置，所有要求两边的字段、属性顺序要一一对应
     */
    public static <T> T copyByObject(Object[] src, Class<T> targetType){
        T targetVo = null;
        try {
            //遍历Object[]转换为Field[]
            targetVo  = targetType.newInstance();
            Field[] fields = targetType.getDeclaredFields();
            int length = Math.min(src.length, fields.length);
            for (int i = 0; i < length; i++) {
                Field field = fields[i];
                Object fieldVal = src[i];
                if (fieldVal instanceof Character || fieldVal instanceof BigDecimal) {
                    fieldVal = String.valueOf(fieldVal);
                }

                field.setAccessible(true);//获取授权
                field.set(targetVo, fieldVal);//赋值
            }
        } catch (InstantiationException | IllegalAccessException e) {
            System.out.println(e.toString());
        }
        return targetVo;
    }

    /**
     * 类型转换：List<Object[]>转List<Vo>
     */
    public static <T> List<T> copyListByObject(List<Object[]> srcList, Class<T> targetType) {
        List<T> newList = new ArrayList<>();
        if (srcList != null) {
            for (Object[] src : srcList) {
                newList.add(CopyUtil.copyByObject(src,targetType));
            }
        }
        return newList;
    }
}
