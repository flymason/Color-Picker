import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class ColorArea extends JPanel implements MouseMotionListener, MouseListener
{
	private int size;
	private int pixelSize;
	private float h;
	int selectorX = 0;
	int selectorY = 0;
	boolean initialPainting = true;
	
	public ColorArea(int s)
	{
		size = (s / 100) * 100;
		pixelSize = size/100;
		h = 1;
		
		setPreferredSize(new Dimension(size, size));
		setSize(new Dimension(size, size));
		addMouseMotionListener(this);
		addMouseListener(this);
		
	}
	
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		for (int y = 0; y < 100; y++)
		{
			for(int x = 0; x < 100; x++)
			{
				g.setColor(Color.getHSBColor(h, ((float)x)/100, ((float)100-y)/100));
				g.fillRect(x * pixelSize, y * pixelSize, pixelSize, pixelSize);
			}
		}
			g.setXORMode(Color.WHITE);
			g.drawOval(selectorX-5, selectorY-5, 10, 10);
			initialPainting = false;
	}
	
	public void setH(float h)
	{
		this.h = h / 360;
		repaint();
	}
	
	public Color getColor(int x, int y)
	{
		return Color.getHSBColor(h, ((float)x)/100, ((float)100-y)/100);
	}
	
	public void setSelector(int x, int y)
	{
		selectorX = x;
		selectorY = y;
		repaint();
	}

	public void mouseDragged(MouseEvent e)
	{
		//if(e.getX() > 0 && e.getX() < size && e.getY() > 0 && e.getY() < size)
		{
			Graphics g = getGraphics();
			g.setXORMode(Color.WHITE);
			g.drawOval(selectorX-5, selectorY-5, 10, 10);	//erase
			
			selectorX = e.getX();
			selectorY = e.getY();
			
			if(selectorX < 0)
			{
				selectorX = 0;
			}
			if(selectorX > size)
			{
				selectorX = size;
			}
			if(selectorY < 0)
			{
				selectorY = 0;
			}
			if(selectorY > size)
			{
				selectorY = size;
			}
			
			
			g.drawOval(selectorX-5, selectorY-5, 10, 10);	//new circle
		}
	}

	public void mouseMoved(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mousePressed(MouseEvent e)
	{
		Graphics g = getGraphics();
		g.setXORMode(Color.WHITE);
		g.drawOval(selectorX-5, selectorY-5, 10, 10);	//erase
		selectorX = e.getX();
		selectorY = e.getY();
		g.drawOval(selectorX-5, selectorY-5, 10, 10);	//draw
		
	}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}























