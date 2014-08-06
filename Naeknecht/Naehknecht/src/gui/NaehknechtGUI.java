/**
 * 
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

import werte.Masse;

/**
 * 
 * @author marrus
 *
 */
public class NaehknechtGUI {

	
	static Color weinrot = new Color(72, 0, 36);
	static Color blaugrau = new Color(243,243,243);
	static Masse m = new Masse();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Erzeugen des Hauptframes
		  JFrame mainFrame = new JFrame("Nähknecht");
		  mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		  mainFrame.setLayout(new BorderLayout());
		  mainFrame.setSize(400, 300);
		  
		  //Menüleiste
		  JMenuBar menueleiste = new JMenuBar();
		  menueleiste.setBorder(new LineBorder(weinrot));
		  JMenu datei = new JMenu("Datei");
		  menueleiste.add(datei);
		  JMenu bearbeiten = new JMenu("Bearbeiten");
		  menueleiste.add(bearbeiten);
		  
		  //Panel für Werte der Körper Maße
		  Werte werte = new Werte();
		  werte.buttons(m);
		  werte.setBackground(blaugrau);
		  werte.setPreferredSize(new Dimension(80, mainFrame.getHeight()));
		  
		  //Panel für die optische Darstellung des Schnitts
		  ZeichenflächeOberteil schnitt = new ZeichenflächeOberteil();
		  schnitt.setBackground(Color.white);
		  schnitt.setPreferredSize(new Dimension(mainFrame.getWidth()-werte.getWidth(), mainFrame.getHeight()));
		  
		  JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		  split.setLeftComponent(werte);
		  split.setRightComponent(schnitt);
		  
		 
		  //Hinzufügen der erstellten Komponenten
		  mainFrame.setJMenuBar(menueleiste);
		  mainFrame.add(split);
		  mainFrame.setVisible(true);
	}

}
