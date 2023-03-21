package notebook.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import notebook.model.User;
import notebook.repository.GBRepository;

public class UserController {
    private final GBRepository<User, Long> repository;

    public UserController(GBRepository<User, Long> repository) {
        this.repository = repository;
    }

    public void saveUser(User user) {
        this.repository.create(user);
    }

    public User readUser(Long userId) throws Exception {
        List<User> users = this.repository.findAll();
        Iterator var3 = users.iterator();

        User user;
        do {
            if (!var3.hasNext()) {
                throw new RuntimeException("User not found");
            }

            user = (User)var3.next();
        } while(!Objects.equals(user.getId(), userId));

        return user;
    }

    public List<User> getAllUsers() {
        return this.repository.findAll();
    }

    public void updateUser(String userId, User update) {
        update.setId(Long.parseLong(userId));
        this.repository.update(Long.parseLong(userId), update);
    }

    public void deleteUser(String userId) {
        this.repository.delete(Long.parseLong(userId));
    }

    public void userUpdate(long userId, User updatedUser) {
    }
}
