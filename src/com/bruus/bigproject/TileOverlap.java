package com.bruus.bigproject;

import com.bruus.bigproject.gameobjects.GameObject;

public class TileOverlap {
	  GameObject theObject;
	  int surface;

	  TileOverlap(GameObject o, int s) {
	    theObject = o;
	    surface = s;
	  }

	  public int getSurface() {
	    return surface;
	  }

	  public void addSurface(int surface) {
	    this.surface += surface;
	  }

	  public char getType() {
	    return theObject.getType();
	  }

	  public String toString() {
	    return  theObject + " surface " + surface;
	  }
	}
