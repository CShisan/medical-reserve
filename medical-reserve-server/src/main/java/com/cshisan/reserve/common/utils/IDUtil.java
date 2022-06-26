package com.cshisan.reserve.common.utils;

import cn.hutool.core.util.RandomUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * @author yuanbai
 * @date 2022/2/21 17:05
 */
public class IDUtil {

    private static String create() {
        String time = String.valueOf(System.currentTimeMillis());
        StringBuilder sb = new StringBuilder(time);
        sb.append(RandomUtil.randomNumbers(6));
        return sb.substring(7);
    }

    public static Long patientId() {
        return Long.parseLong(IdEnum.PATIENT.getPrefix() + create());
    }

    public static Long doctorId() {
        return Long.parseLong(IdEnum.DOCTOR.getPrefix() + create());
    }

    public static Long businessId() {
        return Long.parseLong(IdEnum.BUSINESS.getPrefix() + create());
    }


    @Getter
    @AllArgsConstructor
    public enum IdEnum {
        /**
         * 角色对应前缀
         */
        ADMIN(1, "Admin", "8"),
        ADMIN_PLATFORM(2, "AdminPlatform", "8"),
        PATIENT(3, "Patient", "5"),
        DOCTOR(4, "Doctor", "6"),
        BUSINESS(1001, "Business", "7");

        private final long code;
        private final String key;
        private final String prefix;

        public static String toPrefix(long code) {
            for (IdEnum value : values()) {
                if (value.getCode() == code) {
                    return value.getPrefix();
                }
            }
            return null;
        }

        public static String toPrefix(String key) {
            for (IdEnum value : values()) {
                if (Objects.equals(value.getKey(), key)) {
                    return value.getPrefix();
                }
            }
            return null;
        }

        public static long toCode(String key) {
            for (IdEnum value : values()) {
                if (Objects.equals(value.getKey(), key)) {
                    return value.getCode();
                }
            }
            return 99999;
        }
    }
}
