package notebook;

import notebook.controller.UserController;
import notebook.dao.impl.FileOperation;
import notebook.model.User;
import notebook.repository.GBRepository;
import notebook.repository.impl.UserRepository;
import notebook.util.DBConnector;
import notebook.view.UserView;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        DBConnector.createDB();
        FileOperation fileOperation = new FileOperation("db.txt");
        GBRepository<User, Long> repository = new UserRepository(fileOperation);
        UserController controller = new UserController(repository);
        UserView view = new UserView(controller);
        view.run();
    }
}
