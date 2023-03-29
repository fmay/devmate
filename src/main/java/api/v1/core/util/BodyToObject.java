package api.v1.core.util;

import api.v1.finder.request.FinderRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.*;

import java.util.Set;

public class BodyToObject {
    static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    static Validator validator = factory.getValidator();


    public static <T> T convert(String body, Class<?> cls) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(body);
        T result = (T) mapper.convertValue(json, cls);

        Set<ConstraintViolation<T>> constraintViolations = validator.validate(result);
        if(constraintViolations.size()>0) {
            throw new ValidationException(ValidationMessages.getAll(constraintViolations.stream().toList()));
        }

        return result;
    }
}
