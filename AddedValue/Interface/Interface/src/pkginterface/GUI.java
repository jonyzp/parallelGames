/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkginterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JOptionPane;

/**
 *
 * @author mateo
 */
public class GUI extends javax.swing.JFrame {

    /**
     * Creates new form GUI
     */
    String selectedMethod="";
            
    public GUI() {
        initComponents();
    }
    
    public BufferedReader[] executeCommand(String file, String args){

        BufferedReader stdOutput = null;
        BufferedReader stdError = null;
        try {
            
	    // run the Unix "ps -ef" command
            // using the Runtime exec method:
            String ruta_args = "python /Users/marcossierra/Dropbox/Eafit/Semestre6/AnalisisNumerico/Proyecto/parallelGames/AddedValue/"+file+" "+args;
            Process p = Runtime.getRuntime().exec(ruta_args);
            
            stdOutput = new BufferedReader(new 
                 InputStreamReader(p.getInputStream()));

            stdError = new BufferedReader(new 
                 InputStreamReader(p.getErrorStream()));
            
            //System.exit(0);
        }
        catch (IOException e) {
            System.out.println("exception happened - here's what I know: ");
            e.printStackTrace();
            System.exit(-1);
        }
        
        BufferedReader[] std = {stdOutput, stdError};
        
        return std;
    }
    
