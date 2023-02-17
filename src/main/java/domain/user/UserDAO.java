package domain.user;

import domain.application.Dao;

import java.util.Arrays;
import java.util.List;

public final class UserDAO implements Dao<User> {

    User u1 = new User("id1", UserType.PERSON, false, false  );
    User u2 = new User("id2", UserType.PERSON, false, false  );
    User u3 = new User("id3", UserType.PERSON, false, false  );
    private List<User> list = Arrays.asList(u1, u2, u3);

    public UserDAO() {
    }

    @Override
    public List<User> getAll() {

        return list;
    }

    @Override
    public User get(String id) {
        return u2;
    }

    @Override
    public void save(User u) {

    }

    @Override
    public void update(User u, String[] data) {

    }

    @Override
    public void delete(User u) {

    }


}
