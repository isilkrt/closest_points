package closest_point;

import java.lang.*;
import java.text.DecimalFormat;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * MainClass.java
 * @author isil kurt
 * @version v1.0
 * 30.10.2017
 */
public class MainClass {

	public static Distance finald = new Distance();	//
	public static int dimension;
	public static int numberOfPoints;

	/**Main method.
	 * Takes Argument and run main function.
	 * @param args - input file name
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		final long startTime = System.nanoTime(); 				//keep start time of system.

		String filename = args[0];								//input filename
		runFunction(filename);	    							//runs main function for algorithm

		final long duration = System.nanoTime() - startTime; 	//keep end time of system.
		System.out.print("Run time of system : ");
		System.out.print( (float) duration / 1000000 );
		System.out.print(" milliseconds.");

	}

	/**
	 * Method to organize the flow of application. Reads points from input file.
	 * Creates point lists and run divide conquer algorithm.
	 * @param filename - input file name
	 * @throws Exception
	 */
	public static void runFunction(String filename) throws Exception{

		List<Point> point = new ArrayList<Point>();			// point list keeps all points.
		point = readFile(filename);	//get points from input file

		Point[] point_list = new Point[numberOfPoints];
		point_list = point.toArray(point_list);

		finald.setDistance(Float.MAX_VALUE);			//sets minimum distance as maximum float  number.
        finald = divide_conquer(point_list, 0);			//run divide_conquer function

        try{
        	writeFile();					//write final minimum point to output file
        }finally{
        }
	}

	/**
	 * @param point_list - point list to be implemented divide and conquer algorithm.
	 * @param axis	- which axis should be used. (0 means that x- 1 means that y, e.g)
	 * @param dimension	- dimensions of points.
	 * @return Final Distance object that has minimum distance of points.
	 */
	public static Distance divide_conquer(Point [] point_list, int axis){

		if(point_list.length <2){

			/* Point list has zero or one point */
			return null;

		}else if(point_list.length <= 3){

			/* Point list has two or three points */
			Distance distance_object = new Distance(point_list[0], point_list[1]); 	//Distance object.

			if(point_list.length == 3){

				/* Point list has three points
				 * In this condition, there are three different distance object
				 * We should find Distance object that has minimum distance
				 */
				Distance dis2 = new Distance(point_list[1], point_list[2]); 	//other distance
				Distance dis3 = new Distance(point_list[0], point_list[2]); 	//other distance
				dis2 = dis2.distance_min_distance(dis3);						//find Distance with minimum distance.
				distance_object = distance_object.distance_min_distance(dis3); 	//find final Distance with minimum distance.
				return distance_object;

			}else{

				/*Point list has two point
				 * Therefore there is only one Distance object.
				 * Returns distance object
				 */
				return distance_object;
			}

		}else{

			/* Point list has more than three points.
			 * Divide and conquer algorithm should be implemented.
			 * Sorting point list based on axis that we work on.
			 * Dim = 0 : mean that we work on x axis.
			 * Dim = 1 : mean that we work on y axis. e.g.
			 */
			java.util.Arrays.sort(point_list, java.util.Comparator.comparingDouble(a -> a.getCoordinates()[axis]));

			int length = point_list.length;		//length of point list.
			Point [] left_side = new Point [length/2];		//create a new list which size is equal to half of main point list
			int a = length/2;
			if(length%2 != 0 ){
				a ++;
			}
			Point [] right_side  = new Point [a]; 	//create a new list which size is equal to size(point list) - size(left_size)

			// dividing point list. left_side and right_side
			for(int i = 0; i<length/2;i++){
				left_side[i] = point_list[i];
			}
			for(int i= length/2; i<length;i++){
				right_side[i-length/2] = point_list[i];
			}

			Distance first_min = divide_conquer(left_side, axis);
			Distance sec_min =  divide_conquer(right_side, axis);
			Distance min_divide_part = first_min.distance_min_distance(sec_min);

			float divide_distance = min_divide_part.getDistance();
			float finald_distance = finald.getDistance();
			if(divide_distance < finald_distance){
				finald = new Distance(divide_distance, min_divide_part.getA(), min_divide_part.getB());
			}

			/* Find median of used axis. */
			float med1 = left_side[left_side.length-1].getCoordinates()[axis];
			float med2 = right_side[0].getCoordinates()[axis];
			float median = (Float)(Math.abs(med1 + med2))/2;

			/* Point list oluştur to keeps points that are in intersection area of two divided parts.
			 * Starts conquer part.
			 */
			List<Point> conquer_list = new ArrayList<Point>();

			//find points by using median and minimum distance.
			for(int i = 0; i<length;i++){
				if(point_list[i].getCoordinates()[axis] <= divide_distance+median){
					conquer_list.add(point_list[i]);
				}else if(point_list[i].getCoordinates()[axis] >= median-divide_distance){
					conquer_list.add(point_list[i]);
				}
			}

			Point[] conquer_poins = new Point[conquer_list.size()];
			conquer_poins = conquer_list.toArray(conquer_poins);			// List to array conversion.

			/* Axis represents our working axis.
			 * If dimension - axis is smaller than 3, we can find minimum distance between point.
			 * If not, we should change axis and run divide conquer algorithm again for point list.
			 */
			if(dimension - axis < 3){
				int size = conquer_poins.length;	//length of point list

				/* for every point, search closest point.
				 * In this step, search only fixed number of points. */
				for(int p = 0; p<size ; p++){
					int number_point = (int) Math.pow(2,dimension+1) - 1; 	//number of points that are searched

					if(size < number_point){

						//if there is not enough point, change number_point to look
						number_point = size;
					}

					// look fixed number points that are closest.
					for(int i = p+1; i<number_point; i++){
						Point first = conquer_poins[p];		//main point
						Point second = conquer_poins[i];	//sample point
						Distance cc = new Distance(first, second);

						float closeness = cc.getDistance();
						finald_distance = finald.getDistance();

						if(finald_distance > closeness){

							// new distance is smaller than old.
							finald = cc;
						}
					}
				}
			}else{

				// dimension - axis bigger than 2.
				// Change dimension and call divide conquer algorithm for new list.
				Distance space_min = divide_conquer(conquer_poins, axis+1);

				float closeness = space_min.getDistance();
				finald_distance = finald.getDistance();
				if(closeness < finald_distance){
					finald = space_min;
				}
			}

			return finald;
		}
	}

