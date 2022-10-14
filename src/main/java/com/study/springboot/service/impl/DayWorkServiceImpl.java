package com.study.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.springboot.entity.DayWork;
import com.study.springboot.mapper.DayWorkMapper;
import com.study.springboot.service.DayWorkService;
import org.springframework.stereotype.Service;


@Service
public class DayWorkServiceImpl extends ServiceImpl<DayWorkMapper,DayWork> implements DayWorkService {

}
