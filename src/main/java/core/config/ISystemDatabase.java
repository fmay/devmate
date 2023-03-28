package core.config;

import java.util.List;

public interface ISystemDatabase<T> {
    List<T> readQuery(String query);
}
