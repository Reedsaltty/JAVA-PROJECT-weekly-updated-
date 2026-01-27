

public class Movie {
    public String movieId ;
    public String title ;
    public int duration ;
    
    public Movie(String movieId, String title , int duration){
        this.movieId = movieId;
        this.title = title;
        this.duration = duration;
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        
        return ("[ Movie Id : " + movieId + ", Movie title : "+ title +"Movie dur: "+ duration +"]");
    }

}
