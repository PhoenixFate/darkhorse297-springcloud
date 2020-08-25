package com.example.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import javax.servlet.http.HttpServletRequest;

// @Component
public class LoginFilter extends ZuulFilter {


    /**
     * 过滤器类型
     *
     *     //错误过滤器，过滤器发生错误 会进入错误过滤器
     *     public static final String ERROR_TYPE = "error";
     *
     *     //过滤过滤器
     *     public static final String POST_TYPE = "post";
     *
     *     //前置过滤器
     *     public static final String PRE_TYPE = "pre";
     *
     *     //路由过滤器
     *     public static final String ROUTE_TYPE = "route";
     * @return
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /**
     * 过滤器的顺序、优先级
     * 值越大 优先级越小
     * @return
     */
    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER-1;
    }

    /**
     * 是否应该过滤
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器的逻辑
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        //获得请求上下文
        RequestContext currentContext = RequestContext.getCurrentContext();
        //获得请求request
        HttpServletRequest request = currentContext.getRequest();
        //1.获取请求参数，
        String token = request.getParameter("access-token");
        //2.判断是否存在
        //3.不存在，未登录，则拦截
        if(StringUtils.isBlank(token)){
            //拦截
            //true为放心
            currentContext.setSendZuulResponse(false);
            //返回403
            currentContext.setResponseStatusCode(HttpStatus.SC_FORBIDDEN);
        }
        return null;
    }
}
