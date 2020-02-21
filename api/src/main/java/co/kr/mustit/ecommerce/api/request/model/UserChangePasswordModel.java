package co.kr.mustit.ecommerce.api.request.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserChangePasswordModel {
    public String userId;
    public String oldPassword;
    public String newPassword;
}
