package com.bruus.bigproject.gameobjects;

import com.bruus.bigproject.TheBigProject;

public 	class CityCornerBotLeft extends GameObject {
	  public CityCornerBotLeft(TheBigProject mainApplet,int x, int y, int w, int h) {
		    super( mainApplet,'K', x, y, w, h);
		    img = mainApplet.resourceManager.cityRoadLeftBotCorner;
		  }
		  public String toString()
		  { 
		    return "CityCornerBotLeft"+coords();
		  }
		}
