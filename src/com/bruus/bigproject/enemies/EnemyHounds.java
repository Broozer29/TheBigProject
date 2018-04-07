package com.bruus.bigproject.enemies;

import com.bruus.bigproject.TheBigProject;

import gifAnimation.Gif;

public class EnemyHounds {
	float houndPosX;
	float houndPosY;
	float houndLife;
	float houndMaxLife;
	String houndDirection;
	float lanceSize = 60;
	float houndAttackSpeed = 2200;
	float waitUntilDamage;
	boolean alive;
	boolean aggro;
	boolean houndAttacking;
	String houndDebuff;
	float houndMovementSpeed;
	boolean houndAttackDone;
	float maxPsionicEssence;
	boolean dropLoot;
	float experience;
	float actualPsionicEssence;
	TheBigProject theBigProject;
	int timeOfDeath;
	float displayEssence;

	Gif houndAnimation;

	public EnemyHounds(TheBigProject theBigProject, float houndPosX, float houndPosY, String houndDirection,
			float houndLife, float houndMaxLife, float houndMovementSpeed, float maxPsionicEssence,
			float actualPsionicEssence, float experience, boolean dropLoot, boolean alive, boolean aggro, boolean houndAttacking, boolean houndAttackDone,
			String houndDebuff, Gif houndAnimation) {
		this.houndPosX = houndPosX;
		this.houndPosY = houndPosY;
		this.houndDirection = houndDirection;
		this.houndLife = houndLife;
		this.houndMaxLife = houndMaxLife;
		this.maxPsionicEssence = maxPsionicEssence;
		this.actualPsionicEssence = actualPsionicEssence;
		this.dropLoot = dropLoot;
		this.experience = experience;
		this.houndAnimation = houndAnimation;
		this.houndDebuff = houndDebuff;
		this.houndMovementSpeed = houndMovementSpeed;
		this.alive = alive;
		this.aggro = aggro;
		this.houndAttacking = houndAttacking;
		this.houndAttackDone = houndAttackDone;
		this.theBigProject = theBigProject;
	}

