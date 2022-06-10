namespace ListLib
{
    // Клас, який представляє вузол в двоспрямованому списку.
    internal class DoublyNode<T>
    {
        // Дані, які зберігаються.
        public T Data { get; set; }
        // Посилання на попередній вузол.
        public DoublyNode<T> Previous { get; set; }
        // Посилання на наступний вузол.
        public DoublyNode<T> Next { get; set; }

        // Конструктори.
        public DoublyNode() { Data = default(T); }
        public DoublyNode(T data) { Data = data; }

    }
}
