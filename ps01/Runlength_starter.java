
/* Jason Morse
   9/11/13
   Professor Blumenthal
   
   Computer Science 102 Assignment One:
   Arrays, conditionals and control statements
   
   Write the code for method runlength to figure out how to compute the runlength 
   for each value in the array as you read through the array and print it out
*/
public class Runlength_starter {

	public static void runlength(int[] a) { // method runlength
		 if (a == null) return;
		 
		 int count = 0;
		 int curr = a[0];
		 
		 for(int i=0; i<a.length; i++) {
			 
			 if (a[i] == curr) {
				 count++;
				 }else{
					 System.out.println(curr + " " + count);
					 count = 1;
					 curr = a[i];
				 }
			 }
		 
		 System.out.println(curr + " " + count);
		 
		 }
		 
	public static void main(String[] args) {
	        int[] a = {1,1,2,3,3,4,5,5,5,6,6,8,9,9,9,7,7}; // create an int array with default values
	        runlength(a); // pass array a into method runLength
    }
}


//tips: 

		 // you can get the length of array a object by accessing it's length field:  a.length
         // use == to compare int values
         // e.g.
         // int a = 1;
         // int b = 2;
         // if (a == b) {
         //  ...do something...
         // }
         // else {
         // ...do something else...
         // }
		 // examples of accessing values in array:
		 // int index = 0;
		 // int val = val = a[index]; // val is set to value of first element of array a
		 
		 // index ++; // increment index by 1
		 
		 // val = a[index]; // val is now set to value of second element of array a
		 // index = index + 6;  
		 // val = a[index]; // val is now set to value of 8th element of array a

		 // int valRunLength=0; create a runlength variable of type int

		 // Sample of the type of printing you need to do
		 // System.out.println(val + " " + valRunLength );