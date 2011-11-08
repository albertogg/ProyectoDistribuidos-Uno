/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agente;

import java.net.*;
import java.io.*;
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
public class Agente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {

            // Objeto de tipo LectorXML para poder ejecutar la lectura del XML
            // Para poder obtener el IP y el Puerto de destino a la conexion.
            LectorXML lx = new LectorXML();
            lx.lecturaXML();
            
            //Utilizamos el ip y el pueto leidos en el XML.
            Socket s1 = new Socket(lx.ip, lx.port);


            ObjectOutputStream oos = new ObjectOutputStream(s1.getOutputStream());
            
            // Comandos para leer la salida standard del Terminal.
            Runtime rt = Runtime.getRuntime();
            Process ps = rt.exec("./script.sh");
            BufferedReader input = new BufferedReader(new 
                                        InputStreamReader(ps.getInputStream()));

            String line = null;
            
            //Ciclo para leer todas las lineas de la salida standard.
            while ((line = input.readLine()) != null) {
//                System.out.println(line);
            }
            
            // Variable utilizada para imprimir el status arrojado por la
            // salida standard del terminal.
            int exitval = ps.waitFor();
            System.out.println("bolas   " + exitval);
            oos.writeObject("hola server");

            // Imprime el texto recibido por el servidor.
            ObjectInputStream ois = new ObjectInputStream(s1.getInputStream());
            String message = (String) ois.readObject();
            System.out.println("Message: " + message);


            oos.close();
            ois.close();


        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (ConnectException connExc) {
            System.err.println("Imposible establecer la conexion");
        } catch (IOException e) {
        }
    }
}

class LectorXML {

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
                    System.out.println("Puerto : " + 
                                               getTagValue("puerto", eElement));
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