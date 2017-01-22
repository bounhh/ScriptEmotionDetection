package org.emotiondetection.campus;

import java.util.Arrays;
import java.util.Vector;

/**
 * 
 * @author BOUDEFFA AMIN
 * @author TIFARINE MOHAMED AMINE
 * @author BOUGHELLOUT ZAKARYA
 *
 */
public class EmotionIndication {

	private Vector<String> Happiness = new Vector<String>(
			Arrays.asList("love", "lol", "fun", "good", "happy", "nice", "awesome", "funny", "great", "excited"));
	private Vector<String> Sadness = new Vector<String>(
			Arrays.asList("Hurt", "Miss", "Sorry", "Bad", "Sad", "Lost", "Cry", "Stress", "Wept", "Longing"));
	private Vector<String> Anger = new Vector<String>(Arrays.asList("fucking", "Angry", "Bitch", "furious", "annoyed",
			"possed", "yelling", "upset", "mad", "shut up"));
	private Vector<String> Disgust = new Vector<String>(Arrays.asList("Hate", "Dislike", "Dislike", "Shit", "Stupid",
			"Fucking", "Disgusting", "Crap", "Bitch", "Sick"));
	private Vector<String> Surprise = new Vector<String>(Arrays.asList("Surprise", "amazing", "amazing", "wonder",
			"unexpected", "can't belive", "weird", "suddenly", "odd", "strange"));
	private Vector<String> Fear = new Vector<String>(Arrays.asList("afraid", "scared", "nervous", "worry", "security",
			"fear", "what if", "threat", "freak", "dangerous"));

	public Vector<String> getHappiness() {
		return Happiness;
	}

	public void setHappiness(Vector<String> happiness) {
		Happiness = happiness;
	}

	public Vector<String> getSadness() {
		return Sadness;
	}

	public void setSadness(Vector<String> sadness) {
		Sadness = sadness;
	}

	public Vector<String> getAnger() {
		return Anger;
	}

	public void setAnger(Vector<String> anger) {
		Anger = anger;
	}

	public Vector<String> getDisgust() {
		return Disgust;
	}

	public void setDisgust(Vector<String> disgust) {
		Disgust = disgust;
	}

	public Vector<String> getSurprise() {
		return Surprise;
	}

	public void setSurprise(Vector<String> surprise) {
		Surprise = surprise;
	}

	public Vector<String> getFear() {
		return Fear;
	}

	public void setFear(Vector<String> fear) {
		Fear = fear;
	}

}