	void houndAttack() {
		if (waitUntilDamage < theBigProject.millis() && this.houndAttacking == true
				&& this.houndAttackDone == false) {
			if (this.houndDebuff == "Lightning") {
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
			this.houndAttackDone = true;
		}
		if (waitUntilDamage < theBigProject.millis() + houndAttackSpeed && this.houndAttacking == true
				&& this.houndAttackDone == true) {
			this.houndAttacking = false;
			this.houndAttackDone = true;
		}
	}

	public void displayhounds() {
		if (this.houndLife > 0) {
			float randomNumber = theBigProject.random(0,100);
			if (randomNumber < 10){
				this.dropLoot = true;
			}
			
			if (randomNumber > 10){
				this.dropLoot = false;
			}
			this.actualPsionicEssence = theBigProject.random(this.maxPsionicEssence - 50, this.maxPsionicEssence);
			this.houndAnimation.play();
			if (this.alive == true) {
				theBigProject.healthBar(this.houndPosX, this.houndPosY, this.houndLife, houndMaxLife);
				if (this.houndDirection == "Right") {
					if (TheBigProject.dist(theBigProject.characterX, theBigProject.characterY, this.houndPosX,
							this.houndPosY) <= lanceSize) {
						if (this.houndAttacking == false && this.houndAttackDone == true) {
							waitUntilDamage = theBigProject.millis() + houndAttackSpeed;
							this.houndAttackDone = false;
							this.houndAttacking = true;
						}
						this.houndAnimation = theBigProject.resourceManager.houndRightAttack;
						this.aggro = true;
						houndAttack();
					} else {
						this.houndAnimation = theBigProject.resourceManager.houndRightIdle;
						this.houndAttacking = false;
						this.houndAttackDone = true;
					}
				}

				if (this.houndDirection == "Left") {
					if (TheBigProject.dist(theBigProject.characterX, theBigProject.characterY, this.houndPosX,
							this.houndPosY) <= lanceSize) {
						if (this.houndAttacking == false && this.houndAttackDone == true) {
							waitUntilDamage = theBigProject.millis() + houndAttackSpeed;
							this.houndAttackDone = false;
							this.houndAttacking = true;
						}

						this.houndAnimation = theBigProject.resourceManager.houndLeftAttack;
						this.aggro = true;
						houndAttack();
					} else {
						this.houndAnimation = theBigProject.resourceManager.houndLeftIdle;
						this.houndAttacking = false;
						this.houndAttackDone = true;
					}
				}

				if (aggro == true) {
					if (this.houndPosX > theBigProject.characterX + lanceSize) {
						this.houndPosX -= this.houndMovementSpeed;
						this.houndDirection = "Left";
						this.houndAnimation = theBigProject.resourceManager.houndLeftWalk;
					}
					if (this.houndPosX < theBigProject.characterX - lanceSize) {
						this.houndPosX += this.houndMovementSpeed;
						this.houndDirection = "Right";
						this.houndAnimation = theBigProject.resourceManager.houndRightWalk;
					}
					if (this.houndPosY < theBigProject.characterY) {
						this.houndPosY += this.houndMovementSpeed;
					}
					if (this.houndPosY > theBigProject.characterY) {
						this.houndPosY -= this.houndMovementSpeed;
					}
				}
			}
		}
		if (this.houndLife <= 0) {
			this.houndDebuff = "";
			if (this.alive == true) {
				this.houndAnimation = theBigProject.resourceManager.deathExplosion;
				theBigProject.deathExplosionTimer = theBigProject.millis() + 1080;
				this.houndAnimation.play();
				displayEssence = this.actualPsionicEssence;
				timeOfDeath = theBigProject.millis();
			}
			this.alive = false;
			if (theBigProject.deathExplosionTimer < theBigProject.millis()) {
				this.houndAnimation = theBigProject.resourceManager.chestClosed;
				theBigProject.characterExp += this.experience;
				this.experience = 0;
			}

			if (TheBigProject.dist(theBigProject.characterX, theBigProject.characterY, this.houndPosX,
					this.houndPosY) < 50 && this.houndAnimation == theBigProject.resourceManager.chestClosed
					&& theBigProject.action == true) {
				this.houndAnimation = theBigProject.resourceManager.chestOpen;
				theBigProject.itemsGained(displayEssence, "Lance Knight Spear tip", this.houndPosX, this.houndPosY, timeOfDeath);
				theBigProject.playerPsionicEssence += this.actualPsionicEssence;
				this.actualPsionicEssence = 0;
				if (this.dropLoot == true){
					theBigProject.houndTeeth += 1;
					this.dropLoot = false;
				}
			}
		}
		theBigProject.image(this.houndAnimation, this.houndPosX, this.houndPosY);
		if (this.houndDebuff == "Fire") {
			theBigProject.image(theBigProject.resourceManager.fireBurning, this.houndPosX, this.houndPosY);
			this.houndLife -= theBigProject.burningDamage;
		}
	}

	public void swordDamage(float attackDirection, float swordSize, float swordDamage) {
		if (TheBigProject.dist(theBigProject.characterX + (attackDirection - theBigProject.characterX),
				(theBigProject.characterY - (swordSize / 2)), this.houndPosX, this.houndPosY) < swordSize) {
			if (theBigProject.lightningElement == true) {
				this.houndLife = this.houndLife - (swordDamage * 1.5f);
			} else {
				this.houndLife = this.houndLife - swordDamage;
			}

			if (theBigProject.fireElement == true) {
				this.houndDebuff = "Fire";
			}
			if (theBigProject.iceElement == true) {
				this.houndMovementSpeed = this.houndMovementSpeed - theBigProject.iceSlow;
			}
			if (theBigProject.darkElement == true) {
				theBigProject.characterHealth += (swordDamage / 100) * theBigProject.lifeSteal;
			}
			if (theBigProject.lightningElement == true) {
				this.houndDebuff = "Lightning";
			}
		}
	}

	public void bowDamage(float bowDamage) {
		if (TheBigProject.dist(theBigProject.arrowX, theBigProject.arrowY, this.houndPosX, this.houndPosY) < 50
				&& theBigProject.arrowToFar == false) {
			if (theBigProject.lightningElement == true) {
				this.houndLife = this.houndLife - (bowDamage * theBigProject.lightningDamage);
			} else if (theBigProject.lightElement == true) {
				this.houndLife = this.houndLife - (bowDamage * theBigProject.lightCharDamage);
			} else {
				this.houndLife = this.houndLife - bowDamage;
			}
			theBigProject.arrowToFar = true;
			this.aggro = true;
			if (theBigProject.fireElement == true) {
				this.houndDebuff = "Fire";
			}
			if (theBigProject.iceElement == true) {
				this.houndMovementSpeed = 2.5f;
			}
			if (theBigProject.darkElement == true) {
				theBigProject.characterHealth += (bowDamage / 100) * theBigProject.lifeSteal;
			}
			if (theBigProject.lightningElement == true) {
				this.houndDebuff = "Lightning";
			}
		}

		if (theBigProject.arrowX < theBigProject.oldCharacterX - 500
				|| theBigProject.arrowX > theBigProject.oldCharacterX + 500) {
			theBigProject.arrowToFar = true;
		}
	}
}
