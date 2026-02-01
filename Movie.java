

public class Movie {
    private String movieId ;
    private String title ;
    private int duration ;
    
    public Movie(String movieId, String title , int duration){
        setMovieId(movieId);
        setTitle(title);
        getDuration(duration);
    }

    public String getMovieId(){
        return movieId;
    }
    
    public String getTitle(){
        return title;
    }
    
    public int getDuration(){
        return duration;
    }
    
    public void setMovieId(String movieId){
        this.movieId = movieId;
    }
    
    public void setTitle(String title){
        this.title = title;
    }
    
    public void setDuration(int duration){
        this.duration = duration;
    }
    
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        
        return ("[ Movie Id : " + movieId + ", Movie title : "+ title +"Movie dur: "+ duration +"]");
    }

}
