package com.bruus.bigproject.enemies;

import com.bruus.bigproject.TheBigProject;

import gifAnimation.Gif;
import processing.core.PApplet;

public class EnemyLanceKnights {
	float knightPosX;
	float knightPosY;
	float knightLife;
	float knightMaxLife;
	String knightDirection;
	float lanceSize = 60;
	float knightAttackSpeed = 2200;
	float waitUntilDamage;
	boolean alive;
	boolean aggro;
	boolean knightAttacking;
	String knightDebuff;
	float knightMovementSpeed;
	boolean knightAttackDone;
	float maxPsionicEssence;
	float actualPsionicEssence;
	float experience;
	TheBigProject theBigProject;
	boolean dropLoot;
	int timeOfDeath;
	float displayEssence;

	Gif knightAnimation;

	public EnemyLanceKnights(TheBigProject theBigProject, float knightPosX, float knightPosY, String knightDirection,
			float knightLife, float knightMaxLife, float knightMovementSpeed, float maxPsionicEssence,
			float actualPsionicEssence, float experience, boolean dropLoot, boolean alive, boolean aggro,
			boolean knightAttacking, boolean knightAttackDone, String knightDebuff, Gif knightAnimation) {
		this.knightPosX = knightPosX;
		this.knightPosY = knightPosY;
		this.knightDirection = knightDirection;
		this.knightLife = knightLife;
		this.knightMaxLife = knightMaxLife;
		this.maxPsionicEssence = maxPsionicEssence;
		this.actualPsionicEssence = actualPsionicEssence;
		this.experience = experience;
		this.dropLoot = dropLoot;
		this.knightAnimation = knightAnimation;
		this.knightDebuff = knightDebuff;
		this.knightMovementSpeed = knightMovementSpeed;
		this.alive = alive;
		this.aggro = aggro;
		this.knightAttacking = knightAttacking;
		this.knightAttackDone = knightAttackDone;
		this.theBigProject = theBigProject;
	}

