package co.edu.udea.talentotech.programacion.intermedio.api_rest;

public class User {
    private static int idCounter = 0;
    private int id;
    private String name;
    private String email;
    private int age;

    public User() {
        this.id = ++idCounter;
    }

    public User(String name, String email, int age) {
        super();
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
