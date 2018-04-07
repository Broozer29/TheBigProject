package com.bruus.bigproject.enemies;

import com.bruus.bigproject.TheBigProject;

import gifAnimation.Gif;

public class EnemyMedusas {
	float medusaPosX;
	float medusaPosY;
	float medusaLife;
	float medusaMaxLife;
	String medusaDirection;
	float medusaAttackSpeed = 1500;
	float waitUntilDamage;
	boolean alive;
	boolean aggro;
	boolean medusaAttacking;
	String medusaDebuff;
	float medusaMovementSpeed;
	boolean medusaAttackDone;
	float maxPsionicEssence;
	float experience;
	float actualPsionicEssence;
	boolean dropLoot;
	int medusaRange = 150;
	TheBigProject theBigProject;
	float displayEssence;
	int timeOfDeath;

	Gif medusaAnimation;

	public EnemyMedusas(TheBigProject theBigProject, float medusaPosX, float medusaPosY, String medusaDirection,
			float medusaLife, float medusaMaxLife, float medusaMovementSpeed, float maxPsionicEssence,
			float actualPsionicEssence, float experience, boolean dropLoot, boolean alive, boolean aggro, boolean medusaAttacking, boolean medusaAttackDone,
			String medusaDebuff, Gif medusaAnimation) {
		this.medusaPosX = medusaPosX;
		this.medusaPosY = medusaPosY;
		this.medusaDirection = medusaDirection;
		this.medusaLife = medusaLife;
		this.medusaMaxLife = medusaMaxLife;
		this.maxPsionicEssence = maxPsionicEssence;
		this.actualPsionicEssence = actualPsionicEssence;
		this.dropLoot = dropLoot;
		this.experience = experience;
		this.medusaAnimation = medusaAnimation;
		this.medusaDebuff = medusaDebuff;
		this.medusaMovementSpeed = medusaMovementSpeed;
		this.alive = alive;
		this.aggro = aggro;
		this.medusaAttacking = medusaAttacking;
		this.medusaAttackDone = medusaAttackDone;
		this.theBigProject = theBigProject;
	}

	void medusaAttack() {
		if (waitUntilDamage < theBigProject.millis() && this.medusaAttacking == true
				&& this.medusaAttackDone == false) {
			
			RangedProjectiles projectile = new RangedProjectiles(theBigProject, this.medusaPosX, this.medusaPosY, false,
					"Medusa", this.medusaDebuff, this.medusaDirection);
			this.theBigProject.allProjectiles.add(projectile);
			
			this.medusaAttackDone = true;
		}
		if (waitUntilDamage < theBigProject.millis() + medusaAttackSpeed && this.medusaAttacking == true
				&& this.medusaAttackDone == true) {
			this.medusaAttacking = false;
			this.medusaAttackDone = true;
		}
	}

