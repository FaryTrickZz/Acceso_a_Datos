package SAX;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.lang.model.element.Element;

public class Bernat_Aparicio_SAXEvaluable extends DefaultHandler {
    String qName = "";
    String titulos[];
    int t = 0;
    String creador[];
    int c = 0;
    String sinopsis[];
    int s = 0;
    String plataforma[];
    int p = 0;
    String any[];
    int a = 0;

    public void startDocument() throws SAXException
    {
        titulos = new String[5];
        creador = new String[5];
        sinopsis = new String[5];
        plataforma = new String[5];
        any = new String[5];
        System.out.println("Start the document");
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
    {
        this.qName = qName;
        System.out.println("<"+qName+">");
        for (int i = 0; i<attributes.getLength();i++) {
            System.out.println("\t"+attributes.getQName(i)+": "+attributes.getValue(i));
        }
    }

    public void characters(char[] ch, int start, int lenght) throws  SAXException
    {
        String cont = new String(ch, start, lenght);
        System.out.println("\t"+cont);
        if (cont.equals(" ")) {
            System.err.println("\nUno o varios nodos se encuentran vacios");
        }

        if (this.qName.equals("Titol")){
            titulos[t] = cont;
            t++;
        }
        else if (this.qName.equals("Creador")) {
            creador[c] = cont;
            c++;
        }
        else if (this.qName.equals("Sinopsis")) {
            sinopsis[s] = cont;
            s++;
        }
        else if (this.qName.equals("Plataforma")) {
            plataforma[p] = cont;
            p++;
        }
        else if (this.qName.equals("Any")) {
            any[a] = cont;
            a++;
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException
    {
        System.out.println("</"+qName+">");
        this.qName = "";
    }

    public void endDocument() throws SAXException
    {
        System.out.println("End of the document");
    }

    // Metodo para leer los titulos
    public void leerTitulos() {
        for (int i = 0; i<titulos.length;i++) {
            System.out.println(titulos[i]);
        }
    }

    // Metodo para mostrar los juegos creados entre x años
    public void videojocEntreXAnios(int any1, int any2) {
        for(int i = 0; i<any.length;i++) {
            int anio = Integer.parseInt(any[i]);
            if (anio >= any1 && anio <= any2) {
                System.out.println(titulos[i]+": "+ anio);
            }
        }
    }

    // Metodo para mostrar el juego creado en un año especifico
    public void videojocAnioEspecifico(int any1) {
        for(int i = 0; i<any.length;i++) {
            int anio = Integer.parseInt(any[i]);
            if (anio == any1) {
                System.out.println(titulos[i]+": "+ anio);
            }
        }
    }
}
