/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

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
 * @author albertogg
 */
public class EscribirXML {

    public EscribirXML() {
    }

    public void writeXMLFile(ArrayList mensaje) {
//        System.out.println("Mensaje Recibido: " + mensaje);
        int bolas = 1;

        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("system");
            doc.appendChild(rootElement);

            // staff elements
            Element staff = doc.createElement("info");
            rootElement.appendChild(staff);

            Iterator it = mensaje.iterator();
            while (it.hasNext()) {

                if (bolas == 1) {
                    Attr attr = doc.createAttribute("id");
                    attr.setValue(it.next().toString());
                    staff.setAttributeNode(attr);
                } else {
                    // itemz elements
                    Element itemz = doc.createElement("item");
                    itemz.appendChild(doc.createTextNode(it.next().toString()));
                    staff.appendChild(itemz);
                }
                bolas = 2;
            }


            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
//            StreamResult result = new StreamResult(new File("C:\\file.xml"));

//             Output to console for testing
            StreamResult result = new StreamResult(System.out);

            transformer.transform(source, result);

            System.out.println("File saved!");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }

    }
}
