package gov.lanl.opencv;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import javax.imageio.ImageIO;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;

import gov.lanl.dll.V3_3_x86.CLibrary;

public class Generate {

	public CLibrary cl;
	
	int h1;
	int w1;
	byte[] buf1;
	
	public Generate(){		
		
		Native.setProtected(true);
		//remember you added the external folder location in Build Path -> Library -> Add External Folder
		cl = (CLibrary) Native.loadLibrary("ImageMatcher", CLibrary.class);

		cl.setDetector(0);
		cl.setMatcher(1);			
	}
	
	public void generateDescriptorsTextFile() {
		
		  try {
				String s = cl.getDescriptorsAsString();
				Files.write(Paths.get("C:\\output\\imageDescriptors.txt"), s.getBytes(), StandardOpenOption.CREATE);
	        } catch(Exception e) {
	        	
	        }		
	}
	
	public void generateKeypointsTextFile() {
		
		cl.getSubjectKeypoints();
	}
	
	private boolean setBuffersAndDimensions(String imagePath) throws IOException {
    	/* Load object we are searching for */
		BufferedImage img = ImageIO.read(new File(imagePath));
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
		
		//set the image and extract features from it
		cl.extractFeaturesFromImage(buf1, h1, w1);
		PointerByReference myFloats = new PointerByReference();
		cl.getDescriptorsAsArray(myFloats);
		
		final Pointer descriptorVals = myFloats.getValue();
		
		/*
		for (int i=0; i<9; i++) {
			Float valAti = descriptorVals.getFloat(i * Native.getNativeSize(Float.TYPE));
			System.out.println(valAti);
		}	
		*/
		//free memory
		cl.cleanUp(descriptorVals);
				
		return true;
	}
	
	
	public static void main(String[] args){
		Generate gt = new Generate();
		try {
			
			gt.setBuffersAndDimensions("C:\\Users\\299490\\Desktop\\Alexie\\Subject.jpg");
			//gt.generateDescriptorsTextFile();
			//gt.generateKeypointsTextFile();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
