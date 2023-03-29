package api.v1.user.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.inject.Inject;
import api.v1.core.database.ISystemDatabase;
import api.v1.user.models.Profile;
import api.v1.user.models.Skill;
import api.v1.user.models.User;
import org.mapstruct.factory.Mappers;
import org.neo4j.driver.Record;
import org.neo4j.driver.internal.InternalNode;
import api.v1.user.models.DbToUserMapper;
import api.v1.user.models.UserDB;

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

        // Build query
        String query = "MATCH (user:User {uid: '" + loggedInUserId + "'})" +
                "OPTIONAL MATCH (user:User)-[:SKILL]->(skills)" +
                "OPTIONAL MATCH (user:User)-[:PROFILE]->(profile)" +
                "RETURN user, profile, collect(distinct skills) as skills";

        // Run query
        List<Record> result = _db.readQuery(query);

        // Get Neo4J result components as Maps
        Map<String, Object> userMap = result.get(0).get("user").asMap();
        Map<String, Object> profileMap = result.get(0).get("profile").asMap();
        List<Object> skillsList = result.get(0).get("skills").asList();
        Skill[] skillsArray = new Skill[skillsList.size()];

        // Map Skills to POJO
        final ObjectMapper jacksonMapper = new ObjectMapper();
        jacksonMapper.registerModule(new JavaTimeModule());
        DbToUserMapper mapper = Mappers.getMapper(DbToUserMapper.class);
        int index = 0;
        for(var item: skillsList) {
            Map<String, Object> map = ((InternalNode) item).asMap();
            skillsArray[index++] = jacksonMapper.convertValue(map, Skill.class);
        }

        // Build complete User POJO to have skills and profile
        UserDB udb = jacksonMapper.convertValue(userMap, UserDB.class);
        User user = mapper.dtoDbToUser(udb);

        // Assign skills and profile to main User POJO
        user.setSkills(skillsArray);
        user.setProfile(jacksonMapper.convertValue(profileMap, Profile.class));

        return user;
    }
}
