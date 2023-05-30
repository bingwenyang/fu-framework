package com.fu.nettychat.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author: asus
 * @date: 2022/8/16
 * @description:
 */
public class ChatServer {

    public static void main(String[] args) {
        //创建主线程组，接收请求
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //创建从线程组，处理主线程组分配下来的io操作
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        //创建netty服务器
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)//设置主从线程组
                    .channel(NioServerSocketChannel.class)//设置通道
                    .childHandler(new NettyServerInitializer());//子处理器，用于处理workerGroup中的操作
            // 客户端启动
            System.out.println("服务端启动--------->");
            //启动server
            ChannelFuture channelFuture = serverBootstrap.bind(9000).sync();
            //监听关闭channel
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();//关闭主线程
            workerGroup.shutdownGracefully();//关闭从线程
        }
    }
}
