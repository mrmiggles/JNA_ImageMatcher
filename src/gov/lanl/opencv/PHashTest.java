package gov.lanl.opencv;

import java.util.Vector;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.DoubleByReference;

import gov.lanl.dll.V3_3_x86.CLibrary;

public class PHashTest {

	public CLibrary cl;
	
	public PHashTest(){		
		
		Native.setProtected(true);
		//remember you added the external folder location in Build Path -> Library -> Add External Folder
		cl = (CLibrary) Native.loadLibrary("ImageMatcher", CLibrary.class);

		cl.setDetector(0);
		cl.setMatcher(1);			
	}
	public void run() {
		
		Vector<Integer> h1 = new Vector<Integer>();
		Vector<Integer> h2 = new Vector<Integer>();
		
		h1.add(236);
		h1.add(77);
		h1.add(135);
		h1.add(137);
		h1.add(41);
		h1.add(82);
		h1.add(19);
		h1.add(56);
		
		h2.add(100);
		h2.add(141);
		h2.add(19);
		h2.add(14);
		h2.add(145);
		h2.add(36);
		h2.add(219);
		h2.add(112);		
		
		//similar (simpler) way of doing things
		//int[] hash1 = new int[8];
		//int[] hash2 = new int[8];
		
		/*
		for(int i = 0; i < h1.size(); i++) {
			hash1[i] = h1.get(i).intValue();
			hash2[i] = h2.get(i).intValue();
			
			System.out.println(hash1[i]);
			System.out.println(hash2[i]);
			
		}
		*/
		
		final Pointer hash1 = new Memory(8 * Native.getNativeSize(Integer.TYPE));
		final Pointer hash2 = new Memory(8 * Native.getNativeSize(Integer.TYPE));
		
		for(int i = 0; i < h1.size(); i++) {
			hash1.setInt(i*Native.getNativeSize(Integer.TYPE), h1.get(i));
			hash2.setInt(i*Native.getNativeSize(Integer.TYPE), h2.get(i));
			
		}

		
		System.out.println("----------------------------------------------------\n");
		DoubleByReference hashComparisonResult =  new DoubleByReference();
		cl.comparePrecomputedHashes(hash1, hash2, hashComparisonResult);
		System.out.println("Hash Compare Result: " + hashComparisonResult.getValue());
		
	}
	
	public static void main(String[] args){
		PHashTest t = new PHashTest();
		t.run();
	}
	
}
