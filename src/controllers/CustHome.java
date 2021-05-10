/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Customers;
import model.schemaAdmin;
import alpha.SchedulingBuddy;
import java.sql.Statement;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import model.Appointments;
import java.sql.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;
import model.Type;

/**
 * FXML Controller class
 * Main fxml controller. All fxml will return to this form.
 */
public class CustHome implements Initializable {

    Stage stage;
    Parent scene;
    
    @FXML
    private TableColumn<Customers, Integer> IDCol;
    @FXML
    private TableColumn<Customers, String> NameCol;
    @FXML
    private TableColumn<Customers, String> AddressCol;
    @FXML
    private TableColumn<Customers, String> ZipCol;
    @FXML
    private TableColumn<Customers, String> PhoneCol;
    
    @FXML
    private TableView<Customers> CustTable;
    
    @FXML
    private Button createCustButton;
    @FXML
    private Button ModifyCustButton;
    @FXML
    private Button DeleteCustButton;
    @FXML
    private Button createApptButton;
    @FXML
    private TableView<Appointments> apptTable;
    @FXML
    private Button modifyApptButton;
    @FXML
    private Button exitButton;
    @FXML
    private TableColumn<Appointments, Integer> apptIDCol;
    @FXML
    private TableColumn<Appointments, String> titleCol;
    @FXML
    private TableColumn<Appointments, String> descCol;
    @FXML
    private TableColumn<Appointments, String> locCol;
    @FXML
    private TableColumn<Appointments, Integer> contactCol;
    @FXML
    private TableColumn<Appointments, String> typeCol;
    @FXML
    private TableColumn<Appointments, String> startDateCol;
    @FXML
    private TableColumn<Appointments, String> endDateCol;
    @FXML
    private TableColumn<Appointments, Integer> custIDCol;
    @FXML
    private Button deleteApptButton;
    @FXML
    private Text titleText;
    @FXML
    private RadioButton defaultTableButton;
    @FXML
    private ToggleGroup views;
    @FXML
    private RadioButton weeklyTableButton;
    @FXML
    private RadioButton monthlyTableButton;
    @FXML
    private TableView<Type> typeApptTable;
    @FXML
    private TableColumn<Type, String> monthCol;
    @FXML
    private TableColumn<Type, Integer> countCol;
    @FXML
    private Button contactScheduleButton;
    @FXML
    private TableColumn<Type, String> countTypeCol;
    
