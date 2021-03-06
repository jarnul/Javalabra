package Javalabra;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */

/*
 * gameMenu.java
 *
 * Created on Oct 30, 2012, 7:40:19 PM
 */
/**
 * The main game-window which renders game and updates it by implementing
 * abstract class ActionListener and using timer.
 *
 * @author jarno
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

public class GameMenu extends javax.swing.JFrame implements ActionListener {

    //game-instances handle for main menu
    private Javalabra menu;
    private int squareWidth;
    //the gameLogic-object for currently running tetris-game
    private GameLogic.GameLogic currentGame;
    private Timer timer;
    //the int[][]-table of last gamestatus
    private int[][] lastStatus;
    private int score;
    private boolean gameOver;
    private GameFiles files;
    private int blockColor;
    private int gameWidth;
    private int gameHeight;
    private boolean paused;

    /**
     * Creates new form gameMenu
     */
    public GameMenu() {
        initComponents();
    }

    /**
     * Creates new form gameMenu and fills the game-status table status and
     * rotations-table
     * @param handle The handle of the Javalabra-object that created this gameMenu-object
     */
    public GameMenu(Javalabra handle) {
        initComponents();
        menu = handle;
        this.paused=false;
        this.squareWidth = 20;
        this.gameWidth = 12;
        this.gameHeight = 15;
        this.score = 0;
        this.gameOver = false;
        this.blockColor = 0;
        this.files = new GameFiles("Player", 0);
        this.currentGame = new GameLogic.GameLogic(gameWidth, gameHeight);
        this.lastStatus = new int[gameWidth][gameHeight];
        for (int i = 0; i < this.lastStatus.length; ++i) {
            for (int j = 0; j < this.lastStatus[i].length; ++j) {
                this.lastStatus[i][j] = 0;
            }
        }
        drawGame(jLabel1.getGraphics());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane2 = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jButton1.setText("Pause");
        jButton1.setFocusPainted(false);
        jButton1.setFocusable(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Stop");
        jButton2.setFocusPainted(false);
        jButton2.setFocusable(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Start");
        jButton3.setFocusPainted(false);
        jButton3.setFocusable(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTextPane1.setEditable(false);
        jTextPane1.setFocusable(false);
        jScrollPane1.setViewportView(jTextPane1);

        jButton4.setText("Color");
        jButton4.setFocusPainted(false);
        jButton4.setFocusable(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel1.setFocusable(false);

        jTextPane2.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jTextPane2.setText("Controls:\nLeft arrow - move left\nRight arrow - move right\nUp arrow - rotate piece\nDown arrow - move down");
        jTextPane2.setFocusable(false);
        jScrollPane2.setViewportView(jTextPane2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton3)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(88, Short.MAX_VALUE))
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /*
     * Action Performed for button "Lopeta"
     */
private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    this.setVisible(false);
    (this.menu).setVisible(true);
    if (this.timer != null) {
        this.timer.stop();
        files.setScore(this.score);
        files.saveScore();
    }
}//GEN-LAST:event_jButton2ActionPerformed

    /*
     * Draw game in given graphics-object
     */
    private void drawGame(Graphics g) {
        int[][] tempStatus = this.currentGame.getGameStatus();
        for (int i = 0; i < tempStatus.length; ++i) {
            for (int j = 0; j < tempStatus[i].length; ++j) {
                drawBlock(g, tempStatus, i, j);
            }
        }
    }

    /*
     * Method to draw single block
     */
    private void drawBlock(Graphics g, int[][] tempStatus, int x, int y) {
        if (tempStatus[x][y] == 1) {
            g.setColor(new Color(0xEB7D00));
            g.fillRect(20 * x + 50, 20 * y + 50, this.squareWidth, this.squareWidth);
        } else if (tempStatus[x][y] == 2) {
            if (this.blockColor == 0) {
                g.setColor(Color.yellow);
            } else if (this.blockColor == 1) {
                g.setColor(Color.blue);
            } else {
                g.setColor(Color.red);
            }
            g.fillRect(20 * x + 50, 20 * y + 50, this.squareWidth, this.squareWidth);
        } else {
            //The colour of the background
            g.setColor(Color.darkGray);
            g.fillRect(20 * x + 50, 20 * y + 50, this.squareWidth, this.squareWidth);
        }
    }

    /*
     * Method for keypress
     */
private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    if (this.timer != null) {
        this.timer.stop();
        this.paused=true;
    }
}//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.paused=false;
        this.timer = new Timer(1000, this);
        timer.start();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (this.blockColor == 1) {
            this.blockColor = 0;
        } else if (this.blockColor == 0) {
            this.blockColor = 2;
        } else if (this.blockColor == 2) {
            this.blockColor = 1;
        }
        drawGame(jLabel1.getGraphics());
    }//GEN-LAST:event_jButton4ActionPerformed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        if (!this.gameOver && this.paused==false) {
            if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
                this.currentGame.movePiece(2);
            }
            if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
                this.currentGame.movePiece(1);
            }
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                this.currentGame.movePiece(0);
            }
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                updateGameStatus();
            }
            drawGame(jLabel1.getGraphics());
      }
    }//GEN-LAST:event_formKeyPressed

    /*
     * Method for implementing abstract class ActionListener, updates the
     * game-status after set times.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        updateGameStatus();
        drawGame(jLabel1.getGraphics());
    }

    private void updateGameStatus() {
        int tempScore = this.currentGame.updateGame();
        if (tempScore > -1) {
            this.score = this.score + 10 * tempScore;
            jTextPane1.setText(Integer.toString(this.score));
        } else {
            this.timer.stop();
            files.setScore(this.score);
            files.saveScore();
            JOptionPane.showMessageDialog(this,
                    "Your score was " + this.score,
                    "Game Over",
                    JOptionPane.INFORMATION_MESSAGE);
            this.currentGame = new GameLogic.GameLogic(this.gameWidth, this.gameHeight);
            this.score = 0;
            repaint();
            jTextPane1.setText(Integer.toString(this.score));
            drawGame(jLabel1.getGraphics());
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTextPane jTextPane2;
    // End of variables declaration//GEN-END:variables
}