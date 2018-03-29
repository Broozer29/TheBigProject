package com.bruus.bigproject;

public class Water extends GameObject {
	  public Water(TheBigProject mainApplet,int x, int y, int w, int h) {
		    super( mainApplet,'W', x, y, w, h);
		    img = mainApplet.resourceManager.waterImage;
		  }
		  public String toString()
		  { 
		    return "Water"+coords();
		  }
		}
