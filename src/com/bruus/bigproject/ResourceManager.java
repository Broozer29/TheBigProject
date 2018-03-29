package com.bruus.bigproject;

import gifAnimation.Gif;
import processing.core.PApplet;
import processing.core.PImage;
import processing.sound.SoundFile;

public class ResourceManager {
	public SoundFile desertMusic, forestMusic, squareMusic, traderHouseMusic, soundFile;
	public Gif currentAnimation, walkingRight, walkingLeft, lookingLeft, lookingRight, lookingUp, lookingDown, attackLeft, attackRight, attackBowLeft, attackBowRight, projectileRight, projectileLeft, projectileSaber;

	public Gif lanceKnightRightAnimation, lanceKnightLeftAnimation, lanceKnightRightAttack, lanceKnightRightIdle, lanceKnightLeftIdle, lanceKnightLeftAttack;
	public Gif paladinRightIdle, paladinLeftIdle, paladinLeftAttack, paladinRightAttack;

	public Gif deathExplosion, chestOpen, chestClosed, fireBurning;
	public PImage oldDudeImage, oldDudeLeft, oldDudeRight, oldDudeUp, oldDudeDown, oldDudeRightUp, oldDudeRightDown, oldDudeLeftUp, oldDudeLeftDown;
	public PImage elementsImage, grassImage, waterImage, roadImage, treesImage, traderHouse, traderHouseGround, blackImage, traderHouseDoor;
	public PImage DesertStoneOne, DesertGround, DesertStoneTwo, DesertStoneThree;
	public PImage hauntedForestImage;
	public PImage arrowLeft, arrowRight;
	public PImage elementImageFire, elementImageLight, elememtImageWater, elementImageDark, elementImageEarth, elementImageLightning, elementImageWind, elementImageIce, elementImageReset, elementSelectedImage;