    private ObservableList<Type> typeList = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.Preload information into the tables to be observated and modified.
     * @param ul 
     * @param rb 
     */
    @Override
    public void initialize(URL ul, ResourceBundle rb) {
        schemaAdmin.getObservableListOfCust().clear();
        schemaAdmin.getObservableListOfAppt().clear();
        
        try{
            SchedulingBuddy.connectAndUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        CustTable.setItems(schemaAdmin.getObservableListOfCust());
        IDCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
        NameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        AddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        ZipCol.setCellValueFactory(new PropertyValueFactory<>("postal"));
        PhoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        
        apptTable.setItems(schemaAdmin.getObservableListOfAppt());
        apptIDCol.setCellValueFactory(new PropertyValueFactory<>("Appointment_ID"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("Title"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("Description"));
        locCol.setCellValueFactory(new PropertyValueFactory<>("Location"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("Contact_ID"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("Type"));
        startDateCol.setCellValueFactory(new PropertyValueFactory<>("Start"));
        endDateCol.setCellValueFactory(new PropertyValueFactory<>("End"));
        custIDCol.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));
        
        
        try{
            String serverName = "//wgudb.ucertify.com:3306/WJ07jSy";
            String user = "U07jSy";
            String pass = "53689047995";
            String dbName = "WJ07jSy";
            String port = "3306";
            String url = "jdbc" + ":mysql:" + serverName;


            Connection conn = DriverManager.getConnection(url, user, pass);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT count(distinct Type) as 'Number of appointments by type',\n" +
                "Type, Start\n" +
                "from appointments\n" +
                "group by Type,Start;");
            
            while (rs.next()){
                int count = rs.getInt("Number of appointments by type");
                String type = rs.getString("Type");
                Date startDate = rs.getDate("Start");
                String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
                String month = monthNames[startDate.getMonth()];
                System.out.println("Count: " + count);
                System.out.println("Type: " + type);
                System.out.println("Month: " + month);
                Type newEntry = new Type(count, type, month);
                typeList.add(newEntry);
            }
        }catch(SQLException e){
            
        }
        
        typeApptTable.setItems(typeList);
        monthCol.setCellValueFactory(new PropertyValueFactory<>("Month"));
        countCol.setCellValueFactory(new PropertyValueFactory<>("Count"));
        countTypeCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        
        String langInUse = Locale.getDefault().toString().split("_")[0];
        String region = Locale.getDefault().toString().split("_")[1];
        if (langInUse.equals("fr")){
            titleText.setText("Planification");
            IDCol.setText("identifiant");
            NameCol.setText("Nom");
            AddressCol.setText("adresse");
            ZipCol.setText("code postal");
            PhoneCol.setText("téléphoner");
            createCustButton.setText("créer");
            ModifyCustButton.setText("modifier");
            DeleteCustButton.setText("effacer");
            createApptButton.setText("créer un rendez-vous");
            modifyApptButton.setText("modifier le rendez-vous");
            apptIDCol.setText("identifiant");
            titleCol.setText("Titre");
            descCol.setText("la description");
            locCol.setText("emplacement");
            typeCol.setText("taper");
            startDateCol.setText("date de début");
            endDateCol.setText("date de fin");
            contactCol.setText("Contacts");
            exitButton.setText("sortir");
            custIDCol.setText("identifiant");
            deleteApptButton.setText("supprimer un rendez-vous");
        }
        LocalTime lt = ZonedDateTime.now(ZoneId.systemDefault()).toLocalTime();
        
        schemaAdmin.getObservableListOfAppt().forEach(app -> {
            //System.out.println("Appointment start time: " + app.getStart().split(" ")[1]);
            LocalTime startTime = LocalTime.parse(app.getStart().split(" ")[1]);
            int minTillStart = (int)startTime.until(lt, ChronoUnit.MINUTES);
            
            if (minTillStart <= 15){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                if (langInUse.equals("fr")){
                    alert.setContentText("Rendez-vous à venir dans quelques %s minutes.".replace("%s", minTillStart*-1+"")
                        + " Identification de rendez-vous: " + app.getAppointment_ID()
                        + " Début du rendez-vous " + app.getStart()
                        + " Fin de rendez-vous: " + app.getEnd());
                    alert.show();
                }else{
                alert.setContentText("Appointment upcoming in %s minutes.".replace("%s", minTillStart*-1+"")
                        + " Appointment ID: " + app.getAppointment_ID()
                        + " Appointment start " + app.getStart()
                        + "End time: " + app.getEnd());
                alert.show();
                }
            }
        });
        
        /*
        for (Appointments app : schemaAdmin.getObservableListOfAppt()){
            System.out.println("Appointment start time: " + app.getStart().split(" ")[1]);
            LocalTime startTime = LocalTime.parse(app.getStart().split(" ")[1]);
            int minTillStart = (int)startTime.until(lt, ChronoUnit.MINUTES);
            
            if (minTillStart <= 15){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                if (langInUse.equals("fr")){
                    alert.setContentText("Rendez-vous à venir dans quelques %s minutes.".replace("%s", minTillStart*-1+"")
                        + " Identification de rendez-vous: " + app.getAppointment_ID()
                        + " Début du rendez-vous " + app.getStart()
                        + " Fin de rendez-vous: " + app.getEnd());
                    alert.show();
                }else{
                alert.setContentText("Appointment upcoming in %s minutes.".replace("%s", minTillStart*-1+"")
                        + " Appointment ID: " + app.getAppointment_ID()
                        + " Appointment start " + app.getStart()
                        + "End time: " + app.getEnd());
                alert.show();
                }
            }
            
        }
        */
    }    
       
    /**
     * Create cust will move the form to the create cust fxml 
     * @param event create button pressed
     * @throws IOException exception being thrown 
     */
    @FXML
    private void createCust(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/views/CreateCust.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    
    /**
     * Modify cust will take the highlighted customer object and send it the 
     *  the modify cust fxml to be modified.
     * @param event modify button pressed
     * @throws IOException exception being thrown
     */
    @FXML
    private void modifyCust(ActionEvent event) throws IOException {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/ModifyCust.fxml"));
            loader.load();

            ModifyCustController MCController = loader.getController();
            Customers cust = CustTable.getSelectionModel().getSelectedItem();
            MCController.setCustomer(cust);
            schemaAdmin.getObservableListOfCust().remove(cust);
            stage = (Stage)((Button)event.getSource()).getScene().getWindow(); 
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select a customer to modify");
            alert.showAndWait();
        }
    }

    /**
     * get started will create the connection to the database and return 
     *  the connection object
     * @return connection object
     * @throws SQLException sql issue being thrown
     */
    public Connection getStarted() throws SQLException{
        try{
            String serverName = "//wgudb.ucertify.com:3306/WJ07jSy";
            String user = "U07jSy";
            String pass = "53689047995";
            String dbName = "WJ07jSy";
            String port = "3306";
            String url = "jdbc" + ":mysql:" + serverName;
            Connection conn = DriverManager.getConnection(url, user, pass);
            return conn;
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Database connection info is wrong");
            alert.showAndWait();
            Connection c = null;
            return c;
        }
    }
    
    /**
     * delete cust will take the highlighted customer object and delete it
     *  from the database and the list maintained on the application. 
     * @param event delete button clicked
     */
    @FXML
    private void deleteCust(ActionEvent event) {
        try{
            Customers cust = CustTable.getSelectionModel().getSelectedItem();
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            Optional<ButtonType> result = confirm.showAndWait();
            if(result.get() == ButtonType.OK){
                deleteCustInDb(cust);
            }
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select a customer to delete");
            alert.showAndWait();
        }
        
    }

    /**
     * create appt will take the highlighted customer object and send it
     *  to the appointment create fxml to create an appointment
     * @param event create appointment button
     * @throws IOException fxml loading
     */
    @FXML
    private void createAppt(ActionEvent event) throws IOException {
        
        try{
            Customers c = CustTable.getSelectionModel().getSelectedItem();
            if (c==null){
                NullPointerException nullPointer = new NullPointerException(); 
                throw nullPointer;
            }
            System.out.println("Cust Home got: " + c);
        
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/AppointmentCreate.fxml"));
            loader.load();
            AppointmentCreateController apptCController = loader.getController();
            apptCController.getCustID(c);
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
            
            }catch(Exception e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please select a customer before creating an appointment");
                alert.showAndWait();
        }
        
        
    }
    
    /**
     * delete cust in db will take the customer object and use its id to find the 
     *  matching customer and delete it and the appointment assigned to it. 
     * @param c customer object
     */
    private void deleteCustInDb(Customers c){
        Connection conn = null;
        PreparedStatement stmt = null;
        String deleteCust = "delete from customers where Customer_ID = ?";
        String deleteAppt = "delete from appointments where Customer_ID = ?";
        try{
            conn = getStarted();
            System.out.println(conn);
            stmt = conn.prepareStatement(deleteAppt);
            stmt.setInt(1, c.getID());
            stmt.execute();
            
            stmt.close();
            conn.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
        try{
            conn = getStarted();
            System.out.println(conn);
            stmt = conn.prepareStatement(deleteCust);
            stmt.setInt(1, c.getID());
            stmt.execute();
            schemaAdmin.getObservableListOfCust().remove(c);
            stmt.close();
            conn.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    /**
     * delet appt will take the appointment object and delete the matching appt
     *  inside the db using the appt id
     * @param a appointment id
     */
    private void deleteAppt(Appointments a){
        String deleteAppt = "delete from appointments where Appointment_ID = ?";
        schemaAdmin.getObservableListOfAppt().remove(a);
        try{
            Connection conn = getStarted();
            System.out.println(conn);
            PreparedStatement stmt = conn.prepareStatement(deleteAppt);
            stmt.setInt(1, a.getAppointment_ID());
            stmt.execute();
            
            stmt.close();
            conn.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * modify appt will take the selected appointment and preload the fxml with
     *  the appointment objects information. 
     * @param event modify appointment button pressed
     * @throws IOException fxml loading
     */
    @FXML
    private void modifyAppt(ActionEvent event) throws IOException {
        try{
            Appointments appt = apptTable.getSelectionModel().getSelectedItem();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/ModifyAppointment.fxml"));
            loader.load();

            ModifyAppointmentController MAController = loader.getController();

            MAController.setCurrentAppt(appt);


            stage = (Stage)((Button)event.getSource()).getScene().getWindow(); 
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please select an appointment to modify");
                alert.showAndWait();
        }
        
        
    }

    /**
     * Exit will close the application
     * @param event exit button pressed
     */
    @FXML
    private void exit(ActionEvent event) {
        System.exit(0);
    }

    /**
     * delete appt will take the selected appointment object and send it to the 
     *  deleteAppt method to delete the corresponding appointment in the db. 
     * @param event delete appointment button is pressed
     */
    @FXML
    private void deleteAppt(ActionEvent event) {
        try{
            Appointments apt = apptTable.getSelectionModel().getSelectedItem();
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            Optional<ButtonType> result = confirm.showAndWait();
            if(result.get() == ButtonType.OK){
                deleteAppt(apt);
            }
            
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select an appointment to delete");
            alert.showAndWait();
        }
    }

    @FXML
    private void defaultTable(ActionEvent event) {
    }

    @FXML
    private void weeklyTable(ActionEvent event) {
    }

    @FXML
    private void monthlyTable(ActionEvent event) {
    }

    @FXML
    private void contactSchedule(ActionEvent event) {
    }
}
