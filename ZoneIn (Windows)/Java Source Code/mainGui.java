import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.*;
public class mainGui {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader wgin = new BufferedReader(new FileReader("C:\\ZoneIn\\WritableFiles\\firstTime.txt"));

		if (wgin.readLine().equals("TRUE")) 
		{
			welcomeGui wg = new welcomeGui();
			wg.startWelcome();
		}else 
		{
			

			BufferedReader namein = new BufferedReader(new FileReader("C:\\ZoneIn\\WritableFiles\\name.txt"));
			String nameS = namein.readLine();
			System.out.println("Creating Main Gui");
			JFrame frame = new JFrame();
			frame.setBounds(100, 100, 170, 400);
			frame.setTitle("ZoneIn");
			frame.setResizable(false);
			Image icon = Toolkit.getDefaultToolkit().getImage("C:\\ZoneIn\\Icons\\jlogoicon.png");
			frame.setIconImage(icon);
			
			
			
			//Generate zonein logo 
			BufferedImage logoLocation = null;
			try {
				logoLocation = ImageIO.read(new File("C:\\ZoneIn\\Icons\\jlogo.png"));
	
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JLabel logo = new JLabel(new ImageIcon(logoLocation));
	
			
			JPanel pane = new JPanel();
			frame.add(pane);
			pane.setLayout(null);
			pane.setBackground(new Color(255,248,242));
			pane.add(logo);
			logo.setBounds(15, 0, 128, 128);
			
			
			
			
			JLabel welcomeL = new JLabel();
			pane.add(welcomeL);
			welcomeL.setText("Welcome "+nameS+"!");
			welcomeL.setBounds(10, 110, 170, 20);
			
			
			
			
			
			BufferedImage buttonIconH = null;
			try {
				buttonIconH = ImageIO.read(new File("C:\\ZoneIn\\Icons\\jbuttonH.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			BufferedImage buttonIconJ = null;
			try {
				buttonIconJ = ImageIO.read(new File("C:\\ZoneIn\\Icons\\jbuttonJ.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			BufferedImage buttonIconS = null;
			try {
				buttonIconS = ImageIO.read(new File("C:\\ZoneIn\\Icons\\jbuttonS.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			BufferedImage jsetting = null;
			try {
				jsetting = ImageIO.read(new File("C:\\ZoneIn\\Icons\\jsetting.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			JButton button1 = new JButton(new ImageIcon(buttonIconH));
			JButton button2 = new JButton(new ImageIcon(buttonIconJ));
			JButton button3 = new JButton(new ImageIcon(buttonIconS));
			
			JButton setting = new JButton(new ImageIcon(jsetting));
			
			pane.add(button1);
			button1.setFocusPainted(false);
			button1.setContentAreaFilled(false);
			button1.setBounds(8, 150, 140, 40);
			button1.addActionListener(new launchHost());
			
			
			
			pane.add(button2);
			button2.setFocusPainted(false);
			button2.setContentAreaFilled(false);
			button2.setBounds(8, 200, 140, 40);
			button2.addActionListener(new launchClient());
			
			pane.add(button3);
			button3.setFocusPainted(false);
			button3.setContentAreaFilled(false);
			button3.setBounds(8, 250, 140, 40);
			button3.addActionListener(new startSingle());
			
			
			pane.add(setting);
			setting.setFocusPainted(false);
			setting.setContentAreaFilled(false);
			setting.setBounds(10, 320, 32, 32);
			setting.addActionListener(new launchSettings());

			
			
			JLabel versionL = new JLabel();
			pane.add(versionL);
			versionL.setText("ZoneIn V 1.5");
			versionL.setBounds(80, 340, 150, 20);
			
			frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
			frame.setVisible(true);
			
			System.out.println("Finished Gui");
		}
	}
	
	public static class startSingle implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			System.out.println("Creating Single Session Gui");
			singleSessionGui ss = new singleSessionGui();
			ss.startGui();
		}
	}
	
	public static class launchSettings implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			System.out.println("Creating Setting Gui");
			settingsGui sg = new settingsGui();
			sg.startSettingGui();
		}
	}
	
	public static class launchHost implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			System.out.println("Creating Host Gui");
			hostGui hg = new hostGui();
			hg.startHostGui();
		}
	}
	
	public static class launchClient implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			System.out.println("Creating Join Gui");
			joinGui jg = new joinGui();
			jg.startJoin();
		}
	}

}
