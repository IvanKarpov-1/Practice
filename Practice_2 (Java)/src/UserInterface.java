import java.io.IOException;
import java.util.Scanner;
import ua.KarpovIvan.atd.*;

public class UserInterface
{
    /*
     * Метод, який запускає користувацький інтерфейс.
     */
    public static void start(DoublyLinkedList<Student> students) throws IOException, InterruptedException {
        var in = new Scanner(System.in); /* Для зчитування введеня з консолі */
        String input;

        /* Перший вивід всіх можливих команд. */
        CommandsHandler.commandHelp();
        System.out.println();

        while (true) {
            /* Очікування введення команди. */
            System.out.print(" ");
            input = in.nextLine();

            /* Перевірка на співпадіння з можливими командами. */
            if (input.equals("/addfirst")) {
                System.out.print("\r");
                CommandsHandler.commandAddFirst(students);
                System.out.println();
                continue;
            }
            if (input.equals("/clear")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                continue;
            }
            if (input.equals("/end")) {
                break;
            }
            if (input.equals("/help")) {
                CommandsHandler.commandHelp();
                System.out.println();
                continue;
            }
            if (input.equals("/length")) {
                CommandsHandler.commandLength(students);
                System.out.println();
                continue;
            }
            if (input.equals("/removelast")) {
                CommandsHandler.commandRemoveLast(students);
                System.out.println();
                continue;
            }
            if (input.equals("/search")) {
                CommandsHandler.commandSearch(students);
                System.out.println();
                continue;
            }
            if (input.equals("/show")) {
                CommandsHandler.commandShow(students);
                System.out.println();
                continue;
            }
            if (input.equals("/showback")) {
                CommandsHandler.commandShowBack(students);
                System.out.println();
                continue;
            }
            if (input.equals("/sort")) {
                CommandsHandler.commandSort(students);
                System.out.println();
                continue;
            }

            /* Якщо введена команда не співпадає з можливими, прохання ввести команду ще раз. */
            System.out.println(" Команда ввведена невiрно. Спробуйте ще раз.");
            CommandsHandler.commandHelp();
            System.out.println();
        }
    }
}