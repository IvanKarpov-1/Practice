package ua.KarpovIvan.atd;

public class Student {
    private Names name; /* Ім'я сдунта. */
    private int height; /* Вага студента. */
    private double weight; /* Зріст студента. */

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