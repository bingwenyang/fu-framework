package com.fu.service.impl;

import com.fu.dao.DeptDao;
import com.fu.entity.Dept;
import com.fu.service.DeptService;
import io.seata.core.context.RootContext;
import io.seata.core.exception.TransactionException;
import io.seata.spring.annotation.GlobalTransactional;
import io.seata.tm.api.GlobalTransaction;
import io.seata.tm.api.GlobalTransactionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
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
    public List<Dept> add(Dept dept) throws TransactionException {
        List<Dept> deptList = new ArrayList<>();
        try {
            // seata-second服务也执行数据库插入
            Dept dept2 = new Dept();
            dept2.setDeptno(9999L);
            dept2.setDname("二号");
            dept2.setDb_source("clouddb01");
            ResponseEntity<Dept> entity = restTemplate.postForEntity("http://seata-second/v1/seata-second//dept/add", dept2, Dept.class);
            Dept result = entity.getBody();
            // 此处插入失败，回滚seata-secondf服务
            dao.addDept(dept);
            deptList.add(dept);
            deptList.add(result);
        } catch (Exception e) {
            GlobalTransaction reload = GlobalTransactionContext.reload(RootContext.getXID());
            // 全局回滚
            reload.rollback();
        }

        return deptList;
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
