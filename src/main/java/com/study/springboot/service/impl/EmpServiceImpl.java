package com.study.springboot.service.impl;


import com.study.springboot.entity.Emp;
import com.study.springboot.entity.EmpExample;
import com.study.springboot.mapper.EmpMapper;
import com.study.springboot.service.EmpService;
import com.study.springboot.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Override
    public Emp getEmpByLogin(String loginAccount, String loginPassword) {

        // 1、密码加密
        String encodedLoginPassword = MD5Util.encode(loginPassword);
        // 2、通过 QBC 查询方式封装查询条件
        EmpExample example = new EmpExample();

        EmpExample.Criteria criteria = example.createCriteria();
        criteria.andLoginAccountEqualTo(loginAccount).andLoginPasswordEqualTo(encodedLoginPassword);

        List<Emp> empList = empMapper.selectByExample(example);

        if (empList != null && empList.size() > 0) {

            // 3、返回查询结果
            return empList.get(0);
        }

        return null;
    }

    @Override
    public int getSumAllEmp() {
        // 2、通过 QBC 查询方式封装查询条件
        EmpExample example = new EmpExample();

        EmpExample.Criteria criteria = example.createCriteria();
        int i = empMapper.countByExample(example);
        return i;
    }

    @Override
    public List<Emp> getAllEmp() {
        // 2、通过 QBC 查询方式封装查询条件
        EmpExample example = new EmpExample();

        List<Emp> empList = empMapper.selectByExample(example);

        return empList;
    }
}
