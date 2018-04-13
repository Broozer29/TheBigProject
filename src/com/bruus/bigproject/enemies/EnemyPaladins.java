package com.bruus.bigproject.enemies;

import com.bruus.bigproject.TheBigProject;

import gifAnimation.Gif;

public class EnemyPaladins {
	float paladinPosX;
	float paladinPosY;
	float paladinLife;
	float paladinMaxLife;
	String paladinDirection;
	float paladinLanceSize = 80;
	float paladinAttackSpeed = 1700;
	float paladinDamage = 10;
	float waitUntilDamage;
	boolean alive;
	boolean aggro;
	boolean paladinAttacking;
	String paladinDebuff;
	float paladinMovementSpeed;
	boolean paladinAttackDone;
	float maxPsionicEssence;
	boolean dropLoot;
	float experience;
	float actualPsionicEssence;
	TheBigProject theBigProject;
	int timeOfDeath;
	float displayEssence;
	
	Gif paladinAnimation;

	public EnemyPaladins(TheBigProject theBigProject, float paladinPosX, float paladinPosY, String paladinDirection,
			float paladinLife, float paladinMaxLife, float paladinMovementSpeed, float maxPsionicEssence,
			float actualPsionicEssence, float experience, boolean dropLoot, boolean alive, boolean aggro, boolean paladinAttacking,
			boolean paladinAttackDone, String paladinDebuff, Gif paladinAnimation) {
		this.paladinPosX = paladinPosX;
		this.paladinPosY = paladinPosY;
		this.paladinDirection = paladinDirection;
		this.paladinLife = paladinLife;
		this.paladinMaxLife = paladinMaxLife;
		this.maxPsionicEssence = maxPsionicEssence;
		this.actualPsionicEssence = actualPsionicEssence;
		this.dropLoot = dropLoot;
		this.experience = experience;
		this.paladinAnimation = paladinAnimation;
		this.paladinDebuff = paladinDebuff;
		this.paladinMovementSpeed = paladinMovementSpeed;
		this.alive = alive;
		this.aggro = aggro;
		this.paladinAttacking = paladinAttacking;
		this.paladinAttackDone = paladinAttackDone;
		this.theBigProject = theBigProject;
	}

	void paladinAttack() {
		float randomNumber = theBigProject.random(0,100);
		if (randomNumber < theBigProject.dropRate){
			this.dropLoot = true;
		}
		
		if (randomNumber > theBigProject.dropRate){
			this.dropLoot = false;
		}
		if (waitUntilDamage < theBigProject.millis() && this.paladinAttacking == true
				&& this.paladinAttackDone == false) {
			if (this.paladinDebuff == "Lightning") {
				if (theBigProject.lightElement == true) {
					theBigProject.characterHealth -= paladinDamage / 2;
				} else {
					theBigProject.characterHealth -= paladinDamage * 0.5;
				}
			}
			if (theBigProject.lightElement == true) {
				theBigProject.characterHealth -= paladinDamage / 2;
			} else {
				theBigProject.characterHealth -= paladinDamage;
			}
			this.paladinAttackDone = true;
			waitUntilDamage = theBigProject.millis();
		}
		if (waitUntilDamage < (theBigProject.millis() + paladinAttackSpeed + 5600) && this.paladinAttacking == true
				&& this.paladinAttackDone == true) {
			this.paladinAttacking = false;
			this.paladinAttackDone = true;
		}
	}

