package com.bruus.bigproject.util;

import java.util.Comparator;

public class TileOverlapTypeComparator implements Comparator<TileOverlap> {
	public int compare(TileOverlap o1, TileOverlap o2) {
		if (!(o1 instanceof TileOverlap))
			return -1;
		if (!(o2 instanceof TileOverlap))
			return 1;

		return (o2).getType() - (o1).getType();
	}
}