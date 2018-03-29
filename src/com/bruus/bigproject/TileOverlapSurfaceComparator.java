package com.bruus.bigproject;

import java.util.Comparator;

public class TileOverlapSurfaceComparator implements Comparator<TileOverlap> {
	  public int compare(TileOverlap o1, TileOverlap o2) {
	    if (! (o1 instanceof TileOverlap)) return -1;
	    if (! (o2 instanceof TileOverlap)) return 1;

	    return ((TileOverlap) o2).getSurface() - ((TileOverlap) o1).getSurface();
	  }
	}
