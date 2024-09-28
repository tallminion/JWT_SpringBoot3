package com.minion.jwt.Services;

import com.minion.jwt.models.User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    // User user = new User();
    private List<User> store  = new ArrayList<>();

    public UserService() {
        store.add(new User(UUID.randomUUID().toString(), "Sagar", "sai.abc.com"));
        store.add(new User(UUID.randomUUID().toString(), "Milind", "milind.abc.com"));
        store.add(new User(UUID.randomUUID().toString(), "Puneet", "Puneet.abc.com"));
        store.add(new User(UUID.randomUUID().toString(), "Arpit", "Arpit.abc.com"));
    }

    public List<User> getUsers(){
        return this.store;
    }
}
