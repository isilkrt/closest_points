## Find Closest Points using Divide and Conquer Aproach

* Divide and conquer algorithm can be used to find closest pair of points from set of multidimensional points because it is effective in the case of multidimentional points.
  * It recursively divides problem to sub-problems. 
  * In this code, list of point divided by two and for each part calls divide conquer algorithm again. 
  * It returns 'Distance' object that have a distance variable and two Point object referances. 
    
* In conquer part, last operations are done. Conquer part looks over the intersection of two part of points that are divided by algorithm.   
  * The code looks for smaller distance from last minimum distance that is found divide part. Therefore, the code searchs only close points. 
  * For example, if points have only two dimension, looking for at most seven points is enough to find smaller distance (O(1) complexity for fixed size). It depends on dimension. 

  * The formula is (2^(d+1) - 1) points. (d : dimension) [1],[2]


### Input && Output Files

* It can read tsv file and write txt files. 

* Each line of input file contains coordinates of multidimensional points. Therefore, the number of points equals to line number. Each number are seperated with "\t" and they can be float and negative. 

* Output file includes line numbers and coordinates closest pairs.

**Note : If there are many points have same distance, this code regards only first two points.**  

### Run Time & Comlexity

* Divide  : T(n/2, d).2   
Conquer : T(n, d-1)   
Sort    : n.logn   
T(n, d) = T(n/2, d).2 + T(n, d-1) + n.logn = n.(logn)^d-1    

* dimension: 10    
number of points : 10    
runtime : 83.4 ms   

* dimension: 100   
number of points : 10    
runtime : 123.2 ms

* dimension: 10
number of points : 100
runtime : 198.8 ms

* dimension: 100    
number of points : 100    
runtime : 6574318.0 ms

It can be easily seen that the increase of number of points is more effective that dimension. Therefore, this aproach is more effective that others in multidimensional points. 

### Run

Java code takes only one argument that is file name. 
In repo file(closest_points), for example, write;
  * set ..................\closest_points\build (path)
  * javac -d .\build .\src\closest_point\*.java
  * java closest_point.MainClass sample_input_10_10

Or in eclipse, write file name to arguments and run.      
     
     
### Appendix

[Link 1](https://www.google.com.tr/url?sa=t&rct=j&q=&esrc=s&source=web&cd=8&cad=rja&uact=8&ved=0ahUKEwjDlYX4qZnXAhWhNJoKHe2oAWgQFghbMAc&url=http%3A%2F%2Fcs.iupui.edu%2F~xkzou%2Fteaching%2FCS580%2FDivide-and-conquer-closestPair.ppt&usg=AOvVaw1ganNQ0oWoUJzvQHwVBsQi)  


[Link 2](https://www.cs.ucsb.edu/~suri/cs235/ClosestPair.pdf)

[Link 3](https://en.wikipedia.org/index.php?q=aHR0cHM6Ly9lbi53aWtpcGVkaWEub3JnL3dpa2kvRGl2aWRlX2FuZF9jb25xdWVyXyhhbGdvcml0aG0p)
