package com.cshisan.reserve;

import cn.binarywang.tools.generator.ChineseIDCardNumberGenerator;
import cn.binarywang.tools.generator.ChineseMobileNumberGenerator;
import cn.binarywang.tools.generator.ChineseNameGenerator;

/**
 * @author CShisan
 * @date 2022-3-11 20:47
 */
public class GeneratorUtil {

    public static String name(){
        return ChineseNameGenerator.getInstance().generate();
    }

    public static String phone(){
        return ChineseMobileNumberGenerator.getInstance().generate();
    }

    public static String idCard(){
        return ChineseIDCardNumberGenerator.getInstance().generate();
    }
}
