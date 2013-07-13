
package snakefinal;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author Cypher
 */
public class Body
{
    private final int LEFT_WALL = 19;
    private final int RIGHT_WALL = 515;
    private final int TOP_WALL = 34;
    private final int BOTTOM_WALL = 530;
    private Rectangle segment;
    private int xPos, yPos, xVel, yVel, oldX, oldY;

    public Body()
    {
        segment = new Rectangle(5, 5);
        xVel = 0;
        yVel = 0;
    }
    
    public Body(int xPos, int yPos)
    {
        segment = new Rectangle(5, 5);
        this.xPos = xPos;
        this.yPos = yPos;
    }
    
    public void drawThis(Graphics2D g2d)
    {
        segment.setLocation(xPos, yPos);
        g2d.setColor(Color.white);
        g2d.fill(segment);
    }
    
    
    
    public boolean isIntercept(Body otherBody)
    {
        if(this.segment.intersects(otherBody.segment))
        {
            return true;
        }
        return false;
    }
    
    public void changeDirection(int direction)
    {
        //up = 38. Down = 40. left = 37. right = 39.
        switch(direction)
        {
            case 38:
                //up
                if(yVel == 0)
                {
                    xVel = 0;
                    yVel -= 5;
                }
                break;
            case 40:
                //down
                if(yVel == 0)
                {
                    xVel = 0;
                    yVel += 5;
                }
                break;
            case 37:
                //left
                if(xVel == 0)
                {
                    yVel = 0;
                    xVel -= 5;
                }
                break;
            case 39:
                //right
                if(xVel == 0)
                {
                    yVel = 0;
                    xVel += 5;
                }
                break;
            default:
                //seems this isnt needed anymore....
                break;
        }
    }
    
    public void update()
    {
        oldX = xPos;
        oldY = yPos;
        xPos += (xVel);
        yPos += (yVel);
    }
    
    public boolean checkBounds()
    {
        if(xPos < LEFT_WALL || xPos > RIGHT_WALL || yPos < TOP_WALL || yPos > BOTTOM_WALL)
        {
            return true;
        }
        return false;
    }
    
    
    //<editor-fold defaultstate="collapsed" desc="Get and set crap">
    
    public int getOldX()
    {
        return oldX;
    }

    public int getOldY()
    {
        return oldY;
    }
    
    public Rectangle getSegment()
    {
        return segment;
    }

    public void setSegment(Rectangle segment)
    {
        this.segment = segment;
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
    
    @Override
    public String toString()
    {
        return "Body{" + "xPos=" + xPos + ", yPos=" + yPos + '}';
    }
    
    public void setXY(int x, int y)
    {
        this.oldX = this.xPos;
        this.oldY = this.yPos;
        this.xPos = x;
        this.yPos = y;
    }
    //</editor-fold>
}
