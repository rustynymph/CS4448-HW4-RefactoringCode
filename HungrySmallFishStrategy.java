class HungrySmallFishStrategy extends MoveStrategy{

	public HungrySmallFishStrategy(Fish fish, double x, double y){
		this.fish = fish;
		this.x = x;
		this.y = y;
	}
	
	public void move(Pond pond){
		double[] location = pond.findNearestBigFish(x, y);
        this.fish.swimAway(location[0], location[1]);
	}
}