package com.integ.core.authentication.filters;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Author: Manan
 * Date: 22-02-2016 20:10
 */

public class CORSFilter implements Filter {

    private static final Logger LOG= Logger.getLogger(CORSFilter.class);

    private FilterConfig filterConfig = null;

    public CORSFilter() {
        BasicConfigurator.configure();
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception java.io.IOException if an input/output error occurs
     * @exception javax.servlet.ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest)request;
        if(req.getMethod().equals("OPTIONS")) {
            LOG.debug("option request");
            ((HttpServletResponse)response).setHeader("Access-Control-Allow-Origin", "*");
            ((HttpServletResponse)response).setHeader("Access-Control-Allow-Headers", "Content-type, jwtoken");
            ((HttpServletResponse)response).setHeader("Access-Control-Allow-Methods", "PUT, GET, DELETE, POST, HEAD");
        } else {
            LOG.debug("post request");
            ((HttpServletResponse)response).setHeader("Access-Control-Allow-Origin", "*");
            chain.doFilter(request, response);
        }
    }

    /**
     * Return the filter configuration object for this filter.
     * @return
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    @Override
    public void destroy() {
    }

    /**
     * Init method for this filter
     * @param filterConfig
     */
    @Override
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("CORSFilter()");
        }
        StringBuffer sb = new StringBuffer("CORSFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

}
