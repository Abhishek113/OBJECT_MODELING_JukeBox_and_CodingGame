package com.crio.jukebox.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.crio.jukebox.entities.User;

public class UserRepository implements IUserRepository {

    private Map<String, User> userMap;
    private Integer autoIncrement = 0;

    public UserRepository()
    {
        this.userMap = new HashMap<>();
    }

    public UserRepository(Map<String, User> userMap)
    {
        this.userMap = userMap;
    }

    @Override
    public User save(User user)
    {
        if(user.getId() == null)
        {
            autoIncrement++;
            user = new User(Integer.toString(autoIncrement), user.getName(), user.getAllPlaylists(), user.getUserPlaylistSongs());
        }
        userMap.put(user.getId(), user);

        return user;
    }

    @Override
    public Optional<User> findByName(String userName) {
        return userMap.entrySet().stream().filter(user -> user.getValue().getName().equals(userName))
                                                                .map(Map.Entry::getValue).findFirst();
    }

    @Override
    public List<User> findAll() {
        if(userMap.size() == 0)
            return new ArrayList<User>();

        return userMap.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.ofNullable(userMap.get(id));
    }
    
    @Override
    public boolean existsById(String id) {
        
        if(userMap.containsKey(id))
            return true;
        return false;
    }


    @Override
    public void deleteById(String userId) {
        if(userMap.containsKey(userId))
            userMap.remove(userId);
    }

    @Override
    public void delete(User entity) {
        String userId = entity.getId();
        if(userId != null)
           deleteById(userId);
    }


    @Override
    public long count() {
        return userMap.size();
    }
    
    
}