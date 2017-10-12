package gov.lanl.opencv;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;

import javax.imageio.ImageIO;

import com.sun.jna.Native;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

import gov.lanl.dll.V3_3_x86.CLibrary;

public class MatchTest {

	public CLibrary cl;
	
	public MatchTest(){		
		
		Native.setProtected(true);
		//remember you added the external folder location in Build Path -> Library -> Add External Folder
		cl = (CLibrary) Native.loadLibrary("ImageMatcher", CLibrary.class);

		cl.setDetector(0);
		cl.setMatcher(1);			
	}
	public void run() {
		   String path = TestImages.getBasePath();
	       String searchObject = path + "\\Subject.jpg";//"\\MICC-Logos\\pepsi\\pepsi_11.png"; 
	       String searchScene = path + "\\Scene.jpg"; //"\\MICC-Logos\\pepsi\\pepsi_13.png";
				
	        try {
	        	
	        	byte[] buf1, buf2;
	        	
	        	/* Load object we are searching for */
				BufferedImage img = ImageIO.read(new File(searchObject));
				int h1;
				int w1;			
				int iType1 = img.getType();
				
				if((img.getWidth() * img.getHeight())/1024 < 300){
					img = gov.lanl.opencv.Processing.scaleUp(img);
				}
				
				if(iType1 != 5 && iType1 != 10 && iType1 != 4 && iType1 != 1) {
					BufferedImage iBGR1;
					System.out.println("Converting to BGR");
					iBGR1 = gov.lanl.opencv.Processing.convertToBGR(img);
					img.flush();
					buf1 = ((DataBufferByte) iBGR1.getRaster().getDataBuffer()).getData();
					h1 = iBGR1.getHeight();
					w1 = iBGR1.getWidth();
				} else {
					buf1 = ((DataBufferByte) img.getRaster().getDataBuffer()).getData();
					h1 = img.getHeight();
					w1 = img.getWidth();
				}  
				

					
				int h2;
				int w2;
	        
				BufferedImage img1 =ImageIO.read(new File(searchScene));
				
				//int size = (img1.getHeight() * img1.getWidth())/1024;

				iType1 = img1.getType();
				if(iType1 != 5 && iType1 != 10 && iType1 != 4 && iType1 != 1){
					System.out.println("Converting to BGR");
					BufferedImage iBGR2;
					iBGR2 = gov.lanl.opencv.Processing.convertToBGR(img1);
					buf2 = ((DataBufferByte) iBGR2.getRaster().getDataBuffer()).getData();
					h2 = iBGR2.getHeight();
					w2  = iBGR2.getWidth();				
				} else {
					buf2 = ((DataBufferByte) img1.getRaster().getDataBuffer()).getData();
					h2 = img1.getHeight();
					w2  = img1.getWidth();	
				}
				
				//long startTime = System.currentTimeMillis();  
				IntByReference goodmatches = new IntByReference();
				//long startTime = System.currentTimeMillis();  
				
				cl.setSubjectImage(buf1, h1, w1);
				//cl.setSceneImage(buf2, h2, w2);
				cl.HashAndCompare(buf2, h2, w2, goodmatches);
				//cl.testMatching(buf1, h1, w1, buf2, h2, w2, goodmatches);
				//System.out.println("Good Matches: " + goodmatches.getValue() + "\n");
		
				
				//System.out.println("Comparison: " +  (System.currentTimeMillis() - startTime) + "ms");		
				//cl.CompareImageHash(buf1, h1, w1, buf1, h1, w1);
				//img1.flush();
				//buf2 = null;			
		
	        } catch(Exception e){
	        	e.printStackTrace();
	        }		
	}
	public static void main(String[] args){
		MatchTest mt = new MatchTest();
		mt.run();
	}
}
