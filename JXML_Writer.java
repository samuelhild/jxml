/**
 * Created by Samuel Hild on 7/30/2017.
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


//TODO remove Prolog Functions
public class JXML_Writer {
    private boolean prolog_bool = true;
    private String xml_version, xml_encoding;
    private final String prolog_start = "<?xml";
    private final String prolog_end = "?>\n";
    private String file_prolog;

    private BufferedWriter writer;                                              //Bufferedwriter used to write file

    public void setProlog(boolean argb) {                                        //decides weather to add a xml prolog
        prolog_bool = argb;
        file_prolog = gen_prolog(null, null);
    }

    public void formatProlog(String x_version, String encoding) {
        //allows user to set encoding and xml version
        //if default or null is passed into function
        //default values are set
        if (x_version == "default" || x_version == "DEFAULT" || x_version == null) {
            xml_version = "1.0";                                                //Default version: 1.0
        } else {
            xml_version = x_version;
        }
        if (encoding == "default" || encoding == "DEFAULT" || encoding == null) {
            xml_encoding = "UTF-8";                                             //Default Encoding: UTF-8
        } else xml_encoding = encoding;
        file_prolog = gen_prolog(xml_version, xml_encoding);
    }

    private String gen_prolog(String x_version, String encoding) {
        //Generates xml file prolog
        //uses values set in formatProlog()
        String prolog;
        if (!prolog_bool) {
            prolog = null;                                                      //set prolog to null
        } else {
            prolog = prolog_start + " version = '" + x_version + "' encoding = '" + encoding + "'" + prolog_end;
        }
        return prolog;
    }

    public void writexml(XML_Document x_doc, String[] argv) {
        //Primary Write function
        //Requires: XML_Document <Document to write>, String <filename>
        if (argv.length == 0) fatal_io(null, "Malformed Function Declaration of Function writexml()\nToo Few Args!");
        else {
            //TODO: add functionality
        }
    }

    public void write_raw_xml(String xml_string, String filename, String x_version, String encoding) {
        //Writes raw String to an xml file
        //Does not use any formatting other than the file prolog
        if (prolog_bool) {
            //append prolog to input String
            formatProlog(x_version, encoding);
            xml_string = file_prolog + xml_string;
        }
        try {
            writer = new BufferedWriter(new FileWriter(filename));              //initialize new buffered writer
            writer.write(xml_string);                                           //Attempt to write xml
            writer.close();                                                     //close Writer
        } catch (IOException e) {
            fatal_io(e, "Failed to Write Xml Data to file in Function write_raw_xml()");
        }
    }

    public void write_raw_xml(String xml_string, String filename) {
        //Overridden Version of write_raw_xml() without optional Parameters
        //Defaults to no file Prolog
        //Immediately calls original function with null
        write_raw_xml(xml_string, filename, null, null);
    }

    private void fatal_io(IOException e, String e_message) {
        //Reports Fatal io Exceptions
        System.out.println("[!!] IOException:\n" + e_message + "\nException Stack Trace:");
        if (e != null) e.printStackTrace();
        else System.out.println("No Stack Trace!");
        System.exit(-1);
    }
}
