package com.springcloud.netty.config;

import com.springcloud.netty.File.FileServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: Think
 * @date: 2020-08-04 00:12
 */
@Component
@ConditionalOnProperty(
        value = {"netty.file.enabled"},
        matchIfMissing = false
)
@Slf4j
public class FilePipeline extends ChannelInitializer<SocketChannel> {

    @Autowired
    FileServerHandler fileServerHandler;

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline p = socketChannel.pipeline();
        p.addLast("http-decoder", new HttpRequestDecoder());
        p.addLast("http-aggregator", new HttpObjectAggregator(65536));
        p.addLast("http-encoder", new HttpResponseEncoder());
        p.addLast("http-chunked", new ChunkedWriteHandler());
        p.addLast("fileServerHandler", fileServerHandler);
    }
}
