package com.cshisan.reserve.common.utils;

import java.util.Collection;

/**
 * @author CShisan
 * @date 2022-3-12 5:19
 */
public class RandomUtil {
    public static <T> int randomIndex(Collection<T> collection) {
        return (int) (Math.random() * (collection.size() - 1));
    }
}
