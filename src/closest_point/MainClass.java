package closest_point;

import java.lang.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainClass {

	public static Distance finald = new Distance();

	public static void main(String[] args) throws Exception {

		String filename = args[0];
		runFunction(filename);

	}

	public static void runFunction(String filename) throws Exception{

		String[] tokens = filename.split("_");

		int dimension = Integer.parseInt(tokens[2]);
		int numberOfPoints = Integer.parseInt(tokens[3]);

		Point [] point_list = new Point[numberOfPoints];

		point_list = readFile(dimension, numberOfPoints, filename);

		finald.setDistance(Float.MAX_VALUE);
        finald = divide_conquer_for2d(point_list, 0, dimension);

        System.out.println("Final : ");
        System.out.println(finald.getDistance());
        System.out.println(finald.getA().getOrder());
        System.out.println(finald.getB().getOrder());

        writeFile(dimension, numberOfPoints);
	}

	public static Distance divide_conquer_for2d(Point [] point_list, int Dim, int dimension){

		if(point_list.length <2){
			System.out.println("part 1");
			return null;

		}else if(point_list.length <= 3){
			System.out.println("part 2");
			Distance dd = new Distance(point_list[0], point_list[1]);

			if(point_list.length == 3){
				System.out.println("part 2 1");
				Distance dis2 = new Distance(point_list[1], point_list[2]);
				Distance dis3 = new Distance(point_list[0], point_list[2]);
				float min = Math.min(dis2.getDistance(), dis3.getDistance());
				min = Math.min(dd.getDistance(), dis3.getDistance());
				if(min == dd.getDistance()){
					return dd;
				}else if ( min == dis2.getDistance()){
					return dis2;
				}else{
					return dis3;
				}
			}else{
				System.out.println("part 2 2");
				return dd;
			}

		}else{
			System.out.println("part 3");
			java.util.Arrays.sort(point_list, java.util.Comparator.comparingDouble(a -> a.getDimensions()[Dim]));

			int length = point_list.length;
			Point [] left_side = new Point [length/2];
			int a = length/2;
			if(length%2 != 0 ){
				a ++;
			}
			Point [] r_side  = new Point [a];

			for(int i = 0; i<length/2;i++){
				left_side[i] = point_list[i];
			}

			Distance first_min = divide_conquer_for2d(left_side, Dim, dimension);
			float min1 = first_min.getDistance();

			for(int i= length/2; i<length;i++){
				r_side[i-length/2] = point_list[i];
			}

			Distance sec_min =  divide_conquer_for2d(r_side, Dim, dimension);
			float min2 = sec_min.getDistance();

			float main_min = Math.min(min1,min2);

			if(main_min < finald.getDistance()){
				System.out.println("part 3 1");
				if(main_min == min1){
					System.out.println("part 3 1 1");
					finald = new Distance(main_min,first_min.getA(),first_min.getB());
				}else{
					System.out.println("part 3 1 2");
					finald = new Distance(main_min, sec_min.getA(),sec_min.getB());
				}

			}

			float med1 = left_side[left_side.length-1].getDimensions()[Dim];
			float med2 = r_side[0].getDimensions()[Dim];
			float median = (Float)(Math.abs(med1 + med2))/2;

			List<Point> n = new ArrayList<Point>();

			for(int i = 0; i<length;i++){
				if(point_list[i].getDimensions()[Dim] <= main_min+median){
					n.add(point_list[i]);
				}else if(point_list[i].getDimensions()[Dim] >= median-main_min){
					n.add(point_list[i]);
				}else{

				}
			}

			Point[] nn = new Point[n.size()];
			nn = n.toArray(nn);

			if(dimension - Dim <= 2){
				System.out.println("part 3 2");
				int size = nn.length;
				for(int p = 0; p<size ; p++){
					int seven = (int) Math.pow(2,dimension) -1 ;
					if(size<seven){
						seven = size;
					}
					for(int i = p+1; i<seven; i++){
						Distance cc = new Distance(nn[p], nn[i]);
						float closeness = cc.getDistance();

						if(finald.getDistance() <= closeness){
							System.out.println("part 3 2 1");
						}else{
							System.out.println("part 3 2 2");
							finald.setDistance(closeness);
							finald.setA(nn[p]);
							finald.setB(nn[i]);
						}
					}
				}
			}else{
				System.out.println("part 3 3");
				Distance space_min = divide_conquer_for2d(nn, Dim+1, dimension);
				if(space_min.getDistance() < finald.getDistance()){
					System.out.println("part 3 3 1");
					finald.setA(space_min.getA());
					finald.setB(space_min.getB());
					finald.setDistance(space_min.getDistance());
				}else{
					System.out.println("part 3 3 2");
				}

			}

			return finald;
		}
	}

	public static Point[] readFile(int dimension, int numberOfPoints, String filename) throws Exception{
		BufferedReader inputFile = new BufferedReader(new FileReader("./input/"+filename+".tsv"));

		String line = inputFile.readLine();

		Point[] point_list = new Point[numberOfPoints];

		int order = 0;
		while (line != null){
			Point p = new Point();
			order++;
			p.setOrder(order);

			String[] listOfDimention = line.split("\t");

			Float[] float_dimentions = new Float[dimension];

			int count = 0;
			for (String num: listOfDimention) {
				float_dimentions[count] = Float.parseFloat(num) ;
				count ++;
			}

			p.setDimensions(float_dimentions);
			p.setDimension(dimension);
			point_list[order-1] = p;

			line = inputFile.readLine();
		}

		inputFile.close();
		return point_list;
	}

	public static void writeFile(int dim, int number) throws IOException, UnsupportedEncodingException{

		String file = "sample_output_" + dim +"_" + number + ".txt" ;
		PrintWriter writer = new PrintWriter(file, "UTF-8");
		writer.println(finald.getA().print());
		writer.println(finald.getB().print());
		writer.close();
	}

}
