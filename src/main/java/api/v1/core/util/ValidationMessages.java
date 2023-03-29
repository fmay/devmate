package api.v1.core.util;

import api.v1.finder.request.FinderQuery;
import jakarta.validation.ConstraintViolation;

import java.util.List;

public class ValidationMessages {

    public static String getAll(List<ConstraintViolation<FinderQuery>> errors) {
        String msg = "";
        for(var item: errors) {
            msg += item.getPropertyPath() + " - " + item.getMessage() + "; ";
        }
        return msg;
    }

}
