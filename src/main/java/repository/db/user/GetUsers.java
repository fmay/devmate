package repository.db.user;
import domain.application.Config;
import domain.user.User;
import domain.user.UserType;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;

import java.util.Arrays;
import java.util.List;

public class GetUsers {

    private final Config config = Config.getInstance();

    User u1 = new User("USER1", UserType.PERSON, false, false);
    User u2 = new User("USER2", UserType.PERSON, false, false);
    List<User> users = Arrays.asList(u1, u2);

    public List<User> execute() {
        String query = "MATCH (u:User) return u";
        List<Record> result = config.db.readTx(query);
        System.out.println("Rec:" + result.toString());
        return users;
    }
}
