package com.springcloud.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
public class AuthFilter extends ZuulFilter {
    // 被路由前调用
    @Override
    public String filterType() {
        log.info("----路由前调用----");
        return "路由前调用";
    }
    //filter执行顺序，通过数字指定 ,优先级为0，数字越大，优先级越低
    @Override
    public int filterOrder() {
        return 0;
    }
    // 是否执行该过滤器，此处为true，说明需要过滤
    @Override
    public boolean shouldFilter() {
        return true;
    }
    /**
     *  filter需要执行的具体操作
     *
     * 例如：本filter实际执行的逻辑 是验证所有的访问请求中，是否包含安全信息auth
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        // String auth = servlet.getParameter("auth");
        //第一次请求,经过内部负责分发的请求,应该是options方法,所以第一次请求肯定不是
        if (request.getMethod().equals("OPTIONS")){
            return null;
        }
        //当前单词在url中出现位置的下标大于0
        if (request.getRequestURI().indexOf("login")>0){
            return null;
        }
        //得到头信息
        String header = request.getHeader("Authorization");
        if (!StringUtils.isEmpty(header)){
            if (header.startsWith("Bearer ")){
                String token = header.substring(7);
                try {
//                    Claims claims = jwtUtil.parseJWT(token);
//                    String roles = (String) claims.get("roles");
//                    if (roles.equals("admin")){
//                        currentContext.addZuulRequestHeader("Authorization",token);
//                        return null;
//                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    //终止放行
                    currentContext.setSendZuulResponse(false);
                }
            }
        }
        currentContext.setSendZuulResponse(false);
        currentContext.setResponseStatusCode(403);
        currentContext.setResponseBody("权限不足");
        currentContext.getResponse().setContentType("text/html;charset=utf-8");
        System.out.println("经过的是后台过滤器");
        return null;

    }
}
