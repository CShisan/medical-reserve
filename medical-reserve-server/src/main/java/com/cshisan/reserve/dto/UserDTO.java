package com.cshisan.reserve.dto;

import com.cshisan.reserve.common.base.BaseDTO;
import com.cshisan.reserve.entity.Role;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author yuanbai
 * @date 2022/2/21 15:40
 */
@Data
public class UserDTO extends BaseDTO {
    private Long uid;
    private String username;
    private String password;
    private String newPassword;
    private Date birthday;
    private Integer sex;
    private String phone;
    private String avatarUrl;
    private String realName;
    private String idCard;
    private List<Role> roles;

    /**
     * 以下是微信小程序字段
     */
    private String wxCode;

    private String encryptedData;

    private String iv;

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof UserDTO)) return false;
        final UserDTO other = (UserDTO) o;
        if (!other.canEqual((Object) this)) return false;
        if (!super.equals(o)) return false;
        final Object this$uid = this.getUid();
        final Object other$uid = other.getUid();
        if (this$uid == null ? other$uid != null : !this$uid.equals(other$uid)) return false;
        final Object this$username = this.getUsername();
        final Object other$username = other.getUsername();
        if (this$username == null ? other$username != null : !this$username.equals(other$username)) return false;
        final Object this$password = this.getPassword();
        final Object other$password = other.getPassword();
        if (this$password == null ? other$password != null : !this$password.equals(other$password)) return false;
        final Object this$newPassword = this.getNewPassword();
        final Object other$newPassword = other.getNewPassword();
        if (this$newPassword == null ? other$newPassword != null : !this$newPassword.equals(other$newPassword))
            return false;
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
        final Object this$wxCode = this.getWxCode();
        final Object other$wxCode = other.getWxCode();
        if (this$wxCode == null ? other$wxCode != null : !this$wxCode.equals(other$wxCode)) return false;
        final Object this$encryptedData = this.getEncryptedData();
        final Object other$encryptedData = other.getEncryptedData();
        if (this$encryptedData == null ? other$encryptedData != null : !this$encryptedData.equals(other$encryptedData))
            return false;
        final Object this$iv = this.getIv();
        final Object other$iv = other.getIv();
        if (this$iv == null ? other$iv != null : !this$iv.equals(other$iv)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof UserDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = super.hashCode();
        final Object $uid = this.getUid();
        result = result * PRIME + ($uid == null ? 43 : $uid.hashCode());
        final Object $username = this.getUsername();
        result = result * PRIME + ($username == null ? 43 : $username.hashCode());
        final Object $password = this.getPassword();
        result = result * PRIME + ($password == null ? 43 : $password.hashCode());
        final Object $newPassword = this.getNewPassword();
        result = result * PRIME + ($newPassword == null ? 43 : $newPassword.hashCode());
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
        final Object $wxCode = this.getWxCode();
        result = result * PRIME + ($wxCode == null ? 43 : $wxCode.hashCode());
        final Object $encryptedData = this.getEncryptedData();
        result = result * PRIME + ($encryptedData == null ? 43 : $encryptedData.hashCode());
        final Object $iv = this.getIv();
        result = result * PRIME + ($iv == null ? 43 : $iv.hashCode());
        return result;
    }
}
