/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.swing.JOptionPane;

/**
 *
 * @author mateo
 */
public class GUI extends javax.swing.JFrame {

    private String selectedMethod = "";
    /**
     * Creates new form GUI
     */
    public GUI() {
        initComponents();
    }
    
    public void execute(){
        // TODO add your handling code here:
        String file = "";
        String args = "";
        if(selectedMethod.equals("jacobi")){
            file = "jacobi.py";
            String tolerance = txtTolerance.getText();
            String maxIters = txtMaxIterations.getText();
            String initial = initialValuesText.getText();

            args = tolerance + " " + maxIters + " "+initial;
            
        }else if(selectedMethod.equals("seidel")){
            file = "gaussSeidel.py";
            String tolerance = txtTolerance.getText();
            String maxIters = txtMaxIterations.getText();
            String initial = initialValuesText.getText();

            args = tolerance + " " + maxIters + " "+ initial;
            
        }else if(selectedMethod.equals("jacobiRela")){
            file = "jacobiRelajado.py";
            String tolerance = txtTolerance.getText();
            String maxIters = txtMaxIterations.getText();
            String initial = initialValuesText.getText();
            String landa = landaText.getText();

            args = tolerance + " " + maxIters + " "+initial+" "+landa;
            
        }else if(selectedMethod.equals("seidelRela")){
            file = "gaussSeidelRelajado.py";
            String tolerance = txtTolerance.getText();
            String maxIters = txtMaxIterations.getText();
            String initial = initialValuesText.getText();
            String landa = landaText.getText();

            args = tolerance + " " + maxIters + " "+initial+" "+landa;
            
        }else if(selectedMethod.equals("crout")){
            file = "luCrout.py";
        }else if(selectedMethod.equals("lucho")){
            file = "luCholesky.py";
        }else if(selectedMethod.equals("doolittle")){
            file = "luDoolittle.py";
        }else if(selectedMethod.equals("gauss")){
            file = "luGauss.py";
        }else if(selectedMethod.equals("gaussPartial")){
            file = "gaussPivoteoParcial.py";
        }else if(selectedMethod.equals("gaussTotal")){
            file = "gaussPivoteoTotal.py";
        }else if(selectedMethod.equals("gaussSimple")){
            file = "elimGaussNormal.py";
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
                //System.out.println("Here is the standard output of the command:\n");
                output.setText("");
                output.append("Here is the standard output of the command:\n");
                while ((s = std[0].readLine()) != null) {
                    //System.out.println(s);
                    output.append(s + "\n");
                }
            }
        }
        catch (IOException e) {
            System.out.println("exception happened - here's what I know: ");
            e.printStackTrace();
            System.exit(-1);
        }
    }
    
    public BufferedReader[] executeCommand(String file, String args){

        BufferedReader stdOutput = null;
        BufferedReader stdError = null;
        try {
            
	    // run the Unix "ps -ef" command
            // using the Runtime exec method:
            String ruta = "/Users/marcossierra/Dropbox/Eafit/Semestre6/"
                    + "AnalisisNumerico/Proyecto/parallelGames/InterfaceMethodsPython/";
            String ruta_args = "python " + ruta+file+" " + ruta + "matrix.txt "+args;
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
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnExecCholesky = new javax.swing.JButton();
        btnExecute = new javax.swing.JButton();
        btnJacobi = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtTolerance = new javax.swing.JTextField();
        txtMaxIterations = new javax.swing.JTextField();
        btnExecCrout = new javax.swing.JButton();
        btnExecDooLittle = new javax.swing.JButton();
        btnExecGauss1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        output = new javax.swing.JTextArea();
        btnSeidel = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        initialValuesText = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnJacobiRel = new javax.swing.JButton();
        btnSeidelRela = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        landaText = new javax.swing.JTextField();
        gaussPP = new javax.swing.JButton();
        gaussPT = new javax.swing.JButton();
        gaussSimple = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnExecCholesky.setText("Execute Lu Cholesky");
        btnExecCholesky.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExecCholeskyActionPerformed(evt);
            }
        });

        btnExecute.setText("Execute");
        btnExecute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExecuteActionPerformed(evt);
            }
        });

        btnJacobi.setText("Select Jacobi");
        btnJacobi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJacobiActionPerformed(evt);
            }
        });

        jLabel1.setText("Tolerance");

        jLabel2.setText("Max Iterations");

        txtTolerance.setEnabled(false);

        txtMaxIterations.setEnabled(false);

        btnExecCrout.setText("Execute Lu Crout");
        btnExecCrout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExecCroutActionPerformed(evt);
            }
        });

        btnExecDooLittle.setText("Execute Lu DooLittle");
        btnExecDooLittle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExecDooLittleActionPerformed(evt);
            }
        });

        btnExecGauss1.setText("Execute Lu Gauss");
        btnExecGauss1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExecGauss1ActionPerformed(evt);
            }
        });

        output.setColumns(20);
        output.setRows(5);
        jScrollPane1.setViewportView(output);

        btnSeidel.setText("Select Seidel");
        btnSeidel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeidelActionPerformed(evt);
            }
        });

        jLabel3.setText("Initial Values");

        jLabel4.setText("i.e [1,2,3]");

        btnJacobiRel.setText("Select Jacobi Relajado");
        btnJacobiRel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJacobiRelActionPerformed(evt);
            }
        });

        btnSeidelRela.setText("Select Seidel Relajado ");
        btnSeidelRela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeidelRelaActionPerformed(evt);
            }
        });

        jLabel5.setText("Landa");

        gaussPP.setText("Execute Gauss Partial");
        gaussPP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gaussPPActionPerformed(evt);
            }
        });

        gaussPT.setText("Execute Gauss Total");
        gaussPT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gaussPTActionPerformed(evt);
            }
        });

        gaussSimple.setText("Execute Gauss");
        gaussSimple.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gaussSimpleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnJacobi)
                    .addComponent(btnSeidel)
                    .addComponent(btnExecCrout)
                    .addComponent(btnJacobiRel, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSeidelRela, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(27, 27, 27)
                                .addComponent(txtTolerance, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtMaxIterations, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel4)
                                    .addComponent(initialValuesText)
                                    .addComponent(landaText, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(btnExecute))
                    .addComponent(btnExecDooLittle)
                    .addComponent(gaussPP)
                    .addComponent(gaussPT)
                    .addComponent(btnExecCholesky)
                    .addComponent(gaussSimple)
                    .addComponent(btnExecGauss1))
                .addGap(230, 230, 230)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 655, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnJacobi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSeidel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnJacobiRel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSeidelRela)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtTolerance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtMaxIterations, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(initialValuesText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(landaText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42)
                        .addComponent(btnExecute)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(gaussPP)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(gaussPT)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(gaussSimple)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExecCholesky)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExecCrout)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnExecDooLittle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExecGauss1)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExecCholeskyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExecCholeskyActionPerformed
        selectedMethod = "lucho";
        txtTolerance.setEnabled(false);
        txtMaxIterations.setEnabled(false);
        execute();
    }//GEN-LAST:event_btnExecCholeskyActionPerformed

    private void btnExecuteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExecuteActionPerformed
        execute();
    }//GEN-LAST:event_btnExecuteActionPerformed

    private void btnJacobiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJacobiActionPerformed
        selectedMethod = "jacobi";
        txtTolerance.setEnabled(true);
        txtMaxIterations.setEnabled(true);
    }//GEN-LAST:event_btnJacobiActionPerformed

    private void btnExecCroutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExecCroutActionPerformed
        selectedMethod = "crout";
        txtTolerance.setEnabled(false);
        txtMaxIterations.setEnabled(false);
        execute();
    }//GEN-LAST:event_btnExecCroutActionPerformed

    private void btnExecDooLittleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExecDooLittleActionPerformed
        selectedMethod = "doolittle";
        txtTolerance.setEnabled(false);
        txtMaxIterations.setEnabled(false);
        execute();
    }//GEN-LAST:event_btnExecDooLittleActionPerformed

    private void btnExecGauss1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExecGauss1ActionPerformed
        selectedMethod = "gauss";
        txtTolerance.setEnabled(false);
        txtMaxIterations.setEnabled(false);
        execute();
    }//GEN-LAST:event_btnExecGauss1ActionPerformed

    private void btnSeidelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeidelActionPerformed
        selectedMethod = "seidel";
        txtTolerance.setEnabled(true);
        txtMaxIterations.setEnabled(true);
    }//GEN-LAST:event_btnSeidelActionPerformed

    private void btnJacobiRelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJacobiRelActionPerformed
        // TODO add your handling code here:
        selectedMethod = "jacobiRela";
        txtTolerance.setEnabled(true);
        txtMaxIterations.setEnabled(true);
        landaText.setEditable(true);
    }//GEN-LAST:event_btnJacobiRelActionPerformed

    private void btnSeidelRelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeidelRelaActionPerformed
        // TODO add your handling code here:
        selectedMethod = "seidelRela";
        txtTolerance.setEnabled(true);
        txtMaxIterations.setEnabled(true);
        landaText.setEditable(true);
    }//GEN-LAST:event_btnSeidelRelaActionPerformed

    private void gaussPPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gaussPPActionPerformed
        // TODO add your handling code here:
        selectedMethod = "gaussPartial";
        txtTolerance.setEnabled(false);
        txtMaxIterations.setEnabled(false);
        execute();
    }//GEN-LAST:event_gaussPPActionPerformed

    private void gaussPTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gaussPTActionPerformed
        // TODO add your handling code here:
        selectedMethod = "gaussTotal";
        txtTolerance.setEnabled(false);
        txtMaxIterations.setEnabled(false);
        execute();
    }//GEN-LAST:event_gaussPTActionPerformed

    private void gaussSimpleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gaussSimpleActionPerformed
        // TODO add your handling code here:
        selectedMethod = "gaussSimple";
        txtTolerance.setEnabled(false);
        txtMaxIterations.setEnabled(false);
        execute();
    }//GEN-LAST:event_gaussSimpleActionPerformed

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
    private javax.swing.JButton btnExecCholesky;
    private javax.swing.JButton btnExecCrout;
    private javax.swing.JButton btnExecDooLittle;
    private javax.swing.JButton btnExecGauss1;
    private javax.swing.JButton btnExecute;
    private javax.swing.JButton btnJacobi;
    private javax.swing.JButton btnJacobiRel;
    private javax.swing.JButton btnSeidel;
    private javax.swing.JButton btnSeidelRela;
    private javax.swing.JButton gaussPP;
    private javax.swing.JButton gaussPT;
    private javax.swing.JButton gaussSimple;
    private javax.swing.JTextField initialValuesText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField landaText;
    private javax.swing.JTextArea output;
    private javax.swing.JTextField txtMaxIterations;
    private javax.swing.JTextField txtTolerance;
    // End of variables declaration//GEN-END:variables
}
