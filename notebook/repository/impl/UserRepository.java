package notebook.repository.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import notebook.dao.impl.FileOperation;
import notebook.mapper.impl.UserMapper;
import notebook.model.User;
import notebook.repository.GBRepository;

public class UserRepository implements GBRepository<User, Long> {
    private final UserMapper mapper = new UserMapper();
    private final FileOperation operation;

    public UserRepository(FileOperation operation) {
        this.operation = operation;
    }

    public List<User> findAll() {
        List<String> lines = this.operation.readAll();
        List<User> users = new ArrayList();
        Iterator var3 = lines.iterator();

        while(var3.hasNext()) {
            String line = (String)var3.next();
            users.add(this.mapper.toOutput(line));
        }

        return users;
    }

    public User create(User user) {
        List<User> users = this.findAll();
        long max = 0L;
        Iterator var5 = users.iterator();

        while(var5.hasNext()) {
            User u = (User)var5.next();
            long id = u.getId();
            if (max < id) {
                max = id;
            }
        }

        long next = max + 1L;
        user.setId(next);
        users.add(user);
        this.write(users);
        return user;
    }

    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    public Optional<User> update(Long userId, User update) {
        List<User> users = this.findAll();
        User editUser = (User)users.stream().filter((u) -> {
            return u.getId().equals(userId);
        }).findFirst().orElseThrow(() -> {
            return new RuntimeException("User not found");
        });
        editUser.setFirstName(update.getFirstName());
        editUser.setLastName(update.getLastName());
        editUser.setPhone(update.getPhone());
        this.write(users);
        return Optional.of(update);
    }

    public boolean delete(Long userId) {
        List<User> users = this.findAll();
        User deleteUser = (User)users.stream().filter((ux) -> {
            return ux.getId().equals(userId);
        }).findFirst().orElseThrow(() -> {
            return new RuntimeException("User not found");
        });
        List<User> usersAfterDelete = new ArrayList();
        Iterator var5 = users.iterator();

        while(var5.hasNext()) {
            User u = (User)var5.next();
            if (u != deleteUser) {
                usersAfterDelete.add(u);
            }
        }

        this.write(usersAfterDelete);
        return true;
    }

    private void write(List<User> users) {
        List<String> lines = new ArrayList();
        Iterator var3 = users.iterator();

        while(var3.hasNext()) {
            User u = (User)var3.next();
            lines.add(this.mapper.toInput(u));
        }

        this.operation.saveAll(lines);
    }
}
