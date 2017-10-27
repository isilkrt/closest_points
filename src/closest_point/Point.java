package closest_point;

import java.util.List;

import java.lang.Comparable;
public class Point implements Comparable<Point>{
	
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
	public int compareTo(Point p){

		if(this.equals(p)){
			return 0;
		}
        int number = 0;
		int lastCmp = this.getDimensions()[number].compareTo(p.getDimensions()[number]);
		if(lastCmp == 0){
			for(int i = 0; i<dimension; i++){
				lastCmp = dimensions[i].compareTo(p.getDimensions()[i]);
			}
		}
        return lastCmp;
	}
	
	public boolean equals(Object o) {
        if (!(o instanceof Point))
            return false;
        Point n = (Point) o;

        for(int i = 0; i<dimension; i++){

        	if(n.getDimensions()[i] != this.getDimensions()[i]){
        		return false;
        	}
        }
        
        return true;
    }

    public int hashCode() {
        return this.order;
    }

    public String toString() {
    	return Integer.toString(order);
    }
}
