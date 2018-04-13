package com.bruus.bigproject.gameobjects;

import com.bruus.bigproject.TheBigProject;

public 	class CityCornerTopLeft extends GameObject {
	  public CityCornerTopLeft(TheBigProject mainApplet,int x, int y, int w, int h) {
		    super( mainApplet,'G', x, y, w, h);
		    img = mainApplet.resourceManager.cityRoadLeftTopCorner;
		  }
		  public String toString()
		  { 
		    return "CityCornerTopLeft"+coords();
		  }
		}
