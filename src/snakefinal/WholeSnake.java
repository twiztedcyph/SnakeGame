
package snakefinal;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Cypher
 */
public class WholeSnake
{
    private List<Body> wholeSnake;
    private Food food;
    private int lives, score = 0;
    
    public WholeSnake()
    {
        Body b = new Body();
        b.setxPos(200);
        b.setyPos(200);
        wholeSnake = Collections.synchronizedList(new ArrayList<Body>());
        wholeSnake.add(b);
        food = new Food();
    }
    
    public void updateHead()
    {
        Body head = wholeSnake.get(0);
        head.update();
        
        for (int i = 1; i < wholeSnake.size(); i++)
        {
            Body seg = wholeSnake.get(i);
            seg.setXY(wholeSnake.get(i - 1).getOldX(), wholeSnake.get(i - 1).getOldY());
        }
        
        if(head.getSegment().intersects(food.getFood()))
        {
            food = new Food();
            score++;
            System.out.println(head.toString() + " " + food.toString());
            addSegment();
            addSegment();
        }
        
    }
    
    public boolean selfEat()
    {
        Boolean result = false;
        for (int i = 2; i < wholeSnake.size(); i++)
        {
            if(wholeSnake.get(0).isIntercept(wholeSnake.get(i)))
            {
                result = true;
                System.out.println(wholeSnake.get(0).toString() + " " + i + " " + wholeSnake.get(i).toString());
            }
        }
        return result;
    }
    
    public void addSegment()
    {
        wholeSnake.add(new Body(wholeSnake.get(wholeSnake.size() - 1).getOldX(), wholeSnake.get(wholeSnake.size() - 1).getOldY()));
    }
    
    public void drawAll(Graphics2D g2d)
    {
        Iterator<Body> it = wholeSnake.iterator();
        while(it.hasNext())
        {
            it.next().drawThis(g2d);
        }
        food.drawThis(g2d);
        g2d.drawString("Score: " + String.valueOf(score), 570, 40);
    }
    
    public void changeDir(int dir)
    {
        wholeSnake.get(0).changeDirection(dir);
    }
    
    public boolean inZone()
    {
        return wholeSnake.get(0).checkBounds();
    }
    
    public void flashSnake(Graphics2D g2d)
    {
        
    }
    
    public int getScore()
    {
        return score;
    }
    
    public void setScore(int score)
    {
        this.score = score;
    }
}
