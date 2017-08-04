/**
 * Created by Samuel Hild on 7/30/2017.
 *
 * Provides Formatting Information for xml Elements
 */
public class XML_Element {
    //Main Vars
    private String element_name;
    private Attribute attributes[];
    private String element_data;
    private XML_Element child_elements[];
    private String prefix;
    private String raw_element;

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

    private String genRawElement() {
        //a function to generate the element as it will be outputed to xml
        //also Generates all child elements
        String openning_tag_text;
        openning_tag_text = element_name;
        if (attributes != null) {

            for (int i = 0; i < attributes.length; i++) {
                openning_tag_text = openning_tag_text + " " + attributes[i].getAttributeText();
            }
        }

        String raw_element_tmp = open_tag.replace(rchar, openning_tag_text);

        if (element_data != null) raw_element_tmp = raw_element_tmp + "\t" + element_data;

        if (child_elements != null) {
            //only run if there are child elements
            for (int i = 0; i < child_elements.length; i++) {
                raw_element_tmp = raw_element_tmp + "\n\t" + child_elements[i].getElement();
            }
        }
        raw_element_tmp = raw_element_tmp + close_tag.replace(rchar, element_name);
        return raw_element_tmp;
    }


    //getters and setters
    //TODO add getters and setters

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

    public String getElement() {
        raw_element = genRawElement();
        return raw_element;
    }

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
