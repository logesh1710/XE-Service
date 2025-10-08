package io.gomobi.quartz.domain.query;

import io.gomobi.quartz.common.util.StringUtil;
import io.gomobi.quartz.domain.entity.PrimaryQueryField;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class EntityUtil {

    private EntityUtil() throws IllegalAccessException {
        throw new IllegalAccessException("Cannot instantiate Utility class");
    }


    public static String[] extractSelectAttributes(Class<?> classType){
        List<String> selectAttributes = new ArrayList<>();

        for (Field field: classType.getDeclaredFields()){
            boolean annotationPresent = field.isAnnotationPresent(PrimaryQueryField.class);

            if (annotationPresent)
                selectAttributes.add(StringUtil.camelToUpperSnake(field.getName()));
        }

        return selectAttributes.toArray(String[]::new);
    }


}
