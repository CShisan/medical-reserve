package com.cshisan.reserve.common.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cshisan.reserve.common.base.BasePage;

import java.util.Map;

/**
 * @author yuanbai
 * @date 2022/2/23 14:03
 */
public class PageUtil {
    public static IPage<Map<String, Object>> build(BasePage page){
        return new Page<>(page.getCurrent(), page.getSize());
    }
}
