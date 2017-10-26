package gov.lanl.dll;

import com.sun.jna.Library;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.DoubleByReference;
import com.sun.jna.ptr.IntByReference;

public class OpencvPHasher_x86 {

	CLibrary cl;

	public interface CLibrary extends Library {
	
		void setSceneImage(byte[] buf1, int pictureHeight, int pictureWidth);		
		void setSubjectImage(byte[] buf1, int pictureHeight, int pictureWidth);
		
		void CompareImageHash(byte[] buf1, int h1, int w1, byte[] buf2, int h2, int w2);
		void HashAndCompare(byte[] buf1, int h1, int w1, DoubleByReference compareResult);
		void comparePrecomputedHashes(int[] hash1, int[] hash2, DoubleByReference result);
		void comparePrecomputedHashes(Pointer hash1, Pointer hash2, DoubleByReference result);
	}
}
