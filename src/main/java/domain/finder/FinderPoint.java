package domain.finder;

import domain.user.Skill;
import domain.user.UserType;

public record FinderPoint(
    String userId,
    UserType userType,
    String gigId,
    String ownerId,
    Skill[] skills,
    String name,
    boolean hasAvatar,
    double lat,
    double lng,
    boolean isCluster,
    int pointCount,
    int distance,
    boolean isTestData
){}
