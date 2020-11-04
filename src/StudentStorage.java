import java.util.HashMap;
import java.util.logging.Logger;

public class StudentStorage {

    private static final Logger logger = Logger.getLogger(StudentStorage.class.getName());
    static {
        logger.setParent(Main.logger);
        logger.setUseParentHandlers(true);
    }

    private HashMap<String, Student> storage;

    public StudentStorage() {
        storage = new HashMap<>();
    }

    public void addStudent(String data) {
        String[] components = data.split("\\s+");
        try {
            String name = components[0] + " " + components[1];
            storage.put(name, new Student(name, components[3], components[2]));
            logger.info(String.format("Добавляем пользователя: %s", data));
        } catch (ArrayIndexOutOfBoundsException e) {
            logger.severe("Обращение к массиву компонентов по слишком большому индексу");
        }
    }

    public void listStudent() {
        storage.values().forEach(System.out::println);
    }

    public void removeStudent(String name) {
        storage.remove(name);
    }

    public Student getStudentByName(String name) {
        return storage.get(name);
    }

    public int getCount() {
        return storage.size();
    }
}