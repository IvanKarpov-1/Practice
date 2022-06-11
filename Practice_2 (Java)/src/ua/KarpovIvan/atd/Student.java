package ua.KarpovIvan.atd;

public class Student {
    private final Names name; /* Ім'я сдунта. */
    private final int height; /* Вага студента. */
    private final double weight; /* Зріст студента. */

    public Student() { name = Names.None; height = 0; weight = 0; }
    public Student(Names name, int height, double weight) {
        this.name = name;
        this.height = height;
        this.weight = weight;
    }

    /*
     * Методи отримання значень.
     */
    public Names getName() {
        return name;
    }
    public int getHeight() {
        return height;
    }
    public double getWeight() {
        return weight;
    }
}