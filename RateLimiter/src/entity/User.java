package entity;

import enums.UserTier;

public class User {
    private final String userId;
    private  UserTier tier;

    public User(String userId, UserTier tier) {
        this.userId = userId;
        this.tier = tier;
    }

    public void setTier(UserTier tier) {
        this.tier = tier;
    }

    public String getUserId() {
        return userId;
    }

    public UserTier getTier() {
        return tier;
    }
}
