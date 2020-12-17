package com.psg.objectboard.model.service;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.psg.objectboard.model.own.ownsEntity.classDAO.AssignmentsDAO;
import com.psg.objectboard.model.own.ownsEntity.classVO.OtherVO.AssignmentsConsultVO;
import org.bson.Document;

import java.util.ArrayList;

public class MongoDB {

    public void prueba(){

        AssignmentsDAO cod = new AssignmentsDAO();
        ArrayList<AssignmentsConsultVO> asigna = null;
        cod.setDataUser("6C0REY2T");
        cod.setDataPassword("vFpvUVvGPojdI27");

        asigna = cod.getListAssignmentsConsult("masterUser_bussinessUnit_bu_bis_code = " + Integer.parseInt("2"));

        String uri = "mongodb+srv://prueba:prueba@cluster0.mbngf.mongodb.net/test?retryWrites=true&w=majority";
        MongoClientURI mongoClientURI = new MongoClientURI(uri);
        MongoClient mongoClient = new MongoClient(mongoClientURI);

        MongoDatabase mongoDatabase = mongoClient.getDatabase("objectboard_db");
        MongoCollection mongoCollection = mongoDatabase.getCollection("prueba");

        /*Document document = new Document("name", "Daeshan");
        document.append("Sex", "male");
        document.append("Age", "21");
        document.append("Race", "Africa");

        mongoCollection.insertOne(document);*/

        mongoCollection.insertOne(asigna);

    }

}
