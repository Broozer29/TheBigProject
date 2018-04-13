package com.bruus.bigproject.gameobjects;

import com.bruus.bigproject.TheBigProject;

public 	class CityGrass extends GameObject {
	  public CityGrass(TheBigProject mainApplet,int x, int y, int w, int h) {
		    super( mainApplet,'V', x, y, w, h);
		    img = mainApplet.resourceManager.cityGrass;
		  }
		  public String toString()
		  { 
		    return "CityGrass"+coords();
		  }
		}
