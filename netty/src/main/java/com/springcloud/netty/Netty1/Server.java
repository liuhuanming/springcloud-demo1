package com.springcloud.netty.Netty1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @description:
 * @author: Think
 * @date: 2020-06-28 22:02
 */
public class Server {
    public static void main(String[] args) throws InterruptedException {
        // 1创建两个线程组
        // 一个是用于处理服务端接收客户端连接的
        // 一个是用于网络通信的(网络读写的)
        EventLoopGroup pGroup = new NioEventLoopGroup();
        EventLoopGroup cGroup = new NioEventLoopGroup();

        // 2创建辅助工具类，用于服务器通道的配置
        ServerBootstrap bootstrap = new ServerBootstrap();
        // 绑定两个线程级
        bootstrap.group(pGroup, cGroup)
                .channel(NioServerSocketChannel.class) // 指定NIO的模式
                .option(ChannelOption.SO_BACKLOG, 1024) //指定TCP的缓冲区
                .option(ChannelOption.SO_SNDBUF, 32 * 1024) //指定发送区缓冲区
                .option(ChannelOption.SO_RCVBUF, 32 * 1024) //指定接收区缓冲区
                .option(ChannelOption.SO_KEEPALIVE, true) //保持连接
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel sc) throws Exception {
                        // 3 在这里指定具体的数据接收的方法
                        sc.pipeline().addLast(new ServerHandler());
                    }
                });
        // 4进行绑定
        ChannelFuture cf = bootstrap.bind(8005).sync();
        // 5等待关闭
        cf.channel().closeFuture().sync();
        pGroup.shutdownGracefully();
        cGroup.shutdownGracefully();
    }
}
