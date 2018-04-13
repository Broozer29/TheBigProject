package com.bruus.bigproject.enemies;

import com.bruus.bigproject.TheBigProject;

import gifAnimation.Gif;

public class EnemyScorpions {
	float scorpionPosX;
	float scorpionPosY;
	float scorpionLife;
	float scorpionMaxLife;
	String scorpionDirection;
	float lanceSize = 60;
	float scorpionAttackSpeed = 2200;
	float waitUntilDamage;
	boolean alive;
	boolean aggro;
	boolean scorpionAttacking;
	String scorpionDebuff;
	float scorpionMovementSpeed;
	boolean scorpionAttackDone;
	float maxPsionicEssence;
	float actualPsionicEssence;
	float experience;
	TheBigProject theBigProject;
	boolean dropLoot;
	int timeOfDeath;
	float displayEssence;

	Gif scorpionAnimation;

	public EnemyScorpions(TheBigProject theBigProject, float scorpionPosX, float scorpionPosY, String scorpionDirection,
			float scorpionLife, float scorpionMaxLife, float scorpionMovementSpeed, float maxPsionicEssence,
			float actualPsionicEssence, float experience, boolean dropLoot, boolean alive, boolean aggro,
			boolean scorpionAttacking, boolean scorpionAttackDone, String scorpionDebuff, Gif scorpionAnimation) {
		this.scorpionPosX = scorpionPosX;
		this.scorpionPosY = scorpionPosY;
		this.scorpionDirection = scorpionDirection;
		this.scorpionLife = scorpionLife;
		this.scorpionMaxLife = scorpionMaxLife;
		this.maxPsionicEssence = maxPsionicEssence;
		this.actualPsionicEssence = actualPsionicEssence;
		this.experience = experience;
		this.dropLoot = dropLoot;
		this.scorpionAnimation = scorpionAnimation;
		this.scorpionDebuff = scorpionDebuff;
		this.scorpionMovementSpeed = scorpionMovementSpeed;
		this.alive = alive;
		this.aggro = aggro;
		this.scorpionAttacking = scorpionAttacking;
		this.scorpionAttackDone = scorpionAttackDone;
		this.theBigProject = theBigProject;
	}