	void knightAttack() {
		if (waitUntilDamage < theBigProject.millis() && this.knightAttacking == true
				&& this.knightAttackDone == false) {
			if (this.knightDebuff == "Lightning") {
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
			this.knightAttackDone = true;
		}
		if (waitUntilDamage < theBigProject.millis() + knightAttackSpeed && this.knightAttacking == true
				&& this.knightAttackDone == true) {
			this.knightAttacking = false;
			this.knightAttackDone = true;
		}
	}

	public void displayKnights() {
		if (this.knightLife > 0) {
			float randomNumber = theBigProject.random(0, 100);
			if (randomNumber < theBigProject.dropRate) {
				this.dropLoot = true;
			}
			if (randomNumber > theBigProject.dropRate) {
				this.dropLoot = false;
			}
			if (theBigProject.waterElement == true) {
				this.actualPsionicEssence = theBigProject.random(this.maxPsionicEssence - theBigProject.dropRate,
						this.maxPsionicEssence + theBigProject.dropRate);
			} else {
				this.actualPsionicEssence = theBigProject.random(this.maxPsionicEssence - 50, this.maxPsionicEssence);
			}
			this.knightAnimation.play();
			if (this.alive == true) {
				theBigProject.healthBar(this.knightPosX, this.knightPosY, this.knightLife, knightMaxLife);
				if (this.knightDirection == "Right") {
					if (TheBigProject.dist(theBigProject.characterX, theBigProject.characterY, this.knightPosX,
							this.knightPosY) <= lanceSize) {
						if (this.knightAttacking == false && this.knightAttackDone == true) {
							waitUntilDamage = theBigProject.millis() + knightAttackSpeed;
							this.knightAttackDone = false;
							this.knightAttacking = true;
						}
						this.knightAnimation = theBigProject.resourceManager.lanceKnightRightAttack;
						this.aggro = true;
						knightAttack();
					} else {
						this.knightAnimation = theBigProject.resourceManager.lanceKnightRightIdle;
						this.knightAttacking = false;
						this.knightAttackDone = true;
					}
				}

				if (this.knightDirection == "Left") {
					if (TheBigProject.dist(theBigProject.characterX, theBigProject.characterY, this.knightPosX,
							this.knightPosY) <= lanceSize) {
						if (this.knightAttacking == false && this.knightAttackDone == true) {
							waitUntilDamage = theBigProject.millis() + knightAttackSpeed;
							this.knightAttackDone = false;
							this.knightAttacking = true;
						}

						this.knightAnimation = theBigProject.resourceManager.lanceKnightLeftAttack;
						this.aggro = true;
						knightAttack();
					} else {
						this.knightAnimation = theBigProject.resourceManager.lanceKnightLeftIdle;
						this.knightAttacking = false;
						this.knightAttackDone = true;
					}
				}

				if (aggro == true) {
					if (this.knightPosX > theBigProject.characterX + lanceSize) {
						this.knightPosX -= this.knightMovementSpeed;
						this.knightDirection = "Left";
					}
					if (this.knightPosX < theBigProject.characterX - lanceSize) {
						this.knightPosX += this.knightMovementSpeed;
						this.knightDirection = "Right";
					}
					if (this.knightPosY < theBigProject.characterY) {
						this.knightPosY += this.knightMovementSpeed;
					}
					if (this.knightPosY > theBigProject.characterY) {
						this.knightPosY -= this.knightMovementSpeed;
					}
				}
			}
		}
		if (this.knightLife <= 0) {
			this.knightDebuff = "";
			if (this.alive == true) {
				this.knightAnimation = theBigProject.resourceManager.deathExplosion;
				theBigProject.deathExplosionTimer = theBigProject.millis() + 1080;
				this.knightAnimation.play();
				displayEssence = this.actualPsionicEssence;
				timeOfDeath = theBigProject.millis();
			}
			this.alive = false;
			if (theBigProject.deathExplosionTimer < theBigProject.millis()) {
				this.knightAnimation = theBigProject.resourceManager.chestClosed;
				theBigProject.characterExp += this.experience;
				this.experience = 0;
			}

			if (TheBigProject.dist(theBigProject.characterX, theBigProject.characterY, this.knightPosX,
					this.knightPosY) < 50 && this.knightAnimation == theBigProject.resourceManager.chestClosed
					&& theBigProject.action == true) {
				this.knightAnimation = theBigProject.resourceManager.chestOpen;
				theBigProject.itemsGained(displayEssence, "Lance Knight Spear tip", this.knightPosX, this.knightPosY,
						timeOfDeath);
				theBigProject.playerPsionicEssence = theBigProject.playerPsionicEssence + this.actualPsionicEssence;
				this.actualPsionicEssence = 0;
				if (this.dropLoot == true) {
					theBigProject.lanceKnightSpearTips += 1;
					this.dropLoot = false;
				}

			}
		}
		theBigProject.image(this.knightAnimation, this.knightPosX, this.knightPosY);
		if (this.knightDebuff == "Fire") {
			theBigProject.image(theBigProject.resourceManager.fireBurning, this.knightPosX, this.knightPosY);
			this.knightLife -= theBigProject.burningDamage;
		}
	}

	public void swordDamage(float attackDirection, float swordSize, float swordDamage) {
		PApplet.println(attackDirection - theBigProject.characterX, theBigProject.characterY - (swordSize / 2));
		PApplet.println(this.knightPosX, this.knightPosY);
		PApplet.println(theBigProject.characterX, theBigProject.characterY);
		
		if (TheBigProject.dist(theBigProject.characterX + (attackDirection - theBigProject.characterX),
				(theBigProject.characterY - (swordSize / 2)), this.knightPosX, this.knightPosY) < swordSize) {
			
			if (theBigProject.lightningElement == true) {
				this.knightLife = this.knightLife - (swordDamage * 1.5f);
			} else {
				this.knightLife = this.knightLife - swordDamage;
			}

			if (theBigProject.fireElement == true) {
				this.knightDebuff = "Fire";
			}
			if (theBigProject.iceElement == true) {
				this.knightMovementSpeed = this.knightMovementSpeed - theBigProject.iceSlow;
			}
			if (theBigProject.darkElement == true) {
				theBigProject.characterHealth += (swordDamage / 100) * theBigProject.lifeSteal;
			}
			if (theBigProject.lightningElement == true) {
				this.knightDebuff = "Lightning";
			}
		}
	}

	public void bowDamage(float bowDamage) {
		if (TheBigProject.dist(theBigProject.arrowX, theBigProject.arrowY, this.knightPosX, this.knightPosY) < 50
				&& theBigProject.arrowToFar == false) {
			if (theBigProject.lightningElement == true) {
				this.knightLife = this.knightLife - (bowDamage * theBigProject.lightningDamage);
			} else if (theBigProject.lightElement == true) {
				this.knightLife = this.knightLife - (bowDamage * theBigProject.lightCharDamage);
			} else {
				this.knightLife = this.knightLife - bowDamage;
			}
			theBigProject.arrowToFar = true;
			this.aggro = true;
			if (theBigProject.fireElement == true) {
				this.knightDebuff = "Fire";
			}
			if (theBigProject.iceElement == true) {
				this.knightMovementSpeed =  this.knightMovementSpeed - theBigProject.iceSlow;
			}
			if (theBigProject.darkElement == true) {
				theBigProject.characterHealth += (bowDamage / 100) * theBigProject.lifeSteal;
			}
			if (theBigProject.lightningElement == true) {
				this.knightDebuff = "Lightning";
			}
		}

		if (theBigProject.arrowX < theBigProject.oldCharacterX - 500
				|| theBigProject.arrowX > theBigProject.oldCharacterX + 500) {
			theBigProject.arrowToFar = true;
		}
	}
}
