/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PeliTilanne.java
 *
 * Created on Oct 30, 2012, 7:40:19 PM
 */
/**
 *
 * @author jarno
 */

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;


public class PeliTilanne extends javax.swing.JFrame {
    private Javalabra menu;
    private boolean[][] status;
    private int width;
    private int height;
    private int squareWidth;
    
    /** Creates new form PeliTilanne */
    public PeliTilanne() {
        initComponents();
    }
    
    public PeliTilanne(Javalabra handle) {
        initComponents();
        menu = handle;
        this.squareWidth=20;
        this.width=12;
        this.height=15;
        this.status = new boolean[this.width][this.height];
        for (int i=0;i<this.width;++i){
            for (int j=0;j<this.height;++j){
                this.status[i][j]=true;
            }
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jButton1.setText("Tauko");

        jButton2.setText("Lopeta");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(327, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addContainerGap(310, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /*
     * Action Performed for button "Lopeta"
     */
    
private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    this.setVisible(false);
    (this.menu).setVisible(true);
    this.paintComponents(null);
}//GEN-LAST:event_jButton2ActionPerformed

/*
 * Method for drawing game-status
 */

@Override
public void paint(Graphics g) {
   
   super.paint(g);
   for (int i=0;i<this.status.length;++i){
       for(int j=0;j<this.status[i].length;++j){
           if (status[i][j]) {
                g.setColor(Color.yellow);
                g.fillRect (20*i+50, 20*j+50, this.squareWidth, this.squareWidth);
           }
           else{
               g.clearRect (20*i+50, 20*j+50, this.squareWidth, this.squareWidth);
           }
       }
   }
}

private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
// TODO add your handling code here:
}//GEN-LAST:event_formKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    // End of variables declaration//GEN-END:variables
}
