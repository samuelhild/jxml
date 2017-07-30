/**
 * Created by Samuel Hild on 7/30/2017.
 *
 * Provides Formatting Information for xml Elements
 */
public class XML_Element {
    private static String element_name;
    private Attribute attributes[];
    private String element_data;
    private XML_Element nested_elements[];
    private char prefix;

    public void printElement(){
        //TODO add function
    }
    public void addAttribute(String title, String value){
        Attribute[] a_buffer = attributes;
        attributes = new Attribute[attributes.length + 1];
        for (int i = 0; i < attributes.length; i++) attributes[i] = a_buffer[i];
        attributes[a_buffer.length].setTitle(title);
        attributes[a_buffer.length].setValue(value);
    }

    //getters and setters



}class Attribute{
    //allows for attribute objects to be created
    private String title;
    private String value;

    private String attribute_text = title + "='"+value+"'";

    public String getTitle(){
        return title;
    }

    public void setTitle(String t){
        title = t;
    }

    public String getValue(){
        return value;
    }

    public void setValue(String v){
        value = v;
    }

    public String getAttributeText(){
        return attribute_text;
    }

}
