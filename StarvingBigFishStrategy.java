class StarvingBigFishStrategy extends MoveStrategy{

	public StarvingBigFishStrategy(Fish fish, double x, double y, Pond pond){
		this.fish = fish;
		this.x = x;
		this.y = y;
		this.pond = pond;
	}
	
	public void move(){
        double[] location = pond.findNearestSmallFish(x, y);
        this.fish.swimTowards(location[0], location[1]);
	}
}