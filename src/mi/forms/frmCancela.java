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
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import mi.functions.JMail;
import mi.models.convite;



public class frmCancela extends javax.swing.JFrame {

    private static frmCancela obj = null;
    
    private frmCancela() {
        initComponents();
        
                
    }

    
    public static frmCancela getObj() { 
        
        if ( obj == null ) { 
        
            obj = new frmCancela();
        } return obj;
    
    }
    
    private void refresh_cancelados() { 
    
        
        Gson gson = new Gson();
 
        try {
 
            URL url = new URL("https://dancingbrasil.com.br/thefour/get_cancels.php?token=tfc007");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();            
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                
                DefaultListModel model = new DefaultListModel();
                model.addElement("Failed : HTTP Error code : " + conn.getResponseCode());                
                jList1.setModel(model);
                
            }
             InputStreamReader in = new InputStreamReader(conn.getInputStream());
             BufferedReader br = new BufferedReader(in);
 
            //Converte String JSON para objeto Java
            convite[] obj2 = gson.fromJson(br, convite[].class);
            
             DefaultListModel model = new DefaultListModel();
             
            for (convite object : obj2) {
              model.addElement(object.getEmail());
            }
            
            jList1.setModel(model);
            
            //jlNumberOfConvites.setText(obj.length + " Convites Enviados ");
 
        } catch (IOException e) {
        }

    
    }
    
    
     private void refresh_Convidados() { 
    
        
        Gson gson = new Gson();
 
        try {
 
            URL url = new URL("https://dancingbrasil.com.br/thefour/get_convidados.php?token=tfc007");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();            
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                
                DefaultListModel model = new DefaultListModel();
                model.addElement("Failed : HTTP Error code : " + conn.getResponseCode());                
                jListConvidados.setModel(model);
                
            }
             InputStreamReader in = new InputStreamReader(conn.getInputStream());
             BufferedReader br = new BufferedReader(in);
 
            //Converte String JSON para objeto Java
            convite[] obj = gson.fromJson(br, convite[].class);
            
             DefaultListModel model = new DefaultListModel();
             
            for (convite object : obj) {
              model.addElement(object.getEmail());
            }
            
            jListConvidados.setModel(model);
 
        } catch (IOException e) {
            e.printStackTrace();
        }

    
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListConvidados = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Lista de Convidados");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Convites Cancelados");

        jList1.setForeground(java.awt.Color.red);
        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList1.setEnabled(false);
        jScrollPane1.setViewportView(jList1);

        jListConvidados.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { " ", " ", " ", "                         Carregando Por Favor Aguarde...", " ", "     \t                     Isso pode levar alguns minutos." };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jListConvidados);

        jButton1.setText("Cancelar Convites");
        jButton1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, java.awt.Color.red));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(118, 118, 118))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        int[] selectedIx = jListConvidados.getSelectedIndices();
        
        
        if ( selectedIx.length > 0 ) {
        
         
        int dialogResult = JOptionPane.showConfirmDialog(null, "Tem Certeza que Deseja Cancelar "+ selectedIx.length +" convites?","Warning", JOptionPane.YES_NO_OPTION);
            
            if(dialogResult == JOptionPane.YES_OPTION){
                
                
                List<String> listCancel = new ArrayList<>();
                
                
                
                for (int i = 0; i < selectedIx.length; i++) {
            
                System.out.println(jListConvidados.getModel().getElementAt(selectedIx[i]));
                
                String TO = jListConvidados.getModel().getElementAt(selectedIx[i]);
                
                listCancel.add(TO);
                
                }
                
                FrmCanceling FormCancel = FrmCanceling.getObj();
                
                FormCancel.setListMails(listCancel);
                
                FormCancel.setVisible(true);
                
                this.setVisible(false);
                

                
            }
        }

        
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened


        
        refresh_Convidados();
        
        refresh_cancelados();
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
            java.util.logging.Logger.getLogger(frmCancela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmCancela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmCancela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmCancela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmCancela().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList<String> jList1;
    private javax.swing.JList<String> jListConvidados;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
