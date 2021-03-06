/*
 * Copyright 2011 Danish Maritime Authority. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *   1. Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 *
 *   2. Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY Danish Maritime Authority ``AS IS'' 
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

 * The views and conclusions contained in the software and documentation are those
 * of the authors and should not be interpreted as representing official policies,
 * either expressed or implied, of Danish Maritime Authority.
 * 
 */
package dk.frv.enav.ins.common;

import java.io.IOException;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.log4j.Logger;

import dk.frv.enav.ins.common.util.Compressor;
import dk.frv.enav.ins.services.shore.ShoreHttp;
import dk.frv.enav.ins.services.shore.ShoreServiceErrorCode;
import dk.frv.enav.ins.services.shore.ShoreServiceException;
import dk.frv.enav.ins.settings.EnavSettings;

/**
 * Class for making HTTP requests. Accepts gziped responses. 
 */
public class HttpRequest extends Thread {
	
	private static final Logger LOG = Logger.getLogger(ShoreHttp.class);
	
	private static final String USER_AGENT = "ee-INS";
	
	private String uri;
	private String url;
	private String host;
	private int port = 80;
	private int readTimeout = 60000; // 60 sec
	private int connectionTimeout = 30000; // 30 sec
	
	private HttpClient httpClient;
	private GetMethod method;
	private byte[] responseBody;
	
	/**
	 * Constructor given uri and enav settings
	 * @param uri
	 * @param enavSettings
	 */
	public HttpRequest(String uri, EnavSettings enavSettings) {
		this.host = enavSettings.getServerName();
		this.port = enavSettings.getHttpPort();
		this.connectionTimeout = enavSettings.getConnectTimeout();
		this.readTimeout = enavSettings.getReadTimeout();
		setUri(uri);
	}
	
	/**
	 * Initialize the class
	 */
	public void init() {
		httpClient = new HttpClient();
		method = new GetMethod(url);
		HttpConnectionManagerParams params = httpClient.getHttpConnectionManager().getParams();
		params.setSoTimeout(readTimeout);
		params.setConnectionTimeout(connectionTimeout);
		method.setRequestHeader("User-Agent", USER_AGENT);
		method.setRequestHeader("Connection", "close");
		method.addRequestHeader("Accept", "text/*");	
		method.addRequestHeader("Accept-Encoding", "gzip");
	}
	
	/**
	 * Make the actual request
	 * @throws ShoreServiceException
	 */
	public void makeRequest() throws ShoreServiceException {
		int statusCode;
		try {
			statusCode = httpClient.executeMethod(method);
		} catch (HttpException e) {
			LOG.error("HTTP request failed with: " + e.getMessage());
			throw new ShoreServiceException(ShoreServiceErrorCode.INTERNAL_ERROR);
		} catch (IOException e) {
			LOG.error("Failed to make HTTP connection: " + e.getMessage());
			throw new ShoreServiceException(ShoreServiceErrorCode.NO_CONNECTION_TO_SERVER);
		}
		
		if (statusCode != 200) {
			method.releaseConnection();
			throw new ShoreServiceException(ShoreServiceErrorCode.SERVER_ERROR);
		}
		
		try {
			responseBody = method.getResponseBody();
			
			// Check for GZip content encoding
			Header contentEncoding = method.getResponseHeader("Content-Encoding");
			if (contentEncoding != null && contentEncoding.getValue().toUpperCase().indexOf("GZIP") >= 0) {
				responseBody = Compressor.decompress(responseBody);
			}		
			LOG.debug("Received XML: " + new String(responseBody));
		} catch (IOException e) {
			LOG.error("Failed to read response body: " + e.getMessage());
			throw new ShoreServiceException(ShoreServiceErrorCode.INVALID_RESPONSE);
		}
		
		method.releaseConnection();
	}
	
	/**
	 * Set uri
	 * @param uri
	 */
	public void setUri(String uri) {
		this.uri = uri;
		this.url = "http://" + host;
		if (port != 80) {
			this.url += ":" + port;
		}
		this.url += this.uri;
	}
	
	/**
	 * Get response
	 * @return
	 */
	public byte[] getResponseBody() {
		return responseBody;
	}
	
	/**
	 * Get URL
	 * @return
	 */
	public String getUrl() {
		return url;
	}

}
