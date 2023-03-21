package notebook.mapper.impl;

import notebook.mapper.Mapper;
import notebook.model.User;

public class UserMapper implements Mapper<User, String> {
    public UserMapper() {
    }

    public String toInput(User user) {
        return String.format("%s,%s,%s,%s", user.getId(), user.getFirstName(), user.getLastName(), user.getPhone());
    }

    public User toOutput(String s) {
        String[] lines = s.split(",");
        if (this.isDigit(lines[0])) {
            long id = Long.parseLong(lines[0]);
            return new User(id, lines[1], lines[2], lines[3]);
        } else {
            throw new NumberFormatException("Id must be a large number");
        }
    }

    private boolean isDigit(String s) throws NumberFormatException {
        try {
            Long.parseLong(s);
            return true;
        } catch (NumberFormatException var3) {
            return false;
        }
    }
}
