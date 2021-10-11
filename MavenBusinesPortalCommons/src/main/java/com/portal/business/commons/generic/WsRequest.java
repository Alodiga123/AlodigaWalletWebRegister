package com.portal.business.commons.generic;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;



public class WsRequest implements Serializable {

    private Integer limit;
    private Integer first;
    private Object param;
    private Map<String, Object> params;
    private String url;
    private HttpMethod method;

    public WsRequest() {
        super();
    }

    public WsRequest(Object param) {
        super();
        this.param = param;
    }

    public WsRequest(Integer limit, Integer first) {
        super();
        this.limit = limit;
        this.first = first;
    }

    public WsRequest(Integer limit, Integer first, Object param) {
        super();
        this.limit = limit;
        this.first = first;
        this.param = param;
    }

    public WsRequest(Integer limit, Integer first, Map<String, Object> params) {
        super();
        this.limit = limit;
        this.first = first;
        this.params = params;
    }

    public WsRequest(Integer limit, Integer first, Object param,
            Map<String, Object> params) {
        super();
        this.limit = limit;
        this.first = first;
        this.param = param;
        this.params = params;

    }


    public WsRequest(Map<String, Object> params) {
        super();
        this.params = params;
    }



    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getFirst() {
        return first;
    }

    public void setFirst(Integer first) {
        this.first = first;
    }

    public Object getParam() {
        return param;
    }

    public void setParam(Object param) {
        this.param = param;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }




    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

  

    public HttpMethod getMethod() {
        return method;
    }

    public void setMethod(HttpMethod method) {
        this.method = method;
    }
}
