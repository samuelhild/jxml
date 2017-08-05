/**
 * Created by agentquebeq on 8/3/2017.
 */
public class TestMain {
    public static void main(String[]args){
        XML_Document x_doc = new XML_Document();

        x_doc.setProlog(true);
        x_doc.setVersion("1.0");
        x_doc.setEncoding("UTF-8");

        x_doc.addElement(new XML_Element("cats"));

        XML_Element dogs = new XML_Element("dogs", "woof");
        x_doc.addElement(dogs);

        dogs.addAttribute("fur", "black");

        System.out.println(x_doc.getDocument());
    }
}
