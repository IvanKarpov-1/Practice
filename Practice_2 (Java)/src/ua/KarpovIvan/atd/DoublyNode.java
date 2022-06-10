package ua.KarpovIvan.atd;

public class DoublyNode<T> {
    /* Дані, які зберігаються. */
    private T data;
    /* Посилання на попередній вузол. */
    private DoublyNode<T> previous;
    /* Посилання на наступний вузол. */
    private DoublyNode<T> next;

    public DoublyNode() { data = null; }
    public DoublyNode(T data) { this.data = data; }

    /*
     * Методи встановлення значень.
     */
    public void setData(T data) {
        this.data = data;
    }
    public void setPrevious(DoublyNode<T> previous) {
        this.previous = previous;
    }
    public void setNext(DoublyNode<T> next) {
        this.next = next;
    }
    /*
     * Методи отримання значень.
     */
    public T getData() {
        return data;
    }
    public DoublyNode<T> getPrevious() {
        return previous;
    }
    public DoublyNode<T> getNext() {
        return next;
    }
}