package com.cshisan.reserve.auth;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.cshisan.reserve.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.lang.NonNull;

import java.util.Objects;

/**
 * @author CShisan
 * @date 2022-2-20 23:27
 */
@Getter
@AllArgsConstructor
public class UserLoginBean {
    public static final String VALUE_NAME = "value";
    private String key;
    private String value;
    private String password;

    public UserLoginBean(@NonNull User user) {
        String phone = user.getPhone();
        Long uid = user.getUid();
        if (Objects.nonNull(phone)) {
            this.value = phone;
            this.key = "phone";
        } else if (Objects.nonNull(uid)) {
            this.value = uid.toString();
            this.key = "uid";
        }
        this.password = user.getPassword();
    }

    public static String toJson(UserLoginBean bean) {
        return JSONObject.toJSONString(bean, SerializerFeature.WriteMapNullValue);
    }

    public static UserLoginBean toBean(String json) {
        return JSONObject.parseObject(json, UserLoginBean.class);
    }

}
