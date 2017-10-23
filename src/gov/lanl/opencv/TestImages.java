package gov.lanl.opencv;

public class TestImages {

	private static String user = "Miguel";
	private static String path = "C:\\Users\\" + user + "\\Desktop\\Alexie";
	
	public static String getBasePath(){
		return path;
	}
	public static String getScenePath() {
		return path + "\\Scene.jpg";
	}
	
	public static String getSubjectPath(){
		return path + "\\Subject.jpg";
	}
	
	public static String textOutputPath(String filename){
		return "C:\\output\\" + filename;
	}
	
}
