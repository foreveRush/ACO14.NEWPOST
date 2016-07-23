package newpost.test;

import newpost.db.AppDataContainer;
import newpost.db.InitDB;

import java.io.IOException;

/**
 * Created by macaque on 16.07.2016.
 */
public class TestInitDB {

    public static void main(String[] args) {
        String location = "resources/db.json";
        AppDataContainer appDataContainer = new AppDataContainer();
        InitDB.initDB(appDataContainer);
        InitDB.saveDBToFileAsJson(appDataContainer, location);

        try {
            String res = InitDB.loadDB(location);
            System.out.println("load was ok");
            System.out.println(res);
            AppDataContainer appDataContainer1 = new AppDataContainer();
            appDataContainer1 = InitDB.loadDBAsJson(location);
            System.out.println(appDataContainer1.getTickets().get(0).asString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
