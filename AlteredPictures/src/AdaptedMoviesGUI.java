import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.text.*;
import javax.swing.BorderFactory; 
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.util.ArrayList;
import java.awt.List;
import java.io.FileNotFoundException;
import java.io.*;


public class AdaptedMoviesGUI extends JFrame //implements ActionListener,
				//ItemListener,ListSelectionListener
{            
   private JTextArea ta = new JTextArea();  //text area for movieInfo
   private JTextArea titleText = new JTextArea();  //text area for movieTitle and year
   private JTextArea projectDescr = new JTextArea();
   private JTextArea projectCredits = new JTextArea(10,10);
   private String[] logos2 = {"300.gif","aclockworkorange.gif", "americanpsycho.gif", "cloudatlas.gif", "deadpool.gif","deathnote.gif","doom.gif",
                               "dune.gif", "jurassicpark.gif", "tombraider.gif", "silenthill.gif","sincity.gif","spiderman.gif", "narnia.gif", "thedarkknight.gif"};
   private ImageIcon[] logos = new ImageIcon[15];
   private ImageIcon mainLogo = new ImageIcon("logo.gif");
   private ImageIcon tvLogo = new ImageIcon("tv.gif");
   private ImageIcon showCase = new ImageIcon("reel2.gif");

      
   private JLabel AlteredMovieLbl = new JLabel(""); //for north panel
   private JButton settingsBtn, discussBtn, loginBtn, createAccountBtn;
   private JLabel menuLbl = new JLabel("Menu"); //for west panel
   private JButton homeBtn, browseTitlesBtn, browseGenresBtn, browseRatingsBtn;
   private JTextField searchBar = new JTextField();  //text area for east panel search bar
   private String[] searchCriteria = {"Titles","Genres","Watched","Tags"};
   private JComboBox searchOptions;  
   private JLabel searchLbl = new JLabel("Search: "); 
   private JButton tvLogoBtn = new JButton();
   private JLabel movieImg = new JLabel(); //for center panel   
   private JLabel sourceLbl = new JLabel(); 
   private JLabel movieInfo = new JLabel();
   private JLabel movieTitle = new JLabel();
   private JLabel movieGenre = new JLabel();
   private JLabel movieYear = new JLabel();
   private JLabel movieRating = new JLabel();
   private JLabel movieWatched = new JLabel();
  // private JLabel projectDescr = new JLabel(); //for the description on homePanel

   private JList movieTitlesList = new JList();
   private JList searchResultsList = new JList();
   static String movieTitles[]; 
   private String searchResults[];
   static int selectedMovieIndex;
   private String selectedMovieInfo;
   private String selectedMovieTitle;
   private String selectedMovieGenre;
   private int selectedMovieYear;
   private double selectedMovieRating;
   private boolean selectedMovieWatched;
   int selectedTitleIndex;
   
   private String comingSoon = "This Feature is Coming Soon!";
   private String homeDescription =  "Altered Pictures is a compendium of movies that have been adapted from "
                                    + "their original source. Movies are often adapted from books, comic books, "
                                    + "video games, and even other films. Remakes, reboots, reinterpretations, "
                                    + "and other sorts of adaptations are becoming more and more common. "
                                    + "Altered Pictures is a growing project! In the future, there will be the "
                                    + "capability to track which movies have been watched. Until then, "
                                    + "please enjoy browsing and viewing information about adaptated movies here on Altered Pictures!";
   
   //create an arraylist of AdaptedMovie objects
   static ArrayList <AdaptedMovie> AdaptedMovieList = new ArrayList <AdaptedMovie> ();
   
   private Container c = getContentPane();
   private CardLayout cl;


   public static void main(String [] args) throws Exception
   {
      
     //    public AdaptedMovie MoviesArrayListClass()
   //{
   // We need to provide file path as the parameter:
   // double backquote is to avoid compiler interpret words
   // like \test as \t (ie. as a escape sequence)
   //File moviesFile = new File("C:\\Users\\gabby\\Desktop\\AdaptedMovie\\moviesTestFile.txt");
   File moviesFile = new File("..\\moviesTestFile.txt");
   //connect to file
   BufferedReader in = new BufferedReader(new FileReader(moviesFile));
   //create an arraylist of AdaptedMovie objects
   //ArrayList <AdaptedMovie> AdaptedMovieList = new ArrayList <AdaptedMovie> ();
   //read the next line of the file
   String line = in.readLine();
  
   while (line != null)
   {
      //parse the line into its fields
      String[] fields = line.split("\t");
      String title = fields [0];
      String genre = fields[1];
      String year = fields[2];
      String rating = fields[3];
      String watched = fields[4];
      String original = fields[5];
   
      //create a project object from the data in the fields
      AdaptedMovie theMovie = new AdaptedMovie(title, genre, Integer.parseInt(year), Float.parseFloat(rating), Boolean.parseBoolean(watched), original);
   
      //print the product object
      //theMovie.display(); comment out this line to not display the object an additional time
   
      //Add object to arraylist
      AdaptedMovieList.add(theMovie); 
      //read the next line
      line = in.readLine();
   }
   
   //indexthrough items
   //declare an identifier to receive the object from the get
   AdaptedMovie movieOptions;
   for(int index = 0; index<AdaptedMovieList.size();index++) 
   {   
      movieOptions = AdaptedMovieList.get(index);
    //  movieOptions.display();
   }   
   //close the input stream
   in.close();   
 // }//end create arraylist method
 
  //declare identifers to add titles and add JList to list movie titles
      String titleHolder;
      movieTitles = new String[15];   //this number needs to be changed if more movies are added to the list

      for(int index = 0; index<AdaptedMovieList.size();index++) 
      {   
         titleHolder = AdaptedMovieList.get(index).getTitle();
         movieTitles[index] = titleHolder; 
      }   
      
       
      AdaptedMoviesGUI app = new AdaptedMoviesGUI();    
      app.setSize(1200, 700);  
      app.pack();
      app.setVisible(true);
      app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      

        // System.out.println(AdaptedMovieList.get(selectedMovieIndex).getTitle() + " ");
          //        System.out.println(AdaptedMovieList.get(selectedMovieIndex) + " ");


   }//end main method
   
   //gui constructor 
   public AdaptedMoviesGUI()
   {
      //at beginning of conctructor set up array for movie images and text area for output
      for (int i = 0; i<logos.length;i++)  
      {
         logos[i]=new ImageIcon(logos2[i]);
      }
      
      
      
      setTitle("Altered Pictures");   //set title and layout
      c.setLayout(new BorderLayout());
      c.setBackground(new java.awt.Color(153, 0, 153));
      //create border
      Border compound, raisedetched, loweredetched;
      raisedetched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
      loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
      compound = BorderFactory.createCompoundBorder(raisedetched, loweredetched);
      
      //create 5 JPanel objects, one for each border area.
      //set up North JPanel
      JPanel panelNorth = new JPanel();
      panelNorth.setLayout(new GridLayout(1,5));
      panelNorth.setBackground(new java.awt.Color(95,55, 135));
      c.add(panelNorth, BorderLayout.NORTH); //add panelNorth to ContentPane
      
      settingsBtn = new JButton();
      settingsBtn.setToolTipText("Settings");
      settingsBtn.setForeground(new Color(255, 255, 255));//3 int for red/green/blue
      settingsBtn.setFont(new Font("Tahoma", 3, 16));//or your choice
      settingsBtn.setText("Settings");
      settingsBtn.setBackground(new Color(76, 0, 153));
      settingsBtn.setOpaque(false);//see background color 

      panelNorth.add(settingsBtn); //add settingsBtn to panelNorth
      //set rollover color for JButton
      settingsBtn.addMouseListener(new MouseAdapter() {
         Color color = settingsBtn.getForeground();
         public void mouseEntered(MouseEvent me) {
            color = settingsBtn.getForeground();
            settingsBtn.setForeground(new Color(160,  160, 160)); // change the color when mouse over a button
         }
         public void mouseExited(MouseEvent me) {
            settingsBtn.setForeground(color);
         }
      });

      discussBtn = new JButton();
      discussBtn.setToolTipText("Discuss");
      discussBtn.setForeground(new Color(255, 255, 255));//3 int for red/green/blue
      discussBtn.setFont(new Font("Tahoma", 3, 16));//or your choice
      discussBtn.setText("Discuss");
      discussBtn.setBackground(new Color(153, 0, 153));
      discussBtn.setOpaque(false);//see background color 
      panelNorth.add(discussBtn);      
      //set rollover color for JButton
      discussBtn.addMouseListener(new MouseAdapter() {
         Color color = discussBtn.getForeground();
         public void mouseEntered(MouseEvent me) {
            color = discussBtn.getForeground();
            discussBtn.setForeground(new Color(160,  160, 160)); // change the color when mouse over a button
         }
         public void mouseExited(MouseEvent me) {
            discussBtn.setForeground(color);
         }
      });
   
      AlteredMovieLbl.setIcon(mainLogo);
      //AlteredMovieLbl.setSize(205,480);
      panelNorth.add(AlteredMovieLbl);
        
      loginBtn = new JButton();
      loginBtn.setToolTipText("Log in");
      loginBtn.setForeground(new Color(255, 255, 255));//3 int for red/green/blue
      loginBtn.setFont(new Font("Tahoma", 3, 16));//or your choice
      loginBtn.setText("Log in");
      loginBtn.setBackground(new Color(153, 0, 153));
      loginBtn.setOpaque(false);//see background color       
      panelNorth.add(loginBtn);
      //set rollover color for JButton
      loginBtn.addMouseListener(new MouseAdapter() {
         Color color = loginBtn.getForeground();
         public void mouseEntered(MouseEvent me) {
            color = loginBtn.getForeground();
            loginBtn.setForeground(new Color(160,  160, 160)); // change the color when mouse over a button
         }
         public void mouseExited(MouseEvent me) {
            loginBtn.setForeground(color);
         }
      });
      
      createAccountBtn = new JButton();
      createAccountBtn.setToolTipText("Create Account");
      createAccountBtn.setForeground(new Color(255, 255, 255));//3 int for red/green/blue
      createAccountBtn.setFont(new Font("Tahoma", 3, 16));//or your choice
      createAccountBtn.setText("Create Account");
      createAccountBtn.setBackground(new Color(153, 0, 153));
      createAccountBtn.setOpaque(false);//see background color
      panelNorth.add(createAccountBtn);
      //set rollover color for JButton
      createAccountBtn.addMouseListener(new MouseAdapter() {
         Color color = createAccountBtn.getForeground();
         public void mouseEntered(MouseEvent me) {
            color = createAccountBtn.getForeground();
            createAccountBtn.setForeground(new Color(160,  160, 160)); // change the color when mouse over a button
         }
         public void mouseExited(MouseEvent me) {
            createAccountBtn.setForeground(color);
         }
      });
      
      //set up WEST panel:  a JLabel: menuLbl and JList: menuContents 
      JPanel panelWest = new JPanel();
      panelWest.setLayout(new GridLayout(6,1));
      panelWest.setBackground(new java.awt.Color(0, 0, 0));
      panelWest.setBorder(compound);
      c.add(panelWest, BorderLayout.WEST); 
      menuLbl.setFont(new Font("Tahoma", 3, 21));//or your choice
      menuLbl.setForeground(new Color(255, 255, 255));//3 int for red/green/blue
      menuLbl.setBackground(new java.awt.Color(153, 0, 0));
      menuLbl.setOpaque(true);
      menuLbl.setText("Menu "); 
      panelWest.add(menuLbl); 
      
      homeBtn = new JButton("Home");    
      homeBtn.setFont(new Font("serif", 1, 24));
      homeBtn.setForeground(new Color(255, 255, 255));
      homeBtn.setBackground(new Color(0, 0, 0));
      homeBtn.setOpaque(false);
      homeBtn.setToolTipText("Home");
      homeBtn.setText("Home");
      //createAccountBtn.setBackground(new Color(153, 0, 153));
      homeBtn.setOpaque(false);//see background color
      panelWest.add(homeBtn); 
      //set rollover color for JButton
      homeBtn.addMouseListener(new MouseAdapter() {
         Color color = homeBtn.getForeground();
         public void mouseEntered(MouseEvent me) {
            color = homeBtn.getForeground();
            homeBtn.setForeground(new Color(160,  160, 160));
         }
         public void mouseExited(MouseEvent me) {
            homeBtn.setForeground(color);
         }
      });
      
      browseTitlesBtn = new JButton("Browse Movie Titles");    
      browseTitlesBtn.setFont(new Font("serif", 1, 24));
      browseTitlesBtn.setForeground(new Color(255, 255, 255));
      browseTitlesBtn.setBackground(new Color(0, 0, 0));
      browseTitlesBtn.setToolTipText("Browse Titles");
      browseTitlesBtn.setText("Browse Movie Titles");
      panelWest.add(browseTitlesBtn); 
      //set rollover color for JButton
      browseTitlesBtn.addMouseListener(new MouseAdapter() {
         Color color = browseTitlesBtn.getForeground();
         public void mouseEntered(MouseEvent me) {
            color = browseTitlesBtn.getForeground();
            browseTitlesBtn.setForeground(new Color(160,  160, 160)); // change the color when mouse over a button
         }
         public void mouseExited(MouseEvent me) {
            browseTitlesBtn.setForeground(color);
         }
      });
      
      browseGenresBtn = new JButton("Browse Movie Genres");    
      browseGenresBtn.setFont(new Font("serif", 1, 24));
      browseGenresBtn.setForeground(new Color(255, 255, 255));
      browseGenresBtn.setBackground(new Color(0, 0, 0));
      browseGenresBtn.setToolTipText("Browse Genres");
      browseGenresBtn.setText("Browse Movie Genres");
      panelWest.add(browseGenresBtn); 
      //set rollover color for JButton
      browseGenresBtn.addMouseListener(new MouseAdapter() {
         Color color = browseGenresBtn.getForeground();
         public void mouseEntered(MouseEvent me) {
            color = browseGenresBtn.getForeground();
            browseGenresBtn.setForeground(new Color(160,  160, 160)); // change the color when mouse over a button
         }
         public void mouseExited(MouseEvent me) {
            browseGenresBtn.setForeground(color);
         }
      });
            
      browseRatingsBtn = new JButton("Browse Movies by Ratings");    
      browseRatingsBtn.setFont(new Font("serif", 1, 24));
      browseRatingsBtn.setForeground(new Color(255, 255, 255));
      browseRatingsBtn.setBackground(new Color(0, 0, 0));
      browseRatingsBtn.setToolTipText("Browse by Ratings");
      browseRatingsBtn.setText("Browse Movies by Ratings");
      panelWest.add(browseRatingsBtn); 
      //set rollover color for JButton
      browseRatingsBtn.addMouseListener(new MouseAdapter() {
         Color color = browseRatingsBtn.getForeground();
         public void mouseEntered(MouseEvent me) {
            color = browseRatingsBtn.getForeground();
            browseRatingsBtn.setForeground(new Color(160,  160, 160)); // change the color when mouse over a button
         }
         public void mouseExited(MouseEvent me) {
            browseRatingsBtn.setForeground(color);
         }
      });

     // JButton tvLogoLeft = new JButton();
     // tvLogoLeft.setIcon(tvLogo);
     // tvLogoLeft.setOpaque(true);
     // tvLogoLeft.setBackground(new java.awt.Color(0, 0, 0));
     // panelWest.add(tvLogoLeft);
      
      //set up EAST panel:  JTextArea searchBar and a JComboBox for searchOptions   
      JPanel panelEast = new JPanel();
      panelEast.setBackground(new java.awt.Color(0, 0, 0));
      panelEast.setLayout(new GridLayout(7,1));
      c.add(panelEast, BorderLayout.EAST);
      panelEast.setBorder(compound);
      searchLbl.setFont(new Font("serif", 3, 20));
      searchLbl.setForeground(new Color(255, 255, 255));//3 int for red/green/blue
      searchLbl.setText("Search Movies: ");
      panelEast.add(searchLbl);
      searchBar.setFont(new Font("Arial", 1, 20));
      searchBar.setForeground(new Color(0, 0, 0));//3 int for red/green/blue
      //searchBar.setText("");
      //searchBar.setLineWrap(true);
      //searchBar.setWrapStyleWord(true);
      JScrollPane searchScrollPane = new JScrollPane(searchBar);
      panelEast.add(searchScrollPane); 
 


      
      searchOptions = new JComboBox( searchCriteria );     
      searchOptions.setFont(new Font("serif", 1, 20));
      searchOptions.setForeground(new Color(0, 0, 0));
      searchOptions.setToolTipText("Search Options");         
      searchOptions.setMaximumRowCount( 4 );//set max rows to dixplay
      panelEast.add(searchOptions); 

      
      //searchOptions.addItemListener(new java.awt.event.ItemListener() {
      //      public void itemStateChanged(java.awt.event.ItemEvent evt){
      //          searchOptionsItemStateChanged(evt);
      //      }
      //  });
      JLabel filler1 = new JLabel(" ");
      JLabel filler2 = new JLabel(" ");
      JLabel filler3 = new JLabel(" ");
      panelEast.add(filler1);
      panelEast.add(filler2);
      panelEast.add(filler3);

      tvLogoBtn.setIcon(tvLogo);
      tvLogoBtn.setOpaque(true);
      tvLogoBtn.setBackground(new java.awt.Color(0, 0, 0));
      panelEast.add(tvLogoBtn);
      
      
       tvLogoBtn.addActionListener(new ActionListener()
         {
            public void actionPerformed(ActionEvent arg0)
            {
               projectCredits.append("\nCIS 1512");
               projectCredits.append("\nProfessor Hadi Nasser");
               projectCredits.append("\nSDD");
               projectCredits.append("\nFall 2021");
               projectCredits.append("\n");
               projectCredits.append("\nGabrielle Saab");
               projectCredits.append("\nMaria Bradley");
               projectCredits.append("\nMuhammad Atteeb");
               projectCredits.append("\nRobert Dent");
               projectCredits.append("\nRobert Phillips");
               projectCredits.append("\n\n");   
               projectCredits.setEditable(false);

               JOptionPane.showMessageDialog(null,projectCredits,"Altered Pictures",JOptionPane.INFORMATION_MESSAGE,tvLogo);

            }
         });

      
      //set up CENTER panel:  
      JPanel panelCenter = new JPanel();
      //panelCenter.setLayout(new GridLayout(2,2));
      panelCenter.setBackground(new java.awt.Color(96, 96, 96));
      //panelCenter.setSize(300, 100 );
      //panelCenter.add( new JLabel("CENTER") );
      c.add(panelCenter, BorderLayout.CENTER); //add panelCenter to ContentPane   
      
      // Creating Object of "Jpanel" class
      JPanel cardPanel = new JPanel();
      // Initialization of object "c1"
      // of CardLayout class.
      cl = new CardLayout(); 
      // set the layout
      cardPanel.setLayout(cl);
      cardPanel.setBackground(new java.awt.Color(96, 96, 96));
      // Initialization of object
      // 1st-6th JPanel objects of JPanel class.
      JPanel homePanel = new JPanel();
      JPanel titlesPanel = new JPanel();
      JPanel genresPanel = new JPanel();
      JPanel ratingsPanel = new JPanel();
      JPanel infoPanel = new JPanel();
      JPanel resultsPanel = new JPanel(); //for displaying search results

      // Initialization of object
      // 1st-6th JLabel objects of JLabel class.
      JLabel jl1 = new JLabel("Home");
      jl1.setBackground(new java.awt.Color(96, 96, 96));
      jl1.setForeground(new java.awt.Color(255, 255, 255));
      JLabel jl2 = new JLabel("Browse by Titles");
      jl2.setBackground(new java.awt.Color(96, 96, 96));
      jl2.setForeground(new java.awt.Color(255, 255, 255));
      JLabel jl3 = new JLabel("Browse by Genres");
      jl3.setBackground(new java.awt.Color(96, 96, 96));
      jl3.setForeground(new java.awt.Color(255, 255, 255));
      JLabel jl4 = new JLabel("Browse by Ratings");
      jl4.setBackground(new java.awt.Color(96, 96, 96));
      jl4.setForeground(new java.awt.Color(255, 255, 255));
      JLabel jl5 = new JLabel("View Movie Info");
      jl5.setBackground(new java.awt.Color(96, 96, 96));
      jl5.setForeground(new java.awt.Color(255, 255, 255));
      JLabel jl6 = new JLabel("View Search Results");
      jl6.setBackground(new java.awt.Color(96, 96, 96));
      jl6.setForeground(new java.awt.Color(255, 255, 255));


      // Adding JLabel to JPanels.
      //homePanel.add(jl1);
      titlesPanel.add(jl2);
      genresPanel.add(jl3);
      ratingsPanel.add(jl4);
      //infoPanel.add(jl5); 
      resultsPanel.add(jl6);     
      //adding JLabels to main cardPanel 
      cardPanel.add(homePanel, "1"); 
      cardPanel.add(titlesPanel, "2"); 
      cardPanel.add(genresPanel, "3");
      cardPanel.add(ratingsPanel, "4");
      cardPanel.add(infoPanel, "5");
      cardPanel.add(resultsPanel, "6");

     // JPanel westInfoPanel = new JPanel(); //west panel and right panel are for the movie infoPanel
     // westInfoPanel.setLayout(new GridLayout(3, 1));
     // JPanel rightInfoPanel = new JPanel();
     // rightInfoPanel.setLayout(new GridLayout(3, 1));    
        
      //setting up homePanel
      homePanel.setBackground(new java.awt.Color(96, 96, 96));
      homePanel.setLayout(new GridLayout(2,1));
      JLabel showReel = new JLabel();
      
      //create an object of the box class
      Box hbox = Box.createHorizontalBox();
      //create an object of the box class
      Box vbox = Box.createVerticalBox();
      showReel.setIcon(showCase);
      showReel.setOpaque(true);
      showReel.setBackground(new java.awt.Color(0, 0, 0));
      // homePanel.add(showReel);    
      //JLabel projectDescr = new JLabel();
      //projectDescr.setText("" + homeDescription);
      //projectDescr.setForeground(new java.awt.Color(255, 255, 255));
      // homePanel.add(projectDescr);
      projectDescr.append("\n" + homeDescription);
      projectDescr.setLineWrap(true);
      projectDescr.setWrapStyleWord(true);
      projectDescr.setOpaque(false);
      projectDescr.setForeground(new java.awt.Color(255, 255, 255));
      projectDescr.setFont(new Font("Courier New", Font.BOLD, 16));      

      //add horizontal and vertical components to the container
      hbox.add(showReel);
      hbox.add(projectDescr);
      homePanel.add(hbox);          
                    
      //setting up titlesPanel
      movieTitlesList = new JList( movieTitles );//array of strings to display
	   //set colors, Font size , etc here
	   movieTitlesList.setVisibleRowCount( 15 );     //rows to display, this number needs to be changed if more movies are added to the list
      movieTitlesList.setOpaque(false);
      movieTitlesList.setBackground(new java.awt.Color(96, 96, 96));
      movieTitlesList.setForeground(new Color(0, 178, 255));
      movieTitlesList.setFont(new Font("serif", 1, 16));
      //titlesPanel.add( new JScrollPane(movieTitlesList) );   //add a ScrollPane   
      titlesPanel.add(movieTitlesList);
      titlesPanel.setBackground(new java.awt.Color(96, 96, 96));
      movieImg = new JLabel();
      
      //setting up infoPanel
      infoPanel.setBackground(new java.awt.Color(96, 96, 96));
      //add horizontal and vertical components to the container
      infoPanel.setLayout(new FlowLayout());
      Box leftInfo = Box.createVerticalBox();
      Box rightInfo = Box.createVerticalBox();
      leftInfo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
      rightInfo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
      infoPanel.add(leftInfo);
      infoPanel.add(rightInfo);
      sourceLbl = new JLabel();
      sourceLbl.setText("Original Source of adaption: ");
      sourceLbl.setFont(new Font("tahoma", 3, 16));
      sourceLbl.setForeground(new java.awt.Color(255, 255, 255));
      sourceLbl.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


      
      movieTitlesList.addListSelectionListener(new javax.swing.event.ListSelectionListener()
      {
         public void valueChanged(javax.swing.event.ListSelectionEvent evt) 
         {
            String chosenMovieTitle = null;
            movieTitle.setText(null);
            //movieInfo.setText(null);
            ta.selectAll(); 
		      ta.replaceSelection("");
            titleText.selectAll(); 
		      titleText.replaceSelection("");
            
            selectedTitleIndex = movieTitlesList.getSelectedIndex();
            selectedMovieIndex = selectedTitleIndex;
            AdaptedMovie.setIndex(selectedMovieIndex);
            
            //System.out.print("The selected movie is: ");
            //System.out.println(movieTitles[selectedTitleIndex]);      
            //System.out.println("The index is: " + AdaptedMovie.getIndex());
             
            int chosenMovieIndex = AdaptedMovie.getIndex();
            AdaptedMovie selectedMovie = AdaptedMovieList.get(chosenMovieIndex);
            chosenMovieTitle = selectedMovie.getTitle();
            selectedMovieYear = selectedMovie.getYear();
            
            //infoPanel.setLayout(new BorderLayout());
            //infoPanel.setLayout(new GridLayout(2,1));
                                  
            
            movieTitle = new JLabel();
            movieTitle.setText("" + chosenMovieTitle + "  - " + selectedMovieYear);
            movieTitle.setFont(new Font("tahoma", 1, 20));
            movieTitle.setForeground(new java.awt.Color(255, 255, 255));
         
            selectedMovieInfo = selectedMovie.getOriginal();
            movieInfo = new JLabel();
            movieInfo.setText(selectedMovieInfo);
            movieInfo.setFont(new Font("tahoma", 1, 18));
            movieInfo.setForeground(new java.awt.Color(255, 255, 255));
            movieInfo.setBackground(new java.awt.Color(0, 0, 0));
            
            movieImg.setIcon(logos[chosenMovieIndex]);
            
            ta.setLineWrap(true);
            ta.setWrapStyleWord(true);
            ta.setOpaque(false);
            ta.setForeground(new java.awt.Color(255, 255, 255));
            ta.append(selectedMovieInfo);
            ta.setFont(new Font("Courier New", Font.BOLD, 16));
            
            titleText.setLineWrap(true);
            titleText.setWrapStyleWord(true);
            titleText.setOpaque(false);
            titleText.setForeground(new java.awt.Color(255, 255, 255));
            titleText.append("\n" + "" + chosenMovieTitle + "  - " + selectedMovieYear);
            titleText.setFont(new Font("Courier New", Font.BOLD, 16));
                                               
            leftInfo.add(movieImg);
            //rightInfo.add(movieTitle);
            rightInfo.add(titleText);
            rightInfo.add(sourceLbl);
            rightInfo.add(ta);
            rightInfo.setAlignmentX(Component.LEFT_ALIGNMENT);


            //infoPanel.add(movieImg, BorderLayout.WEST);
            //infoPanel.add(movieTitle, BorderLayout.NORTH);
            //infoPanel.add(sourceLbl, BorderLayout.CENTER);
            //infoPanel.add(ta, BorderLayout.CENTER);
            revalidate();
            pack();
            
            cl.show(cardPanel, "5");   
         }
      });
          
      //setting up genresPanel
      genresPanel.setBackground(new java.awt.Color(96, 96, 96));  
      BoxLayout genresboxlayout = new BoxLayout(genresPanel, BoxLayout.Y_AXIS);       
      genresPanel.setLayout(genresboxlayout);    
      //text area notLaunched to display string comingSoon
      JTextArea notLaunched = new JTextArea(); 
      notLaunched.setLineWrap(true);
      notLaunched.setWrapStyleWord(true);
      notLaunched.setOpaque(false);
      notLaunched.setForeground(new java.awt.Color(255, 255, 255));
      notLaunched.append(comingSoon);
      notLaunched.setFont(new Font("Courier New", Font.BOLD, 22)); 
      genresPanel.add(notLaunched);

      //setting up ratingsPanel
      ratingsPanel.setBackground(new java.awt.Color(96, 96, 96)); 
      BoxLayout boxlayout = new BoxLayout(ratingsPanel, BoxLayout.Y_AXIS);       
      ratingsPanel.setLayout(boxlayout);
      JTextArea ratingsNotLaunched = new JTextArea(); 
      ratingsNotLaunched.setLineWrap(true);
      ratingsNotLaunched.setWrapStyleWord(true);
      ratingsNotLaunched.setOpaque(false);
      ratingsNotLaunched.setForeground(new java.awt.Color(255, 255, 255));
      ratingsNotLaunched.append(comingSoon);
      ratingsNotLaunched.setFont(new Font("Courier New", Font.BOLD, 22));
      ratingsPanel.add(ratingsNotLaunched);


     
      

      //westInfoPanel.setBackground(new java.awt.Color(96, 96, 96));
      //rightInfoPanel.setBackground(new java.awt.Color(96, 96, 96));
      //infoPanel.add(westInfoPanel, BorderLayout.WEST);
      //infoPanel.add(rightInfoPanel, BorderLayout.CENTER);
      
     
      
      //System.out.print("The selected movie is: ");
      //System.out.println(movieTitles[selectedTitleIndex]);      
      //System.out.println("The index is: " + AdaptedMovie.getIndex());
      


      //events ActionListener for the menu buttons
      homeBtn.addActionListener(new ActionListener()
         {
            public void actionPerformed(ActionEvent arg0)
            {
                // used first c1 CardLayout
                cl.first(cardPanel);
            }
         });
         
         browseTitlesBtn.addActionListener(new ActionListener()
         {
             public void actionPerformed(ActionEvent arg0)
             {
                cl.show(cardPanel, "2");
                movieTitlesList.clearSelection(); 
                ta.selectAll(); 
		          ta.replaceSelection("");  
                movieImg.setIcon(null);
             }
         });
         
         browseGenresBtn.addActionListener(new ActionListener()
         {
            public void actionPerformed(ActionEvent arg0)
            {
                cl.show(cardPanel, "3");
                movieTitlesList.clearSelection(); 
                ta.selectAll(); 
		          ta.replaceSelection("");  
                movieImg.setIcon(null);
            }
         });
         
         browseRatingsBtn.addActionListener(new ActionListener()
         {
             public void actionPerformed(ActionEvent arg0)
             {
                cl.show(cardPanel, "4");
                ta.selectAll(); 
		          ta.replaceSelection("");  
                movieImg.setIcon(null);
             }
         });         
                 
         //action listener for performing searches, adding to searchBar, can also add to search "go" button
         //searchBar.addActionListener(new ActionListener()
         //{
         // public void actionPerformed(ActionEvent e)
         // {
         //    cl.show(cardPanel, "6");
         //  }
         // });           
         ActionListener SearchAction = ae -> {
         searchResults = new String[15];  
         String searchText = null;
         //searchResults = null;
         
         searchText = searchBar.getText();                  
         ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(searchResults));                             
                
         System.out.println("Contents of the array list: ");
         for (String element : movieTitles)
         {
            if (element.toUpperCase().contains(searchText.toUpperCase()))
            {
               //System.out.println(element);
               arrayList.add(element);
               // System.out.println(arrayList);         
            }
         }   
         // Convert the Arraylist to array
         
         searchResults = arrayList.toArray(searchResults);
            
         //setting up resultsPanel "6"
         resultsPanel.setBackground(new java.awt.Color(96, 96, 96));         
         BoxLayout resultsboxlayout = new BoxLayout(resultsPanel, BoxLayout.Y_AXIS);       
         resultsPanel.setLayout(resultsboxlayout);
         
         searchResultsList = new JList( searchResults );//array of strings to display
	      //set colors, Font size , etc here
	      searchResultsList.setVisibleRowCount( 15 );     //rows to display, this number needs to be changed if more movies are added to the list
         searchResultsList.setOpaque(false);
         searchResultsList.setBackground(new java.awt.Color(96, 96, 96));
         searchResultsList.setForeground(new Color(0, 178, 255));
         searchResultsList.setFont(new Font("serif", 1, 16));
         resultsPanel.add(searchResultsList); 

         JTextArea searchNotLaunched = new JTextArea(); 
         searchNotLaunched.setLineWrap(true);
         searchNotLaunched.setWrapStyleWord(true);
         searchNotLaunched.setOpaque(false);
         searchNotLaunched.setForeground(new java.awt.Color(255, 255, 255));
         searchNotLaunched.append(comingSoon);
         searchNotLaunched.setFont(new Font("Courier New", Font.BOLD, 22));
         resultsPanel.add(searchNotLaunched);
         
         
         cl.show(cardPanel, "6");};
         
         searchBar.addActionListener(SearchAction); 
                  
         // used to get content pane
         panelCenter.add(cardPanel);                    
   } // end constructor
}

