
package mi.forms;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.FileDataSource;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import mi.functions.ConviteGen;
import mi.functions.JMail;
import mi.functions.QRgen;
import mi.functions.mailValidator;
import org.apache.commons.io.FileUtils;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;



public class frmSendMail extends javax.swing.JFrame {

    private static frmSendMail obj = null;
    
    private JList listMails = null;
    
     
    private frmSendMail() {
        initComponents();
         
       
    }
    
    public void addImageWatermark(File watermark, String type, InputStream source, File destination) throws IOException {
        
        BufferedImage image = ImageIO.read(source);
        BufferedImage overlay = resize(ImageIO.read(watermark), 190, 200);

        // determine image type and handle correct transparency
        int imageType = "png".equalsIgnoreCase(type) ? BufferedImage.TYPE_INT_ARGB : BufferedImage.TYPE_INT_RGB;
        BufferedImage watermarked = new BufferedImage(image.getWidth(), image.getHeight(), imageType);

        // initializes necessary graphic properties
        Graphics2D w = (Graphics2D) watermarked.getGraphics();
        w.drawImage(image, 0, 0, null);
        AlphaComposite alphaChannel = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f);
        w.setComposite(alphaChannel);

        // calculates the coordinate where the String is painted
        int centerX = 1740;
        int centerY = 420;

        // add text watermark to the image
        w.drawImage(overlay, centerX, centerY, null);
        ImageIO.write(watermarked, type, destination);
        w.dispose();
    }

    private static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }


    
    private void Generate_Convite (String email) throws IOException, ParserConfigurationException, ParserConfigurationException, SAXException{
    
        
         File xmlFile = new File(System.getProperty("user.dir") + "/config.xml");
        


        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(xmlFile);		        	
        doc.getDocumentElement().normalize();
        
        
        
        String data = doc.getElementsByTagName("data").item(0).getTextContent();
        String hora = doc.getElementsByTagName("hora").item(0).getTextContent();
        
        QRgen.GEN_QR_FROM_TEXT(email);
        
         InputStream initialStream = getClass().getResourceAsStream("/mi/resources/ticket.png");
 
        File ticket = new File(System.getProperty("user.dir") + "/temp/ticket.png");
 
        try {
            FileUtils.copyInputStreamToFile(initialStream, ticket);
        } catch (IOException ex) {
            Logger.getLogger(frmHome.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        File qrcode = new File(System.getProperty("user.dir") + "/temp/QRCode.png");
        
        File temp_convite = new File(System.getProperty("user.dir") + "/temp/temp_convite.png");         
        
        File final_convite = new File(System.getProperty("user.dir") + "/temp/convite.png");         
        
        
        
        try {
            ConviteGen.addImageWatermark(qrcode, "png", ticket, temp_convite);
            
            ConviteGen.addTextWatermark(email, "png", temp_convite, final_convite, 1300, 150);
            
            ConviteGen.addTextWatermark(data, "png", final_convite, final_convite, 30, 425);
            
            ConviteGen.addTextWatermark(hora, "png", final_convite, final_convite, 30, 500);
            
            
            FileUtils.deleteQuietly(qrcode);
            FileUtils.deleteQuietly(temp_convite);
            FileUtils.deleteQuietly(ticket);
            
        } catch (IOException ex) {
            Logger.getLogger(frmHome.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    
    private int i = 0;
    private Runnable t1 = new Runnable() {
        public void run() {
            
            
            try{
         int total = listMails.getModel().getSize();
   
        if(mailValidator.isValid(txtUser.getText().trim()) && txtPassword.getPassword().length > 0) { 

         
         
            DefaultListModel model = new DefaultListModel();
         
            for (i = i; i < total; i++) {
                     
            try {
                
                model.addElement("Enviando convite " + (i+1) + " isso pode levar alguns minutos...");
                jlistLOG.setModel(model);
                
                Generate_Convite(listMails.getModel().getElementAt(i).toString());
                
                FileDataSource file = new FileDataSource(System.getProperty("user.dir") + "/temp/convite.png");
                
                JMail.SendGMail(listMails.getModel().getElementAt(i).toString(), txtUser.getText().trim(), new String(txtPassword.getPassword()), file);
                
                model.addElement("Convite enviado para " + listMails.getModel().getElementAt(i) + " com Sucesso! \n");
                
                lbStatus.setText((i+1) +" de "+ total +" Convites enviados.");
                
                jProgressBar.setValue((i + 1) * 100 / total);
                
               
                
            } catch (SAXException | IOException | ParserConfigurationException ex) {
                Logger.getLogger(frmSendMail.class.getName()).log(Level.SEVERE, null, ex);
            }
            
         
         
        jlistLOG.setModel(model);
        }
            
            model.addElement("Concluído ...");
            jlistLOG.setModel(model);
        
        }   
                
                
            } catch (Exception e){}
 
        }
    };
    
    
    public void setListMails(JList listMails) {
        this.listMails = listMails;
       
        lbStatus.setText("0 de "+ listMails.getModel().getSize() +" Convites enviados.");
    }
    
    
    
    public static frmSendMail getObj(JList MailList) { 
    
        if(obj == null) { 
        obj = new frmSendMail();        
        }
        obj.setListMails(MailList);
        
        return obj;
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        txtUser = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jlistLOG = new javax.swing.JList<>();
        lbStatus = new javax.swing.JLabel();
        jProgressBar = new javax.swing.JProgressBar();
        txtPassword = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setForeground(new java.awt.Color(102, 102, 0));
        jButton1.setText("ENVIAR");
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 0)));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Usuario:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Senha:");

        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(51, 51, 255)));

        jlistLOG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlistLOGMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jlistLOG);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        lbStatus.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbStatus.setText("0 de 0 Convites enviados.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtUser)
                                    .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(lbStatus)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(38, 38, 38)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbStatus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        DefaultListModel model = new DefaultListModel();
        model.addElement("Preparando para enviar lista de convites ...");
        jlistLOG.setModel(model);
        
            this.jButton1.setEnabled(false);
            this.txtPassword.setEnabled(false);
            this.txtUser.setEnabled(false);
            
           new Thread(t1, "Thread 1").start();
           
    
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jlistLOGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlistLOGMouseClicked

            
             
    }//GEN-LAST:event_jlistLOGMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmSendMail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmSendMail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmSendMail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmSendMail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmSendMail().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> jlistLOG;
    private javax.swing.JLabel lbStatus;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
