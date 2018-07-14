// Using the libraries below, draws out my face in a cartoon style
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.BasicStroke;
import java.awt.geom.RoundRectangle2D.Double;

public class FaceComposition extends JComponent
{
   public void paintComponent(Graphics g)
	{
		final int MID = 300;

		//Recover Graphics2D
		Graphics2D g2 = (Graphics2D) g;

		//Background
		Color background = new Color(242,226,205);
		g2.setColor(background);
		g2.fillRect(0,0,600,600);

		//My back Hair
		Color myHair= new Color (58,34,4);
		g2.setColor(myHair);
		g2.fillOval(MID-125, 90, 250, 350);

		//My neck
		Color myNeck = new Color(241,194,125);
		g2.setColor(myNeck);
		g2.fillRect(MID-20, 340, 40, 40);

		//My ears
		g2.fillOval(MID+65, 240, 40, 40);
		g2.fillOval(MID-105, 240, 40, 40);

		//My Skin Colour
		Color mySkin = new Color(247,231,180);

		//My Head
		g2.setColor(mySkin);
		g2.fillOval(MID-95, 100, 190, 200);

		//My Chin
		g2.fillOval(MID-85, 150, 170, 200);

		//My Hair
		g2.setColor(myHair);
		g2.fillArc(MID+65, 140, 70,50, 115, 180);
		g2.fillArc(MID-133, 95, 245,230,20, 180);

		//Cheeks
		Color myCheeks = new Color(255,211,182);
		g2.setColor(myCheeks);
		g2.fillOval(MID+45, 240, 40, 20);
		g2.fillOval(MID-85, 240, 40, 20);

		//My eyes
		Color myEyes = new Color(45,27,4);
		g2.setColor(myEyes);
		g2.fillOval(MID-60, 220, 30, 30);
		g2.fillOval(MID+30, 220, 30, 30);

		//My nose
		int length = 3;
		int[] firstNose = new int[length];
		firstNose[0]=MID-15;
		firstNose[1]=MID;
		firstNose[2]= MID+15;
		int[] secondNose = new int[length];
		secondNose[0] = 280;
		secondNose[1] = 255;
		secondNose[2]= 280;

		Color myNose = new Color(227,158,84);
		g2.setColor(myNose);
		g2.fillPolygon(firstNose, secondNose, length);

		//My eyeBrows
		Color myEyeBrows = new Color(88,63,24);
		g2.setColor(myEyeBrows);
		g2.setStroke(new BasicStroke(4));

		//g2.drawLine(MID-65,220, MID-50, 210);
		g2.drawLine(MID+45,205, MID+70, 210);

		//My Mouth
		Color myMouth = new Color(193,130,53);
		g2.setColor(myMouth);
		g2.drawArc(MID-17, 280,35,30, 315, -90);

		//My glasses
		Color myGlasses = new Color(56,29,75);
		g2.setColor(myGlasses);
		g2.drawRoundRect(MID-75,215,60,40,10,10);
		g2.drawRoundRect(MID+15,215,60,40,10,10);
		g2.drawLine(MID-15,235, MID+15, 235);

		//My body
		Color myBody = new Color(166,158,176);
		g2.setColor(myBody);
		g2.fillArc(MID-98, 370, 200,170,0,180);

		//My lower neck
		g2.setColor(myNeck);
		g2.fillOval(MID-20, 352, 40,30);

		//My bow
		g2.setColor(myCheeks);
		g2.fillOval(MID-11,390,20,20);
		int length1 = 3;
		int[] first = new int[length1];
		first[0]=MID-28;
		first[1]=MID-28;
		first[2]= MID-5;
		int[] second = new int[length1];
		second[0] = 414;
		second[1] = 384;
		second[2]= 399;
		g2.fillPolygon(first, second, length1);

		first[0]=MID+27;
		first[1]=MID+27;
		first[2]= MID+4;
		second[0] = 414;
		second[1] = 384;
		second[2]= 399;
		g2.fillPolygon(first, second, length1);
	}
}