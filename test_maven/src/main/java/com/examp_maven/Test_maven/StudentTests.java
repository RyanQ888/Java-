package com.examp_maven.Test_maven;


import org.junit.Test;

import static com.mongodb.assertions.Assertions.assertFalse;
import static org.junit.Assert.assertEquals;

public class StudentTests {

    @Test
    public void addStudentTest() {
        Student student = new Student(3, 15, "Smith", "John", "F");
        StudentConnector studentConnector = new StudentConnector("mongodb://localhost:27017/", "Students");

        studentConnector.addStudent(student);
        try {
            studentConnector.getStudent(student.getName());
        } catch (Exception ex) {
            assertFalse(true);
            System.err.println(ex);
        }

    }

    @Test
    public void deleteStudentTest() {
        Student student = new Student(3, 15, "Smith", "John", "F");
        StudentConnector studentConnector = new StudentConnector("mongodb://localhost:27017/", "Students");
        studentConnector.deleteStudent(student);
        try {
            studentConnector.getStudent(student.getName());
        } catch (Exception ex) {
            assertFalse(true);
            System.err.println(ex);
            System.out.println("No Student found - delete student is working");
        }
    }

    @Test
    public void updateStudentTest() {
        Student student = new Student(3, 15, "Smith", "John", "F");
        StudentConnector studentConnector = new StudentConnector("mongodb://localhost:27017/", "Students");
        studentConnector.updateStudent(student);
    }
}



