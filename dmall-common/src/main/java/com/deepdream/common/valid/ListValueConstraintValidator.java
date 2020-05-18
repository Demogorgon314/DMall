package com.deepdream.common.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

/**
 * @author wangkai
 */
public class ListValueConstraintValidator implements ConstraintValidator<ListValue,Integer> {
    private Set<Integer> set = new HashSet<>();
    @Override
    public void initialize(ListValue constraintAnnotation) {
        int[] values = constraintAnnotation.value();
        for( int val : values){
            set.add(val);
        }
    }

    /**
     *
     * @param integer 需要校验的值
     * @param context
     * @return
     */
    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext context) {
        return set.contains(integer);
    }
}
