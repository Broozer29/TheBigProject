package com.bruus.bigproject.gameobjects;

import com.bruus.bigproject.TheBigProject;

public 	class HauntedForestWater extends GameObject {
	  public HauntedForestWater(TheBigProject mainApplet,int x, int y, int w, int h) {
		    super( mainApplet,'Z', x, y, w, h);
		    img = mainApplet.resourceManager.hauntedForestWaterImage;
		  }
		  public String toString()
		  { 
		    return "HauntedForestWater"+coords();
		  }
		}
