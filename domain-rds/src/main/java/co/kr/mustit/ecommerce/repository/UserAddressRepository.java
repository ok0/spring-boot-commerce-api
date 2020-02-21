package co.kr.mustit.ecommerce.repository;

import co.kr.mustit.ecommerce.database.model.UserAddress;
import org.springframework.data.repository.CrudRepository;

public interface UserAddressRepository extends CrudRepository<UserAddress, String> {
    UserAddress findByUserIdAndStatus(String userId, int status);
    
    UserAddress findByAdressIdAndStatus(Long adressId, int status);
}
