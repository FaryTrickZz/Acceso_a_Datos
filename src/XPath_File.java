import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.File;
import java.io.IOException;

public class XPath_File {


    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {
        //Cargar XML en el documento
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new File("bookings.xml"));

        //Variables XPath
        XPath xPath = XPathFactory.newInstance().newXPath();

        //Buscamos los nodos client
        XPathExpression clients = xPath.compile("/reservas/booking/client");
        NodeList clientNodes = (NodeList)clients.evaluate(doc,XPathConstants.NODESET);


        for (int i = 0; i<clientNodes.getLength(); i++) {
            Node node = clientNodes.item(i);
            Element element = (Element)node;
            System.out.println("Client "+i+" : "+ element.getTextContent());
        }

        //Busca el elemento booking con la location number especifica
        XPathExpression loc01 = xPath.compile("/reservas/booking[@location_number='01']");
        //Get element
        Element element = (Element)loc01.evaluate(doc, XPathConstants.NODE);

        //Print value
        System.out.println("\nLocation number : " + element.getAttribute("location_number"));
        System.out.println("Client : "+element.getElementsByTagName("client").item(0).getTextContent());

        //Sacamos la cuenta de bookings
        XPathExpression count = xPath.compile("count(/reservas/booking)");
        System.out.println("Numero de bookings : "+count.evaluate(doc, XPathConstants.NUMBER));

    }
}
