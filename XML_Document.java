/**
 * Created by Samuel Hild on 7/30/2017.
 */
public class XML_Document {
    private XML_Element Elements[];
    private String x_version;
    private String x_encoding;
    private String x_filename;
    //Prolog Formatters
    private final String prolog_start = "<?xml version = '";
    private final String prolog_mid = "' encoding = '";
    private final String prolog_end = "'?>\n";

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

    //Prints Entire xml Document
    public void printXmlDocument() {
        System.out.println(prolog_start + x_version + prolog_mid + x_encoding + prolog_end);
        for (int i = 0; i < Elements.length; i++) Elements[i].printElement();
    }
}
