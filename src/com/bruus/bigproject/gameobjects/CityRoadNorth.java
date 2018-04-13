package com.bruus.bigproject.gameobjects;

import com.bruus.bigproject.TheBigProject;

public 	class CityRoadNorth extends GameObject {
	  public CityRoadNorth(TheBigProject mainApplet,int x, int y, int w, int h) {
		    super( mainApplet,'M', x, y, w, h);
		    img = mainApplet.resourceManager.cityRoadNorth;
		  }
		  public String toString()
		  { 
		    return "CityRoad"+coords();
		  }
		}
