/**
 * Created by Samuel Hild on 7/30/2017.
 * Manages information stored in the document
 */

public class XML_Doc {

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
    private boolean show_warnings = true;
    private boolean auto_prefix = false;


    public void XML_Doc(String...argv){
        /*Constructor
            Run on creation of new XML_Doc
            on initialization creates:
            0: version
            1: encoding
            2: filename
         */

        //Switch to assign vars
        switch (argv.length){
            case 0:
                //no args set default values
                break;
            case 1:
                x_version = argv[0];
                break;
            case 2:
                x_version = argv[0];
                x_encoding = argv[1];
                break;
            case 3:
                x_version = argv[0];
                x_encoding = argv[1];
                x_filename = argv[3];
                break;
        }
    }private String assemble_Document_String(){
        /*Assembles Document String
        * Includes:
        * Header and all elements
        * returns String of full Document rtn_val
        * */

        //sets rtn_val to nothing
        String rtn_val = "";

        //add or removes Prolog
        if(prolog_bool) rtn_val = getProlog();

        //Element Loop
        //Loops Through Elements and adds them too Document
        for (int i = 0; i < x_elements.length; i++){
            rtn_val += x_elements[i].printElement();
        }

        return rtn_val;
    }public void addElement(XML_Element xml_e){
        /*Adds Elements.
        * All Elements added into the Document will be root
        * */

        //check if x_elements has been declared
        if(x_elements == null){
            //Declares x_elements and sets first value to the added value
            x_elements = new XML_Element[1];
            x_elements[0] = xml_e;
        }else{
            XML_Element[] buffer = x_elements;
            x_elements = new XML_Element[buffer.length+1];
            for(int i = 0; i < buffer.length; i++){
                x_elements[i] = buffer[i];
            }
            x_elements[x_elements.length - 1] = xml_e;
        }
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
    public String getDocument() { return assemble_Document_String(); }

    public XML_Element getElementbyName(String name){
        //returns Element based on its name

        for (XML_Element element : x_elements){
            if(element.getTitle() == name){
                return element;
            }
            //check for child elements in other elements
            if(element.getChildTree() != null){
                for (XML_Element element2 : element.getChildTree()){
                    if(element2.getTitle() == name){
                        return element2;
                    }
                }
            }


        }
        warn(0, "getElementbyName()", "No Element " + name + " found.");
        return null;
    }
    public void warn(int sev, String in_method, String message){
        /*Displays Warnings and errors
        * Has three levels of Severity
        * 1: Warnings: Low level, can be ignored
        * 2: Errors: Medium Level, could be severe
        * 3: Critical: Critical errors, Program exits
        * */
        if (show_warnings) {
            switch (sev) {
                case 0:
                    System.out.println("[??] WARNING: in method: " + in_method + "\n" + message);
                    break;
                case 1:
                    System.out.println("[!!] ERROR: in method: " + in_method + "\n" + message);
                    break;
                case 2:
                    System.out.println("[!!] CRITICAL: in method: " + in_method + "\n" + message + "\n Exiting...");
                    System.exit(1);
            }
        }
    }
}
