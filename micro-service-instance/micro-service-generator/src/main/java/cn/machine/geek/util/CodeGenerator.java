package cn.machine.geek.util;

import cn.machine.geek.entity.DatabaseTableColumn;
import cn.machine.geek.mapper.DatabaseMapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.google.common.base.CaseFormat;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Author: MachineGeek
 * @Description: 代码生成器
 * @Date: 2020/11/05
 */
@Component
public class CodeGenerator{
    // FreeMarker
    private Configuration configuration;
    // 数据库映射类
    private DatabaseMapper databaseMapper;
    // 生成路径
    @Value("${generate-path:./}")
    private String generatePath;

    @Autowired
    public CodeGenerator(Configuration configuration, DatabaseMapper databaseMapper) {
        this.configuration = configuration;
        this.databaseMapper = databaseMapper;
        // 加入驼峰函数
        this.configuration.setSharedVariable("toHump",new FreeMarkerHump());
    }

    /**
    * @Author: MachineGeek
    * @Description: 生成代码
    * @Date: 12:58 下午
     * @param tableName
     * @param moduleName
    * @Return: java.lang.String
    */
    @SneakyThrows
    public String generate(String tableName, String packageName, String moduleName){
        // 获取数据
        List<DatabaseTableColumn> databaseTableColumns = getColumnsByTableName(tableName);
        if(databaseTableColumns.size() > 0){
            Map<String,Object> data = new HashMap<>();
            String className = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL,tableName);
            String instanceName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL,tableName);
            // 设置数据
            data.put("data",databaseTableColumns);
            data.put("tableName",tableName);
            data.put("packageName",packageName);
            data.put("className", className);
            data.put("instanceName", instanceName);
            data.put("moduleName",moduleName);
            data.put("date", LocalDate.now());
            data.put("id", IdWorker.getIdStr());
            // 创建目录
            String randomName = UUID.randomUUID().toString();
            String directory = generatePath + randomName + "/";
            File file = new File(directory);
            if(file.mkdirs()){
                generateFile("sql.ftl",data,directory + className +".sql");
                generateFile("entity.ftl",data,directory + className +".java");
                generateFile("xml.ftl",data,directory + className +"Mapper.xml");
                generateFile("mapper.ftl",data,directory + className +"Mapper.java");
                generateFile("service.ftl",data,directory + className +"Service.java");
                generateFile("serviceimpl.ftl",data,directory + className +"ServiceImpl.java");
                generateFile("controller.ftl",data,directory + className +"Controller.java");
                generateFile("api.ftl",data,directory + className +"Api.js");
                generateFile("vue.ftl",data,directory + className +".vue");
                // 打包
                String zipPath = generatePath + randomName + ".zip";
                ZipUtil.compressionToZip(directory,zipPath);
                return zipPath;
            }
        }
        return null;
    }

    /**
    * @Author: MachineGeek
    * @Description: 根据表名获取表字段
    * @Date: 1:26 下午
     * @param tableName
    * @Return: java.util.List<cn.machine.geek.entity.DatabaseTableColumn>
    */
    private List<DatabaseTableColumn> getColumnsByTableName(String tableName){
        return databaseMapper.listColumnByTableName(tableName);
    }

    /**
    * @Author: MachineGeek
    * @Description: 根据模板生成文件
    * @Date: 2021/1/7
     * @param templateName
     * @param data
     * @param filePath
    * @Return: void
    */
    private void generateFile(String templateName,Map<String,Object> data,String filePath){
        try {
            Template template = configuration.getTemplate(templateName);
            File file = new File(filePath);
            if(file.createNewFile()){
                template.process(data,new BufferedWriter(new FileWriter(filePath)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}