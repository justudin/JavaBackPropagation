package com.github.justudin.mlp;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;

public class Graph extends JFrame{
	String acc;
	String inp;
	public Graph (String accuracy, String input){
		 setTitle("Tutorial Rectangle");
         setSize(800, 800);
         setVisible(true);
         setDefaultCloseOperation(EXIT_ON_CLOSE);
         this.acc = accuracy;
         this.inp = input;
	}
	
	 public void paint(Graphics g)
     {
		 	int h=100;
		 	
		 for (int i=0;i<18;i++){
		 	g.setColor(Color.BLUE);
            g.fillOval(100, h, 20, 20);
            int k=100;
            for (int i1=0;i1<18;i1++){
            g.drawLine(100, h, 300, k);
            k=k+30;
            }
            h=h+30;
           
		 }
           
		 	int h2=100;
		 for (int i=0;i<18;i++){
		 	g.setColor(Color.GREEN);
		 	g.fillOval(300, h2, 20, 20);
		 	 g.drawLine(300, h2, 500, 300);
		 	h2=h2+30;
         
		 }
            
		 g.setColor(Color.RED);
         g.fillOval(500, 300, 20, 20);
         
         g.setColor(Color.BLUE);
         Font font = new Font("Serif", Font.PLAIN, 20);
         g.setFont(font);
	      	g.drawString("[1,0] = accepted, not accepted", 500, 350);
         
         
           
  	      	g.drawString(acc, 100, 700);
            
            
     }
     public static void main(String[] args)
     {
          Graph t = new Graph("Accuracy is","");
         // t.paint(null); // Not a proper way, but it still works.
     }

}
