package io.github.kimmking.gateway.outbound.httpclient4;

import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;

/**
 * Hello world!
 *
 */
public class MyHttpClientOutboundHandler {
	
	private String backendUrl;
	
	public MyHttpClientOutboundHandler(String backendUrl) {
		this.backendUrl = backendUrl.endsWith("/") ? backendUrl.substring(0,backendUrl.length()-1) : backendUrl;
	}
    public void handle(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx) {
    	
		HttpGet httpGet = new HttpGet(backendUrl);	
		FullHttpResponse rsp = null;
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build();
				CloseableHttpResponse response = httpClient.execute(httpGet)) {
			
			System.out.println("状态:" + response.getStatusLine());
			HttpEntity responseEntity = response.getEntity();
			
			 byte[] body = EntityUtils.toByteArray(responseEntity);
   
           rsp = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(body));
           rsp.headers().set("Content-Type", "application/json");
           rsp.headers().setInt("Content-Length", Integer.parseInt(response.getFirstHeader("Content-Length").getValue()));
   
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rsp = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
		} finally {
			if (fullRequest != null) {
                if (!HttpUtil.isKeepAlive(fullRequest)) {
                    ctx.write(rsp).addListener(ChannelFutureListener.CLOSE);
                } else {
                    //response.headers().set(CONNECTION, KEEP_ALIVE);
                    ctx.write(rsp);
                }
            }
            ctx.flush();
		}
    }
}
