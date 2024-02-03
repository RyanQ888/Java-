package com.examp_maven.Test_maven;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.bson.json.JsonObject;
import java.util.List;
import java.util.ArrayList;


import java.util.Objects;
import java.util.Scanner;

public class test_maven {
    public static MongoDatabase establish_db(String connection_string){
        MongoClient mongoClient = MongoClients.create(connection_string);
        return mongoClient.getDatabase("com_vtx");
    }

    public static void retrieve_data(MongoCollection<Document> collection){
        Scanner reader = new Scanner(System.in);
        System.out.println("What is your query field?");
        String field = reader.nextLine();
        System.out.println("What is your value for the associated field?");
        String value = reader.nextLine();
        try {
            Document query = new Document(field, value);
            MongoCursor<Document> cursor = collection.find(query).iterator();
            while (cursor.hasNext()) {
                Document document = cursor.next();
                System.out.println(document.toJson());
            }
        }
        catch (Exception e) {
            System.out.println("Could not find the specified document");
        }
    }

    public static void insert_data(MongoCollection<Document> collection){
        System.out.println("What data do you want to insert? (in json format)");
        Scanner reader = new Scanner(System.in);
        String json = reader.nextLine();
        Document document = Document.parse(json);
        collection.insertOne(document);

    }

    public static void update_data(MongoCollection<Document> collection){
        Scanner reader = new Scanner(System.in);
        System.out.println("What is your query field?");
        String query_str = reader.nextLine();
        System.out.println("What would you like to update this document with?");
        String update_field_str =  reader.nextLine();
        Document query = Document.parse(query_str);
        Document update_field = Document.parse(update_field_str);
        Document update = new Document("$set", update_field);
        collection.updateOne(query, update);
    }

    public static void delete_data(MongoCollection<Document> collection){
        Scanner reader = new Scanner(System.in);

        List<Document> students = collection.find().into(new ArrayList<>());

        for(Document student : students) {
            System.out.println(student.toJson());
        }
        System.out.println("What document would you like to delete?");
        String query_string = reader.nextLine();

        Document query = new Document("name", query_string);
        collection.deleteOne(query);
        System.out.println(query.toJson());
    }

    public static void main(String[] args) {
        String db_url = "mongodb://localhost:27017/";
        MongoDatabase database = establish_db(db_url);

        for (String name : database.listCollectionNames()) {
            System.out.println(name);
        }
        Scanner reader = new Scanner(System.in);

        System.out.println("What collection do you want to access?");
        String collection_name = reader.nextLine();
        MongoCollection<Document> collection = database.getCollection(collection_name);

        System.out.println("What operation do you want to do within this collection?");
        System.out.println("Type: Retrieve, Insert, Update or Delete");
        String operation = reader.nextLine();

        switch (operation){
            case "Retrieve":
                retrieve_data(collection);
                break;
            case "Insert":
                insert_data(collection);
                break;
            case "Update":
                update_data(collection);
                break;
            case "Delete":
                delete_data(collection);
                break;
        }
    }
}
