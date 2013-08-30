import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import java.text.NumberFormat;


public class ColorPicker extends JPanel implements MouseMotionListener, MouseListener, ActionListener
{
	private ColorArea colorArea;
	private ColorSlider slider;
	private ColorBox colorBox;
	
	private int size = 300;
	private int selectorX;
	private int selectorY;
	
	private JLabel redLabel 	= new JLabel("R: ");
	private JLabel greenLabel 	= new JLabel("G: ");
	private JLabel blueLabel 	= new JLabel("B: ");
	private JLabel hLabel 		= new JLabel("H: ");
	private JLabel sLabel 		= new JLabel("S: ");
	private JLabel bLabel		= new JLabel("B: ");
	
	private JTextField redField 	= new JTextField();
	private JTextField blueField 	= new JTextField();
	private JTextField greenField	= new JTextField();
	private JTextField hField 		= new JTextField();
	private JTextField sField 		= new JTextField();
	private JTextField bField 		= new JTextField();
	private JTextField hexField 	= new JTextField();
	
	public ColorPicker()
	{
		setLayout(null);
		setPreferredSize(new Dimension(440, 350));
		colorArea = new ColorArea(size);
		slider = new ColorSlider(size);
		colorBox =  new ColorBox(colorArea.getColor(0, 0), 60);
		
		JPanel valuesPanel = makeValuesPanel();
		
		hexField.addActionListener(this);
		hexField.setSize(60,  20);
		
		colorArea.addMouseMotionListener(this);
		colorArea.addMouseListener(this);
		slider.addMouseMotionListener(this);
		slider.addMouseListener(this);
		
		colorArea.setLocation(10,  40);
		slider.setLocation(315,  35);
		colorBox.setLocation(370,  50);
		hexField.setLocation(130,  10);
		valuesPanel.setLocation(365, 130);
		
		add(colorArea);
		add(slider);
		add(colorBox);
		add(hexField);
		add(valuesPanel);
		
		selectorX = colorArea.getX();
		selectorY = colorArea.getY();
		
	}
	


	private JPanel makeValuesPanel()
	{
		JPanel panel = new JPanel();
		panel.setSize(60, 250);
		
		Dimension d = new Dimension(30, 20);
		
		redField.setPreferredSize(d);
		blueField.setPreferredSize(d);
		greenField.setPreferredSize(d);
		hField.setPreferredSize(d);
		sField.setPreferredSize(d);
		bField.setPreferredSize(d);
		
		redField.addActionListener(this);
		blueField.addActionListener(this);
		greenField.addActionListener(this);
		hField.addActionListener(this);
		sField.addActionListener(this);
		bField.addActionListener(this);
		
		panel.add(redLabel);
		panel.add(redField);
		panel.add(greenLabel);
		panel.add(greenField);
		panel.add(blueLabel);
		panel.add(blueField);
		panel.add(hLabel);
		panel.add(hField);
		panel.add(sLabel);
		panel.add(sField);
		panel.add(bLabel);
		panel.add(bField);
		
		updateValues();

		return panel;
	}
	
	public void updateValues()	//updates values in color fields
	{
		Color c = colorArea.getColor((selectorX)/3, (selectorY)/3);
		
		redField.setText(c.getRed() + "");
		blueField.setText(c.getBlue() + "");
		greenField.setText(c.getGreen() + "");
		
		float[] hsbVals = new float[3];
		Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), hsbVals);
		
		hField.setText((int)(hsbVals[0]*360) + "");
		sField.setText((int)(hsbVals[1]*100) + "");
		bField.setText((int)(hsbVals[2]*100) + "");
		
		hexField.setText(getHex());
	}
	
	public void updateColor(Color newColor)
	{
		colorBox.setColor(newColor);
		
		float[] hsbVals = new float[3];
		Color.RGBtoHSB(newColor.getRed(), newColor.getGreen(), newColor.getBlue(), hsbVals);
		
		selectorX = (int)(hsbVals[1]*300);
		selectorY = (int)(300 - hsbVals[2]*300);
		
		slider.setH((int)(300 - hsbVals[0]*300));
		colorArea.setH(hsbVals[0]*360);
		colorArea.setSelector((int)(hsbVals[1]*300),(int)(300 - hsbVals[2]*300));
		
		updateValues();	
	}

	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		Color newColor = null;
		
		if(source == redField || source == greenField || source == blueField)
		{
			int r = Integer.parseInt(redField.getText());
			int g = Integer.parseInt(greenField.getText());
			int bl = Integer.parseInt(blueField.getText());
		
			newColor = new Color(r, g, bl);
		}
		else if(source == hField || source == sField || source == bField)
		{
			float h = Float.parseFloat(hField.getText())/360;
			float s = Float.parseFloat(sField.getText())/100;
			float br = Float.parseFloat(bField.getText())/100;
			newColor = Color.getHSBColor(h, s, br);
		}
		else if(source == hexField)
		{
			newColor = Color.decode(hexField.getText());
		}
		
		updateColor(newColor);	
	}

	public void mouseDragged(MouseEvent e)
	{
		Object source = e.getSource();
		
		if(source == slider)
		{
			slider.setH(e.getY());
			colorArea.setH(slider.getH());
		}
		else if(source == colorArea)
		{	
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
		}
		colorBox.setColor(colorArea.getColor((selectorX)/3, (selectorY)/3));
		updateValues();
		
	}

	public void mousePressed(MouseEvent e)
	{
		Object source = e.getSource();
		
		if(source == colorArea)
		{
			if(e.getX() > 0 && e.getX() < 300 && e.getY() > 0 && e.getY() < 300)
			{
				selectorX = e.getX();
				selectorY = e.getY();
			}
		}
		else if(source == slider)
		{
			slider.setH(e.getY());
			colorArea.setH(slider.getH());
		}
		
		colorBox.setColor(colorArea.getColor((selectorX)/3, (selectorY)/3));
		updateValues();
		
	}
	public void mouseMoved(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
	public String getHex()
	{
		Color c = colorArea.getColor((selectorX)/3, (selectorY)/3);
		int red = c.getRed();
		int green = c.getGreen();
		int blue = c.getBlue();
		
		String hex = "#";
		hex += Integer.toHexString(red/16);
		hex+= Integer.toHexString(red%16);
		hex += Integer.toHexString(green/16);
		hex+= Integer.toHexString(green%16);
		hex += Integer.toHexString(blue/16);
		hex+= Integer.toHexString(blue%16);
		
		return hex.toUpperCase();
	}
	
	public static void main(String[] args)
	{
		
		JFrame f = new JFrame();
		f.add(new ColorPicker());

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setTitle("Color Picker");
		
		f.pack();
		f.setVisible(true);
	}
}























