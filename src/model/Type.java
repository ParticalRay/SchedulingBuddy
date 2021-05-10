
package model;

/**
 *
 * @author jonat
 */
public class Type {
    private int count;
    private String name;
    private String month;
    
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
