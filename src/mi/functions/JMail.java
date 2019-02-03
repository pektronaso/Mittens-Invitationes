/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mi.functions;


import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import mi.forms.frmHistorico;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author Pek
 */
public class JMail {
 
    
    
    
     private static String _assunto, _message, _eventDate;    
    private static void get_xml_infos() throws SAXException, IOException, ParserConfigurationException { 
    
        System.out.println("Carregando Arquivo de configuração XML.");        
        
        
        File xmlFile = new File(System.getProperty("user.dir") + "/config.xml");
        
        //JOptionPane.showMessageDialog(null, xmlFile.toPath());

        
        System.out.println("Arquivo Carregado...");        
        
        System.out.println("Montando documento...");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(xmlFile);		        	
        doc.getDocumentElement().normalize();
        
        _assunto = doc.getElementsByTagName("assunto").item(0).getTextContent();
        _message = doc.getElementsByTagName("message").item(0).getTextContent();
        _eventDate = doc.getElementsByTagName("data").item(0).getTextContent();
        

    
    }

    
   
    
    public static void Send_CancelGmail(String _To, String _User, String _Password){ 
    
        Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.port", "465");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.socketFactory.port", "465");
		prop.put("mail.smtp.socketFactory.fallback", "false");
		prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                prop.put("mail.debug", "true");

 
            Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() { 
                
                                    @Override
                                    protected PasswordAuthentication getPasswordAuthentication() {
                
                                    return new PasswordAuthentication(_User, _Password);
                             }
                        });
 
            
            // Ativa Debug para sessão */
            
            session.setDebug(true);
            
            try {
 
                  Message message = new MimeMessage(session);
                  message.setFrom(new InternetAddress(_User)); //Remetente
 
                  Address[] toUser = InternetAddress.parse(_To);   // Destinatarios
 
                  message.setRecipients(Message.RecipientType.TO, toUser);
                  message.setSubject("TheFour - Convite Cancelado");//Assunto
                  
                  
                    // cria a primeira parte da mensagem
                 MimeBodyPart mbp1 = new MimeBodyPart();
                 mbp1.setText("Informe: O convite para " + _To +" foi cancelado, e não poderá mais ser utilizado! \n Esperamos ve-lo de novo. ");

                  
                Multipart mp = new MimeMultipart();
      
                mp.addBodyPart(mbp1);
      
                
                message.setContent(mp);

                
                message.setSentDate(new Date());
                
                Transport.send(message);
 
                  
                System.out.println("Convite Cancelado!");
                
 
             } catch (MessagingException e) {
                  throw new RuntimeException(e);
            }
            
            RemoveConvite(_To);
            
    
            
            
    
    }
    
    
     public static void SendGMail(String _To, String _User, String _Password, FileDataSource _filename) throws SAXException, IOException, ParserConfigurationException { 
    
                
                Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.port", "465");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.socketFactory.port", "465");
		prop.put("mail.smtp.socketFactory.fallback", "false");
		prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                prop.put("mail.debug", "true");

 
            Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() { 
                
                                    @Override
                                    protected PasswordAuthentication getPasswordAuthentication() {
                
                                    return new PasswordAuthentication(_User, _Password);
                             }
                        });
 
            
            // Ativa Debug para sessão */
            
            session.setDebug(true);
            
            
            get_xml_infos();
            
            try {
 
                  Message message = new MimeMessage(session);
                  message.setFrom(new InternetAddress(_User)); //Remetente
 
                  Address[] toUser = InternetAddress.parse(_To);   // Destinatarios
 
                  message.setRecipients(Message.RecipientType.TO, toUser);
                  message.setSubject(_assunto);//Assunto
                  
                  
                    // cria a primeira parte da mensagem
                 MimeBodyPart mbp1 = new MimeBodyPart();
                 mbp1.setText(_message);

                  // cria a segunda parte da mensage
                 MimeBodyPart mbp2 = new MimeBodyPart();
                 
                 
                 FileDataSource termo_auth = new FileDataSource(System.getProperty("user.dir") + "/termos/TERMO DE AUTORIZACAO DE USO DE IMAGEM E VOZ.docx");
                 FileDataSource termo_confiabilidade = new FileDataSource(System.getProperty("user.dir") + "/termos/Termo de Confidencialidade Endemol Brasil_THE FOUR.docx");
                 
                 
                 MimeBodyPart mbp3 = new MimeBodyPart();
                 
                 MimeBodyPart mbp4 = new MimeBodyPart();

                 // anexa o arquivo na mensagem
            
             
                 mbp2.setDataHandler(new DataHandler(_filename));
      
                 mbp2.setFileName(_filename.getName());
                 
                 mbp3.setDataHandler(new DataHandler(termo_auth));
      
                 mbp3.setFileName(termo_auth.getName());
                 
                 mbp4.setDataHandler(new DataHandler(termo_confiabilidade));
      
                 mbp4.setFileName(termo_confiabilidade.getName());

                Multipart mp = new MimeMultipart();
      
                mp.addBodyPart(mbp1);
      
                mp.addBodyPart(mbp2);
                
                mp.addBodyPart(mbp3);
                
                mp.addBodyPart(mbp4);

                // adiciona a Multipart na mensagem      
      
      
                message.setContent(mp);

                // configura a data: cabecalho
      
                
                message.setSentDate(new Date());
                
                  
                  //Método para enviar a mensagem criada
                  Transport.send(message);
 
                  ADDH(_To,_eventDate);
                  
                  System.out.println("Feito!");
                
 
             } catch (MessagingException e) {
                  throw new RuntimeException(e);
            }
            
       
      }
     
      private static void RemoveConvite(String email) { 
        try { 
            
            
            URL url = new URL("https://dancingbrasil.com.br/thefour/cancel.php?token=tfc007&email="+email.trim());            
            
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 201) {
                throw new RuntimeException("Failed : HTTP Error code : "
                        + conn.getResponseCode());
            }
            
        } catch (IOException ex) {
            Logger.getLogger(frmHistorico.class.getName()).log(Level.SEVERE, null, ex);
        }
       
            
    }
        

      
          private static void ADDH(String email, String date) { 
        try { 
            
            date = date.trim();
            
            date = date.replaceAll("\\u00A0", "_");
            
            date = date.replace(' ', '_');            
            
            URL url = new URL("https://dancingbrasil.com.br/thefour/add.php?token=tfc007&email="+email.trim()+"&dataEvent="+date);            
            
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 201) {
                throw new RuntimeException("Failed : HTTP Error code : "
                        + conn.getResponseCode());
            }
            
        } catch (IOException ex) {
            Logger.getLogger(frmHistorico.class.getName()).log(Level.SEVERE, null, ex);
        }
       
            
    }
    
    
}
