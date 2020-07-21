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

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public class settingsGui {

	public JFrame frame = new JFrame();

	public JTextField nameF = new JTextField();

	
	
	public JRadioButton allWeb  = new JRadioButton();
	public JRadioButton rbRecreation = new JRadioButton();
	public JRadioButton rbSports  = new JRadioButton();
	public JRadioButton rbShopping  = new JRadioButton();
	public JRadioButton rbGames  = new JRadioButton();
	public JRadioButton rbAdult  = new JRadioButton();
	public JRadioButton rbHome  = new JRadioButton();
	
	
	
	
	
	
	public JRadioButton allYT  = new JRadioButton();
	public JRadioButton rbComedy = new JRadioButton();
	public JRadioButton rbFilmAnima  = new JRadioButton();
	public JRadioButton rbAnimals  = new JRadioButton();
	public JRadioButton rbSportsyt  = new JRadioButton();
	public JRadioButton rbTravel  = new JRadioButton();
	public JRadioButton rbGaming  = new JRadioButton();
	public JRadioButton rbPeople  = new JRadioButton();
	public JRadioButton rbEntertainment  = new JRadioButton();
	public JRadioButton rbStyle  = new JRadioButton();


	public void startSettingGui() throws IOException 
	{
		System.out.println("Creating Setting Gui");
		frame.setBounds(100, 100, 500, 600);
		frame.setTitle("ZoneIn-Settings");
		frame.setResizable(false);
		Image icon = Toolkit.getDefaultToolkit().getImage("/Applications/ZoneIn/Icons/jlogoicon.png");
		frame.setIconImage(icon);
		
		JPanel pane = new JPanel();
		frame.add(pane);
		pane.setLayout(null);
		pane.setBackground(new Color(255,248,242));
		
		
		JLabel nameL = new JLabel();
		pane.add(nameL);
		nameL.setText("Change Name:");
		nameL.setFont(new Font("Sans-Serif", Font.PLAIN, 15));
		nameL.setBounds(10, 50, 120, 20);
		
		pane.add(nameF);
		nameF.setBounds(120, 50, 100, 20);
		
		JLabel websiteTagsL = new JLabel();
		pane.add(websiteTagsL);
		websiteTagsL.setText("What kind of websites would you like to block? ");
		websiteTagsL.setFont(new Font("Sans-Serif", Font.BOLD, 15));
		websiteTagsL.setBounds(10, 100, 380, 20);
		
		
		pane.add(allWeb);
		allWeb.setBounds(10, 130, 120, 30);
		allWeb.setText("AI AutoPilot");
		allWeb.setBackground(new Color(255,248,242));
		allWeb.setFont(new Font("Sans-Serif", Font.PLAIN, 15));
		allWeb.addActionListener(new clearAll());
		
		
		pane.add(rbRecreation);
		rbRecreation.setBounds(10, 160, 120, 30);
		rbRecreation.setText("Recreation");
		rbRecreation.setBackground(new Color(255,248,242));
		rbRecreation.setFont(new Font("Sans-Serif", Font.PLAIN, 15));
		rbRecreation.addActionListener(new deselectAI());
		
		pane.add(rbSports);
		rbSports.setBounds(10, 190, 100, 30);
		rbSports.setText("Sports");
		rbSports.setBackground(new Color(255,248,242));
		rbSports.setFont(new Font("Sans-Serif", Font.PLAIN, 15));
		rbSports.addActionListener(new deselectAI());

		pane.add(rbShopping);
		rbShopping.setBounds(10, 220, 100, 30);
		rbShopping.setText("Shopping");
		rbShopping.setBackground(new Color(255,248,242));
		rbShopping.setFont(new Font("Sans-Serif", Font.PLAIN, 15));
		rbShopping.addActionListener(new deselectAI());
		
		pane.add(rbGames);
		rbGames.setBounds(150, 160, 100, 30);
		rbGames.setText("Games");
		rbGames.setBackground(new Color(255,248,242));
		rbGames.setFont(new Font("Sans-Serif", Font.PLAIN, 15));
		rbGames.addActionListener(new deselectAI());
		
		pane.add(rbAdult);
		rbAdult.setBounds(150, 190, 100, 30);
		rbAdult.setText("Adult");
		rbAdult.setBackground(new Color(255,248,242));
		rbAdult.setFont(new Font("Sans-Serif", Font.PLAIN, 15));
		rbAdult.addActionListener(new deselectAI());
		
		pane.add(rbHome);
		rbHome.setBounds(150, 220, 100, 30);
		rbHome.setText("Home");
		rbHome.setBackground(new Color(255,248,242));
		rbHome.setFont(new Font("Sans-Serif", Font.PLAIN, 15));
		rbHome.addActionListener(new deselectAI());
		
		
		JLabel ytTagsL = new JLabel();
		pane.add(ytTagsL);
		ytTagsL.setText("What kind of YouTube videos would you like to block?");
		ytTagsL.setFont(new Font("Sans-Serif", Font.BOLD, 15));
		ytTagsL.setBounds(10, 280, 430, 20);
		
		pane.add(allYT);
		allYT.setBounds(10, 310, 230, 30);
		allYT.setText("All Youtube Entertainment");
		allYT.setBackground(new Color(255,248,242));
		allYT.setFont(new Font("Sans-Serif", Font.PLAIN, 15));
		allYT.addActionListener(new clearAllYT());
		
		pane.add(rbComedy);
		rbComedy.setBounds(10, 340, 130, 30);
		rbComedy.setText("Comedy");
		rbComedy.setBackground(new Color(255,248,242));
		rbComedy.setFont(new Font("Sans-Serif", Font.PLAIN, 15));
		rbComedy.addActionListener(new deselectYT());
		
		pane.add(rbFilmAnima);
		rbFilmAnima.setBounds(10, 370, 160, 30);
		rbFilmAnima.setText("Film & Animation");
		rbFilmAnima.setBackground(new Color(255,248,242));
		rbFilmAnima.setFont(new Font("Sans-Serif", Font.PLAIN, 15));
		rbFilmAnima.addActionListener(new deselectYT());
		
		
		pane.add(rbAnimals);
		rbAnimals.setBounds(10, 400, 150, 30);
		rbAnimals.setText("Pets & Animals");
		rbAnimals.setBackground(new Color(255,248,242));
		rbAnimals.setFont(new Font("Sans-Serif", Font.PLAIN, 15));
		rbAnimals.addActionListener(new deselectYT());
		
		pane.add(rbSportsyt);
		rbSportsyt.setBounds(170, 340, 130, 30);
		rbSportsyt.setText("Sports");
		rbSportsyt.setBackground(new Color(255,248,242));
		rbSportsyt.setFont(new Font("Sans-Serif", Font.PLAIN, 15));
		rbSportsyt.addActionListener(new deselectYT());
		
		pane.add(rbTravel);
		rbTravel.setBounds(170, 370, 150, 30);
		rbTravel.setText("Travel & Events");
		rbTravel.setBackground(new Color(255,248,242));
		rbTravel.setFont(new Font("Sans-Serif", Font.PLAIN, 15));
		rbTravel.addActionListener(new deselectYT());
		
		pane.add(rbGaming);
		rbGaming.setBounds(170, 400, 130, 30);
		rbGaming.setText("Gaming");
		rbGaming.setBackground(new Color(255,248,242));
		rbGaming.setFont(new Font("Sans-Serif", Font.PLAIN, 15));
		rbGaming.addActionListener(new deselectYT());
		
		pane.add(rbPeople);
		rbPeople.setBounds(310, 340, 150, 30);
		rbPeople.setText("People & Blogs");
		rbPeople.setBackground(new Color(255,248,242));
		rbPeople.setFont(new Font("Sans-Serif", Font.PLAIN, 15));
		rbPeople.addActionListener(new deselectYT());
		
		pane.add(rbEntertainment);
		rbEntertainment.setBounds(310, 370, 150, 30);
		rbEntertainment.setText("Entertainment");
		rbEntertainment.setBackground(new Color(255,248,242));
		rbEntertainment.setFont(new Font("Sans-Serif", Font.PLAIN, 15));
		rbEntertainment.addActionListener(new deselectYT());
		
		pane.add(rbStyle);
		rbStyle.setBounds(310, 400, 150, 30);
		rbStyle.setText("Howto & Style");
		rbStyle.setBackground(new Color(255,248,242));
		rbStyle.setFont(new Font("Sans-Serif", Font.PLAIN, 15));
		rbStyle.addActionListener(new deselectYT());
		
		
		
		
		
		BufferedReader blockTagsSelect = new BufferedReader(new FileReader("/Applications/ZoneIn/WritableFiles/blockTags.txt"));

		StringBuffer stringBuffer = new StringBuffer("");
		// for reading one line
		String line = null;
		// keep reading till readLine returns null
		while ((line = blockTagsSelect.readLine()) != null) {
		    // keep appending last line read to buffer
		    switch(line) {
		    case "ALL":
		    	allWeb.setSelected(true);
		    	break;
		    case "Recreation":
		    	rbRecreation.setSelected(true);
		    	break;
		    case "Sports":
		    	rbSports.setSelected(true);
		    	break;
		    case "Shopping":
		    	rbShopping.setSelected(true);
		    	break;
		    case "Games":
		    	rbGames.setSelected(true);
		    	break;
		    case "Adult":
		    	rbAdult.setSelected(true);
		    	break;
		    case "Home":
		    	rbHome.setSelected(true);
		    	break;
		    }
		}
		
		BufferedReader blockTagsYTSelect = new BufferedReader(new FileReader("/Applications/ZoneIn/WritableFiles/blockTagsYT.txt"));

		StringBuffer stringBufferYT = new StringBuffer("");
		// for reading one line
		String lineYT = null;
		// keep reading till readLine returns null
		while ((lineYT = blockTagsYTSelect.readLine()) != null) {
		    // keep appending last line read to buffer
		    switch(lineYT) {
		    case "ALL":
		    	allYT.setSelected(true);
		    	break;
		    case "Comedy":
		    	rbComedy.setSelected(true);
		    	break;
		    case "Film & Animation":
		    	rbFilmAnima.setSelected(true);
		    	break;
		    case "Sports":
		    	rbSportsyt.setSelected(true);
		    	break;
		    case "Travel & Events":
		    	rbTravel.setSelected(true);
		    	break;
		    case "Gaming":
		    	rbGaming.setSelected(true);
		    	break;
		    case "People & Blogs":
		    	rbPeople.setSelected(true);
		    	break;
		    case "Entertainment":
		    	rbEntertainment.setSelected(true);
		    	break;
		    case "Howto & Style":
		    	rbStyle.setSelected(true);
		    	break;
		    }
		}
		
		
		
		
		
		
		BufferedImage buttonIconSAV = null;
		try {
			buttonIconSAV = ImageIO.read(new File("/Applications/ZoneIn/Icons/jbuttonSAV.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JButton button1 = new JButton(new ImageIcon(buttonIconSAV));
		pane.add(button1);
		button1.setFocusPainted(false);
		button1.setContentAreaFilled(false);
		button1.setBounds(170, 500, 140, 40);
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
			try {

				if(nameF.getText().isEmpty()) 
				{
					System.out.println("No Name");

				}
				else 
				{
					PrintWriter writer;
					writer = new PrintWriter("/Applications/ZoneIn/WritableFiles/name.txt", "UTF-8");
					writer.print(nameF.getText());
					writer.close();

				}
				

		
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Saved Changes!");
			
			if(allWeb.isSelected()) 
			{
				try {
					PrintWriter writer;

					writer = new PrintWriter("/Applications/ZoneIn/WritableFiles/blockTags.txt", "UTF-8");
					writer.print("ALL");
					writer.close();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else 
			{
				
				try {
					PrintWriter writer;

					writer = new PrintWriter("/Applications/ZoneIn/WritableFiles/blockTags.txt", "UTF-8");
					if(rbRecreation.isSelected()) 
					{
						writer.println("Recreation");

					}
					if(rbSports.isSelected()) 
					{
						writer.println("Sports");
					}
					
					if(rbShopping.isSelected()) 
					{
						writer.println("Shopping");

					}
					if(rbGames.isSelected()) 
					{
						writer.println("Games");

					}
					if(rbAdult.isSelected()) 
					{
						writer.println("Adult");
					}
					
					if(rbHome.isSelected()) 
					{
						writer.println("Home");

					}
					writer.close();

				} catch (FileNotFoundException | UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
			
			
			
			
			if(allYT.isSelected()) 
			{
				try {
					PrintWriter writer;

					writer = new PrintWriter("/Applications/ZoneIn/WritableFiles/blockTagsYT.txt", "UTF-8");
					writer.print("ALL");
					writer.close();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else 
			{
			
				try {
					PrintWriter writer;

					writer = new PrintWriter("/Applications/ZoneIn/WritableFiles/blockTagsYT.txt", "UTF-8");
					if(rbComedy.isSelected()) 
					{
						writer.println("Comedy");

					}
					if(rbFilmAnima.isSelected()) 
					{
						writer.println("Film & Animation");
					}
					
					if(rbAnimals.isSelected()) 
					{
						writer.println("Pets & Animals");

					}
					if(rbSportsyt.isSelected()) 
					{
						writer.println("Sports");

					}
					if(rbTravel.isSelected()) 
					{
						writer.println("Travel & Events");
					}
					
					if(rbGaming.isSelected()) 
					{
						writer.println("Gaming");

					}
					if(rbPeople.isSelected()) 
					{
						writer.println("People & Blogs");

					}
					if(rbEntertainment.isSelected()) 
					{
						writer.println("Entertainment");
					}
					
					if(rbStyle.isSelected()) 
					{
						writer.println("Howto & Style");

					}
					writer.close();

				} catch (FileNotFoundException | UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			JOptionPane.showMessageDialog(null, "Changes Saved! Closing ZoneIn. Re-open application to update the changes");
			System.exit(0);
		}
	}
	
	
	
	
	
	public class deselectAI implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			if(allWeb.isSelected()) 
			{
				allWeb.setSelected(false);
			}
		}
	}
	
	public class clearAll implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			if(allWeb.isSelected()) 
			{
				rbRecreation.setSelected(false);
				rbSports.setSelected(false);
				rbShopping.setSelected(false);
				rbGames.setSelected(false);
				rbAdult.setSelected(false);
				rbHome.setSelected(false);
			}
		}
	}
	
	public class deselectYT implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			if(allYT.isSelected()) 
			{
				allYT.setSelected(false);
			}
		}
	}
	
	public class clearAllYT implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			if(allYT.isSelected()) 
			{
				rbComedy.setSelected(false);
				rbFilmAnima.setSelected(false);
				rbAnimals.setSelected(false);
				rbSportsyt.setSelected(false);
				rbTravel.setSelected(false);
				rbGaming.setSelected(false);
				rbPeople.setSelected(false);
				rbEntertainment.setSelected(false);
				rbStyle.setSelected(false);
			}
		}
	}
		
	
	

}