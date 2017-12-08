package edu.csu2017fa314.T08.View;
import static org.junit.Assert.*;


import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;


import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;


@Ignore
public class testmakeSvg {

    @Before
    public void setUp() throws Exception 
    {

    }
	
    @Test
    public void testxmlDec() {
	assertEquals(makeSvg.xmlDec(),"<?xml version=\"1.0\"?>");
    }

    @Test	
    public void testSvgSizeTag(){
	assertEquals(makeSvg.svgSizeTag(),"<svg width=\"1280\" height=\"1024\" xmlns=\"http://www.w3.org/2000/svg\">");	
    }

    @Test	
    public void testSvgTag(){
	assertEquals(makeSvg.svgTag(),"</svg>");	
    }

    @Test
    public void testGTag1(){
	assertEquals(makeSvg.gTag1(),"<g>");
    }

    @Test	
    public void testGTag2(){
	assertEquals(makeSvg.gTag2(),"</g>");
    }

    @Test	
    public void testTitleTag(){
	assertEquals(makeSvg.titleTag("Title"),"<title>Title</title>");
    }

    @Test	
    public void testCommentTag(){
	assertEquals(makeSvg.commentTag("Comment"),"<!-- Comment-->");
    }
	
    @Test
    public void testDrawLine() {
	assertEquals(makeSvg.drawLine("Line",0,0,0,0,1,"#000000"),"<line id=\"Line\" y2=\"0\" x2=\"0\" y1=\"0\" x1=\"0\" stroke-width=\"1\" stroke=\"#000000\"/>");
    }

    @Test    
    public void testDrawStartPoint() {
	assertEquals(makeSvg.drawStartPoint(200,200,1,"blue","blue",1),"<circle cx=\"200\" cy=\"200\" r=\"1\" stroke=\"blue\" fill=\"blue\" stroke-width=\"1\"/>");
	}
	
    /*@Test
    public void testAddText() {
		return "<text x=\"" + x + "\" y=\"" + y + "\" font-family=\"" + fontFamily + "\" font-size=\"" + size + "\" fill=\"" + color + "\">" + actualText + "</text>";
    }

    @Test    
    public void testConvertLatitude(double latitude, double y1, double y2){
		return ((y2 - y1) * ((85 - latitude) / (85 - (-85)))) + y1;
    }
	
    @Test	
    public void testConvertLongitude(double longitude, double x1, double x2){
		return ((x2 - x1) * ((-180 - longitude) / (-180 - 180))) + x1;
	}

    @Test
    public void testDrawInterpolationLines(){
		String newLines = "";
		newLines += ("\n" + drawLine("path", largerX, largerY, 800, (((800-largerX)/((800-largerX)+smallerX)*(smallerY-largerY))+largerY), 1, "#000000"));
		newLines += ("\n" + drawLine("path", 0, (((800-largerX)/((800-largerX)+smallerX)*(smallerY-largerY))+largerY), smallerX, smallerY, 1, "#000000"));
		return newLines;
    }

    @Test
    public void testGetCoordinate(){
		//If selector = 0 return latitude else return longitude		
		double coordinate;
		if(selector == 0){
			coordinate = convertLongitude((edu.csu2017fa314.T08.Model.Distance.degreesToDecimal(DataBase.getLongit(s))),0,800);
		}
		else {
			coordinate = convertLatitude((edu.csu2017fa314.T08.Model.Distance.degreesToDecimal(DataBase.getLatit(s))),0,400);
		}
		return coordinate;
    }*/
}
