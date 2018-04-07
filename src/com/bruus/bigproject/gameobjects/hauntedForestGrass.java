package com.bruus.bigproject.gameobjects;

import com.bruus.bigproject.TheBigProject;

public 	class hauntedForestGrass extends GameObject {
	  public hauntedForestGrass(TheBigProject mainApplet,int x, int y, int w, int h) {
		    super(mainApplet,'Q', x, y, w, h);
		    img = mainApplet.resourceManager.hauntedForestGrassImage;
		  }
		  public String toString() {
		    return "hauntedForestGrass"+coords();
		  }
		}
