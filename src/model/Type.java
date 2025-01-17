
package model;

import java.sql.Date;

/**
 * Type object is used to count the amount of meeting types based on each month
 *  greater than 0
 * @author jonat
 */
public class Type {
    private int count;
    private String name;
    private String month;
    
    /**
     * 
     * @param c
     * @param name
     * @param s 
     */
    public Type(int c, String name, String s){
        this.count = c;
        this.name = name;
        this.month = s;
    }

 
    public int getCount() {
        return count;
    }

    public String getName() {
        return name;
    }

    public String getMonth() {
        return month;
    }
    
    
   
    
}
