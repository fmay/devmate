package services.finder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import core.util.ValidationMessages;
import domain.finder.FinderQuery;
import domain.finder.FinderQueryType;
import domain.finder.FinderResponse;
import jakarta.validation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.db.finder.FinderRepository;
import services.user.UserService;

import java.io.IOException;
import java.util.Set;

public class FinderService {
    static Logger logger = LoggerFactory.getLogger(UserService.class.getName());
    static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    static Validator validator = factory.getValidator();

    public String runQuery(String body) throws IOException, ValidationException {
        logger.info("getUser()");
        // Convert body to FinderQuery
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonTree = mapper.readTree(body);

        // Map nested bounding box to flat POJO format
        String tlLat = jsonTree.get("boundingBox").get("topLeft").get("lat").toString();
        String tlLng = jsonTree.get("boundingBox").get("topLeft").get("lng").toString();
        String brLat = jsonTree.get("boundingBox").get("bottomRight").get("lat").toString();
        String brLng = jsonTree.get("boundingBox").get("bottomRight").get("lng").toString();

        // Remove bounding box and add back to map
        ((ObjectNode)jsonTree).remove("boundingBox");
        ((ObjectNode)jsonTree).put("tlLat", tlLat);
        ((ObjectNode)jsonTree).put("tlLng", tlLng);
        ((ObjectNode)jsonTree).put("brLat", brLat);
        ((ObjectNode)jsonTree).put("brLng", brLng);

        // Map to type enum
//        String enumString = jsonTree.get("type").toString();
//        FinderQueryType enumValue = FinderQueryType.valueOf(enumString);
//        ((ObjectNode)jsonTree).put("type", FinderQueryType.valueOf(""));


        // Map to class and validate
        FinderQuery query = mapper.convertValue(jsonTree, FinderQuery.class);
        Set<ConstraintViolation<FinderQuery>> constraintViolations = validator.validate(query);
        if(constraintViolations.size()>0) {
            throw new ValidationException(ValidationMessages.getAll(constraintViolations.stream().toList()));
        }

        // Run query
        FinderResponse response = FinderRepository.execute(query);
        return new Gson().toJson(response);
    }
}
