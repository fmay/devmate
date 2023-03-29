package api.v1.finder.models;

import api.v1.user.models.Skill;
import api.v1.user.models.UserType;

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
