/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Statement;
import java.sql.*;
import java.time.LocalDate;
import java.util.Locale;
import javafx.scene.text.Text;
import model.Contacts;
import model.Customers;

/**
 * FXML Controller class
 *
 * @author jonat
 */
public class ModifyAppointmentController implements Initializable {
    
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
    private Appointments appt;
    @FXML
    private Text aptTitleText;
    @FXML
    private Text titleText;
    @FXML
    private Text idTextTrans;
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


    /**
     * Initializes the controller class.
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
            titleText.setText("modificateur de rendez-vous");
            idTextTrans.setText("identifiant de rendez-vous");
            aptTitleText.setText("Titre");
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
     *
     * @param c
     */
    public void getCustID(Customers c){
        currentCustID = c;
    }
    
    /**
     *
     * @param appt
     */
    public void setCurrentAppt(Appointments appt){
        this.appt = appt;
        
        apptID.setText(getCurrentAppt().getAppointment_ID() + "");
        titleBox.setText(getCurrentAppt().getTitle());
        DesBox.setText(getCurrentAppt().getDescription());
        localBox.setText(getCurrentAppt().getLocation());
        typeBox.setText(getCurrentAppt().getType());
        System.out.println(getCurrentAppt().getStart());
        System.out.println(getCurrentAppt().getEnd());
        
        String[] temp = getCurrentAppt().getStart().split(" ");
        LocalDate startD = LocalDate.parse(temp[0]);
        int startTime = Integer.parseInt(temp[1].split(":")[0]);
        int startT = Integer.parseInt(temp[1].split(":")[0]);
        
        String[] temp1 = getCurrentAppt().getEnd().split(" ");
        LocalDate endD = LocalDate.parse(temp1[0]);
        int endTime = Integer.parseInt(temp1[1].split(":")[0]);
        int endT = Integer.parseInt(temp1[1].split(":")[0]);
        
        startDate.setValue(startD);
        endDate.setValue(endD);
        if (startT >=8 && startT <=11){
            startCombo.setValue(startTime + " Am");
        }else{
            startCombo.setValue(startTime - 12 + " Pm");
        }
        if (endT >=8 && endT <=11){
            endCombo.setValue(endTime + " Am");
        }else{
            endCombo.setValue(endTime - 12 + " Pm");
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
        int contactID = getCurrentAppt().getContact_ID();
        System.out.println("Contact ID found: " + contactID);
        String contactName = null;
        for (Contacts i:schemaAdmin.getObservableListOfContacts()){
            if (i.getContact_ID() == contactID){
                contactName = i.getContact_Name();
                System.out.println("Contact name found: " + contactName);
                break;
            } 
        }
        contactsCombo.getSelectionModel().select(contactName);
        
    }

    /**
     *
     * @return
     */
    public Appointments getCurrentAppt(){
        return appt;
    }
    

    @FXML
    private void confirmAppointment(ActionEvent event) throws IOException {
        //'2021-04-18 01:34:42' year, month, day, then time
        
        String originalStart = startDate.getValue().toString();
        String originalEnd = endDate.getValue().toString();
        /*
        String[] temp1 = originalStart.split("-");
        String year = temp1[0];
        String month = temp1[1];
        String day = temp1[2];
        */

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
        for (Contacts i:schemaAdmin.getObservableListOfContacts()){
            if (i.getContact_Name().equals(contactName)){
                contactsID = i.getContact_ID();
                break;
            }
            else{
                System.out.println("Something went wrong");
            }
        }
        
        int userID = schemaAdmin.getUser().getUser_ID();

        Appointments appt = new Appointments(title,desc,loc,type, startDateTime,
                endDateTime,createdBy,getCurrentAppt().getCustomer_ID(),userID,contactsID);
        schemaAdmin.addAppts(appt);
        
        
        PreparedStatement stmt;
        String insert = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, "
                    + "End = ?, Last_Updated_By = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? "
                    + "WHERE Appointment_ID = ?" ;
        
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
            stmt.setInt(8, getCurrentAppt().getCustomer_ID());
            stmt.setInt(9, userID);
            stmt.setInt(10, contactsID);
            stmt.setInt(11, getCurrentAppt().getAppointment_ID());
            System.out.println(stmt);
            stmt.execute();
        }catch(SQLException e){
            System.out.println(e);
        }
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/views/CustHome.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
        
    }

    @FXML
    private void resetForm(ActionEvent event) {
    }

    @FXML
    private void cancelAppointment(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/views/CustHome.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    
    /**
     *
     * @return
     * @throws SQLException
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
