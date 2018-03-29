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
	float actualPsionicEssence;
	TheBigProject theBigProject;

	Gif paladinAnimation;

	public EnemyPaladins(TheBigProject theBigProject, float paladinPosX, float paladinPosY, String paladinDirection,
			float paladinLife, float paladinMaxLife, float paladinMovementSpeed, float maxPsionicEssence,
			float actualPsionicEssence, boolean alive, boolean aggro, boolean paladinAttacking,
			boolean paladinAttackDone, String paladinDebuff, Gif paladinAnimation) {
		this.paladinPosX = paladinPosX;
		this.paladinPosY = paladinPosY;
		this.paladinDirection = paladinDirection;
		this.paladinLife = paladinLife;
		this.paladinMaxLife = paladinMaxLife;
		this.maxPsionicEssence = maxPsionicEssence;
		this.actualPsionicEssence = actualPsionicEssence;
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
		if (waitUntilDamage < theBigProject.millis() && this.paladinAttacking == true
				&& this.paladinAttackDone == false) {
			if (this.paladinDebuff == "Lightning") {
				if (theBigProject.lightElement == true) {
					theBigProject.characterHealth -= paladinDamage / 2;
				} else {
					theBigProject.characterHealth -= paladinDamage;
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
					}
					if (this.paladinPosX < theBigProject.characterX - paladinLanceSize) {
						this.paladinPosX += this.paladinMovementSpeed;
						this.paladinDirection = "Right";
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
			}
			this.alive = false;
			if (theBigProject.deathExplosionTimer < theBigProject.millis()) {
				this.paladinAnimation = theBigProject.resourceManager.chestClosed;
			}
		}

		theBigProject.image(this.paladinAnimation, this.paladinPosX, this.paladinPosY);
		if (this.paladinDebuff == "Fire") {
			theBigProject.image(theBigProject.resourceManager.fireBurning, this.paladinPosX, this.paladinPosY);
			this.paladinLife -= theBigProject.burningDamage;
		}
	}

	public void swordDamage(float attackDirection, float swordSize) {
		if (TheBigProject.dist(theBigProject.characterX + (attackDirection - theBigProject.characterX),
				(theBigProject.characterY - (swordSize / 2)), this.paladinPosX, this.paladinPosY) < swordSize) {
			if (theBigProject.lightningElement == true) {
				this.paladinLife = this.paladinLife - (theBigProject.swordDamage * 1.5f);
			} else {
				this.paladinLife = this.paladinLife - theBigProject.swordDamage;
			}

			if (theBigProject.fireElement == true) {
				this.paladinDebuff = "Fire";
			}
			if (theBigProject.iceElement == true) {
				this.paladinMovementSpeed = this.paladinMovementSpeed / 2;
			}
			if (theBigProject.darkElement == true) {
				theBigProject.characterHealth += (theBigProject.swordDamage / 100) * theBigProject.lifeSteal;
			}
			if (theBigProject.lightningElement == true) {
				this.paladinDebuff = "Light";
			}
		}
	}

	public void bowDamage() {
		if (TheBigProject.dist(theBigProject.arrowX, theBigProject.arrowY, this.paladinPosX, this.paladinPosY) < 50
				&& theBigProject.arrowToFar == false) {
			this.paladinLife -= theBigProject.bowDamage;
			theBigProject.arrowToFar = true;
			this.aggro = true;
			if (theBigProject.fireElement == true) {
				this.paladinDebuff = "Fire";
			}
			if (theBigProject.iceElement == true) {
				this.paladinMovementSpeed = 2.5f;
			}
			if (theBigProject.darkElement == true) {
				theBigProject.characterHealth += (theBigProject.bowDamage / 100) * theBigProject.lifeSteal;
			}
			if (theBigProject.lightningElement == true) {
				this.paladinDebuff = "Lightning";
			}
		}
	}
}
