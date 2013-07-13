/*
 * Shitty version of snake I was attempting to make... No comments in the code :p
 */

 
package snakefinal;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

/**
 *
 * @author Cypher
 */
public class Food
{
    private int xPos, yPos;
    private Rectangle food;
    private Random rand = new Random();

    public Food()
    {
        this.xPos = (rand.nextInt(100) * 5) + 20;
        this.yPos = (rand.nextInt(100) * 5) + 35;
        this.food = new Rectangle(xPos, yPos, 5, 5);
    }
    
    public void drawThis(Graphics2D g2d)
    {
        food.setLocation(xPos, yPos);
        g2d.setColor(Color.red);
        g2d.fill(food);
    }

    public int getxPos()
    {
        return xPos;
    }

    public void setxPos(int xPos)
    {
        this.xPos = xPos;
    }

    public int getyPos()
    {
        return yPos;
    }

    public void setyPos(int yPos)
    {
        this.yPos = yPos;
    }

    public Rectangle getFood()
    {
        return food;
    }

    public void setFood(Rectangle food)
    {
        this.food = food;
    }

    @Override
    public String toString()
    {
        return "Food{" + "xPos=" + xPos + ", yPos=" + yPos + ", food=" + food + ", rand=" + rand + '}';
    }
    
    
}
