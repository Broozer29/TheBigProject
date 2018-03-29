package com.bruus.bigproject.gameobjects;

import com.bruus.bigproject.TheBigProject;

public 	class Grass extends GameObject {
	  public Grass(TheBigProject mainApplet, int x, int y, int w, int h) {
		    super( mainApplet,'G', x, y, w, h);
		    img = mainApplet.resourceManager.grassImage;
		  }
		  public String toString()
		  { 
		    return "Grass"+coords();
		  }
		}
