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

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class settingsGui {

	public JFrame frame = new JFrame();

	public JTextField nameF = new JTextField();

	
	public void startSettingGui() 
	{
		System.out.println("Creating Setting Gui");
		frame.setBounds(100, 100, 300, 400);
		frame.setTitle("ZoneIn-Settings");
		frame.setResizable(false);
		Image icon = Toolkit.getDefaultToolkit().getImage("C:\\ZoneIn\\Icons\\jlogoicon.png");
		frame.setIconImage(icon);
		
		JPanel pane = new JPanel();
		frame.add(pane);
		pane.setLayout(null);
		pane.setBackground(new Color(255,248,242));
		
		
		JLabel nameL = new JLabel();
		pane.add(nameL);
		nameL.setText("Change Name:");
		nameL.setBounds(10, 50, 120, 20);
		
		pane.add(nameF);
		nameF.setBounds(100, 50, 100, 20);
		
		
		BufferedImage buttonIconSAV = null;
		try {
			buttonIconSAV = ImageIO.read(new File("C:\\ZoneIn\\Icons\\jbuttonSAV.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JButton button1 = new JButton(new ImageIcon(buttonIconSAV));
		pane.add(button1);
		button1.setFocusPainted(false);
		button1.setContentAreaFilled(false);
		button1.setBounds(75, 300, 140, 40);
		button1.addActionListener(new saveChanges());
		
		
		frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		
		System.out.println("Finished Gui");
	}
	
	
	public class saveChanges implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			System.out.println("Saving Changes");
			PrintWriter writer;
			try {
				writer = new PrintWriter("C:\\ZoneIn\\WritableFiles\\name.txt", "UTF-8");
				writer.print(nameF.getText());
				writer.close();
				JOptionPane.showMessageDialog(null, "Changes Saved! Closing ZoneIn. Re-open application to update the changes");
				System.exit(0);
		
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Saved Changes!");
		}
	}
}
