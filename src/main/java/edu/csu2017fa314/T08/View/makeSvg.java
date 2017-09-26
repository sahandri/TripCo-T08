public class MakeSVG {

	public static void main(String[] args) {
		String svgFile = xmlDec();
		svgFile += svgSizeTag();
		svgFile += "\n" + gTag1();
		svgFile += "\n" + titleTag("Title");
		svgFile += "\n" + commentTag("Comments go Here!");
		svgFile += "\n" + drawLine("north", 50, 50, 1230, 50, 5, "#6666");
		svgFile += "\n" + gTag2();
		svgFile += "\n" + svgTag();
		System.out.println(svgFile);

	}
	
	public static String xmlDec() {
		return "<?xml version=\"1.0\"?>";
	}
	
	public static String svgSizeTag(){
		return "<svg width=\"1280\" height=\"1024\" xmlns=\"http://www.w3.org/2000/svg\">";
	}
	
	public static String svgTag(){
		return "</svg>";
	}

	public static String gTag1(){
		return"<g>";
	}
	
	public static String gTag2(){
		return"</g>";
	}
	
	public static String titleTag(String title){
		return "<title>"+ title + "</title>";
	}
	
	public static String commentTag(String comment){
		return "<!-- " + comment + "-->";
	}
	
	public static String drawLine(String id, int x1, int y1, int x2, int y2, int strokeWidth, String color) {
		return "<line id=\"" + id + "\" y2=\"" + y2 + "\" x2=\"" + x2 + "\" y1=\"" + y1 + "\" x1=\"" + x1 + "\" stroke-width=\"" + strokeWidth + "\" stroke=\""+ color + "\"/>";
	}
	
	public static String drawAllLines(){
		return "";
	}
}
