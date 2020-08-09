package com.springcloud.netty.File;

import com.springcloud.netty.config.FilePipeline;
import com.springcloud.netty.config.NettyFileProperties;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: Think
 * @date: 2020-08-03 23:50
 */
@Configuration
@EnableConfigurationProperties({NettyFileProperties.class})
@ConditionalOnProperty(
        value = {"netty.file.enabled"},
        matchIfMissing = false
)
public class FileServer {
    private static Logger logger = LoggerFactory.getLogger(FileServer.class);

    @Autowired
    private FilePipeline filePipeline;

    @Autowired
    NettyFileProperties nettyFileProperties;

    @Bean("starFileServer")
    public String start() {
        Thread thread = new Thread(() -> {
            NioEventLoopGroup bGroup = new NioEventLoopGroup(nettyFileProperties.getBossThreads());
            NioEventLoopGroup workerGroup = new NioEventLoopGroup(nettyFileProperties.getWorkThreads());

            logger.info("start netty [FileServer] server ,port: " + nettyFileProperties.getPort());
            ServerBootstrap bootstrap = new ServerBootstrap();
            options(bootstrap).group(bGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.DEBUG))
                    .childHandler(filePipeline);
            Channel channel = null;
            try {
                // 判断是否绑定ip
                if (StringUtils.isNotEmpty(nettyFileProperties.getBindIp())) {
                    channel = bootstrap.bind(nettyFileProperties.getBindIp(), nettyFileProperties.getPort()).sync().channel();
                } else {
                    channel = bootstrap.bind(nettyFileProperties.getPort()).sync().channel();
                }
                channel.closeFuture().sync();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                bGroup.shutdownGracefully();
                workerGroup.shutdownGracefully();
            }
        });
        thread.setName("file_server");
        thread.start();
        return thread.getName() + "start ...";
    }

    private ServerBootstrap options(ServerBootstrap bootstrap) {
        return bootstrap;
    }
}
