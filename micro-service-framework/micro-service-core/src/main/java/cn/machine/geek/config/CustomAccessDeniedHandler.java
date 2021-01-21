package cn.machine.geek.config;

import cn.machine.geek.common.R;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: MachineGeek
 * @Description: 自定义拒绝访问
 * @Email: 794763733@qq.com
 * @Date: 2021/1/6
 */
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    private ObjectMapper objectMapper;

    public CustomAccessDeniedHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter writer = httpServletResponse.getWriter();
        String json = objectMapper.writeValueAsString(new R(false,403,"无权访问",null));
        writer.print(json);
        writer.flush();
        writer.close();
    }
}
