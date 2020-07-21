import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;



public class hostGui {
	
	
	public boolean numeric = true;
	public int totalSeconds = 0 ;
	public JTextField hoursF = new JTextField();
	public JTextField minF = new JTextField();
    public JTextField passF = new JTextField();
	public JLabel onofI;
	public JLabel onofL = new JLabel();
	public Timer timer;
	public JButton startSession;
	public void startHostGui() 
	{
		System.out.println("Creating Host Gui");
		JFrame frame = new JFrame();
		frame.setBounds(100, 100, 300, 300);
		frame.setTitle("ZoneIn-Host");
		frame.setResizable(false);
		Image icon = Toolkit.getDefaultToolkit().getImage("/Applications/ZoneIn/Icons/jlogoicon.png");
		frame.setIconImage(icon);
		
		JPanel pane = new JPanel();
		frame.add(pane);
		pane.setLayout(null);
		pane.setBackground(new Color(255,248,242));

		
		JLabel passL = new JLabel();
		pane.add(passL);
		passL.setText("Enter a Password:");
		passL.setFont(new Font("Sans-Serif", Font.PLAIN, 15));
		passL.setBounds(10, 30, 150, 20);
		
		
		pane.add(passF);
		passF.setBounds(140,30,100,20);
		
		JLabel hoursL = new JLabel();
		pane.add(hoursL);
		hoursL.setText("Enter Hours:");
		hoursL.setFont(new Font("Sans-Serif", Font.PLAIN, 15));
		hoursL.setBounds(10,60,100,20);
		
		pane.add(hoursF);
		hoursF.setBounds(140,60,50,20);
		
		JLabel minL = new JLabel();
		pane.add(minL);
		minL.setText("Enter Minutes:");
		minL.setFont(new Font("Sans-Serif", Font.PLAIN, 15));
		minL.setBounds(10, 90, 120, 20);
		
		pane.add(minF);
		minF.setBounds(140,90,50,20);
		
		
		
		
		//Generate zonein logo 
		BufferedImage onofIM = null;
		try {
			onofIM = ImageIO.read(new File("/Applications/ZoneIn/Icons/offlineIcon.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		onofI = new JLabel(new ImageIcon(onofIM));
		
		pane.add(onofI);
		onofI.setBounds(10, 120, 40, 40);
		
		pane.add(onofL);
		onofL.setText("Offline");
		onofL.setBounds(50, 130, 80, 20);
		onofL.setFont(new Font("Sans-Serif", Font.PLAIN, 15));

		
		
		
		BufferedImage buttonIconSS = null;
		try {
			buttonIconSS = ImageIO.read(new File("/Applications/ZoneIn/Icons/jbuttonSS.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		startSession = new JButton(new ImageIcon(buttonIconSS));
		pane.add(startSession);
		startSession.setBounds(70, 200, 140, 40);
		startSession.addActionListener(new startHostSession());
		
		frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		
	}
	
	
	public class startHostSession implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			PrintWriter writer;
			totalSeconds = checkTime(hoursF.getText(),minF.getText());
			if (totalSeconds > -1)
			{
				System.out.println("Starting Host");

				try {
					writer = new PrintWriter("/Applications/ZoneIn/WritableFiles/GroupSessionSeconds.txt", "UTF-8");
					writer.print(totalSeconds);
					writer.close();
					
					writer = new PrintWriter("/Applications/ZoneIn/WritableFiles/GroupSessionPassword.txt", "UTF-8");
					writer.print(passF.getText());
					writer.close();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				MyThread mt = new MyThread ();
			    mt.start ();
				
				
				BufferedImage onofIM = null;
				try {
					onofIM = ImageIO.read(new File("/Applications/ZoneIn/Icons/onlineIcon.png"));

				} catch (IOException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
				onofI.setIcon(new ImageIcon(onofIM));
				onofL.setText("Online");
				
				startSession.setEnabled(false);

				 ActionListener taskPerformer = new ActionListener() {
			            public void actionPerformed(ActionEvent evt) {
			            	
			            	totalSeconds -=1;
			            	if (totalSeconds == 0) 
			            	{
			            		timer.setRepeats(false);
			            		BufferedImage onofIM = null;
			    				try {
			    					onofIM = ImageIO.read(new File("/Applications/ZoneIn/Icons/offlineIcon.png"));

			    				} catch (IOException e3) {
			    					// TODO Auto-generated catch block
			    					e3.printStackTrace();
			    				}
			    				onofI.setIcon(new ImageIcon(onofIM));
			    				onofL.setText("Offline");
			    				startSession.setEnabled(true);
			    				System.out.println("[FINISHED] Finished Hosting!");

			            	}

			            }
			        };
			        timer = new Timer(1000 ,taskPerformer);
			        timer.setRepeats(true);
			        timer.start();
			}
		}
	}
	
	public int checkTime(String hour,String minute) 
	{
		int h = 0;
		int m = 0;
		try 
    	{
    		h = Integer.parseInt(hour);
    		m = Integer.parseInt(minute);
    	}catch (NumberFormatException e) {
            numeric = false;
        }
    	
		if(numeric) 
		{
			if (h <= 24 && h>=0 && m<=59 && m>=0) 
			{
				int totalMinutes = (h*60) + m;
		        totalSeconds = totalMinutes * 60;
		        return totalSeconds;
			}
		}else 
		{
			JOptionPane.showMessageDialog(null, "ERROR INVALID TIME! Hours should be less than 24 and minutes should be less than 60");
		}
		numeric = true;
		return -1;
    	
	}
	
	class MyThread extends Thread
	{
	   public void run ()
	   {
		   try {
				System.out.println("[Start]Launching Python");
				ProcessBuilder pb = new ProcessBuilder("/usr/local/bin/python3","/Applications/ZoneIn/Python/startGSession.py");
				Process p = pb.start();
				p.waitFor();
				}catch(Exception ed) 
				{
					ed.printStackTrace();
				}
	   }
	}

}