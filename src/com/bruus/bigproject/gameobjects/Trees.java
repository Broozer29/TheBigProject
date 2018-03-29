package com.bruus.bigproject.gameobjects;

import com.bruus.bigproject.TheBigProject;

public 	class Trees extends GameObject {
	  public Trees(TheBigProject mainApplet,int x, int y, int w, int h) {
		    super( mainApplet,'T', x, y, w, h);
		    img = mainApplet.resourceManager.treesImage;
		  }
		  public String toString()
		  { 
		    return "Trees"+coords();
		  }
		}
