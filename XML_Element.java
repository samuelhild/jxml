/**
 * Created by Samuel Hild on 7/30/2017.
 *
 * Provides Formatting Information for xml Elements
 */
public class XML_Element {
    private static String element_name;
    private Attribute attributes[];
    private String element_data;
    private XML_Element child_elements[];
    private String prefix;
    private String raw_element;

    //Element Formatters
    private final String rchar = "^^";                     //Replace Character used for formatting
    private final String open_tag = "<^^>";
    private final String close_tag = "</^^>";

    public void printElement(){
        System.out.println(raw_element);
    }

    public XML_Element addElement(XML_Element parent, String... argv){
        //adds new Element
        //arg1 is parent element
        //arg2 is title text
        //arg3 is String text
        //Example: <arg1>
        //          <arg2>arg3</arg2>
        //         </arg1>
        XML_Element nElement = new XML_Element();
        nElement.setTitle(argv[0]);
        nElement.setDataText(argv[1]);

        if(parent != null) parent.addChild(nElement);           //if there is no parent passed, assume root element

        return nElement;
    }public XML_Element addElement(String... argv){
        return addElement(null, argv);
    }
    private void addChild(XML_Element child){

    }

    public void addAttribute(String title, String value){
        //adds attributes to elements
        int length_var;

        if (attributes == null){
            attributes = new Attribute[1];
        }else{
            Attribute[] a_buffer = attributes;
            attributes = new Attribute[attributes.length+1];
            for(int i = 0; i<a_buffer.length; i++) attributes[i] = a_buffer[i];
        }
        length_var = attributes.length;
        attributes[length_var-1] = new Attribute();
        attributes[length_var-1].setTitle(title);
        attributes[length_var-1].setValue(value);
    }

    private String genRawElement(){
        //a function to generate the element as it will be outputed to xml
        //also Generates all child elements
        String openning_tag_text;//TODO cat element_name and attribute text stored in this String

        String raw_element_tmp = open_tag.replace(rchar, element_name /*+ attributes*/);
        if (child_elements != null) {
            //only run if there are child elements
            for (int i = 0; i < child_elements.length; i++) {
                raw_element_tmp = raw_element_tmp + child_elements[i].getElement();
            }
        }
        raw_element_tmp = raw_element_tmp + close_tag.replace(rchar, element_name);
        return raw_element_tmp;
    }


    //getters and setters
    //TODO add getters and setters

    public void setTitle(String title){
        element_name = title;
    }
    public void setDataText(String data){
        element_data = data;
    }
    public Attribute[] getAttributes (){
        return attributes;
    }
    public String getElement(){
        raw_element = genRawElement();
        return raw_element;
    }

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
