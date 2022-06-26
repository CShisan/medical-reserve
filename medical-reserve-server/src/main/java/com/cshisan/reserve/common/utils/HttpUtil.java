package com.cshisan.reserve.common.utils;

import com.alibaba.fastjson.JSON;
import com.cshisan.reserve.common.base.Result;
import com.cshisan.reserve.common.enums.CodeEnum;
import com.cshisan.reserve.common.exception.CustomException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author CShisan
 * @date 2022-2-20 17:52
 */
public class HttpUtil {
    /**
     * 获取当前请求的HttpServletRequest实例
     * RequestContextHolder.getRequestAttributes()在非控制层获取需要开启RequestContextListener,否则为空
     *
     * @return HttpServletRequest
     */
    public static HttpServletRequest getHttpServletRequest() {
        try {
            return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取头部信息
     *
     * @see HttpUtil#getHeaders
     */
    public static Map<String, String> getHeaders() {
        HttpServletRequest request = getHttpServletRequest();
        return null == request ? null : getHeaders(request);
    }

    /**
     * 获取请求头
     *
     * @param request request
     * @return map
     */
    public static Map<String, String> getHeaders(HttpServletRequest request) {
        Map<String, String> map = new LinkedHashMap<>();
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }

    /**
     * 获取Body
     *
     * @param request request
     * @return String
     */
    public static String getBody(HttpServletRequest request){
        StringBuilder sb = new StringBuilder();
        try {
            ServletInputStream is = request.getInputStream();
            InputStreamReader isReader = new InputStreamReader(is, StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isReader);
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            throw new CustomException(CodeEnum.FAIL);
        }
        return sb.toString();
    }

    public static void writeResponse(HttpServletResponse response, Result<?> result) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write(JSON.toJSONString(result));
    }
}
