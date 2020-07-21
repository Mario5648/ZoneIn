import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.awt.*;




public class singleSessionGui {
	
	
	public JTextField hoursF = new JTextField();
	public JTextField minutesF = new JTextField();
	public JLabel countDownTimer = new JLabel();
	public JButton startSession;
	public JButton quitSession;

	public int hours = 0;
	public int minutes = 0;
	public int seconds = 0;
	public int totalSeconds = 0;
	public Timer timer;
	public boolean numeric = true;
	public void startGui() 
	{
		System.out.println("[START] Creating Gui Single Session");
		JFrame frame = new JFrame();
		frame.setBounds(100, 100, 200, 400);
		frame.setTitle("ZoneIn-SingleSession");
		frame.setResizable(false);
		Image icon = Toolkit.getDefaultToolkit().getImage("/Applications/ZoneIn/Icons/jlogoicon.png");
		frame.setIconImage(icon);
		
		
		JPanel pane = new JPanel();
		frame.add(pane);
		pane.setLayout(null);
		pane.setBackground(new Color(255,248,242));
		
		JLabel hoursL = new JLabel();
		pane.add(hoursL);
		hoursL.setText("Enter Hours: ");
		hoursL.setFont(new Font("Sans-Serif", Font.PLAIN, 15));
		hoursL.setBounds(10, 20, 100, 20);
		
		JLabel minutesL = new JLabel();
		pane.add(minutesL);
		minutesL.setText("Enter Minutes: ");
		minutesL.setFont(new Font("Sans-Serif", Font.PLAIN, 15));
		minutesL.setBounds(10, 60, 120, 20);
		
		pane.add(hoursF);
		hoursF.setBounds(120, 20, 40, 20);
		
		
		pane.add(minutesF);
		minutesF.setBounds(120, 60, 40, 20);
		
		
		
		pane.add(countDownTimer);
		countDownTimer.setText("00:00:00");
		countDownTimer.setFont(new Font("Sans-Serif", Font.PLAIN, 35));
		countDownTimer.setBounds(25, 100, 170, 70);
		countDownTimer.setForeground(new Color(255,109,77));
		
		
		
		BufferedImage buttonIconSS = null;
		try {
			buttonIconSS = ImageIO.read(new File("/Applications/ZoneIn/Icons/jbuttonSS.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		startSession = new JButton(new ImageIcon(buttonIconSS));
		pane.add(startSession);
		startSession.setFocusPainted(false);
		startSession.setContentAreaFilled(false);
		startSession.setBounds(25, 200, 140, 40);
		startSession.addActionListener(new startSinglePython());
		
		BufferedImage buttonIconQ = null;
		try {
			buttonIconQ = ImageIO.read(new File("/Applications/ZoneIn/Icons/jbuttonQ.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		quitSession = new JButton(new ImageIcon(buttonIconQ));
		pane.add(quitSession);
		quitSession.setFocusPainted(false);
		quitSession.setContentAreaFilled(false);
		quitSession.setBounds(25, 270, 140, 40);
		quitSession.addActionListener(new quitSingle());
		
		frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
		
		frame.setVisible(true);
		System.out.println("[FINISHED] Done with Single Session Gui");
	}
	
	
	public class quitSingle implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			timer.setRepeats(false);
			startSession.setEnabled(true);
			URL url;
			try {
				url = new URL("http://127.0.0.1:5000/shutdown");
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("GET");
				int responseCode = connection.getResponseCode();
				System.out.println(responseCode);
				totalSeconds = 0;
				System.out.println("[SHUTDOWN] Shutting down Python Server");
			} catch (IOException e4) {
				e4.printStackTrace();
			}
			System.out.println("[FINISHED] Finished Session!");
			countDownTimer.setText("00:00:00");

		}
	
	}
	public class startSinglePython implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
		    
			totalSeconds = checkTime(hoursF.getText(),minutesF.getText());
			if (totalSeconds > -1)
			{
				MyThread mt = new MyThread ();
			    mt.start ();
				System.out.println("[Start]Starting Timer");
				startSession.setEnabled(false);
		        ActionListener taskPerformer = new ActionListener() {
		            public void actionPerformed(ActionEvent evt) {
		            	
		            	minutes = totalSeconds/60;
		            	seconds = totalSeconds % 60;
		            	hours = minutes / 60;
		            	minutes = minutes % 60;
		            	
		            	String tH = null;
		            	String tM = null;
		            	String tS = null;
		            	if(hours < 10) 
		            	{
		            		tH = "0"+Integer.toString(hours);
		            	}else 
		            	{
		            		tH = Integer.toString(hours);
		            	}
		            	if(minutes < 10)
		            	{
		            		 
		            		tM = "0"+Integer.toString(minutes);
		            	}else 
		            	{
		            		tM = Integer.toString(minutes);
		            	}
		            	if(seconds < 10) 
		            	{
		            		tS = "0"+Integer.toString(seconds);
		            	}else 
		            	{
		            		tS = Integer.toString(seconds);
		            	}
		            	
		            	String timeLeft = tH+":"+tM+":"+tS;

		            	countDownTimer.setText(timeLeft);
		            	totalSeconds -=1;
		            	if (totalSeconds == 0) 
		            	{
		            		timer.setRepeats(false);
		    				startSession.setEnabled(true);
		    				URL url;
							try {
								url = new URL("http://127.0.0.1:5000/shutdown");
			    				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			    				connection.setRequestMethod("GET");
			    				int responseCode = connection.getResponseCode();
			    				System.out.println(responseCode);
			    				countDownTimer.setText("00:00:00");
			    				System.out.println("[SHUTDOWN] Shutting down Python Server");
							} catch (IOException e) {
								e.printStackTrace();
							}
		    				System.out.println("[FINISHED] Finished Session!");
		    				countDownTimer.setText("00:00:00");

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
				ProcessBuilder pb = new ProcessBuilder("/usr/local/bin/python3","/Applications/ZoneIn/Python/main.py");
				Process p = pb.start();
				p.waitFor();
				}catch(Exception ed) 
				{
					ed.printStackTrace();
				}
	   }
	}
}
