package notebook.util;

import java.io.File;

public class DBConnector {
    public static final String DB_PATH = "db.txt";

    public DBConnector() {
    }

    public static void createDB() {
        try {
            File db = new File("db.txt");
            if (db.createNewFile()) {
                System.out.println("DB created");
            } else {
                System.out.println("DB already exists");
            }
        } catch (Exception var1) {
            System.err.println(var1);
        }

    }
}
