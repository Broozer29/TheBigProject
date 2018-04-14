package com.bruus.bigproject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.bruus.bigproject.enemies.EnemyHounds;
import com.bruus.bigproject.enemies.EnemyLanceKnights;
import com.bruus.bigproject.enemies.EnemyMedusas;
import com.bruus.bigproject.enemies.EnemyPaladins;
import com.bruus.bigproject.enemies.EnemyScorpions;
import com.bruus.bigproject.enemies.EnemySkeletons;
import com.bruus.bigproject.enemies.RangedProjectiles;
import com.bruus.bigproject.gameobjects.GameObject;
import com.bruus.bigproject.loaders.MapLoader;
import com.bruus.bigproject.loaders.ResourceManager;
import com.bruus.bigproject.util.TileOverlap;
import com.bruus.bigproject.util.TileOverlapSurfaceComparator;
import com.bruus.bigproject.util.TileOverlapTypeComparator;

import processing.core.PApplet;
import processing.core.PFont;

public class TheBigProject extends PApplet {

	private String baseFolder = new File("bin").getAbsolutePath();

	ArrayList<GameObject> objects = new ArrayList<GameObject>();
	public ArrayList<RangedProjectiles> allProjectiles;
	ArrayList<EnemyLanceKnights> allKnights;
	ArrayList<EnemyPaladins> allPaladins;
	ArrayList<EnemySkeletons> allSkeletons;
	ArrayList<EnemyHounds> allHounds;
	ArrayList<EnemyMedusas> allMedusas;
	ArrayList<EnemyScorpions> allScorpions;

	public ResourceManager resourceManager = new ResourceManager();
	int screenWidth;
	int screenHeight;

	// Character stats
	public int characterX;
	public int characterY;
	public float characterXTile;
	public float characterYTile;
	public float characterMaxHealth = 100;
	public float characterHealth = 100;
	public float healthBonus;
	public float characterMoveSpeed = 20;
	public float playerPsionicEssence = 0;
	public int scorpionScales = 500;
	public int medusaHair = 500;
	public int skeletonBones = 500;
	public int paladinArmorScraps = 500;
	public int lanceKnightSpearTips = 500;
	public int houndTeeth = 500;

	// Character combat stats
	public int characterLevel = 0;
	public float characterExp = 0;
	public float lightningDamage = 1.5f;
	public float lightDamageReduction = 50f;
	public float lightCharDamage = 0.75f;
	public float waterEssenceIncrease = 20f;
	public float earthDamage = 33f;
	public float iceSlow = 1f;
	public float baseDamage = 5;
	public float baseBowDamage = 50;
	public float burningDamage = 1;
	public long attackSpeed = 2220;
	public float lifeSteal = 10;
	public float windSpeed;
	public float dropRate = 10;
	float swordDamage = baseDamage;
	float bowDamage = baseBowDamage;
	float levelAmplifier = 1;
	int nextLevelExp = 5000;
	public float swordSize = 50;
	float areaImpact = swordSize;
	public float swordBonusDamage = 0;
	public float axeBonusRange = 0;
	public float axeBonusDamage = 0;
	public float scimitarBonusDamage = 0;
	public int swordLevel = 1;
	public int axeLevel = 1;
	public int scimitarLevel = 1;
	public int helmLevel = 0;
	public int chestLevel = 0;
	public int pantsLevel = 0;
	public int bootsLevel = 0;

	// Map variables
	public int tileSize = 50;
	public float currentLevelNorth;
	public float currentLevelEast;
	public float currentLevelWest;
	public float currentLevelSouth;

	public String currentZone = "Forest";
	private boolean loadedMap;

	// Variables for Traders House and the Old Man
	public float oldManX = 900;
	public float oldManY = 300;
	public float oldManXMiddle = (oldManX + tileSize / 2) / 50;
	public float oldManYMiddle = (oldManY + tileSize * 2) / 50;
	public boolean enteredHouse = false;
	public boolean openShop = false;
	boolean openWeaponShop = false;
	int blackSmithX = 550;
	int blackSmithY = 300;

	// Settings for the chat
	public int extraChatX = 365;
	public int prevChatX = 440;
	public int shopChatX = 600;
	public int nextChatX = 515;
	public int nextChatY = 765;
	public int currentText = 0;
	public String currentTextSetting = "Introduction";
	public boolean changeExtra = false;

	// Variables for the main character
	public int timeForCombo = 500;
	public float timeUntilCombo;
	public String walkingDirection = "left";
	public String prevWalkingDirection = "left";
	public String weaponSelected = "";
	public boolean standingStill = true;
	public boolean swordAttack = false;
	public boolean attackDoubleA = false;
	public boolean bowAttack = false;
	public boolean loadElements = false;
	public boolean initialized = false;
	public boolean action = false;
	boolean leveledUp = true;

	// Variables for the sound
	private boolean playingMusic = false;

	// Variables for enemies
	public long deathExplosionTimer = 0;
	public long attackUntil = 0;
	public long animationWait = 0;
	public long walkUntil = 0;
	public float paladinHealth = 500;
	public float lanceKnightHealth = 300;
	public float skeletonHealth = 300;
	public float scorpionHealth = 350;
	public float medusaHealth = 450;
	public float houndHealth = 600;
	public float houndMoveSpeed = 6;
	public float skeletonMoveSpeed = 4;
	public float medusaMoveSpeed = 4;
	public float psionicEssenceDropKnight = 50;
	public float psionicEssenceDropPaladin = 100;
	public float psionicEssenceDropSkeleton = 50;
	public float psionicEssenceDropScorpion = 75;
	public float psionicEssenceDropMedusa = 150;
	public float psionicEssenceDropHound = 200;

	public boolean dropLoot = false;

	public float lanceKnightExp = 50;
	public float paladingExp = 100;
	public float skeletonExp = 50;
	public float scorpionExp = 75;
	public float medusaExp = 200;
	public float houndExp = 300;

	// Variables for attacks
	public float attackDirection = 0;
	public String arrowDirection;
	public float arrowX = 0;
	public float arrowY = 0;
	public float oldCharacterX, oldCharacterY;
	public boolean arrowToFar = true;
	public boolean ableToAttack = true;
	public boolean swordRangeCombo = false;
	public boolean arrowRangeCombo = false;

	// Variables for text
	public PFont psionicFont;
	public PFont textFont;

	// Variables for images on screen
	public float swordX = 400;
	public float axeX = 800;
	public float scimitarX = 1200;
	public float weaponY = 100;
	public float armorX = 75;
	public float helmY = 150;
	public float chestY = 250;
	public float pantsY = 350;
	public float bootsY = 450;

	public float elementFireX = 195;
	public float elementLightX = 360;
	public float elementWaterX = 515;
	public float elementDarkX = 670;
	public float elementEarthX = 825;
	public float elementLightningX = 980;
	public float elementWindX = 1135;
	public float elementIceX = 1290;

	public float elementY = 100;
	public float elementResetX = 720;
	public float elementResetY = 700;

	public String firstElement;
	public String secondElement;

	public boolean fireUnlocked = true;
	public boolean iceUnlocked = false;
	public boolean darkUnlocked = false;
	public boolean lightUnlocked = false;
	public boolean earthUnlocked = false;
	public boolean windUnlocked = false;
	public boolean waterUnlocked = false;
	public boolean lightningUnlocked = false;

	public boolean fireElement = false;
	public boolean iceElement = false;
	public boolean darkElement = false;
	public boolean lightElement = false;
	public boolean earthElement = false;
	public boolean windElement = false;
	public boolean waterElement = false;
	public boolean lightningElement = false;
	public boolean initializeDamage = false;

	// Variables for item upgrades
	public String selectUpgrade = "";
	public boolean swordUpgradeable = false;
	public boolean axeUpgradeable = false;
	public boolean scimitarUpgradeable = false;
	public boolean helmUpgradeable = false;
	public boolean chestUpgradeable = false;
	public boolean pantsUpgradeable = false;
	public boolean bootsUpgradeable = false;
	public int helmMaterials = 5;
	public int chestMaterials = 5;
	public int pantsMaterials = 5;
	public int bootsMaterials = 5;

	public boolean loadWeapons = false;
	public boolean initializeWeaponDamage = false;
	public boolean loadInventory = false;

	public void settings() {
		size(1440, 900);

	}

	public void setup() {
		imageMode(CENTER);
		allKnights = new ArrayList<>();
		allPaladins = new ArrayList<>();
		allSkeletons = new ArrayList<>();
		allHounds = new ArrayList<>();
		allMedusas = new ArrayList<>();
		allScorpions = new ArrayList<>();
		allProjectiles = new ArrayList<>();
		psionicFont = createFont("Georgia", 20);
		textFont = createFont("Georgia", 12);

		setLoadedMap(false);
		resourceManager.loadImages(this);
		resourceManager.loadMusic(this);
		screenWidth = width;
		screenHeight = height;
		characterX = screenWidth / 2;
		characterY = screenHeight / 2;
		currentLevelNorth = 0;
		currentLevelWest = 0;
	}

	public void draw() {
		background(255);
		for (GameObject o : objects) {
			o.drawTerrain();
		}

		if (animationWait < millis()) {
			resourceManager.currentAnimation.play();
			animationWait = millis() + 500;
		}
		levels();
		characterStats();
		character();
		drawEnemies();
	}

	// ---------------------------------------------------------------------------------------------------\\
	// Music
	void playMusic() {
		if (isPlayingMusic() == false) {
			if (resourceManager.soundFile != null)
				resourceManager.soundFile.stop();
			if (currentZone == "Forest") {
				resourceManager.soundFile = resourceManager.forestMusic;
				resourceManager.soundFile.amp(1f);
				setPlayingMusic(true);
			}

			if (currentZone == "Desert") {
				resourceManager.soundFile = resourceManager.desertMusic;
				resourceManager.soundFile.amp(0.5f);
				setPlayingMusic(true);
			}
			if (currentZone == "TraderHouse") {
				resourceManager.soundFile = resourceManager.traderHouseMusic;
				setPlayingMusic(true);
				resourceManager.soundFile.amp(0.5f);
			}
			if (currentZone == "HauntedForest") {
				resourceManager.soundFile = resourceManager.hauntedForestMusic;
				setPlayingMusic(true);
				resourceManager.soundFile.amp(0.5f);
			}
			if (currentZone == "City") {
				resourceManager.soundFile = resourceManager.cityMusic;
				setPlayingMusic(true);
				resourceManager.soundFile.amp(0.5f);
			}
			resourceManager.soundFile.loop();
		}
	}

	// ---------------------------------------------------------------------------------------------------\\
	// Enemies
	void drawEnemies() {
		if (!swordAttack && !attackDoubleA) {
			swordDamage = baseDamage;
			areaImpact = swordSize;
		}
		if (swordAttack == true) {
			swordDamage = baseDamage;
			areaImpact = swordSize;
		}
		if (attackDoubleA == true) {
			swordDamage = baseDamage * 1.5f;
			areaImpact = swordSize * 3f;
		}

		for (RangedProjectiles temp : allProjectiles) {
			temp.displayProjectiles();
		}

		for (EnemyLanceKnights temp : allKnights) {
			temp.displayKnights();
			temp.bowDamage(bowDamage);
			if (swordAttack == true) {
				temp.swordDamage(attackDirection, areaImpact, swordDamage);
			}
			if (attackDoubleA == true) {
				temp.swordDamage(attackDirection, areaImpact, swordDamage);
			}
		}

		for (EnemyPaladins temp : allPaladins) {
			temp.displayPaladins();
			temp.bowDamage(bowDamage);
			if (swordAttack == true) {
				temp.swordDamage(attackDirection, areaImpact, swordDamage);
			}
			if (attackDoubleA == true) {
				temp.swordDamage(attackDirection, areaImpact, swordDamage);
			}
		}
		for (EnemySkeletons temp : allSkeletons) {
			temp.displayskeletons();
			temp.bowDamage(bowDamage);
			if (swordAttack == true) {
				temp.swordDamage(attackDirection, areaImpact, swordDamage);
			}
			if (attackDoubleA == true) {
				temp.swordDamage(attackDirection, areaImpact, swordDamage);
			}
		}
		for (EnemyHounds temp : allHounds) {
			temp.displayhounds();
			temp.bowDamage(bowDamage);
			if (swordAttack == true) {
				temp.swordDamage(attackDirection, areaImpact, swordDamage);
			}
			if (attackDoubleA == true) {
				temp.swordDamage(attackDirection, areaImpact, swordDamage);
			}
		}

		for (EnemyMedusas temp : allMedusas) {
			temp.displaymedusas();
			temp.bowDamage(bowDamage);
			if (swordAttack == true) {
				temp.swordDamage(attackDirection, areaImpact, swordDamage);
			}
			if (attackDoubleA == true) {
				temp.swordDamage(attackDirection, areaImpact, swordDamage);
			}
		}
		for (EnemyScorpions temp : allScorpions) {
			temp.displayscorpions();
			temp.bowDamage(bowDamage);
			if (swordAttack == true) {
				temp.swordDamage(attackDirection, areaImpact, swordDamage);
			}
			if (attackDoubleA == true) {
				temp.swordDamage(attackDirection, areaImpact, swordDamage);
			}
		}
	}

	void clearEnemies() {
		if (allKnights.size() > 0) {
			allKnights.clear();
		}
		if (allPaladins.size() > 0) {
			allPaladins.clear();
		}

		if (allSkeletons.size() > 0) {
			allSkeletons.clear();
		}
		if (allHounds.size() > 0) {
			allHounds.clear();
		}

		if (allMedusas.size() > 0) {
			allMedusas.clear();
		}
		if (allProjectiles.size() > 0) {
			allProjectiles.clear();
		}
		if (allScorpions.size() > 0) {
			allScorpions.clear();
		}

	}

	// PosX, PosY, Direction, Life, MaxLife, MovementSpeed, maxPsionicEssence,
	// actualPsionicEssence,
	// alive, aggro, Attacking, AttackDone, Debuff, Animation
	// Float, Float, String, Float, Float, Float, Float, Float, Boolean,
	// Boolean, Boolean, Boolean, String, Gif

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	void createFirstKnight(float knightPosX, float knightPosY) {
		EnemyLanceKnights Knight = new EnemyLanceKnights(this, knightPosX, knightPosY, "Right", lanceKnightHealth,
				lanceKnightHealth, 2, psionicEssenceDropKnight, 0, lanceKnightExp, dropLoot, true, false, false, true,
				"", resourceManager.lanceKnightRightIdle);
		allKnights.add(Knight);
	}

