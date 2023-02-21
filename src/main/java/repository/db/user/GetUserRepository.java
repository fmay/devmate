package repository.db.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.application.Config;
import domain.user.Profile;
import domain.user.Skill;
import domain.user.User;
import org.mapstruct.factory.Mappers;
import org.neo4j.driver.Record;
import org.neo4j.driver.internal.InternalNode;
import java.util.List;
import java.util.Map;

public class GetUserRepository {

    public User execute(String loggedInUserId) {
        final Config config = Config.getInstance();
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
        Map<String, Object> userMap = result.get(0).get("user").asMap();
        Map<String, Object> profileMap = result.get(0).get("profile").asMap();
        List<Object> skillsList = result.get(0).get("skills").asList();
        Skill[] skillsArray = new Skill[skillsList.size()];
        int index = 0;
        for(var item: skillsList) {
            Map<String, Object> map = ((InternalNode) item).asMap();
            skillsArray[index++] = jacksonMapper.convertValue(map, Skill.class);
        }

        // Sanitise user db data
        UserDB udb = jacksonMapper.convertValue(userMap, UserDB.class);
        User user = mapper.dbToUserDto(udb);

        // Assign skills
        user.setSkills(skillsArray);

        // Assign profile
        user.setProfile(jacksonMapper.convertValue(profileMap, Profile.class));

        return user;
    }
}
