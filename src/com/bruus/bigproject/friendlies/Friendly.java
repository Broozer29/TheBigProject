package com.bruus.bigproject.friendlies;

import com.bruus.bigproject.TheBigProject;

import gifAnimation.Gif;
import processing.core.PApplet;

public class Friendly {
	float posX;
	float posY;
	float friendlyMovementSpeed;
	String friendlyName;
	String friendlyDirection;
	String friendlyChat;
	Gif friendlyAnimation;
	TheBigProject theBigProject;

	public Friendly(TheBigProject theBigProject, String friendlyName, float posX, float posY,
			float friendlyMovementSpeed, String friendlyDirection, Gif friendlyAnimation) {
		this.theBigProject = theBigProject;
		this.posX = posX;
		this.posY = posY;
		this.friendlyMovementSpeed = friendlyMovementSpeed;
		this.friendlyName = friendlyName;
		this.friendlyDirection = friendlyDirection;
		this.friendlyAnimation = friendlyAnimation;
	}

	public void displayFriendly() {
		if (this.friendlyName.equals("Boy")) {
			if (this.friendlyDirection.equals("Left")) {
				this.friendlyAnimation = theBigProject.resourceManager.boyIdleLeft;
			}
			if (this.friendlyDirection.equals("Right")) {
				this.friendlyAnimation = theBigProject.resourceManager.boyIdleRight;
			}
		}

		if (this.friendlyName.equals("Girl")) {
			if (this.friendlyDirection.equals("Left")) {
				this.friendlyAnimation = theBigProject.resourceManager.girlIdleLeft;
			}
			if (this.friendlyDirection.equals("Right")) {
				this.friendlyAnimation = theBigProject.resourceManager.girlIdleRight;
			}
		}

		if (this.friendlyName.equals("Man")) {
			if (this.friendlyDirection.equals("Left")) {
				this.friendlyAnimation = theBigProject.resourceManager.manIdleLeft;
			}
			if (this.friendlyDirection.equals("Right")) {
				this.friendlyAnimation = theBigProject.resourceManager.manIdleRight;
			}
		}

		if (this.friendlyName.equals("Woman")) {
			if (this.friendlyDirection.equals("Left")) {
				this.friendlyAnimation = theBigProject.resourceManager.womanIdleLeft;
			}
			if (this.friendlyDirection.equals("Right")) {
				this.friendlyAnimation = theBigProject.resourceManager.womanIdleRight;
			}
		}

		if (PApplet.dist(theBigProject.characterX, theBigProject.characterY, this.posX, this.posY) < 50
				&& theBigProject.action) {
			theBigProject.friendlyChat(this.friendlyName, this.friendlyChat);
		} else {
			whatText(this.friendlyName);
		}

		theBigProject.image(this.friendlyAnimation, posX, posY);
		this.friendlyAnimation.play();
	}

	void whatText(String name) {
		String[] boySentences = { "Hey there!", "I am going to be a stron man when I grow big!",
				"You want to play tag? TAG! You're it!", "Sssssh! I'm playing hide and seek!" };

		String[] girlSentences = { "Hiya!", "Wauw you are big!", "Do you have time to play with me?",
				"Mom wants me to help with cleaning the kitchen, but I want to play!" };

		String[] manSentences = { "Greetings stranger, what can I do for you?",
				"Kids these days, so joyful, no way of knowing what life used to be.",
				"When I had your age I used to be as strong as a bear.",
				"It's a shame our blacksmith moved away, now we don't have the ability to create new weapons. \nI heard he moved to the desert, but who would want to live there?", };

		String[] womanSentences = { "Hey there stranger.",
				"Say you wouldn't have the ability to make the kids a bit more quite do you?",
				"I used to be a beautiful woman like you back in the day.",
				"I heard there was a witch living in the forest, but he's just an old man." };

		if (name.equals("Boy")) {
			int number = (int) theBigProject.random(boySentences.length);
			this.friendlyChat = (boySentences[number]);
		}
		if (name.equals("Girl")) {
			int number = (int) theBigProject.random(boySentences.length);
			this.friendlyChat = (girlSentences[number]);
		}
		if (name.equals("Man")) {
			int number = (int) theBigProject.random(boySentences.length);
			this.friendlyChat = (manSentences[number]);
		}
		if (name.equals("Woman")) {
			int number = (int) theBigProject.random(boySentences.length);
			this.friendlyChat = (womanSentences[number]);
		}
	}

}
