package api.v1.user.repository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import api.v1.user.models.User;
import org.mapstruct.factory.Mappers;
import org.neo4j.driver.Record;
import api.v1.core.database.Neo4j;
import api.v1.user.mappers.DbToUserMapper;
import api.v1.user.mappers.UserSummaryData;

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
        // Run query
        String query = "MATCH (u:User) return u";
        List<Record> result = _db.readQuery(query);

        // Jackson mapper from N4J map to POJO
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        DbToUserMapper mapper = Mappers.getMapper(DbToUserMapper.class);
        return result.stream()
            .map(record -> {
                UserSummaryData udb = objectMapper.convertValue(record.get("u").asMap(), UserSummaryData.class);
                return mapper.dtoDbToUser(udb);
            })
            .collect(Collectors.toList());
    }
}
