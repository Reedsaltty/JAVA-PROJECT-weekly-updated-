

public class Movie {
    private String movieId ;
    private String title ;
    private int duration ;
    
    public Movie(String movieId, String title , int duration){
        this.movieId = movieId;
        this.title = title;
        this.duration = duration;
    }

    String getMovieId(){
        return movieId;
    }
    String title(){
        return 
    }
    
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        
        return ("[ Movie Id : " + movieId + ", Movie title : "+ title +"Movie dur: "+ duration +"]");
    }

}
