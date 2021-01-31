package cn.machine.geek.handler;

import cn.machine.geek.common.R;
import cn.machine.geek.service.RabbitMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author: MachineGeek
 * @Description: 全局异常处理
 * @Email: 794763733@qq.com
 * @Date: 2021/1/31
 */
@ConditionalOnClass(Source.class)
@ControllerAdvice
public class GlobalExceptionHandler {
    @Autowired
    private RabbitMessageProvider rabbitMessageProvider;
    @Value("${spring.application.name:UNKNOWN}")
    private String applicationName;

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public R exceptionHandler(HttpServletRequest httpServletRequest, Exception e){
        // 构建异常信息
        Map<String,Object> map = new HashMap<>();
        map.put("service",applicationName);
        try {
            map.put("host", Inet4Address.getLocalHost().getAddress());
        } catch (UnknownHostException unknownHostException) {
            unknownHostException.printStackTrace();
        }
        map.put("uri",httpServletRequest.getRequestURI());
        map.put("ip",getIpAddr(httpServletRequest));
        String method = httpServletRequest.getMethod();
        map.put("method",method);
        if(method.equals("GET") || method.equals("DELETE")){
            map.put("parameter",getParameter(httpServletRequest));
        }else{
            map.put("parameter",getBody(httpServletRequest));
        }
        map.put("exceptionClass",e.getClass().getName());
        map.put("exceptionMessage",e.getMessage());
        map.put("createTime",LocalDateTime.now());
        rabbitMessageProvider.send(map);
        // 返回异常信息
        return R.fail("服务器繁忙");
    }

    /**
     * @Author: MachineGeek
     * @Description: 获取用户的真实IP地址
     * @Date: 11:09 上午
     * @param request
     * @Return: java.lang.String
     */
    private String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x - forwarded - for");
        if (ip == null || ip.length() == 0 ||"unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("Proxy - Client - IP");
        }
        if (ip == null || ip.length() == 0 ||"unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("WL - Proxy - Client - IP");
        }
        if (ip == null || ip.length() == 0 ||"unknown".equalsIgnoreCase(ip)){
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * @Author: MachineGeek
     * @Description: 获取HTTP Body
     * @Date: 11:11 上午
     * @param request
     * @Return: java.lang.String
     */
    private String getBody(HttpServletRequest request) {
        BufferedReader br = null;
        String str, wholeStr = "";
        try {
            br = request.getReader();
            while((str = br.readLine()) != null){
                wholeStr += str;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wholeStr;
    }

    /**
     * @Author: MachineGeek
     * @Description: 获取请求参数
     * @Date: 11:14 上午
     * @param httpServletRequest
     * @Return: java.lang.String
     */
    private String getParameter(HttpServletRequest httpServletRequest){
        String parameter = "?";
        Set<Map.Entry<String, String[]>> entries = httpServletRequest.getParameterMap().entrySet();
        for (Map.Entry<String,String[]> entry : entries){
            parameter = parameter + entry.getKey() + "=" + Arrays.toString(entry.getValue()) + "&";
        }
        return parameter;
    }
}
