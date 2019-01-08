package com.abby.elema.annotation;

import com.abby.elema.model.enums.OperationEnum;

import java.lang.annotation.*;

/**
 * @author: Abby
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SaveLog {
    OperationEnum type() default OperationEnum.UNKNOWN;
}
