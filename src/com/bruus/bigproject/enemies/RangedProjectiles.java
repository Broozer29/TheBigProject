package com.bruus.bigproject.enemies;

import com.bruus.bigproject.TheBigProject;

import processing.core.PApplet;

public class RangedProjectiles {
	float projectileX;
	float projectileY;
	boolean projectileHit;
	String projectileDebuff;
	String projectileType;
	String projectileDirection;
	TheBigProject theBigProject;
	float medusaSpeed = 2;

	public RangedProjectiles(TheBigProject theBigProject, float projectileX, float projectileY, boolean projectileHit,
			String projectileType, String projectileDebuff, String projectileDirection) {
		this.projectileX = projectileX;
		this.projectileY = projectileY;
		this.projectileType = projectileType;
		this.projectileDirection = projectileDirection;
		this.projectileDebuff = projectileDebuff;
		this.projectileHit = projectileHit;
		this.theBigProject = theBigProject;
	}

	public void displayProjectiles() {
		if (this.projectileType == "Medusa" && this.projectileHit == false) {
			theBigProject.image(theBigProject.resourceManager.medusaProjectile, this.projectileX, this.projectileY, 20, 20);
			if (this.projectileDirection == "Right") {
				projectileX += medusaSpeed;
			}
			if (this.projectileDirection == "Left") {
				projectileX -= medusaSpeed;
			}

			if (PApplet.dist(this.projectileX, this.projectileY, theBigProject.characterX,
					theBigProject.characterY) <= 25) {
				if (this.projectileDebuff == "Light") {
					theBigProject.characterHealth -= 5;
				} else if (this.projectileDebuff == "Lightning") {
					if (this.projectileDebuff == "Light") {
						theBigProject.characterHealth -= 7.5;
					} else
						theBigProject.characterHealth -= 15;
				} else
					theBigProject.characterHealth -= 10;
				this.projectileHit = true;
			}
		}
		theBigProject.resourceManager.medusaProjectile.play();
	}
}
