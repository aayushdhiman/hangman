import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Hangman extends JPanel {

	int incorrectGuesses = 0;
	String answerString;
	String currString = "";

	final int TOP_PERCENT = 10;
	final int HEAD_PERCENT = 15;
	final int TORSO_PERCENT = 35;
	final int LEGS_PERCENT = 20;

	/**
	 * Constructor for the Hangman object.
	 * Sets the incorrect gueses to 0, the answer string to the input parameter
	 * str, and adds the underscores to the currString.
	 * @param str the string value that is the answer to the hangman puzzle
	 */
	public Hangman(String str) {
		incorrectGuesses = 0;
		answerString = str;
		for (int i = 0; i < str.length(); i++) {
			currString = currString + " _";
		}
		currString = currString.substring(1);

		
	}
	
	/**
	 * Getter for the current string
	 * @return currString, the current string for the hangman
	 */
	public String getString() {
		return currString;
	}

	/**
	 * Resets the hangman game and gets a new word.
	 * @param str the string with the answer
	 */
	public void reset(String str) {
		String word = WordGenerator.getWord();
		
		incorrectGuesses = 0;
		answerString = word;
		currString = "";
		for (int i = 0; i < word.length(); i++) {
			currString = currString + " _";
		}
		currString = currString.substring(1);
		System.out.println(word);
		repaint();
	}

	/**
	 * Tries a character to see if it is part of the answer
	 * @param ch the character being tried
	 * @return boolean which says true if the character is in the word, false if it isn't.
	 */
	public boolean tryCharacter(char ch) {
		boolean contains = false;
		String spacedAnswer = answerString.replace("", " ").trim();
		for (int i = 0; i < answerString.length(); i++) {

			if (answerString.charAt(i) == ch) {
				currString = currString.substring(0, i * 2) + ch + currString.substring((i * 2) + 1);
				contains = true;
			}
			
			if (spacedAnswer.equals(currString)) {
				reset("hello");
			}
		}

		if (!contains) {
			incorrectGuesses++;
		}
		repaint();
		return contains;
	}

	/*
	 * All methods below deal with drawing the Hangman image You do not need to
	 * modify them, but can if you want....
	 */

	public void drawFace(Graphics g, int midX) {

		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("Head.png"));
		} catch (IOException ex) {
			System.out.println("error");
		}

		int height = getHeight();

		int diameter = (int) (HEAD_PERCENT * height / 100.0);
		int start_y = (int) (TOP_PERCENT * (height / 100.0));
		//g.fillOval(midX - diameter / 2, start_y, diameter, diameter);
		 g.drawImage(image, 110, 10,100,100,this);
//
	}

	public void drawTorso(Graphics g, int midX) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("Dress.png"));
		} catch (IOException ex) {
			System.out.println("error");
		}
		int height = getHeight();

		int start_y = (int) ((TOP_PERCENT + HEAD_PERCENT) * (height / 100.0));
		int end_y = (int) ((TOP_PERCENT + HEAD_PERCENT + TORSO_PERCENT) * (height / 100.0));
	//	g.drawLine(midX, start_y, midX, end_y);
		 g.drawImage(image, 95, 100,140,140,this);
		
	}

	public void drawRightArm(Graphics g, int midX) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("Right Arm.png"));
		} catch (IOException ex) {
			System.out.println("error");
		}
		int angle = 315;
		int length = (int) ((TORSO_PERCENT / 2) * (getHeight() / 100.0));
		int start_y = (int) ((TOP_PERCENT + HEAD_PERCENT + TORSO_PERCENT / 4) * (getHeight() / 100.0));
		int end_x = (int) (midX + length * Math.cos(angle * Math.PI / 180));
		int end_y = (int) (start_y - length * Math.sin(angle * Math.PI / 180));
		//g.drawLine(midX, start_y, end_x, end_y);
		 g.drawImage(image, 190, 70,140,140,this);
		
	}

	public void drawLeftArm(Graphics g, int midX) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("LeftArm.png"));
		} catch (IOException ex) {
			System.out.println("error");
		}
		int angle = 225;
		int length = (int) ((TORSO_PERCENT / 2) * (getHeight() / 100.0));
		int start_y = (int) ((TOP_PERCENT + HEAD_PERCENT + TORSO_PERCENT / 4) * (getHeight() / 100.0));
		int end_x = (int) (midX + length * Math.cos(angle * Math.PI / 180));
		int end_y = (int) (start_y - length * Math.sin(angle * Math.PI / 180));
	//	g.drawLine(midX, start_y, end_x, end_y);
		
		g.drawImage(image, 9, 130,140,140,this);	
	}

	public void drawRightLeg(Graphics g, int midX) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("LeftLef.png"));
		} catch (IOException ex) {
			System.out.println("error");
		}

		int angle = 315;
		int length = (int) ((TORSO_PERCENT / 2) * (getHeight() / 100.0));
		int start_y = (int) ((TOP_PERCENT + HEAD_PERCENT + TORSO_PERCENT) * (getHeight() / 100.0));
		int end_x = (int) (midX + length * Math.cos(angle * Math.PI / 180));
		int end_y = (int) (start_y - length * Math.sin(angle * Math.PI / 180));
		//g.drawLine(midX, start_y, end_x, end_y);
		g.drawImage(image, 170, 230,100,130,this);
	}

	public void drawLeftLeg(Graphics g, int midX) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("RightLeg.png"));
		} catch (IOException ex) {
			System.out.println("error");
		}
		int angle = 225;
		int length = (int) ((TORSO_PERCENT / 2) * (getHeight() / 100.0));
		int start_y = (int) ((TOP_PERCENT + HEAD_PERCENT + TORSO_PERCENT) * (getHeight() / 100.0));
		int end_x = (int) (midX + length * Math.cos(angle * Math.PI / 180));
		int end_y = (int) (start_y - length * Math.sin(angle * Math.PI / 180));
	//	g.drawLine(midX, start_y, end_x, end_y);
		g.drawImage(image, 70, 240,100,130,this);
	}

	public void paintComponent(Graphics g) {
		int width = getWidth();
		int height = getHeight();

		g.setColor(Color.gray);
		g.fillRect(0, 0, width, height);

		g.setColor(Color.red);
		if (incorrectGuesses > 0)
			drawFace(g, width / 2);

		if (incorrectGuesses > 1)
			drawTorso(g, width / 2);

		if (incorrectGuesses > 2)
			drawRightArm(g, width / 2);

		if (incorrectGuesses > 3)
			drawLeftArm(g, width / 2);

		if (incorrectGuesses > 4)
			drawRightLeg(g, width / 2);

		if (incorrectGuesses > 5)
			drawLeftLeg(g, width / 2);
	}

}