	public void displayPaladins() {
		if (this.paladinLife > 0) {
			this.actualPsionicEssence = theBigProject.random(this.maxPsionicEssence - 50, this.maxPsionicEssence);
			this.paladinAnimation.play();
			if (this.alive == true) {
				theBigProject.healthBar(this.paladinPosX, this.paladinPosY, this.paladinLife, this.paladinMaxLife);
				if (this.paladinDirection == "Right") {
					if (TheBigProject.dist(theBigProject.characterX, theBigProject.characterY, this.paladinPosX,
							this.paladinPosY) <= paladinLanceSize) {
						if (this.paladinAttacking == false && this.paladinAttackDone == true) {
							waitUntilDamage = theBigProject.millis() + paladinAttackSpeed;
							this.paladinAttackDone = false;
							this.paladinAttacking = true;
						}
						this.paladinAnimation = theBigProject.resourceManager.paladinRightAttack;
						this.aggro = true;
						paladinAttack();
					} else {
						this.paladinAnimation = theBigProject.resourceManager.paladinRightIdle;
						this.paladinAttacking = false;
						this.paladinAttackDone = true;
					}
				}

				if (this.paladinDirection == "Left") {
					if (TheBigProject.dist(theBigProject.characterX, theBigProject.characterY, this.paladinPosX,
							this.paladinPosY) <= paladinLanceSize) {
						if (this.paladinAttacking == false && this.paladinAttackDone == true) {
							waitUntilDamage = theBigProject.millis() + paladinAttackSpeed;
							this.paladinAttackDone = false;
							this.paladinAttacking = true;
						}

						this.paladinAnimation = theBigProject.resourceManager.paladinLeftAttack;
						this.aggro = true;
						paladinAttack();
					} else {
						this.paladinAnimation = theBigProject.resourceManager.paladinLeftIdle;
						this.paladinAttacking = false;
						this.paladinAttackDone = true;
					}
				}
				if (aggro == true) {
					if (this.paladinPosX > theBigProject.characterX + paladinLanceSize) {
						this.paladinPosX -= this.paladinMovementSpeed;
						this.paladinDirection = "Left";
						this.paladinAnimation = theBigProject.resourceManager.paladinLeftWalk;
					}
					if (this.paladinPosX < theBigProject.characterX - paladinLanceSize) {
						this.paladinPosX += this.paladinMovementSpeed;
						this.paladinDirection = "Right";
						this.paladinAnimation = theBigProject.resourceManager.paladinRightWalk;
					}
					if (this.paladinPosY < theBigProject.characterY) {
						this.paladinPosY += this.paladinMovementSpeed;
					}
					if (this.paladinPosY > theBigProject.characterY) {
						this.paladinPosY -= this.paladinMovementSpeed;
					}
				}
			}
		}

		if (this.paladinLife <= 0) {
			this.paladinDebuff = "";
			if (this.alive == true) {
				this.paladinAnimation = theBigProject.resourceManager.deathExplosion;
				theBigProject.deathExplosionTimer = theBigProject.millis() + 1080;
				this.paladinAnimation.play();
				displayEssence = this.actualPsionicEssence;
				timeOfDeath = theBigProject.millis();
			}
			this.alive = false;
			if (theBigProject.deathExplosionTimer < theBigProject.millis()) {
				this.paladinAnimation = theBigProject.resourceManager.chestClosed;
				theBigProject.characterExp += this.experience;
				this.experience = 0;
			}
			if (TheBigProject.dist(theBigProject.characterX, theBigProject.characterY, this.paladinPosX,
					this.paladinPosY) < 50 && this.paladinAnimation == theBigProject.resourceManager.chestClosed
					&& theBigProject.action == true) {
				this.paladinAnimation = theBigProject.resourceManager.chestOpen;
				theBigProject.itemsGained(displayEssence, "Paladin Armor Scraps", this.paladinPosX, this.paladinPosY, timeOfDeath);
				theBigProject.playerPsionicEssence = theBigProject.playerPsionicEssence + this.actualPsionicEssence;
				this.actualPsionicEssence = 0;
				if (this.dropLoot == true){
					theBigProject.paladinArmorScraps += 1;
					this.dropLoot = false;
				}
			}
		}

		theBigProject.image(this.paladinAnimation, this.paladinPosX, this.paladinPosY);
		if (this.paladinDebuff == "Fire") {
			theBigProject.image(theBigProject.resourceManager.fireBurning, this.paladinPosX, this.paladinPosY);
			this.paladinLife -= theBigProject.burningDamage;
		}
	}

	public void swordDamage(float attackDirection, float swordSize, float swordDamage) {
		if (TheBigProject.dist(theBigProject.characterX + (attackDirection - theBigProject.characterX),
				(theBigProject.characterY - (swordSize / 2)), this.paladinPosX, this.paladinPosY) < swordSize) {
			if (theBigProject.lightningElement == true) {
				this.paladinLife = this.paladinLife - (swordDamage * 1.5f);
			} else {
				this.paladinLife = this.paladinLife - swordDamage;
			}

			if (theBigProject.fireElement == true) {
				this.paladinDebuff = "Fire";
			}
			if (theBigProject.iceElement == true) {
				this.paladinMovementSpeed = this.paladinMovementSpeed - theBigProject.iceSlow;
			}
			if (theBigProject.darkElement == true) {
				theBigProject.characterHealth += (swordDamage / 100) * theBigProject.lifeSteal;
			}
			if (theBigProject.lightningElement == true) {
				this.paladinDebuff = "Lightning";
			}
		}
	}

	public void bowDamage(float bowDamage) {
		if (TheBigProject.dist(theBigProject.arrowX, theBigProject.arrowY, this.paladinPosX, this.paladinPosY) < 50
				&& theBigProject.arrowToFar == false) {

			if (theBigProject.lightningElement == true) {
				this.paladinLife = this.paladinLife - (bowDamage * theBigProject.lightningDamage);
			} else if (theBigProject.lightElement == true) {
				this.paladinLife = this.paladinLife - (bowDamage * theBigProject.lightCharDamage);
			} else {
				this.paladinLife = this.paladinLife - bowDamage;
			}
			theBigProject.arrowToFar = true;
			this.aggro = true;
			if (theBigProject.fireElement == true) {
				this.paladinDebuff = "Fire";
			}
			if (theBigProject.iceElement == true) {
				this.paladinMovementSpeed = 2.5f;
			}
			if (theBigProject.darkElement == true) {
				theBigProject.characterHealth += (bowDamage / 100) * theBigProject.lifeSteal;
			}
			if (theBigProject.lightningElement == true) {
				this.paladinDebuff = "Lightning";
			}
		}

		if (theBigProject.arrowX < theBigProject.oldCharacterX - 500
				|| theBigProject.arrowX > theBigProject.oldCharacterX + 500) {
			theBigProject.arrowToFar = true;
		}
	}
}
