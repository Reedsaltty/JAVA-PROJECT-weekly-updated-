

public class Movie {
    private String movieId ;
    private String title ;
    private int duration ;
    
    public Movie(String movieId, String title , int duration){
        setMovieId(movieId);
        setTitle(title);
        getDuration(duration);
    }

    String getMovieId(){
        return movieId;
    }
    String title(){
        return title;
    }
    int getDuration(){
        return duration;
    }
    String setMovieId(String movieId){
        return this.movieId = movieId;
    }
    String setTitle( String title){
        return this.title = title;
    }
    int getDuration(int dur){
        return this.duration = dur;
    }
    
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        
        return ("[ Movie Id : " + movieId + ", Movie title : "+ title +"Movie dur: "+ duration +"]");
    }

}