    public void execute(){
         String args="";
        String file="";
        if(selectedMethod.equals("newton")){
            file = "newton.py";
            String funct = txtfx.getText();
            String deriv = txtfpx.getText();
            String x0 = txtx0.getText();
            String toler = txttol.getText();
            String maxIters = txtIter.getText();

            args = funct + " " + deriv + " " + x0 + " " + toler + " " + maxIters;
        }else if(selectedMethod.equals("biseccion")){
            file = "biseccion.py";
            String funct = txtfx.getText();
            String x0 = txtx0.getText();
            String x1 = txtx1.getText();
            String toler = txttol.getText();
            String maxIters = txtIter.getText();

            args = funct + " " + x0 + " " + x1 + " " + toler + " " + maxIters;
            
        }else if(selectedMethod.equals("secante")){
            file = "secante.py";
            String funct = txtfx.getText();
            String x0 = txtx0.getText();
            String x1 = txtx1.getText();
            String toler = txttol.getText();
            String maxIters = txtIter.getText();

            args = funct + " " + x0 + " " + x1 + " " + toler + " " + maxIters;
            
        }else if(selectedMethod.equals("puntoFijo")){
            file = "puntoFijo.py";
            String funct = txtfx.getText();
            String functg = txtgx.getText();
            String x0 = txtx0.getText();
            String toler = txttol.getText();
            String maxIters = txtIter.getText();

            args = funct + " " + functg + " " + x0 + " " + toler + " " + maxIters;
            
        }else if(selectedMethod.equals("raicesMultiples")){
            file = "AddedValue/raicesMultiples.py";
            String funct = txtfx.getText();
            String deriv = txtfpx.getText();
            String deriv2 = txtfppx.getText();
            String x0 = txtx0.getText();
            String toler = txttol.getText();
            String maxIters = txtIter.getText();

            args = funct + " " + deriv +" "+deriv2 + " " + x0 + " " + toler + " " + maxIters;
            
        }else if(selectedMethod.equals("help")){
            file = "helpReader.py";
        }
        BufferedReader [] std = executeCommand(file, args);  
        String s = null;
        try{
            boolean error=false;
            // read any errors from the attempted command
            while ((s = std[1].readLine()) != null) {
                JOptionPane.showMessageDialog(this,s,"Error",JOptionPane.ERROR_MESSAGE);
                error=true;
            }  
            // read the output from the command
            if(!error){
                outputPane.setText("");
                //System.out.println("Here is the standard output of the command:\n");
                outputPane.append("Here is the standard output of the command:\n");
                while ((s = std[0].readLine()) != null) {
                    //System.out.println(s);
                    outputPane.append(s + "\n");
                }
            }
        }
        catch (IOException e) {
            System.out.println("exception happened - here's what I know: ");
            e.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnNewton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtfx = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtfpx = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtx0 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txttol = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtIter = new javax.swing.JTextField();
        btnBisection = new javax.swing.JButton();
        btnSecant = new javax.swing.JButton();
        btnfixpoint = new javax.swing.JButton();
        btnmultroots = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtx1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtfppx = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtgx = new javax.swing.JTextField();
        btnExecute = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        outputPane = new javax.swing.JTextArea();
        helpButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnNewton.setText("Execute Newton");
        btnNewton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewtonActionPerformed(evt);
            }
        });

        jLabel1.setText("f(x)");

        txtfx.setToolTipText("");
        txtfx.setEnabled(false);

        jLabel2.setText("f'(x)");

        txtfpx.setToolTipText("");
        txtfpx.setEnabled(false);

        jLabel3.setText("Xo");

        txtx0.setToolTipText("");
        txtx0.setEnabled(false);

        jLabel4.setText("Tol");

        txttol.setToolTipText("i.e: 0.0001");
        txttol.setEnabled(false);
        txttol.setName("i.e: 0.0001"); // NOI18N

        jLabel5.setText("Max Iters");

        txtIter.setToolTipText("        ");
        txtIter.setEnabled(false);

        btnBisection.setText("Execute Bisection");
        btnBisection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBisectionActionPerformed(evt);
            }
        });

        btnSecant.setText("Execute Secant");
        btnSecant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSecantActionPerformed(evt);
            }
        });

        btnfixpoint.setText("Execute Fixed Point");
        btnfixpoint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnfixpointActionPerformed(evt);
            }
        });

        btnmultroots.setText("Execute Multiple Roots");
        btnmultroots.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmultrootsActionPerformed(evt);
            }
        });

        jLabel6.setText("X1");

        txtx1.setToolTipText("");
        txtx1.setEnabled(false);

        jLabel7.setText("f''(x)");

        txtfppx.setToolTipText("");
        txtfppx.setEnabled(false);

        jLabel8.setText("G(x)");

        txtgx.setToolTipText("");
        txtgx.setEnabled(false);

        btnExecute.setText("Execute");
        btnExecute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExecuteActionPerformed(evt);
            }
        });

        outputPane.setColumns(20);
        outputPane.setRows(5);
        jScrollPane1.setViewportView(outputPane);

        helpButton.setText("Help");
        helpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel8)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtgx))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtx1))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtIter, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txttol))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel3))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtfpx, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                                        .addComponent(txtfx)
                                        .addComponent(txtx0)))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtfppx)))
                            .addComponent(btnExecute))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnNewton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBisection)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSecant)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnfixpoint)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnmultroots)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 370, Short.MAX_VALUE)
                        .addComponent(helpButton))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNewton)
                    .addComponent(btnBisection)
                    .addComponent(btnSecant)
                    .addComponent(btnfixpoint)
                    .addComponent(btnmultroots)
                    .addComponent(helpButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtfx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtfpx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtfppx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtgx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtx0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtx1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txttol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtIter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btnExecute)
                        .addGap(0, 186, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewtonActionPerformed
        // TODO add your handling code here:
        selectedMethod="newton";
        
        txtfx.setEnabled(true);
        txtfpx.setEnabled(true);
        txtx0.setEnabled(true);
        txttol.setEnabled(true);
        txtIter.setEnabled(true);
        
        txtfppx.setEnabled(false);
        txtgx.setEnabled(false);
        txtx1.setEnabled(false);
        
    }//GEN-LAST:event_btnNewtonActionPerformed

    private void btnBisectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBisectionActionPerformed
        
        selectedMethod="biseccion";
                
        txtfx.setEnabled(true);
        txtfpx.setEnabled(false);
        txtx0.setEnabled(true);
        txttol.setEnabled(true);
        txtIter.setEnabled(true);
        
        txtfppx.setEnabled(false);
        txtgx.setEnabled(false);
        txtx1.setEnabled(true);
    }//GEN-LAST:event_btnBisectionActionPerformed

    private void btnSecantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSecantActionPerformed
        selectedMethod="secante";
        
        txtfx.setEnabled(true);
        txtfpx.setEnabled(false);
        txtx0.setEnabled(true);
        txttol.setEnabled(true);
        txtIter.setEnabled(true);
        
        txtfppx.setEnabled(false);
        txtgx.setEnabled(false);
        txtx1.setEnabled(true);
    }//GEN-LAST:event_btnSecantActionPerformed

    private void btnfixpointActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnfixpointActionPerformed
        selectedMethod="puntoFijo";
        
        txtfx.setEnabled(true);
        txtfpx.setEnabled(false);
        txtx0.setEnabled(true);
        txttol.setEnabled(true);
        txtIter.setEnabled(true);
        
        txtfppx.setEnabled(false);
        txtgx.setEnabled(true);
        txtx1.setEnabled(false);
    }//GEN-LAST:event_btnfixpointActionPerformed

    private void btnmultrootsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmultrootsActionPerformed
        selectedMethod="raicesMultiples";
        txtfx.setEnabled(true);
        txtfpx.setEnabled(true);
        txtx0.setEnabled(true);
        txttol.setEnabled(true);
        txtIter.setEnabled(true);
        
        txtfppx.setEnabled(true);
        txtgx.setEnabled(false);
        txtx1.setEnabled(false);
    }//GEN-LAST:event_btnmultrootsActionPerformed

    private void btnExecuteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExecuteActionPerformed
        // TODO add your handling code here:
       execute();
    }//GEN-LAST:event_btnExecuteActionPerformed

    private void helpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpButtonActionPerformed
        // TODO add your handling code here:
        selectedMethod = "help";
        execute();
    }//GEN-LAST:event_helpButtonActionPerformed

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
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBisection;
    private javax.swing.JButton btnExecute;
    private javax.swing.JButton btnNewton;
    private javax.swing.JButton btnSecant;
    private javax.swing.JButton btnfixpoint;
    private javax.swing.JButton btnmultroots;
    private javax.swing.JButton helpButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea outputPane;
    private javax.swing.JTextField txtIter;
    private javax.swing.JTextField txtfppx;
    private javax.swing.JTextField txtfpx;
    private javax.swing.JTextField txtfx;
    private javax.swing.JTextField txtgx;
    private javax.swing.JTextField txttol;
    private javax.swing.JTextField txtx0;
    private javax.swing.JTextField txtx1;
    // End of variables declaration//GEN-END:variables
}
