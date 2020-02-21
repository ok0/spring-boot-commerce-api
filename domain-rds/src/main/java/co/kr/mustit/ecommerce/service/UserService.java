package co.kr.mustit.ecommerce.service;

import co.kr.mustit.ecommerce.database.model.User;
import co.kr.mustit.ecommerce.database.model.UserToken;
import co.kr.mustit.ecommerce.repository.UserRepository;
import co.kr.mustit.ecommerce.repository.UserTokenRepository;
import co.kr.mustit.ecommerce.repository.specification.UserSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserTokenRepository userTokenRepository;
    
    public User getUserByEmail(String email, long companyId, int status) {
        return userRepository.findByEmailAndCompanyIdAndStatus(email, companyId, status);
    }

    public User save(User users) {
        return userRepository.save(users);
    }

    public User getUserByUserIdAndComIdAndStatus(String userId, Long companyId, int status) {
        return userRepository.findByUserIdAndCompanyIdAndStatus(userId, companyId, status);
    }

    public User getUserByActivationCode(String token) {
        UserToken userToken = userTokenRepository.findOne(token);

        if (userToken != null) {
            return userRepository.findOne(userToken.getUserId());
        } else {
            return null;
        }
    }
    
    public Page<User> doFilterSearchSortPagingUser(String userId, long companyId, String searchKey, int sortKey, boolean isAscSort, int pSize, int pNumber) {
        return userRepository.findAll(new UserSpecification(userId, companyId, searchKey, sortKey, isAscSort), new PageRequest(pNumber, pSize));
    }
}
