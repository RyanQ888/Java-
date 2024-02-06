package com.examp_maven.Test_maven;

public class Student {
    public int year = 0, age = 0;
    public String surname = "", name = "", studentClass = "";

    public Student() {

    }

    public Student(int y, int a, String s, String n, String sc) {
        year = y;
        age = a;
        surname = s;
        name = n;
        studentClass = sc;
    }

    public int getYear() {
        return year;
    }

    public int getAge() {
        return age;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getStudentClass() {
        return studentClass;
    }
}
