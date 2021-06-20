package cn.machine.geek.config;

import cn.machine.geek.common.R;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: MachineGeek
 * @Description: 未登录或身份过期处理
 * @Email: 794763733@qq.com
 * @Date: 2021/1/6
 */
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private ObjectMapper objectMapper;

    public CustomAuthenticationEntryPoint(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * @param httpServletRequest
     * @param httpServletResponse
     * @param e
     * @Author: MachineGeek
     * @Description: 未登录或身份过期处理
     * @Date: 2021/1/6
     * @Return: void
     */
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter writer = httpServletResponse.getWriter();
        String json = objectMapper.writeValueAsString(new R(false, 401, "身份过期或未登陆", null));
        writer.print(json);
        writer.flush();
        writer.close();
    }
}
