package com.netty.ch1;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * 作者：DarkKing
 * 创建日期：2019/10/02
 * 类说明：netty客户端
 *
 */

public class NettyClient {

    private final int port;
    private final String host;


    public NettyClient(int port, String host) {
        this.port = port;
        this.host = host;
    }

    public void start() throws InterruptedException {
        /*线程组*/
        EventLoopGroup group = new NioEventLoopGroup();
        try{
            /*客户端启动必备*/
            Bootstrap b = new Bootstrap();
            b.group(group)/*把线程组传入*/
                    /*指定使用NIO进行网络传输*/
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(host,port))
                    .handler(new NettyClientHandler());
            /*连接到远程节点，阻塞直到连接完成*/
            ChannelFuture f = b.connect().sync();
            /*阻塞程序，直到Channel发生了关闭*/
            f.channel().closeFuture().sync();
        }finally {
            group.shutdownGracefully().sync();
        }
    }
    public static void main(String[] args) throws InterruptedException {
        new NettyClient(9999,"127.0.0.1").start();
    }
}
