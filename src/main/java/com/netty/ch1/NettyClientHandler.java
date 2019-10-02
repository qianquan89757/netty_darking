package com.netty.ch1;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * 作者：DarkKing
 * 创建日期：2019/10/02
 * 类说明：netty客户端处理器
 *
 */
public class NettyClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    /*客户端读到数据以后，就会执行*/
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg)
            throws Exception {
        System.out.println("client acccept:"+msg.toString(CharsetUtil.UTF_8));
    }

    /*连接建立以后*/
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer(
                "Hello Netty",CharsetUtil.UTF_8));
        //ctx.fireChannelActive();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        cause.printStackTrace();

        ctx.close();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
    }
}
