package gui;

import java.awt.Color;
import java.text.Format;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.JFormattedTextField;


public class Zahlenfeld extends JFormattedTextField {

	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Color ERROR_BACKGROUND_COLOR = new Color( 255, 215, 215 );

	private Color fBackground;
	
	NumberFormat numberInstance = NumberFormat.getNumberInstance();
	
	
	public Zahlenfeld( Format aFormat ) {
	    
	    super(aFormat);
	    setFocusLostBehavior( JFormattedTextField.COMMIT_OR_REVERT );
	    

	  }
	
	 @Override
	  public void setValue( Object value ) {
	    boolean validValue = true;
	    
	    // Inhalt checken
	    
	    try {
	    	AbstractFormatter formatter = getFormatter();
	    	if ( formatter != null ) {
	    		formatter.valueToString( value );
	    	}
	    } catch ( ParseException e ) {
	    	validValue = false;
	    	setBackground(ERROR_BACKGROUND_COLOR);
	    }

	    
	    //only set the value when valid
	    if ( validValue ) {
	    	setBackground(ERROR_BACKGROUND_COLOR);
	      int old_caret_position = getCaretPosition();
	      super.setValue( value );
	      setCaretPosition( Math.min( old_caret_position, getText().length() ) );
	    }
	  }

	  
	}
