package com.study.springboot.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.*;

@Data
@Getter
@ToString
@Setter
public class DayWork {
    @ExcelProperty("顺序id")
    private Integer dayId;
    @ExcelProperty("填报时间")
    private String dayData;
    @ExcelProperty("日志描述")
    private String dayMiaoshu;

    public DayWork() {
    }

    public DayWork(Integer dayId, String dayData, String dayMiaoshu) {
        this.dayId = dayId;
        this.dayData = dayData;
        this.dayMiaoshu = dayMiaoshu;
    }
}
