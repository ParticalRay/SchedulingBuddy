
package alpha;

import java.util.Locale;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Customers;
import model.schemaAdmin;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.TimeZone;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Region;
import model.Appointments;


/**
 * Main javafx application. Initially starts the application and load up the 
 *  login form. 
 * @author Jonathon Makepeace
 */
public class SchedulingBuddy extends Application{
   
    private LocalDate ld = ZonedDateTime.now(ZoneId.systemDefault()).toLocalDate();
    
    
    /**
     * Main starts the application and loads method connectAndUpdate and Launch.
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        
        connectAndUpdate();
        launch(args);
        
        
    }
    
   /**
    * connectAndUpdate will connect to the db and load all customers and 
    *   appointments to their objects. 
    * @throws SQLException connecting to the db
    */
    public static void connectAndUpdate() throws SQLException{
        String serverName = "//wgudb.ucertify.com:3306/WJ07jSy";
        String user = "U07jSy";
        String pass = "53689047995";
        String dbName = "WJ07jSy";
        String port = "3306";
        String url = "jdbc" + ":mysql:" + serverName;
        
        
        Connection conn = DriverManager.getConnection(url, user, pass);
        Statement stmt = conn.createStatement();
        
        ResultSet rs = stmt.executeQuery("select * from customers");
        
        while (rs.next()){
            int id = rs.getInt(1);
            String name = rs.getString("Customer_Name");
            String address = rs.getString("Address");
            String zip = rs.getString("Postal_Code");
            String phone = rs.getString("Phone");
            Date createDate = rs.getDate("Create_Date");
            String createBy = rs.getString("Created_By");
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdateBy = rs.getString("Last_Updated_By");
            int divisionID = rs.getInt("Division_ID");
            
            
            Customers cx = new Customers(id, name, address, zip, phone, createDate, createBy, lastUpdate, lastUpdateBy, divisionID);
            
            schemaAdmin.addCust(cx);
        }
        
        ResultSet rsAppt = stmt.executeQuery("select * from appointments");
        while (rsAppt.next()){
            int id = rsAppt.getInt("Appointment_ID");
            String title = rsAppt.getString("Title");
            String desc = rsAppt.getString("Description");
            String loc = rsAppt.getString("Location");
            String type = rsAppt.getString("Type");
            String start = rsAppt.getString("Start");
            String end = rsAppt.getString("End");
            int custID = rsAppt.getInt("Customer_ID");
            int userID = rsAppt.getInt("User_ID");
            int contactID = rsAppt.getInt("Contact_ID");
            String createDate = rsAppt.getString("Create_Date");
            String createdBy = rsAppt.getString("Created_By");
            String lastUpdate = rsAppt.getString("Last_Update");
            String lastUpdateBy = rsAppt.getString("Last_Updated_By");
            schemaAdmin.getTypeCollection().put(type, 0);
            Appointments appt = new Appointments(id,title,desc,loc,type,start,end,createDate,createdBy,lastUpdate,lastUpdateBy,custID,userID,contactID);
            schemaAdmin.addAppts(appt);
        }
    }
   
    /**
     * start will start the application and load up userlogin to verify the user.
     * @param stage initial stage given to the application
     * @throws Exception fxml loading
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/views/UserLogin.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Scheduling Buddy");
        stage.show();
    }
}
