package io.github.kimmking.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

public class HttpRequestAddHeadFilter implements HttpRequestFilter {

	@Override
	public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
		
		System.out.println("add head.");
        fullRequest.headers().set("nio","netty_gateway_test");

	}

}
