package com.springcloud.netty.File;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.stream.ChunkedFile;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @description:
 * @Sharable 注解用来说明ChannelHandler是否可以在多个channel直接共享使用
 * @author: Think
 * @date: 2020-08-04 00:34
 */
@Component
@ChannelHandler.Sharable
@Slf4j
public class FileServerHandler extends ChannelInboundHandlerAdapter {
    @Value("${netty.file.path}")
    String path;

    // 读取文件
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            if (msg instanceof FullHttpRequest) {
                FullHttpRequest req = (FullHttpRequest) msg;
                // get方法 不允许
                if (req.method() != HttpMethod.GET) {
                    sendError(ctx, HttpResponseStatus.METHOD_NOT_ALLOWED);
                }
                String uri = req.uri();
                File file = new File(path + uri);
                if (file.exists()) {
                    if (file.isDirectory()) { // 是目录
                        if (uri.endsWith("/")) {
                            sendList(ctx, file);
                        } else {
                            sendRedirect(ctx, uri + "/");
                        }
                        return;
                    } else {
                        transferFile(file, ctx);
                    }
                } else {
                    sendError(ctx, HttpResponseStatus.NOT_FOUND);
                }
            }
        } catch (Exception e) {
        }
    }
    // 传输文件
    private void transferFile(File file, ChannelHandlerContext ctx) {

        try {
            // 只读
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
            long length = randomAccessFile.length();
            DefaultHttpResponse defaultHttpResponse = new DefaultHttpResponse(HttpVersion.HTTP_1_0, HttpResponseStatus.OK);
            defaultHttpResponse.headers().set(HttpHeaderNames.CONTENT_LENGTH, length);
            ctx.write(defaultHttpResponse);

            ChannelFuture channelFuture = ctx.write(new ChunkedFile(
                    randomAccessFile, 0, length ,8192),ctx.newProgressivePromise());
            addListener(channelFuture);
            ChannelFuture lastChanelFuture = ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);
            lastChanelFuture.addListener(ChannelFutureListener.CLOSE);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * 监听传输状态
     * @param channelFuture
     */
    private void addListener(ChannelFuture channelFuture) {
        channelFuture.addListener(new ChannelProgressiveFutureListener() {
            @Override
            public void operationProgressed(ChannelProgressiveFuture channelProgressiveFuture,
                                            long progress, long total) throws Exception {
                if (total < 0) {
                    log.debug("Transfer progress: " + progress);
                } else {
                    log.debug("Transfer progress: " + progress + "/" + total);
                }
            }

            @Override
            public void operationComplete(ChannelProgressiveFuture channelProgressiveFuture) throws Exception {
                log.debug("transfer complete...");
            }
        });
    }

    /**
     * 跳转链接
     * @param ctx
     * @param newUrl
     */
    private static void sendRedirect(ChannelHandlerContext ctx, String newUrl) {
        DefaultHttpResponse defaultHttpResponse =
                new DefaultHttpResponse(HttpVersion.HTTP_1_0, HttpResponseStatus.FOUND);
        defaultHttpResponse.headers().set(HttpHeaderNames.LOCATION, newUrl);
        ctx.writeAndFlush(defaultHttpResponse).addListener(ChannelFutureListener.CLOSE);
    }

    /**
     * 请求为目录时，显示文件列表
     * @param ctx
     * @param dir
     */
    private static void sendList(ChannelHandlerContext ctx, File dir) {
        FullHttpResponse response =
                new DefaultFullHttpResponse(HttpVersion.HTTP_1_0, HttpResponseStatus.OK);
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html;charset=UTF-8");
        String path = dir.getPath();
        StringBuilder sbr = new StringBuilder();
        sbr.append("<!DOCTYPE html>\r\n");
        sbr.append("<html><head><title>");
        sbr.append(path);
        sbr.append("目录:");
        sbr.append("</title></head><body>\r\n");

        sbr.append("<h3>");
        sbr.append(path).append(" 目录：");
        sbr.append("</h3>\r\n");
        sbr.append("<ul>");
        sbr.append("<li>链接：<a href=\" ../\")..</a></li>\r\n");
        for (File f : dir.listFiles()) {
            if (f.isHidden() || !f.canRead()) {
                continue;
            }
            String name = f.getName();
            /*if (!ALLOWED_FILE_NAME.matcher(name).matches()) {
                continue;
            }*/
            sbr.append("<li>链接：<a href=\"");
            sbr.append(name);
            sbr.append("\">");
            sbr.append(name);
            sbr.append("</a></li>\r\n");
        }
        sbr.append("</ul></body></html>\r\n");
        ByteBuf byteBuf = Unpooled.copiedBuffer(sbr, CharsetUtil.UTF_8);
        response.content().writeBytes(byteBuf);
        byteBuf.release();
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }

    /**
     * 失败响应
     *
     * @param ctx
     * @param status
     */
    private static void sendError(ChannelHandlerContext ctx, HttpResponseStatus status) {
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status,
                Unpooled.copiedBuffer("Failure: " + status.toString() + "\r\n", CharsetUtil.UTF_8));
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html;charset=UTF-8");
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }
}
