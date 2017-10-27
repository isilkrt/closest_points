package closest_point;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainClass {
	
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
		
        Arrays.sort(point_list);

        /*for (Point p: point_list){
        	System.out.println(p.getOrder());
        	//System.out.println(p.getDimension());
        }*/
        
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

}
