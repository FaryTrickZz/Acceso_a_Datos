package SAX;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLSaxHandler extends DefaultHandler {
    public void startDocument() throws SAXException
    {
        System.out.println("Start the document");
    }

    public void endDocument() throws SAXException
    {
        System.out.println("End of the document");
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
    {
        System.out.println("Start element qname: "+ qName);
    }

    public void endElement(String uri, String localName, String qName) throws SAXException
    {
        System.out.println("End element: "+ qName);
    }

    public void character(char ch[], int start, int lenght) throws  SAXException
    {
        System.out.println("Characters: " + new String(ch, start, lenght));
    }
}
