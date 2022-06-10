using System.Collections.Generic;
using System.Collections;
using System;
using ListLib;

namespace Practice_1
{
    internal class CommandsHandler
    {
        // Команда для виводу списока всіх доступних команд.
        public static void CommandHelp()
        {
            Console.WriteLine(" Доступні команди:");
            Console.WriteLine(" /addfirst\t- додати елемент в початок списку.");
            Console.WriteLine(" /clear\t\t- очистити консоль.");
            Console.WriteLine(" /end\t\t- закінчити роботу.");
            Console.WriteLine(" /help\t\t- вивести список доступних команд.");
            Console.WriteLine(" /length\t- вивести довжину списку.");
            Console.WriteLine(" /removelast\t- видалити елемент з кінця списку.");
            Console.WriteLine(" /search\t- знайти у списку студентів відмінників, що займаються спортом.");
            Console.WriteLine(" /show\t\t- вивести список.");
            Console.WriteLine(" /showback\t- вивести список у зворотньому порядку.");
            Console.WriteLine(" /sort\t\t- сортувати список за середнім балом.");
        }

        // Команда для додавання елемента в початок списку.
        public static void CommandAddFirst(ref DoublyLinkedList<Student> students)
        {
            // Ввід імені студента з перевіркою на правильність.
            Names name;
            Console.WriteLine(" Введіть ім'я студента");
            Console.WriteLine(" (0 - Андрій, " +
                                "1 - Віка, " +
                                "2 - Денис, " +
                                "3 - Іван, " +
                                "4 - Інна, " +
                                "5 - Ліза, " +
                                "6 - Микола, " +
                                "7 - Мирослав, " +
                                "8 - Сергій, " +
                                "9 - Сніжана):");  
            while (true)
            {
                // Використання блоку try/catch потрібне, щоб запобігти випадку,
                // коли рядок типу будь-чого, окрім числових значень, пробує конвертуватись в enum.
                try
                {
                    Console.Write(" ");
                    name = (Names)Convert.ToInt16(Console.ReadLine());
                    if (0 <= name && name < Names.Count)
                    {
                        break;
                    }
                    else
                    {
                        throw new Exception();
                    }
                }
                catch
                {
                    Console.WriteLine(" Введено невірне значення! Спробуйте ще раз:");
                }                
            }

            // Ввід середнього балу студента з перевіркою на правильність.
            double gpa;
            Console.WriteLine(" Введіть середній бал (від 0 до 100 в форматі XX,X):");
            while (true)
            {
                // Використання блоку try/catch потрібне, щоб запобігти випадку,
                // коли рядок типу "XX.X" пробує конвертуватись в дійсний тип.
                // Дана дія визиває виключення FormatException.
                try
                {
                    Console.Write(" ");
                    gpa = Convert.ToDouble(Console.ReadLine());

                    if (0 <= gpa && gpa <= 100)
                    {
                        break;
                    }
                    else
                    {
                        throw new Exception();
                    }
                }
                catch
                {
                    Console.WriteLine(" Введено невірне значення! Спробуйте ще раз:");
                }                
            }

            // Ввід логічно значення залежного від того чи займається студент спортом, чи ні,
            // з перевіркою на правильність.
            bool sport;
            Console.WriteLine(" Виберіть чи займається студент спортом (Y/N):");
            while (true)
            {
                Console.Write(" ");
                string answer = Console.ReadLine().ToLower();
                if (answer == "y" || answer == "n")
                {
                    sport = answer == "y" ? true : false;
                    break;
                }
                else
                {
                    Console.WriteLine(" Введено невірне значення! Спробуйте ще раз:");
                }
            }

            // Створення нового екземпляру Student да додавання його до списку.
            var student = new Student(name, gpa, sport);
            students.AddFirst(student);

            // Виводить оновлений список на екран.
            CommandShow(ref students);
        }

        // Команда для виводу довжини списку.
        public static void CommandLenght(ref DoublyLinkedList<Student> students)
        {
            Console.WriteLine(" Довжина списку: {0}", students.Count);
        }

        // Команда для видалення останнього елементу зі списку.
        public static void CommandRemoveLast(ref DoublyLinkedList<Student> students)
        {
            try
            {
                students.RemoveLast();
            }
            catch (Exception exception)
            {
                Console.WriteLine($" {exception.Message}");
            }

            // Виводить оновлений список на екран.
            CommandShow(ref students);
        }

        // Команда для пошуку і виводу на консоль студента відмінника, який займається спортом.
        public static void CommandSearch(ref DoublyLinkedList<Student> students)
        {
            try
            {
                DoublyLinkedList<Student> searchResult = students.SearchOfElement();
                Console.WriteLine(" Результати пошуку:");
                CommandShow(ref searchResult);
            }
            catch (Exception exception)
            {
                Console.WriteLine($" {exception.Message}");
            }            
        }

        // Команда для виводу списку на консоль у вигляді таблиці.
        public static void CommandShow(ref DoublyLinkedList<Student> students)
        {
            Console.Write("-".PadLeft(42, '-'));
            Console.Write("\r \n");
            Console.WriteLine(" |  {0,8}  |  {1,5}  |  {2,5}  |", "Ім'я", "Середній бал", "Спорт");
            Console.Write("-".PadLeft(42, '-'));
            Console.Write("\r \n");

            if (students.ListEmpty)
            {
                Console.WriteLine(" |  {0,8}  |  {1,12}  |  {2,5}  |", "", "", "");
            }
            else
            {
                foreach (var student in students)
                {
                    Console.WriteLine(" |  {0,8}  |  {1,12}  |  {2,5}  |", student.Name, student.Gpa, student.Sport);
                }
            }            
            Console.Write("-".PadLeft(42, '-'));
            Console.Write("\r \n");
        }

        // Команда для виводу списку на консоль у вигляді таблиці у зворотному порядку.
        public static void CommandShowBack(ref DoublyLinkedList<Student> students)
        {
            Console.Write("-".PadLeft(42, '-'));
            Console.Write("\r \n");
            Console.WriteLine(" |  {0,8}  |  {1,5}  |  {2,5}  |", "Ім'я", "Середній бал", "Спорт");
            Console.Write("-".PadLeft(42, '-'));
            Console.Write("\r \n");

            if (students.ListEmpty)
            {
                Console.WriteLine(" |  {0,8}  |  {1,12}  |  {2,5}  |", "", "", "");
            }
            else
            {
                foreach (var student in students.BackEnumerator())
                {
                    Console.WriteLine(" |  {0,8}  |  {1,12}  |  {2,5}  |", student.Name, student.Gpa, student.Sport);
                }
            }
            Console.Write("-".PadLeft(42, '-'));
            Console.Write("\r \n");
        }

        // Командля для сортування списку.
        public static void CommandSort(ref DoublyLinkedList<Student> students)
        {
            try
            {
                students.Sort();
            }
            catch (Exception exception)
            {
                Console.WriteLine($" {exception.Message}");
            }
            
            CommandShow(ref students);
        }
    }    
}
