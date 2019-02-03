/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mi.forms;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.FileDataSource;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.xml.parsers.ParserConfigurationException;
import mi.functions.JMail;
import mi.functions.mailValidator;
import org.xml.sax.SAXException;

/**
 *
 * @author Pek
 */
public class FrmCanceling extends javax.swing.JFrame {

    
        private List<String> listMails = null;
        
        private static FrmCanceling obj = null;
        
        
    
    private FrmCanceling() {
        initComponents();
        
    }

    
    public static FrmCanceling getObj () { 
    
        if ( obj == null) { 
          
            obj = new FrmCanceling();
        }
        
        return obj;
    
    }

    
    public void setListMails(List<String> listMails) {
        this.listMails = listMails;
    }
    
    
    private int i = 0;
    private Runnable t1 = new Runnable() {
        public void run() {
            
            
            try{
                
                
            if (txUsuario.getText().length() > 0 && txPassword.getPassword().length > 0) { 
        
            
            int total = listMails.size();
            
            
             DefaultListModel model = new DefaultListModel();
             
            for (int i = 0; i < total; i++) {                
            
                
                model.addElement(" Cancelando " + listMails.get(i) + "...");    
                JMail.Send_CancelGmail(listMails.get(i), txUsuario.getText(), new String(txPassword.getPassword()));
                model.addElement(listMails.get(i) + " Cancelado com Sucesso...");                    
                jProgressBar1.setValue((i + 1) * 100 / total);
                jList_LOG.setModel(model);
                
            }
            
            
            model.addElement(" Pronto ...");    
            jList_LOG.setModel(model);
            
        }
 
        } catch (Exception e) { 
            
        }}
    };
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txUsuario = new javax.swing.JTextField();
        txPassword = new javax.swing.JPasswordField();
        jButton_Cancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList_LOG = new javax.swing.JList<>();
        jProgressBar1 = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Usuario:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Senha:");

        jButton_Cancelar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton_Cancelar.setText("Cancelar");
        jButton_Cancelar.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.red));
        jButton_Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_CancelarActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(jList_LOG);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_Cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txUsuario)
                                    .addComponent(txPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton_Cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_CancelarActionPerformed

            DefaultListModel model = new DefaultListModel();

            model.addElement("Iniciando cancelamento de convites..");
            
            model.addElement("isso pode levar alguns minutos ...");
            
            jList_LOG.setModel(model);
            
        
            txPassword.setEnabled(false);
            txUsuario.setEnabled(false);
            jButton_Cancelar.setEnabled(false);
               
            new Thread(t1).start();
            
        
    }//GEN-LAST:event_jButton_CancelarActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

            DefaultListModel model = new DefaultListModel();
            
            model.clear();
        
            model.addElement("O Seguintes Convites Serão Cancelados ... ");
            
            listMails.forEach((listMail) -> {
                model.addElement(listMail);
            });
    
            model.addElement("...........................");
            model.addElement(" \\/ ");
            model.addElement("Faça o login e pressione Cancelar para continuar ...");
            
            
            jList_LOG.setModel(model);
        
        
        
    }//GEN-LAST:event_formWindowOpened

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
            java.util.logging.Logger.getLogger(FrmCanceling.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmCanceling.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmCanceling.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmCanceling.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmCanceling().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Cancelar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList<String> jList_LOG;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPasswordField txPassword;
    private javax.swing.JTextField txUsuario;
    // End of variables declaration//GEN-END:variables
}
