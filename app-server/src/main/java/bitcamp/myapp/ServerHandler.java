package bitcamp.myapp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.HttpRequest;
// Netty를 기반으로 하는 웹 서버에서 클라이언트의 요청을 처리하는 핸들러 클래스인 ServerHandler를 정의

public class ServerHandler extends SimpleChannelInboundHandler {
    private HttpRequest request;
    StringBuilder responseData = new StringBuilder();

    //클라이언트 요청 처리가 완료되었을때 호출되는 메소드
    //응답 데이터를 모두 보낸 뒤 채널을 비움
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    //클라이언트로부터 수신된 데이터 처리하는 메서드
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) {
       // implementation to follow
    }

    //예외가 발생했을 때 호출되는 메서드 
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}

