package com.study.springboot.util.excel;

import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.handler.WriteHandler;
import org.springframework.cglib.core.Converter;

import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.CLASS)
public @interface ResponseExcel {
    String name() default "";
    ExcelTypeEnum suffix() default ExcelTypeEnum.XLS;
    String password() default "";
    String[] sheet() default "";
    boolean inMomery() default false;
    String template() default "";
    String[] include() default {};
    String[] exclude() default {};
    Class<? extends WriteHandler>[] writeHandler() default {};
    Class<? extends Converter>[] converter() default {};
}
