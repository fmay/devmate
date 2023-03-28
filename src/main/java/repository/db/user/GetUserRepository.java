package repository.db.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import core.config.ISystemDatabase;
import domain.user.Profile;
import domain.user.Skill;
import domain.user.User;
import org.mapstruct.factory.Mappers;
import org.neo4j.driver.Record;
import org.neo4j.driver.internal.InternalNode;
import repository.db.user.mappers.DbToUserMapper;
import repository.db.user.mappers.UserDB;

import java.util.List;
import java.util.Map;

public class GetUserRepository implements IGetUserRepository {

    private ISystemDatabase _db;

    @Inject
    public GetUserRepository(ISystemDatabase db) {
        _db = db;
    }

    @Override
    public User execute(String loggedInUserId) {
        // Jackson mapper from N4J map to POJO
        final ObjectMapper jacksonMapper = new ObjectMapper();
        jacksonMapper.findAndRegisterModules();
        DbToUserMapper mapper = Mappers.getMapper(DbToUserMapper.class);

        // Build query
        String query = "MATCH (user:User {uid: '" + loggedInUserId + "'})" +
                "OPTIONAL MATCH (user:User)-[:SKILL]->(skills)" +
                "OPTIONAL MATCH (user:User)-[:PROFILE]->(profile)" +
                "RETURN user, profile, collect(distinct skills) as skills";

        // Run query
        List<Record> result = _db.readQuery(query);

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
        User user = mapper.dtoDbToUser(udb);

        // Assign skills
        user.setSkills(skillsArray);

        // Assign profile
        user.setProfile(jacksonMapper.convertValue(profileMap, Profile.class));

        return user;
    }
}
