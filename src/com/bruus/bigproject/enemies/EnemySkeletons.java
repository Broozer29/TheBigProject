package com.bruus.bigproject.enemies;

import com.bruus.bigproject.TheBigProject;

import gifAnimation.Gif;

public class EnemySkeletons {
	float skeletonPosX;
	float skeletonPosY;
	float skeletonLife;
	float skeletonMaxLife;
	String skeletonDirection;
	float lanceSize = 50;
	float skeletonAttackSpeed = 1000;
	float waitUntilDamage;
	boolean alive;
	boolean aggro;
	boolean skeletonAttacking;
	String skeletonDebuff;
	float skeletonMovementSpeed;
	boolean skeletonAttackDone;
	float maxPsionicEssence;
	boolean dropLoot;
	float experience;
	float actualPsionicEssence;
	TheBigProject theBigProject;
	int timeOfDeath;
	float displayEssence;

	Gif skeletonAnimation;

	public EnemySkeletons(TheBigProject theBigProject, float skeletonPosX, float skeletonPosY, String skeletonDirection,
			float skeletonLife, float skeletonMaxLife, float skeletonMovementSpeed, float maxPsionicEssence,
			float actualPsionicEssence, float experience, boolean dropLoot, boolean alive, boolean aggro, boolean skeletonAttacking, boolean skeletonAttackDone,
			String skeletonDebuff, Gif skeletonAnimation) {
		this.skeletonPosX = skeletonPosX;
		this.skeletonPosY = skeletonPosY;
		this.skeletonDirection = skeletonDirection;
		this.skeletonLife = skeletonLife;
		this.skeletonMaxLife = skeletonMaxLife;
		this.maxPsionicEssence = maxPsionicEssence;
		this.actualPsionicEssence = actualPsionicEssence;
		this.dropLoot = dropLoot;
		this.experience = experience;
		this.skeletonAnimation = skeletonAnimation;
		this.skeletonDebuff = skeletonDebuff;
		this.skeletonMovementSpeed = skeletonMovementSpeed;
		this.alive = alive;
		this.aggro = aggro;
		this.skeletonAttacking = skeletonAttacking;
		this.skeletonAttackDone = skeletonAttackDone;
		this.theBigProject = theBigProject;
	}

	void skeletonAttack() {
		if (waitUntilDamage < theBigProject.millis() && this.skeletonAttacking == true
				&& this.skeletonAttackDone == false) {
			if (this.skeletonDebuff == "Lightning") {
				if (theBigProject.lightElement == true) {
					theBigProject.characterHealth -= 1;
				} else {
					theBigProject.characterHealth -= 2;
				}
			}
			if (theBigProject.lightElement == true) {
				theBigProject.characterHealth -= 0.5;
			} else {
				theBigProject.characterHealth -= 1;
			}
			this.skeletonAttackDone = true;
		}
		if (waitUntilDamage < theBigProject.millis() + skeletonAttackSpeed && this.skeletonAttacking == true
				&& this.skeletonAttackDone == true) {
			this.skeletonAttacking = false;
			this.skeletonAttackDone = true;
		}
	}

