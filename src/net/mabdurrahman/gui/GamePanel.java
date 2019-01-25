package net.mabdurrahman.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.JDialog;
import javax.swing.JOptionPane;


/**
 * The GamePanel Class
 * @author: MAbdurrahman
 * @date: September 22, 2014
 */
public class GamePanel extends JPanel {
    //Instance Variables
    public Ball ball = new Ball(this);
    public Paddle paddle = new Paddle(this);
    public int score = 1;
    private static final Color color = new Color(92, 242, 75);
    protected Timer timer;

    /**
     * GamePanel Constructor - Creates, initiates, and adds the key listener
     */
    public GamePanel() {
        ActionListener actionListener = new ActionListener() {
            /**
             * actionPerformed Method - Responds to the action taken each time
             * the timer fires.
             * @param  - the action event of the timer
             * @return Void
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if (this != null) {
                    move();

                }
                repaint();
            }//end of the actionPerformed Method
        };//end of the ActionListener action Class

        timer = new Timer(6, actionListener);
        /**
         * Anonymous MouseListener
         */
        addMouseListener(new MouseAdapter() {
            /**
             * mousePressed Method - Responds to the pressed of the mouse in the
             * panel.
             * @param - the event of pressing the mouse
             * @return Void
             */
            @Override
            public void mousePressed(MouseEvent e) {
                requestFocus();
                net.mabdurrahman.gui.Sound.BACKGROUND.loop();

            }//end of the mousePressed Method
        });
        /**
         * Anonymous FocusListener
         */
        addFocusListener(new FocusListener() {
            /**
             * focusGained Method - Responds to the mousePressed method of requesting focus
             * @param - the event of gaining focus
             * @return Void
             */
            @Override
            public void focusGained(FocusEvent e) {
                timer.start();
                repaint();
            }//end of the focusGained Method

            /**
             * focusLost Method - Responds
             * @param - the event of loosing focus
             * @return Void
             */
            @Override
            public void focusLost(FocusEvent e) {
                timer.stop();
                repaint();
            }//end of the focusLost Method
        });
        /**
         * Anonymous KeyListener
         */
        addKeyListener(new KeyListener() {
            /**
             * keyPressed Method - Responds to the pressing of the left or right arrow key
             * @param - the key event of pressing a key
             * @return void
             */
            @Override
            public void keyPressed(KeyEvent e) {
                paddle.keyPressed(e);

            }//end of the keyPressed Method

            /**
             * keyReleased Method - Responds to the releasing of the left or right arrow key
             * @param - the key event of releasing a key
             * @return void
             */
            @Override
            public void keyReleased(KeyEvent e) {
                paddle.keyReleased(e);

            }//end of the keyPressed Method

            /**
             * keyTyped Method
             * @param - the key event
             * @return Void
             */
            @Override
            public void keyTyped(KeyEvent e) {
            }
        });

    }//end of the GamePanel Constructor

    /**
     * move Method - Moves the ball and paddle by calling the move method of the ball
     * and the paddle.
     *
     * @return Void
     */
    public void move() {
        paddle.move();
        ball.move();

    }//end of the move Method

    /**
     * paint Method - Paints the ball, paddle, and draws the score string
     *
     * @param - the graphics context
     * @return Void
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        if (hasFocus()) {
            ball.paint(g2d);
            paddle.paint(g2d);
            g2d.setColor(new Color(0, 0, 100));
            g2d.setFont(new Font("Bookman Old Style", Font.BOLD, 40));
            g2d.drawString(String.valueOf(getScore()), 10, 30);

        } else if (ball.getBounds().y > paddle.getBounds().y) {
            ball.paint(g2d);
            paddle.paint(g2d);
            g2d.setColor(new Color(0, 0, 100));
            g2d.setFont(new Font("Bookman Old Style", Font.BOLD, 40));
            g2d.drawString(String.valueOf(getScore()), 10, 30);

        } else {
            ball.paint(g2d);
            paddle.paint(g2d);
            g2d.setColor(new Color(0, 0, 100));
            g2d.setFont(new Font("Bookman Old Style", Font.BOLD, 40));
            g2d.drawString(String.valueOf(getScore()), 10, 30);
            g2d.drawString("CLICK TO BEGIN", ((getWidth() / 2) - 140), getHeight() / 10);
        }

    }//end of the paint Method

    /**
     * getScore Method - Keeps count of the number of times the ball hits the
     * paddle.
     *
     * @return Int - Returns an int for the number of times the paddle hits the ball
     */
    private int getScore() {
        return score - 1;

    }//end of the getScore Method

    /**
     * doNewGame Method - Redraws the screen for a new game
     *
     * @return Void
     */
    public void doNewGame() {
        java.awt.EventQueue.invokeLater(() -> {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int screenWidth = (int) screenSize.getWidth();
            int screenHeight = (int) screenSize.getHeight();

            JFrame frame = new JFrame("MINI-PONG");
            frame.setBounds(0, 0, screenWidth, screenHeight);
            GamePanel gamePanel = new GamePanel();
            gamePanel.setBackground(color);
            frame.add(gamePanel);
            Image icon = Toolkit.getDefaultToolkit().getImage(GamePanel.class
                    .getResource("../res/img/ball-image-icon.png"));
            frame.setIconImage(icon);
            frame.setResizable(false);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        });

    }//end of the doNewGame Method

    /**
     * doGameOver Method - Stop the background music, plays the end of game music, and
     * displays the game over frame.  Also, it gives the player the option of playing the
     * game again.
     *
     * @return Void
     */
    public void doGameOver() {
        net.mabdurrahman.gui.Sound.BACKGROUND.stop();
        net.mabdurrahman.gui.Sound.GAME_OVER.play();

        JDialog.setDefaultLookAndFeelDecorated(false);
        JOptionPane.showMessageDialog(this, "                        Your score is " + getScore(),
                "                         G A M E  O V E R", JOptionPane.PLAIN_MESSAGE);

        String message = "Do you want to play again?";
        int response = JOptionPane.showConfirmDialog(null, message, null,
                JOptionPane.YES_NO_OPTION);

        switch (response) {
            case JOptionPane.YES_OPTION:
                doNewGame();
                break;
            case JOptionPane.NO_OPTION:
                System.exit(0);
                break;
            default:
                System.exit(0);
        }

    }//end of the gameOver Method

    /**
     * main Method - Creates the frame for the game; sets the background color; adds the game;
     * sets the location of the frame; sets the preferred size of the game; and performs the
     * game loop.
     *
     * @param - the String[] argument
     * @return Void
     */
    public static void main(String[] args) throws InterruptedException {
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
        } catch (ClassNotFoundException |
                InstantiationException |
                IllegalAccessException |
                javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GamePanel.class.getName()).
                    log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /** Create and display the Game */
        java.awt.EventQueue.invokeLater(() -> {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int screenWidth = (int) screenSize.getWidth();
            int screenHeight = (int) screenSize.getHeight();

            JFrame frame = new JFrame("MINI-PONG");
            frame.setBounds(0, 0, screenWidth, screenHeight);
            GamePanel gamePanel = new GamePanel();
            gamePanel.setBackground(color);
            frame.add(gamePanel);
            Image icon = Toolkit.getDefaultToolkit().getImage(GamePanel.class
                    .getResource("../res/img/ball-image-icon.png"));
            frame.setIconImage(icon);
            frame.setResizable(false);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        });
    }//end of the main Method

}//end of the GamePanel Class

