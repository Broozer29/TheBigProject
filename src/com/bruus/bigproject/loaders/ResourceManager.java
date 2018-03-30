package com.bruus.bigproject.loaders;

import java.io.File;

import gifAnimation.Gif;
import processing.core.PApplet;
import processing.core.PImage;
import processing.sound.SoundFile;

public class ResourceManager {
	public SoundFile desertMusic, forestMusic, squareMusic, traderHouseMusic, soundFile;
	public Gif currentAnimation, walkingRight, walkingLeft, lookingLeft, lookingRight, lookingUp, lookingDown,
			attackLeft, attackRight, attackBowLeft, attackBowRight, projectileRight, projectileLeft, projectileSaber;

	public Gif lanceKnightRightAnimation, lanceKnightLeftAnimation, lanceKnightRightAttack, lanceKnightRightIdle,
			lanceKnightLeftIdle, lanceKnightLeftAttack;
	public Gif paladinRightIdle, paladinLeftIdle, paladinLeftAttack, paladinRightAttack;
	public Gif skeletonRightIdle, skeletonLeftIdle, skeletonLeftAttack, skeletonRightAttack, skeletonRightWalk,
			skeletonLeftWalk, skeletonAnimation;

	public Gif deathExplosion, chestOpen, chestClosed, fireBurning;

	public Gif oldDudeImage, oldDudePurchase;

	/*
	 * public PImage oldDudeImage, oldDudeLeft, oldDudeRight, oldDudeUp,
	 * oldDudeDown, oldDudeRightUp, oldDudeRightDown, oldDudeLeftUp,
	 * oldDudeLeftDown;
	 */
	public PImage elementsImage, grassImage, waterImage, roadImage, treesImage, traderHouse, traderHouseGround,
			blackImage, traderHouseDoor;
	public PImage DesertStoneOne, DesertGround, DesertStoneTwo, DesertStoneThree;
	public PImage hauntedForestImage;
	public PImage arrowLeft, arrowRight;
	public PImage elementImageFire, elementImageLight, elememtImageWater, elementImageDark, elementImageEarth,
			elementImageLightning, elementImageWind, elementImageIce, elementImageReset, elementSelectedImage;
	private String baseFolder = new File("bin").getAbsolutePath();