	void scorpionAttack() {
		if (waitUntilDamage < theBigProject.millis() && this.scorpionAttacking == true
				&& this.scorpionAttackDone == false) {
			if (this.scorpionDebuff == "Lightning") {
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
			this.scorpionAttackDone = true;
		}
		if (waitUntilDamage < theBigProject.millis() + scorpionAttackSpeed && this.scorpionAttacking == true
				&& this.scorpionAttackDone == true) {
			this.scorpionAttacking = false;
			this.scorpionAttackDone = true;
		}
	}

	public void displayscorpions() {
		if (this.scorpionLife > 0) {
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
			this.scorpionAnimation.play();
			if (this.alive == true) {
				theBigProject.healthBar(this.scorpionPosX, this.scorpionPosY, this.scorpionLife, scorpionMaxLife);
				if (this.scorpionDirection == "Right") {
					if (TheBigProject.dist(theBigProject.characterX, theBigProject.characterY, this.scorpionPosX,
							this.scorpionPosY) <= lanceSize) {
						if (this.scorpionAttacking == false && this.scorpionAttackDone == true) {
							waitUntilDamage = theBigProject.millis() + scorpionAttackSpeed;
							this.scorpionAttackDone = false;
							this.scorpionAttacking = true;
						}
						this.scorpionAnimation = theBigProject.resourceManager.scorpionRightAttack;
						this.aggro = true;
						scorpionAttack();
					} else {
						this.scorpionAnimation = theBigProject.resourceManager.scorpionRightIdle;
						this.scorpionAttacking = false;
						this.scorpionAttackDone = true;
					}
				}

				if (this.scorpionDirection == "Left") {
					if (TheBigProject.dist(theBigProject.characterX, theBigProject.characterY, this.scorpionPosX,
							this.scorpionPosY) <= lanceSize) {
						if (this.scorpionAttacking == false && this.scorpionAttackDone == true) {
							waitUntilDamage = theBigProject.millis() + scorpionAttackSpeed;
							this.scorpionAttackDone = false;
							this.scorpionAttacking = true;
						}

						this.scorpionAnimation = theBigProject.resourceManager.scorpionLeftAttack;
						this.aggro = true;
						scorpionAttack();
					} else {
						this.scorpionAnimation = theBigProject.resourceManager.scorpionLeftIdle;
						this.scorpionAttacking = false;
						this.scorpionAttackDone = true;
					}
				}

				if (aggro == true) {
					if (this.scorpionPosX > theBigProject.characterX + lanceSize) {
						this.scorpionPosX -= this.scorpionMovementSpeed;
						this.scorpionDirection = "Left";
						this.scorpionAnimation = theBigProject.resourceManager.scorpionLeftWalk;
					}
					if (this.scorpionPosX < theBigProject.characterX - lanceSize) {
						this.scorpionPosX += this.scorpionMovementSpeed;
						this.scorpionDirection = "Right";
						this.scorpionAnimation = theBigProject.resourceManager.scorpionRightWalk;
					}
					if (this.scorpionPosY < theBigProject.characterY) {
						this.scorpionPosY += this.scorpionMovementSpeed;
					}
					if (this.scorpionPosY > theBigProject.characterY) {
						this.scorpionPosY -= this.scorpionMovementSpeed;
					}
				}
			}
		}
		if (this.scorpionLife <= 0) {
			this.scorpionDebuff = "";
			if (this.alive == true) {
				this.scorpionAnimation = theBigProject.resourceManager.deathExplosion;
				theBigProject.deathExplosionTimer = theBigProject.millis() + 1080;
				this.scorpionAnimation.play();
				displayEssence = this.actualPsionicEssence;
				timeOfDeath = theBigProject.millis();
			}
			this.alive = false;
			if (theBigProject.deathExplosionTimer < theBigProject.millis()) {
				this.scorpionAnimation = theBigProject.resourceManager.chestClosed;
				theBigProject.characterExp += this.experience;
				this.experience = 0;
			}

			if (TheBigProject.dist(theBigProject.characterX, theBigProject.characterY, this.scorpionPosX,
					this.scorpionPosY) < 50 && this.scorpionAnimation == theBigProject.resourceManager.chestClosed
					&& theBigProject.action == true) {
				this.scorpionAnimation = theBigProject.resourceManager.chestOpen;
				theBigProject.itemsGained(displayEssence, "Lance scorpion Spear tip", this.scorpionPosX,
						this.scorpionPosY, timeOfDeath);
				theBigProject.playerPsionicEssence = theBigProject.playerPsionicEssence + this.actualPsionicEssence;
				this.actualPsionicEssence = 0;
				if (this.dropLoot == true) {
					theBigProject.scorpionScales += 1;
					this.dropLoot = false;
				}

			}
		}
		theBigProject.image(this.scorpionAnimation, this.scorpionPosX, this.scorpionPosY);
		if (this.scorpionDebuff == "Fire") {
			theBigProject.image(theBigProject.resourceManager.fireBurning, this.scorpionPosX, this.scorpionPosY);
			this.scorpionLife -= theBigProject.burningDamage;
		}
	}

	public void swordDamage(float attackDirection, float swordSize, float swordDamage) {
		if (TheBigProject.dist(theBigProject.characterX + (attackDirection - theBigProject.characterX),
				(theBigProject.characterY - (swordSize / 2)), this.scorpionPosX, this.scorpionPosY) < swordSize) {
			if (theBigProject.lightningElement == true) {
				this.scorpionLife = this.scorpionLife - (swordDamage * 1.5f);
			} else {
				this.scorpionLife = this.scorpionLife - swordDamage;
			}

			if (theBigProject.fireElement == true) {
				this.scorpionDebuff = "Fire";
			}
			if (theBigProject.iceElement == true) {
				this.scorpionMovementSpeed = this.scorpionMovementSpeed - theBigProject.iceSlow;
			}
			if (theBigProject.darkElement == true) {
				theBigProject.characterHealth += (swordDamage / 100) * theBigProject.lifeSteal;
			}
			if (theBigProject.lightningElement == true) {
				this.scorpionDebuff = "Lightning";
			}
		}
	}

	public void bowDamage(float bowDamage) {
		if (TheBigProject.dist(theBigProject.arrowX, theBigProject.arrowY, this.scorpionPosX, this.scorpionPosY) < 50
				&& theBigProject.arrowToFar == false) {
			if (theBigProject.lightningElement == true) {
				this.scorpionLife = this.scorpionLife - (bowDamage * theBigProject.lightningDamage);
			} else if (theBigProject.lightElement == true) {
				this.scorpionLife = this.scorpionLife - (bowDamage * theBigProject.lightCharDamage);
			} else {
				this.scorpionLife = this.scorpionLife - bowDamage;
			}
			theBigProject.arrowToFar = true;
			this.aggro = true;
			if (theBigProject.fireElement == true) {
				this.scorpionDebuff = "Fire";
			}
			if (theBigProject.iceElement == true) {
				this.scorpionMovementSpeed = 2.5f;
			}
			if (theBigProject.darkElement == true) {
				theBigProject.characterHealth += (bowDamage / 100) * theBigProject.lifeSteal;
			}
			if (theBigProject.lightningElement == true) {
				this.scorpionDebuff = "Lightning";
			}
		}

		if (theBigProject.arrowX < theBigProject.oldCharacterX - 500
				|| theBigProject.arrowX > theBigProject.oldCharacterX + 500) {
			theBigProject.arrowToFar = true;
		}
	}
}
