/**
 * 
 */
package de.umpanet.naehen.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

import de.umpanet.naehen.tools.ParseMeasuerments;
import de.umpanet.naehen.werte.ConstructionMeasuerments;
import de.umpanet.naehen.werte.Masse;

/**
 * 
 * @author marrus
 *
 */
public class NaehknechtGUI {

	
	static Color weinrot = new Color(72, 0, 36);
	static Color blaugrau = new Color(243,243,243);
	static ConstructionMeasuerments m = new ConstructionMeasuerments();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		  HashMap<String, Double> mae = new ParseMeasuerments().parse();
		
		//Erzeugen des Hauptframes
		  JFrame mainFrame = new JFrame("Nähknecht");
		  mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		  mainFrame.setLayout(new BorderLayout());
		  mainFrame.setSize(1200, 800);
		  
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
		  BodiceSloper schnitt = new BodiceSloper(m,-1);
		  schnitt.setBackground(Color.white);

		 

//		  if(schnitt.width() != -1 && schnitt.height() != -1){
//			  schnitt.setPreferredSize(new Dimension(schnitt.width(), schnitt.height()));
//		  }
		  JScrollPane scrollPane = new JScrollPane(schnitt, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			
//		  JPanel panel = new JPanel();
//		  panel.setLayout(new GridLayout(1, 0));
//		  panel.add(werte);
//		  //panel.add(schnitt);
//		  panel.add(scrollPane);
		  JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		  split.setLeftComponent(werte);
		  split.setRightComponent(scrollPane);

		 
		 // scrollPane.setBounds(50, 30, 300, 50);
		  
		 
		  //Hinzufügen der erstellten Komponenten

		 
		  mainFrame.setJMenuBar(menueleiste);
		  mainFrame.add(split);

		  mainFrame.setVisible(true);
		  //System.out.println(schnitt.getPreferredSize().getWidth() + "  " );
		  //mainFrame.setSize((int) schnitt.getPreferredSize().getWidth()+werte.getWidth(), (int) schnitt.getPreferredSize().getHeight());
	}

}
