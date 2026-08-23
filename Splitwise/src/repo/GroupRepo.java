package repo;

import entity.Group;

import java.util.*;

public class GroupRepo {
    private final Map<String, Group> store = new HashMap<>();

    public Optional<Group> findById(String id) { return Optional.ofNullable(store.get(id)); }
    public void save(Group g) { store.put(g.getGroupId(), g); }
}
