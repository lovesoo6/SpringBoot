package com.study.springboot.service;

import com.study.springboot.entity.Emp;
import org.springframework.stereotype.Service;

import java.util.List;


public interface EmpService {
    Emp getEmpByLogin(String loginAccount, String loginPassword);

    int getSumAllEmp();

    List<Emp> getAllEmp();
}
