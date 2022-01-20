package com.fu.dao;

import com.fu.entity.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DeptDao {
    public boolean addDept(@Param("t") Dept dept);

    public Dept findById(Long id);

    public List<Dept> findAll();
}
