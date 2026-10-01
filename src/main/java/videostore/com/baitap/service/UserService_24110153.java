package videostore.com.baitap.service;

import videostore.com.baitap.dao.UserDAO_24110153;
import videostore.com.baitap.entity.User;

public class UserService_24110153 {
    
    private UserDAO_24110153 dao = new UserDAO_24110153();

    public void register(User user) {
        dao.insert(user);
    }

    public User findByUsername(String username) {
        return dao.findById(username);
    }
}