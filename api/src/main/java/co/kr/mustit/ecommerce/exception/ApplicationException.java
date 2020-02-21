package co.kr.mustit.ecommerce.exception;

import co.kr.mustit.ecommerce.api.response.util.APIStatus;
import co.kr.mustit.util.Constant;
import java.util.List;


public class ApplicationException extends RuntimeException {

    private APIStatus apiStatus;
    private List<Constant.ParamError> data;

    public ApplicationException(APIStatus apiStatus) {
        this.apiStatus = apiStatus;
    }

    /**
     *
     *
     *
     * @param apiStatus
     * @param data
     */
    public ApplicationException(APIStatus apiStatus, List<Constant.ParamError> data) {
        this(apiStatus);
        this.data = data;
    }

    public ApplicationException(Throwable cause) {
        super(cause);
    }

    public APIStatus getApiStatus() {
        return apiStatus;
    }

    public List<Constant.ParamError> getData() {
        return data;
    }

}
