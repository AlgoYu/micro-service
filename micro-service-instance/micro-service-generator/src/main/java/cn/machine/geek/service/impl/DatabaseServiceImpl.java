package cn.machine.geek.service.impl;

import cn.machine.geek.entity.DatabaseTable;
import cn.machine.geek.mapper.DatabaseMapper;
import cn.machine.geek.service.DatabaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: MachineGeek
 * @Description: 数据库服务实现类
 * @Date: 2020/11/6
 */
@Service
public class DatabaseServiceImpl implements DatabaseService {
    @Autowired
    private DatabaseMapper databaseMapper;

    @Override
    public List<String> listDatabase() {
        return databaseMapper.listDatabase();
    }

    @Override
    public IPage<DatabaseTable> paging(int page, int size, String keyWord) {
        return databaseMapper.pagingTableByDatabaseName(new Page<>(page,size),databaseMapper.getCurrentDatabase(),keyWord);
    }
}
