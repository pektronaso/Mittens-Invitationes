/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mi.forms;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import mi.models.convite;

/**
 *
 * @author Pek
 */
public class frmHistorico extends javax.swing.JFrame {

    private static frmHistorico obj = null;
    
    private frmHistorico() {
        initComponents();
    }
    
    public static frmHistorico getObj() {
    
        if(obj == null) { 
         obj = new frmHistorico();
        }        
        return obj;
        
    }
    
    private void clear() { 
            
        
        try { 
            URL url = new URL("https://dancingbrasil.com.br/thefour/clear.php?token=tfc007");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 205) {
                throw new RuntimeException("Failed : HTTP Error code : "
                        + conn.getResponseCode());
            }
            
        } catch (IOException ex) {
            Logger.getLogger(frmHistorico.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    
    private void refresh() { 
    
        
        Gson gson = new Gson();
 
        try {
 
            URL url = new URL("https://dancingbrasil.com.br/thefour/get_historico.php?token=tfc007");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();            
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                DefaultListModel model = new DefaultListModel();
                model.addElement("Failed : HTTP Error code : " + conn.getResponseCode());                
                jListConviteHistorico.setModel(model);
            }
             InputStreamReader in = new InputStreamReader(conn.getInputStream());
             BufferedReader br = new BufferedReader(in);
 
            //Converte String JSON para objeto Java
            convite[] obj = gson.fromJson(br, convite[].class);
            
             DefaultListModel model = new DefaultListModel();
             
            for (convite object : obj) {
              model.addElement("Para ->" + object.getEmail() + " em -> " + object.getData_send());
            }
            
            jListConviteHistorico.setModel(model);
            
            jlNumberOfConvites.setText(obj.length + " Convites Enviados ");
 
        } catch (IOException e) {
            e.printStackTrace();
        }

    
    }

    
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jListConviteHistorico = new javax.swing.JList<>();
        jlNumberOfConvites = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jListConviteHistorico.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { " ", "                                                              CARREGANDO ....", " ", "                                                   Isso pode levar alguns minutos" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jListConviteHistorico);

        jlNumberOfConvites.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jlNumberOfConvites.setText("0 Convites Enviados");

        jButton1.setText("limpar histórico");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jlNumberOfConvites)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jlNumberOfConvites)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        
        clear();
        refresh();
                
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

        refresh();
        
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
            java.util.logging.Logger.getLogger(frmHistorico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmHistorico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmHistorico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmHistorico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmHistorico().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JList<String> jListConviteHistorico;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlNumberOfConvites;
    // End of variables declaration//GEN-END:variables
}
