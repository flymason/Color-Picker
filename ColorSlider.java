import javax.swing.*;
import java.awt.*;

public class ColorSlider extends JPanel{
	
	private int height;
	private int width = 20;
	private int hPosition = 0;
	
	public ColorSlider(int height)
	{
		this.height = height;
		setPreferredSize(new Dimension(width + 20, height+10));
		setSize(new Dimension(width + 20, height+10));
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		for (int y = 0; y < height; y++)
		{
			for(int x = 0; x < width; x++)
			{
				g.setColor(Color.getHSBColor(((float)height-y)/height, 1, 1));
				g.fillRect(x+10, y+5, 1, 1);
			}
		}

		g.setColor(Color.BLACK);
		g.drawRect(10,  5, width, height);
		
		int[] x1 = {1, 1, 10};
		int[] y1 = {hPosition, hPosition + 10, hPosition + 5};
		Polygon p = new Polygon(x1,	y1, 3);
		g.fillPolygon(p);
		
		int[] x2 = {width + 20, width + 20, width + 10,};
		int[] y2 = {hPosition, hPosition + 10, hPosition + 5};
		p = new Polygon(x2,	y2, 3);
		g.fillPolygon(p);
		
	}
	
	public void setH(int h)
	{
		if( h <= height && h >= 0)
		{
			hPosition = h;
			repaint();
		}
	}
	public int getH()
	{
		return (height - hPosition) * 360 / height;
	}
	
	
}















