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
public class UserListRequestModel {
    public String searchKey;
    public int sortCase;
    public Boolean ascSort;
    public int pageNumber;
    public int pageSize;
    
}
