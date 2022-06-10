using System;
using ListLib;

namespace Practice_1
{
    internal class Program
    {
        static void Main(string[] args)
        {
            // Для коректного читання і відображення ураїнської мови.
            Console.OutputEncoding = System.Text.Encoding.Default;
            Console.InputEncoding = System.Text.Encoding.Default;

            // Створення списку для подальшої роботи з ним.
            var students = new DoublyLinkedList<Student>();

            // Запуск користувацького інтерфейсу.
            UserInterface.Start(ref students);
        }
    }
}
