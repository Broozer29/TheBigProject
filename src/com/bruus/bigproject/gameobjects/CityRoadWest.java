package com.bruus.bigproject.gameobjects;

import com.bruus.bigproject.TheBigProject;

public 	class CityRoadWest extends GameObject {
	  public CityRoadWest(TheBigProject mainApplet,int x, int y, int w, int h) {
		    super( mainApplet,'N', x, y, w, h);
		    img = mainApplet.resourceManager.cityRoadWest;
		  }
		  public String toString()
		  { 
		    return "CityRoadWest"+coords();
		  }
		}
