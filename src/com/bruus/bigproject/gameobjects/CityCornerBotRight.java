package com.bruus.bigproject.gameobjects;

import com.bruus.bigproject.TheBigProject;

public 	class CityCornerBotRight extends GameObject {
	  public CityCornerBotRight(TheBigProject mainApplet,int x, int y, int w, int h) {
		    super( mainApplet,'J', x, y, w, h);
		    img = mainApplet.resourceManager.cityRoadRightBotCorner;
		  }
		  public String toString()
		  { 
		    return "CityCornerBotRight"+coords();
		  }
		}
