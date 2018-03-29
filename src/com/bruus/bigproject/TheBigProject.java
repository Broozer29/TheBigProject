package com.bruus.bigproject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.bruus.bigproject.enemies.EnemyLanceKnights;
import com.bruus.bigproject.enemies.EnemyPaladins;
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
	ArrayList<EnemyLanceKnights> allKnights;
	ArrayList<EnemyPaladins> allPaladins;

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
	public float playerPsionicEssence = 0;

	// Map variables
	public int tileSize = 50;
	public float currentLevel;
	public String currentZone = "Forest";
	private boolean loadedMap;

	// Variables for Traders House and the Old Man
	public float oldManX = 900;
	public float oldManY = 300;
	public float oldManXMiddle = (oldManX + tileSize / 2) / 50;
	public float oldManYMiddle = (oldManY + tileSize * 2) / 50;
	public boolean enteredHouse = false;

	// Settings for the chat
	public int extraChatX = 365;
	public int prevChatX = 440;
	public int nextChatX = 515;
	public int nextChatY = 765;
	public int currentText = 0;
	public String currentTextSetting = "Introduction";
	public boolean changeExtra = false;

	// Variables for the main character
	public String walkingDirection = "left";
	public String prevWalkingDirection = "left";
	public boolean standingStill = true;
	public boolean swordAttack = false;
	public boolean bowAttack = false;
	public boolean loadElements = false;
	public boolean initialized = false;
	public boolean action = false;

	// Variables for the sound
	private boolean playingMusic = false;

	// Variables for enemies
	public long deathExplosionTimer = 0;
	public long attackUntil = 0;
	public long animationWait = 0;
	public long walkUntil = 0;

	// Variables for attacks
	public float attackDirection = 0;
	public float swordSize = 50;
	public String arrowDirection;
	public float arrowX = 0;
	public float arrowY = 0;
	public float oldCharacterX, oldCharacterY;
	public boolean arrowToFar = true;
	public boolean ableToAttack = true;

	// Variables for text
	public PFont psionicFont;
	public PFont textFont;

	public float elementFireX = 155;
	public float elementLightX = 310;
	public float elementWaterX = 465;
	public float elementDarkX = 620;
	public float elementEarthX = 775;
	public float elementLightningX = 930;
	public float elementWindX = 1085;
	public float elementIceX = 1240;

	public float elementY = 100;
	public float elementResetX = 670;
	public float elementResetY = 700;

	// elementImageFire, elementImageLight, elememtImageWater, elementImageDark,
	// elementImageEarth, elementImageLightning, elementImageWind,
	// elementImageIce;

	// --------------------------------------------------------//
	public float swordDamage = 5;
	public float bowDamage = 50;
	public float burningDamage = 1;
	public long attackSpeed = 2200;
	public float lifeSteal = 10;
	public float windSpeed;
	// --------------------------------------------------------//

	public String firstElement;
	public String secondElement;

	public boolean fireElement = false;
	public boolean iceElement = false;
	public boolean darkElement = false;
	public boolean lightElement = false;
	public boolean earthElement = false;
	public boolean windElement = false;
	public boolean waterElement = false;
	public boolean lightningElement = false;
	public boolean initializeDamage = false;

	public void settings() {
		size(1440, 900);

	}

	public void setup() {
		psionicFont = createFont("Georgia", 20);
		textFont = createFont("Georgia", 12);

		setLoadedMap(false);
		resourceManager.loadImages(this);
		resourceManager.loadMusic(this);

		screenWidth = width;
		screenHeight = height;
		characterX = screenWidth / 2;
		characterY = screenHeight / 2;
		currentLevel = 0;
		frameRate(60);

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
		elementalDamage();
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
				setPlayingMusic(true);
			}

			if (currentZone == "Desert") {
				resourceManager.soundFile = resourceManager.desertMusic;
				setPlayingMusic(true);
			}
			if (currentZone == "TraderHouse") {
				resourceManager.soundFile = resourceManager.traderHouseMusic;
				setPlayingMusic(true);
			}
			resourceManager.soundFile.amp(0.5f);
			resourceManager.soundFile.play();
		}
	}
	// ---------------------------------------------------------------------------------------------------\\
	// Enemies

	// knightPosX, knightPosY, knightDirection, knightLife, knightMaxLife,
	// knightMovementSpeed, maxPsionicEssence, actualPsionicEssence, alive,
	// aggro, knightAttacking, knightAttackDone, knightDebuff, knightAnimation
	// Float, Float, String, Float, Float, Float, Float, Float, Boolean,
	// Boolean, Boolean, Boolean, String, Gif
	void drawEnemies() {
		for (EnemyLanceKnights temp : allKnights) {
			temp.displayKnights();
			temp.bowDamage();
			if (swordAttack == true) {
				temp.swordDamage(attackDirection, swordSize);
			}
		}
		for (EnemyPaladins temp : allPaladins) {
			temp.displayPaladins();
			temp.bowDamage();
			if (swordAttack == true) {
				temp.swordDamage(attackDirection, swordSize);
			}
		}
	}

	void clearEnemies() {
		allKnights.clear();
		allPaladins.clear();
	}

	void createEnemies() {
		if (currentLevel == 0 && currentZone == "Forest") {
			allKnights = new ArrayList<>();
			allPaladins = new ArrayList<>();
			EnemyLanceKnights firstKnight = new EnemyLanceKnights(this, 600, 600, "Right", 200, 200, 5, 50, 0, true,
					false, false, true, "", resourceManager.lanceKnightRightIdle);
			allKnights.add(firstKnight);
			EnemyLanceKnights secondKnight = new EnemyLanceKnights(this, 500, 500, "Right", 100, 100, 5, 50, 0, true,
					false, false, true, "", resourceManager.lanceKnightRightIdle);
			allKnights.add(secondKnight);
			EnemyLanceKnights thirdKnight = new EnemyLanceKnights(this, 400, 400, "Right", 300, 300, 5, 50, 0, true,
					false, false, true, "", resourceManager.lanceKnightRightIdle);
			allKnights.add(thirdKnight);
			EnemyPaladins firstPaladin = new EnemyPaladins(this, 800, 600, "Right", 200, 200, 5, 100, 0, true, false,
					false, true, "", resourceManager.paladinRightIdle);
			allPaladins.add(firstPaladin);
		}
		if (currentLevel == 1 && currentZone == "Forest") {
			EnemyLanceKnights firstKnight = new EnemyLanceKnights(this, 200, 200, "Right", 100, 100, 5, 50, 0, true,
					false, false, false, "", resourceManager.lanceKnightRightIdle);
			allKnights.add(firstKnight);
		}
	}

	public void healthBar(float posX, float posY, float knightLife, float knightMaxLife) {
		rectMode(CORNER);
		float factor = knightMaxLife / 40;
		fill(255, 0, 0);
		rect(posX, posY, 40, 5);
		fill(0, 255, 0);
		rect(posX, posY, knightLife / factor, 5);
	}

	// ---------------------------------------------------------------------------------------------------\\
	// Character \\

	void character() {
		lookingDirection();
		walking();
		swordAttack();
		bowAttack();
		characterHealth();
		image(resourceManager.currentAnimation, characterX, characterY);
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
		text("Psionic Essence: ", 20, 80);
		text(playerPsionicEssence, 200, 80);

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
		if (standingStill == false && swordAttack == false) {
			if (walkingDirection == "left") {
				resourceManager.currentAnimation = resourceManager.walkingLeft;
			}
			if (walkingDirection == "right") {
				resourceManager.currentAnimation = resourceManager.walkingRight;
			}
			if (walkingDirection == "up" || walkingDirection == "down") {
				if (prevWalkingDirection == "left") {
					resourceManager.currentAnimation = resourceManager.walkingLeft;
				}
				if (prevWalkingDirection == "right") {
					resourceManager.currentAnimation = resourceManager.walkingRight;
				}
			}
		}
		if (walkUntil < millis() && swordAttack == false) {
			standingStill = true;
		}
	}

	void lookingDirection() {
		if (standingStill == true && swordAttack == false) {
			if (walkingDirection == "up" || walkingDirection == "down") {
				if (prevWalkingDirection == "left") {
					resourceManager.currentAnimation = resourceManager.lookingLeft;
				}
				if (prevWalkingDirection == "right") {
					resourceManager.currentAnimation = resourceManager.lookingRight;
				}
			}
			if (walkingDirection == "left") {
				resourceManager.currentAnimation = resourceManager.lookingLeft;
			}
			if (walkingDirection == "right") {
				resourceManager.currentAnimation = resourceManager.lookingRight;
			}
		}
	}

	void swordAttack() {
		int attackDirectionLeft = 20;
		int attackDirectionRight = 30;
		if (swordAttack == true) {
			if (walkingDirection == "left") {
				resourceManager.currentAnimation = resourceManager.attackLeft;
				attackDirection = characterX - attackDirectionLeft;
			}
			if (walkingDirection == "right") {
				resourceManager.currentAnimation = resourceManager.attackRight;
				attackDirection = characterX + attackDirectionRight;
			}
			if (walkingDirection == "up" || walkingDirection == "down") {
				if (prevWalkingDirection == "left") {
					resourceManager.currentAnimation = resourceManager.attackLeft;
					attackDirection = characterX - attackDirectionLeft;
				}
				if (prevWalkingDirection == "right") {
					resourceManager.currentAnimation = resourceManager.attackRight;
					attackDirection = characterX + attackDirectionRight;
				}
			}
		}
		keepCounting();
	}

	void bowAttack() {
		if (arrowToFar == false) {
			image(resourceManager.projectileSaber, arrowX, arrowY);
		}

		int bowAttackDirectionLeft = 20;
		int bowAttackDirectionRight = 70;

		if (arrowDirection == "right" && arrowToFar == false) {
			resourceManager.projectileSaber = resourceManager.projectileRight;
			arrowX += 10 + windSpeed;
		}
		if (arrowDirection == "left" && arrowToFar == false) {
			arrowX -= 10 + windSpeed;
			resourceManager.projectileSaber = resourceManager.projectileLeft;
		}

		if (bowAttack == true) {
			arrowY = characterY;
			if (walkingDirection == "left") {
				resourceManager.currentAnimation = resourceManager.attackBowLeft;
				arrowDirection = "left";
				arrowX = characterX - bowAttackDirectionLeft;
			}
			if (walkingDirection == "right") {
				resourceManager.currentAnimation = resourceManager.attackBowRight;
				arrowDirection = "right";
				arrowX = characterX + bowAttackDirectionRight;
			}
			if (walkingDirection == "up" || walkingDirection == "down") {
				if (prevWalkingDirection == "left") {
					resourceManager.currentAnimation = resourceManager.attackBowLeft;
					arrowDirection = "left";
					arrowX = characterX - bowAttackDirectionLeft;
				}
				if (prevWalkingDirection == "right") {
					resourceManager.currentAnimation = resourceManager.attackBowLeft;
					arrowDirection = "right";
					arrowX = characterX + bowAttackDirectionRight;
				}
			}
		}
		if (arrowX < oldCharacterX - 500 || arrowX > oldCharacterX + 500) {
			arrowToFar = true;
		}
		resourceManager.projectileSaber.play();
	}

	void keepCounting() {
		if (attackUntil < millis()) {
			swordAttack = false;
			bowAttack = false;
			ableToAttack = true;
		}
	}

	// ---------------------------------------------------------------------------------------------------\\
	// Levels

	void levels() {
		leavingZone();
		if (currentLevel == 0 && currentZone == "Forest") {
			starterZone();
			playMusic();
		}
		if (currentLevel == 1 && currentZone == "Forest") {
			forestZoneOne();
		}
		if (currentLevel == 1 && currentZone == "Desert") {
			desertZoneOne();
			playMusic();
		}
		if (currentLevel == 0 && currentZone == "TraderHouse") {
			traderHouse();
			playMusic();
		}
	}

	void leavingZone() {
		if (characterX < 0) {
			currentLevel++;
			characterX = screenWidth;
			clearEnemies();
			setLoadedMap(false);
		}

		if (characterX > screenWidth) {
			currentLevel--;
			characterX = 0;
			clearEnemies();
			setLoadedMap(false);
		}

		if (characterY < 0) {
			if (currentLevel == 0 && currentZone == "Forest") {
				currentZone = "Desert";
			}
			currentLevel++;
			characterY = screenHeight;
			clearEnemies();
			setLoadedMap(false);
		}

		if (characterY > screenHeight) {
			if (currentLevel == 1 && currentZone == "Desert") {
				currentZone = "Forest";
			}
			currentLevel--;
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
		int locationTradeHouseX = 500;
		int locationTradeHouseY = 0;
		if (!isLoadedMap()) {
			resourceManager.soundFile.stop();
			setPlayingMusic(false);
			createEnemies();
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

	void forestZoneOne() {
		if (!isLoadedMap()) {
			MapLoader loader = new MapLoader(this);
			try {
				objects = loader.loadTiles(baseFolder + "/ForestZones/forestZoneOne.txt");
			} catch (IOException e) {
				println("Sorry, kon map niet laden: " + e.getMessage());
			}
			createEnemies();
			setLoadedMap(true);
		}
	}

	void desertZoneOne() {
		if (!isLoadedMap()) {
			resourceManager.soundFile.stop();
			setPlayingMusic(false);
			MapLoader loader = new MapLoader(this);
			try {
				objects = loader.loadTiles(baseFolder + "/DesertZones/DesertZoneOne.txt");
			} catch (IOException e) {
				println("Sorry, kon map niet laden: " + e.getMessage());
			}
			createEnemies();
			setLoadedMap(true);
		}
	}

	void traderHouse() {
		float traderHouseDoorX = 1200;
		float traderHouseDoorY = 300;
		if (!isLoadedMap()) {
			// resourceManager.soundFile.stop();
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
	void elementalDamage() {
		elementSelection();
		damageCalculator();
		resourceManager.fireBurning.play();
	}

	void damageCalculator() {
		if (initializeDamage == true) {
			// Earth does more damage
			if (firstElement == "Earth" || secondElement == "Earth") {
				swordDamage = swordDamage + (swordDamage / 3);
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
				swordDamage = swordDamage - (swordDamage / 4);
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
				waterElement = true;
			}
			initializeDamage = false;
		}
	}

	void elementSelection() {
		if (loadElements == true) {
			image(resourceManager.elementImageFire, elementFireX, elementY);
			image(resourceManager.elementImageIce, elementIceX, elementY);
			image(resourceManager.elementImageLight, elementLightX, elementY);
			image(resourceManager.elememtImageWater, elementWaterX, elementY);
			image(resourceManager.elementImageDark, elementDarkX, elementY);
			image(resourceManager.elementImageEarth, elementEarthX, elementY);
			image(resourceManager.elementImageWind, elementWindX, elementY);
			image(resourceManager.elementImageLightning, elementLightningX, elementY);
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

	void resetDamageValues() {
		windSpeed = 0;
		swordDamage = 5;
		bowDamage = 50;
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
		// Chat gedeelte

		if (dist(mouseX, mouseY, nextChatX, nextChatY) < 50 && action == true) {
			currentText++;
			if (currentText == 4 && currentTextSetting == "Introduction") {
				currentText = 0;
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

		if (loadElements == true) {
			if (dist(mouseX, mouseY, elementFireX + tileSize, elementY + tileSize) < tileSize) {
				if (firstElement == null && firstElement != "Fire") {
					firstElement = "Fire";
					initializeDamage = true;
				}
				if (firstElement != null && firstElement != "Fire" && secondElement == null
						&& secondElement != "Fire") {
					secondElement = "Fire";
					initializeDamage = true;
				}
			}
			if (dist(mouseX, mouseY, elementIceX + tileSize, elementY + tileSize) < tileSize) {
				if (firstElement == null && firstElement != "Ice") {
					firstElement = "Ice";
					initializeDamage = true;
				}
				if (firstElement != null && firstElement != "Ice" && secondElement == null && secondElement != "Ice") {
					secondElement = "Ice";
					initializeDamage = true;
				}
			}
			if (dist(mouseX, mouseY, elementLightX + tileSize, elementY + tileSize) < tileSize) {
				if (firstElement == null && firstElement != "Light") {
					firstElement = "Light";
					initializeDamage = true;
				}
				if (firstElement != null && firstElement != "Light" && secondElement == null
						&& secondElement != "Light") {
					secondElement = "Light";
					initializeDamage = true;
				}
			}
			if (dist(mouseX, mouseY, elementWaterX + tileSize, elementY + tileSize) < tileSize) {
				if (firstElement == null && firstElement != "Water") {
					firstElement = "Water";
					initializeDamage = true;
				} else if (firstElement != null && firstElement != "Water" && secondElement == null
						&& secondElement != "Water") {
					secondElement = "Water";
					initializeDamage = true;
				}
			}
			if (dist(mouseX, mouseY, elementDarkX + tileSize, elementY + tileSize) < tileSize) {
				if (firstElement == null && firstElement != "Dark") {
					firstElement = "Dark";
					initializeDamage = true;
				}
				if (firstElement != null && firstElement != "Dark" && secondElement == null
						&& secondElement != "Dark") {
					secondElement = "Dark";
					initializeDamage = true;
				}
			}
			if (dist(mouseX, mouseY, elementEarthX + tileSize, elementY + tileSize) < tileSize) {
				if (firstElement == null && firstElement != "Earth") {
					firstElement = "Earth";
					initializeDamage = true;
				}
				if (firstElement != null && firstElement != "Earth" && secondElement == null
						&& secondElement != "Earth") {
					secondElement = "Earth";
					initializeDamage = true;
				}
			}
			if (dist(mouseX, mouseY, elementWindX + tileSize, elementY + tileSize) < tileSize) {
				if (firstElement == null && firstElement != "Wind") {
					firstElement = "Wind";
					initializeDamage = true;
				}
				if (firstElement != null && firstElement != "Wind" && secondElement == null
						&& secondElement != "Wind") {
					secondElement = "Wind";
					initializeDamage = true;
				}
			}
			if (dist(mouseX, mouseY, elementLightningX + tileSize, elementY + tileSize) < tileSize) {
				if (firstElement == null && firstElement != "Lightning") {
					firstElement = "Lightning";
					initializeDamage = true;
				}
				if (firstElement != null && firstElement != "Lightning" && secondElement == null
						&& secondElement != "Lightning") {
					secondElement = "Lightning";
					initializeDamage = true;
				}
			}
			if (dist(mouseX, mouseY, elementResetX + tileSize, elementResetY + tileSize) < tileSize) {
				firstElement = null;
				secondElement = null;
				initializeDamage = true;
				resetDamageValues();
			}
		}
	}

	// ---------------------------------------------------------------------------------------------------\\
	// Old man
	void oldDude(float oldDudeX, float oldDudeY) {
		if (characterXTile < oldManXMiddle) {
			resourceManager.oldDudeImage = resourceManager.oldDudeLeft;
		}
		if (characterXTile > oldManXMiddle) {
			resourceManager.oldDudeImage = resourceManager.oldDudeRight;
		}

		if (characterXTile < oldManXMiddle) {
			if (characterYTile < oldManYMiddle - 2) {
				resourceManager.oldDudeImage = resourceManager.oldDudeLeftUp;
			}
			if (characterYTile > oldManYMiddle - 2) {
				resourceManager.oldDudeImage = resourceManager.oldDudeLeftDown;
			}
		}
		if (characterXTile > oldManXMiddle) {
			if (characterYTile < oldManYMiddle - 2) {
				resourceManager.oldDudeImage = resourceManager.oldDudeRightUp;
			}
			if (characterYTile > oldManYMiddle - 2) {
				resourceManager.oldDudeImage = resourceManager.oldDudeRightDown;
			}
		}

		if (characterXTile == oldManXMiddle - 0.5) {
			if (characterYTile < oldManYMiddle - 2) {
				resourceManager.oldDudeImage = resourceManager.oldDudeUp;
			}
			if (characterYTile > oldManYMiddle - 2) {
				resourceManager.oldDudeImage = resourceManager.oldDudeDown;
			}
		}

		if (dist(characterX, characterY, (oldDudeX + 25), (oldDudeY + 50)) < 200 && action == true) {
			int chatBoxX = 50;
			int chatBoxY = 600;

			chatBox(chatBoxX, chatBoxY);
			textChat(chatBoxX, chatBoxY);
		}
	}

	// ---------------------------------------------------------------------------------------------------\\
	// Text for the old man

	void textChat(int chatBoxX, int chatBoxY) {
		textFont(textFont);
		int chatYStart = chatBoxY + 50;
		String oldDudeText = "Hey there, if you are reading this, then the code has fucked up \nand whatever I wanted to say isn't actually going to be said. \nYou managed to break the game! Congratulations and fuck you!";
		String currentText = allTexts(oldDudeText);
		textAlign(CORNER);
		fill(255);
		text(currentText, chatBoxX + 10, chatYStart);
	}

	String allTexts(String oldDudeText) {

		String firstIntroText = "Greetings, I am the Old Dude of the Forest. But you can call me anytime.\nHaha okay enough flirting, I presume you have broken your way \ninto my home for my infamous knowledge of the elements. \nOr you just like to barge into other people's homes for some reason. \nEither way, I can teach you a few things about the elements. For a Price.";
		String secondIntroText = "The elements are powers of nature. They can be used to empower your attacks. \nSince the sundering of the Ajimeri Empire many of the secrets of these abilities \nhave been long forgotten. However, once in a while a child is born with these powers. \nI can sense from your strength that you are one of them.";
		String thirdIntroText = "Though you are a powerful warrior, you have no experience with \nthe power of the elements. \nI am willing to teach you it's secrets and learn to master it. For a price. \nWith the sundering of the Ajimeri Empire most of the psionic power has been \nblasted into the world. \nThese psionic powers have nested in almost everything that lives. \nEmpowering their abilities, but also driving them \nin a rage.";
		String fourthIntroText = "If you wish for me to train you, I require these psionic powers from such entities. \nBring their psionic essence to me, \nand I will trade it with you for knowledge of the elements.";
		String firstElementText = "EEEUGHGHGHG";

		if (currentTextSetting == "Introduction") {
			if (currentText == 0) {
				oldDudeText = firstIntroText;
			} else if (currentText == 1) {
				oldDudeText = secondIntroText;
			} else if (currentText == 2) {
				oldDudeText = thirdIntroText;
			} else if (currentText == 3) {
				oldDudeText = fourthIntroText;
			}
		}
		if (currentTextSetting == "ElementExplanation") {
			if (currentText == 0) {
				oldDudeText = firstElementText;
			}
		}

		return oldDudeText;
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
		fill(0);
		textFont(textFont);
		text("Next", nextChatX - 10, nextChatY);
		text("Previous", prevChatX - 23, nextChatY);
		text("Extra", extraChatX - 10, nextChatY);

	}

	static public void main(String[] passedArgs) {
		String[] appletArgs = new String[] { TheBigProject.class.getName() };
		if (passedArgs != null) {
			PApplet.main(concat(appletArgs, passedArgs));
		} else {
			PApplet.main(appletArgs);
		}
	}

	// ---------------------------------------------------------------------------------------------------\\
	// Keypressed, responsible for multiple actions

	public void keyPressed() {
		int newX = characterX;
		int newY = characterY;

		if (key == 'a' && ableToAttack == true) {
			action = false;
			swordAttack = true;
			standingStill = false;
			ableToAttack = false;
			attackUntil = millis() + attackSpeed;
		}

		if (key == 'd' && ableToAttack == true) {
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
		if (key == 'c') {
			action = false;
			if (loadElements == true) {
				loadElements = false;
			} else if (loadElements == false) {
				loadElements = true;
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
					newY -= 10;
					walkUntil = millis() + 1000;
					walkingDirection = "up";
					standingStill = false;
				}
				if (keyCode == DOWN) {
					action = false;
					newY += 10;
					walkUntil = millis() + 1000;
					walkingDirection = "down";
					standingStill = false;
				}
				if (keyCode == LEFT) {
					action = false;
					newX -= 10;
					walkUntil = millis() + 1000;
					walkingDirection = "left";
					prevWalkingDirection = "left";
					standingStill = false;
				}
				if (keyCode == RIGHT) {
					action = false;
					newX += 10;
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
							|| overlap.getType() == 'b' || overlap.getType() == 'c' || overlap.getType() == 'B')
						validTile = false;
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
}
