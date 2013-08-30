import java.awt.*;

import javax.swing.*;


public class ColorBox extends JPanel
{
	Color color;
	int size;
	
	public ColorBox(Color color, int size)
	{
		this.color = color;
		this.size = size;
		setPreferredSize(new Dimension(size, size));
		setSize(new Dimension(size, size));
	}
	
	public void paintComponent(Graphics g)
	{
		g.setColor(color);
		g.fillRect(0, 0, size, size);
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, size, size);
	}
	
	public void setColor(Color c)
	{
		color = c;
		repaint();
	}
	
}
