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
@ApiModel("定时任务日志")
@Entity(name = "ScheduleJobLogEntity")
@Table(name = "t_schedule_job_log")
@Data
public class ScheduleJobLogEntity {
    public static final String JOB_PARAM_KEY = "JOB_PARAM_KEY";
    @Id
    @GeneratedValue
    @ApiModelProperty("日志ID")
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
     * 任务状态    0：成功    1：失败
     */
    @ApiModelProperty("任务状态(0：成功,1：失败)")
    private Integer status;

    /**
     * 失败信息
     */
    @ApiModelProperty("失败信息")
    private String error;

    /**
     * 耗时(单位：毫秒)
     */
    @ApiModelProperty("耗时(单位：毫秒)")
    private Integer times;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remark;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", hidden = true)
    private Date createTime;

}
