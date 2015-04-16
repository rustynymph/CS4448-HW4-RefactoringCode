class StarvingSmallFishStrategy extends MoveStrategy{

	public StarvingSmallFishStrategy(Fish fish, double x, double y){
		this.fish = fish;
		this.x = x;
		this.y = y;
	}
	
	public void move(Pond pond){
        double[] location = pond.findNearestPlant(x, y);
        this.fish.swimTowards(location[0], location[1]);
	}
}