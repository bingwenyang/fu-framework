package com.fu.nettychat.server;

import com.fu.nettychat.server.NettyHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author: asus
 * @date: 2022/8/16
 * @description:
 */
public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        //以下三个是Http的支持
        //http解码器
        pipeline.addLast("decoder", new StringDecoder());
        //http解码器
        pipeline.addLast("encoder", new StringEncoder());
        //添加自定义的业务类助手类
        pipeline.addLast(new NettyHandler());
    }
}