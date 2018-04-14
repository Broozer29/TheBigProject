package com.bruus.bigproject.gameobjects;

import com.bruus.bigproject.TheBigProject;

public 	class CityRoad extends GameObject {
	  public CityRoad(TheBigProject mainApplet,int x, int y, int w, int h) {
		    super( mainApplet,'M', x, y, w, h);
		    img = mainApplet.resourceManager.cityRoad;
		  }
		  public String toString()
		  { 
		    return "CityRoad"+coords();
		  }
		}
