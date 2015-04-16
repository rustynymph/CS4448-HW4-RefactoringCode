/**
* Fish
*
* A single fish in the simulation
*/

import java.lang.Math;
import java.util.Random;
import java.util.Observable;

public class Fish extends Observable{


  public static final double STARVING_FISH = 0.2;
  public static final double HUNGRY_FISH = 0.7;
  public static final double SMALL_FISH = 0.7;
  public static final double BIG_FISH = 5.0;

  public static final double INIT_HUNGER = 0.9;
  public static final double INIT_SIZE = 1.0;    

  private static Random random = new Random();
  private static int numberOfFish = 0;

  private double hunger;            
  // A value between 0 (hungry) and 1 (full)
  private double size;
  private double x;                 // Where the fish is
  private double y;
  private int id; 
  private MoveStrategy moveStrategy;

  // Don't want to be able to directly query the fish for information, but do 
  // need to get information for logging or displaying on a GUI, etc.
  
  public Fish(double x, double y, FishReport report)
  {

    // A fish is born!
    hunger = INIT_HUNGER;
    size = INIT_SIZE;

    //a fish is full when initalized
    updateMoveStrategy();

    // Put it in the pond
    this.x = x;
    this.y = y;

    // And give it an id
    id = numberOfFish;
    numberOfFish++;

    // Who to report to?
    addObserver(report);
    setChanged();
    notifyObservers(createFishAttributesArray());
  }

  /* allows us to pass in an array of the fish's attributes for updating */
  private double[] createFishAttributesArray()
  {
    double[] fishAttributesArray = {hunger,size,x,y};
    return fishAttributesArray;
  }

  public double getSize()           
  {
    return size;
  } 

  public void age(double timePassed)
  {
    // Growth is based on how much time has passed and how much food
    // has been eaten
    double deltaSize = size * (1 + hunger * Math.exp(-size * timePassed));
    size = size + deltaSize;

    // If the fish grows a lot (relative to the size change), lots of food
    // has been consumed
    hunger = hunger * Math.exp(-deltaSize/size);

    updateMoveStrategy();
    setChanged();
    notifyObservers(createFishAttributesArray());  
  }

  public void move(Pond pond)
  {
    moveStrategy.move(pond);
  }

  // Swim towards a location
  public void swimTowards(double tx, double ty)
  {
    double distance = Math.sqrt((tx - x)*(tx - x) + (ty - y)*(ty - y));
    x = x + (tx/distance);
    y = y + (ty/distance);

    setChanged();
    notifyObservers(createFishAttributesArray());
  }


  // Swim away from a location
  public void swimAway(double tx, double ty)
  {
    double distance = Math.sqrt((tx - x)*(tx - x) + (ty - y)*(ty - y));
    x = x - (tx/distance);
    y = y - (ty/distance);

    setChanged();
    notifyObservers(createFishAttributesArray());
  }


  // Just swim around
  public void swimRandomly()
  {
    System.out.println("FISH #" + id + ": I'm swimming around!");

    x = x + random.nextDouble();
    y = y + random.nextDouble();

    setChanged();
    notifyObservers(createFishAttributesArray());
  }

  // Just let the world know I hid!
  public void hide()
  {
    System.out.println("FISH #" + id + ": I'm hiding!");
  }

  public void updateMoveStrategy(){
    if(hunger < STARVING_FISH)              
    {
      if(size < SMALL_FISH)
      {
        // Smaller fish only eat plants
        moveStrategy = new StarvingSmallFishStrategy(this,x,y);
      }
      else
      {
        // Really big fish like to eat other fish
        moveStrategy = new StarvingBigFishStrategy(this,x,y);
      }
    }
    else 
    { 
      // Fish isn't starving, but is hungry
      if (hunger < HUNGRY_FISH)
      {
        // A big enough fish won't worry about being eaten
        if (size > BIG_FISH)
        {
          moveStrategy = new HungryBigFishStrategy(this,x,y);
        }
        else
        {
          // Want to avoid the nearest big fish
          moveStrategy = new HungrySmallFishStrategy(this,x,y);
        }
      }
      else
      {
        // A really full fish will just hide and sleep
        moveStrategy = new FullFishStrategy(this,x,y);

      }
    }

  }

}
