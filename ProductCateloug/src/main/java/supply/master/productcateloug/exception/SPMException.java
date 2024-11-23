package supply.master.productcateloug.exception;

import lombok.Data;
import supply.master.productcateloug.dto.ErrorResponse;

@Data
public class SPMException extends RuntimeException{
    private final ErrorResponse errorResponse;

    public SPMException(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }
}
