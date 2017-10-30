package closest_point;

import java.util.Formatter;
import java.util.List;

import java.lang.Comparable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
public class Point{

	private int order;
	private int dimension;
	private Float[] dimensions;

	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public Float[] getDimensions() {
		return dimensions;
	}
	public void setDimensions(Float[] dimensions) {
		this.dimensions = dimensions;
	}
	public int getDimension() {
		return dimension;
	}
	public void setDimension(int dimension) {
		this.dimension = dimension;
	}
	public String print(){
		DecimalFormat df = new DecimalFormat("#");
		df.setMaximumIntegerDigits(6);
		df.setMaximumFractionDigits(1);

		String write = this.order + ":";
		for(int i = 0;i<dimension ; i++){

			write = write + df.format(this.dimensions[i]) +"\t";
		}
		return write;
	}
}
