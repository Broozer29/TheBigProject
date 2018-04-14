package com.bruus.bigproject.gameobjects;

import com.bruus.bigproject.TheBigProject;

public 	class CityTree extends GameObject {
	  public CityTree(TheBigProject mainApplet,int x, int y, int w, int h) {
		    super( mainApplet,'L', x, y, w, h);
		    img = mainApplet.resourceManager.cityTrees;
		  }
		  public String toString()
		  { 
		    return "CityTree"+coords();
		  }
		}
