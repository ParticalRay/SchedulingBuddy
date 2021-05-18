
package model;

import java.util.Date;

/**
 * Users is used to get automatic assigning to customers and modifications to 
 *  them. 
 * @author jonat
 */
public class Users {
    private int User_ID; //Auto assigned
    private String User_Name;
    private String Password;
    private Date Created_Date;
    private String Created_By;
    private Date Last_Update;
    private String Last_Updated_By;
    
    /**
     * Constructor
     * @param User_ID
     * @param User_Name
     * @param Password
     * @param Created_Date
     * @param Created_By
     * @param Last_Update
     * @param Last_Updated_By
     */
    public Users(int User_ID,String User_Name, String Password, Date Created_Date, String Created_By, Date Last_Update, String Last_Updated_By) {
        this.User_ID = User_ID;
        this.User_Name = User_Name;
        this.Password = Password;
        this.Created_Date = Created_Date;
        this.Created_By = Created_By;
        this.Last_Update = Last_Update;
        this.Last_Updated_By = Last_Updated_By;
    }
    
    /**
     *
     * @return
     */
    public int getUser_ID() {
        return User_ID;
    }

    /**
     *
     * @return
     */
    public String getUser_Name() {
        return User_Name;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return Password;
    }

    /**
     *
     * @return
     */
    public Date getCreated_Date() {
        return Created_Date;
    }

    /**
     *
     * @return
     */
    public String getCreated_By() {
        return Created_By;
    }

    /**
     *
     * @return lastUpdate
     */
    public Date getLast_Update() {
        return Last_Update;
    }

    /**
     *
     * @return lastUpdateBy
     */
    public String getLast_Updated_By() {
        return Last_Updated_By;
    }

    /**
     *
     * @param User_ID
     */
    public void setUser_ID(int User_ID) {
        this.User_ID = User_ID;
    }
    
    /**
     *
     * @param User_Name
     */
    public void setUser_Name(String User_Name) {
        this.User_Name = User_Name;
    }

    /**
     *
     * @param Password
     */
    public void setPassword(String Password) {
        this.Password = Password;
    }

    /**
     *
     * @param Created_Date
     */
    public void setCreated_Date(Date Created_Date) {
        this.Created_Date = Created_Date;
    }

    /**
     *
     * @param Created_By
     */
    public void setCreated_By(String Created_By) {
        this.Created_By = Created_By;
    }

    /**
     *
     * @param Last_Update
     */
    public void setLast_Update(Date Last_Update) {
        this.Last_Update = Last_Update;
    }

    /**
     *
     * @param Last_Updated_By
     */
    public void setLast_Updated_By(String Last_Updated_By) {
        this.Last_Updated_By = Last_Updated_By;
    }
    
    
}
