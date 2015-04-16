/**
* FishReport
*
* An object individual fish update whenever their status changes
*/
import java.util.Observer;

public class FishReport{
  //implements Observer
//{
  private Fish fish; 
  private double hunger;
  private double size;
  private double x;
  private double y;

  public FishReport()
  {
    hunger = 0;
    size = 0;
    x = 0;
    y = 0;
  }

  //@Override
  //public void update(Observable o, Object arg){
   // fish = (Fish)o;
    //fish.updateHunger(o.)

 
  public void updateHunger(double hunger)
  {
    this.hunger = hunger;
  }

  public void updateSize(double size)
  {
    this.size = size;
  }

  public void updateLocation(double x, double y)
  {
    this.x = x;
    this.y = y;
  }

  public double getHunger()
  {
    return hunger;
  } 

  public double getSize()
  {
    return size;
  }

  public double[] getLocation()
  {
    double location[] = {x, y};
    return location;
  }
}
