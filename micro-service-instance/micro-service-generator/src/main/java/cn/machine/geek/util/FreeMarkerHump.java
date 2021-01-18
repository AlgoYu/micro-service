package cn.machine.geek.util;

import com.google.common.base.CaseFormat;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

import java.util.List;
import java.util.regex.Pattern;

/**
 * @Author: MachineGeek
 * @Description: FreeMarker驼峰函数
 * @Email: 794763733@qq.com
 * @Date: 2020/11/9
 */
public class FreeMarkerHump implements TemplateMethodModelEx {
    // 正则表达式
    private final Pattern linePattern = Pattern.compile("_(\\w)");

    @Override
    public Object exec(List list) throws TemplateModelException {
        String str = list.get(0).toString();
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL,str);
    }
}