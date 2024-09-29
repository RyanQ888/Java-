package com.examp_maven.Test_maven;

import com.mongodb.client.*;
import org.bson.Document;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentConnector {
    public MongoDatabase database;
    public String collectionName = "";

    public StudentConnector(String url, String stringCollection) {

        collectionName = stringCollection;

        try {
            MongoClient mongoClient = MongoClients.create(url);
            database = mongoClient.getDatabase("com_vtx");

        }
        catch (Exception exception) {
           System.err.println("There is an error" + exception);
        }

    }

    public List<Student> getAllStudents() {

        ArrayList<Student> returns = new ArrayList<>();
        FindIterable<Document> iterable = database.getCollection(collectionName).find();

        for (Document document : iterable) {

            Student student = new Student(
                    document.getInteger("year"),
                    document.getInteger("age") ,
                    document.getString("surname") ,
                    document.getString("name"),
                    document.getString("studentClass")
            );

            returns.add(student);
        }

        return returns;
    }
    public Student getStudent(String studentName) {

        Document query = new Document("name", studentName);
        FindIterable<Document> iterable = database.getCollection(collectionName).find(query);

        Document document = iterable.first();

        return new Student(
                document.getInteger("year"),
                document.getInteger("age") ,
                document.getString("surname") ,
                document.getString("name"),
                document.getString("studentClass")
            );
    }
    public void addStudent(Student student) {
        Document studentDocument = new Document()

                .append("year", student.getYear())
                .append("age", student.getAge())
                .append("surname", student.getSurname())
                .append("name", student.getName())
                .append("studentClass", student.getStudentClass());

        database.getCollection(collectionName).insertOne(studentDocument);
    }
    public void deleteStudent(Student student) {
        Document studentDocument = new Document()

                .append("year", student.getYear())
                .append("age", student.getAge())
                .append("surname", student.getSurname())
                .append("name", student.getName())
                .append("studentClass", student.getStudentClass());

        database.getCollection(collectionName).deleteOne(studentDocument);
    }
    public void updateStudent(Student student) {
        Document query = new Document("name", student.getName());

        Document studentDocument = new Document()

                .append("year", student.getYear())
                .append("age", student.getAge())
                .append("surname", student.getSurname())
                .append("name", student.getName())
                .append("studentClass", student.getStudentClass());

        database.getCollection(collectionName).updateOne(query, studentDocument);

    }
}
