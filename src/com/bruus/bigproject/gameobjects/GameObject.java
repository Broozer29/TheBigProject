package com.bruus.bigproject.gameobjects;

import com.bruus.bigproject.TheBigProject;

import processing.core.PImage;

public class GameObject {
	TheBigProject mainApplet;
	  PImage img;
	  int x1, y1, x2, y2;
	  int w; 
	  int h;
	  char type;

	  public GameObject(TheBigProject mainApplet,char type, int x, int y, int w, int h) {
	    x1 = x;
	    y1 = y;
	    x2 = x + w;
	    y2 = y + h;
	    this.w = w;
	    this.h = h;
	    this.type = type;
	    this.mainApplet = mainApplet;
	  }

	  public char getType() {
	    return type;
	  }  

	  //
	  //  xOverlap1:      x1..a1...x2  a2
	  //  xOverlap2:  a1  x1....a2.x2
	  //
	  //


	  public int overlap(int a1, int b1, int a2, int b2) {
	    int xOverlap1 = a1>=x1 && a1<=x2 ?x2 - a1:0;
	    int xOverlap2 = a2>=x1 && a2<=x2 ?a2 - x1:0;

	    int yOverlap1 = b1>=y1 && b1<=y2 ?y2 - b1:0;
	    int yOverlap2 = b2>=y1 && b2<=y2 ?b2 - y1:0;

	    int xOverLap = Math.max(xOverlap1, xOverlap2);
	    int yOverLap = Math.max(yOverlap1, yOverlap2);

	    int surface = xOverLap * yOverLap;
	    return surface;
	  }

	  public boolean contains(int a1, int b1, int a2, int b2) {
	    boolean topLeftHit = a1 >= x1 && a1 <= x2 && b1 >= y1 && b1 <= y2;
	    boolean topRightHit = a2 >= x1 && a2 <= x2 && b1 >= y1 && b1 <= y2;

	    boolean bottomLeftHit = a1 >= x1 && a1 <= x2 && b2 >= y1 && b2 <= y2;
	    boolean bottomRightHit = a2 >= x1 && a2 <= x2 && b2 >= y1 && b2 <= y2;
	    return  topLeftHit || topRightHit || bottomRightHit || bottomLeftHit;
	  }

	  public void drawTerrain() {
	    mainApplet.image(img, x1, y1, w, h);
	  }
	  public String coords() {
	    return x1+","+y1+","+x2+","+y2;
	  }
	}



	


	




