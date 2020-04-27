package com.springcloud.quartz.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @description:
 * @author: Think
 * @date: 2020-04-14 08:00
 */
@ApiModel("定时任务")
@Entity(name = "ScheduleJobEntity")
@Table(name = "t_schedule_job")
@Data
public class ScheduleJobEntity{
    public static final String JOB_PARAM_KEY = "JOB_PARAM_KEY";
    @Id
    @GeneratedValue
    @ApiModelProperty("任务ID")
    private Long id;

    @Column
    private String name;

    /**
     * spring bean名称
     */
    @ApiModelProperty("spring bean名称")
    @NotBlank(message = "bean名称不能为空")
    private String beanName;

    /**
     * 方法名
     */
    @ApiModelProperty("方法名")
    @NotBlank(message = "方法名称不能为空")
    private String methodName;

    /**
     * 参数
     */
    @ApiModelProperty("参数")
    private String params;

    /**
     * cron表达式
     */
    @ApiModelProperty("cron表达式")
    @NotBlank(message = "cron表达式不能为空")
    private String cronExpression;

    /**
     * 任务状态
     */
    @ApiModelProperty("任务状态")
    private Integer status;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("启停")
    private Boolean enabled;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", hidden = true)
    private Date createTime;

}