	public void displaymedusas() {
		if (this.medusaLife > 0) {
			float randomNumber = theBigProject.random(0,100);
			if (randomNumber < 10){
				this.dropLoot = true;
			}
			
			if (randomNumber > 10){
				this.dropLoot = false;
			}
			this.actualPsionicEssence = theBigProject.random(this.maxPsionicEssence - 50, this.maxPsionicEssence);
			this.medusaAnimation.play();
			if (this.alive == true) {
				theBigProject.healthBar(this.medusaPosX, this.medusaPosY, this.medusaLife, medusaMaxLife);
				if (this.medusaDirection == "Right") {
					if (TheBigProject.dist(theBigProject.characterX, theBigProject.characterY, this.medusaPosX,
							this.medusaPosY) <= medusaRange) {
						if (this.medusaAttacking == false && this.medusaAttackDone == true) {
							waitUntilDamage = theBigProject.millis() + medusaAttackSpeed;
							this.medusaAttackDone = false;
							this.medusaAttacking = true;
						}
						this.medusaAnimation = theBigProject.resourceManager.medusaRightAttack;
						this.aggro = true;
						medusaAttack();
					} else {
						this.medusaAnimation = theBigProject.resourceManager.medusaRightIdle;
						this.medusaAttacking = false;
						this.medusaAttackDone = true;
					}
				}

				if (this.medusaDirection == "Left") {
					if (TheBigProject.dist(theBigProject.characterX, theBigProject.characterY, this.medusaPosX,
							this.medusaPosY) <= medusaRange) {
						if (this.medusaAttacking == false && this.medusaAttackDone == true) {
							waitUntilDamage = theBigProject.millis() + medusaAttackSpeed;
							this.medusaAttackDone = false;
							this.medusaAttacking = true;
						}

						this.medusaAnimation = theBigProject.resourceManager.medusaLeftAttack;
						this.aggro = true;
						medusaAttack();
					} else {
						this.medusaAnimation = theBigProject.resourceManager.medusaLeftIdle;
						this.medusaAttacking = false;
						this.medusaAttackDone = true;
					}
				}

				if (aggro == true) {
					if (this.medusaPosX > theBigProject.characterX + 50){
						this.medusaDirection = "Left";
					}
					if (this.medusaPosX < theBigProject.characterX - 50){
						this.medusaDirection = "Right";
					}
					if (this.medusaPosX > theBigProject.characterX + medusaRange) {
						this.medusaPosX -= this.medusaMovementSpeed;
						
						this.medusaAnimation = theBigProject.resourceManager.medusaLeftWalk;
					}
					if (this.medusaPosX < theBigProject.characterX - medusaRange) {
						this.medusaPosX += this.medusaMovementSpeed;
						this.medusaAnimation = theBigProject.resourceManager.medusaRightWalk;
					}
					if (this.medusaPosY < theBigProject.characterY) {
						this.medusaPosY += this.medusaMovementSpeed;
					}
					if (this.medusaPosY > theBigProject.characterY) {
						this.medusaPosY -= this.medusaMovementSpeed;
					}
				}
			}
		}
		if (this.medusaLife <= 0) {
			this.medusaDebuff = "";
			if (this.alive == true) {
				this.medusaAnimation = theBigProject.resourceManager.deathExplosion;
				theBigProject.deathExplosionTimer = theBigProject.millis() + 1080;
				this.medusaAnimation.play();
				displayEssence = this.actualPsionicEssence;
				timeOfDeath = theBigProject.millis();
			}
			this.alive = false;
			if (theBigProject.deathExplosionTimer < theBigProject.millis()) {
				this.medusaAnimation = theBigProject.resourceManager.chestClosed;
				theBigProject.characterExp += this.experience;
				this.experience = 0;
			}

			if (TheBigProject.dist(theBigProject.characterX, theBigProject.characterY, this.medusaPosX,
					this.medusaPosY) < 50 && this.medusaAnimation == theBigProject.resourceManager.chestClosed
					&& theBigProject.action == true) {
				this.medusaAnimation = theBigProject.resourceManager.chestOpen;
				theBigProject.itemsGained(displayEssence, "Lance Knight Spear tip", this.medusaPosX, this.medusaPosY, timeOfDeath);
				theBigProject.playerPsionicEssence = theBigProject.playerPsionicEssence + this.actualPsionicEssence;
				this.actualPsionicEssence = 0;
				if (this.dropLoot == true){
					theBigProject.medusaHair += 1;
					this.dropLoot = false;
				}
			}
		}
		theBigProject.image(this.medusaAnimation, this.medusaPosX, this.medusaPosY);
		if (this.medusaDebuff == "Fire") {
			theBigProject.image(theBigProject.resourceManager.fireBurning, this.medusaPosX, this.medusaPosY);
			this.medusaLife -= theBigProject.burningDamage;
		}
	}

	public void swordDamage(float attackDirection, float swordSize, float swordDamage) {
		if (TheBigProject.dist(theBigProject.characterX + (attackDirection - theBigProject.characterX),
				(theBigProject.characterY - (swordSize / 2)), this.medusaPosX, this.medusaPosY) < swordSize) {
			if (theBigProject.lightningElement == true) {
				this.medusaLife = this.medusaLife - (swordDamage * 1.5f);
			} else {
				this.medusaLife = this.medusaLife - swordDamage;
			}

			if (theBigProject.fireElement == true) {
				this.medusaDebuff = "Fire";
			}
			if (theBigProject.iceElement == true) {
				this.medusaMovementSpeed = this.medusaMovementSpeed - theBigProject.iceSlow;
			}
			if (theBigProject.darkElement == true) {
				theBigProject.characterHealth += (swordDamage / 100) * theBigProject.lifeSteal;
			}
			if (theBigProject.lightningElement == true) {
				this.medusaDebuff = "Lightning";
			}
		}
	}

	public void bowDamage(float bowDamage) {
		if (TheBigProject.dist(theBigProject.arrowX, theBigProject.arrowY, this.medusaPosX, this.medusaPosY) < 50
				&& theBigProject.arrowToFar == false) {
			if (theBigProject.lightningElement == true) {
				this.medusaLife = this.medusaLife - (bowDamage * theBigProject.lightningDamage);
			} else if (theBigProject.lightElement == true) {
				this.medusaLife = this.medusaLife - (bowDamage * theBigProject.lightCharDamage);
			} else {
				this.medusaLife = this.medusaLife - bowDamage;
			}
			theBigProject.arrowToFar = true;
			this.aggro = true;
			if (theBigProject.fireElement == true) {
				this.medusaDebuff = "Fire";
			}
			if (theBigProject.iceElement == true) {
				this.medusaMovementSpeed = 2.5f;
			}
			if (theBigProject.darkElement == true) {
				theBigProject.characterHealth += (bowDamage / 100) * theBigProject.lifeSteal;
			}
			if (theBigProject.lightningElement == true) {
				this.medusaDebuff = "Lightning";
			}
		}

		if (theBigProject.arrowX < theBigProject.oldCharacterX - 500
				|| theBigProject.arrowX > theBigProject.oldCharacterX + 500) {
			theBigProject.arrowToFar = true;
		}
	}
}