	public void loadImages(PApplet myApplet) {

		// Character //
		walkingRight = new Gif(myApplet, baseFolder + "/Saber/SaberGifs/SaberWalkingRight.gif");
		walkingLeft = new Gif(myApplet, baseFolder + "/Saber/SaberGifs/SaberWalkingLeft.gif");

		lookingLeft = new Gif(myApplet, baseFolder + "/Saber/SaberGifs/SaberIdleLeft.gif");
		lookingRight = new Gif(myApplet, baseFolder + "/Saber/SaberGifs/SaberIdleRight.gif");

		attackRight = new Gif(myApplet, baseFolder + "/Saber/SaberGifs/SaberRightAttack.gif");
		attackLeft = new Gif(myApplet, baseFolder + "/Saber/SaberGifs/SaberAttackLeft.gif");
		attackBowLeft = new Gif(myApplet, baseFolder + "/Saber/SaberGifs/SaberRangedAttackLeft.gif");
		attackBowRight = new Gif(myApplet, baseFolder + "/Saber/SaberGifs/SaberRangedAttackRight.gif");

		arrowLeft = myApplet.loadImage(baseFolder + "/Loading_Images/ArrowLeft.png");
		arrowRight = myApplet.loadImage(baseFolder + "/Loading_Images/ArrowRight.png");
		projectileRight = new Gif(myApplet, baseFolder + "/Loading_Images/SaberRangedRight.gif");
		projectileLeft = new Gif(myApplet, baseFolder + "/Loading_Images/SaberRangedLeft.gif");

		projectileSaber = projectileLeft;
		currentAnimation = lookingLeft;

		// Old dude //
		
		/*
		 * oldDudeLeft = myApplet.loadImage(baseFolder +
		 * "/Loading_Images/OldDude/OldDudeLookingLeft.png"); oldDudeRight =
		 * myApplet.loadImage(baseFolder +
		 * "/Loading_Images/OldDude/OldDudeLookingRight.png"); oldDudeUp =
		 * myApplet.loadImage(baseFolder +
		 * "/Loading_Images/OldDude/OldDudeLookingUp.png"); oldDudeDown =
		 * myApplet.loadImage(baseFolder +
		 * "/Loading_Images/OldDude/OldDudeLookingDown.png"); oldDudeRightUp =
		 * myApplet.loadImage(baseFolder +
		 * "/Loading_Images/OldDude/OldDudeLookingRightUp.png");
		 * oldDudeRightDown = myApplet.loadImage(baseFolder +
		 * "/Loading_Images/OldDude/OldDudeLookingRightDown.png"); oldDudeLeftUp
		 * = myApplet.loadImage(baseFolder +
		 * "/Loading_Images/OldDude/OldDudeLookingLeftUp.png"); oldDudeLeftDown
		 * = myApplet.loadImage(baseFolder +
		 * "/Loading_Images/OldDude/OldDudeLookingLeftDown.png");
		 */
		oldDudePurchase = new Gif(myApplet,"/Users/bruusriezebos/Git/TheBigProject/resources/gifImages/untitled folder/oldDudePurchase.gif");
		
		oldDudeImage = oldDudePurchase;

		// LanceKnights //
		lanceKnightRightAttack = new Gif(myApplet, baseFolder + "/Loading_Images/LanceKnightAttackRight.gif");
		lanceKnightRightIdle = new Gif(myApplet, baseFolder + "/Loading_Images/lanceKnightIdleRight.gif");
		lanceKnightLeftIdle = new Gif(myApplet, baseFolder + "/Loading_Images/lanceKnightIdleLeft.gif");
		lanceKnightLeftAttack = new Gif(myApplet, baseFolder + "/Loading_Images/lanceKnightAttackLeft.gif");

		lanceKnightLeftAnimation = lanceKnightLeftIdle;
		lanceKnightRightAnimation = lanceKnightRightIdle;

		// Paladins //
		paladinLeftIdle = new Gif(myApplet, baseFolder + "/Loading_Images/PaladinIdleLeft.gif");
		paladinRightIdle = new Gif(myApplet, baseFolder + "/Loading_Images/PaladingIdleRight.gif");
		paladinLeftAttack = new Gif(myApplet, baseFolder + "/Loading_Images/PaladinAttackLeft.gif");
		paladinRightAttack = new Gif(myApplet, baseFolder + "/Loading_Images/PaladinAttackRight.gif");

		// Skeletons //

		skeletonRightIdle = new Gif(myApplet,
				"/Users/bruusriezebos/Git/TheBigProject/resources/Loading_Images/Skeleton/SkeletonIdleRight.gif");
		skeletonLeftIdle = new Gif(myApplet,
				"/Users/bruusriezebos/Git/TheBigProject/resources/Loading_Images/Skeleton/SkeletonIdleLeft.gif");
		skeletonLeftAttack = new Gif(myApplet,
				"/Users/bruusriezebos/Git/TheBigProject/resources/Loading_Images/Skeleton/SkeletonAttackLeft.gif");
		skeletonRightAttack = new Gif(myApplet,
				"/Users/bruusriezebos/Git/TheBigProject/resources/Loading_Images/Skeleton/SkeletonAttackRight.gif");
		skeletonRightWalk = new Gif(myApplet,
				"/Users/bruusriezebos/Git/TheBigProject/resources/Loading_Images/Skeleton/SkeletonWalkRight.gif");
		skeletonLeftWalk = new Gif(myApplet,
				"/Users/bruusriezebos/Git/TheBigProject/resources/Loading_Images/Skeleton/SkeletonWalkLeft.gif");

		// General enemies //
		deathExplosion = new Gif(myApplet, baseFolder + "/Loading_Images/DeathExplosion.gif");
		chestOpen = new Gif(myApplet, baseFolder + "/Loading_Images/ChestOpen.gif");
		chestClosed = new Gif(myApplet, baseFolder + "/Loading_Images/ChestClosed.gif");
		fireBurning = new Gif(myApplet, baseFolder + "/Loading_Images/Burning.gif");

		// Tiles //

		grassImage = myApplet.loadImage(baseFolder + "/Loading_Images/Grass.png");
		waterImage = myApplet.loadImage(baseFolder + "/Loading_Images/Water.png");
		roadImage = myApplet.loadImage(baseFolder + "/Loading_Images/Road.png");
		treesImage = myApplet.loadImage(baseFolder + "/Loading_Images/Trees.png");
		traderHouse = myApplet.loadImage(baseFolder + "/Loading_Images/Huisje?.png");
		traderHouseGround = myApplet.loadImage(baseFolder + "/Loading_Images/HouseTile.png");
		blackImage = myApplet.loadImage(baseFolder + "/Loading_Images/blackImage.png");
		traderHouseDoor = myApplet.loadImage(baseFolder + "/Loading_Images/Door1.png");

		hauntedForestImage = myApplet.loadImage(baseFolder + "/Loading_Images/TimTile.png");

		DesertGround = myApplet.loadImage(baseFolder + "/Loading_Images/DesertGrond.png");
		DesertStoneOne = myApplet.loadImage(baseFolder + "/Loading_Images/DesertStone1.png");
		DesertStoneTwo = myApplet.loadImage(baseFolder + "/Loading_Images/DesertStone2.png");
		DesertStoneThree = myApplet.loadImage(baseFolder + "/Loading_Images/DesertStone3.png");

		// Elements //
		elementsImage = myApplet.loadImage(baseFolder + "/Loading_Images/Inventory leeg.jpg");
		elementImageFire = myApplet.loadImage(baseFolder + "/Loading_Images/ElementsFire.png");
		elementImageLight = myApplet.loadImage(baseFolder + "/Loading_Images/ElementsLight.png");
		elememtImageWater = myApplet.loadImage(baseFolder + "/Loading_Images/ElementsWater.png");
		elementImageDark = myApplet.loadImage(baseFolder + "/Loading_Images/ElementsDark.png");
		elementImageEarth = myApplet.loadImage(baseFolder + "/Loading_Images/ElementsEarth.png");
		elementImageLightning = myApplet.loadImage(baseFolder + "/Loading_Images/ElementsLightning.png");
		elementImageWind = myApplet.loadImage(baseFolder + "/Loading_Images/ElementsWind.png");
		elementImageIce = myApplet.loadImage(baseFolder + "/Loading_Images/ElementsIce.png");
		elementImageReset = myApplet.loadImage(baseFolder + "/Loading_Images/Reset.png");
		elementSelectedImage = myApplet.loadImage(baseFolder + "/Loading_Images/Circle.png");
	}

	public void loadMusic(PApplet myApplet) {
		forestMusic = new SoundFile(myApplet, baseFolder + "/Music_Folder/NieRForest.mp3");
		squareMusic = new SoundFile(myApplet, baseFolder + "/Music_Folder/ProleteR - Faidherbe square.mp3");
		desertMusic = new SoundFile(myApplet, baseFolder + "/Music_Folder/NieR Automata OST - Great Desert.mp3");
		traderHouseMusic = new SoundFile(myApplet,
				baseFolder + "/Music_Folder/Parasyte - Next To You (Anime Version).mp3");
		soundFile = forestMusic;
	}
}
