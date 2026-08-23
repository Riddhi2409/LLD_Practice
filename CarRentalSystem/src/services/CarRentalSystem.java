package services;

import entity.RentalStore;
import entity.User;

import java.util.ArrayList;
import java.util.List;

public class CarRentalSystem {
    List<RentalStore> storeList;
    List<User> userList;

    public CarRentalSystem(){

        storeList = new ArrayList<>();
        userList = new ArrayList<>();
    }

    public RentalStore getStore(String storeId) {
        return storeList.stream().filter(store -> store.getStoreId()== storeId).findFirst().get();
    }

    public User getUser(int userId) {
        return userList.get(userId);
    }

    public void addStore(RentalStore store) {
        storeList.add(store);
    }

    public void addUser(User user) {
        userList.add(user);
    }

    public void removeStore(int storeId) {
        storeList.remove(storeId);
    }

    public void removeUser(int userId) {
        userList.remove(userId);
    }

}
