package com.fu.nettychat.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Scanner;

/**
 * @author: asus
 * @date: 2022/8/16
 * @description: 客户端
 */
public class ChatClient {

    public static void main(String[] args) {
        //客户端线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();

        //创建netty服务器
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(bossGroup)
                    .channel(NioSocketChannel.class)//设置通道
                    .handler(new NettyServerInitializer());
            //启动server
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 9000).sync();
            // 获取收到的消息channel
            Channel channel = channelFuture.channel();
            System.out.println("客户端启动" + channel.remoteAddress());
            // 输入
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                String msg = scanner.nextLine();
                // 发送消息到客户端
                channel.writeAndFlush(msg);
            }
            //监听关闭channel
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();//关闭主线程
        }
    }
}
