package api.v1.user.repository;

import api.v1.core.logging.ILogging;
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
import api.v1.user.mappers.DbToUserMapper;
import api.v1.user.mappers.UserSummaryData;

import java.util.List;
import java.util.Map;

public class GetUserRepository implements IGetUserRepository {

    private ISystemDatabase _db;
    private ILogging _logger;

    @Inject
    public GetUserRepository(ISystemDatabase db, ILogging logger) {
        _db = db;
        _logger = logger;
    }

    @Override
    public User execute(String loggedInUserId) {

        _logger.debug("N4J GetUser:" + loggedInUserId);

        // Build query
        String query = "MATCH (user:User {uid: '" + loggedInUserId + "'})" +
                "OPTIONAL MATCH (user:User)-[:SKILL]->(skills)" +
                "OPTIONAL MATCH (user:User)-[:PROFILE]->(profile)" +
                "RETURN user, profile, collect(distinct skills) as skills";

        // Run query
        List<Record> result = _db.readQuery(query);

        // Get Neo4J query result components as Maps
        Map<String, Object> userMap = result.get(0).get("user").asMap();
        Map<String, Object> profileMap = result.get(0).get("profile").asMap();
        List<Object> skillsList = result.get(0).get("skills").asList();
        Skill[] skillsArray = new Skill[skillsList.size()];

        // Get basic User POJO and add profile data
        final ObjectMapper jacksonMapper = new ObjectMapper();
        jacksonMapper.registerModule(new JavaTimeModule());
        UserSummaryData udb = jacksonMapper.convertValue(userMap, UserSummaryData.class);
        DbToUserMapper mapper = Mappers.getMapper(DbToUserMapper.class);
        User user = mapper.dtoDbToUser(udb);
        user.setProfile(jacksonMapper.convertValue(profileMap, Profile.class));

        // Add Skills to User POJO
        int index = 0;
        for(var skillItem: skillsList) {
            Map<String, Object> skillMap = ((InternalNode) skillItem).asMap();
            skillsArray[index++] = jacksonMapper.convertValue(skillMap, Skill.class);
        }
        user.setSkills(skillsArray);

        return user;
    }
}
