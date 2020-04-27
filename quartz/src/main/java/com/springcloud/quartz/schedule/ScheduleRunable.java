package com.springcloud.quartz.schedule;

import javax.annotation.security.RunAs;
import java.lang.annotation.Annotation;

/**
 * @description:
 * @author: Think
 * @date: 2020-04-17 08:03
 */
public class ScheduleRunable implements RunAs {
    @Override
    public String value() {
        return null;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}
