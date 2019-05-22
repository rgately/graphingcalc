package com.reagan.graphingcalc;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				Function f = new Function() { //-> TODO: Re-do as lambda

					@Override
					public double f(double x) {
						//return Math.exp(x);
						//return Math.sin(x);
						//return Math.sqrt(4*4-x*x);
						//return Math.sin(4*x)*Math.cos(5*x)* 4;
						//return Math.sin(x)*Math.cos(10*x);
						//return (Math.pow(x*x, x*x)*Math.sin(x))*Math.cos(201*x);
						//return x*x*x;
						//return x*x;
						//return Math.sin(x*x);
						//return Math.pow(x, x);
						//return Math.sin(x)*Math.cos(201*x);
						return Math.sin(x)*Math.cos(51*x);
						
						//return -2*Math.cos(2*Math.PI/5*x) + 3;
					}

				};
				
				Function f2 = new Function() { //-> TODO: Re-do as lambda
					@Override
					public double f(double x) {
						return Math.sin(x)*Math.cos(201*x);
					}
				};
				
				Function sinx = new Function() { //-> TODO: Re-do as lambda
					@Override
					public double f(double x) {
						return Math.sin(x);
					}
				};

				JFrame window = new JFrame("Graph");
				//										  Domain-rad background	  not used	   thickness
				CartesianPlane plane = new CartesianPlane(Math.PI/2, Color.BLACK, Color.WHITE, 2);
				plane.addFunction(f);
				//plane.addFunction(f2);
				//plane.addFunction(sinx);
				window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				window.add(plane);
				window.pack();
				window.setVisible(true);
			}

		});
	}
}
