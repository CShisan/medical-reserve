package com.cshisan.reserve.common.base;

import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

/**
 * 分页基类
 *
 * @author CShisan
 * @date 2022-2-18 23:48
 */
@Getter
public class BasePage {

    private static final int[] SIZES = new int[]{5, 10, 15, 20, 25, 30};

    /**
     * 当前页
     */
    private Integer current;

    /**
     * 每页大小
     */
    private Integer size;

    /**
     * 设置当前页,最小值为1
     *
     * @return 当前页
     */
    public int getCurrent() {
        if (Objects.isNull(current)) {
            return 1;
        }
        return Math.max(current, 1);
    }

    /**
     * 设置每页大小,若值不在SIZES中则直接设置大小为10
     *
     * @return 每页大小
     */
    public int getSize() {
        if (Objects.isNull(size)) {
            return 10;
        }
        return Arrays.stream(SIZES).anyMatch(x -> x == size) ? size : 10;
    }
}
