import ua.KarpovIvan.atd.*;

import java.io.IOException;

class Main {
    public static void main(String[] args) {

        /* Список, з яким ведеться робота. */
        var students = new DoublyLinkedList<Student>();

        /* Запуск користувацького інтерфейсу. */
        try {
            UserInterface.start(students);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}