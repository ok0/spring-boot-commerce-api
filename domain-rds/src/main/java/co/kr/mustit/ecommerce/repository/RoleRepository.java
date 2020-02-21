package co.kr.mustit.ecommerce.repository;

import co.kr.mustit.ecommerce.database.model.Role;
import javax.transaction.Transactional;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 *
 */
@Repository
@Transactional
public interface RoleRepository extends PagingAndSortingRepository<Role, Integer> {

}
