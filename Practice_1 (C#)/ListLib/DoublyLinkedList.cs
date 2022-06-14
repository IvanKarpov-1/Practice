using System.Collections.Generic;
using System.Collections;
using System;

namespace ListLib
{
    // Клас, що реалізує двоспрямований список.
    public class DoublyLinkedList<T> : IEnumerable<T>
    {
        private DoublyNode<T> _head; // Початок списку.
        private DoublyNode<T> _tail; // Кінець списку.
        private int _count = 0;      // Кількість елементів в списку.

        // Додавання елемента в початок списку.
        public void AddFirst(T data)
        {
            DoublyNode<T> node = new DoublyNode<T>(data);
            DoublyNode<T> temp = _head;

            node.Next = temp;
            _head = node;

            if (ListEmpty)
            {
                _tail = _head;
            }
            else
            {
                temp.Previous = node;
            }

            _count++;
        }

        // Видалення елемента з кінця списку.
        public void RemoveLast()
        {
            DoublyNode<T> last = _tail;

            if (ListEmpty)
            {
                throw new Exception("Список порожній.");
            }
            else if (_count == 1)
            {
                _head = null;
                _tail = null;
                _count = 0;
            }
            else
            {
                _tail = last.Previous;
                _tail.Next = null;
                --_count;
            }
        }

        // Індексатор читання\зміни значення, заданого порядковим номером вузла.
        public T this[int index]
        {
            get
            {
                // Перевірка, чи не виходе заданий індекс за допустимі межі.
                if (index < 0 || index > _count)
                {
                    throw new IndexOutOfRangeException("Індекс поза межами діапазону!");
                }

                // Отримання значення елементу за індексом.
                DoublyNode<T> current = _head;
                for (int i = 0; i < index; i++)
                {
                    current = current.Next;
                }
                return current.Data;
            }
            set
            {
                // Перевірка, чи не виходе заданий індекс за допустимі межі.
                if (index < 0 || index > _count)
                {
                    throw new IndexOutOfRangeException("Індекс поза межами діапазону!");
                }
                // Якщо список порожній, елемент додається на початок.
                else if (ListEmpty)
                {
                    AddFirst(value);
                }
                // Додавання елемента по індексу.
                else
                {
                    DoublyNode<T> current = _head;
                    for (int i = 0; i < index; i++)
                    {
                        current = current.Next;
                    }
                    current.Data = value;
                }
            }
        }

        // Властивість довжини списку.
        public int Count { get => _count; }

        // Перевірка на порожність списку.
        public bool ListEmpty => _count == 0;

        // Метод ітерації списку.
        IEnumerator IEnumerable.GetEnumerator()
        {
            return ((IEnumerable)this).GetEnumerator();
        }

        // Метод ітерації списку для наступного елемента.
        IEnumerator<T> IEnumerable<T>.GetEnumerator()
        {
            DoublyNode<T> current = _head;
            while (current != null)
            {
                yield return current.Data;
                current = current.Next;
            }
        }

        // Метод ітерації списку в зворотньому порядку.
        public IEnumerable<T> BackEnumerator()
        {
            DoublyNode<T> current = _tail;
            while (current != null)
            {
                yield return current.Data;
                current = current.Previous;
            }
        }

        // Допоміжний метод зміни місцями двох значень елементів списку.
        public void Swap(DoublyLinkedList<T> list, int i, int j)
        {
            T temp = list[i];
            list[i] = list[j];
            list[j] = temp;
        }

        // Метод сортування списку за спаданням методом шейкер-сортування.
        public void Sort()
        {
            // Перевірка чи не порожній список.
            if (ListEmpty)
            {
                throw new Exception("Список порожній!");
            }

            // Якщо елементи в списку не типу Student, сортування не буде відбуватись.
            if (this[0] is Student)
            {
                int left = 0;
                int right = _count - 1;

                // Сортування.
                while (left < right)
                {
                    for (int i = left; i < right; i++)
                    {
                        if ((this[i] as Student).Gpa < (this[i + 1] as Student).Gpa)
                        {
                            Swap(this, i, i + 1);
                        }
                    }
                    right--;

                    for (int i = right; i > left; i--)
                    {
                        if ((this[i - 1] as Student).Gpa < (this[i] as Student).Gpa)
                        {
                            Swap(this, i - 1, i);
                        }
                    }
                    left++;
                }
            }
        }

        // Метод пошуку студентів відмінників що займаються спортом.
        public DoublyLinkedList<T> SearchOfElement()
        {
            // Перевірка чи не порожній список.
            if (ListEmpty)
            {
                throw new Exception("Список порожній!");
            }

            // Якщо елементи в списку не типу Student, нічого не буде шукатись,
            // а також повернеться стандатне значення для T.
            if (this[0] is Student)
            {
                DoublyNode<T> current = _head;
                var searchResult = new DoublyLinkedList<T>();

                while (current != null)
                {
                    if ((current.Data as Student).Gpa >= 90 && (current.Data as Student).Sport == true)
                    {
                        searchResult.AddFirst(current.Data);
                    }
                    current = current.Next;
                }
                
                // Повертає значення студентів, якщо був збіг, інакше: "Елемент не знайдено!".
                if (searchResult != null)
                {
                    return searchResult;
                }
                else
                {
                    throw new Exception("Елемент не знайдено!");
                }
            }
            return default;
        }
    }
}