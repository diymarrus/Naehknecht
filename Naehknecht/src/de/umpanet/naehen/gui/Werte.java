package de.umpanet.naehen.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import de.umpanet.naehen.werte.ConstructionMeasuerments;
import de.umpanet.naehen.werte.Masse;

public class Werte extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5656082172127606867L;
	;
	
	JTextField 	rückenlängeFeld;
	JTextField 	brustumfangFeld;
	JTextField 	tailleFeld;
	JTextField 	brustpunktWeiteFeld;
	JTextField 	brustpunktTiefeFeld;
	JTextField 	schulterweiteHintenFeld;
	JTextField 	schulterweiteVornFeld;

	HashMap<String,JTextField> felder = new HashMap<String,JTextField>();
	
	JButton		buttonSchnitt;
	JButton		buttonSpeichern;
	ConstructionMeasuerments m;
	
	
	public void buttons(ConstructionMeasuerments m) {
		GridLayout gridLayout = new GridLayout(0,2);
		this.setLayout(gridLayout);

		this.m = m;
/*		// Instanzieren der Eingabefelder
		rückenlängeFeld = new JTextField();
		rückenlängeFeld.setText(String.valueOf(m.getRückenlänge()));
		brustumfangFeld = new JTextField();
		brustumfangFeld.setText(String.valueOf(m.getBrustumfang()));
		tailleFeld  =new JTextField();
		tailleFeld.setText(String.valueOf(m.getTaille()));
		brustpunktWeiteFeld = new JTextField();
		brustpunktWeiteFeld.setText(String.valueOf(m.getBrustpunktWeite()));
		brustpunktTiefeFeld = new JTextField();
		brustpunktTiefeFeld.setText(String.valueOf(m.getBrustpunktTiefe()));
		schulterweiteHintenFeld = new JTextField();
		schulterweiteHintenFeld.setText(String.valueOf(m.getSchulterweiteHinten()));
		schulterweiteVornFeld = new JTextField();
		schulterweiteVornFeld.setText(String.valueOf(m.getSchulterweiteVorn()));*/
		
		
			
		buttonSchnitt = new JButton("Schnitt erstellen");
		buttonSchnitt.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				buttonSchnittClicked();
			}

			private void buttonSchnittClicked() {
				
				Set<String> keys = felder.keySet();
				for(int i =0 ; i <= keys.size(); i++){
					
				}
				
			}
			
		});
		buttonSpeichern = new JButton("Speichern");
		buttonSpeichern.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				buttonSpeichernClicked();
			}

			private void buttonSpeichernClicked() {
				
				//TODO Import/Export
				
			}
			
		});
//		this.add(new JLabel("Rückenlänge"));
//		this.add(rückenlängeFeld);
//		this.add(new JLabel("Brustumfang"));
//		this.add(brustumfangFeld);
//		this.add(tailleFeld);
//		this.add(brustpunktWeiteFeld);
//		this.add(brustpunktTiefeFeld);
//		this.add(schulterweiteHintenFeld);
//		this.add(brustpunktWeiteFeld);
//		this.add(schulterweiteVornFeld);
		
		HashMap<String, Double> werte = m.getAll();
		Iterator<String> werteIter = werte.keySet().iterator();
		while(werteIter.hasNext()){
			String name = werteIter.next();
			addField(name,werte.get(name));
		}
		
		this.add(buttonSchnitt);
		
		
		
	}
	
	private void addField(String name, double wert){
		
		Dimension dim = new Dimension(100, 60);
		JPanel hilfspanel = new JPanel();
		//hilfspanel.setPreferredSize(dim);
		hilfspanel.setMinimumSize(new Dimension(60,60));
		//hilfspanel.setLayout(new BoxLayout(hilfspanel, BoxLayout.PAGE_AXIS));
		NumberFormat numberInstance = NumberFormat.getNumberInstance();
		JTextField feld = new JFormattedTextField(numberInstance);
		feld.setName(name);
		feld.setText(String.valueOf(wert));
		feld.setMinimumSize(dim);
		feld.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		hilfspanel.add(feld);
		
		JLabel namelable = new JLabel(name);
		
		this.add(namelable);
		this.add(hilfspanel);
		felder.put(name, feld);
		
	}

}
