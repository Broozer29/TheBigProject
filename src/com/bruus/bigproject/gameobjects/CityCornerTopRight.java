package com.bruus.bigproject.gameobjects;

import com.bruus.bigproject.TheBigProject;

public 	class CityCornerTopRight extends GameObject {
	  public CityCornerTopRight(TheBigProject mainApplet,int x, int y, int w, int h) {
		    super( mainApplet,'H', x, y, w, h);
		    img = mainApplet.resourceManager.cityRoadRightTopCorner;
		  }
		  public String toString()
		  { 
		    return "CityCornerTopRight"+coords();
		  }
		}
