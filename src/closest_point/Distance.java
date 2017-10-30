package closest_point;

public class Distance {

	private float distance;
	private Point a;
	private Point b;

	Distance(){
	}
	Distance(Point a, Point b){
		this.a = a;
		this.b = b;
		this.distance = cal_distance(a,b);
	}
	Distance(float distance, Point a, Point b){
		this.distance = distance;
		this.a = a;
		this.b = b;
	}
	public float getDistance() {
		return distance;
	}
	public void setDistance(float distance) {
		this.distance = distance;
	}
	public Point getA() {
		return a;
	}
	public void setA(Point a) {
		this.a = a;
	}
	public Point getB() {
		return b;
	}
	public void setB(Point b) {
		this.b = b;
	}
	public float cal_distance(){
		return 0;
	}
	public float cal_distance(Point a, Point b){
		int dim = a.getDimension();
		float count = 0;
		for(int i = 0; i<dim; i++){
			float result = Math.abs(a.getDimensions()[i] - b.getDimensions()[i]);
			result = result * result;
			count = count + result;
		}
		return count;
	}

}
