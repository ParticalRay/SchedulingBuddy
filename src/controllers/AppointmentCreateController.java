/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Appointments;
import model.schemaAdmin;
import static java.lang.Integer.parseInt;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.util.Locale;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.text.Text;
import model.Contacts;
import model.Customers;

/**
 * FXML Controller class
 * Appointment create controller will create an appointment object using the
 *  given information in the text fields.
 */
public class AppointmentCreateController implements Initializable {
    
    Stage stage;
    Parent scene;

    @FXML
    private TextField apptID;
    @FXML
    private TextField titleBox;
    @FXML
    private TextField DesBox;
    @FXML
    private TextField localBox;
    @FXML
    private TextField typeBox;
    @FXML
    private DatePicker startDate;
    @FXML
    private DatePicker endDate;
    @FXML
    private Button confirmButton;
    @FXML
    private Button resetButton;
    @FXML
    private Button cancelButton;
    
    private Customers currentCustID;
    @FXML
    private ComboBox<String> startCombo;
    @FXML
    private ComboBox<String> endCombo;
    @FXML
    private ComboBox<String> contactsCombo;
    @FXML
    private Text idTextTrans;
    @FXML
    private Text titleTextTrans;
    @FXML
    private Text descTextTrans;
    @FXML
    private Text localTextTrans;
    @FXML
    private Text typeTextTrans;
    @FXML
    private Text startDateTextTrans;
    @FXML
    private Text endDateTextTrans;
    @FXML
    private Text contactTextTrans;
    @FXML
    private Text startTimeTextTrans;
    @FXML
    private Text endTimeTextTrans;
    @FXML
    private Text aptTitleTextTrans;
    


    /**
     * Initializes the controller class.preloads information into the fxml, including time frames.
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        startCombo.getItems().addAll("8 Am","9 Am","10 Am","11 Am","12 Pm","1 Pm",
                    "2 Pm","3 Pm", "4 Pm", "5 Pm", "6 Pm","7 Pm","8 Pm","9 Pm");
        endCombo.getItems().addAll("9 Am","10 Am","11 Am","12 Pm","1 Pm",
                    "2 Pm","3 Pm", "4 Pm", "5 Pm","6 Pm","7 Pm","8 Pm","9 Pm", "10 Pm");
        String langInUse = Locale.getDefault().toString().split("_")[0];
        String region = Locale.getDefault().toString().split("_")[1];
        if (langInUse.equals("fr")){
            titleTextTrans.setText("modificateur de rendez-vous");
            idTextTrans.setText("identifiant de rendez-vous");
            aptTitleTextTrans.setText("Titre");
            descTextTrans.setText("la description");
            localTextTrans.setText("emplacement");
            typeTextTrans.setText("taper");
            startDateTextTrans.setText("date de début");
            endDateTextTrans.setText("date de fin");
            contactTextTrans.setText("Contacts");
            startTimeTextTrans.setText("Heure de début");
            endTimeTextTrans.setText("heure de fin");
            confirmButton.setText("confirmer");
            resetButton.setText("réinitialiser");
            cancelButton.setText("Annuler");
        }
        
        try {
            Connection conn = getStarted();
            String grabContacts = "select * from contacts";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(grabContacts);
            while (rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString("Contact_Name");
                String email = rs.getString("Email");
                Contacts c = new Contacts(id, name, email);
                schemaAdmin.addContacts(c);
                contactsCombo.getItems().add(name);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }   
    
    /**
     * getCustID is used to get the customer object from the main fxml and 
     *  connect it to the new appointment object.
     * @param c customer object
     */
    public void getCustID(Customers c){
        currentCustID = c;
    }

