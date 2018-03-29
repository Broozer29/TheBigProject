package com.bruus.bigproject;

public class traderHouseFloor extends GameObject {
	  public traderHouseFloor(TheBigProject mainApplet,int x, int y, int w, int h) {
		    super(mainApplet, 'O', x, y, w, h);
		    img = mainApplet.resourceManager.traderHouseGround;
		  }
		  public String toString()
		  { 
		    return "traderHouseFloor"+coords();
		  }
		}
