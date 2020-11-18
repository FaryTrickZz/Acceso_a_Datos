import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.beans.Expression;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class xPath_BernatAparicio {

    //Cargar XML en el documento y lo devuelve
    public static Document cargarXML() throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new File("videojocs-modificat.xml"));
        return doc;
    }

    //Recibe 1/2/3 nodos por parametro y muestre los que tengan las mismas caracteristicas
    public static void nodosMismasCaracteristicas(Document doc, String nodo1, String nodo2, String nodo3) throws XPathExpressionException {
        //Variable XPath
        XPath xPath = XPathFactory.newInstance().newXPath();
        String expresion = "";

        //Depende de que nodo sea nulo, la expresion quee se ejecutara sera diferente
        if (nodo2 == null && nodo3 == null) {
            System.out.println("Los nodos 2 y 3 estan vacios.");
            expresion = "/"+nodo1;

        } else if (nodo3 == null){
            System.out.println("El nodo 3 esta vacio.");
            expresion = "/"+nodo1+"/"+nodo2;

        } else if (nodo2 == null ) {
            System.out.println("El nodo 2 esta vacio.");
            expresion = "/"+nodo1+"/"+nodo3;

        } else {
              expresion = "/"+nodo1+"/"+nodo2+"/"+nodo3;
        }
        //Busco el nodo en el archivo XML
        XPathExpression Nodo = xPath.compile(expresion);

        //Creo una lista con los nodos obtenidos
        NodeList Nodolist = (NodeList)Nodo.evaluate(doc, XPathConstants.NODESET);

        //Printeo el contenido del nodo obtenido
        for (int i = 0; i<Nodolist.getLength(); i++) {
            Node node = Nodolist.item(i);
            Element element = (Element)node;
            System.out.println(element.getTagName()+": "+element.getTextContent());
        }
    }

    //Recibe un nodo y busca todos los nodos iguales
    public static void mostrarNodosIguales(Document doc, String nodo) throws XPathExpressionException {
        //Variable XPath
        XPath xPath = XPathFactory.newInstance().newXPath();
        XPathExpression expresion = xPath.compile("//"+nodo);
        NodeList Nodolist = (NodeList)expresion.evaluate(doc, XPathConstants.NODESET);
        for (int i = 0; i<Nodolist.getLength(); i++) {
            Node node = Nodolist.item(i);
            Element element = (Element)node;
            System.out.println(element.getTagName()+": "+element.getTextContent());
        }

    }

    //Se le pasa un titulo por parametro y muestra todo el contenido del juego con ese titulo
    public static void mostrarContenidoAPArtirDeTtiulo(Document doc, String Titulo) throws XPathExpressionException {
        //Variable XPath
        XPath xPath = XPathFactory.newInstance().newXPath();
        XPathExpression expresion = xPath.compile("//Videojoc[Titol='"+Titulo+"']");
        Element element = (Element)expresion.evaluate(doc, XPathConstants.NODE);
        System.out.println(element.getTagName()+": "+element.getTextContent());
    }

    //Pide por pantalla el identificador de un juego para mostrarlo
    public static void busquedaMedianteID(Document doc) throws XPathExpressionException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el id del juego que quieres buscar: ");
        try {
            int id = sc.nextInt();
            //Variable XPath
            XPath xPath = XPathFactory.newInstance().newXPath();

            //Busca en el arbol el nodo con el atributo indicado
            XPathExpression expresion = xPath.compile("//Videojoc[@id=" + id + "]");
            NodeList Nodolist = (NodeList) expresion.evaluate(doc, XPathConstants.NODESET);
            Node node = Nodolist.item(0);
            Element element = (Element) node;
            System.out.println(element.getTagName() + ": " + element.getTextContent());
        } catch (NullPointerException e){
           System.out.println("El id del juego indicado no se ha encontrado.");
        }
    }

    //Pide el titulo de todos los juegos y los cuenta todos exceptuando el que le hemos indicado
    public static void contarNumeroJuegos(Document doc, String Titulo) throws XPathExpressionException {
        //Variable XPath
        XPath xPath = XPathFactory.newInstance().newXPath();
        XPathExpression expresion = xPath.compile("//Titol");
        int contJuegos = 0;
        NodeList Nodolist = (NodeList)expresion.evaluate(doc, XPathConstants.NODESET);
        for (int i = 0; i<Nodolist.getLength(); i++) {
                Node node = Nodolist.item(i);
                Element element = (Element)node;
                if (element.getTextContent().equals(Titulo)){
                } else {
                    System.out.println(element.getTagName()+": "+element.getTextContent());
                    contJuegos++;
                }
        }
    }

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
        //Cargamos el XML con
        Document doc = cargarXML();

        //nodosMismasCaracteristicas(doc, "videojocs", null, null);

        //mostrarNodosIguales(doc, "Titol");

        //busquedaMedianteID(doc);

        //contarNumeroJuegos(doc, "League of Legends");

        //mostrarContenidoAPArtirDeTtiulo(doc, "COD");

    }
}
