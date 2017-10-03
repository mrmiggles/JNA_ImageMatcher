package gov.lanl.dll;

import com.sun.jna.Library;

public class V3_3_x64 {

	CLibrary cl;

	/**
	 * 
	 * 
	 * setDectector(int type)
	 * 0 - BRISK
	 * 1 - AKAZE -- Note ** (AKAZE descriptors can only be used with KAZE or AKAZE keypoints)
	 * 2 - ORB -- Note ** (ORB descriptors must be converted to CV_32F in order to work with FLANN)
	 * 3 - SURF //not yet implemented
	 * 4 - SIFT //not yet implemented
	 * 5 - FREAK
	 * 
	 * setMatcher(int type)
	 * 0 - BruteForce
	 * 1 - FLANN
	 * 
	 * 
	 * 
	 * Use: 
	 *  - Set your detector
	 *  - Set your matcher
	 *  - Set your subject image (The base image to search for in other images);
	 *  - Detect Keypoints and Descriptors for Subject Image
	 *  
	 *  
	 */
	public interface CLibrary extends Library {

		
	}
	
	public void run() {
		
	}
	
	public static void main(String[] args){
		V3_3_x64 v33 = new V3_3_x64();
		v33.run();
	}
}
