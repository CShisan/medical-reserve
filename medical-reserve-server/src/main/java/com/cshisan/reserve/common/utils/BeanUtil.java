package com.cshisan.reserve.common.utils;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import org.springframework.beans.BeanUtils;

/**
 * @author CShisan
 * @date 2022-2-19 19:09
 */
public class BeanUtil {
    public static <T, R> R convert(T t, R r) {
        BeanUtils.copyProperties(t, r);
        return r;
    }



    public static boolean andIsNull(Object... objs) {
        return objs.length == isNull(objs);
    }

    public static boolean orIsNull(Object... objs) {
        return isNull(objs) > 0;
    }

    public static boolean andNonNull(Object... objs) {
        return objs.length == isNonNull(objs);
    }

    public static boolean orNonNull(Object... objs) {
        return isNonNull(objs) > 0;
    }

    public static boolean andIsEmpty(Object... objs) {
        return objs.length == isEmpty(objs);
    }

    public static boolean orIsEmpty(Object... objs) {
        return isEmpty(objs) > 0;
    }

    public static boolean andNonEmpty(Object... objs) {
        return objs.length == isNonEmpty(objs);
    }

    public static boolean orNonEmpty(Object... objs) {
        return isNonEmpty(objs) > 0;
    }

    private static int isNull(Object... objs) {
        int count = 0;
        for (Object obj : objs) {
            if (ObjectUtils.isNull(obj)) {
                count++;
            }
        }
        return count;
    }

    private static int isNonNull(Object... objs) {
        int count = 0;
        for (Object obj : objs) {
            if (ObjectUtils.isNotNull(obj)) {
                count++;
            }
        }
        return count;
    }

    private static int isEmpty(Object... objs){
        int count = 0;
        for (Object obj : objs) {
            if (ObjectUtils.isEmpty(obj)) {
                count++;
            }
        }
        return count;
    }

    private static int isNonEmpty(Object... objs){
        int count = 0;
        for (Object obj : objs) {
            if (ObjectUtils.isNotEmpty(obj)) {
                count++;
            }
        }
        return count;
    }
}
