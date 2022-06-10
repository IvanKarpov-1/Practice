using System;
using ListLib;

namespace Practice_1
{
    internal class UserInterface
    {
        // Метод, який запускає користувацький інтерфейс.
        public static void Start(ref DoublyLinkedList<Student> students)
        {
            // Перший вивід всіх можливих команд.
            CommandsHandler.CommandHelp();
            Console.WriteLine();
            string input;

            while (true)
            {
                // Очікування введення команди.
                Console.Write(" ");
                input = Console.ReadLine().ToLower();

                // Перевірка на співпадіння з можливими командами.
                if (input == "/addfirst")
                {
                    CommandsHandler.CommandAddFirst(ref students);
                    Console.WriteLine();
                    continue;
                }
                if (input == "/clear")
                {
                    Console.Clear();
                    continue;
                }
                if (input == "/end")
                {
                    break;
                }
                if (input == "/help")
                {
                    CommandsHandler.CommandHelp();
                    Console.WriteLine();
                    continue;
                }
                if (input == "/length")
                {
                    CommandsHandler.CommandLenght(ref students);
                    Console.WriteLine();
                    continue;
                }
                if (input == "/removelast")
                {
                    CommandsHandler.CommandRemoveLast(ref students);
                    Console.WriteLine();
                    continue;
                }
                if (input == "/search")
                {
                    CommandsHandler.CommandSearch(ref students);
                    Console.WriteLine();
                    continue;
                }
                if (input == "/show")
                {
                    CommandsHandler.CommandShow(ref students);
                    Console.WriteLine();
                    continue;
                }
                if (input == "/showback")
                {
                    CommandsHandler.CommandShowBack(ref students);
                    Console.WriteLine();
                    continue;
                }
                if (input == "/sort")
                {
                    CommandsHandler.CommandSort(ref students);
                    Console.WriteLine();
                    continue;
                }

                // Якщо введена команда не співпадає з можливими, прохання ввести команду ще раз.
                Console.Clear();
                Console.WriteLine(" Команда ввведена невірно. Спробуйте ще раз.");
                CommandsHandler.CommandHelp();
            }
        }
    }
}
