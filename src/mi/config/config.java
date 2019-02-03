/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mi.config;


import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


/**
 *
 * @author Pek
 */
public class config {
    
    
    

    
    
    
    
    public static void  set_DefaultConfig() throws IOException { 
    
        try {

 

            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

 

            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

 

            Document document = documentBuilder.newDocument();

 

            

            // config element

            Element config = document.createElement("config");

            document.appendChild(config);

 

            // assunto element

            Element assunto = document.createElement("assunto");

            assunto.appendChild(document.createTextNode("TheFour Brasil - Convite"));

            config.appendChild(assunto);

 

            // message element

            Element message = document.createElement("message");

            message.appendChild(document.createTextNode("\n"
                    
                    + "É Com imenso prazer que convidamos você para assistir o programa TheFour Brasil comigo.\n" +
                    "\n" +
                    "- Data de gravação: {data}\n" +
                    "\n" +
                    "- Horário de chegada:\n" +
                    "\n" +
                    "- Endereço: Pavilhão Vera Cruz - Av. Lucas Nogueira Garcéz, 856 Centro - São Bernardo do Campo - SP\n" +
                    "\n" +
                    "- Idade acima de 18 anos.\n" +
                    "\n" +
                    "- Figurino: Roupa sem marca nem número, lembrando que precisam usar o mesmo para todos dias que foram chamado.\n" +
                    "\n" +
                    "- Trazer autorização de imagem e termo de confidencialidade (em anexo);\n" +
                    "\n" +
                    "- Só será permitida a entrada nas dependências do pavilhão com doc originais, identidade, CNH ou Carteira de trabalho\n" +
                    "\n" +
                    "- Seu nome estará na lista de convidados e não será permitida a saída antes do término do programa.\n" +
                    "\n" +
                    "- É exigência de produção que não haja \"FALTAS\" e a produção deverá ser informada com 48h de antecedência quanto a   possibilidade de desistência possibilitando a captação do público complementar.\n" +
                    "\n" +
                    "- NÃO SERÁ PERMITIDO O USO DE MÁQUINA FOTOGRÁFICA, CELULAR E EXPRESSAMENTE PROIBIDA O USO DE MAQUINA DE FILMAR.\n" +
                    "\n" +
                    "- Não é permitido fumar nas dependências do pavilhão Vera Cruz."));

            config.appendChild(message);

 

            // data element

            Element data = document.createElement("data");

            data.appendChild(document.createTextNode("01 de Janeiro"));

            config.appendChild(data);

 

            // hora elements

            Element hora = document.createElement("hora");

            hora.appendChild(document.createTextNode("às 00:00"));

            config.appendChild(hora);

 

            // create the xml file

            //transform the DOM Object to an XML File

            TransformerFactory transformerFactory = TransformerFactory.newInstance();

            Transformer transformer = transformerFactory.newTransformer();

            DOMSource domSource = new DOMSource(document);


            
 
           File writer = new File(System.getProperty("user.dir") + "/config.xml");
        
            
           StreamResult result = new StreamResult(writer);
        
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");


            transformer.transform(domSource, result);
            
            System.out.println("Done creating Default XML File");

 

        } catch (ParserConfigurationException pce) {

            pce.printStackTrace();

        } catch (TransformerException tfe) {

            tfe.printStackTrace();

        }
    }
    
    
    public static void  set_Config(String _assunto, String _message, String _data, String _hora) throws IOException, ParserConfigurationException, TransformerConfigurationException, TransformerException { 
    
        try {

 

            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

 

            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

 

            Document document = documentBuilder.newDocument();

 

            

            // config element

            Element config = document.createElement("config");

            document.appendChild(config);

 

            // assunto element

            Element assunto = document.createElement("assunto");

            assunto.appendChild(document.createTextNode(_assunto));

            config.appendChild(assunto);

 

            // message element

            Element message = document.createElement("message");

            message.appendChild(document.createTextNode("\n" + _message));

            config.appendChild(message);

 

            // data element

            Element data = document.createElement("data");

            data.appendChild(document.createTextNode(_data));

            config.appendChild(data);

 

            // hora elements

            Element hora = document.createElement("hora");

            hora.appendChild(document.createTextNode(_hora));

            config.appendChild(hora);

 

            // create the xml file

            //transform the DOM Object to an XML File

            TransformerFactory transformerFactory = TransformerFactory.newInstance();

            Transformer transformer = transformerFactory.newTransformer();

            DOMSource domSource = new DOMSource(document);


            
 
           File writer = new File(System.getProperty("user.dir") + "/config.xml");
        
            
           StreamResult result = new StreamResult(writer);
        
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");


            transformer.transform(domSource, result);
            
            System.out.println("Done update XML Config File");

 

        } catch (ParserConfigurationException pce) {

            pce.printStackTrace();

        } catch (TransformerException tfe) {

            tfe.printStackTrace();

        }
    }
    
    
}
