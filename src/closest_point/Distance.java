package closest_point;

/**
 * Distance.java
 */
public class Distance {


	/**
	 * Distance from two point. (square of distance is not necessary)
	 * @HasGetter
	 * @HasSetter
	 */
	private float distance;	//distance between two points.


	/**
	 * First point object is regarded
	 * @HasGetter
	 * @HasSetter
	 */
	private Point a;	//First point

	/**
	 * Second point object is regarded
	 * @HasGetter
	 * @HasSetter
	 */
	private Point b;	//Second point


	/**
	 * Class constructor.
	 */
	Distance(){

	}

	/**
	 * Class constructor with two points as parameters.
	 * @param a - First Point
	 * @param b - Second Point
	 */
	Distance(Point a, Point b){
		this.a = a;
		this.b = b;
		this.distance = cal_distance(a,b);
	}


	/**
	 * Class constructor with two points and one distance as parameters.
	 * @param distance
	 * @param a
	 * @param b
	 */
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

	/**
	 * calculate distance between two Points.
	 * @param a - first point
	 * @param b - second point
	 * @return distance
	 */
	public float cal_distance(Point a, Point b){
		//Distance m = new Distance();
		//m.setA(a);
		//m.setB(b);
		int dim = a.getDimension();	// dimension of point
		float distance = 0;	//keep square of distance between points
		for(int i = 0; i < dim; i++){
			float result = Math.abs(a.getCoordinates()[i] - b.getCoordinates()[i]);
			result = result * result;
			distance = distance + result;
		}
		//m.setDistance(count);
		return distance;
	}

	/**
	 * Find Distance objecct has smaller distance variable than other.
	 * @param a - Distance object
	 * @return Distance object that has smaller distance
	 */
	public Distance distance_min_distance(Distance a){
		float min = Math.min(a.getDistance(), this.getDistance());	//min distance
		//min = Math.min(distance_object.getDistance(), dis3.getDistance());
		if(min == a.getDistance()){
			return a;
		}else{
			return this;
		}
	}

}
