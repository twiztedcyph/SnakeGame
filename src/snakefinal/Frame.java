
package snakefinal;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Cypher
 */
public class Frame extends Canvas
{
    private BufferStrategy strat;
    private int fps, I = 0, lives = 3;
    private long lastFpsTime;
    private WholeSnake wholeSnake;
    private boolean gameOn = true, pause = false;
        
    public Frame()
    {
        JFrame frame = new JFrame("\"Snake\" by Ian");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(800, 600));
        
        this.setIgnoreRepaint(true);
        this.setBounds(0, 0 , 800, 600);
        
        JPanel panel = (JPanel) frame.getContentPane();
        panel.setBounds(0,0,800,600);
        
        panel.add(Frame.this);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        
        this.createBufferStrategy(2);
        strat = this.getBufferStrategy();
        
        wholeSnake = new WholeSnake();
        
        this.addKeyListener(new KeyListener()
        {

            @Override
            public void keyTyped(KeyEvent e)
            {
                
            }

            @Override
            public void keyPressed(KeyEvent e)
            {
                if(e.getKeyCode() == 80)
                {
                    if(pause)
                    {
                        pause = false;
                    }else
                    {
                        pause = true;
                    }
                }
                if(!pause)
                {
                    wholeSnake.changeDir(e.getKeyCode());
                }
            }

            @Override
            public void keyReleased(KeyEvent e)
            {
                
            }
        });
        this.requestFocus();
    }
    
    public void loop()
    {
        long lastLoopTime = System.nanoTime();
        int targetFps = 2;
        final long OPT_TIME = 1000000000 / targetFps;
        while(gameOn)
        {
            long now = System.nanoTime();
            long updateLength = now - lastLoopTime;
            lastLoopTime = now;
            
            Graphics2D g2d = (Graphics2D) strat.getDrawGraphics();
            g2d.setColor(Color.BLACK);
            g2d.fillRect(0, 0, 794, 572);
            
            g2d.setColor(Color.LIGHT_GRAY);
            g2d.drawRect(19, 34, 501, 501);
            
            if(!pause)
            {
                wholeSnake.updateHead();
            }
            
            if(wholeSnake.inZone() || wholeSnake.selfEat())
            {
                lives--;
                wholeSnake = new WholeSnake();
                wholeSnake.flashSnake(g2d);
                if(lives == 0)
                {
                    gameOn = false;
                }
            }
            
            wholeSnake.drawAll(g2d);

            lastFpsTime += updateLength;
            fps++;

            if(lastFpsTime > 1000000000)
            {
                lastFpsTime = 0;
                I = fps;
                fps = 0;
            }
            g2d.drawString("FPS: " + I, 579, 55);
            g2d.drawString("Lives: " + lives, 573, 70);
            
            
            double delta = updateLength / ((double) OPT_TIME);
            
            
            
            g2d.dispose();
            strat.show();
            
            try
            {
                Thread.sleep( (lastLoopTime - System.nanoTime() + OPT_TIME) / 10000000);
            }catch(Exception e)
            {
                
            }
        }
    }
}
