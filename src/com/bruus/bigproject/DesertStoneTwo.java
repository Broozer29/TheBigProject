package com.bruus.bigproject;

public 
class DesertStoneTwo extends GameObject {
	  public DesertStoneTwo(TheBigProject mainApplet,int x, int y, int w, int h) {
	    super( mainApplet, 'b', x, y, w, h);
	    img = mainApplet.resourceManager.DesertStoneTwo;
	  }
	  public String toString() {
	    return "DesertStoneTwo"+coords();
	  }
	}
