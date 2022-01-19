package com.fu.second.service.impl;

import com.fu.second.dao.DeptDao;
import com.fu.second.entity.Dept;
import com.fu.second.service.DeptService;
import io.seata.core.context.RootContext;
import io.seata.core.exception.TransactionException;
import io.seata.spring.annotation.GlobalTransactional;
import io.seata.tm.api.GlobalTransaction;
import io.seata.tm.api.GlobalTransactionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptDao dao;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 测试seata
     *
     * @param dept
     * @return
     * @throws TransactionException
     */
    @Override
    @GlobalTransactional(name = "add-dept", rollbackFor = Exception.class)
    public Dept add(Dept dept) throws TransactionException {
        try {
            dao.addDept(dept);
        } catch (Exception e) {
            GlobalTransaction reload = GlobalTransactionContext.reload(RootContext.getXID());
            // 全局回滚
            reload.rollback();
        }

        return dept;
    }

    @Override
    public Dept get(Long id) {
        return dao.findById(id);
    }

    @Override
    public List<Dept> list() {
        return dao.findAll();
    }

}
