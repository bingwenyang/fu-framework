package com.fu.nettychat.client;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author: yangbingwen
 * @date: 2022/8/16
 * @description:
 */
public class NettyHandler extends SimpleChannelInboundHandler<String> {
    //所有正在连接的channel都会存在这里面，所以也可以间接代表在线的客户端
    public static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * 接收到消息
     *
     * @param ctx
     * @param msg
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) {
        // 获取当前客户端的channel
        System.out.println("获取到的消息为" + msg);

    }
}
