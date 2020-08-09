package com.springcloud.netty.config;

import io.netty.util.internal.SystemPropertyUtil;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @description:
 * @author: Think
 * @date: 2020-08-04 00:06
 */
@ConfigurationProperties(prefix = "netty.file")
@Data
@Validated
public class NettyFileProperties {

    @NotNull(message = "端口不能为空")
    @Range(min = 1000, max = 60000)
    private Integer port;

    @NotNull(message = "文件路径不能为空")
    private String path;

    @Pattern(regexp = "((25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))", message = "ip地址格式不正确")
    private String bindIp;

    //必须大于1 ,老板线程，即认为是分配工作的线程
    @DecimalMin("1")
    private Integer bossThreads = Math.max(1, SystemPropertyUtil.getInt(
            "io.netty.eventLoopThreads", Runtime.getRuntime().availableProcessors() * 2));

    //必须大于1，实际工作线程数量，这个数量最好根据JVM的系统信息进行配置，这里直接动态获取
    @DecimalMin("1")
    private Integer workThreads = Math.max(1, SystemPropertyUtil.getInt(
            "io.netty.eventLoopThreads", Runtime.getRuntime().availableProcessors() * 2));

}