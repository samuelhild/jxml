/**
 * Created by Samuel Hild on 7/30/2017.
 */

//TODO move addElement() function to XML_Document
//
//
//

public class XML_Document {
    private XML_Element Elements[];
    private String x_version;
    private String x_encoding;
    private String x_filename;
    //Prolog Formatters
    private final String prolog_start = "<?xml version = '";
    private final String prolog_mid = "' encoding = '";
    private final String prolog_end = "'?>\n";

    //other vars
    private boolean PrologBool = true;                              //decides whether or not to print the header to the output file

    public void addElement(XML_Element parent, String... argv) {

        if (Elements == null) Elements = new XML_Element[1];

        if (parent == null) {                                   //When no parent is passed assume element is root

        } else {
            //parent.addChild(new XML_Element());//TODO replace addElement() with addchild in XML_Element()
        }

    }

    /*public XML_Element addElement(String... argv) {
        return addElement(null, argv);
    }*/

    private String assembleDocument() {
        //creates  an XML document including the header and all elements
        //returns a String xml_file
        String xml_file = "";
        if (PrologBool) {
            xml_file = getProlog();                             //Check if Prolog should be outputted to the file
        }

        xml_file = xml_file + getElements();                    //add all Elements to the file

        return xml_file;
    }

    //Setter and Getter Functions
    //sets/gets filename
    public String getXmlFilename() {
        return x_filename;
    }

    public void setXmlFilename(String filename) {
        x_filename = filename;
    }

    //sets/gets version
    public String getVersion() {
        return x_version;
    }

    public void setVersion(String version) {
        x_version = version;
    }

    //sets/gets encoding
    public String getEncoding() {
        return x_encoding;
    }

    public void setEncoding(String encoding) {
        x_encoding = encoding;
    }

    //returns entire xml prolog
    public String getProlog() {
        return prolog_start + x_version + prolog_mid + x_encoding + prolog_end;
    }

    //returns Elements
    public XML_Element[] getElements() {
        return Elements;
    }

    public String getDocument() {
        return assembleDocument();
    }

    //Prints Entire xml Document
    public void printXmlDocument() {
        System.out.println(assembleDocument());
    }
}
