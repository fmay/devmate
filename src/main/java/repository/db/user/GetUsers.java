package repository.db.user;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.application.Config;
import domain.user.User;
import domain.user.UserType;
import org.mapstruct.factory.Mappers;
import org.neo4j.driver.Record;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GetUsers {

    private final Config config = Config.getInstance();

    public List<User> execute() {
        // Jackson mapper from N4J map to POJO
        final ObjectMapper objectMapper = new ObjectMapper();
        // For date processing
        objectMapper.findAndRegisterModules();

        // Mapper from DB POJO to Entity POJO
        DbToUserMapper mapper = Mappers.getMapper(DbToUserMapper.class);

        // Run query
        String query = "MATCH (u:User) return u";
        List<Record> result = config.db.readTx(query);

        // Convert results to List<User>
        List<User> myList = result.stream()
            .map(record -> {
                UserDB udb = objectMapper.convertValue(record.get("u").asMap(), UserDB.class);
                return mapper.dbToUserDto(udb);
            })
            .collect(Collectors.toList());

        return myList;
    }
}