	void loadImages(PApplet myApplet) {
	  // Character //
	  walkingRight = new Gif(myApplet, "/Users/bruusriezebos/Documents/Processing/TheBigProject_0_3/Saber?/SaberGifs/SaberWalkingRight.gif");
	  walkingLeft = new Gif(myApplet, "/Users/bruusriezebos/Documents/Processing/TheBigProject_0_3/Saber?/SaberGifs/SaberWalkingLeft.gif");

	  lookingLeft = new Gif(myApplet, "/Users/bruusriezebos/Documents/Processing/TheBigProject_0_3/Saber?/SaberGifs/SaberIdleLeft.gif");
	  lookingRight = new Gif(myApplet, "/Users/bruusriezebos/Documents/Processing/TheBigProject_0_3/Saber?/SaberGifs/SaberIdleRight.gif");

	  attackRight = new Gif(myApplet, "/Users/bruusriezebos/Documents/Processing/TheBigProject_0_3/Saber?/SaberGifs/SaberRightAttack.gif");
	  attackLeft = new Gif(myApplet, "/Users/bruusriezebos/Documents/Processing/TheBigProject_0_3/Saber?/SaberGifs/SaberAttackLeft.gif");
	  attackBowLeft = new Gif(myApplet, "/Users/bruusriezebos/Documents/Processing/TheBigProject_0_3/Saber?/SaberGifs/SaberRangedAttackLeft.gif");
	  attackBowRight = new Gif(myApplet, "/Users/bruusriezebos/Documents/Processing/TheBigProject_0_3/Saber?/SaberGifs/SaberRangedAttackRight.gif");

	  arrowLeft = myApplet.loadImage("/Users/bruusriezebos/Documents/Processing/TheBigProject_0_3/Loading_Images/ArrowLeft.png");
	  arrowRight = myApplet.loadImage("/Users/bruusriezebos/Documents/Processing/TheBigProject_0_3/Loading_Images/ArrowRight.png");
	  projectileRight = new Gif(myApplet, "/Users/bruusriezebos/Documents/Processing/TheBigProject_0_3/Loading_Images/SaberRangedRight.gif"); 
	  projectileLeft = new Gif (myApplet, "/Users/bruusriezebos/Documents/Processing/TheBigProject_0_3/Loading_Images/SaberRangedLeft.gif");


	  projectileSaber = projectileLeft;
	  currentAnimation = lookingLeft;

	  // Old dude //
	  oldDudeLeft = myApplet.loadImage("/Users/bruusriezebos/Documents/Processing/TheBigProject_0_3/Loading_Images/OldDude/OldDudeLookingLeft.png");
	  oldDudeRight = myApplet.loadImage("/Users/bruusriezebos/Documents/Processing/TheBigProject_0_3/Loading_Images/OldDude/OldDudeLookingRight.png");
	  oldDudeUp = myApplet.loadImage("/Users/bruusriezebos/Documents/Processing/TheBigProject_0_3/Loading_Images/OldDude/OldDudeLookingUp.png");
	  oldDudeDown = myApplet.loadImage("/Users/bruusriezebos/Documents/Processing/TheBigProject_0_3/Loading_Images/OldDude/OldDudeLookingDown.png");
	  oldDudeRightUp = myApplet.loadImage("/Users/bruusriezebos/Documents/Processing/TheBigProject_0_3/Loading_Images/OldDude/OldDudeLookingRightUp.png");
	  oldDudeRightDown = myApplet.loadImage("/Users/bruusriezebos/Documents/Processing/TheBigProject_0_3/Loading_Images/OldDude/OldDudeLookingRightDown.png");
	  oldDudeLeftUp = myApplet.loadImage("/Users/bruusriezebos/Documents/Processing/TheBigProject_0_3/Loading_Images/OldDude/OldDudeLookingLeftUp.png");
	  oldDudeLeftDown = myApplet.loadImage("/Users/bruusriezebos/Documents/Processing/TheBigProject_0_3/Loading_Images/OldDude/OldDudeLookingLeftDown.png");

	  oldDudeImage = oldDudeLeft;

	  // LanceKnights //
	  lanceKnightRightAttack = new Gif(myApplet, "/Users/bruusriezebos/Documents/Processing/TheBigProject_0_3/Loading_Images/LanceKnightAttackRight.gif");
	  lanceKnightRightIdle = new Gif (myApplet, "/Users/bruusriezebos/Documents/Processing/TheBigProject_0_3/Loading_Images/lanceKnightIdleRight.gif");
	  lanceKnightLeftIdle = new Gif (myApplet, "/Users/bruusriezebos/Documents/Processing/TheBigProject_0_3/Loading_Images/lanceKnightIdleLeft.gif");
	  lanceKnightLeftAttack = new Gif (myApplet, "/Users/bruusriezebos/Documents/Processing/TheBigProject_0_3/Loading_Images/lanceKnightAttackLeft.gif");

	  lanceKnightLeftAnimation = lanceKnightLeftIdle;
	  lanceKnightRightAnimation = lanceKnightRightIdle;

	  // Paladins //
	  paladinLeftIdle = new Gif(myApplet, "/Users/bruusriezebos/Documents/Processing/TheBigProject_0_3/Loading_Images/PaladinIdleLeft.gif");
	  paladinRightIdle = new Gif(myApplet, "/Users/bruusriezebos/Documents/Processing/TheBigProject_0_3/Loading_Images/PaladingIdleRight.gif");
	  paladinLeftAttack = new Gif(myApplet, "/Users/bruusriezebos/Documents/Processing/TheBigProject_0_3/Loading_Images/PaladinAttackLeft.gif");
	  paladinRightAttack = new Gif(myApplet, "/Users/bruusriezebos/Documents/Processing/TheBigProject_0_3/Loading_Images/PaladinAttackRight.gif");

	  // General enemies //
	  deathExplosion = new Gif (myApplet, "/Users/bruusriezebos/Documents/Processing/TheBigProject_0_3/Loading_Images/DeathExplosion.gif");
	  chestOpen = new Gif (myApplet, "/Users/bruusriezebos/Documents/Processing/TheBigProject_0_3/Loading_Images/ChestOpen.gif");
	  chestClosed = new Gif (myApplet, "/Users/bruusriezebos/Documents/Processing/TheBigProject_0_3/Loading_Images/ChestClosed.gif");
	  fireBurning = new Gif (myApplet, "/Users/bruusriezebos/Documents/Processing/TheBigProject_0_3/Loading_Images/Burning.gif");

	  // Tiles //

	  grassImage = myApplet.loadImage("/Users/bruusriezebos/Documents/Processing/TheBigProject_0_3/Loading_Images/Grass.png");
	  waterImage = myApplet.loadImage("/Users/bruusriezebos/Documents/Processing/TheBigProject_0_3/Loading_Images/Water.png");
	  roadImage = myApplet.loadImage("/Users/bruusriezebos/Documents/Processing/TheBigProject_0_3/Loading_Images/Road.png");
	  treesImage = myApplet.loadImage("/Users/bruusriezebos/Documents/Processing/TheBigProject_0_3/Loading_Images/Trees.png");
	  traderHouse = myApplet.loadImage("/Users/bruusriezebos/Documents/Processing/TheBigProject_0_3/Loading_Images/Huisje?.png");
	  traderHouseGround = myApplet.loadImage("/Users/bruusriezebos/Documents/Processing/TheBigProject_0_3/Loading_Images/HouseTile.png");
	  blackImage = myApplet.loadImage("/Users/bruusriezebos/Documents/Processing/TheBigProject_0_3/Loading_Images/blackImage.png");
	  traderHouseDoor = myApplet.loadImage("/Users/bruusriezebos/Documents/Processing/TheBigProject_0_3/Loading_Images/Door1.png");

	  hauntedForestImage = myApplet.loadImage("/Users/bruusriezebos/Documents/Processing/TheBigProject_0_3/Loading_Images/TimTile.png");

	  DesertGround = myApplet.loadImage("/Users/bruusriezebos/Documents/Processing/TheBigProject_0_3/Loading_Images/DesertGrond.png");
	  DesertStoneOne = myApplet.loadImage("/Users/bruusriezebos/Documents/Processing/TheBigProject_0_3/Loading_Images/DesertStone1.png");
	  DesertStoneTwo = myApplet.loadImage("/Users/bruusriezebos/Documents/Processing/TheBigProject_0_3/Loading_Images/DesertStone2.png");
	  DesertStoneThree = myApplet.loadImage("/Users/bruusriezebos/Documents/Processing/TheBigProject_0_3/Loading_Images/DesertStone3.png");

	  // Elements //
	  elementsImage = myApplet.loadImage("/Users/bruusriezebos/Documents/Processing/TheBigProject_0_3/Loading_Images/Inventory leeg.jpg");
	  elementImageFire = myApplet.loadImage("/Users/bruusriezebos/Documents/Processing/TheBigProject_0_3/Loading_Images/ElementsFire.png");
	  elementImageLight = myApplet.loadImage("/Users/bruusriezebos/Documents/Processing/TheBigProject_0_3/Loading_Images/ElementsLight.png");
	  elememtImageWater =myApplet.loadImage("/Users/bruusriezebos/Documents/Processing/TheBigProject_0_3/Loading_Images/ElementsWater.png");
	  elementImageDark = myApplet.loadImage("/Users/bruusriezebos/Documents/Processing/TheBigProject_0_3/Loading_Images/ElementsDark.png");
	  elementImageEarth =myApplet.loadImage("/Users/bruusriezebos/Documents/Processing/TheBigProject_0_3/Loading_Images/ElementsEarth.png");
	  elementImageLightning = myApplet.loadImage("/Users/bruusriezebos/Documents/Processing/TheBigProject_0_3/Loading_Images/ElementsLightning.png");
	  elementImageWind = myApplet.loadImage("/Users/bruusriezebos/Documents/Processing/TheBigProject_0_3/Loading_Images/ElementsWind.png");
	  elementImageIce = myApplet.loadImage("/Users/bruusriezebos/Documents/Processing/TheBigProject_0_3/Loading_Images/ElementsIce.png");
	  elementImageReset = myApplet.loadImage("/Users/bruusriezebos/Documents/Processing/TheBigProject_0_3/Loading_Images/Reset.png");
	  elementSelectedImage = myApplet.loadImage("/Users/bruusriezebos/Documents/Processing/TheBigProject_0_3/Loading_Images/Circle.png");
	}
	

	void loadMusic(PApplet myApplet) {
	  forestMusic = new SoundFile(myApplet, "/Users/bruusriezebos/Documents/Processing/TheBigProject_0_3/Music_Folder/NieRForest.mp3");
	  squareMusic = new SoundFile(myApplet, "/Users/bruusriezebos/Documents/Processing/TheBigProject_0_3/Music_Folder/ProleteR - Faidherbe square.mp3");
	  desertMusic = new SoundFile(myApplet, "/Users/bruusriezebos/Documents/Processing/TheBigProject_0_3/Music_Folder/NieR Automata OST - Great Desert.mp3");
	  traderHouseMusic = new SoundFile (myApplet, "/Users/bruusriezebos/Documents/Processing/TheBigProject_0_3/Music_Folder/Parasyte - Next To You (Anime Version).mp3");
	  soundFile = forestMusic;
	}
}
