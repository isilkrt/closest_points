package closest_point;

/**
 * Distance.java
 */
public class Distance {

	/**Distance from two point.
	 * @HasGetter
	 * @HasSetter
	 */
	private double distance;	//distance between two points.

	/** First point object is regarded
	 * @HasGetter
	 * @HasSetter
	 */
	private Point a;	//First point

	/**Second point object is regarded
	 * @HasGetter
	 * @HasSetter
	 */
	private Point b;	//Second point

	/**Class constructor.
	 */
	Distance(){
	}

	/**Class constructor with two points as parameters.
	 * @param a - First Point
	 * @param b - Second Point
	 */
	Distance(Point a, Point b){
		this.a = a;
		this.b = b;
		this.distance = cal_distance(a,b);
	}

	/**Class constructor with two points and one distance as parameters.
	 * @param distance
	 * @param a
	 * @param b
	 */
	Distance(double distance, Point a, Point b){
		this.distance = distance;
		this.a = a;
		this.b = b;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
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

	/**calculate distance between two Points.
	 * @param a - first point
	 * @param b - second point
	 * @return distance
	 */
	public double cal_distance(Point a, Point b){

		int dim = a.getDimension();	      // dimension of point
		double distance = 0;	              //keep square of distance between points
		for(int i = 0; i < dim; i++){
			double result = Math.abs(a.getCoordinates()[i] - b.getCoordinates()[i]);
			result = result * result;
			distance = distance + result;
		}
		distance = (double) Math.sqrt(distance);
		return distance;
	}

	/**Find Distance objecct has smaller distance variable than other.
	 * @param a - Distance object
	 * @return Distance object that has smaller distance
	 */
	public Distance distance_min_distance(Distance a){
		double min = Math.min(a.getDistance(), this.getDistance());	//min distance
		if(min == a.getDistance()){
			return a;
		}else{
			return this;
		}
	}
}
