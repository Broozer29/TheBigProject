package com.bruus.bigproject.loaders;

import java.io.File;

import gifAnimation.Gif;
import processing.core.PApplet;
import processing.core.PImage;
import processing.sound.SoundFile;

public class ResourceManager {
	public SoundFile hauntedForestMusic, desertMusic, forestMusic, squareMusic, traderHouseMusic, soundFile;
	public Gif currentAnimation, walkingRight, walkingLeft, lookingLeft, lookingRight, lookingUp, lookingDown,
			attackLeft, attackRight, attackBowLeft, attackBowRight, projectileRight, projectileLeft, projectileSaber;
	public Gif saberDoubleALeft, saberDoubleARight, saberMeleeRangedLeft, saberMeleeRangedRight;

	public Gif lanceKnightRightAnimation, lanceKnightLeftAnimation, lanceKnightRightAttack, lanceKnightRightIdle,
			lanceKnightLeftIdle, lanceKnightLeftAttack;
	public Gif paladinRightIdle, paladinLeftIdle, paladinLeftAttack, paladinRightAttack, paladinLeftWalk,
			paladinRightWalk;
	public Gif skeletonRightIdle, skeletonLeftIdle, skeletonLeftAttack, skeletonRightAttack, skeletonRightWalk,
			skeletonLeftWalk, skeletonAnimation;
	public Gif houndRightIdle, houndLeftIdle, houndRightAttack, houndLeftAttack, houndRightWalk, houndLeftWalk;

	public Gif medusaLeftIdle, medusaRightIdle, medusaLeftWalk, medusaRightWalk, medusaLeftAttack, medusaRightAttack,
			medusaProjectile;

	public Gif scorpionLeftIdle, scorpionRightIdle, scorpionLeftAttack, scorpionRightAttack, scorpionLeftWalk,
			scorpionRightWalk;

	public Gif deathExplosion, chestOpen, chestClosed, fireBurning;

	public Gif oldDudeImage, oldDudePurchase, oldDudeIdleLeft, oldDudeIdleRight;
	public Gif blackSmith;

	/*
	 * public PImage oldDudeImage, oldDudeLeft, oldDudeRight, oldDudeUp,
	 * oldDudeDown, oldDudeRightUp, oldDudeRightDown, oldDudeLeftUp,
	 * oldDudeLeftDown;
	 */
	public PImage grassImage, waterImage, roadImage, treesImage, traderHouse, traderHouseGround, blackImage,
			traderHouseDoor;
	public PImage DesertStoneOne, DesertGround, DesertStoneTwo, DesertStoneThree;
	public PImage hauntedForestGrassImage, hauntedForestWaterImage, hauntedForestBushOne, hauntedForestBushTwo;
	public PImage arrowLeft, arrowRight;
	public PImage elementImageFire, elementImageLight, elememtImageWater, elementImageDark, elementImageEarth,
			elementImageLightning, elementImageWind, elementImageIce, elementImageReset, elementSelectedImage;
	public PImage swordImage, swordOne, swordTwo, swordThree, swordFour, swordFive, swordSix;
	public PImage axeImage, axeOne, axeTwo, axeThree, axeFour, axeFive;
	public PImage scimitarImage, scimitarOne, scimitarTwo, scimitarThree, scimitarFour;
	public PImage helmImage, helmOne, helmTwo, helmThree, helmFour, helmFive;
	public PImage chestImage, chestOne, chestTwo, chestThree, chestFour, chestFive;
	public PImage pantsImage, pantsOne, pantsTwo, pantsThree, pantsFour, pantsFive;
	public PImage bootsImage, bootsOne, bootsTwo, bootsThree, bootsFour, bootsFive;
	public PImage blacksmithHouse;
	public PImage scorpionScale, medusaHair, houndTooth, skeletonBone, paladinArmorScrap, lanceKnightSpearTip;
	public PImage cityGrass, cityRoadWest, cityRoadNorth, cityRoadRightTopCorner, cityRoadRightBotCorner, cityRoadLeftTopCorner, cityRoadLeftBotCorner, cityTree;

