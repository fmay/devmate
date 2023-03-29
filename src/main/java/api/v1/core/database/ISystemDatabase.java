package api.v1.core.database;

import java.util.List;

public interface ISystemDatabase<T> {
    List<T> readQuery(String query);
}
