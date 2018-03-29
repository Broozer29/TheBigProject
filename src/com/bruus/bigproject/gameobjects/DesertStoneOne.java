package com.bruus.bigproject.gameobjects;

import com.bruus.bigproject.TheBigProject;

public 	class DesertStoneOne extends GameObject {
	  public DesertStoneOne(TheBigProject mainApplet,int x, int y, int w, int h) {
		    super( mainApplet,'a', x, y, w, h);
		    img = mainApplet.resourceManager.DesertStoneOne;
		  }
		  public String toString() {
		    return "DesertStoneOne"+coords();
		  }
		}
