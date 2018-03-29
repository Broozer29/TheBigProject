package com.bruus.bigproject;

public 	class DesertStoneThree extends GameObject {
	  public DesertStoneThree(TheBigProject mainApplet,int x, int y, int w, int h) {
		    super( mainApplet,'c', x, y, w, h);
		    img = mainApplet.resourceManager.DesertStoneThree;
		  }
		  public String toString() {
		    return "DesertStoneThree"+coords();
		  }
		}
