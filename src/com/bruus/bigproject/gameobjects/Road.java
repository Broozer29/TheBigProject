package com.bruus.bigproject.gameobjects;

import com.bruus.bigproject.TheBigProject;

public 	class Road extends GameObject {
	  public Road(TheBigProject mainApplet,int x, int y, int w, int h) {
		    super( mainApplet,'R', x, y, w, h);
		    img = mainApplet.resourceManager.roadImage;
		  }
		  public String toString()
		  { 
		    return "Road"+coords();
		  }
		}
