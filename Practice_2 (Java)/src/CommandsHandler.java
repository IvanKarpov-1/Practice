import java.util.Iterator;
import java.util.Scanner;
import ua.KarpovIvan.atd.*;

public class CommandsHandler {
    static Scanner in = new Scanner(System.in);

    /*
     * Команда для виводу списока всіх доступних команд.
     */
    public static void commandHelp() {
        System.out.println(" Доступнi команди:");
        System.out.println(" /addfirst\t- додати елемент в початок списку.");
        System.out.println(" /clear\t\t- очистити консоль.");
        System.out.println(" /end\t\t- закiнчити роботу.");
        System.out.println(" /help\t\t- вивести список доступних команд.");
        System.out.println(" /length\t- вивести довжину списку.");
        System.out.println(" /removelast\t- видалити елемент з кiнця списку.");
        System.out.println(" /search\t- знайти у списку студентiв з iдеальною вагою.");
        System.out.println(" /show\t\t- вивести список.");
        System.out.println(" /showback\t- вивести список у зворотньому порядку.");
        System.out.println(" /sort\t\t- сортувати список зростом.");
    }

    /*
     * Команда для додавання елемента в початок списку.
     */
    public static void commandAddFirst(DoublyLinkedList<Student> students) {
        /* Ввід імені студента з перевіркою на правильність та допустимість діапазону. */
        Names name;
        System.out.println(" Введiть iм'я студента");
        System.out.println(" (0 - Андрiй, " +
                "1 - Вiка, " +
                "2 - Денис, " +
                "3 - iван, " +
                "4 - iнна, " +
                "5 - Лiза, " +
                "6 - Микола, " +
                "7 - Мирослав, " +
                "8 - Сергiй, " +
                "9 - Снiжана):");
        while (true) {
            try {
                System.out.print(" ");
                int index = in.nextInt(); /* Зчитування числа. */
                if (-1 < index && index < Names.Count.ordinal()) {
                    name = Names.values()[index];
                    break;
                }
                else {
                    throw new Exception();
                }
            }
            catch (Exception exception) {
                System.out.println(" Введено невiрне значення! Спробуйте ще раз:");
                in.nextLine();
            }
        }

        /* Ввід зросту студента з перевіркою на правильність та допустимість діапазону. */
        int height;
        System.out.println(" Введiть зрiст студента в межах вiд 50 см до 250 см (цiле значення):");
        while (true) {
            try {
                System.out.print(" ");
                height = in.nextInt();

                if (50 <= height && height <= 250) {
                    break;
                }
                else {
                    throw new Exception();
                }
            }
            catch (Exception exception) {
                System.out.println(" Введено невiрне значення! Спробуйте ще раз:");
                in.nextLine();
            }
        }

        /* Ввід ваги студента з перевіркою на правильність та допустимість діапазону. */
        double weight;
        System.out.println(" Введiть вагу студента вiд 40 кг до 250 кг:");
        while (true) {
            try {
                System.out.print(" ");
                weight = in.nextDouble();

                if (40 <= weight && weight <= 250) {
                    break;
                }
                else {
                    throw new Exception();
                }
            }
            catch (Exception exception) {
                System.out.println(" Введено невiрне значення! Спробуйте ще раз:");
                in.nextLine();
            }
        }

        /* Створення нового екземпляру Student да додавання його до списку. */
        Student student = new Student(name, height, weight);
        students.addFirst(student);

        /* Виведення оновленого списоку на екран. */
        commandShow(students);
    }

    /*
     * Команда для виводу довжини списку.
     */
    public static void commandLength(DoublyLinkedList<Student> students) {
        System.out.printf(" Довжина списку: %d\n", students.getCount());
    }

    /*
     * Команда для видалення останнього елементу зі списку.
     */
    public static void commandRemoveLast(DoublyLinkedList<Student> students) {
        try {
            students.removeLast();
        }
        catch (Exception exception) {
            System.out.printf(" %s\n", exception.getMessage());
        }

        /* Виводить оновлений список на екран. */
        commandShow(students);
    }

    /*
     * Команда для пошуку і виводу на консоль студента з ідеальною вагою тіла.
     */
    public static void commandSearch(DoublyLinkedList<Student> students) {
        try {
            DoublyLinkedList<Student> searchResult = students.searchOfElement();
            System.out.println(" Результати пошуку:");
            commandShow(searchResult);
        }
        catch (Exception exception) {
            System.out.printf(" %s\n", exception.getMessage());
        }
    }

    /*
     * Команда для виводу списку на консоль у вигляді таблиці.
     */
    public static void commandShow(DoublyLinkedList<Student> students) {
        String hyphensLine = String.format("*%38s", "-").replace(' ', '-').replace('*', ' ');
        System.out.println(hyphensLine);
        System.out.printf(" |  %8s  |  %8s  |  %6s  |%n", "Iм'я", "Зрiст", "Вага");
        System.out.println(hyphensLine);

        /* Виведення порожньої таблиці або таблиці з елементами списку. */
        if (students.listEmpty()) {
            System.out.printf(" |  %8s  |  %8s  |  %6s  |%n", "", "", "");
        }
        else {
            for (Student student : students) {
                System.out.printf(" |  %8s  |  %8d  |  %6.2f  |%n", student.getName().name(), student.getHeight(), student.getWeight());
            }
        }
        System.out.println(hyphensLine);
    }

    /*
     * Команда для виводу списку на консоль у вигляді таблиці у зворотному порядку.
     */
    public static void commandShowBack(DoublyLinkedList<Student> students) {
        String hyphensLine = String.format("*%38s", "-").replace(' ', '-').replace('*', ' ');
        System.out.println(hyphensLine);
        System.out.printf(" |  %8s  |  %8s  |  %6s  |%n", "Iм'я", "Зрiст", "Вага");
        System.out.println(hyphensLine);

        /* Виведення порожньої таблиці або таблиці з елементами списку. */
        if (students.listEmpty()) {
            System.out.printf(" |  %8s  |  %8s  |  %6s  |%n", "", "", "");
        }
        else {
            Iterator<Student> iterator = students.backIterator();

            while (iterator.hasNext()) {
                Student student = iterator.next();
                System.out.printf(" |  %8s  |  %8d  |  %6.2f  |%n", student.getName().name(), student.getHeight(), student.getWeight());
            }
        }
        System.out.println(hyphensLine);
    }

    /*
     * Командля для сортування списку.
     */
    public static void commandSort(DoublyLinkedList<Student> students) {
        try {
            students.sort();
            commandShow(students);
        }
        catch (Exception exception) {
            System.out.printf(" %s\n", exception.getMessage());
        }
    }
}  