import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {

    public static final Logger logger = Logger.getLogger(Main.class.getName());

    private static String addCommand = "add Василий Петров " +
            "vasily.petrov@gmail.com +79787775747";
    private static String commandExamples = "\t" + addCommand + "\n" +
            "\tlist\n\tcount\n\tremove Василий Петров";
    private static String commandError = "Wrong command! Available command examples: \n" +
            commandExamples;
    private static String helpText = "Command examples:\n" + commandExamples;

    public static void main(String[] args) {
        logger.info("Приложение запустилось");
        try {
            final FileHandler handler = new FileHandler("StudentList_Java.log");
            handler.setFormatter(new SimpleFormatter());
            logger.addHandler(handler);
            logger.info("Логирование настроено");
        } catch (SecurityException e) {
            logger.warning("Не удалось настроить файл логирования из-за политик безопасности.");
        } catch (IOException e) {
            logger.warning("Не удалось настроить файл логирования.");
        }

        Scanner scanner = new Scanner(System.in);
        StudentStorage executor = new StudentStorage();
        while (true) {
            try {
                String command = scanner.nextLine();
                String[] tokens = command.split("\\s+", 2);

                if (tokens[0].equals("add")) {
                    executor.addStudent(tokens[1]);
                } else if (tokens[0].equals("list")) {
                    executor.listStudent();
                } else if (tokens[0].equals("get")) {
                    executor.getStudentByName(tokens[1]).toString();
                } else if (tokens[0].equals("remove")) {
                    executor.removeStudent(tokens[1]);
                } else if (tokens[0].equals("count")) {
                    logger.info("There are " + executor.getCount() + " customers");
                } else if (tokens[0].equals("help")) {
                    logger.info(helpText);
                } else {
                    logger.warning(commandError);
                }

            } catch (NoSuchElementException e) {
                logger.log(Level.SEVERE, "Не введена строка");
            } catch (ArrayIndexOutOfBoundsException e) {
                logger.log(Level.SEVERE, "Запрос токена по слишком большому индексу");
            }
        }
    }
}
