package com.cshisan.reserve.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.cshisan.reserve.auth.UserLoginEntity;
import com.cshisan.reserve.common.base.BaseEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Date;

/**
 * @author CShisan
 * @date 2022-2-19 13:04
 */
@Component
@Slf4j
public class MybatisMetaObjectHandler implements MetaObjectHandler {
    private UserLoginEntity user;

    @Override
    public void insertFill(MetaObject metaObject) {
        BaseEntity entity = (BaseEntity) metaObject.getOriginalObject();
        if (ObjectUtils.isEmpty(entity.getEditor())) {
            fillEditor(metaObject, getUid());
        }
        fillCurrentTime(metaObject, "createTime", "updateTime");
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        fillCurrentTime(metaObject, "updateTime");
        fillEditor(metaObject, getUid());
    }

    /**
     * 填充当前时间
     *
     * @param metaObject metaObject
     * @param fieldNames fieldNames
     */
    private void fillCurrentTime(MetaObject metaObject, String... fieldNames) {
        Date date = new Date();
        for (String fieldName : fieldNames) {
            this.setFieldValByName(fieldName, date, metaObject);
        }
    }

    /**
     * 填充编辑者ID
     *
     * @param metaObject metaObject
     * @param uid        uid
     */
    private void fillEditor(MetaObject metaObject, Long uid) {
        this.setFieldValByName("editor", uid, metaObject);
    }

    /**
     * 获取编辑者id
     *
     * @return uid
     */
    private Long getUid() {
        try {
            return getUser().getUid();
        } catch (NullPointerException e) {
            log.warn("SpringSecurity上下文对象获取为空，获取当前操作用户失败!\n当前操作编辑者自动设置为最高权限管理员-->[9527]");
            return 9527L;
        }
    }

    /**
     * 获取security上下文用户登录对象
     *
     * @return user
     */
    private UserLoginEntity getUser() {
        if (ObjectUtils.isEmpty(user)) {
            user = (UserLoginEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        return user;
    }
}
