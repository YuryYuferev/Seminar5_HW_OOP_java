package notebook.dao.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import notebook.dao.Operation;

public class FileOperation implements Operation<String> {
    private final String fileName;

    public FileOperation(String fileName) {
        this.fileName = fileName;

        try {
            FileWriter writer = new FileWriter(fileName, true);

            try {
                writer.flush();
            } catch (Throwable var6) {
                try {
                    writer.close();
                } catch (Throwable var5) {
                    var6.addSuppressed(var5);
                }

                throw var6;
            }

            writer.close();
        } catch (IOException var7) {
            System.out.println(var7.getMessage());
        }

    }

    public List<String> readAll() {
        List<String> lines = new ArrayList();

        try {
            File file = new File(this.fileName);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            if (line != null) {
                lines.add(line);
            }

            while(line != null) {
                line = reader.readLine();
                if (line != null) {
                    lines.add(line);
                }
            }

            fr.close();
        } catch (IOException var6) {
            var6.printStackTrace();
        }

        return lines;
    }

    public void saveAll(List<String> data) {
        try {
            FileWriter writer = new FileWriter(this.fileName, false);

            try {
                Iterator var3 = data.iterator();

                while(true) {
                    if (!var3.hasNext()) {
                        writer.flush();
                        break;
                    }

                    String line = (String)var3.next();
                    writer.write(line);
                    writer.append('\n');
                }
            } catch (Throwable var6) {
                try {
                    writer.close();
                } catch (Throwable var5) {
                    var6.addSuppressed(var5);
                }

                throw var6;
            }

            writer.close();
        } catch (IOException var7) {
            System.out.println(var7.getMessage());
        }

    }
}
