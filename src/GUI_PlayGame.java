import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class GUI_PlayGame implements ActionListener
{
    JFrame frame;
    JLabel label1;
    JLabel label2;
    JLabel label3;
    HangmanGame game;
    private char InputLetter;
    //private boolean IsEvil = true;
    JLabel result;


    public GUI_PlayGame(int letters, int guesses)
    {
    	game = new EvilHangMan(letters, guesses);
    	
    }
    
    /*
     * This method creates all the UI components and then
     * opens a new window.
     */
    public void show() {
    	
    	initializeFrame();
        createLabels();
        
        //this generates an image
        ImageIcon icon = new ImageIcon("blank.gif"); 
        JLabel hangmanPic = new JLabel(icon);

       addLabels();
       frame.add(hangmanPic);
        addButtons();
        
        frame.setResizable(false);
        frame.setVisible(true);
    }
    public void initializeFrame()
    {
    	frame = new JFrame("Evil Hangman");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(360,370));
        frame.setLayout(new FlowLayout());
        frame.setResizable(false);
    }
    public void createLabels()
    {
    	label1 = new JLabel("Let's play Evil Hangman!");
        label2 = new JLabel("Secret Word: "+game.displayGameState());
        label2.setFont(new Font("Default",Font.PLAIN,23));
        label3 = new JLabel(String.valueOf("Guesses Remaining: "+ game.numGuessesRemaining() +"\n"));
        result = new JLabel("");
        result.setForeground(Color.red);
    }
    public void addLabels()
    {
    	 frame.add(label1);
         frame.add(label2);
         frame.add(label3);
         frame.add(result);
    }
    public void addButtons()
    {
    	 //add user choice
        for(int i = 65; i<91;i++)
        {
            char x = (char)i;
            JButton tempBtn = new JButton(String.valueOf(x));
            tempBtn.addActionListener(this);
            frame.add(tempBtn);
            
        }
    }
    /*
     * This is called when the user clicks any of the buttons in the UI.
     */
    public void actionPerformed(ActionEvent e)
    {
        //to figure out which button the user pressed
        JButton temp = (JButton)e.getSource();
        temp.setEnabled(false);
        InputLetter = temp.getText().charAt(0);
        check(InputLetter); // make sure it's a valid choice
        game=game.controller(InputLetter, result, label2, label3, frame, game);
        
    }

    
    public boolean isEvil()
    {
    	if(game instanceof EvilHangMan)
    		return true;
    	else 
    		return false;
    }

    public boolean check(char input)
    {
        //do the input check. Player can just input the English letters.
        if((input >= 'a' && input <= 'z') || (input >= 'A' && input <= 'Z'))
            return true;
        else
            return false;
    }
    
}