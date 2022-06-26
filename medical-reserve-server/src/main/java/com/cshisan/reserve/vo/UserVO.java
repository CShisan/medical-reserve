package com.cshisan.reserve.vo;

import com.cshisan.reserve.common.base.BaseVO;
import com.cshisan.reserve.entity.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;


/**
 * @author CShisan
 * @date 2022-2-20 0:05
 *
 * {@link com.cshisan.reserve.entity.User}
 */
@Data
public class UserVO extends BaseVO {
    private Long uid;
    private String username;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date birthday;
    private Integer sex;
    private String phone;
    private String avatarUrl;
    private String realName;
    private String idCard;
    private List<Role> roles;

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof UserVO)) return false;
        final UserVO other = (UserVO) o;
        if (!other.canEqual((Object) this)) return false;
        if (!super.equals(o)) return false;
        final Object this$uid = this.getUid();
        final Object other$uid = other.getUid();
        if (this$uid == null ? other$uid != null : !this$uid.equals(other$uid)) return false;
        final Object this$username = this.getUsername();
        final Object other$username = other.getUsername();
        if (this$username == null ? other$username != null : !this$username.equals(other$username)) return false;
        final Object this$birthday = this.getBirthday();
        final Object other$birthday = other.getBirthday();
        if (this$birthday == null ? other$birthday != null : !this$birthday.equals(other$birthday)) return false;
        final Object this$sex = this.getSex();
        final Object other$sex = other.getSex();
        if (this$sex == null ? other$sex != null : !this$sex.equals(other$sex)) return false;
        final Object this$phone = this.getPhone();
        final Object other$phone = other.getPhone();
        if (this$phone == null ? other$phone != null : !this$phone.equals(other$phone)) return false;
        final Object this$avatarUrl = this.getAvatarUrl();
        final Object other$avatarUrl = other.getAvatarUrl();
        if (this$avatarUrl == null ? other$avatarUrl != null : !this$avatarUrl.equals(other$avatarUrl)) return false;
        final Object this$realName = this.getRealName();
        final Object other$realName = other.getRealName();
        if (this$realName == null ? other$realName != null : !this$realName.equals(other$realName)) return false;
        final Object this$idCard = this.getIdCard();
        final Object other$idCard = other.getIdCard();
        if (this$idCard == null ? other$idCard != null : !this$idCard.equals(other$idCard)) return false;
        final Object this$roles = this.getRoles();
        final Object other$roles = other.getRoles();
        if (this$roles == null ? other$roles != null : !this$roles.equals(other$roles)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof UserVO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = super.hashCode();
        final Object $uid = this.getUid();
        result = result * PRIME + ($uid == null ? 43 : $uid.hashCode());
        final Object $username = this.getUsername();
        result = result * PRIME + ($username == null ? 43 : $username.hashCode());
        final Object $birthday = this.getBirthday();
        result = result * PRIME + ($birthday == null ? 43 : $birthday.hashCode());
        final Object $sex = this.getSex();
        result = result * PRIME + ($sex == null ? 43 : $sex.hashCode());
        final Object $phone = this.getPhone();
        result = result * PRIME + ($phone == null ? 43 : $phone.hashCode());
        final Object $avatarUrl = this.getAvatarUrl();
        result = result * PRIME + ($avatarUrl == null ? 43 : $avatarUrl.hashCode());
        final Object $realName = this.getRealName();
        result = result * PRIME + ($realName == null ? 43 : $realName.hashCode());
        final Object $idCard = this.getIdCard();
        result = result * PRIME + ($idCard == null ? 43 : $idCard.hashCode());
        final Object $roles = this.getRoles();
        result = result * PRIME + ($roles == null ? 43 : $roles.hashCode());
        return result;
    }
}
