namespace ListLib
{
    // Перечислювач для імен.
    public enum Names
    {
        Андрій, Віка, Денис, Іван, Інна, Ліза, Микола, Мирослав, Сергій, Сніжана, Count, None
        // None - використовується для позначення відсутості імені.
        // Count - використовується для позначення кількості імен.
    }

    // Клас що реалізує тип даних.
    public class Student
    {
        public Names Name { get; set; } // Ім'я студента.
        public double Gpa { get; set; } // Середній бал студента.
        public bool Sport { get; set; } // Чи займається студент спортом.

        public Student() { Name = Names.None; Gpa = 0; Sport = false; }
        public Student(Names name, double gpa, bool sport) { Name = name; Gpa = gpa; Sport = sport; }
    }
}
