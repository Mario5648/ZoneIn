import java.awt.Color;
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


public class joinGui {
	
	public JTextField ipF = new JTextField();
	public JFrame frame = new JFrame();

	public void startJoin() 
	{
		System.out.println("Creating Join Gui");
		frame.setBounds(100, 100, 300, 200);
		frame.setTitle("ZoneIn-Join");
		frame.setResizable(false);
		Image icon = Toolkit.getDefaultToolkit().getImage("/Applications/ZoneIn/Icons/jlogoicon.png");
		frame.setIconImage(icon);
		
		JPanel pane = new JPanel();
		frame.add(pane);
		pane.setLayout(null);
		pane.setBackground(new Color(255,248,242));
		
		
		JLabel ipL = new JLabel();
		pane.add(ipL);
		ipL.setText("Enter the Host's IP:");
		ipL.setBounds(10, 30, 150, 20);
		
		pane.add(ipF);
		ipF.setBounds(140, 30, 100, 20);
		
		
		BufferedImage buttonIconJ = null;
		try {
			buttonIconJ = ImageIO.read(new File("/Applications/ZoneIn/Icons/jbuttonJ.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JButton button1 = new JButton(new ImageIcon(buttonIconJ));
		pane.add(button1);
		button1.setBounds(75, 100, 140, 40);
		button1.addActionListener(new writeIP());

		frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
	}
	
	
	public class writeIP implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			System.out.println("Writing IP");
			PrintWriter writer;
			try {
				writer = new PrintWriter("/Applications/ZoneIn/WritableFiles/HostIP.txt", "UTF-8");
				writer.print(ipF.getText());
				writer.close();
				
				
				
				MyThread mt = new MyThread();
				mt.start();
				
				MyThreadSS mtSS = new MyThreadSS();
				mtSS.start();
				
				frame.dispose();
				
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}
	
	class MyThread extends Thread
	{
	   public void run ()
	   {
		   try {
				System.out.println("[Start]Launching Python");
				ProcessBuilder pb = new ProcessBuilder("/usr/local/bin/python3","/Applications/ZoneIn/Python/client.py");
				Process p = pb.start();
				p.waitFor();
				}catch(Exception ed) 
				{
					ed.printStackTrace();
				}
	   }
	}
	
	class MyThreadSS extends Thread
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
