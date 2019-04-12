package ui;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
 
/*
 * FileChooserDemo.java uses these files:
 *   images/Open16.gif
 *   images/Save16.gif
 */
public class UPU extends JPanel
                             implements ActionListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = 2473104394389832512L;
	static private final String newline = "\n";
    static JFrame frame = null;
    JTextField username,password,url;
    static String us,p,ur;
    
    public String getUs() {
		return us;
	}

	public void setUs() {
		us = username.getText();
	}

	public String getP() {
		return p;
	}

	public void setP() {
		p = password.getText();
	}

	public String getUr() {
		return ur;
	}

	public void setUr() {
		ur = url.getText();
	}

	JButton enterButton;
    static boolean pressed = false;
    
    public boolean isPressed() {
    	return pressed;
    }
    public void setPressed(boolean p) {
    	pressed = p;
    }
    
    
    public UPU() {
        super(new BorderLayout());

        enterButton = new JButton("Enter");
        enterButton.addActionListener(this);
        
        username = new JTextField("Username");
        username.setSize(200, 100);
        username.addActionListener(this);
        
        password = new JTextField("Password");
        password.setSize(200, 100);
        password.addActionListener(this);
        
        url = new JTextField("URL");
        url.setSize(200, 100);
        url.addActionListener(this);
 
        //For layout purposes, put the buttons in a separate panel
        JPanel buttonPanel = new JPanel(); //use FlowLayout
        buttonPanel.add(enterButton);
        JPanel fieldPanel = new JPanel();
        fieldPanel.add(username);
        fieldPanel.add(password);
        fieldPanel.add(url);
        
 
        //Add the buttons and the log to this panel.
        add(fieldPanel, BorderLayout.PAGE_START);
        add(buttonPanel, BorderLayout.PAGE_END);
        //pressed = false;
    }
 
    public void actionPerformed(ActionEvent e) {
    	System.out.println("pressed");
        	setUs();
        	setP();
        	setUr();
            setPressed(true);
            System.out.print(isPressed());
    }
 
  
 
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        frame = new JFrame("QUIA Bot");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 800);
        //Add content to the window.
        frame.add(new UPU());
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    public void closeFrame() {
    	frame.dispose();
    }
 
    public void choser() {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.FALSE); 
                createAndShowGUI();
            }
        });
    }
}
