package core.util;

import domain.finder.FinderQuery;
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