	private String baseFolder = new File("bin").getAbsolutePath();

	public void loadImages(PApplet myApplet) {
		//blackSmith = new Gif(myApplet, baseFolder + "/Loading_Images/SmithAnimation.gif");
		blacksmithHouse = myApplet.loadImage(baseFolder + "/Loading_Images/BlackSmithHouse.png");

		// Character //
		walkingRight = new Gif(myApplet, baseFolder + "/Saber/SaberGifs/SaberWalkingRight.gif");
		walkingLeft = new Gif(myApplet, baseFolder + "/Saber/SaberGifs/SaberWalkingLeft.gif");

		lookingLeft = new Gif(myApplet, baseFolder + "/Saber/SaberGifs/SaberIdleLeft.gif");
		lookingRight = new Gif(myApplet, baseFolder + "/Saber/SaberGifs/SaberIdleRight.gif");

		attackRight = new Gif(myApplet, baseFolder + "/Saber/SaberGifs/SaberRightAttack.gif");
		attackLeft = new Gif(myApplet, baseFolder + "/Saber/SaberGifs/SaberAttackLeft.gif");
		//saberDoubleARight = new Gif(myApplet, baseFolder + "/Saber/SaberGifs/SaberDoubleARight.gif");
		//saberDoubleALeft = new Gif(myApplet, baseFolder + "/Saber/SaberGifs/SaberDoubleALeft.gif");
		//saberMeleeRangedLeft = new Gif(myApplet, baseFolder + "/Saber/SaberGifs/SaberSwordRangedAttackLeft.gif");
		//saberMeleeRangedRight = new Gif(myApplet, baseFolder + "/Saber/SaberGifs/SaberSwordRangedAttackRight.gif");
		attackBowLeft = new Gif(myApplet, baseFolder + "/Saber/SaberGifs/SaberRangedAttackLeft.gif");
		attackBowRight = new Gif(myApplet, baseFolder + "/Saber/SaberGifs/SaberRangedAttackRight.gif");

		projectileRight = new Gif(myApplet, baseFolder + "/Loading_Images/SaberRangedRight.gif");
		projectileLeft = new Gif(myApplet, baseFolder + "/Loading_Images/SaberRangedLeft.gif");

		projectileSaber = projectileLeft;
		currentAnimation = lookingLeft;

		// Materials //
		scorpionScale = myApplet.loadImage(baseFolder + "/Loading_Images/Materials/ScorpionScale.png");
		medusaHair = myApplet.loadImage(baseFolder + "/Loading_Images/Materials/MedusaHair.png");
		skeletonBone = myApplet.loadImage(baseFolder + "/Loading_Images/Materials/SkeletonBone.png");
		houndTooth = myApplet.loadImage(baseFolder + "/Loading_Images/Materials/HoundTeeth.png");
		lanceKnightSpearTip = myApplet.loadImage(baseFolder + "/Loading_Images/Materials/LanceKnightSpearTip.png");
		paladinArmorScrap = myApplet.loadImage(baseFolder + "/Loading_Images/Materials/PaladinArmorScraps.png");

		// Character Weapons //
		swordOne = myApplet.loadImage(baseFolder + "/Loading_Images/Weapons/Sword1.png");
		swordTwo = myApplet.loadImage(baseFolder + "/Loading_Images/Weapons/Sword2.png");
		swordThree = myApplet.loadImage(baseFolder + "/Loading_Images/Weapons/Sword3.png");
		swordFour = myApplet.loadImage(baseFolder + "/Loading_Images/Weapons/Sword4.png");
		swordFive = myApplet.loadImage(baseFolder + "/Loading_Images/Weapons/Sword5.png");
		swordSix = myApplet.loadImage(baseFolder + "/Loading_Images/Weapons/Sword6.png");

		axeOne = myApplet.loadImage(baseFolder + "/Loading_Images/Weapons/Axe1.png");
		axeTwo = myApplet.loadImage(baseFolder + "/Loading_Images/Weapons/Axe2.png");
		axeThree = myApplet.loadImage(baseFolder + "/Loading_Images/Weapons/Axe3.png");
		axeFour = myApplet.loadImage(baseFolder + "/Loading_Images/Weapons/Axe4.png");
		axeFive = myApplet.loadImage(baseFolder + "/Loading_Images/Weapons/Axe5.png");

		scimitarOne = myApplet.loadImage(baseFolder + "/Loading_Images/Weapons/Scimitar1.png");
		scimitarTwo = myApplet.loadImage(baseFolder + "/Loading_Images/Weapons/Scimitar2.png");
		scimitarThree = myApplet.loadImage(baseFolder + "/Loading_Images/Weapons/Scimitar3.png");
		scimitarFour = myApplet.loadImage(baseFolder + "/Loading_Images/Weapons/Scimitar4.png");

		swordImage = swordOne;
		axeImage = axeOne;
		scimitarImage = scimitarOne;

		helmOne = myApplet.loadImage(baseFolder + "/Loading_Images/Armor/Helm1.png");
		helmTwo = myApplet.loadImage(baseFolder + "/Loading_Images/Armor/Helm2.png");
		helmThree = myApplet.loadImage(baseFolder + "/Loading_Images/Armor/Helm3.png");
		helmFour = myApplet.loadImage(baseFolder + "/Loading_Images/Armor/Helm4.png");
		helmFive = myApplet.loadImage(baseFolder + "/Loading_Images/Armor/Helm5.png");

		chestOne = myApplet.loadImage(baseFolder + "/Loading_Images/Armor/Chest1.png");
		chestTwo = myApplet.loadImage(baseFolder + "/Loading_Images/Armor/Chest2.png");
		chestThree = myApplet.loadImage(baseFolder + "/Loading_Images/Armor/Chest3.png");
		chestFour = myApplet.loadImage(baseFolder + "/Loading_Images/Armor/Chest4.png");
		chestFive = myApplet.loadImage(baseFolder + "/Loading_Images/Armor/Chest5.png");

		pantsOne = myApplet.loadImage(baseFolder + "/Loading_Images/Armor/Pants1.png");
		pantsTwo = myApplet.loadImage(baseFolder + "/Loading_Images/Armor/Pants2.png");
		pantsThree = myApplet.loadImage(baseFolder + "/Loading_Images/Armor/Pants3.png");
		pantsFour = myApplet.loadImage(baseFolder + "/Loading_Images/Armor/Pants4.png");
		pantsFive = myApplet.loadImage(baseFolder + "/Loading_Images/Armor/Pants5.png");

		bootsOne = myApplet.loadImage(baseFolder + "/Loading_Images/Armor/Boots1.png");
		bootsTwo = myApplet.loadImage(baseFolder + "/Loading_Images/Armor/Boots2.png");
		bootsThree = myApplet.loadImage(baseFolder + "/Loading_Images/Armor/Boots3.png");
		bootsFour = myApplet.loadImage(baseFolder + "/Loading_Images/Armor/Boots4.png");
		bootsFive = myApplet.loadImage(baseFolder + "/Loading_Images/Armor/Boots5.png");

		helmImage = helmOne;
		chestImage = chestOne;
		pantsImage = pantsOne;
		bootsImage = bootsOne;
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
		oldDudePurchase = new Gif(myApplet, baseFolder + "/Loading_Images/OldDude/oldDudePurchase.gif");
		oldDudeIdleLeft = new Gif(myApplet, baseFolder + "/Loading_Images/OldDude/OldDudeIdleLeft.gif");
		oldDudeIdleRight = new Gif(myApplet, baseFolder + "/Loading_Images/OldDude/OldDudeIdleRight.gif");
		oldDudeImage = oldDudeIdleLeft;

		// Hounds //
		houndLeftIdle = new Gif(myApplet, baseFolder + "/Loading_Images/Hound/HoundIdleLeft.gif");
		houndRightIdle = new Gif(myApplet, baseFolder + "/Loading_Images/Hound/HoundIdleRight.gif");
		houndLeftWalk = new Gif(myApplet, baseFolder + "/Loading_Images/Hound/HoundWalkLeft.gif");
		houndRightWalk = new Gif(myApplet, baseFolder + "/Loading_Images/Hound/HoundWalkRight.gif");
		houndLeftAttack = new Gif(myApplet, baseFolder + "/Loading_Images/Hound/HoundAttackLeft.gif");
		houndRightAttack = new Gif(myApplet, baseFolder + "/Loading_Images/Hound/HoundAttackRight.gif");

		// LanceKnights //
		lanceKnightRightAttack = new Gif(myApplet, baseFolder + "/Loading_Images/LanceKnightAttackRight.gif");
		lanceKnightRightIdle = new Gif(myApplet, baseFolder + "/Loading_Images/lanceKnightIdleRight.gif");
		lanceKnightLeftIdle = new Gif(myApplet, baseFolder + "/Loading_Images/lanceKnightIdleLeft.gif");
		lanceKnightLeftAttack = new Gif(myApplet, baseFolder + "/Loading_Images/lanceKnightAttackLeft.gif");

		lanceKnightLeftAnimation = lanceKnightLeftIdle;
		lanceKnightRightAnimation = lanceKnightRightIdle;

		// Medusas//
		medusaLeftIdle = new Gif(myApplet, baseFolder + "/Loading_Images/Medusa/MedusaIdleLeft.gif");
		medusaRightIdle = new Gif(myApplet, baseFolder + "/Loading_Images/Medusa/MedusaIdleRight.gif");
		medusaLeftAttack = new Gif(myApplet, baseFolder + "/Loading_Images/Medusa/MedusaLeftAttack.gif");
		medusaRightAttack = new Gif(myApplet, baseFolder + "/Loading_Images/Medusa/MedusaAttackRight.gif");
		medusaLeftWalk = new Gif(myApplet, baseFolder + "/Loading_Images/Medusa/MedusaWalkLeft.gif");
		medusaRightWalk = new Gif(myApplet, baseFolder + "/Loading_Images/Medusa/MedusaWalkRight.gif");
		medusaProjectile = new Gif(myApplet, baseFolder + "/Loading_Images/Medusa/MedusaProjectile.gif");

		// Paladins //
		paladinLeftIdle = new Gif(myApplet, baseFolder + "/Loading_Images/PaladinIdleLeft.gif");
		paladinRightIdle = new Gif(myApplet, baseFolder + "/Loading_Images/PaladingIdleRight.gif");
		paladinLeftAttack = new Gif(myApplet, baseFolder + "/Loading_Images/PaladinAttackLeft.gif");
		paladinRightAttack = new Gif(myApplet, baseFolder + "/Loading_Images/PaladinAttackRight.gif");
		paladinLeftWalk = new Gif(myApplet, baseFolder + "/Loading_Images/PaladinWalkLeft.gif");
		paladinRightWalk = new Gif(myApplet, baseFolder + "/Loading_Images/PaladinWalkRight.gif");

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

		// Scorpions //
		scorpionLeftIdle = new Gif(myApplet, baseFolder + "/Loading_Images/Scorpion/ScorpionIdleLeft.gif");
		scorpionRightIdle = new Gif(myApplet, baseFolder + "/Loading_Images/Scorpion/ScorpionIdleRight.gif");
		scorpionLeftAttack = new Gif(myApplet, baseFolder + "/Loading_Images/Scorpion/ScorpionAttackLeft.gif");
		scorpionRightAttack = new Gif(myApplet, baseFolder + "/Loading_Images/Scorpion/ScorpionAttackRight.gif");
		scorpionLeftWalk = new Gif(myApplet, baseFolder + "/Loading_Images/Scorpion/ScorpionWalkLeft.gif");
		scorpionRightWalk = new Gif(myApplet, baseFolder + "/Loading_Images/Scorpion/ScorpionWalkRight.gif");

		// General enemies //
		deathExplosion = new Gif(myApplet, baseFolder + "/Loading_Images/DeathExplosion.gif");
		chestOpen = new Gif(myApplet, baseFolder + "/Loading_Images/ChestOpen.gif");
		chestClosed = new Gif(myApplet, baseFolder + "/Loading_Images/ChestClosed.gif");
		fireBurning = new Gif(myApplet, baseFolder + "/Loading_Images/Burning.gif");

		// Tiles //

		hauntedForestGrassImage = myApplet.loadImage(baseFolder + "/Loading_Images/HauntedForestTile.png");
		hauntedForestWaterImage = myApplet.loadImage(baseFolder + "/Loading_Images/HauntedForestWaterTile.png");
		hauntedForestBushOne = myApplet.loadImage(baseFolder + "/Loading_Images/BushBush1.png");
		hauntedForestBushTwo = myApplet.loadImage(baseFolder + "/Loading_Images/BushBush2.png");
		grassImage = myApplet.loadImage(baseFolder + "/Loading_Images/Grass.png");
		waterImage = myApplet.loadImage(baseFolder + "/Loading_Images/Water.png");
		roadImage = myApplet.loadImage(baseFolder + "/Loading_Images/Road.png");
		treesImage = myApplet.loadImage(baseFolder + "/Loading_Images/Trees.png");
		traderHouse = myApplet.loadImage(baseFolder + "/Loading_Images/Huisje?.png");
		traderHouseGround = myApplet.loadImage(baseFolder + "/Loading_Images/HouseTile.png");
		blackImage = myApplet.loadImage(baseFolder + "/Loading_Images/blackImage.png");
		traderHouseDoor = myApplet.loadImage(baseFolder + "/Loading_Images/Door1.png");

		DesertGround = myApplet.loadImage(baseFolder + "/Loading_Images/DesertGrond.png");
		DesertStoneOne = myApplet.loadImage(baseFolder + "/Loading_Images/DesertStone1.png");
		DesertStoneTwo = myApplet.loadImage(baseFolder + "/Loading_Images/DesertStone2.png");
		DesertStoneThree = myApplet.loadImage(baseFolder + "/Loading_Images/DesertStone3.png");
		
		// City Tiles //
		cityGrass = myApplet.loadImage(baseFolder + "/Loading_Images/City/CityGround.png");
		cityRoadNorth = myApplet.loadImage(baseFolder + "/Loading_Images/City/RoadTileNorth.png");
		cityRoadWest = myApplet.loadImage(baseFolder + "/Loading_Images/City/RoadTileWest.png");
		cityTree = myApplet.loadImage(baseFolder + "/Loading_Images/City/CityTreeLarger.png");
		cityRoadRightTopCorner = myApplet.loadImage(baseFolder + "Loading_Images/City/cityRoadRightTopCorner"); 
		cityRoadRightBotCorner = myApplet.loadImage(baseFolder + "Loading_Images/City/cityRoadRightBotCorner"); 
		cityRoadLeftTopCorner = myApplet.loadImage(baseFolder + "Loading_Images/City/cityRoadLeftTopCorner"); 
		cityRoadLeftBotCorner = myApplet.loadImage(baseFolder + "Loading_Images/City/cityRoadLeftBotCorner"); 
		
		// Elements //
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
		forestMusic = new SoundFile(myApplet, baseFolder + "/Music_Folder/Forest.mp3");
		squareMusic = new SoundFile(myApplet, baseFolder + "/Music_Folder/ProleteR - Faidherbe square.mp3");
		desertMusic = new SoundFile(myApplet, baseFolder + "/Music_Folder/Desert.mp3");
		traderHouseMusic = new SoundFile(myApplet,
				baseFolder + "/Music_Folder/Parasyte - Next To You (Anime Version).mp3");
		hauntedForestMusic = new SoundFile(myApplet, baseFolder + "/Music_Folder/HauntedForest.mp3");
		soundFile = forestMusic;
	}
}