	public void displayskeletons() {
		if (this.skeletonLife > 0) {
			float randomNumber = theBigProject.random(0,100);
			if (randomNumber < 10){
				this.dropLoot = true;
			}
			
			if (randomNumber > 10){
				this.dropLoot = false;
			}
			this.actualPsionicEssence = theBigProject.random(this.maxPsionicEssence - 50, this.maxPsionicEssence);
			this.skeletonAnimation.play();
			if (this.alive == true) {
				theBigProject.healthBar(this.skeletonPosX, this.skeletonPosY, this.skeletonLife, skeletonMaxLife);
				if (this.skeletonDirection == "Right") {
					if (TheBigProject.dist(theBigProject.characterX, theBigProject.characterY, this.skeletonPosX,
							this.skeletonPosY) <= lanceSize) {
						if (this.skeletonAttacking == false && this.skeletonAttackDone == true) {
							waitUntilDamage = theBigProject.millis() + skeletonAttackSpeed;
							this.skeletonAttackDone = false;
							this.skeletonAttacking = true;
						}
						this.skeletonAnimation = theBigProject.resourceManager.skeletonRightAttack;
						this.aggro = true;
						skeletonAttack();
					} else {
						this.skeletonAnimation = theBigProject.resourceManager.skeletonRightIdle;
						this.skeletonAttacking = false;
						this.skeletonAttackDone = true;
					}
				}

				if (this.skeletonDirection == "Left") {
					if (TheBigProject.dist(theBigProject.characterX, theBigProject.characterY, this.skeletonPosX,
							this.skeletonPosY) <= lanceSize) {
						if (this.skeletonAttacking == false && this.skeletonAttackDone == true) {
							waitUntilDamage = theBigProject.millis() + skeletonAttackSpeed;
							this.skeletonAttackDone = false;
							this.skeletonAttacking = true;
						}

						this.skeletonAnimation = theBigProject.resourceManager.skeletonLeftAttack;
						this.aggro = true;
						skeletonAttack();
					} else {
						this.skeletonAnimation = theBigProject.resourceManager.skeletonLeftIdle;
						this.skeletonAttacking = false;
						this.skeletonAttackDone = true;
					}
				}

				if (aggro == true) {
					if (this.skeletonPosX > theBigProject.characterX + lanceSize) {
						this.skeletonPosX -= this.skeletonMovementSpeed;
						this.skeletonDirection = "Left";
						this.skeletonAnimation = theBigProject.resourceManager.skeletonLeftWalk;
					}
					if (this.skeletonPosX < theBigProject.characterX - lanceSize) {
						this.skeletonPosX += this.skeletonMovementSpeed;
						this.skeletonDirection = "Right";
						this.skeletonAnimation = theBigProject.resourceManager.skeletonRightWalk;
					}
					if (this.skeletonPosY < theBigProject.characterY) {
						this.skeletonPosY += this.skeletonMovementSpeed;
					}
					if (this.skeletonPosY > theBigProject.characterY) {
						this.skeletonPosY -= this.skeletonMovementSpeed;
					}
				}
			}
		}
		if (this.skeletonLife <= 0) {
			this.skeletonDebuff = "";
			if (this.alive == true) {
				this.skeletonAnimation = theBigProject.resourceManager.deathExplosion;
				theBigProject.deathExplosionTimer = theBigProject.millis() + 1080;
				this.skeletonAnimation.play();
				displayEssence = this.actualPsionicEssence;
				timeOfDeath = theBigProject.millis();
			}
			this.alive = false;
			if (theBigProject.deathExplosionTimer < theBigProject.millis()) {
				this.skeletonAnimation = theBigProject.resourceManager.chestClosed;
				theBigProject.characterExp += this.experience;
				this.experience = 0;
			}

			if (TheBigProject.dist(theBigProject.characterX, theBigProject.characterY, this.skeletonPosX,
					this.skeletonPosY) < 50 && this.skeletonAnimation == theBigProject.resourceManager.chestClosed
					&& theBigProject.action == true) {
				this.skeletonAnimation = theBigProject.resourceManager.chestOpen;
				theBigProject.itemsGained(displayEssence, "Lance Knight Spear tip", this.skeletonPosX, this.skeletonPosY, timeOfDeath);
				theBigProject.playerPsionicEssence = theBigProject.playerPsionicEssence + this.actualPsionicEssence;
				this.actualPsionicEssence = 0;
				if (this.dropLoot == true){
					theBigProject.skeletonBones += 1;
					this.dropLoot = false;
				}
			}
		}
		theBigProject.image(this.skeletonAnimation, this.skeletonPosX, this.skeletonPosY);
		if (this.skeletonDebuff == "Fire") {
			theBigProject.image(theBigProject.resourceManager.fireBurning, this.skeletonPosX, this.skeletonPosY);
			this.skeletonLife -= theBigProject.burningDamage;
		}
	}

	public void swordDamage(float attackDirection, float swordSize, float swordDamage) {
		if (TheBigProject.dist(theBigProject.characterX + (attackDirection - theBigProject.characterX),
				(theBigProject.characterY - (swordSize / 2)), this.skeletonPosX, this.skeletonPosY) < swordSize) {
			if (theBigProject.lightningElement == true) {
				this.skeletonLife = this.skeletonLife - (swordDamage * 1.5f);
			} else {
				this.skeletonLife = this.skeletonLife - swordDamage;
			}

			if (theBigProject.fireElement == true) {
				this.skeletonDebuff = "Fire";
			}
			if (theBigProject.iceElement == true) {
				this.skeletonMovementSpeed = this.skeletonMovementSpeed - theBigProject.iceSlow;
			}
			if (theBigProject.darkElement == true) {
				theBigProject.characterHealth += (swordDamage / 100) * theBigProject.lifeSteal;
			}
			if (theBigProject.lightningElement == true) {
				this.skeletonDebuff = "Lightning";
			}
		}
	}

	public void bowDamage(float bowDamage) {
		if (TheBigProject.dist(theBigProject.arrowX, theBigProject.arrowY, this.skeletonPosX, this.skeletonPosY) < 50
				&& theBigProject.arrowToFar == false) {
			if (theBigProject.lightningElement == true) {
				this.skeletonLife = this.skeletonLife - (bowDamage * theBigProject.lightningDamage);
			} else if (theBigProject.lightElement == true) {
				this.skeletonLife = this.skeletonLife - (bowDamage * theBigProject.lightCharDamage);
			} else {
				this.skeletonLife = this.skeletonLife - bowDamage;
			}
			theBigProject.arrowToFar = true;
			this.aggro = true;
			if (theBigProject.fireElement == true) {
				this.skeletonDebuff = "Fire";
			}
			if (theBigProject.iceElement == true) {
				this.skeletonMovementSpeed = 2.5f;
			}
			if (theBigProject.darkElement == true) {
				theBigProject.characterHealth += (bowDamage / 100) * theBigProject.lifeSteal;
			}
			if (theBigProject.lightningElement == true) {
				this.skeletonDebuff = "Lightning";
			}
		}

		if (theBigProject.arrowX < theBigProject.oldCharacterX - 500
				|| theBigProject.arrowX > theBigProject.oldCharacterX + 500) {
			theBigProject.arrowToFar = true;
		}
	}
}
