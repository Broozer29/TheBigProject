package com.bruus.bigproject.gameobjects;

import com.bruus.bigproject.TheBigProject;

public 	class hauntedForestBushOne extends GameObject {
	  public hauntedForestBushOne(TheBigProject mainApplet, int x, int y, int w, int h) {
		    super( mainApplet,'X', x, y, w, h);
		    img = mainApplet.resourceManager.hauntedForestBushOne;
		  }
		  public String toString()
		  { 
		    return "hauntedForestBushOne"+coords();
		  }
		}