    /**
     * confirm appointment will take in the text fields and create an appointment
     *  using the information given. 
     * @param event confirm button pressed
     * @throws IOException fxml loading
     */
    @FXML
    private void confirmAppointment(ActionEvent event) throws IOException {

        try{
            String originalStart = startDate.getValue().toString();
            String originalEnd = endDate.getValue().toString();
            int startTime = parseInt((String)startCombo.getValue().split(" ")[0]);
            int endTime = parseInt((String)endCombo.getValue().split(" ")[0]);
            String title = titleBox.getText();
            String desc = DesBox.getText();
            String loc = localBox.getText();
            String type = typeBox.getText();
            
            if (startCombo.getValue().split(" ")[1].equals("Pm")){
                startTime +=12;
            }
            if (endCombo.getValue().split(" ")[1].equals("Pm")){
                endTime +=12;
            }
            String startDateTime = originalStart + " " + startTime + ":00:00";
            String endDateTime = originalEnd + " " + endTime + ":00:00"; //Combine date and time can split to get info
            String createdBy = schemaAdmin.getUser().getUser_Name();
            String contactName = contactsCombo.getValue();
            
            int contactsID = 0;
            Contacts savedContact = null;
            for (Contacts i:schemaAdmin.getObservableListOfContacts()){
                if (i.getContact_Name().equals(contactName)){
                    contactsID = i.getContact_ID();
                    savedContact = i;
                    break;
                }
                else{
                    System.out.println("Something went wrong");
                }
            }
            for (Appointments appt : schemaAdmin.getObservableListOfAppt()){
                if(appt.getStart().equals(startDateTime) && 
                    appt.getEnd().equals(endDateTime) && 
                    savedContact.getContact_ID() == appt.getContact_ID()){
                    System.out.println("Found an appointment already at this time");
                }
            }
            
            

            int userID = schemaAdmin.getUser().getUser_ID();

            Appointments appt = new Appointments(title,desc,loc,type, startDateTime,
                    endDateTime,createdBy,currentCustID.getID(),userID,contactsID);
            schemaAdmin.addAppts(appt);


            PreparedStatement stmt;
            String insert = "INSERT INTO appointments (Title, Description, Location, Type, Start, "
                        + "End, Created_By, Last_Updated_By, Customer_ID, User_ID, Contact_ID) "
                        + "VALUES (?,?,?,?,?,?,?,?,?,?,?)";

            try{
                Connection conn = getStarted();
                stmt = conn.prepareStatement(insert);
                stmt.setString(1, title);
                stmt.setString(2, desc);
                stmt.setString(3, loc);
                stmt.setString(4, type);
                stmt.setString(5, startDateTime);
                stmt.setString(6, endDateTime);
                stmt.setString(7, createdBy);
                stmt.setString(8, createdBy);
                stmt.setInt(9, currentCustID.getID());
                stmt.setInt(10, userID);
                stmt.setInt(11, contactsID);
                stmt.execute();
            }catch(SQLException e){
                System.out.println(e);
            }
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/views/CustHome.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Title, Description, Location, Type, start and "
                    + "end must be a string. Make sure to select something for each box.");
            alert.showAndWait();
        }
        
    }

    /**
     * reset form is used to reset the text fields to default values 
     * @param event reset button pressed
     */
    @FXML
    private void resetForm(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Are you sure you would like to cancel?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK){
            titleBox.clear();
            DesBox.clear();
            localBox.clear();
            typeBox.clear();
            startDate.getEditor().clear();
            endDate.getEditor().clear();
            startCombo.getEditor().clear();
            endCombo.getEditor().clear();
            contactsCombo.getEditor().clear();
        }
    }

    /**
     * Cancel appointment will cancel the create appointment and return to the
     *  main fxml
     * @param event cancel button pressed
     * @throws IOException fxml loading
     */
    @FXML
    private void cancelAppointment(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Are you sure you would like to cancel?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK){
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/views/CustHome.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
        
    }
    
    /**
     * get started will create the connection to the database and return 
     *  the connection object
     * @return connection object
     * @throws SQLException sql issue being thrown
     */
    public Connection getStarted() throws SQLException{
        String serverName = "//wgudb.ucertify.com:3306/WJ07jSy";
        String user = "U07jSy";
        String pass = "53689047995";
        String dbName = "WJ07jSy";
        String port = "3306";
        String url = "jdbc" + ":mysql:" + serverName;
        Connection conn = DriverManager.getConnection(url, user, pass);
        return conn;
    }
    
}
