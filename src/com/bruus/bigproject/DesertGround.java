package com.bruus.bigproject;

public class DesertGround extends GameObject {
	  public DesertGround(TheBigProject mainApplet, int x, int y, int w, int h) {
	    super(mainApplet,'A', x, y, w, h);
	    img = mainApplet.resourceManager.DesertGround;
	  }
	  public String toString() {
	    return "DesertGround"+coords();
	  }
	}
