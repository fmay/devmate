package api.v1.user.repository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import api.v1.user.models.User;
import org.mapstruct.factory.Mappers;
import org.neo4j.driver.Record;
import api.v1.core.database.Neo4j;
import api.v1.user.models.DbToUserMapper;
import api.v1.user.models.UserDB;

import java.util.List;
import java.util.stream.Collectors;

public class GetAllUsersRepository implements IGetAllUsersRepository {

    private final Neo4j _db;

    @Inject
    public GetAllUsersRepository(Neo4j db) {
        _db = db;
    }

    @Override
    public List<User> execute() {
        // Jackson mapper from N4J map to POJO
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();

        // Mapper for DB object to Response POJO
        DbToUserMapper mapper = Mappers.getMapper(DbToUserMapper.class);

        // Run query
        String query = "MATCH (u:User) return u";
        List<Record> result = _db.readQuery(query);

        // Convert results to List<User>
        return result.stream()
            .map(record -> {
                UserDB udb = objectMapper.convertValue(record.get("u").asMap(), UserDB.class);
                return mapper.dtoDbToUser(udb);
            })
            .collect(Collectors.toList());
    }
}
