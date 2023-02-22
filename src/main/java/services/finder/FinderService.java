package services.finder;

import com.google.gson.Gson;
import domain.finder.FinderQueryDTO;
import domain.finder.FinderResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.user.UserService;

public class FinderService {
    static Logger logger = LoggerFactory.getLogger(UserService.class.getName());
    public String runQuery(FinderQueryDTO query) {
        logger.info("getUser()");
        FinderResponse response = new FinderResponse();
        return new Gson().toJson(response);
    }
}
