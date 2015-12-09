/**
 * 
 */
package com.gsoft.esb.weixin.service.impl;

import java.io.IOException;
import java.net.URI;

import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

/**
 * @author zhyi_12
 *
 */
public class WxRequestFactory extends HttpComponentsClientHttpRequestFactory {

	@Override
	public ClientHttpRequest createRequest(URI uri, HttpMethod httpMethod)
			throws IOException {
		return super.createRequest(uri, httpMethod);
	}

	
}
