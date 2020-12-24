package com.psg.objectboard.batch;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {

        //ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");


        System.out.println( "Hello World!");
        Properties vProp = new Properties();
        InputStream vInputStream = null;
        try {
            vInputStream = App.class.getResourceAsStream("/app.properties");
            vProp.load(vInputStream);
        } finally {
            if (vInputStream != null){
                vInputStream.close();
            }
        }
        System.out.println("Application version : " + vProp.getProperty("propert-name", "?"));

        /*BussinessTypeController controller = new BussinessTypeController();

        BussinessTypeDto bussinessTypeDtoSet = controller.getBussinessTypeList();
        for (BussinessTypeDto bussinessTypeDto:bussinessTypeDtoSet.getBussinessTypeDtoSet()){
            System.out.println(bussinessTypeDto.getBtDescription());
        }
        System.out.println(bussinessTypeDtoSet.getBussinessTypeDtoSet().size());*/

        /*String uri = "mongodb+srv://prueba:prueba@cluster0.mbngf.mongodb.net/test?retryWrites=true&w=majority";
        MongoClientURI mongoClientURI = new MongoClientURI(uri);
        MongoClient mongoClient = new MongoClient(mongoClientURI);

        MongoDatabase mongoDatabase = mongoClient.getDatabase("objectboard_db");
        MongoCollection mongoCollection = mongoDatabase.getCollection("prueba");

        Document document = new Document("name", "Daeshan");
        document.append("Sex", "male");
        document.append("Age", "21");
        document.append("Race", "Africa");

        mongoCollection.insertOne(document);*/

        /*AssignmentsDAO cod = new AssignmentsDAO();
        ArrayList<AssignmentsConsultVO> asigna = null;
        cod.setDataUser("6C0REY2T");
        cod.setDataPassword("vFpvUVvGPojdI27");

        asigna = cod.getListAssignmentsConsult("masterUser_bussinessUnit_bu_bis_code = " + Integer.parseInt("2"));

        String uri = "mongodb+srv://prueba:prueba@cluster0.mbngf.mongodb.net/test?retryWrites=true&w=majority";
        MongoClientURI mongoClientURI = new MongoClientURI(uri);
        MongoClient mongoClient = new MongoClient(mongoClientURI);

        MongoDatabase mongoDatabase = mongoClient.getDatabase("objectboard_db");
        MongoCollection mongoCollection = mongoDatabase.getCollection("prueba");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.equals(asigna);

        System.out.println(objectMapper.equals(asigna));
        /*Document document = new Document("name", "Daeshan");
        document.append("Sex", "male");
        document.append("Age", "21");
        document.append("Race", "Africa");

        mongoCollection.insertOne(document);*/

        /*mongoCollection.insertOne(objectMapper.equals(asigna));*/

    }
}
