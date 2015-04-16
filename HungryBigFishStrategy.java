class HungryBigFishStrategy extends MoveStrategy{

	public HungryBigFishStrategy(Fish fish, double x, double y, Pond pond){
		this.fish = fish;
		this.x = x;
		this.y = y;
		this.pond = pond;
	}
	
	public void move(){
		this.fish.swimRandomly();
	}
}