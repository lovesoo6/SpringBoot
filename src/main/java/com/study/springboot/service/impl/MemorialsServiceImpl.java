package com.study.springboot.service.impl;

import com.study.springboot.entity.Memorials;
import com.study.springboot.entity.MemorialsExample;
import com.study.springboot.mapper.EmpMapper;
import com.study.springboot.mapper.MemorialsMapper;
import com.study.springboot.service.MemorialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MemorialsServiceImpl implements MemorialsService {
    @Autowired
    private MemorialsMapper memorialsMapper;
    @Autowired
    private EmpMapper empMapper;
    @Override
    public List<Memorials> getAllMemorialsDigestByMinister(String empID, Integer pageNum) {
        List<Memorials> memorialsList=memorialsMapper.getAllMemorialsById(empID);
        return memorialsList;
    }



    @Override
    public List<Memorials> getAllMemorialsDigest(Integer pageNum) {

        List<Memorials> memorialsList=memorialsMapper.getAllMemorials();

        if (memorialsList != null && memorialsList.size() > 0) {
            return memorialsList;
        }
        return null;
    }

    @Override
    public Memorials getMemorialsDetailById(Integer memorialsId) {
        Memorials memorials = memorialsMapper.getMemorialsDetailById(memorialsId);
        return memorials;
    }

    @Override
    public Integer insertMemorials(Integer empID, String memorialsTitle, String memorialsContentDigest, String qizouTime ,String imageName) {
        int i = memorialsMapper.insertMemorials(empID,memorialsTitle,memorialsContentDigest,qizouTime,imageName);
        return i;
    }

    @Override
    public Integer updateMemorialsStatus(Integer memorialsId) {
        int i = memorialsMapper.updateMemorialsStatusToRead(memorialsId);
        return i;
    }

    @Override
    public Integer updateMemorialsFeedBack(Integer memorialsId, String feedbackContent,String feedTime) {
        int i = memorialsMapper.updateMemorialsFeedBack(memorialsId,feedbackContent,feedTime);
        return i;
    }

    @Override
    public int getSumMemorials() {
        // 2、通过 QBC 查询方式封装查询条件
        MemorialsExample example = new MemorialsExample();

        int i = memorialsMapper.countByExample(example);
        return i;
    }

    @Override
    public int deleteMemorial(Integer memorialsId) {

        int i = memorialsMapper.deleteByPrimaryKey(memorialsId);

        return i;
    }

    @Override
    public List<Memorials> getAllMemorialsDetailBySearch(String mTitle, String eId, String[] mStatus, String empId) {
        List<Memorials> memorialsList = memorialsMapper.getAllMemorialsDetailBySearch(mTitle,eId,mStatus,empId);
        if (memorialsList != null && memorialsList.size() > 0) {
            return memorialsList;
        }
        return null;
    }

    @Override
    public Integer deleteMemorialByIds(String[] str) {
        Integer i = memorialsMapper.deleteMemorialByIds(str);
        return i;
    }
}
