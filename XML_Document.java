/**
 * Created by Samuel Hild on 7/30/2017.
 * Manages information stored in the document
 */

public class XML_Document {
    //Main vars
    private XML_Element x_elements[];
    private String x_version;
    private String x_encoding;
    private String x_filename;
    //Prolog Formatters
    private final String prolog_start = "<?xml version = '";
    private final String prolog_mid = "' encoding = '";
    private final String prolog_end = "'?>\n";

    //other vars
    private boolean prolog_bool = true;

    //Main Functions
    public void addElement(XML_Element...elements){
        /*simply adds an element to the XML_Document
         *Takes 1-2 arguments:
         *  1: Element to add *Required
         *  2: Parent Element *Optional
         * if no parent argument is passed the element is assumed to be root
         * and will be placed next to any current root element(s)
         */

        //temporary id for the new element
        int temp_id;

        //check if there are any elements
        if(x_elements == null) x_elements = new XML_Element[0];

        //increase array size by one
        XML_Element[] a_buffer = x_elements;
        x_elements = new XML_Element[a_buffer.length + 1];

        //add new element to x_elements
        x_elements[x_elements.length - 1] = elements[0];
        temp_id = x_elements.length - 1;

        if (elements.length == 2){
            //parent passed as second arg
            elements[1].addChild(x_elements[temp_id]);
        }
    }

    private String assembleDocument() {
        //creates  an XML document including the header and all elements
        //returns a String xml_file
        String xml_file = "";
        if (prolog_bool) {
            xml_file = getProlog();                             //Check if Prolog should be outputted to the file
        }
        //loop through each element in document
        //ignore child elements as they will be copied from element contents
        for (XML_Element e : x_elements){
            if (!e.isChild()) {
                xml_file += e.getFullElement();
            }
        }

        return xml_file;
    }

    //Setter and Getter Functions

    //sets the filename for the xml file
    public void setXmlFilename(String filename) { x_filename = filename; }

    //sets the XML version to use
    public void setVersion(String version) {
        x_version = version;
    }

    //decides whether to print header to the file
    public void setProlog(boolean bool){ prolog_bool = bool; }

    //sets encoding for the XML file
    public void setEncoding(String encoding) {
        x_encoding = encoding;
    }

    //sets filename
    public String getXmlFilename() {
        return x_filename;
    }

    //gets version
    public String getVersion() {
        return x_version;
    }

    //gets encoding
    public String getEncoding() {
        return x_encoding;
    }

    //returns entire xml prolog
    public String getProlog() {
        return prolog_start + x_version + prolog_mid + x_encoding + prolog_end;
    }

    //returns Elements
    public XML_Element[] getElements() {
        return x_elements;
    }

    //returns entire Document
    public String getDocument() { return assembleDocument(); }
        
    //returns XML_Element based on name
    public XML_Element getElementbyName(String name){
        for (int i = 0; i < x_elements.length; i++){
            if (x_elements[i].getTitle() == name){
                return x_elements[i];
            }
        }
        System.out.println("Error:\nin Function getElementbyName()\nElement named"+name+"not found!");
        return null;
    }

}
