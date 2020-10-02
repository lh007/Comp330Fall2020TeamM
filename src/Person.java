import java.util.*;
import java.io.*;

public class Person {
    //Global Variables
    private String lastName;
    private String firstName;
    private String suffix;
    private String birthDate;
    private String birthPlace;
    private String deathDate;
    private String deathPlace;
    private String parentRelationID;
    private HashMap<String,ArrayList<String>> relationships; //a list of relationships associated with each person, key=type, value=people

    //Default Constructor
    public Person() {
        lastName=null;
        firstName=null;
        suffix=null;
        birthDate=null;
        birthPlace=null;
        deathDate=null;
        deathPlace=null;
        parentRelationID=null;
        relationships=null;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String val) {
        lastName=val;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String val) {
        firstName=val;
    }
    public String getSuffix() {
        return suffix;
    }
    public void setSuffix(String val) {
        suffix=val;
    }
    public String getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(String val) {
        birthDate=val;
    }
    public String getBirthPlace() {
        return birthPlace;
    }
    public void setBirthPlace(String val) {
        birthPlace=val;
    }
    public String getDeathDate() {
        return deathDate;
    }
    public void setDeathDate(String val) {
        deathDate=val;
    }
    public String getDeathPlace() {
        return deathPlace;
    }
    public void setDeathPlace(String val) {
        deathPlace=val;
    }
    public String getParentRelationID() {
        return parentRelationID;
    }
    public void setParentRelationID(String val) {
        parentRelationID=val;
    }
    public HashMap<String,ArrayList<String>> getRelationships() {
        return relationships;
    }
    public void setRelationships(String type,String person) {
        if (relationships.containsKey(type)) {
            relationships.get(type).add(person);
        } else {
            relationships.put(type,new ArrayList<String>());
            relationships.get(type).add(person);
        }

    }
}
