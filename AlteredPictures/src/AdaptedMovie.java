public class AdaptedMovie
{ 
   //declare variables
   private String movieTitle, movieGenre, movieOriginal;	
   private int movieYear;
   private float movieRating;
   private boolean watchedStatus;
   public static int neededMovieIndex;
   
   //default constructor
   public AdaptedMovie()
  	{	
      movieTitle = "";
     	movieGenre = "";
     	movieYear = 0;
      movieRating = 0;
      watchedStatus = false;
      movieOriginal = "";
   }
   
	//non-default constructor
   public AdaptedMovie(String title, String genre, int year, float rating, boolean watched, String original)
   {
      movieTitle = title;	
      movieGenre = genre;	
      movieYear = year;
      movieRating = rating;
      watchedStatus = watched;
      movieOriginal = original;
   } 
   
      //get and set methods
   public void setTitle(String title)		
   {
      movieTitle = title;			
   }

   public void setGenre(String genre)
   {
      movieGenre = genre;
   }
   public void setYear(int year)
   {
      movieYear=year;
   }
   public void setRating(float rating)
   {
      movieRating = rating;
   }
   public void setWatched(boolean watched)
   {
      watchedStatus = watched;
   }
   public void setOriginal(String original)
   {
      movieOriginal = original;
   }
   public String getTitle()
   {
      return movieTitle;
   }
   public String getGenre()	
   {
      return movieGenre;		  
   }
   public int getYear()
   {
      return movieYear;
   }
   public float getRating()
   {
      return movieRating;
   }
   public boolean getWatched()
   {
      return watchedStatus;
   }
   public String getOriginal()
   {
      return movieOriginal;
   }
   public static void setIndex(int index)		
   {
      neededMovieIndex = index;			
   }
   public static int getIndex()
   {
      return neededMovieIndex;
   }
   //display method
   public void display()
   {
      System.out.println("*******************");
      System.out.println("Title: " + movieTitle + " (" + movieYear + ") ");
      System.out.println("Genre: " + movieGenre);
      System.out.println("Rating: " + movieRating);
      System.out.println("I have seen this movie: " + watchedStatus);
      System.out.println("Original Source of the Adaption: " + movieOriginal);
      System.out.println("*******************");
   }
}   