package api.v1.core.util;

import api.v1.finder.request.FinderRequest;
import jakarta.validation.ConstraintViolation;

import java.util.List;

public class ValidationMessages {

    public static <T> String getAll(List<ConstraintViolation<T>> errors) {
        String msg = "";
        for(var item: errors) {
            msg += item.getPropertyPath() + " - " + item.getMessage() + "; ";
        }
        return msg;
    }

}
