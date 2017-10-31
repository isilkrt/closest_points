package closest_point;

import java.util.Formatter;
import java.util.List;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Point.java
 */
public class Point /*implementsa Comparable<Point>*/{

	/**Keep order of point in point list.
	 * @HasGetter
	 * @HasSetter
	 */
	private int order;

	/**Keep dimension of point.
	 * @HasGetter
	 * @HasSetter
	 */
	private int dimension;

	/**List to keep coordinates of points
	 */
	private Double[] coordinates;

	/**
	 * Class constructor.
	 */
	Point(){
	}

	/**Class constructor with three parameters.
	 * @param order - point order in point list
	 * @param dimension - point dimension
	 * @param coordinates - keep values of axes
	 */
	Point(int order, int dimension, Double[] coordinates){
		this.order = order;
		this.dimension = dimension;
		this.coordinates = coordinates;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public Double[] getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Double[] coordinates) {
		this.coordinates = coordinates;
	}

	public int getDimension() {
		return dimension;
	}

	public void setDimension(int dimension) {
		this.dimension = dimension;
	}

	/**
	 * @return String to write points axis values.
	 */
	public String print(){
		DecimalFormat df = new DecimalFormat("#");
		df.setMaximumIntegerDigits(6);
		df.setMaximumFractionDigits(1);
		String write = this.order + ":";
		for(int i = 0;i<dimension ; i++){

			write = write + df.format(this.coordinates[i]) +"\t";
		}
		return write;
	}
}
