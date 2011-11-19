/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author albertogg
 */

public class LectorXML {

    public String ip;
    public ArrayList<String> listado = new ArrayList<String>();

    // Metodo encargado de la lectura del XML de direccion y puerto para
    // efectuar la conexion entre el agente y el servidor.
    // no recibe parametros solo guarda en las variables "ip" y "port"
    // lo leido para poderlas utilizar en la llamada al socket.
    public void lecturaXML(String dd) {

        try {
            // Direccion del archivo xml, junto con el bloque de codigo para
            // hacer la lectura del archivo.
            File fXmlFile = new File(dd);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbf.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();


            NodeList nList = doc.getElementsByTagName("info");
            int bb = 0;
            
            for (int i = 1; i < 35; i++) {
                
                NodeList inList1 = doc.getElementsByTagName("item" + i);
                
                if (bb != (bb + inList1.getLength()) ) {
                    
                    bb = bb + inList1.getLength();
                    
                }else {
                    
                    break;
                }
                
            }


            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    for (int temp1 = 1; temp1 <= bb; temp1++) {

                        ip = getTagValue("item" + temp1, eElement);
                        eElement.getNextSibling();
                        listado.add(ip);

                    }
                }
            }
//            System.out.println(listado);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Metodo el cual busca por nodo dentro del xml la informacion dado el
    // nombre del mismo y el nombre del nodo padre.
    private static String getTagValue(String sTag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(sTag).
                item(0).getChildNodes();

        Node nValue = (Node) nlList.item(0);

        return nValue.getNodeValue();
    }
}