	void createSecondKnight(float knightPosX, float knightPosY) {
		EnemyLanceKnights Knight = new EnemyLanceKnights(this, knightPosX, knightPosY, "Right", lanceKnightHealth,
				lanceKnightHealth, 2, psionicEssenceDropKnight, 0, lanceKnightExp, dropLoot, true, false, false, true,
				"", resourceManager.lanceKnightRightIdle);
		allKnights.add(Knight);
	}

	void createThirdKnight(float knightPosX, float knightPosY) {
		EnemyLanceKnights Knight = new EnemyLanceKnights(this, knightPosX, knightPosY, "Right", lanceKnightHealth,
				lanceKnightHealth, 2, psionicEssenceDropKnight, 0, lanceKnightExp, dropLoot, true, false, false, true,
				"", resourceManager.lanceKnightRightIdle);
		allKnights.add(Knight);
	}

	void createFourthKnight(float knightPosX, float knightPosY) {
		EnemyLanceKnights Knight = new EnemyLanceKnights(this, knightPosX, knightPosY, "Right", lanceKnightHealth,
				lanceKnightHealth, 2, psionicEssenceDropKnight, 0, lanceKnightExp, dropLoot, true, false, false, true,
				"", resourceManager.lanceKnightRightIdle);
		allKnights.add(Knight);
	}

	void createFirstPaladin(float paladinPosX, float paladinPosY) {
		EnemyPaladins Paladin = new EnemyPaladins(this, paladinPosX, paladinPosY, "Right", paladinHealth, paladinHealth,
				5, psionicEssenceDropPaladin, 0, paladingExp, dropLoot, true, false, false, true, "",
				resourceManager.paladinRightIdle);
		allPaladins.add(Paladin);
	}

	void createSecondPaladin(float paladinPosX, float paladinPosY) {
		EnemyPaladins Paladin = new EnemyPaladins(this, paladinPosX, paladinPosY, "Right", paladinHealth, paladinHealth,
				5, psionicEssenceDropPaladin, 0, paladingExp, dropLoot, true, false, false, true, "",
				resourceManager.paladinRightIdle);
		allPaladins.add(Paladin);
	}

	void createThirdPaladin(float paladinPosX, float paladinPosY) {
		EnemyPaladins Paladin = new EnemyPaladins(this, paladinPosX, paladinPosY, "Right", paladinHealth, paladinHealth,
				5, psionicEssenceDropPaladin, 0, paladingExp, dropLoot, true, false, false, true, "",
				resourceManager.paladinRightIdle);
		allPaladins.add(Paladin);
	}

