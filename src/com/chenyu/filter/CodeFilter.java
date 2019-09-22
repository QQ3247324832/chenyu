package com.chenyu.filter;

import com.chenyu.util.CodeUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author xinYing
 */
@WebFilter(filterName = "CodeFilter", value = "/*")
public class CodeFilter implements Filter {
    private Boolean contextCode = null;
    private String encode = null;
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = contextCode?new CodeUtil((HttpServletRequest) req,encode): (HttpServletRequest) req;
        resp.setContentType("text/html;charset="+encode);

        chain.doFilter(req, resp);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
         contextCode = Boolean.parseBoolean(config.getServletContext().getInitParameter("ContextCode"));
         encode = config.getServletContext().getInitParameter("encode");
    }

}
