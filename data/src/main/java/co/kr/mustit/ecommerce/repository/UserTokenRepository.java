package co.kr.mustit.ecommerce.repository;

import co.kr.mustit.ecommerce.database.model.UserToken;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserTokenRepository extends CrudRepository<UserToken, String> {

}
