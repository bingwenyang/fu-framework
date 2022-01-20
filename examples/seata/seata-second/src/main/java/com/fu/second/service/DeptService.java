package com.fu.second.service;

import com.fu.second.entity.Dept;
import io.seata.core.exception.TransactionException;

import java.util.List;

public interface DeptService {
    public Dept add(Dept dept) throws TransactionException;

    public Dept get(Long id);

    public List<Dept> list();
}
