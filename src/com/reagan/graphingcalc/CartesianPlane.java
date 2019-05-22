package com.reagan.graphingcalc;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class CartesianPlane extends JPanel {
	
	//private Function f;
	private final double domainRadius;
	
	private Color graphColor;
	
	private int thickness;
	
	private ArrayList<Function> functions = new ArrayList<Function>();
	
	public CartesianPlane(/*Function f,*/ double domainRadius, Color bkgrnd, Color graphColor, int thickness) {
		
		this.thickness = thickness;
		this.graphColor = graphColor;
		//this.f = f;
		this.domainRadius = domainRadius;
		
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.setOpaque(true);
		this.setBackground(bkgrnd);
		
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(500,500);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		drawAxis(g);
		for (int i = 0; i < functions.size(); i++) graphMethod2(g, functions.get(i));

	}

	public void addFunction(Function f) {
		
		functions.add(f);
	}
	
	private void drawAxis(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		
		g.fillRect(getWidth()/2 - thickness/2, 0, thickness, getHeight());
		g.fillRect(0, getHeight()/2 - thickness/2, getWidth(), thickness);
	}
	
	private void graphMethod2(Graphics g, Function f) {
		
		int m = 100;
		
		int totalPoints = m*getWidth();
		
		CartesianPoint rawPoint = new CartesianPoint(0,0);
		CartesianPoint centered = new CartesianPoint(0,0);;
		CartesianPoint accurateToScale = new CartesianPoint(0,0);
		
		CartesianPoint subPixelPoint = new CartesianPoint(0,0);
		
		for(int x = 0; x <= totalPoints; x++) {
			
			subPixelPoint.x = x;
			rawPoint.x = subPixelPoint.x/m;
			centered = rawToCentered(rawPoint);
			accurateToScale = scaleToDomainRadius(centered, domainRadius);
			accurateToScale.y = f.f(accurateToScale.x);
			
						
			double xp = accurateToScale.x;
			
			g.setColor(new Color((float)(0.49*Math.sin(xp)+0.5),(float)(0.49*Math.sin(xp + 2)+0.5),(float)(0.49*Math.sin(xp + 4)+0.5)));
			
			g.fillRect(x/m-thickness/2, reverse(accurateToScale.y)-thickness/2, thickness, thickness);
			
			
		}
		
		
	}
	
	private void graph(Graphics g, Function f) {
		
		g.setColor(graphColor);
		
		CartesianPoint rawPoint = new CartesianPoint(0,0);
		CartesianPoint centered = new CartesianPoint(0,0);;
		CartesianPoint accurateToScale = new CartesianPoint(0,0);
		
		for(int y = 0; y < getHeight(); y++) {
			for(int x = 0; x < getWidth(); x++) {
				rawPoint.x = x;
				rawPoint.y = y;
				centered = rawToCentered(rawPoint);
				accurateToScale = scaleToDomainRadius(centered, domainRadius);
				
				double xp = accurateToScale.x;
				
				if (reverse(f.f(accurateToScale.x)) == y) {
					g.setColor(new Color((float)(0.49*Math.sin(xp)+0.5),(float)(0.49*Math.sin(xp + 2)+0.5),(float)(0.49*Math.sin(xp + 4)+0.5)));
					g.fillRect(x-thickness/2, y-thickness/2, thickness, thickness);
				}
			}
		}
		
		
		
	}
	
	private int reverse(double y) {
		
		return (int)((getHeight()/2) - (getWidth()*y)/(2*domainRadius));
	}

	private CartesianPoint scaleToDomainRadius(CartesianPoint p, double domainRadius) {
	
		return new CartesianPoint((2*p.x*domainRadius/getWidth()),(2*p.y*domainRadius/getWidth()));
	}
	
	private CartesianPoint rawToCentered(CartesianPoint raw) {
		
		return new CartesianPoint((-getWidth()/2) + raw.x, (getHeight()/2) - raw.y);
    }
	
}
