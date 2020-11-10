import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class dom {



    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerException {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new File("bookings.xml"));

        NodeList nodes = doc.getElementsByTagName("booking");

        for(int i=0;i<nodes.getLength();i++) {
            Node node=nodes.item(i);
            Element element=(Element)node;

            System.out.println("Location number : " + element.getAttribute("location_number"));
            System.out.println("Client : "+ element.getElementsByTagName("client").item(0).getTextContent());
            System.out.println("Price : "+ element.getElementsByTagName("preu").item(0).getTextContent());
            System.out.println("Hotel : "+ element.getElementsByTagName("hotel").item(0).getTextContent());
            System.out.println("Agency : "+ element.getElementsByTagName("agencia").item(0).getTextContent());
            System.out.println("\n");
        }
        // Creamos el elemento booking
        Node nodeBooking = doc.createElement("booking");
        // Le añadimos un atributo a bokking
        ((Element) nodeBooking).setAttribute("location_number", "04");

        // Creamos el elemento client
        Node nodeClient = doc.createElement("client");
        // Le añadimos un atributo a client
        ((Element) nodeClient).setAttribute("id_client","1001");
        Node nodeClientText = doc.createTextNode("New Client");
        // Añadimos texto al elemento client
        nodeClient.appendChild(nodeClientText);

        // Creamos el elemento preu
        Node nodePreu = doc.createElement("preu");
        Node nodePreuText = doc.createTextNode("200€");
        nodePreu.appendChild(nodePreuText);

        // Creamos el elemento hotel
        Node nodeHotel = doc.createElement("Hotel");
        Node nodeHotelText = doc.createTextNode("Hotel2");
        nodeHotel.appendChild(nodeHotelText);

        // Creamos el elemento agencia
        Node nodeAgencia = doc.createElement("agencia");
        Node nodeAgenciaText = doc.createTextNode("Ryanair");
        nodeAgencia.appendChild(nodeAgenciaText);


        // Añadimos cliente al elemento Booking
        nodeBooking.appendChild(nodeClient);

        nodeClient.appendChild(nodePreu);
        nodePreu.appendChild(nodeHotel);
        nodeHotel.appendChild(nodeAgencia);

        // Añadimos booking al ultimo elemento
        doc.getLastChild().appendChild(nodeBooking);

        //Modificamos un elemento
        ((Element)doc.getLastChild()).getElementsByTagName("client").item(4).setTextContent("Alonso");

        //guardar archivo
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transf = tf.newTransformer();
        transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transf.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(doc);
        StreamResult file = new StreamResult(new File("newfile.xml"));
        transf.transform(source, file);
    }
}