	void createFourthPaladin(float paladinPosX, float paladinPosY) {
		EnemyPaladins Paladin = new EnemyPaladins(this, paladinPosX, paladinPosY, "Right", paladinHealth, paladinHealth,
				5, psionicEssenceDropPaladin, 0, paladingExp, dropLoot, true, false, false, true, "",
				resourceManager.paladinRightIdle);
		allPaladins.add(Paladin);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	void createFirstSkeleton(float skeletonX, float skeletonY) {
		EnemySkeletons Skeleton = new EnemySkeletons(this, skeletonX, skeletonY, "Right", skeletonHealth,
				skeletonHealth, skeletonMoveSpeed, psionicEssenceDropSkeleton, 0, skeletonExp, dropLoot, true, false,
				false, false, "", resourceManager.skeletonRightIdle);
		allSkeletons.add(Skeleton);
	}

	void createSecondSkeleton(float skeletonX, float skeletonY) {
		EnemySkeletons Skeleton = new EnemySkeletons(this, skeletonX, skeletonY, "Right", skeletonHealth,
				skeletonHealth, skeletonMoveSpeed, psionicEssenceDropSkeleton, 0, skeletonExp, dropLoot, true, false,
				false, false, "", resourceManager.skeletonRightIdle);
		allSkeletons.add(Skeleton);
	}

	void createThirdSkeleton(float skeletonX, float skeletonY) {
		EnemySkeletons Skeleton = new EnemySkeletons(this, skeletonX, skeletonY, "Right", skeletonHealth,
				skeletonHealth, skeletonMoveSpeed, psionicEssenceDropSkeleton, 0, skeletonExp, dropLoot, true, false,
				false, false, "", resourceManager.skeletonRightIdle);
		allSkeletons.add(Skeleton);
	}

	void createFourthSkeleton(float skeletonX, float skeletonY) {
		EnemySkeletons Skeleton = new EnemySkeletons(this, skeletonX, skeletonY, "Right", skeletonHealth,
				skeletonHealth, skeletonMoveSpeed, psionicEssenceDropSkeleton, 0, skeletonExp, dropLoot, true, false,
				false, false, "", resourceManager.skeletonRightIdle);
		allSkeletons.add(Skeleton);
	}

	void createFirstScorpion(float scorpionPosX, float scorpionPosY) {
		EnemyScorpions scorpion = new EnemyScorpions(this, scorpionPosX, scorpionPosY, "Right", scorpionHealth,
				scorpionHealth, 2, psionicEssenceDropScorpion, 0, scorpionExp, dropLoot, true, false, false, true, "",
				resourceManager.scorpionRightIdle);
		allScorpions.add(scorpion);
	}

	void createSecondScorpion(float scorpionPosX, float scorpionPosY) {
		EnemyScorpions scorpion = new EnemyScorpions(this, scorpionPosX, scorpionPosY, "Right", scorpionHealth,
				scorpionHealth, 2, psionicEssenceDropScorpion, 0, scorpionExp, dropLoot, true, false, false, true, "",
				resourceManager.scorpionRightIdle);
		allScorpions.add(scorpion);
	}

	void createThirdScorpion(float scorpionPosX, float scorpionPosY) {
		EnemyScorpions scorpion = new EnemyScorpions(this, scorpionPosX, scorpionPosY, "Right", scorpionHealth,
				scorpionHealth, 2, psionicEssenceDropScorpion, 0, scorpionExp, dropLoot, true, false, false, true, "",
				resourceManager.scorpionRightIdle);
		allScorpions.add(scorpion);
	}

	void createFourthScorpion(float scorpionPosX, float scorpionPosY) {
		EnemyScorpions scorpion = new EnemyScorpions(this, scorpionPosX, scorpionPosY, "Right", scorpionHealth,
				scorpionHealth, 2, psionicEssenceDropScorpion, 0, scorpionExp, dropLoot, true, false, false, true, "",
				resourceManager.scorpionRightIdle);
		allScorpions.add(scorpion);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	void createFirstMedusa(float medusaX, float medusaY) {
		EnemyMedusas medusa = new EnemyMedusas(this, medusaX, medusaY, "Right", medusaHealth, medusaHealth,
				medusaMoveSpeed, psionicEssenceDropMedusa, 0, medusaExp, dropLoot, true, false, false, false, "",
				resourceManager.medusaRightIdle);
		allMedusas.add(medusa);
	}

	void createSecondMedusa(float medusaX, float medusaY) {
		EnemyMedusas medusa = new EnemyMedusas(this, medusaX, medusaY, "Right", medusaHealth, medusaHealth,
				medusaMoveSpeed, psionicEssenceDropMedusa, 0, medusaExp, dropLoot, true, false, false, false, "",
				resourceManager.medusaRightIdle);
		allMedusas.add(medusa);
	}

	void createThirdMedusa(float medusaX, float medusaY) {
		EnemyMedusas medusa = new EnemyMedusas(this, medusaX, medusaY, "Right", medusaHealth, medusaHealth,
				medusaMoveSpeed, psionicEssenceDropMedusa, 0, medusaExp, dropLoot, true, false, false, false, "",
				resourceManager.medusaRightIdle);
		allMedusas.add(medusa);
	}

	void createFourthMedusa(float medusaX, float medusaY) {
		EnemyMedusas medusa = new EnemyMedusas(this, medusaX, medusaY, "Right", medusaHealth, medusaHealth,
				medusaMoveSpeed, psionicEssenceDropMedusa, 0, medusaExp, dropLoot, true, false, false, false, "",
				resourceManager.medusaRightIdle);
		allMedusas.add(medusa);
	}

	void createFirstHound(float houndX, float houndY) {
		EnemyHounds Hound = new EnemyHounds(this, houndX, houndY, "Right", houndHealth, houndHealth, houndMoveSpeed,
				psionicEssenceDropHound, 0, houndExp, dropLoot, true, false, false, true, "",
				resourceManager.houndLeftIdle);
		allHounds.add(Hound);
	}

	void createSecondHound(float houndX, float houndY) {
		EnemyHounds Hound = new EnemyHounds(this, houndX, houndY, "Right", houndHealth, houndHealth, houndMoveSpeed,
				psionicEssenceDropHound, 0, houndExp, dropLoot, true, false, false, true, "",
				resourceManager.houndLeftIdle);
		allHounds.add(Hound);
	}

	void createThirdHound(float houndX, float houndY) {
		EnemyHounds Hound = new EnemyHounds(this, houndX, houndY, "Right", houndHealth, houndHealth, houndMoveSpeed,
				psionicEssenceDropHound, 0, houndExp, dropLoot, true, false, false, true, "",
				resourceManager.houndLeftIdle);
		allHounds.add(Hound);
	}

	void createFourthHound(float houndX, float houndY) {
		EnemyHounds Hound = new EnemyHounds(this, houndX, houndY, "Right", houndHealth, houndHealth, houndMoveSpeed,
				psionicEssenceDropHound, 0, houndExp, dropLoot, true, false, false, true, "",
				resourceManager.houndLeftIdle);
		allHounds.add(Hound);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void healthBar(float posX, float posY, float knightLife, float knightMaxLife) {
		rectMode(CORNER);
		float factorCoord = tileSize / 2;
		float factor = knightMaxLife / 40;
		fill(255, 0, 0);
		rect(posX - factorCoord, posY - factorCoord, 40, 5);
		fill(0, 255, 0);
		rect(posX - factorCoord, posY - factorCoord, knightLife / factor, 5);
	}

	public void itemsGained(float psionicEssence, String item, float posX, float posY, int timeOfDeath) {
		float factorX = 30;
		float factorY = 40;
		float factorY3 = factorY * 1.5f;
		float factorX3 = factorX * 3f;

		if (millis() < timeOfDeath + 4000) {
			fill(204, 0, 204);
			text("Psionic Essence +" + psionicEssence, posX - factorX3 - 50, posY - factorY);
			if (item == "Lance Knight Spear tip") {
				text(item + "+1", posX - factorX3 - 50, posY - factorY3);
			}
			if (item == "Medusa Hair") {
				text(item + "+1", posX - factorX3 - 50, posY - factorY3);
			}
			if (item == "Skeleton Bone") {
				text(item + "+1", posX - factorX3 - 50, posY - factorY3);
			}
			if (item == "Paladin Armor Scraps") {
				text(item + "+1", posX - factorX3 - 50, posY - factorY3);
			}
			if (item == "Scorpion Scale") {
				text(item + "+1", posX - factorX3 - 50, posY - factorY3);
			}
			if (item == "Hound Teeth") {
				text(item + "+1", posX - factorX3 - 50, posY - factorY3);
			}
		}

	}

	// ---------------------------------------------------------------------------------------------------\\
	// Character \\

	void character() {
		lookingDirection();
		walking();
		swordAttack();
		bowAttack();
		characterHealth();
		characterExperience();
		resourceManager.fireBurning.play();
		image(resourceManager.currentAnimation, characterX, characterY);
	}

	void characterExperience() {
		if (leveledUp == true) {
			nextLevelExp = nextLevelExp + (characterLevel * 500);
			baseDamage = baseDamage * levelAmplifier;
			baseBowDamage = baseBowDamage * levelAmplifier;
			characterExp = 0;
			characterLevel++;
			leveledUp = false;
		}
		if (characterExp >= nextLevelExp) {
			leveledUp = true;
			levelAmplifier += 0.15;
		}
		rectMode(CORNER);
		fill(255, 255, 255);
		rect(20, 40, 400, 10);
		fill(204, 0, 204);
		float factor = (characterExp / nextLevelExp);
		rect(20, 40, factor * 400, 10);

	}

	void characterHealth() {
		rectMode(CORNER);
		int decayingHealth = 1;
		fill(255, 0, 0);
		rect(20, 20, 400, 10);
		fill(0, 255, 0);
		float factor = characterMaxHealth / 40;
		rect(20, 20, (characterHealth / factor) * 10, 10);
		textFont(psionicFont);

		fill(0, 255, 255);
		text("Level: ", 20, 80);
		text(characterLevel, 200, 80);
		text("Psionic Essence: ", 20, 100);
		text(playerPsionicEssence, 200, 100);

		if (characterHealth > characterMaxHealth) {
			characterHealth -= decayingHealth;
		}
		if (characterHealth <= characterMaxHealth) {
			decayingHealth = 1;
		}
	}

	void walking() {
		characterXTile = characterX / 50;
		characterYTile = characterY / 50;
		if (standingStill == false && swordAttack == false && bowAttack == false && swordRangeCombo == false) {
			if (walkingDirection == "left" && resourceManager.currentAnimation != resourceManager.walkingLeft) {
				resourceManager.currentAnimation.stop();
				resourceManager.currentAnimation = resourceManager.walkingLeft;
			}
			if (walkingDirection == "right" && resourceManager.currentAnimation != resourceManager.walkingRight) {
				resourceManager.currentAnimation.stop();
				resourceManager.currentAnimation = resourceManager.walkingRight;
			}
			if (walkingDirection == "up" || walkingDirection == "down") {
				if (prevWalkingDirection == "left" && resourceManager.currentAnimation != resourceManager.walkingLeft) {
					resourceManager.currentAnimation.stop();
					resourceManager.currentAnimation = resourceManager.walkingLeft;
				}
				if (prevWalkingDirection == "right"
						&& resourceManager.currentAnimation != resourceManager.walkingRight) {
					resourceManager.currentAnimation.stop();
					resourceManager.currentAnimation = resourceManager.walkingRight;
				}
			}
		}
		if (walkUntil < millis() && swordAttack == false) {
			standingStill = true;
		}
	}

	void lookingDirection() {
		if (standingStill == true && swordAttack == false && bowAttack == false && swordRangeCombo == false) {
			if (walkingDirection == "up" || walkingDirection == "down") {
				if (prevWalkingDirection == "left" && resourceManager.currentAnimation != resourceManager.lookingLeft) {
					resourceManager.currentAnimation.stop();
					resourceManager.currentAnimation = resourceManager.lookingLeft;
				}
				if (prevWalkingDirection == "right"
						&& resourceManager.currentAnimation != resourceManager.lookingRight) {
					resourceManager.currentAnimation.stop();
					resourceManager.currentAnimation = resourceManager.lookingRight;
				}
			}
			if (walkingDirection == "left" && resourceManager.currentAnimation != resourceManager.lookingLeft) {
				resourceManager.currentAnimation.stop();
				resourceManager.currentAnimation = resourceManager.lookingLeft;
			}
			if (walkingDirection == "right" && resourceManager.currentAnimation != resourceManager.lookingRight) {
				resourceManager.currentAnimation.stop();
				resourceManager.currentAnimation = resourceManager.lookingRight;
			}
		}
	}

	void swordAttackLeft(int attackDirectionLeft) {
		if (attackDoubleA == true && resourceManager.currentAnimation != resourceManager.saberDoubleALeft) {
			resourceManager.currentAnimation.stop();
			resourceManager.currentAnimation = resourceManager.saberDoubleALeft;
		} else if (resourceManager.currentAnimation != resourceManager.attackLeft && attackDoubleA == false
				&& swordRangeCombo == false) {
			resourceManager.currentAnimation.stop();
			resourceManager.currentAnimation = resourceManager.attackLeft;
		}
		attackDirection = characterX - attackDirectionLeft;
	}

	void swordAttackRight(int attackDirectionRight) {
		if (attackDoubleA == true && resourceManager.currentAnimation != resourceManager.saberDoubleARight) {
			resourceManager.currentAnimation.stop();
			resourceManager.currentAnimation = resourceManager.saberDoubleARight;
		} else if (resourceManager.currentAnimation != resourceManager.attackRight && attackDoubleA == false
				&& swordRangeCombo == false) {
			resourceManager.currentAnimation.stop();
			resourceManager.currentAnimation = resourceManager.attackRight;
		}
		attackDirection = characterX + attackDirectionRight;
	}

	void swordAttack() {
		int attackDirectionLeft = 20;
		int attackDirectionRight = 30;
		if (swordAttack == true) {
			if (walkingDirection == "left") {
				swordAttackLeft(attackDirectionLeft);
			}
			if (walkingDirection == "right") {
				swordAttackRight(attackDirectionRight);
			}
			if (walkingDirection == "up" || walkingDirection == "down") {
				if (prevWalkingDirection == "left") {
					swordAttackLeft(attackDirectionLeft);
				}
				if (prevWalkingDirection == "right") {
					swordAttackRight(attackDirectionRight);
				}
			}

		}
		keepCounting();
	}

	void bowAttack() {
		int bowAttackDirectionLeft = 60;
		int bowAttackDirectionRight = 40;
		if (arrowRangeCombo == true && millis() > attackUntil - 1000) {
			arrowToFar = false;
		}

		if (arrowDirection == "right" && arrowToFar == false) {
			resourceManager.projectileSaber = resourceManager.projectileRight;
			arrowX += 10 + windSpeed;
		}
		if (arrowDirection == "left" && arrowToFar == false) {
			arrowX -= 10 + windSpeed;
			resourceManager.projectileSaber = resourceManager.projectileLeft;
		}

		if (bowAttack == true || swordRangeCombo == true) {
			arrowY = characterY;
			if (walkingDirection == "left" && resourceManager.currentAnimation != resourceManager.attackBowLeft
					&& resourceManager.currentAnimation != resourceManager.saberMeleeRangedLeft) {
				bowAttackLeft(bowAttackDirectionLeft);
			}
			if (walkingDirection == "right" && resourceManager.currentAnimation != resourceManager.attackBowRight
					&& resourceManager.currentAnimation != resourceManager.saberMeleeRangedRight) {
				bowAttackRight(bowAttackDirectionRight);
			}
			if (walkingDirection == "up" || walkingDirection == "down") {
				if (prevWalkingDirection == "left"
						&& resourceManager.currentAnimation != resourceManager.attackBowLeft) {
					bowAttackLeft(bowAttackDirectionLeft);
				}
				if (prevWalkingDirection == "right"
						&& resourceManager.currentAnimation != resourceManager.attackBowRight) {
					bowAttackRight(bowAttackDirectionRight);
				}
			}
		}
		if (arrowX < oldCharacterX - 500 || arrowX > oldCharacterX + 500) {
			arrowToFar = true;
		}
		if (arrowToFar == false) {
			image(resourceManager.projectileSaber, arrowX, arrowY);
		}
		resourceManager.projectileSaber.play();
	}

	void bowAttackRight(int bowAttackDirectionRight) {
		if (resourceManager.currentAnimation != resourceManager.attackBowRight
				&& resourceManager.currentAnimation != resourceManager.saberMeleeRangedRight && bowAttack == false
				&& swordRangeCombo == false) {
			resourceManager.currentAnimation.stop();

		}
		if (bowAttack == true && swordRangeCombo == false) {
			resourceManager.currentAnimation = resourceManager.attackBowRight;
			bowDamage = baseBowDamage;
		}
		if (bowAttack == false && swordRangeCombo == true
				&& resourceManager.currentAnimation != resourceManager.saberMeleeRangedRight) {
			resourceManager.currentAnimation = resourceManager.saberMeleeRangedRight;
			bowDamage = baseBowDamage * 0.5f;
		}
		arrowDirection = "right";
		arrowX = characterX + bowAttackDirectionRight;
	}

	void bowAttackLeft(int bowAttackDirectionLeft) {
		if (resourceManager.currentAnimation != resourceManager.attackBowRight
				&& resourceManager.currentAnimation != resourceManager.saberMeleeRangedLeft && bowAttack == false
				&& swordRangeCombo == false) {
			resourceManager.currentAnimation.stop();

		}
		if (bowAttack == true && swordRangeCombo == false) {
			resourceManager.currentAnimation = resourceManager.attackBowLeft;
			bowDamage = baseBowDamage;

		}
		if (bowAttack == false && swordRangeCombo == true
				&& resourceManager.currentAnimation != resourceManager.saberMeleeRangedLeft) {
			resourceManager.currentAnimation = resourceManager.saberMeleeRangedLeft;
			bowDamage = baseBowDamage * 0.5f;
		}
		arrowDirection = "left";
		arrowX = characterX - bowAttackDirectionLeft;
	}

	void keepCounting() {
		if (attackUntil < millis()) {
			swordAttack = false;
			bowAttack = false;
			ableToAttack = true;
			attackDoubleA = false;
			swordRangeCombo = false;
			arrowRangeCombo = false;
		}
	}

	// ---------------------------------------------------------------------------------------------------\\
	// Levels

	void levels() {
		leavingZone();
		// StartingZone
		if (currentLevelNorth == 0 && currentLevelWest == 0 && currentZone == "Forest") {
			starterZone();
			playMusic();
		}
		// Forest
		if (currentLevelNorth == 0 && currentLevelWest == 1 && currentZone == "Forest") {
			forestZoneOne();
		}
		if (currentLevelNorth == 0 && currentLevelWest == 2 && currentZone == "Forest") {
			forestZoneZeroTwo();
		}
		if (currentLevelNorth == 0 && currentLevelWest == 3 && currentZone == "Forest") {
			forestZoneZeroThree();
		}
		if (currentLevelNorth == 0 && currentLevelWest == 4 && currentZone == "Forest") {
			forestZoneZeroFour();
		}
		if (currentLevelNorth == -1 && currentLevelWest == 1 && currentZone == "Forest") {
			forestZoneMinusOneOne();
		}
		if (currentLevelNorth == -1 && currentLevelWest == 2 && currentZone == "Forest") {
			forestZoneMinusOneTwo();
		}
		if (currentLevelNorth == -1 && currentLevelWest == 0 && currentZone == "Forest") {
			forestZoneMinusOneZero();
		}
		if (currentLevelNorth == -2 && currentLevelWest == 1 && currentZone == "Forest") {
			forestZoneMinusTwoOne();
		}
		if (currentLevelNorth == -2 && currentLevelWest == 2 && currentZone == "Forest") {
			forestZoneMinusTwoTwo();
		}
		if (currentLevelNorth == -3 && currentLevelWest == 1 && currentZone == "Forest") {
			forestZoneMinusThreeOne();
		}
		if (currentLevelNorth == -3 && currentLevelWest == 2 && currentZone == "Forest") {
			forestZoneMinusThreeTwo();
		}
		if (currentLevelNorth == -4 && currentLevelWest == 1 && currentZone == "Forest") {
			forestZoneMinusFourOne();
			playMusic();
		}

		if (currentLevelNorth == -5 && currentLevelWest == 1 && currentZone == "City") {
			cityZoneMinusFiveOne();
			playMusic();
		}
		if (currentLevelNorth == -5 && currentLevelWest == 2 && currentZone == "City") {
			cityZoneMinusFiveTwo();
		}
		if (currentLevelNorth == -5 && currentLevelWest == 3 && currentZone == "City") {
			cityZoneMinusFiveThree();
		}

		// Desert
		if (currentLevelNorth == 1 && currentLevelWest == 0 && currentZone == "Desert") {
			desertZoneOne();
			playMusic();
		}
		if (currentLevelNorth == 1 && currentLevelWest == 1 && currentZone == "Desert") {
			desertZoneOneOne();
		}
		if (currentLevelNorth == 1 && currentLevelWest == 2 && currentZone == "Desert") {
			desertZoneOneTwo();
		}
		if (currentLevelNorth == 1 && currentLevelWest == 3 && currentZone == "Desert") {
			desertZoneOneThree();
		}
		if (currentLevelNorth == 1 && currentLevelWest == 4 && currentZone == "Desert") {
			desertZoneOneFour();
		}
		if (currentLevelNorth == 2 && currentLevelWest == 0 && currentZone == "Desert") {
			desertZoneTwoZero();
			playMusic();
		}
		if (currentLevelNorth == 2 && currentLevelWest == 1 && currentZone == "Desert") {
			desertZoneTwoOne();
		}
		if (currentLevelNorth == 2 && currentLevelWest == 2 && currentZone == "Desert") {
			desertZoneTwoTwo();
		}
		if (currentLevelNorth == 2 && currentLevelWest == 3 && currentZone == "Desert") {
			desertZoneTwoThree();
		}
		if (currentLevelNorth == 2 && currentLevelWest == 4 && currentZone == "Desert") {
			desertZoneTwoFour();
		}

		// Haunted Forest
		if (currentLevelNorth == 0 && currentLevelWest == -1 && currentZone == "HauntedForest") {
			hauntedForestZoneOne();
			playMusic();
		}
		if (currentLevelNorth == 0 && currentLevelWest == -2 && currentZone == "HauntedForest") {
			hauntedZoneZeroMinusTwo();
		}
		if (currentLevelNorth == 0 && currentLevelWest == -3 && currentZone == "HauntedForest") {
			hauntedZoneZeroMinusThree();
		}
		if (currentLevelNorth == 0 && currentLevelWest == -4 && currentZone == "HauntedForest") {
			hauntedZoneZeroMinusFour();
		}
		if (currentLevelNorth == 1 && currentLevelWest == -1 && currentZone == "HauntedForest") {
			hauntedZoneOneMinusOne();
			playMusic();

		}
		if (currentLevelNorth == 1 && currentLevelWest == -2 && currentZone == "HauntedForest") {
			hauntedZoneOneMinusTwo();
		}
		if (currentLevelNorth == 1 && currentLevelWest == -3 && currentZone == "HauntedForest") {
			hauntedZoneOneMinusThree();
		}
		if (currentLevelNorth == 1 && currentLevelWest == -4 && currentZone == "HauntedForest") {
			hauntedZoneOneMinusFour();
		}
		if (currentLevelNorth == 2 && currentLevelWest == -1 && currentZone == "HauntedForest") {
			hauntedZoneTwoMinusOne();
			playMusic();
		}
		if (currentLevelNorth == 2 && currentLevelWest == -2 && currentZone == "HauntedForest") {
			hauntedZoneTwoMinusTwo();
		}
		if (currentLevelNorth == 2 && currentLevelWest == -3 && currentZone == "HauntedForest") {
			hauntedZoneTwoMinusThree();
		}
		if (currentLevelNorth == 2 && currentLevelWest == -4 && currentZone == "HauntedForest") {
			hauntedZoneTwoMinusFour();
		}

		if (currentLevelNorth == 0 && currentLevelWest == 0 && currentZone == "TraderHouse") {
			traderHouse();
			playMusic();
		}
	}

	void leavingZone() {
		if (characterX < 0) {
			if (currentLevelNorth == 0 && currentLevelWest == -1 && currentZone == "HauntedForest") {
				resourceManager.soundFile.stop();
				setPlayingMusic(false);
				currentZone = "Forest";
			}
			if (currentLevelNorth == 1 && currentLevelWest == -1 && currentZone == "HauntedForest") {
				resourceManager.soundFile.stop();
				setPlayingMusic(false);
				currentZone = "Desert";
			}
			if (currentLevelNorth == 2 && currentLevelWest == -1 && currentZone == "HauntedForest") {
				resourceManager.soundFile.stop();
				setPlayingMusic(false);
				currentZone = "Desert";
			}
			currentLevelWest++;
			characterX = screenWidth;
			clearEnemies();
			setLoadedMap(false);
		}

		if (characterX > screenWidth) {
			if (currentLevelNorth == 0 && currentLevelWest == 0 && currentZone == "Forest") {
				resourceManager.soundFile.stop();
				setPlayingMusic(false);
				currentZone = "HauntedForest";
			}
			if (currentLevelNorth == 1 && currentLevelWest == 0 && currentZone == "Desert") {
				resourceManager.soundFile.stop();
				setPlayingMusic(false);
				currentZone = "HauntedForest";
			}
			if (currentLevelNorth == 2 && currentLevelWest == 0 && currentZone == "Desert") {
				resourceManager.soundFile.stop();
				setPlayingMusic(false);
				currentZone = "HauntedForest";
			}
			currentLevelWest--;
			characterX = 0;
			clearEnemies();
			setLoadedMap(false);
		}

		if (characterY < 0) {
			if (currentLevelNorth == 0 && currentLevelWest == 0 && currentZone == "Forest") {
				resourceManager.soundFile.stop();
				setPlayingMusic(false);
				currentZone = "Desert";
			}
			if (currentLevelNorth == -5 && currentLevelWest == 1 && currentZone == "City") {
				resourceManager.soundFile.stop();
				setPlayingMusic(false);
				currentZone = "Forest";
			}
			currentLevelNorth++;
			characterY = screenHeight;
			clearEnemies();
			setLoadedMap(false);
		}

		if (characterY > screenHeight) {
			if (currentLevelNorth == 1 && currentLevelWest == 0 && currentZone == "Desert") {
				resourceManager.soundFile.stop();
				setPlayingMusic(false);
				currentZone = "Forest";
			}
			if (currentLevelNorth == -4 && currentLevelWest == 1 && currentZone == "Forest") {
				resourceManager.soundFile.stop();
				setPlayingMusic(false);
				currentZone = "City";
			}
			currentLevelNorth--;
			characterY = 0;
			clearEnemies();
			setLoadedMap(false);
		}
		if (enteredHouse == true) {
			currentZone = "TraderHouse";
			clearEnemies();
			setLoadedMap(false);
			enteredHouse = false;
		}
	}

	void starterZone() {
		int locationTradeHouseX = 630;
		int locationTradeHouseY = 130;
		if (!isLoadedMap()) {
			MapLoader loader = new MapLoader(this);
			try {
				objects = loader.loadTiles(baseFolder + "/ForestZones/startingZone.txt");
			} catch (IOException e) {
				println("Sorry, kon map niet laden: " + e.getMessage());
			}
			setLoadedMap(true);
		}
		if (characterX / 50 == 12 && characterY / 50 == 6 && action == true) {
			enteredHouse = true;
		}

		image(resourceManager.traderHouse, locationTradeHouseX, locationTradeHouseY, 300, 300);
	}

	// City
	void cityZoneMinusFiveOne() {
		if (!isLoadedMap()) {
			MapLoader loader = new MapLoader(this);
			try {
				objects = loader.loadTiles(baseFolder + "/CityZones/CityMinusFiveOne.txt");
			} catch (IOException e) {
				println("Sorry, kon map niet laden: " + e.getMessage());
			}
			setLoadedMap(true);
		}
		image(resourceManager.cityHouseLarge, 275, 150);
		image(resourceManager.cityHouseOne, 925, 125);
		image(resourceManager.cityHouseOne, 1125, 125);
		image(resourceManager.cityHouseOne, 1325 , 125);
		image(resourceManager.cityHouseTwo, 925, 325);
		image(resourceManager.cityHouseTwo, 1125, 325);
		image(resourceManager.cityHouseTwo, 1325, 325);
	}

	void cityZoneMinusFiveTwo() {
		if (!isLoadedMap()) {
			MapLoader loader = new MapLoader(this);
			try {
				objects = loader.loadTiles(baseFolder + "/CityZones/CityMinusFiveTwo.txt");
			} catch (IOException e) {
				println("Sorry, kon map niet laden: " + e.getMessage());
			}
			setLoadedMap(true);
		}
		image(resourceManager.cityShop, 675, 625);
		image(resourceManager.cityHouseThree, 275, 125);
		image(resourceManager.cityHouseThree, 425, 125);
		image(resourceManager.cityHouseThree, 575, 125);
		image(resourceManager.cityHouseThree, 725, 125);
		image(resourceManager.cityHouseThree, 875, 125);
		image(resourceManager.cityHouseThree, 1025, 125);
		image(resourceManager.cityHouseThree, 1175, 125);
	}

	void cityZoneMinusFiveThree() {
		if (!isLoadedMap()) {
			MapLoader loader = new MapLoader(this);
			try {
				objects = loader.loadTiles(baseFolder + "/CityZones/CityMinusFiveThree.txt");
			} catch (IOException e) {
				println("Sorry, kon map niet laden: " + e.getMessage());
			}
			setLoadedMap(true);
		}
		image(resourceManager.cityHouseOne, 625, 175);
		image(resourceManager.cityHouseTwo, 825, 175);
		image(resourceManager.cityHouseLarge, 325, 450);
	}

	// Forest

	void forestZoneOne() {
		if (!isLoadedMap()) {
			MapLoader loader = new MapLoader(this);
			try {
				objects = loader.loadTiles(baseFolder + "/ForestZones/ForestZeroOne.txt");
			} catch (IOException e) {
				println("Sorry, kon map niet laden: " + e.getMessage());
			}
			createFirstKnight(900, 150);
			createSecondKnight(600, 300);
			createFirstPaladin(500, 700);
			setLoadedMap(true);
		}
	}

	void forestZoneZeroTwo() {
		if (!isLoadedMap()) {
			MapLoader loader = new MapLoader(this);
			try {
				objects = loader.loadTiles(baseFolder + "/ForestZones/ForestZeroTwo.txt");
			} catch (IOException e) {
				println("Sorry, kon map niet laden: " + e.getMessage());
			}
			createFirstKnight(500, 150);
			createSecondKnight(950, 700);
			createFirstPaladin(1050, 150);
			setLoadedMap(true);
		}
	}

	void forestZoneZeroThree() {
		if (!isLoadedMap()) {
			MapLoader loader = new MapLoader(this);
			try {
				objects = loader.loadTiles(baseFolder + "/ForestZones/ForestZeroThree.txt");
			} catch (IOException e) {
				println("Sorry, kon map niet laden: " + e.getMessage());
			}
			createFirstKnight(700, 200);
			createSecondKnight(250, 350);
			createThirdKnight(300, 200);
			createFirstPaladin(700, 450);
			setLoadedMap(true);
		}
	}

	void forestZoneZeroFour() {
		if (!isLoadedMap()) {
			MapLoader loader = new MapLoader(this);
			try {
				objects = loader.loadTiles(baseFolder + "/ForestZones/ForestZeroFour.txt");
			} catch (IOException e) {
				println("Sorry, kon map niet laden: " + e.getMessage());
			}
			createFirstPaladin(300, 400);
			createSecondPaladin(1000, 200);
			createThirdPaladin(500, 200);
			createFourthPaladin(1200, 550);
			setLoadedMap(true);
		}
	}

	void forestZoneMinusOneOne() {
		if (!isLoadedMap()) {
			MapLoader loader = new MapLoader(this);
			try {
				objects = loader.loadTiles(baseFolder + "/ForestZones/ForestMinusOneOne.txt");
			} catch (IOException e) {
				println("Sorry, kon map niet laden: " + e.getMessage());
			}
			createFirstKnight(400, 300);
			createSecondKnight(250, 650);
			createThirdKnight(700, 750);
			createFourthKnight(1200, 533);
			setLoadedMap(true);
		}
	}

	void forestZoneMinusOneTwo() {
		if (!isLoadedMap()) {
			MapLoader loader = new MapLoader(this);
			try {
				objects = loader.loadTiles(baseFolder + "/ForestZones/ForestMinusOneTwo.txt");
			} catch (IOException e) {
				println("Sorry, kon map niet laden: " + e.getMessage());
			}
			createFirstKnight(350, 350);
			createSecondKnight(700, 200);
			createFirstPaladin(650, 700);
			setLoadedMap(true);
		}
	}

	void forestZoneMinusOneZero() {
		if (!isLoadedMap()) {
			MapLoader loader = new MapLoader(this);
			try {
				objects = loader.loadTiles(baseFolder + "/ForestZones/ForestMinusOneZero.txt");
			} catch (IOException e) {
				println("Sorry, kon map niet laden: " + e.getMessage());
			}
			createFirstKnight(900, 300);
			createSecondKnight(300, 150);
			createThirdKnight(950, 700);
			createFirstPaladin(850, 500);
			setLoadedMap(true);
		}
	}

	void forestZoneMinusTwoOne() {
		if (!isLoadedMap()) {
			MapLoader loader = new MapLoader(this);
			try {
				objects = loader.loadTiles(baseFolder + "/ForestZones/ForestMinusTwoOne.txt");
			} catch (IOException e) {
				println("Sorry, kon map niet laden: " + e.getMessage());
			}
			createFirstKnight(350, 250);
			createSecondKnight(1200, 320);
			createThirdKnight(100, 750);
			createFirstPaladin(1050, 700);
			setLoadedMap(true);
		}
	}

	void forestZoneMinusTwoTwo() {
		if (!isLoadedMap()) {
			MapLoader loader = new MapLoader(this);
			try {
				objects = loader.loadTiles(baseFolder + "/ForestZones/ForestMinusTwoTwo.txt");
			} catch (IOException e) {
				println("Sorry, kon map niet laden: " + e.getMessage());
			}
			createFirstKnight(900, 600);
			createFirstPaladin(600, 300);
			createSecondPaladin(1000, 150);
			createThirdPaladin(350, 150);
			setLoadedMap(true);
		}
	}

	void forestZoneMinusThreeOne() {
		if (!isLoadedMap()) {
			MapLoader loader = new MapLoader(this);
			try {
				objects = loader.loadTiles(baseFolder + "/ForestZones/ForestMinusThreeOne.txt");
			} catch (IOException e) {
				println("Sorry, kon map niet laden: " + e.getMessage());
			}
			createFirstKnight(300, 700);
			createSecondKnight(1350, 150);
			createFirstPaladin(400, 250);
			setLoadedMap(true);
		}
	}

	void forestZoneMinusThreeTwo() {
		if (!isLoadedMap()) {
			MapLoader loader = new MapLoader(this);
			try {
				objects = loader.loadTiles(baseFolder + "/ForestZones/ForestMinusThreeTwo.txt");
			} catch (IOException e) {
				println("Sorry, kon map niet laden: " + e.getMessage());
			}
			createFirstKnight(650, 700);
			createFirstPaladin(1150, 150);
			createFirstPaladin(750, 350);
			setLoadedMap(true);
		}
	}

	void forestZoneMinusFourOne() {
		if (!isLoadedMap()) {
			MapLoader loader = new MapLoader(this);
			try {
				objects = loader.loadTiles(baseFolder + "/ForestZones/ForestMinusFourOne.txt");
			} catch (IOException e) {
				println("Sorry, kon map niet laden: " + e.getMessage());
			}
			setLoadedMap(true);
		}
	}

	// Desert
	void desertZoneOne() {
		if (!isLoadedMap()) {
			MapLoader loader = new MapLoader(this);
			try {
				objects = loader.loadTiles(baseFolder + "/DesertZones/DesertZoneOne.txt");
			} catch (IOException e) {
				println("Sorry, kon map niet laden: " + e.getMessage());
			}
			createFirstSkeleton(1150, 400);
			createSecondSkeleton(650, 50);
			setLoadedMap(true);
		}
	}

	void desertZoneOneOne() {
		if (!isLoadedMap()) {
			MapLoader loader = new MapLoader(this);
			try {
				objects = loader.loadTiles(baseFolder + "/DesertZones/DesertOneOne.txt");
			} catch (IOException e) {
				println("Sorry, kon map niet laden: " + e.getMessage());
			}
			createFirstSkeleton(350, 150);
			createSecondSkeleton(250, 650);
			createFirstScorpion(1150, 50);
			setLoadedMap(true);
		}
	}

	void desertZoneOneTwo() {
		if (!isLoadedMap()) {
			MapLoader loader = new MapLoader(this);
			try {
				objects = loader.loadTiles(baseFolder + "/DesertZones/DesertOneTwo.txt");
			} catch (IOException e) {
				println("Sorry, kon map niet laden: " + e.getMessage());
			}
			createFirstSkeleton(500, 600);
			createSecondSkeleton(950, 100);
			createThirdSkeleton(250, 200);
			createFirstScorpion(1100, 650);
			setLoadedMap(true);
		}
	}

	void desertZoneOneThree() {
		if (!isLoadedMap()) {
			MapLoader loader = new MapLoader(this);
			try {
				objects = loader.loadTiles(baseFolder + "/DesertZones/DesertOneThree.txt");
			} catch (IOException e) {
				println("Sorry, kon map niet laden: " + e.getMessage());
			}
			createFirstSkeleton(1050, 650);
			createSecondSkeleton(250, 500);
			createThirdSkeleton(400, 100);
			createFirstScorpion(1300, 150);
			setLoadedMap(true);
		}
	}

	void desertZoneOneFour() {
		if (!isLoadedMap()) {
			MapLoader loader = new MapLoader(this);
			try {
				objects = loader.loadTiles(baseFolder + "/DesertZones/DesertOneFour.txt");
			} catch (IOException e) {
				println("Sorry, kon map niet laden: " + e.getMessage());
			}
			createFirstSkeleton(200, 550);
			createSecondSkeleton(1100, 650);
			createFirstScorpion(400, 300);
			createSecondScorpion(1100, 150);
			setLoadedMap(true);
		}
	}

	void desertZoneTwoZero() {
		if (!isLoadedMap()) {
			MapLoader loader = new MapLoader(this);
			try {
				objects = loader.loadTiles(baseFolder + "/DesertZones/DesertTwoZero.txt");
			} catch (IOException e) {
				println("Sorry, kon map niet laden: " + e.getMessage());
			}
			createFirstSkeleton(1000, 300);
			createSecondSkeleton(300, 300);
			createFirstScorpion(350, 700);
			setLoadedMap(true);
		}
	}

	void desertZoneTwoOne() {
		if (!isLoadedMap()) {
			MapLoader loader = new MapLoader(this);
			try {
				objects = loader.loadTiles(baseFolder + "/DesertZones/DesertTwoOne.txt");
			} catch (IOException e) {
				println("Sorry, kon map niet laden: " + e.getMessage());
			}
			createFirstSkeleton(400, 200);
			createSecondSkeleton(100, 600);
			createFirstScorpion(1300, 100);
			setLoadedMap(true);
		}
	}

	void desertZoneTwoTwo() {
		if (!isLoadedMap()) {
			MapLoader loader = new MapLoader(this);
			try {
				objects = loader.loadTiles(baseFolder + "/DesertZones/DesertTwoTwo.txt");
			} catch (IOException e) {
				println("Sorry, kon map niet laden: " + e.getMessage());
			}
			createFirstSkeleton(250, 200);
			createFirstScorpion(350, 650);
			createSecondScorpion(950, 700);
			setLoadedMap(true);
		}
		blackSmith();
		image(resourceManager.blacksmithHouse, 650, 150);
		image(resourceManager.blackSmith, blackSmithX, blackSmithY);
		resourceManager.blackSmith.play();
	}

	void desertZoneTwoThree() {
		if (!isLoadedMap()) {
			MapLoader loader = new MapLoader(this);
			try {
				objects = loader.loadTiles(baseFolder + "/DesertZones/DesertTwoThree.txt");
			} catch (IOException e) {
				println("Sorry, kon map niet laden: " + e.getMessage());
			}
			createFirstScorpion(300, 500);
			createSecondScorpion(750, 700);
			setLoadedMap(true);
		}
	}

	void desertZoneTwoFour() {
		if (!isLoadedMap()) {
			MapLoader loader = new MapLoader(this);
			try {
				objects = loader.loadTiles(baseFolder + "/DesertZones/DesertTwoFour.txt");
			} catch (IOException e) {
				println("Sorry, kon map niet laden: " + e.getMessage());
			}
			createFirstSkeleton(500, 650);
			createSecondSkeleton(700, 150);
			createThirdSkeleton(1050, 450);
			createFirstScorpion(250, 250);
			createSecondScorpion(800, 550);
			setLoadedMap(true);
		}
	}

	// Haunted Forest
	void hauntedZoneZeroMinusTwo() {
		if (!isLoadedMap()) {
			MapLoader loader = new MapLoader(this);
			try {
				objects = loader.loadTiles(baseFolder + "/HauntedForest/HauntedZeroMinusTwo.txt");
			} catch (IOException e) {
				println("Sorry, kon map niet laden: " + e.getMessage());
			}
			createFirstHound(350, 150);
			createFirstMedusa(1050, 650);
			setLoadedMap(true);
		}
	}

	void hauntedZoneZeroMinusThree() {
		if (!isLoadedMap()) {
			MapLoader loader = new MapLoader(this);
			try {
				objects = loader.loadTiles(baseFolder + "/HauntedForest/HauntedZeroMinusThree.txt");
			} catch (IOException e) {
				println("Sorry, kon map niet laden: " + e.getMessage());
			}
			createFirstHound(400, 500);
			createFirstMedusa(950, 150);
			setLoadedMap(true);
		}
	}

	void hauntedZoneZeroMinusFour() {
		if (!isLoadedMap()) {
			MapLoader loader = new MapLoader(this);
			try {
				objects = loader.loadTiles(baseFolder + "/HauntedForest/HauntedZeroMinusFour.txt");
			} catch (IOException e) {
				println("Sorry, kon map niet laden: " + e.getMessage());
			}
			createFirstHound(950, 250);
			createSecondHound(500, 750);
			createFirstMedusa(500, 750);
			setLoadedMap(true);
		}
	}

	void hauntedZoneOneMinusTwo() {
		if (!isLoadedMap()) {
			MapLoader loader = new MapLoader(this);
			try {
				objects = loader.loadTiles(baseFolder + "/HauntedForest/HauntedOneMinusTwo.txt");
			} catch (IOException e) {
				println("Sorry, kon map niet laden: " + e.getMessage());
			}
			createFirstHound(250, 650);
			createFirstMedusa(400, 150);
			setLoadedMap(true);
		}
	}

	void hauntedZoneOneMinusThree() {
		if (!isLoadedMap()) {
			MapLoader loader = new MapLoader(this);
			try {
				objects = loader.loadTiles(baseFolder + "/HauntedForest/HauntedOneMinusThree.txt");
			} catch (IOException e) {
				println("Sorry, kon map niet laden: " + e.getMessage());
			}
			createFirstHound(500, 700);
			createSecondHound(350, 150);
			setLoadedMap(true);
		}
	}

	void hauntedZoneOneMinusFour() {
		if (!isLoadedMap()) {
			MapLoader loader = new MapLoader(this);
			try {
				objects = loader.loadTiles(baseFolder + "/HauntedForest/HauntedOneMinusFour.txt");
			} catch (IOException e) {
				println("Sorry, kon map niet laden: " + e.getMessage());
			}
			createFirstHound(300, 600);
			createSecondHound(300, 200);
			createFirstMedusa(750, 700);
			setLoadedMap(true);
		}
	}

	void hauntedZoneTwoMinusOne() {
		if (!isLoadedMap()) {
			MapLoader loader = new MapLoader(this);
			try {
				objects = loader.loadTiles(baseFolder + "/HauntedForest/HauntedTwoMinusOne.txt");
			} catch (IOException e) {
				println("Sorry, kon map niet laden: " + e.getMessage());
			}
			createFirstHound(800, 250);
			createSecondHound(1100, 600);
			createThirdHound(600, 750);
			setLoadedMap(true);
		}
	}

	void hauntedZoneTwoMinusTwo() {
		if (!isLoadedMap()) {
			MapLoader loader = new MapLoader(this);
			try {
				objects = loader.loadTiles(baseFolder + "/HauntedForest/HauntedTwoMinusTwo.txt");
			} catch (IOException e) {
				println("Sorry, kon map niet laden: " + e.getMessage());
			}
			createFirstHound(300, 750);
			createSecondHound(1050, 300);
			createFirstMedusa(400, 100);
			setLoadedMap(true);
		}
	}

	void hauntedZoneTwoMinusThree() {
		if (!isLoadedMap()) {
			MapLoader loader = new MapLoader(this);
			try {
				objects = loader.loadTiles(baseFolder + "/HauntedForest/HauntedTwoMinusThree.txt");
			} catch (IOException e) {
				println("Sorry, kon map niet laden: " + e.getMessage());
			}
			createFirstHound(650, 150);
			createFirstMedusa(1050, 700);
			setLoadedMap(true);
		}
	}

	void hauntedZoneTwoMinusFour() {
		if (!isLoadedMap()) {
			MapLoader loader = new MapLoader(this);
			try {
				objects = loader.loadTiles(baseFolder + "/HauntedForest/HauntedTwoMinusFour.txt");
			} catch (IOException e) {
				println("Sorry, kon map niet laden: " + e.getMessage());
			}
			createFirstHound(500, 750);
			createFirstMedusa(350, 100);
			createSecondMedusa(1100, 500);
			setLoadedMap(true);
		}
	}

	void hauntedZoneOneMinusOne() {
		if (!isLoadedMap()) {
			MapLoader loader = new MapLoader(this);
			try {
				objects = loader.loadTiles(baseFolder + "/HauntedForest/HauntedOneMinusOne.txt");
			} catch (IOException e) {
				println("Sorry, kon map niet laden: " + e.getMessage());
			}
			createFirstHound(700, 500);
			createSecondHound(450, 200);
			createFirstMedusa(1000, 150);
			setLoadedMap(true);
		}
	}

	void hauntedForestZoneOne() {
		if (!isLoadedMap()) {
			MapLoader loader = new MapLoader(this);
			try {
				objects = loader.loadTiles(baseFolder + "/HauntedForest/HauntedForest1.txt");
			} catch (IOException e) {
				println("Sorry, kon map niet laden: " + e.getMessage());
			}
			createFirstHound(600, 600);
			createFirstMedusa(850, 150);
			setLoadedMap(true);
		}
	}

	void traderHouse() {
		float traderHouseDoorX = 1225;
		float traderHouseDoorY = 350;
		if (!isLoadedMap()) {
			resourceManager.soundFile.stop();
			setPlayingMusic(false);
			MapLoader loader = new MapLoader(this);
			try {
				objects = loader.loadTiles(baseFolder + "/ForestZones/traderHouse.txt");
			} catch (IOException e) {
				println("Sorry, kon map niet laden: " + e.getMessage());
			}
			setLoadedMap(true);
		}
		if (characterX > traderHouseDoorX) {
			setLoadedMap(false);
			currentZone = "Forest";
			characterX = 600;
			characterY = 300;
			enteredHouse = false;
		}
		oldDude(oldManX, oldManY);
		image(resourceManager.oldDudeImage, oldManX, oldManY);
		image(resourceManager.traderHouseDoor, traderHouseDoorX, traderHouseDoorY, 100, 150);
	}

	// ---------------------------------------------------------------------------------------------------\\
	// Elements and damage
	void characterStats() {
		elementSelection();
		weaponSelection();
		damageCalculator();
		characterStatCalculator();
		inventory();

	}

	void inventory() {
		/*
		 * public float elementWindX = 1135; public float elementIceX = 1290;
		 */

		if (loadInventory == true) {
			fill(255);
			image(resourceManager.scorpionScale, elementFireX, elementY);
			text(scorpionScales, elementFireX, elementY + tileSize);
			image(resourceManager.skeletonBone, elementLightX, elementY);
			text(skeletonBones, elementLightX, elementY + tileSize);
			image(resourceManager.medusaHair, elementWaterX, elementY);
			text(medusaHair, elementWaterX, elementY + tileSize);
			image(resourceManager.paladinArmorScrap, elementDarkX, elementY);
			text(paladinArmorScraps, elementDarkX, elementY + tileSize);
			image(resourceManager.lanceKnightSpearTip, elementEarthX, elementY);
			text(lanceKnightSpearTips, elementEarthX, elementY + tileSize);
			image(resourceManager.houndTooth, elementLightningX, elementY);
			text(houndTeeth, elementLightningX, elementY + tileSize);
		}
	}

	void loadMaterials() {
		float materialSwordX = swordX + 30;
		float textSpace = 30;
		float materialAxeX = axeX;
		float materialScimitarX = scimitarX;

		float armorMaterialX = armorX + 30;
		float helmMaterialOne = helmY;
		float helmMaterialTwo = helmY + 30;
		float chestMaterialOne = chestY;
		float chestMaterialTwo = chestY + 30;
		float pantsMaterialOne = pantsY;
		float pantsMaterialTwo = pantsY + 30;
		float bootsMaterialOne = bootsY;
		float bootsMaterialTwo = bootsY + 30;

		float materialYOne = weaponY + 60;
		float materialYTwo = materialYOne + 30;
		float materialYThree = materialYTwo + 30;
		float materialYFour = materialYThree + 30;
		textSize(15);

		if (helmLevel <= 3) {
			image(resourceManager.paladinArmorScrap, armorMaterialX, helmMaterialOne, 30, 30);
			text(helmMaterials, armorMaterialX + textSpace, helmMaterialOne);
			image(resourceManager.lanceKnightSpearTip, armorMaterialX, helmMaterialTwo, 30, 30);
			text(helmMaterials, armorMaterialX + textSpace, helmMaterialTwo);
		}
		if (chestLevel <= 3) {
			image(resourceManager.paladinArmorScrap, armorMaterialX, chestMaterialOne, 30, 30);
			text(chestMaterials, armorMaterialX + textSpace, chestMaterialOne);
			image(resourceManager.lanceKnightSpearTip, armorMaterialX, chestMaterialTwo, 30, 30);
			text(chestMaterials, armorMaterialX + textSpace, chestMaterialTwo);
		}
		if (pantsLevel <= 3) {
			image(resourceManager.paladinArmorScrap, armorMaterialX, pantsMaterialOne, 30, 30);
			text(pantsMaterials, armorMaterialX + textSpace, pantsMaterialOne);
			image(resourceManager.lanceKnightSpearTip, armorMaterialX, pantsMaterialTwo, 30, 30);
			text(pantsMaterials, armorMaterialX + textSpace, pantsMaterialTwo);
		}
		if (bootsLevel <= 3) {
			image(resourceManager.paladinArmorScrap, armorMaterialX, bootsMaterialOne, 30, 30);
			text(bootsMaterials, armorMaterialX + textSpace, bootsMaterialOne);
			image(resourceManager.lanceKnightSpearTip, armorMaterialX, bootsMaterialTwo, 30, 30);
			text(bootsMaterials, armorMaterialX + textSpace, bootsMaterialTwo);
		}

		if (swordLevel == 1) {
			image(resourceManager.paladinArmorScrap, materialSwordX, materialYOne, 30, 30);
			text("5", materialSwordX + textSpace, materialYOne);
			image(resourceManager.lanceKnightSpearTip, materialSwordX, materialYTwo, 30, 30);
			text("5", materialSwordX + textSpace, materialYTwo);
		}

		if (swordLevel == 2) {
			image(resourceManager.paladinArmorScrap, materialSwordX, materialYOne, 30, 30);
			text("5", materialSwordX + textSpace, materialYOne);
			image(resourceManager.lanceKnightSpearTip, materialSwordX, materialYTwo, 30, 30);
			text("5", materialSwordX + textSpace, materialYTwo);
			image(resourceManager.skeletonBone, materialSwordX, materialYThree, 30, 30);
			text("1", materialSwordX + textSpace, materialYThree);
		}

		if (swordLevel == 3) {
			image(resourceManager.paladinArmorScrap, materialSwordX, materialYOne, 30, 30);
			text("10", materialSwordX + textSpace, materialYOne);
			image(resourceManager.lanceKnightSpearTip, materialSwordX, materialYTwo, 30, 30);
			text("10", materialSwordX + textSpace, materialYTwo);
			image(resourceManager.skeletonBone, materialSwordX, materialYThree, 30, 30);
			text("5", materialSwordX + textSpace, materialYThree);
		}

		if (swordLevel == 4) {
			image(resourceManager.paladinArmorScrap, materialSwordX, materialYOne, 30, 30);
			text("15", materialSwordX + textSpace, materialYOne);
			image(resourceManager.lanceKnightSpearTip, materialSwordX, materialYTwo, 30, 30);
			text("15", materialSwordX + textSpace, materialYTwo);
			image(resourceManager.medusaHair, materialSwordX, materialYThree, 30, 30);
			text("5", materialSwordX + textSpace, materialYThree);
		}

		if (swordLevel == 5) {
			image(resourceManager.paladinArmorScrap, materialSwordX, materialYOne, 30, 30);
			text("15", materialSwordX + textSpace, materialYOne);
			image(resourceManager.lanceKnightSpearTip, materialSwordX, materialYTwo, 30, 30);
			text("15", materialSwordX + textSpace, materialYTwo);
			image(resourceManager.houndTooth, materialSwordX, materialYThree, 30, 30);
			text("5", materialSwordX + textSpace, materialYThree);
		}

		if (axeLevel == 1) {
			image(resourceManager.paladinArmorScrap, materialAxeX, materialYOne, 30, 30);
			text("5", materialAxeX + textSpace, materialYOne);
			image(resourceManager.lanceKnightSpearTip, materialAxeX, materialYTwo, 30, 30);
			text("5", materialAxeX + textSpace, materialYTwo);
		}

		if (axeLevel == 2) {
			image(resourceManager.paladinArmorScrap, materialAxeX, materialYOne, 30, 30);
			text("10", materialAxeX + textSpace, materialYOne);
			image(resourceManager.lanceKnightSpearTip, materialAxeX, materialYTwo, 30, 30);
			text("10", materialAxeX + textSpace, materialYTwo);
			image(resourceManager.medusaHair, materialAxeX, materialYThree, 30, 30);
			text("1", materialAxeX + textSpace, materialYThree);
		}

		if (axeLevel == 3) {
			image(resourceManager.paladinArmorScrap, materialAxeX, materialYOne, 30, 30);
			text("10", materialAxeX + textSpace, materialYOne);
			image(resourceManager.lanceKnightSpearTip, materialAxeX, materialYTwo, 30, 30);
			text("10", materialAxeX + textSpace, materialYTwo);
			image(resourceManager.medusaHair, materialAxeX, materialYThree, 30, 30);
			text("5", materialAxeX + textSpace, materialYThree);
		}

		if (axeLevel == 4) {
			image(resourceManager.paladinArmorScrap, materialAxeX, materialYOne, 30, 30);
			text("15", materialAxeX + textSpace, materialYOne);
			image(resourceManager.lanceKnightSpearTip, materialAxeX, materialYTwo, 30, 30);
			text("15", materialAxeX + textSpace, materialYTwo);
			image(resourceManager.medusaHair, materialAxeX, materialYThree, 30, 30);
			text("5", materialAxeX + textSpace, materialYThree);
			image(resourceManager.houndTooth, materialAxeX, materialYFour, 30, 30);
			text("5", materialAxeX + textSpace, materialYFour);
		}

		if (scimitarLevel == 1) {
			image(resourceManager.scorpionScale, materialScimitarX, materialYOne, 30, 30);
			text("5", materialScimitarX + textSpace, materialYOne);
			image(resourceManager.paladinArmorScrap, materialScimitarX, materialYTwo, 30, 30);
			text("5", materialScimitarX + textSpace, materialYTwo);
		}

		if (scimitarLevel == 2) {
			image(resourceManager.scorpionScale, materialScimitarX, materialYOne, 30, 30);
			text("5", materialScimitarX + textSpace, materialYOne);
			image(resourceManager.paladinArmorScrap, materialScimitarX, materialYTwo, 30, 30);
			text("5", materialScimitarX + textSpace, materialYTwo);
			image(resourceManager.houndTooth, materialScimitarX, materialYThree, 30, 30);
			text("1", materialScimitarX + textSpace, materialYThree);
		}

		if (scimitarLevel == 3) {
			image(resourceManager.scorpionScale, materialScimitarX, materialYOne, 30, 30);
			text("10", materialScimitarX + textSpace, materialYOne);
			image(resourceManager.paladinArmorScrap, materialScimitarX, materialYTwo, 30, 30);
			text("10", materialScimitarX + textSpace, materialYTwo);
			image(resourceManager.houndTooth, materialScimitarX, materialYThree, 30, 30);
			text("5", materialScimitarX + textSpace, materialYThree);
			image(resourceManager.medusaHair, materialScimitarX, materialYFour, 30, 30);
			text("5", materialScimitarX + textSpace, materialYFour);
		}

	}

	void weaponImages() {
		if (swordLevel == 1) {
			resourceManager.swordImage = resourceManager.swordOne;
		}
		if (swordLevel == 2) {
			resourceManager.swordImage = resourceManager.swordTwo;
		}
		if (swordLevel == 3) {
			resourceManager.swordImage = resourceManager.swordThree;
		}

		if (swordLevel == 4) {
			resourceManager.swordImage = resourceManager.swordFour;
		}
		if (swordLevel == 5) {
			resourceManager.swordImage = resourceManager.swordFive;
		}
		if (swordLevel == 6) {
			resourceManager.swordImage = resourceManager.swordSix;
		}
		if (axeLevel == 1) {
			resourceManager.axeImage = resourceManager.axeOne;
		}
		if (axeLevel == 2) {
			resourceManager.axeImage = resourceManager.axeTwo;
		}
		if (axeLevel == 3) {
			resourceManager.axeImage = resourceManager.axeThree;
		}
		if (axeLevel == 4) {
			resourceManager.axeImage = resourceManager.axeFour;
		}
		if (axeLevel == 5) {
			resourceManager.axeImage = resourceManager.axeFive;
		}

		if (scimitarLevel == 1) {
			resourceManager.scimitarImage = resourceManager.scimitarOne;
		}
		if (scimitarLevel == 3) {
			resourceManager.scimitarImage = resourceManager.scimitarTwo;
		}
		if (scimitarLevel == 3) {
			resourceManager.scimitarImage = resourceManager.scimitarThree;
		}
		if (scimitarLevel == 4) {
			resourceManager.scimitarImage = resourceManager.scimitarFour;
		}

		if (helmLevel == 0) {
			resourceManager.helmImage = resourceManager.helmOne;
		}
		if (helmLevel == 1) {
			resourceManager.helmImage = resourceManager.helmTwo;
		}
		if (helmLevel == 2) {
			resourceManager.helmImage = resourceManager.helmThree;
		}
		if (helmLevel == 3) {
			resourceManager.helmImage = resourceManager.helmFour;
		}
		if (helmLevel == 4) {
			resourceManager.helmImage = resourceManager.helmFive;
		}

		if (chestLevel == 0) {
			resourceManager.chestImage = resourceManager.chestOne;
		}
		if (chestLevel == 1) {
			resourceManager.chestImage = resourceManager.chestTwo;
		}
		if (chestLevel == 2) {
			resourceManager.chestImage = resourceManager.chestThree;
		}
		if (chestLevel == 3) {
			resourceManager.chestImage = resourceManager.chestFour;
		}
		if (chestLevel == 4) {
			resourceManager.chestImage = resourceManager.chestFive;
		}

		if (pantsLevel == 0) {
			resourceManager.pantsImage = resourceManager.pantsOne;
		}
		if (pantsLevel == 1) {
			resourceManager.pantsImage = resourceManager.pantsTwo;
		}
		if (pantsLevel == 2) {
			resourceManager.pantsImage = resourceManager.pantsThree;
		}
		if (pantsLevel == 3) {
			resourceManager.pantsImage = resourceManager.pantsFour;
		}
		if (pantsLevel == 4) {
			resourceManager.pantsImage = resourceManager.pantsFive;
		}

		if (bootsLevel == 0) {
			resourceManager.bootsImage = resourceManager.bootsOne;
		}
		if (bootsLevel == 1) {
			resourceManager.bootsImage = resourceManager.bootsTwo;
		}
		if (bootsLevel == 2) {
			resourceManager.bootsImage = resourceManager.bootsThree;
		}
		if (bootsLevel == 3) {
			resourceManager.bootsImage = resourceManager.bootsFour;
		}
		if (bootsLevel == 4) {
			resourceManager.bootsImage = resourceManager.bootsFive;
		}

	}

	void weaponSelection() {
		weaponImages();
		if (weaponSelected == "Sword") {
			if (swordLevel == 1) {
				swordBonusDamage = 0.3f;
			}
			if (swordLevel == 2) {
				swordBonusDamage = 0.6f;
			}
			if (swordLevel == 3) {
				swordBonusDamage = 0.9f;
			}

			if (swordLevel == 4) {
				swordBonusDamage = 1.2f;
			}
			if (swordLevel == 5) {
				swordBonusDamage = 1.5f;
			}
			if (swordLevel == 6) {
				swordBonusDamage = 1.8f;
			}
		}

		if (weaponSelected == "Axe") {
			if (axeLevel == 1) {
				axeBonusDamage = 0.3f;
				axeBonusRange = 2;
			}
			if (axeLevel == 2) {
				axeBonusDamage = 0.5f;
				axeBonusRange = 4;
			}
			if (axeLevel == 3) {
				axeBonusDamage = 0.7f;
				axeBonusRange = 6;
			}
			if (axeLevel == 4) {
				axeBonusDamage = 0.9f;
				axeBonusRange = 8;
			}
			if (axeLevel == 5) {
				axeBonusDamage = 1.3f;
				axeBonusRange = 10;
			}
		}

		if (weaponSelected == "Scimitar") {
			if (scimitarLevel == 1) {
				scimitarBonusDamage += 10f;
			}
			if (scimitarLevel == 3) {
				scimitarBonusDamage += 20f;
			}
			if (scimitarLevel == 3) {
				scimitarBonusDamage += 30f;
			}
			if (scimitarLevel == 4) {
				scimitarBonusDamage += 40f;
			}
		}

		if (loadWeapons == true) {
			image(resourceManager.swordImage, swordX, weaponY);
			image(resourceManager.axeImage, axeX, weaponY);
			image(resourceManager.scimitarImage, scimitarX, weaponY);
			image(resourceManager.helmImage, armorX, helmY);
			image(resourceManager.chestImage, armorX, chestY);
			image(resourceManager.pantsImage, armorX, pantsY);
			image(resourceManager.bootsImage, armorX, bootsY);

			if (openShop == true) {
				loadMaterials();
			}
			if (openShop == false) {
				if (weaponSelected == "Sword") {
					image(resourceManager.elementSelectedImage, swordX, weaponY, 100, 100);
				}
				if (weaponSelected == "Axe") {
					image(resourceManager.elementSelectedImage, axeX, weaponY, 100, 100);
				}
				if (weaponSelected == "Scimitar") {
					image(resourceManager.elementSelectedImage, scimitarX, weaponY, 100, 100);
				}
			}
		}
	}

	void characterStatCalculator() {
		if (initializeWeaponDamage == true) {
			resetCharacterStats();
			if (weaponSelected == "Sword") {
				baseDamage += swordBonusDamage;
			}
			if (weaponSelected == "Axe") {
				swordSize += axeBonusRange;
				baseDamage += axeBonusDamage;
			}
			if (weaponSelected == "Scimitar") {
				baseBowDamage += scimitarBonusDamage;
			}
			healthBonus = (helmLevel * 25) + (chestLevel * 25) + (pantsLevel * 25) + (bootsLevel * 25);
			characterMaxHealth += healthBonus;
			characterHealth += healthBonus;

			initializeWeaponDamage = false;
		}
	}

	void damageCalculator() {
		if (initializeDamage == true) {
			// Earth does more damage
			if (firstElement == "Earth" || secondElement == "Earth") {
				baseDamage = baseDamage + (baseDamage / 3);
				bowDamage = bowDamage + (bowDamage / 3);
				earthElement = true;
			}
			// Fire sets enemies on fire
			if (firstElement == "Fire" || secondElement == "Fire") {
				fireElement = true;
			}
			// Ice slows enemy movement speed
			if (firstElement == "Ice" || secondElement == "Ice") {
				iceElement = true;
			}
			// Light reduces damage taken and dealt
			if (firstElement == "Light" || secondElement == "Light") {
				lightElement = true;
				baseDamage = baseDamage - (baseDamage / 4);
				bowDamage = bowDamage - (bowDamage / 4);
			}
			// Dark restores health whenever you deal damage
			if (firstElement == "Dark" || secondElement == "Dark") {
				darkElement = true;
			}
			// Lightning causes enemies to deal and take more damage
			if (firstElement == "Lightning" || secondElement == "Lightning") {
				lightningElement = true;
			}
			// Wind causes arrows to move faster
			if (firstElement == "Wind" || secondElement == "Wind") {
				windSpeed = 10;
				windElement = true;
			}
			// Water does nothing so far
			if (firstElement == "Water" || secondElement == "Water") {
				dropRate = 20;
			}
			initializeDamage = false;
		}
	}

	void elementSelection() {
		if (loadElements == true) {
			if (fireUnlocked == true || openShop == true) {
				image(resourceManager.elementImageFire, elementFireX, elementY);
			}
			if (iceUnlocked == true || openShop == true) {
				image(resourceManager.elementImageIce, elementIceX, elementY);
			}
			if (lightUnlocked == true || openShop == true) {
				image(resourceManager.elementImageLight, elementLightX, elementY);
			}
			if (waterUnlocked == true || openShop == true) {
				image(resourceManager.elememtImageWater, elementWaterX, elementY);
			}
			if (darkUnlocked == true || openShop == true) {
				image(resourceManager.elementImageDark, elementDarkX, elementY);
			}
			if (earthUnlocked == true || openShop == true) {
				image(resourceManager.elementImageEarth, elementEarthX, elementY);
			}
			if (windUnlocked == true || openShop == true) {
				image(resourceManager.elementImageWind, elementWindX, elementY);
			}
			if (lightningUnlocked == true || openShop == true) {
				image(resourceManager.elementImageLightning, elementLightningX, elementY);
			}
			if (openShop == false)
				image(resourceManager.elementImageReset, elementResetX, elementResetY);

			if (earthElement == true) {
				image(resourceManager.elementSelectedImage, elementEarthX, elementY, 100, 100);
			}
			if (fireElement == true) {
				image(resourceManager.elementSelectedImage, elementFireX, elementY, 100, 100);
			}
			if (iceElement == true) {
				image(resourceManager.elementSelectedImage, elementIceX, elementY, 100, 100);
			}
			if (darkElement == true) {
				image(resourceManager.elementSelectedImage, elementDarkX, elementY, 100, 100);
			}
			if (lightElement == true) {
				image(resourceManager.elementSelectedImage, elementLightX, elementY, 100, 100);
			}
			if (lightningElement == true) {
				image(resourceManager.elementSelectedImage, elementLightningX, elementY, 100, 100);
			}
			if (windElement == true) {
				image(resourceManager.elementSelectedImage, elementWindX, elementY, 100, 100);
			}
			if (waterElement == true) {
				image(resourceManager.elementSelectedImage, elementWaterX, elementY, 100, 100);
			}
		}
	}

	void resetCharacterStats() {
		baseDamage = 5;
		bowDamage = 50;
		characterHealth = 100;
		healthBonus = 0;
		characterMaxHealth = 100;
	}

	void resetDamageValues() {
		windSpeed = 0;
		fireElement = false;
		iceElement = false;
		darkElement = false;
		lightningElement = false;
		lightElement = false;
		earthElement = false;
		windElement = false;
		waterElement = false;
	}

	// ---------------------------------------------------------------------------------------------------\\
	// mousePressed, responsible for multiple actions
	public void mousePressed() {
		println(mouseX, mouseY);
		// Chat gedeelte
		if (dist(mouseX, mouseY, shopChatX, nextChatY) < 50 && action == true) {
			openShop = true;
		}

		if (dist(mouseX, mouseY, nextChatX, nextChatY) < 50 && action == true) {
			if (currentText != 8 && currentTextSetting == "Introduction") {
				currentText++;
			}
			if (currentText != 4 && currentTextSetting == "ElementExplanation") {
				currentText++;
			}
		}
		if (dist(mouseX, mouseY, prevChatX, nextChatY) < 50 && action == true) {
			currentText--;
			if (currentText < 0) {
				currentText = 0;
			}
		}
		if (dist(mouseX, mouseY, extraChatX, nextChatY) < 50 && action == true) {
			changeExtra = true;
			if (currentTextSetting == "Introduction" == changeExtra == true) {
				currentTextSetting = "ElementExplanation";
				currentText = 0;
				changeExtra = false;
			}
			if (currentTextSetting == "ElementExplanation" && changeExtra == true) {
				currentTextSetting = "Introduction";
				currentText = 0;
				changeExtra = false;
			}
		}
		// Weapon selection
		if (loadWeapons == true) {
			if (dist(mouseX, mouseY, armorX, helmY) < 50) {
				if (openShop == true && helmLevel < 5) {
					upgradeWeapons();
					selectUpgrade = "Helm";
					if (helmUpgradeable == true) {
						helmLevel++;
						reduceMaterials();
						initializeWeaponDamage = true;
					}
				}
			}
			if (loadWeapons == true) {
				if (dist(mouseX, mouseY, armorX, chestY) < 50) {
					if (openShop == true && chestLevel < 5) {
						upgradeWeapons();
						selectUpgrade = "Chest";
						if (chestUpgradeable == true) {
							chestLevel++;
							reduceMaterials();
							initializeWeaponDamage = true;
						}
					}
				}
			}
			if (loadWeapons == true) {
				if (dist(mouseX, mouseY, armorX, pantsY) < 50) {
					if (openShop == true && pantsLevel < 5) {
						upgradeWeapons();
						selectUpgrade = "Pants";
						if (pantsUpgradeable == true) {
							pantsLevel++;
							reduceMaterials();
							initializeWeaponDamage = true;
						}
					}
				}
			}
			if (loadWeapons == true) {
				if (dist(mouseX, mouseY, armorX, bootsY) < 50) {
					if (openShop == true && bootsLevel < 5) {
						upgradeWeapons();
						selectUpgrade = "Boots";
						if (bootsUpgradeable == true) {
							bootsLevel++;
							reduceMaterials();
							initializeWeaponDamage = true;
						}
					}
				}
			}

			if (dist(mouseX, mouseY, swordX, weaponY) < 50) {
				if (openShop == false && weaponSelected != "Sword") {
					weaponSelected = "Sword";
					initializeWeaponDamage = true;
				}
				if (openShop == true && swordLevel < 6) {
					upgradeWeapons();
					selectUpgrade = "Sword";
					if (swordUpgradeable == true) {
						swordLevel++;
						reduceMaterials();
						initializeWeaponDamage = true;
					}
				}
			}
			if (dist(mouseX, mouseY, axeX, weaponY) < 50) {
				if (openShop == false && weaponSelected != "Axe") {
					weaponSelected = "Axe";
					initializeWeaponDamage = true;
				}
				if (openShop == true && axeLevel < 5) {
					upgradeWeapons();
					selectUpgrade = "Axe";
					if (axeUpgradeable == true) {
						axeLevel++;
						reduceMaterials();
						initializeWeaponDamage = true;
					}
				}
			}
			if (dist(mouseX, mouseY, scimitarX, weaponY) < 50) {
				if (openShop == false && weaponSelected != "Scimitar") {
					weaponSelected = "Scimitar";
					initializeWeaponDamage = true;
				}
				if (openShop == true && scimitarLevel < 4) {
					upgradeWeapons();
					selectUpgrade = "Scimitar";
					if (scimitarUpgradeable == true) {
						scimitarLevel++;
						reduceMaterials();
						initializeWeaponDamage = true;
					}
				}

			}
		}

		// Elements gedeelte
		if (loadElements == true) {
			if (dist(mouseX, mouseY, elementFireX, elementY) < tileSize) {
				if (openShop == true && action == true && fireUnlocked == false) {
					fireUnlocked = true;
				}
				if (firstElement == null && firstElement != "Fire" && openShop == false && fireUnlocked == true) {
					firstElement = "Fire";
					initializeDamage = true;
				}

				if (firstElement != null && firstElement != "Fire" && secondElement == null && secondElement != "Fire"
						&& openShop == false && fireUnlocked == true) {
					secondElement = "Fire";
					initializeDamage = true;
				}
			}

			if (dist(mouseX, mouseY, elementIceX, elementY) < tileSize) {
				if (openShop == true && action == true && iceUnlocked == false) {
					iceUnlocked = true;
				}
				if (firstElement == null && firstElement != "Ice" && openShop == false && iceUnlocked == true) {
					firstElement = "Ice";
					initializeDamage = true;
				}
				if (firstElement != null && firstElement != "Ice" && secondElement == null && secondElement != "Ice"
						&& openShop == false && iceUnlocked == true) {
					secondElement = "Ice";
					initializeDamage = true;
				}
			}

			if (dist(mouseX, mouseY, elementLightX, elementY) < tileSize) {
				if (openShop == true && action == true && lightUnlocked == false) {
					lightUnlocked = true;
				}
				if (firstElement == null && firstElement != "Light" && openShop == false && lightUnlocked == true) {
					firstElement = "Light";
					initializeDamage = true;
				}
				if (firstElement != null && firstElement != "Light" && secondElement == null && secondElement != "Light"
						&& openShop == false && lightUnlocked == true) {
					secondElement = "Light";
					initializeDamage = true;
				}
			}

			if (dist(mouseX, mouseY, elementWaterX, elementY) < tileSize) {
				if (openShop == true && action == true && waterUnlocked == false) {
					waterUnlocked = true;
				}
				if (firstElement == null && firstElement != "Water" && openShop == false && waterUnlocked == true) {
					firstElement = "Water";
					initializeDamage = true;
				} else if (firstElement != null && firstElement != "Water" && secondElement == null
						&& secondElement != "Water" && openShop == false && waterUnlocked == true) {
					secondElement = "Water";
					initializeDamage = true;
				}
			}

			if (dist(mouseX, mouseY, elementDarkX, elementY) < tileSize) {
				if (openShop == true && action == true && darkUnlocked == false) {
					darkUnlocked = true;
				}
				if (firstElement == null && firstElement != "Dark" && openShop == false && darkUnlocked == true) {
					firstElement = "Dark";
					initializeDamage = true;
				}
				if (firstElement != null && firstElement != "Dark" && secondElement == null && secondElement != "Dark"
						&& openShop == false && darkUnlocked == true) {
					secondElement = "Dark";
					initializeDamage = true;
				}
			}

			if (dist(mouseX, mouseY, elementEarthX, elementY) < tileSize) {
				if (openShop == true && action == true && earthUnlocked == false) {
					earthUnlocked = true;
				}
				if (firstElement == null && firstElement != "Earth" && openShop == false && earthUnlocked == true) {
					firstElement = "Earth";
					initializeDamage = true;
				}
				if (firstElement != null && firstElement != "Earth" && secondElement == null && secondElement != "Earth"
						&& openShop == false && earthUnlocked == true) {
					secondElement = "Earth";
					initializeDamage = true;
				}
			}

			if (dist(mouseX, mouseY, elementWindX, elementY) < tileSize) {
				if (openShop == true && action == true && windUnlocked == false) {
					windUnlocked = true;
				}
				if (firstElement == null && firstElement != "Wind" && openShop == false && windUnlocked == true) {
					firstElement = "Wind";
					initializeDamage = true;
				}
				if (firstElement != null && firstElement != "Wind" && secondElement == null && secondElement != "Wind"
						&& openShop == false && windUnlocked == true) {
					secondElement = "Wind";
					initializeDamage = true;
				}
			}

			if (dist(mouseX, mouseY, elementLightningX, elementY) < tileSize) {
				if (openShop == true && action == true && lightningUnlocked == false) {
					lightningUnlocked = true;
				}
				if (firstElement == null && firstElement != "Lightning" && openShop == false && windUnlocked == true) {
					firstElement = "Lightning";
					initializeDamage = true;
				}
				if (firstElement != null && firstElement != "Lightning" && secondElement == null
						&& secondElement != "Lightning" && openShop == false && windUnlocked == true) {
					secondElement = "Lightning";
					initializeDamage = true;
				}
			}

			if (dist(mouseX, mouseY, elementResetX, elementResetY) < tileSize) {
				firstElement = null;
				secondElement = null;
				initializeDamage = true;
				resetDamageValues();
			}
		}
	}

	// ---------------------------------------------------------------------------------------------------\\
	// Blacksmith
	void blackSmith() {
		int chatBoxX = 50;
		int chatBoxY = 600;
		if (characterX / 50 == 11 && characterY / 50 == 7 && action == true) {
			chatBox(chatBoxX, chatBoxY);
			textChat(chatBoxX, chatBoxY, "Blacksmith");

			if (openShop == true) {
				loadWeapons = true;
			}
		}
		if (openShop == true && action == false) {
			openShop = false;
			loadWeapons = false;
			currentText = 0;
		}
	}

	void reduceMaterials() {
		if (selectUpgrade == "Sword") {
			if (swordLevel == 1) {
				paladinArmorScraps -= 5;
				lanceKnightSpearTips -= 5;
			}
			if (swordLevel == 2) {
				paladinArmorScraps -= 5;
				lanceKnightSpearTips -= 5;
				skeletonBones -= 1;
			}
			if (swordLevel == 3) {
				paladinArmorScraps -= 10;
				lanceKnightSpearTips -= 10;
				skeletonBones -= 5;
			}
			if (swordLevel == 4) {
				paladinArmorScraps -= 15;
				lanceKnightSpearTips -= 15;
				medusaHair -= 5;
			}
			if (swordLevel == 5) {
				paladinArmorScraps -= 15;
				lanceKnightSpearTips -= 15;
				houndTeeth -= 5;
			}
		}
		if (selectUpgrade == "Axe") {
			if (axeLevel == 1) {
				paladinArmorScraps -= 5;
				lanceKnightSpearTips -= 5;
			}
			if (axeLevel == 2) {
				paladinArmorScraps -= 10;
				lanceKnightSpearTips -= 10;
				medusaHair -= 1;
			}
			if (axeLevel == 3) {
				paladinArmorScraps -= 10;
				lanceKnightSpearTips -= 10;
				medusaHair -= 5;
			}
			if (axeLevel == 4) {
				paladinArmorScraps -= 15;
				lanceKnightSpearTips -= 15;
				medusaHair -= 5;
				houndTeeth -= 5;
			}
		}
		if (selectUpgrade == "Scimitar") {
			if (scimitarLevel == 1) {
				scorpionScales -= 5;
				paladinArmorScraps -= 5;
			}
			if (scimitarLevel == 2) {
				scorpionScales -= 5;
				paladinArmorScraps -= 5;
				houndTeeth -= 5;
			}
			if (scimitarLevel == 3) {
				scorpionScales -= 10;
				paladinArmorScraps -= 10;
				houndTeeth -= 5;
				medusaHair -= 5;
			}
		}

		if (selectUpgrade == "Helm") {
			paladinArmorScraps -= helmMaterials;
			lanceKnightSpearTips -= helmMaterials;
			helmMaterials += 5;
		}
		if (selectUpgrade == "Chest") {
			paladinArmorScraps -= chestMaterials;
			lanceKnightSpearTips -= chestMaterials;
			chestMaterials += 5;
		}
		if (selectUpgrade == "Pants") {
			paladinArmorScraps -= pantsMaterials;
			lanceKnightSpearTips -= pantsMaterials;
			pantsMaterials += 5;
		}
		if (selectUpgrade == "Boots") {
			paladinArmorScraps -= bootsMaterials;
			lanceKnightSpearTips -= bootsMaterials;
			bootsMaterials += 5;
		}

		helmUpgradeable = false;
		chestUpgradeable = false;
		pantsUpgradeable = false;
		bootsUpgradeable = false;
		swordUpgradeable = false;
		scimitarUpgradeable = false;
		axeUpgradeable = false;
	}

	void upgradeWeapons() {
		if (swordLevel == 1 && paladinArmorScraps >= 5 && lanceKnightSpearTips >= 5) {
			swordUpgradeable = true;
		}
		if (swordLevel == 2 && paladinArmorScraps >= 5 && lanceKnightSpearTips >= 5 && skeletonBones >= 1) {
			swordUpgradeable = true;
		}
		if (swordLevel == 3 && paladinArmorScraps >= 10 && lanceKnightSpearTips >= 10 && skeletonBones >= 5) {
			swordUpgradeable = true;
		}
		if (swordLevel == 4 && paladinArmorScraps >= 15 && lanceKnightSpearTips >= 15 && medusaHair >= 5) {
			swordUpgradeable = true;
		}
		if (swordLevel == 5 && paladinArmorScraps >= 15 && lanceKnightSpearTips >= 15 && houndTeeth >= 5) {
			swordUpgradeable = true;
		}

		if (axeLevel == 1 && paladinArmorScraps >= 5 && lanceKnightSpearTips >= 5) {
			axeUpgradeable = true;
		}
		if (axeLevel == 2 && paladinArmorScraps >= 10 && lanceKnightSpearTips >= 10 && medusaHair >= 1) {
			axeUpgradeable = true;
		}
		if (axeLevel == 3 && paladinArmorScraps >= 10 && lanceKnightSpearTips >= 10 && medusaHair >= 5) {
			axeUpgradeable = true;
		}
		if (axeLevel == 4 && paladinArmorScraps >= 15 && lanceKnightSpearTips >= 15 && medusaHair >= 5
				&& houndTeeth >= 5) {
			axeUpgradeable = true;
		}

		if (scimitarLevel == 1 && scorpionScales >= 5 && paladinArmorScraps >= 5) {
			scimitarUpgradeable = true;
		}
		if (scimitarLevel == 2 && scorpionScales >= 5 && paladinArmorScraps >= 5 && houndTeeth >= 1) {
			scimitarUpgradeable = true;
		}
		if (scimitarLevel == 3 && scorpionScales >= 10 && paladinArmorScraps >= 10 && houndTeeth >= 5
				&& medusaHair >= 5) {
			scimitarUpgradeable = true;
		}

		// Armor upgrades //

		if (helmLevel <= 4 && paladinArmorScraps >= helmMaterials && lanceKnightSpearTips >= helmMaterials) {
			helmUpgradeable = true;
		}
		if (chestLevel <= 4 && paladinArmorScraps >= chestMaterials && lanceKnightSpearTips >= chestMaterials) {
			chestUpgradeable = true;
		}
		if (pantsLevel <= 4 && paladinArmorScraps >= pantsMaterials && lanceKnightSpearTips >= pantsMaterials) {
			pantsUpgradeable = true;
		}
		if (bootsLevel <= 4 && paladinArmorScraps >= bootsMaterials && lanceKnightSpearTips >= bootsMaterials) {
			bootsUpgradeable = true;
		}

	}

	// Old man
	void oldDude(float oldDudeX, float oldDudeY) {
		resourceManager.oldDudeImage.play();

		if (characterXTile < oldManXMiddle) {
			resourceManager.oldDudeImage = resourceManager.oldDudeIdleLeft;
		}
		if (characterXTile > oldManXMiddle) {
			resourceManager.oldDudeImage = resourceManager.oldDudeIdleRight;
		}
		if (openShop == true && action == false) {
			openShop = false;
			loadElements = false;
			currentText = 0;
		}

		if (dist(characterX, characterY, (oldDudeX + 25), (oldDudeY + 50)) < 200 && action == true) {
			resourceManager.oldDudeImage = resourceManager.oldDudePurchase;
			if (openShop == true) {
				loadElements = true;
			} else {
				int chatBoxX = 50;
				int chatBoxY = 600;
				chatBox(chatBoxX, chatBoxY);
				textChat(chatBoxX, chatBoxY, "OldMan");
			}
		}
	}

	// ---------------------------------------------------------------------------------------------------\\

	// Text for the old man

	void textChat(int chatBoxX, int chatBoxY, String person) {
		textFont(textFont);
		int chatYStart = chatBoxY + 50;
		String currentText = "Hey there, if you are reading this, then the code has fucked up \nand whatever I wanted to say isn't actually going to be said. \nYou managed to break the game! Congratulations and fuck you!";
		String blackSmithText = "Hey there, if you are reading this, then the code has fucked up \nand whatever I wanted to say isn't actually going to be said. \nYou managed to break the game! Congratulations and fuck you!";
		if (person == "OldMan") {
			currentText = allTexts(currentText, person);
		}
		if (person == "Blacksmith") {
			currentText = allTexts(blackSmithText, person);
		}
		textAlign(CORNER);
		fill(255);
		text(currentText, chatBoxX + 10, chatYStart);
	}

	String allTexts(String textMessage, String person) {
		if (person == "OldMan") {
			String firstIntroText = "Greetings, I am the Old Dude of the Forest. But you can call me anytime.\nHaha okay enough flirting, I presume you have broken your way \ninto my home for my infamous knowledge of the elements. \nOr you just like to barge into other people's homes for some reason. \nEither way, I can teach you a few things about the elements. For a Price.";
			String secondIntroText = "The elements are powers of nature. They can be used to empower your attacks. \nSince the sundering of the Ajimeri Empire many of the secrets of these abilities \nhave been long forgotten. However, once in a while a child is born with these powers. \nI can sense from your strength that you are one of them.";
			String thirdIntroText = "Though you are a powerful warrior, you have no experience with \nthe power of the elements. \nI am willing to teach you it's secrets and learn to master it. For a price. \nWith the sundering of the Ajimeri Empire most of the psionic power has been \nblasted into the world. \nThese psionic powers have nested in almost everything that lives. \nEmpowering their abilities, but also driving them \nin a rage.";
			String fourthIntroText = "If you wish for me to train you, I require these psionic powers from such entities. \nBring their psionic essence to me, \nand I will trade it with you for knowledge of the elements.";
			String fifthIntroText = "Now that the roleplaying has gotten over with, on to more practical stuff. \nPress the extra button for additional dialogue. I know it's confusing but the guy who \nprogrammed this is partially retarded so cut him some slack. \nRight, so about the 'How to play' part of this well thought out tutorial.";
			String sixthIntroText = "Moving around is done with the keys, you probably already figured that out \notherwise you wouldn't be standing here. \nPressing 'x' causes you to take an 'action'. \nPressing 'a' makes you do a sword attack. \n Pressing 'd' lets you throw out a bolt of energy. \nPressing 'c' opens the menu of elements.";
			String seventhIntroText = "Right, so you probably opened the element selection screen already, \nso press the extra button for explanation of those.";

			String firstElementText = "To unlock explanations of the elements: \nPlease donate a small fee of 5 euro's to the creater of this game. \nThis message has been brought to you by: Electronic Arts. It's in the game.";
			String secondElementText = "Okay just kidding. Alright let me break it down for you, \nit's fairly easy because the programmer is bad at improvising. \nFire: Using fire sets your enemies on fire that deals a DoT. +50 DKP \n Ice: Using ice slows your enemies movement speed. \n Lightning: Using lightning causes an enemy to take and deal more damage.";
			String thirdElementText = "Wind: Using wind causes your energy to move faster. \n Earth: Using earth makes your sword and energy attack deal more damage. \nDark: Using dark grants you a 10% life steal. \n Light: Using light causes you to take less damage, but also deal less. \n Water: Using water is fucking retarded because it does nothing. So don't even bother.";
			if (currentTextSetting == "Introduction") {
				if (currentText == 0) {
					textMessage = firstIntroText;
				} else if (currentText == 1) {
					textMessage = secondIntroText;
				} else if (currentText == 2) {
					textMessage = thirdIntroText;
				} else if (currentText == 3) {
					textMessage = fourthIntroText;
				} else if (currentText == 4) {
					textMessage = fifthIntroText;
				} else if (currentText == 5) {
					textMessage = sixthIntroText;
				} else if (currentText == 6) {
					textMessage = seventhIntroText;
				} else if (currentText == 7) {
					currentText = 6;
				}
			}
			if (currentTextSetting == "ElementExplanation") {
				if (currentText == 0) {
					textMessage = firstElementText;
				}
				if (currentText == 1) {
					textMessage = secondElementText;
				}
				if (currentText == 2) {
					textMessage = thirdElementText;
				}
				if (currentText == 3) {
					currentText = 2;
				}
			}
		}
		if (person == "Blacksmith") {
			String smithFirstMessage = "Hey there stranger, what brings you to these parts? \nI doubt it's the weather or beautiful surroundings. Because there are skeletons and snakes \nthrowing purple shit everywhere. \nI have been living here for a while now, this place used to be similar to the forest over yonder. \nBut alas, the fools of the Ajimeri Empire dabbled with forces beyond their comprehension, \nand the sundering ruined this land.";
			String smithSecondMessage = "But I don't really care though, all I care about is hitting metal to make it dangerous. \nSo if you want your weapons upgraded, or wish to purchase weapons \njust bring the materials and enough essence and I'm your guy.";

			if (currentText == 0) {
				textMessage = smithFirstMessage;
			}
			if (currentText == 1) {
				textMessage = smithSecondMessage;
			}
			if (currentText == 2) {
				currentText = 1;
			}
		}
		return textMessage;

	}

	void chatBox(int chatBoxX, int chatBoxY) {
		fill(60);
		rectMode(CORNER);
		rect(chatBoxX, chatBoxY, 500, 200);
		rectMode(CENTER);
		fill(255);
		rect(nextChatX, nextChatY, 50, 50);
		rect(prevChatX, nextChatY, 50, 50);
		rect(extraChatX, nextChatY, 50, 50);
		rect(shopChatX, nextChatY, 50, 50);
		fill(0);
		textFont(textFont);
		text("Shop", shopChatX - 10, nextChatY);
		text("Next", nextChatX - 10, nextChatY);
		text("Previous", prevChatX - 23, nextChatY);
		text("Extra", extraChatX - 10, nextChatY);

	}

	// ---------------------------------------------------------------------------------------------------\\
	// Keypressed, responsible for multiple actions

	public void keyPressed() {
		int newX = characterX;
		int newY = characterY;
		if (key == 'm') {
			System.out.println("currentLevelNorth = " + currentLevelNorth);
			System.out.println("currentLevelWest = " + currentLevelWest);
		}

		if (key == 'a') {
			if (action == false && swordAttack == true && standingStill == false && ableToAttack == false
					&& millis() < timeUntilCombo && attackDoubleA == false) {
				attackDoubleA = true;
				attackUntil = millis() + attackSpeed * 2 - 400;
			}
			if (ableToAttack == true) {
				action = false;
				swordAttack = true;
				standingStill = false;
				ableToAttack = false;
				attackUntil = millis() + attackSpeed;
				timeUntilCombo = millis() + timeForCombo;
			}
		}

		if (key == 'd') {
			if (ableToAttack == false && swordAttack == true && swordRangeCombo == false && millis() < timeUntilCombo) {
				oldCharacterX = characterX;
				oldCharacterY = characterY;
				swordRangeCombo = true;
				swordAttack = false;
				attackUntil = millis() + attackSpeed + 1000;
				if (arrowToFar == true) {
					arrowRangeCombo = true;
				}
			}
			if (ableToAttack == true && swordRangeCombo == false && swordAttack == false) {
				action = false;
				ableToAttack = false;
				bowAttack = true;
				standingStill = false;
				oldCharacterX = characterX;
				oldCharacterY = characterY;
				attackUntil = millis() + 1000;
				if (arrowToFar == true) {
					arrowToFar = false;
				}
			}
		}
		if (key == 'c') {
			action = false;
			if (loadElements == true) {
				loadElements = false;
			} else if (loadElements == false) {
				loadElements = true;
				loadWeapons = false;
				loadInventory = false;
			}
		}

		if (key == 'i') {
			action = false;
			if (loadInventory == true) {
				loadInventory = false;
			} else if (loadInventory == false) {
				loadInventory = true;
				loadElements = false;
				loadWeapons = false;
			}
		}

		if (key == 'v') {
			action = false;
			if (loadWeapons == true) {
				loadWeapons = false;
				loadInventory = false;
			} else if (loadWeapons == false) {
				loadWeapons = true;
				loadElements = false;
				loadInventory = false;
			}
		}

		if (key == 'x') {
			if (action != true) {
				action = true;
			} else if (action == true) {
				action = false;
			}
		}

		if (key == CODED) {
			if (swordAttack == false && bowAttack == false) {
				if (keyCode == UP) {
					action = false;
					newY -= characterMoveSpeed;
					walkUntil = millis() + 1000;
					walkingDirection = "up";
					standingStill = false;
				}
				if (keyCode == DOWN) {
					action = false;
					newY += characterMoveSpeed;
					walkUntil = millis() + 1000;
					walkingDirection = "down";
					standingStill = false;
				}
				if (keyCode == LEFT) {
					action = false;
					newX -= characterMoveSpeed;
					walkUntil = millis() + 1000;
					walkingDirection = "left";
					prevWalkingDirection = "left";
					standingStill = false;
				}
				if (keyCode == RIGHT) {
					action = false;
					newX += characterMoveSpeed;
					walkUntil = millis() + 1000;
					walkingDirection = "right";
					prevWalkingDirection = "right";
					standingStill = false;
				}
			}

			ArrayList<TileOverlap> steppedOn = determineSteppedOn(newX, newY);
			boolean validTile = true;
			for (TileOverlap overlap : steppedOn) {
				if (overlap.getSurface() > 0) {
					if (overlap.getType() == 'W' || overlap.getType() == 'T' || overlap.getType() == 'a'
							|| overlap.getType() == 'b' || overlap.getType() == 'c' || overlap.getType() == 'B'
							|| overlap.getType() == 'Z' || overlap.getType() == 'X' || overlap.getType() == 'C'
							|| overlap.getType() == 'L') {
						validTile = false;
					}
				}
			}
			if (validTile) {
				characterX = newX;
				characterY = newY;
			}
		}
	}

	// ---------------------------------------------------------------------------------------------------\\
	// Looking at which tile we are on
	char getTileAt(int x, int y) {
		char result = 'G';

		// Collect objects we are on
		ArrayList<TileOverlap> steppedOn = determineSteppedOn(x, y);
		println("-----------------");
		println("Stepped: " + steppedOn);

		// First compact the list by type (sum the surfaces for the same types)
		// Sort the list by type:
		steppedOn.sort(new TileOverlapTypeComparator());
		ArrayList<TileOverlap> compacted = new ArrayList<TileOverlap>();

		TileOverlap previous = null;
		for (TileOverlap overLap : steppedOn) {
			// First we ever see?
			if (previous == null) {
				compacted.add(overLap);
				previous = overLap;
			} else
			// There was a prev one; let's see if the new one is of the same
			// type. If so: ignore the new one and sum
			if (previous.getType() == overLap.getType()) {
				previous.addSurface(overLap.getSurface());
			} else {
				// We have encountered a new type; add it to the list; make sure
				// we update previous
				compacted.add(overLap);
				previous = overLap;
			}
		}
		println("Compacted: " + compacted);

		compacted.sort(new TileOverlapSurfaceComparator());

		println("Stepped sorted by surface: " + compacted);
		if (!compacted.isEmpty()) {

			TileOverlap tile = ((TileOverlap) compacted.get(0));
			if (tile.getSurface() > 0) {
				println("You are on " + tile.getType());
				result = tile.getType();
			}
		}
		return result;
	}

	public ArrayList<TileOverlap> determineSteppedOn(int x, int y) {
		ArrayList<TileOverlap> steppedOn = new ArrayList<TileOverlap>();

		for (GameObject o : objects) {
			if (o.contains(x, y, x + 50, y + 50)) {
				steppedOn.add(new TileOverlap(o, o.overlap(x, y, x + 50, y + 50)));
			}
		}
		return steppedOn;
	}
	// ---------------------------------------------------------------------------------------------------\\

	public boolean isPlayingMusic() {
		return playingMusic;
	}

	public void setPlayingMusic(boolean playingMusic) {
		if (!playingMusic)
			System.err.println();
		this.playingMusic = playingMusic;
	}

	public boolean isLoadedMap() {
		return loadedMap;
	}

	public void setLoadedMap(boolean loadedMap) {
		this.loadedMap = loadedMap;
	}

	static public void main(String[] passedArgs) {
		String[] appletArgs = new String[] { TheBigProject.class.getName() };
		if (passedArgs != null) {
			PApplet.main(concat(appletArgs, passedArgs));
		} else {
			PApplet.main(appletArgs);
		}
	}
}
