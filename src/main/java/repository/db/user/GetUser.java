package repository.db.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.application.Config;
import domain.user.Skill;
import domain.user.User;
import org.mapstruct.factory.Mappers;
import org.neo4j.driver.Record;
import org.neo4j.driver.internal.InternalNode;
import java.util.List;
import java.util.Map;

public class GetUser {

    private final Config config = Config.getInstance();

    public User execute(String loggedInUserId, String otherUserId) {
        // Jackson mapper from N4J map to POJO
        final ObjectMapper jacksonMapper = new ObjectMapper();
        // For date processing
        jacksonMapper.findAndRegisterModules();

        // Mapper from DB POJO to Entity POJO
        DbToUserMapper mapper = Mappers.getMapper(DbToUserMapper.class);

        // Build query
        String query = "MATCH (user:User {uid: '" + loggedInUserId + "'})" +
                "OPTIONAL MATCH (user:User)-[:SKILL]->(skills)" +
                "OPTIONAL MATCH (user:User)-[:PROFILE]->(profile)" +
                "RETURN user, profile, collect(distinct skills) as skills";

        // Run query
        List<Record> result = config.db.readTx(query);

        // Get result components as Maps
        var userMap = result.get(0).get("user").asMap();
        var profileMap = result.get(0).get("profile").asMap();
        List skillsList = result.get(0).get("skills").asList();
        Skill[] skillsArray = new Skill[skillsList.size()];
        Integer index = 0;
        for(var item: skillsList) {
            Map map = ((InternalNode) item).asMap();
            skillsArray[index++] = jacksonMapper.convertValue(map, Skill.class);
        }
        UserDB udb = jacksonMapper.convertValue(userMap, UserDB.class);
        User user = mapper.dbToUserDto(udb);
        user.setSkills(skillsArray);
        return user;
    }
}
