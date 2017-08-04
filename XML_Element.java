/**
 * Created by Samuel Hild on 7/30/2017.
 *
 * Provides Formatting Information for xml Elements
 */
public class XML_Element {
    //Main Vars
    private String element_name;
    private Attribute attributes[];
    private XML_Element child_elements[];
    private String element_data;
    private String prefix;
    private boolean ischild;

    //Element Formatters
    private final String rchar = "^^";                     //Replace Character used for formatting
    private final String open_tag = "<^^>";
    private final String close_tag = "</^^>";

    public XML_Element(String...argv){
        /*Constructor
         * when new XML_Element is created name and data are set from argv
         * argv represents two arguments
         *  1. Title of the Element *Required
         *  2. data text stored in the Element *Optional
         */
        element_name = argv[0];
        if (argv.length >= 2) element_data = argv[1];
    }

    public void addChild(XML_Element child) {
        //adds a child Element to the parent element
        if(child_elements == null){
            child_elements = new XML_Element[1];                    //If there are no children, Initialize the variable
            child_elements[0] = child;
        }else{
            XML_Element[] c_buffer = child_elements;
            child_elements = new XML_Element[child_elements.length+1];
            for (int i = 0; i < c_buffer.length; i++) child_elements[i] = c_buffer[i];
            child_elements[c_buffer.length] = child;                //add new child to the end of child array
        }
    }

    public void addAttribute(String title, String value) {
        //adds attributes to elements
        int length_var;

        if (attributes == null) {
            attributes = new Attribute[1];                      //If there are no attributes, Initialize the variable
        } else {
            Attribute[] a_buffer = attributes;                  //buffer created for copying the array
            attributes = new Attribute[attributes.length + 1];
            for (int i = 0; i < a_buffer.length; i++) attributes[i] = a_buffer[i];
        }
        length_var = attributes.length;
        attributes[length_var - 1] = new Attribute();
        attributes[length_var - 1].setTitle(title);
        attributes[length_var - 1].setValue(value);
    }

    private String assembleElement(){
        /*Assembles full element
        * Includes all child elements
        * Returns full Element as a String
        */
        String rtrn_str = getOpenTag() + "\n";

        //for each child element call function recursively
        for (XML_Element child_e : child_elements){
            rtrn_str += child_e.assembleElement();
        }

        rtrn_str += getCloseTag() + "\n";
        return null;
    }

    //getters and setters
    //TODO add getters and setters

    //checks if Element is a child of another element
    public boolean isChild () { return ischild; }

    //sets Element as a child
    public void setChild(boolean child_bool) { ischild = child_bool; }

    public String getOpenTag(){
        //returns opening tag
        String o_tag;
        o_tag = open_tag.replaceAll(rchar, getTitle() + " " + getAttributes());
        return o_tag;
    }

    public String getCloseTag(){
        //returns closing tag
        String c_tag;
        c_tag = close_tag.replaceAll(rchar, getTitle());
        return c_tag;
    }

    public XML_Element[] getChildren() { return child_elements; }

    public void setTitle(String title) {
        element_name = title;
    }

    public void setDataText(String data) {
        element_data = data;
    }

    public Attribute[] getAttributes() {
        return attributes;
    }

    public String getTitle(){ return element_name; }

    public String getFullElement() {
        return assembleElement();
    }

    public String getData(){ return element_data; }
}

class Attribute {
    //allows for attribute objects to be created
    private String title;
    private String value;

    private String attribute_text = title + "='" + value + "'";

    private void update_text() {
        attribute_text = title + "='" + value + "'";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String t) {
        title = t;
        update_text();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String v) {
        value = v;
        update_text();
    }

    public String getAttributeText() {
        return attribute_text;
    }
}
