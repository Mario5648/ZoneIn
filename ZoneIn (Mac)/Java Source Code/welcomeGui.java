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
import java.awt.*;
public class welcomeGui
{
	
	public JTextField enF = new JTextField();
	public JFrame frame = new JFrame();

	public void startWelcome() 
	{
		System.out.println("Creating Welcome Gui");
		frame.setBounds(100, 100, 400, 400);
		frame.setTitle("ZoneIn-Welcome");
		frame.setResizable(false);
		Image icon = Toolkit.getDefaultToolkit().getImage("/Applications/ZoneIn/Icons/jlogoicon.png");
		frame.setIconImage(icon);
		
		
		
		
		//Generate zonein logo 
		BufferedImage logoLocation = null;
		try {
			logoLocation = ImageIO.read(new File("/Applications/ZoneIn/Icons/jlogo.png"));

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
		logo.setBounds(120, 0, 128, 128);
		
		JLabel zt = new JLabel();
		pane.add(zt);
		zt.setText("Welcome to ZoneIn!");
		zt.setForeground(new Color(255, 102, 77));
		zt.setBounds(40, 100, 320, 40);
		zt.setFont(new Font("Serif", Font.BOLD, 35));
		
		JLabel intro1L = new JLabel();
		pane.add(intro1L);
		intro1L.setText("This Application blocks entertainment websites and");
		intro1L.setFont(new Font("Serif", Font.PLAIN, 15));
		intro1L.setBounds(40, 150, 350, 20);
		JLabel intro2L = new JLabel();
		pane.add(intro2L);
		intro2L.setText("Youtube videos to allow you to study much more ");
		intro2L.setFont(new Font("Serif", Font.PLAIN, 15));
		intro2L.setBounds(40, 170, 350, 20);
		JLabel intro3L = new JLabel();
		pane.add(intro3L);
		intro3L.setText("effectively all through the power of AI.");
		intro3L.setFont(new Font("Serif", Font.PLAIN, 15));
		intro3L.setBounds(40, 190, 350, 20);
		
		JLabel en = new JLabel();
		pane.add(en);
		en.setText("Enter Your Name: ");
		en.setFont(new Font("Serif", Font.PLAIN, 15));
		en.setBounds(20, 260, 150, 20);
		
		pane.add(enF);
		enF.setBounds(140, 263, 150, 20);
		
		BufferedImage buttonIconSUB = null;
		try {
			buttonIconSUB = ImageIO.read(new File("/Applications/ZoneIn/Icons/jbuttonSUB.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JButton button1 = new JButton(new ImageIcon(buttonIconSUB));
		pane.add(button1);
		button1.setFocusPainted(false);
		button1.setContentAreaFilled(false);
		button1.setBounds(120, 300, 140, 40);
		button1.addActionListener(new writeName());

		
		

		JLabel versionL = new JLabel();
		pane.add(versionL);
		versionL.setText("ZoneIn V 1.5");
		versionL.setBounds(300, 340, 150, 20);
		
		
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	
	
	
	public class writeName implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			System.out.println("Writing Name");
			PrintWriter writer;
			try {
				writer = new PrintWriter("/Applications/ZoneIn/WritableFiles/name.txt", "UTF-8");
				writer.print(enF.getText());
				writer.close();
				
				writer = new PrintWriter("/Applications/ZoneIn/WritableFiles/firstTime.txt", "UTF-8");
				writer.print("FALSE");
				writer.close();
				
				frame.dispose();
				firstTimeMain m = new firstTimeMain();
				try {
					m.startMain();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}
}
