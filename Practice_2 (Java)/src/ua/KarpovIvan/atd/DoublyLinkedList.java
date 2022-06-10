package ua.KarpovIvan.atd;

import java.util.Iterator;

/*
 * Клас, що реалізує двоспрямований список.
 */
public class DoublyLinkedList<T> implements Iterable<T> {
    private DoublyNode<T> head; /* Початок списку. */
    private DoublyNode<T> tail; /* Кінець списку. */
    private int count = 0;      /* Кількість елементів в списку. */

    /*
     * Додавання елемента в початок списку.
     */
    public void addFirst(T data) {
        DoublyNode<T> node = new DoublyNode<>(data);
        DoublyNode<T> temp = head;

        node.setNext(temp);
        head = node;

        if (listEmpty()) {
            tail = head;
        }
        else {
            temp.setPrevious(node);
        }

        count++;
    }

    /*
     * Видалення елемента з кінця списку.
     */
    public void removeLast() throws Exception {
        DoublyNode<T> last = tail;

        if (listEmpty()) {
            throw new Exception("Список порожнiй.");
        }
        else if (count == 1) {
            head = null;
            tail = null;
            count = 0;
        }
        else {
            tail = last.getPrevious();
            tail.setNext(null);
            --count;
        }
    }

    /*
     * В завданні до практики сказано створити індексатор,
     * але засобами Java це неможливо зробити, тому я написав
     * наступні два методи.
     *
     * Встановлення елемента за індексом.
     */
    public void setElementByIndex(T value, int index) throws Exception {
        /* Перевірка, чи не виходе заданий індекс за допустимі межі. */
        if (index < 0 || index > count) {
            throw new Exception("Iндекс поза межами дiапазону!");
        }
        /* Якщо список порожній, елемент додається на початок. */
        else if (listEmpty()) {
            addFirst(value);
        }
        /* Додавання елемента по індексу. */
        else {
            DoublyNode<T> current = head;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
            current.setData(value);
        }
    }

    /*
     * Отримання елемента за індексом.
     */
    public T getElementByIndex(int index) throws Exception {
        /* Перевірка, чи не виходе заданий індекс за допустимі межі. */
        if (index < 0 || index > count) {
            throw new Exception("Iндекс поза межами дiапазону!");
        }

        /* Отримання значення елементу за індексом. */
        DoublyNode<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getData();
    }

    /*
     * В завданні практики сказано створити властивість читання довжини списку,
     * але засобами Java це неможливо створити, тому це звичайний геттер.
     *
     * Метод отримання довини списку.
     */
    public int getCount() {
        return count;
    }

    /*
     * Перевірка на порожність списку.
     */
    public boolean listEmpty() { return count == 0; }

    /*
     * Метод, який повертає ітератор і довзоля проходитись
     * по списку в циклі foreach.
     */
    @Override
    public Iterator<T> iterator() {
        return new DoublyLinkedListIterator<>(head);
    }

    /*
     * Клас, який реалізує ітератор, за допомогою якого
     * відбувається ітерування по елементам списку.
     */
    public static class DoublyLinkedListIterator<E> implements Iterator<E> {
        DoublyNode<E> current;

        public DoublyLinkedListIterator(DoublyNode<E> head) {
            current = head;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            E value = current.getData();
            current = current.getNext();
            return value;
        }
    }

    /*
     * Метод, який повертає ітератор і довзоля проходитись
     * по списку в зворотньому порядку.
     */
    public Iterator<T> backIterator() {
        return new BackDoublyLinkedListIterator<>(tail);
    }

    /*
     * Клас, який реалізує ітератор, за допомогою якого
     * відбувається ітерування по елементам списку в зворотньому порядку.
     */
    public static class BackDoublyLinkedListIterator<E> implements Iterator<E> {
        DoublyNode<E> current;

        public BackDoublyLinkedListIterator(DoublyNode<E> tail) {
            current = tail;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            E value = current.getData();
            current = current.getPrevious();
            return value;
        }
    }

    /*
     * Допоміжний метод зміни місцями двох значень елементів списку.
     */
    public void swap(DoublyLinkedList<T> list, int i, int j) throws Exception {
        try {
            T temp = list.getElementByIndex(i);
            list.setElementByIndex(list.getElementByIndex(j), i);
            list.setElementByIndex(temp, j);
        }
        catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    /*
     * Метод сортування списку за спаданням методом шейкер-сортування.
     */
    public void sort() throws Exception {
        /* Перевірка чи не порожній список. */
        if (listEmpty()) {
            throw new Exception("Список порожнiй!");
        }

        /* Якщо елементи в списку не типу Student, сортування не буде відбуватись. */
        if (getElementByIndex(0) instanceof Student) {
            int left = 0;
            int right = count - 1;

            /* Сортування. */
            while (left < right) {
                for (int i = left; i < right; i++) {
                    if (((Student)getElementByIndex(i)).getHeight() < ((Student)getElementByIndex(i + 1)).getHeight()) {
                        swap(this, i, i + 1);
                    }
                }
                right--;

                for (int i = right; i > left; i--) {
                    if (((Student)getElementByIndex(i - 1)).getHeight() < ((Student)getElementByIndex(i)).getHeight()) {
                        swap(this, i - 1, i);
                    }
                }
                left++;
            }
        }
    }

    /*
     * Метод пошуку студентів відмінників з ідеальною вагою.
     */
    public DoublyLinkedList<T> searchOfElement() throws Exception {
        /* Перевірка чи не порожній список. */
        if (listEmpty()) {
            throw new Exception("Список порожнiй!");
        }

        /*
         * Якщо елементи в списку не типу Student, пошук не буде відбуватись.
         * В такому випадку повертається порожній список. Якщо збігів не має,
         * також повертається пустий список.
         */
        if (getElementByIndex(0) instanceof Student) {
            DoublyNode<T> current = head;
            var searchResult = new DoublyLinkedList<T>();

            while (current != null) {
                boolean idealWeight = ((Student)current.getData()).getHeight() - 110 == ((Student)current.getData()).getWeight();
                if (idealWeight) {
                    searchResult.addFirst(current.getData());
                }
                current = current.getNext();
            }

            /* Повернення значення студента/ів, якщо був збіг. */
            if (!searchResult.listEmpty()) {
                return searchResult;
            }
        }
        return new DoublyLinkedList<>();
    }
}