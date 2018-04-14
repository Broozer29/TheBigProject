package com.bruus.bigproject.loaders;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.bruus.bigproject.TheBigProject;
import com.bruus.bigproject.gameobjects.Barrier;
import com.bruus.bigproject.gameobjects.CityGrass;
import com.bruus.bigproject.gameobjects.CityTree;
import com.bruus.bigproject.gameobjects.CityRoad;
import com.bruus.bigproject.gameobjects.DesertGround;
import com.bruus.bigproject.gameobjects.DesertStoneOne;
import com.bruus.bigproject.gameobjects.DesertStoneThree;
import com.bruus.bigproject.gameobjects.DesertStoneTwo;
import com.bruus.bigproject.gameobjects.GameObject;
import com.bruus.bigproject.gameobjects.Grass;
import com.bruus.bigproject.gameobjects.HauntedForestWater;
import com.bruus.bigproject.gameobjects.Road;
import com.bruus.bigproject.gameobjects.Trees;
import com.bruus.bigproject.gameobjects.Water;
import com.bruus.bigproject.gameobjects.hauntedForestBushOne;
import com.bruus.bigproject.gameobjects.hauntedForestBushTwo;
import com.bruus.bigproject.gameobjects.hauntedForestGrass;
import com.bruus.bigproject.gameobjects.traderHouseFloor;

public class MapLoader { // <>//
	TheBigProject theBigProject;

	public MapLoader(TheBigProject theBigProject) {
		this.theBigProject = theBigProject;
	}

	public ArrayList<GameObject> loadTiles(String filename) throws IOException {

		BufferedReader in = new BufferedReader(new FileReader(new File(filename)));

		ArrayList<GameObject> result = new ArrayList<GameObject>();

		int y = 0;
		for (String line = in.readLine(); line != null; line = in.readLine()) {
			for (int x = 0; x < line.length(); x++) {

				char tileType = line.charAt(x);

				if (tileType == 'W')
					result.add(new Water(theBigProject, x * 50, y * 50, 50, 50));
				else if (tileType == 'T')
					result.add(new Trees(theBigProject, x * 50, y * 50, 50, 50));
				else if (tileType == 'R')
					result.add(new Road(theBigProject, x * 50, y * 50, 50, 50));
				else if (tileType == 'G')
					result.add(new Grass(theBigProject, x * 50, y * 50, 50, 50));
				else if (tileType == 'B')
					result.add(new Barrier(theBigProject, x * 50, y * 50, 50, 50));
				else if (tileType == 'O')
					result.add(new traderHouseFloor(theBigProject, x * 50, y * 50, 50, 50));

				else if (tileType == 'A')
					result.add(new DesertGround(theBigProject, x * 50, y * 50, 50, 50));
				else if (tileType == 'a')
					result.add(new DesertStoneOne(theBigProject, x * 50, y * 50, 50, 50));
				else if (tileType == 'b')
					result.add(new DesertStoneTwo(theBigProject, x * 50, y * 50, 50, 50));
				else if (tileType == 'c')
					result.add(new DesertStoneThree(theBigProject, x * 50, y * 50, 50, 50));

				else if (tileType == 'Q')
					result.add(new hauntedForestGrass(theBigProject, x * 50, y * 50, 50, 50));
				else if (tileType == 'Z')
					result.add(new HauntedForestWater(theBigProject, x * 50, y * 50, 50, 50));
				else if (tileType == 'X')
					result.add(new hauntedForestBushOne(theBigProject, x * 50, y * 50, 50, 50));
				else if (tileType == 'C')
					result.add(new hauntedForestBushTwo(theBigProject, x * 50, y * 50, 50, 50));

				else if (tileType == 'L')
					result.add(new CityTree(theBigProject, x * 50, y * 50, 50, 50));
				else if (tileType == 'V')
					result.add(new CityGrass(theBigProject, x * 50, y * 50, 50, 50));
				else if (tileType == 'M')
					result.add(new CityRoad(theBigProject, x * 50, y * 50, 50, 50));
				else
					System.out.println("Ignored " + tileType);
			}
			y++;
		}
		in.close();
		return result;
	}
}