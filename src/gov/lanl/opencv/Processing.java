package gov.lanl.opencv;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Processing {

	
	public static BufferedImage convertToBGR(BufferedImage img) throws IOException{
		//int size = (img.getHeight() * img.getWidth())/1024;
		BufferedImage img1;

		img1 = new BufferedImage(img.getWidth(), img.getHeight(),  BufferedImage.TYPE_3BYTE_BGR); 

		
		Graphics2D g2d= img1.createGraphics();
		g2d.drawImage(img, 0, 0, null);
		g2d.dispose();	

		return img;
		//File ouptut = new File("C:\\Users\\299490\\Desktop\\Alexie\\bgr.jpg");
		//ImageIO.write(img1, "jpg", ouptut);
		
	}
		
	public static BufferedImage scaleUp(BufferedImage img){
		int width = img.getWidth() *2;
		int height = img.getHeight() *2;
		BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

		Graphics2D g = newImage.createGraphics();
		//g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(img, 0, 0, width, height, null);
		g.dispose();		
		return newImage;
		
	}
}
