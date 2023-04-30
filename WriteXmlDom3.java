import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class WriteXmlDom3 {

    public static void main(String[] args) throws ParserConfigurationException, TransformerException, FileNotFoundException {

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("departments");
        doc.appendChild(rootElement);


        Element departmentFirst = doc.createElement("department");
        Element dp_first_name = doc.createElement("name");
        Element dp_first_email = doc.createElement("email");
        dp_first_name.setTextContent("HR");
        dp_first_email.setTextContent("hr@oracle.com");
        departmentFirst.appendChild(dp_first_name);
        departmentFirst.appendChild(dp_first_email);

        Element departmentSecond = doc.createElement("department");
        Element dp_second_name = doc.createElement("firstname");
        Element dp_second_email = doc.createElement("email");
        dp_second_name.setTextContent("IT");
        dp_second_email.setTextContent("it@java.com");
        departmentSecond.appendChild(dp_second_name);
        departmentSecond.appendChild(dp_second_email);

        rootElement.appendChild(departmentFirst);
        rootElement.appendChild(departmentSecond);

        File file = new File("departments.xml");
        writeXml(doc, new FileOutputStream(file));
    }

    private static void writeXml(Document doc, OutputStream output) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        // pretty print
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(output);

        transformer.transform(source, result);
    }
}
