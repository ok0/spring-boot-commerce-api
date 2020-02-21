package co.kr.mustit.ecommerce.auth;

import co.kr.mustit.ecommerce.database.model.User;
import co.kr.mustit.ecommerce.repository.RoleRepository;
import co.kr.mustit.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 *
 */
@Service
public class AuthUserFactoryImpl implements AuthUserFactory {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public AuthUser createAuthUser(User user) {
        return new AuthUser(
                user.getUserId(),
                user.getEmail(),
                user.getPasswordHash(),
                getUserRoleString(user.getRoleId()),
                user.getFirstName(),
                user.getLastName(),
                user.getStatus() == Constant.STATUS.ACTIVE_STATUS.getValue()
        );
    }

    private String getUserRoleString (int roleId) {
        try {
            return roleRepository
                    .findById(roleId)
                    .get()
                    .getName();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
