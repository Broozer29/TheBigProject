package com.bruus.bigproject.gameobjects;

import com.bruus.bigproject.TheBigProject;

public class Barrier extends GameObject {
	  public Barrier(TheBigProject mainApplet,int x, int y, int w, int h) {
		    super(mainApplet, 'B', x, y, w, h);
		    if (mainApplet.currentZone == "TraderHouse") {
		      img = mainApplet.resourceManager.blackImage;
		    }
		    else if (mainApplet.currentZone == "Desert"){
		    	img = mainApplet.resourceManager.DesertGround;
		    }
		    else if (mainApplet.currentZone == "City"){
		    	img = mainApplet.resourceManager.blackImage;
		    }
		    else img = mainApplet.resourceManager.grassImage;
		  }
		  public String toString()
		  { 
		    return "Barrier"+coords();
		  }
		}
