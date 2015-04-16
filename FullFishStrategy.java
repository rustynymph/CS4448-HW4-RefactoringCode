class FullFishStrategy extends MoveStrategy{

	public FullFishStrategy(Fish fish, double x, double y){
		this.fish = fish;
		this.x = x;
		this.y = y;;
	}
	
	public void move(Pond pond){
        this.fish.hide();
	}
}