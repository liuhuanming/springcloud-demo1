package com.springcloud.netty.Netty2;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.UnsupportedEncodingException;

/**
 * @description:
 * @author: Think
 * @date: 2020-06-28 22:23
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception{
        System.out.println("server channel active ....");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws UnsupportedEncodingException {
        String body = (String) msg;
        System.err.println("服务器接收：" + body);
        //服务端给客户端的响应
        String response = "hi,client!";
        ctx.writeAndFlush(Unpooled.copiedBuffer(response.getBytes()));
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("读完了");
        ctx.flush();
    }
    // 发生异常时触发
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable t) throws Exception {
         ctx.close();
     }
}
