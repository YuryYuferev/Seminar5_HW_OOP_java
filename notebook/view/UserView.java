package notebook.view;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import notebook.controller.UserController;
import notebook.model.User;
import notebook.util.Commands;

public class UserView {
    private final UserController userController;

    public UserView(UserController userController) {
        this.userController = userController;
    }

    public void run() {
        label32:
        while(true) {
            String command = this.prompt("Введите команду: ");
            Commands com = Commands.valueOf(command);
            if (com == Commands.EXIT) {
                return;
            }

            switch (com) {
                case CREATE:
                    String firstName = this.prompt("Имя: ");
                    String lastName = this.prompt("Фамилия: ");
                    String phone = this.prompt("Номер телефона: ");
                    this.userController.saveUser(new User(firstName, lastName, phone));
                    break;
                case READ:
                    String id = this.prompt("Идентификатор пользователя: ");

                    try {
                        User user = this.userController.readUser(Long.parseLong(id));
                        System.out.println(user);
                        System.out.println();
                        break;
                    } catch (Exception var15) {
                        throw new RuntimeException(var15);
                    }
                case LIST:
                    List<User> users = this.userController.getAllUsers();
                    Iterator var17 = users.iterator();

                    while(true) {
                        if (!var17.hasNext()) {
                            continue label32;
                        }

                        User user = (User)var17.next();
                        System.out.println(user);
                    }
//                case UPDATE:
//                    long userId = Long.parseLong(this.prompt("Input id users: "));
//                    String updateName = this.prompt("Имя: ");
//                    String updateLastName = this.prompt("Фамилия: ");
//                    String updatePhone = this.prompt("Номер телефона: ");
//                    User updatedUser = new User(updateName, updateLastName, updatePhone);
//                    this.userController.userUpdate(userId, updatedUser);
//                    break;
                case UPDATE:
                    String userId = prompt("Enter user id: ");
                    userController.updateUser(userId, createUser());
                    break;
                case DELETE:
                    String userIdDelete = this.prompt("Идентификатор пользователя: ");
                    this.userController.deleteUser(userIdDelete);
            }
        }
    }

    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }

    private User createUser() {
        String firstName = this.prompt("Имя: ");
        String lastName = this.prompt("Фамилия: ");
        String phone = this.prompt("Номер телефона: ");
        return new User(firstName, lastName, phone);
    }
}
