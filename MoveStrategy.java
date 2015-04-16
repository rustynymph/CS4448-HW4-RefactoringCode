public abstract class MoveStrategy{

	protected Fish fish;
	protected double x;
	protected double y;
	protected Pond pond;

	public MoveStrategy(){
	}

	public abstract void move(Pond pond);
}