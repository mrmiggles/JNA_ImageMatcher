package gov.lanl.dll;

import com.sun.jna.Library;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;


public class V3_3_x86 {

	
	CLibrary cl;

	/**
	 * 
	 * 
	 * setDectector(int type)
	 * 0 - SIFT
	 * 1 - AKAZE -- Note ** (AKAZE descriptors can only be used with KAZE or AKAZE keypoints)
	 * 2 - ORB -- Note ** (ORB descriptors must be converted to CV_32F in order to work with FLANN)
	 * 3 - SURF 
	 * 4 - BRISK
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
		
		
		boolean setDetector(int type); //if not called Default is SIFT
		boolean setMatcher(int type); //if not called Default is BRUTE FORCE W/HAMMING
		void testMatching(byte[] buf1, int h1, int w1, byte[] buf2, int h2, int w2);
		
		
		boolean extractFeaturesFromImage(byte[] p, int height, int width);
		String getDescriptorsAsString();
		String getKeypointsAsString();
		void getDescriptorsByReference(PointerByReference p);
		void fillDescriptorArray(float[] desc);
		
		void getDescriptorRows(IntByReference r);
		void getDescriptorCols(IntByReference c);
		void getDescriptorsType(IntByReference t);
		
		void setSubjectDescriptors(float[] desc, int rows, int cols, int type);
		void printSubjectDescriptors();
		boolean setSubjectKeypoints();
		void getSubjectKeypoints();
		void fillKeypointsArray(float[] keyPoints);
		void getKeypointsSize(IntByReference s);
		

				
		void freeUp(Pointer p);
	}
	
	
}
