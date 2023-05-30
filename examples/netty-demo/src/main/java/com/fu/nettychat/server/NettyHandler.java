package com.fu.nettychat.server;

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
        Channel channel = ctx.channel();
        channelGroup.forEach(info -> {
            if (info == channel) {
                info.writeAndFlush("--自己--" + info.remoteAddress() + "发送了消息" + msg);
            } else {
                info.writeAndFlush("--客户端--" + info.remoteAddress() + "发送了消息" + msg);
            }
        });

    }

    /**
     * 第一次客户端channel连接
     *
     * @param ctx
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        // 获取当前客户端的channel
        Channel channel = ctx.channel();
        // 发送给当前channel中的所有客户端
        channelGroup.writeAndFlush("--客户端--" + channel.remoteAddress() + "上线了");
        channelGroup.add(channel);
        System.out.println(channel.remoteAddress() + "上线了");
    }

    /**
     * 心跳机制
     *
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        ctx.fireUserEventTriggered(evt);
    }

    /**
     * 客户端端开连接
     *
     * @param ctx
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        // 获取当前客户端的channel
        Channel channel = ctx.channel();
        // 发送给当前channel中的所有客户端
        channelGroup.writeAndFlush("--客户端--" + channel.remoteAddress() + "下线了");
        System.out.println(channel.remoteAddress() + "下线了");
        System.out.println("当前剩余用户数为" + channelGroup.size());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // 异常关闭通道
        ctx.close();
    }
}
