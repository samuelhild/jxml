/**
 * Created by Samuel Hild on 7/30/2017.
 *
 * Provides Formatting Information for xml Elements
 */
public class XML_Element {
    //Main Vars
    private String element_name;
    private Attribute attributes[];
    private XML_Element children[];
    private String element_data;
    private String prefix;

    //Element Formatters
    private final String rchar = "^^";                     //Replace Character used for formatting
    private final String open_tag = "<^^>";
    private final String close_tag = "</^^>";

    public XML_Element(String...e_values){
        /*Construtor
        *Requires: Element Name
        *Optional: Element data
        * */
        element_name = e_values[0];
        element_data = "";
        if(e_values.length > 1) element_data = e_values[1];
    }public void addChild(XML_Element e_child){
        /*Adds child element
        * Child elements added to other Elements not the Document
        * */

        //Check if children[] has been initialized
        if (children == null){
            //if null then initialize
            children = new XML_Element[1];
            children[0] = e_child;
        } else {
            //Create buffer and append to array
            XML_Element[] buffer = children;
            children = new XML_Element[buffer.length+1];
            for(int i = 0; i < buffer.length; i++){
                //Copy buffer back to children[]
                children[i] = buffer[i];
            }
            children[children.length - 1] = e_child;
        }
    }public void addAttribute(String title, String value){
        //adds attributes to Elements

        //check if attributes is initialized
        if(attributes == null){
            //if null then initalize
            attributes = new Attribute[1];
            attributes[0] = new Attribute();
            attributes[0].setTitle(title);
            attributes[0].setValue(value);
        }else{
            //use buffer to append array
            Attribute[] buffer = attributes;
            attributes = new Attribute[buffer.length+1];
            for(int i = 0; i < buffer.length; i++){
                attributes[i] =  buffer[i];
            }
            attributes[attributes.length-1] = new Attribute();
            attributes[attributes.length-1].setTitle(title);
            attributes[attributes.length-1].setValue(value);
        }
    }public String printElement() {
        //returns entire element as string
        String e_string = "";
        String a_text = "";

        if (attributes != null) {
            for (int i = 0; i < attributes.length; i++) {
                a_text +=  " " + attributes[i].getTitle() + " = '" + attributes[i].getValue() + "'";
            }
        }

        e_string += open_tag.replace(rchar, element_name + a_text);
        if (children != null){
            e_string += "\n";

            for (int i = 0; i < children.length; i++) {
                //adds all Child Elements
                e_string += "\n\t"+children[i].printElement();
            }

            e_string += "\n";
        }
        e_string += element_data + close_tag.replace(rchar, element_name)+"\n";

        return e_string;
    }

    public String getOpenTag(){
        //returns opening tag
        String o_tag;
        String a_text = "";

        //assemble attribute text
        for (Attribute a : getAttributes()){
            a_text += " " + a.getTitle() +  " = '" + a.getValue() + "'";
        }
        o_tag = open_tag.replace(rchar, (getTitle() + a_text));
        return o_tag;
    }

    public String getCloseTag(){
        //returns closing tag
        String c_tag;
        c_tag = close_tag.replace(rchar, getTitle());
        return c_tag;
    }

    public XML_Element[] getChildren() { return children; }

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


    public String getData(){ return element_data; }

    public XML_Element[] getChildTree(){
        /*returns array of all nested Elements
        * return XML_Element[]
        * */

        XML_Element[] child_tree = new XML_Element[0];

        //Check is there are any Child Elements
        if(children != null){
            for (XML_Element c_elements : children){
                XML_Element[] buffer = child_tree;
                child_tree = new XML_Element[buffer.length+1];

                for (int i = 0; i < buffer.length; i++){
                    child_tree[i] = buffer[i];
                }

                child_tree[buffer.length] = c_elements;

                if (c_elements.getChildTree() != null){
                    for (int i = 0; i < c_elements.getChildTree().length; i++) {
                        XML_Element[] buffer2 = child_tree;
                        child_tree = new XML_Element[buffer2.length+1];

                        for (int e = 0; e < buffer2.length; e++){
                            child_tree[e] = buffer2[e];
                        }

                        child_tree[buffer2.length] = c_elements.getChildTree()[i];
                    }
                }
            }

            return child_tree;

        }

        return null;
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