	/**
	 * @param dimension - dimension of points
	 * @param numberOfPoints - number of input points
	 * @param filename - input file name
	 * @return point_list - List of Points
	 * @throws Exception
	 */
	public static List<Point> readFile(String filename) throws Exception{

		BufferedReader inputFile = new BufferedReader(new FileReader("./input/"+filename+".tsv"));

		String line = inputFile.readLine();
		List<Point> point_list = new ArrayList<Point>();

		int order = 0; //order of input points
		while (line != null){

			String[] listOfDimension = line.split("\t");
			dimension = listOfDimension.length;
			Float[] float_dimensions = new Float[dimension];  	// dimension values of point

			int count = 0;	// axis - 0 mean x axis
			for (String num: listOfDimension) {
				float_dimensions[count] = Float.parseFloat(num);
				count ++;
			}

			Point new_point = new Point(order+1, dimension, float_dimensions); // create new point with arguments
			point_list.add(new_point);

			order++;	//increase order for following point
			line = inputFile.readLine();
		}
		numberOfPoints = point_list.size();
		inputFile.close();
		return point_list;
	}

	/**
	 * Write closest point orders and coordinates to output file.
	 * @param dim - dimension of point
	 * @param number - number of point
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 */
	public static void writeFile() throws IOException, UnsupportedEncodingException{

		if(finald==null){
			System.out.println("There are not enough point. Zero or one.");
		}else{
			df.setMaximumIntegerDigits(6);
			df.setMinimumIntegerDigits(1);
			df.setMaximumFractionDigits(1);
			df.setGroupingSize(6);

			//process to write output file
			String file = "sample_output_" + dimension +"_" + numberOfPoints + ".txt" ;
			PrintWriter writer = new PrintWriter(file, "UTF-8");

			if(finald.getA().getOrder() > finald.getB().getOrder()){
				Point temp = finald.getA();
				finald.setA(finald.getB());
				finald.setB(temp);
			}

			writer.print(finald.getA().getOrder());
			writer.print(":");
			for(int i = 0; i<dimension; i++){
				String h = df.format(finald.getA().getCoordinates()[i]).replace(',', '.');
				writer.print(h + "\t");
			}

			writer.println();
			writer.print(finald.getB().getOrder());
			writer.print(":");
			for(int i = 0; i<dimension; i++){
				String h = df.format(finald.getB().getCoordinates()[i]).replace(',', '.');
				writer.print(h + "\t");
			}

			writer.close();
		}
	}
}
