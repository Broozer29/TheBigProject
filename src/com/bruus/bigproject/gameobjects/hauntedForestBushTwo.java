package com.bruus.bigproject.gameobjects;

import com.bruus.bigproject.TheBigProject;

public 	class hauntedForestBushTwo extends GameObject {
	  public hauntedForestBushTwo(TheBigProject mainApplet, int x, int y, int w, int h) {
		    super( mainApplet,'C', x, y, w, h);
		    img = mainApplet.resourceManager.hauntedForestBushTwo;
		  }
		  public String toString()
		  { 
		    return "hauntedForestBushTwo"+coords();
		  }
		}
