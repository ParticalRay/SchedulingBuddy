/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alpha;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Customers;
import model.schemaAdmin;
//int Customer_ID, String Customer_Name, String Address, String Postal_Code, String Phone
/**
 *
 * @author jonat
 */
public class SchedulingBuddy extends Application{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Customers test1 = new Customers(1, "Jon Makepeace", "USA Bitches", "98312", "3365969982");
        Customers test2 = new Customers(2, "Wyatt the fag", "USA Bitches", "98312", "some ohio number");
        Customers test3 = new Customers(3, "Mystic the Ginger", "Northern Forest of US(Canada)", "canadian zip", "some canadian number");
        Customers test4 = new Customers(4, "Awon the majestic", "North Africa", "654789", "1234567890");
        schemaAdmin.addCust(test1);
        schemaAdmin.addCust(test2);
        schemaAdmin.addCust(test3);
        schemaAdmin.addCust(test4);
        System.out.println(test1);
        System.out.println(test2);
        System.out.println(test3);
        System.out.println(test4);
        
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/views/UserLogin.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Scheduling Buddy");
        stage.show();

    }
    
}
