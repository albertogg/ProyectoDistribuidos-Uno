/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.ArrayList;
import java.util.Iterator;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author albertogg & carlos fernandes
 */
public class EscribirXML {

    public Attr attr;
    public String dd;

    public EscribirXML() {
    }

    public void writeXMLFile(ArrayList mensaje) {

        int bolas = 1;
        int ddddd = 1;
        int suma = 0;


        try {
            
            // Buscamos el primer elemento de la lista recibida del cliente
            // para utilizar ese elemento para llamar asi nuestro archivo xml
            Iterator ii = mensaje.iterator();
            while (ii.hasNext()) {
                if (ddddd == 1) {
                    dd = ii.next().toString();
                    System.out.println(dd);
                    ddddd = ddddd + 1;
                } else {
                    break;
                }
            }

                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

                // root elements
                Document doc = docBuilder.newDocument();
                Element rootElement = doc.createElement("system");
                doc.appendChild(rootElement);

                // info elements
                Element staff = doc.createElement("info");
                rootElement.appendChild(staff);
                
                // iteramos por la lista recibida de del agente para guardarla
                // en el xml
                Iterator it = mensaje.iterator();
                while (it.hasNext()) {

                    if (bolas == 1) {
                        attr = doc.createAttribute("id");
                        attr.setValue(it.next().toString());
                        staff.setAttributeNode(attr);
                    } else {
                        // itemz elements
                        Element itemz = doc.createElement("item" + suma);
                        itemz.appendChild(doc.createTextNode(it.next().toString()));
                        staff.appendChild(itemz);
                    }
                    bolas = 2;
                    suma = suma + 1;
                }


                // escribimos el contenido en el xml
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                
                File f = new File(System.getProperty("user.home")+
                                                  File.separator+".inventario");
                
                if(f.mkdir()) {
                    System.out.println("El directorio se creo");
                } else {
                    System.out.println("El directorio ya existe");
                }
                
                String ruta = System.getProperty("user.home")+File.separator+
                                                    ".inventario"+File.separator;
                System.out.println(ruta);
                StreamResult result = new StreamResult(new File(ruta + dd + ".xml"));

//            salida para pruebas por consola
//            StreamResult result = new StreamResult(System.out);

                transformer.transform(source, result);

                System.out.println("Archivo Guardado!");
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }

    }
}
