
import interface_abstract.Displayable;
import interface_abstract.Entity;

public class Movie extends Entity implements Displayable   {
    private String title ;
    private int duration ;
    
    public Movie(String movieId, String title , int duration){
        super(movieId);
        setTitle(title);
        setDuration(duration);

    }


    public String getTitle(){
        return title;
    }
    
    public int getDuration(){
        return duration;
    }
        
    public void setTitle(String title){
        this.title = title;
    }
    
    public void setDuration(int duration){
        this.duration = duration;
    }
    
    @Override
    public String displayInfo() {
        // TODO Auto-generated method stub
        return ("[ Movie Id : " + getId() + ", Movie title : "+ title +"Movie dur: "+ duration +"]");
    }


}
