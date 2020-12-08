package SAX;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class SAX {
    private static Object XMLSaxHandler;

    public static void main(String[] args) {
        try {
            File inputFile=new File("bookings.xml");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            XMLSaxHandler userhandler =  new XMLSaxHandler();
            saxParser.parse(inputFile, userhandler);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
