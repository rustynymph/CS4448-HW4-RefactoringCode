class HungrySmallFishStrategy extends MoveStrategy{

	public HungrySmallFishStrategy(Fish fish, double x, double y, Pond pond){
		this.fish = fish;
		this.x = x;
		this.y = y;
		this.pond = pond;
	}
	
	public void move(){
		double[] location = pond.findNearestBigFish(x, y);
        this.fish.swimAway(location[0], location[1]);
	}
}