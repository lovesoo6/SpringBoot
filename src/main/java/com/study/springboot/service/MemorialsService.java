package com.study.springboot.service;


import com.study.springboot.entity.Memorials;

import java.util.List;

public interface MemorialsService  {
    List<Memorials> getAllMemorialsDigestByMinister(String empID, Integer pageNum);

    List<Memorials> getAllMemorialsDigest(Integer pageNum);

    Memorials getMemorialsDetailById(Integer memorialsId);

    Integer insertMemorials(Integer empID, String memorialsTitle, String memorialsContentDigest, String qizouTime,String imageName);

    Integer updateMemorialsStatus(Integer memorialsId);

    Integer updateMemorialsFeedBack(Integer memorialsId, String feedbackContent,String feedTime);

    int getSumMemorials();

    int deleteMemorial(Integer memorialsId);

    List<Memorials> getAllMemorialsDetailBySearch(String mTitle, String eId,String[] mStatus,String empId);


    Integer deleteMemorialByIds(String[] str);
}
