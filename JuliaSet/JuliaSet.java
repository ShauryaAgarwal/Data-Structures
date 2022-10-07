import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class JuliaSet extends JPanel implements AdjustmentListener, MouseListener
{
	JFrame frame;
	double A = 0, B = 0;
	JScrollBar aBar, bBar, radiusBar, zoomBar, saturationBar, brightnessBar, iterationBar, xBar;
	JLabel aLabel, bLabel, radiusLabel, zoomLabel, saturationLabel, brightnessLabel, iterationLabel, xLabel;
	JPanel scrollPanel, labelPanel, bigPanel;
	DecimalFormat decForm = new DecimalFormat("0.000");
	BufferedImage image;
	final int maxIter = 300;
	int pixelSize = 1;
	double radius = 1;
	private double zoom = 1;
	float saturation = 1;
	float brightness = 1;
	int iterations = 300;
	double xPos = 0;

	public JuliaSet()
	{
		frame  = new JFrame("Julia Set!");
		frame.add(this);
		frame.setSize(1000, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		aBar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 0, -2000, 2000);
		A = aBar.getValue()/1000;
		aBar.addAdjustmentListener(this);
		aBar.addMouseListener(this);

		bBar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 0, -2000, 2000);
		B = bBar.getValue()/1000;
		bBar.addAdjustmentListener(this);
		bBar.addMouseListener(this);

		radiusBar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 0, 0, 6000);
		radius = radiusBar.getValue()/1000;
		radiusBar.addAdjustmentListener(this);
		radiusBar.addMouseListener(this);

		zoomBar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 0, 0, 5000);
		zoom = zoomBar.getValue()/1000;
		zoomBar.addAdjustmentListener(this);
		zoomBar.addMouseListener(this);

		saturationBar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 0, 0, 1000);
		saturation = saturationBar.getValue()/1000;
		saturationBar.addAdjustmentListener(this);
		saturationBar.addMouseListener(this);

		brightnessBar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 0, 0, 1000);
		brightness = brightnessBar.getValue()/1000;
		brightnessBar.addAdjustmentListener(this);
		brightnessBar.addMouseListener(this);

		iterationBar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 0, 0, 300);
		iterations = iterationBar.getValue();
		iterationBar.addAdjustmentListener(this);
		iterationBar.addMouseListener(this);

		xBar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 0, -10000, 10000);
		xPos = xBar.getValue()/1000;
		xBar.addAdjustmentListener(this);
		xBar.addMouseListener(this);

		GridLayout grid = new GridLayout(8, 1);
		aLabel = new JLabel("A");
		bLabel = new JLabel("B");
		radiusLabel = new JLabel("Radius");
		zoomLabel = new JLabel("Zoom");
		saturationLabel = new JLabel("Saturation: ");
		brightnessLabel = new JLabel("Brightness: ");
		iterationLabel = new JLabel("Iterations: ");
		xLabel = new JLabel("X: ");

		labelPanel = new JPanel();
		labelPanel.setLayout(grid);
		labelPanel.add(aLabel);
		labelPanel.add(bLabel);
		labelPanel.add(radiusLabel);
		labelPanel.add(zoomLabel);
		labelPanel.add(saturationLabel);
		labelPanel.add(brightnessLabel);
		labelPanel.add(iterationLabel);
		labelPanel.add(xLabel);

		scrollPanel = new JPanel();
		scrollPanel.setLayout(grid);
		scrollPanel.add(aBar);
		scrollPanel.add(bBar);
		scrollPanel.add(radiusBar);
		scrollPanel.add(zoomBar);
		scrollPanel.add(saturationBar);
		scrollPanel.add(brightnessBar);
		scrollPanel.add(iterationBar);
		scrollPanel.add(xBar);

		bigPanel = new JPanel();
		bigPanel.setLayout(new BorderLayout());
		bigPanel.add(labelPanel, BorderLayout.WEST);
		bigPanel.add(scrollPanel, BorderLayout.CENTER);

		frame.add(bigPanel, BorderLayout.SOUTH);
		frame.setVisible(true);
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(drawJulia(), 0, 0, null);
	}

	public void adjustmentValueChanged(AdjustmentEvent e)
	{
		if(e.getSource() == aBar)
		{
			A = aBar.getValue()/1000.0;
			aLabel.setText("A: " + decForm.format(A));
		}
		if(e.getSource() == bBar)
		{
			B = bBar.getValue()/1000.0;
			bLabel.setText("B: " + decForm.format(B));
		}
		if(e.getSource() == radiusBar)
		{
			radius = radiusBar.getValue()/1000.0;
			radiusLabel.setText("Radius: " + decForm.format(radius));
		}
		if(e.getSource() == zoomBar)
		{
			zoom = zoomBar.getValue()/1000.0;
			zoomLabel.setText("Zoom: " + decForm.format(zoom));
		}
		if(e.getSource() == saturationBar)
		{
			saturation = saturationBar.getValue()/1000.0f;
			saturationLabel.setText("Saturation: " + decForm.format(saturation));
		}
		if(e.getSource() == brightnessBar)
		{
			brightness = brightnessBar.getValue()/1000.0f;
			brightnessLabel.setText("Brightness: " + decForm.format(brightness));
		}
		if(e.getSource() == iterationBar)
		{
			iterations = iterationBar.getValue();
			iterationLabel.setText("Iterations: " + decForm.format(iterations));
		}
		if(e.getSource() == xBar)
		{
			xPos = xBar.getValue()/1000.0;
			xLabel.setText("X: " + decForm.format(xPos));
		}
		repaint();
	}

	public BufferedImage drawJulia()
	{
		int w = frame.getWidth() * 2;
		int h = frame.getHeight() * 2;
		image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

		for(int x = 0; x < w; x += pixelSize)
		{
			for(int y = 0; y < h; y += pixelSize)
			{
				float i = iterations;
				double zx = 1.5 * (x - w * 0.5)/(0.5 * zoom * w) + xPos;
				double zy = (y - h * 0.5)/(0.5 * zoom * h);

				while(zx * zx + zy * zy < radius && i > 0)
				{
					double temp = zx * zx - zy * zy + A;
					zy = zx * zy + B;
					zx = temp;
					i--;
				}

				int c;

				if(i > 0)
				{
					c = Color.HSBtoRGB((i / maxIter) % 1, saturation, brightness);
				}
				else
				{
					c = Color.HSBtoRGB(i / maxIter, saturation, 0);
				}
				image.setRGB(x,y,c);
			}
		}
		return image;
	}

	public static void main (String[]args)
	{
		JuliaSet app = new JuliaSet();
	}

	public void mousePressed(MouseEvent e)
	{
		pixelSize = 5;
		repaint();
	}

	public void mouseReleased(MouseEvent e)
	{
		pixelSize = 1;
		repaint();
	}
	public void mouseClicked(MouseEvent e)
	{

	}

	public void mouseEntered(MouseEvent e)
	{

	}

	public void mouseExited(MouseEvent e)
	{

	}
}