/**
 * 
 */
package com.gsoft.esb.weixin.security;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import com.gsoft.framework.util.StringUtils;

/**
 * 微信OAuth授权filter
 * @author zhyi_12
 */
@Component("wxOAuth")
public class WxOAuthFilter extends AuthenticatingFilter{
	
	private static Logger logger = LoggerFactory.getLogger(WxOAuthFilter.class);
	
//	@Autowired(required=false)
//	private WxSubscriptionFinder wxSubscriptionFinder;
//	
//	@Autowired
//	private MessagingTemplate messageTemplate;
	
	@Value("error.jsp")
	private String failureUrl;

	@Override
	protected AuthenticationToken createToken(ServletRequest request,
			ServletResponse response) throws Exception {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		AntPathMatcher matcher = new AntPathMatcher();
		
	    String code = httpRequest.getParameter("code");//code作为换取access_token的票据，每次用户授权带上的code将不一样，code只能使用一次，5分钟未被使用自动过期。
	    Map<String,String> params = matcher.extractUriTemplateVariables(httpRequest.getContextPath()+"/weixin/{name}/login.html", httpRequest.getRequestURI());
	    
		return new WxOAuthToken(code,params.get("name"));
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request,
			ServletResponse response) throws Exception {
		//重定位
		HttpServletRequest httpRequest = (HttpServletRequest)request; 
		
		String code = request.getParameter("code");//微信重定向返回的参数
		if(StringUtils.isNotEmpty(code)){
			//执行登录操作
			this.executeLogin(httpRequest, response);
		}else{
			//执行重定向操作
			String redirect_uri = httpRequest.getRequestURL().toString();
			
			String appid = "wxfdb3ad82dd67bbb3";//response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect
			
			StringBuilder oauthUrl = new StringBuilder();
			
			oauthUrl.append("https://open.weixin.qq.com/connect/oauth2/authorize?")
				.append("appid="+appid)
				.append("&redirect_uri="+URLEncoder.encode(redirect_uri, "UTF-8"))
				.append("&response_type=code&scope=snsapi_base&state=123465#wechat_redirect");
			
			logger.info(oauthUrl.toString());
			
			WebUtils.issueRedirect(request, response, oauthUrl.toString());
		}
		return false;
	}

	/**
     * Returns <code>false</code> to always force authentication (user is never considered authenticated by this filter).
     * 
     * @param request the incoming request
     * @param response the outgoing response
     * @param mappedValue the filter-specific config value mapped to this filter in the URL rules mappings.
     * @return <code>false</code>
     */
	@Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return false;
    }
    
    /**
     * If login has been successful, redirect user to the original protected url.
     * 
     * @param token the token representing the current authentication
     * @param subject the current authenticated subjet
     * @param request the incoming request
     * @param response the outgoing response
     * @throws Exception if there is an error processing the request.
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
                                     ServletResponse response) throws Exception {
        issueSuccessRedirect(request, response);
        return false;
    }
    
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException ae, ServletRequest request,
                                     ServletResponse response) {
        // is user authenticated or in remember me mode ?
        Subject subject = getSubject(request, response);
        if (subject.isAuthenticated() || subject.isRemembered()) {
            try {
                issueSuccessRedirect(request, response);
            } catch (Exception e) {
                logger.error("Cannot redirect to the default success url", e);
            }
        } else {
            try {
                WebUtils.issueRedirect(request, response, failureUrl);
            } catch (IOException e) {
                logger.error("Cannot redirect to failure url : {}", failureUrl, e);
            }
        }
        return false;
    }

    public void setFailureUrl(String failureUrl) {
        this.failureUrl = failureUrl;
    }
}
