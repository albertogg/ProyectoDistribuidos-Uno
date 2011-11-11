/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agente;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
/**
 *
 * @author albertogg
 */
public class LectorXML {
        public String ip;
    public int port;

    // Metodo encargado de la lectura del XML de direccion y puerto para
    // efectuar la conexion entre el agente y el servidor.
    // no recibe parametros solo guarda en las variables "ip" y "port"
    // lo leido para poderlas utilizar en la llamada al socket.
    public void lecturaXML() {

        try {
            // Direccion del archivo xml, junto con el bloque de codigo para
            // hacer la lectura del archivo.
            File fXmlFile = new File("connXML.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbf.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("nodo");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    System.out.println("Ip : " + getTagValue("ip", eElement));
                    ip = getTagValue("ip", eElement);
                    System.out.println("Puerto : "
                            + getTagValue("puerto", eElement));
                    String ports = getTagValue("puerto", eElement);
                    port = Integer.parseInt(ports);

                }
            }
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
