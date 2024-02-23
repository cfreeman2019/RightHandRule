package vectors;

import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class OriginalRHR {

	private static final Pattern ZPATTERN = compileDirectionPattern("in|out");
    private static final Pattern YPATTERN = compileDirectionPattern("up|down");
    private static final Pattern XPATTERN = compileDirectionPattern("right|left");
    
    private static Pattern compileDirectionPattern(String direction) {
        String regex = "(?i)\\b(?:" + direction + ")\\b";
        return Pattern.compile(regex);
    }
    
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		boolean runTest = true;
		while(runTest) {
			System.out.println("Possible directions are -> up, down, in, out, left, or right.\n"
					+ "This is not case sensitive, but any other input and this vector will be assumed as unknown." );
			
			System.out.println("Give a direction for velocity: ");
			String velocity = input.nextLine();
			
			System.out.println("Give a direction for magnetic field: ");
			String magneticField = input.nextLine();
			
			
			System.out.println("Give a direction for force: ");
			String force = input.nextLine();
			
			System.out.println("Is the point charge positive or negative?\n"
					+ "Type 'true' for positive and anything else for negative");
			String chargeString = input.nextLine();
			boolean chargeSign = Boolean.parseBoolean(chargeString);
			//need to ask for input if charge is positive or negative
			
			System.out.println(interaction(velocity, magneticField, force, chargeSign));
			
			System.out.println("Would you like to test for another set of vectors? (Enter true to continue)");
			String continueTest = input.nextLine();
	        runTest = Boolean.parseBoolean(continueTest);
			
		}
	}
	
	public static String interaction(String v, String b, String f, boolean pc) { //need some RegEx
		v = isDirection(v);
		b = isDirection(b);
		f = isDirection(f);
		
		if(notEnoughInfo(v, b, f))
		    return "Not enough information to determine what you were looking for.";
		if(isForceZero(v, b, f)) {
			return "The Force is zero";
		}
		if(isImpossible(v, b, f))
			return "This configuration is impossible.";
		if(v == null) //finding velocity
			return "Velocity's direction" + findThirdDirection(b,f, pc);
		if(b == null)  //finding magField
			return "Magnetic Field's direction" + findThirdDirection(f,v, pc);
		if(f == null)  //finding force
			return "Force's direction" + findThirdDirection(v,b, pc);
		return "There are no unkwowns to find, but this field is possible";
	}
	
	public static String isDirection(String field){ //uses RegEx to determine if input is a direction if not, it makes that variable null
	    String regex = "(?i)\\b(?:up|down|in|out|left|right)\\b";
        Pattern directionPattern = Pattern.compile(regex);
        if (!directionPattern.matcher(field).find())
        	field = null;
        return field;
	}
	
	public static boolean notEnoughInfo(String v, String b, String f){ //makes sure there is enough information given
        if((v == null && b == null) || (v == null && f == null) || (b == null && f == null))
            return true;
        else
            return false;
	}
	
	public static boolean isForceZero(String v, String b, String force) {
	if(ZPATTERN.matcher(v).find() && ZPATTERN.matcher(b).find())
		return true;
	if(YPATTERN.matcher(v).find() && YPATTERN.matcher(b).find())
		return true;
	if(XPATTERN.matcher(v).find() && XPATTERN.matcher(b).find())
		return true;
			
	return false;
	}
	
	public static boolean isImpossible(String v, String b, String f) {

		if(ZPATTERN.matcher(f).find() && ZPATTERN.matcher(b).find())
			return true;
		if(YPATTERN.matcher(f).find() && YPATTERN.matcher(b).find())
			return true;
		if(XPATTERN.matcher(f).find() && XPATTERN.matcher(b).find())
			return true;

		if(ZPATTERN.matcher(f).find() && ZPATTERN.matcher(v).find())
			return true;
		if(YPATTERN.matcher(f).find() && YPATTERN.matcher(v).find())
			return true;
		if(XPATTERN.matcher(f).find() && XPATTERN.matcher(v).find())
			return true;
		
		return false;
	}
	
	public static String findThirdDirection(String field1, String field2, boolean charge) {
		if(charge) {
			if(field1.equalsIgnoreCase("up")) {
				switch(field2.toLowerCase()) {
				case "left":
					return " is out";
				case "right":
					return " is in";
				case "in":
					return " is left";
				case "out":
					return " is right";
				}
			}
			if(field1.equalsIgnoreCase("down")) {
				switch(field2.toLowerCase()) {
				case "left":
					return " is in";
				case "right":
					return " is out";
				case "in":
					return " is right";
				case "out":
					return " is left";
				}
			}
			if(field1.equalsIgnoreCase("left")) {
				switch(field2.toLowerCase()) {
				case "up":
					return " is in";
				case "down":
					return " is out";
				case "in":
					return " is down";
				case "out":
					return " is up";
				}
			}
			if(field1.equalsIgnoreCase("right")) {
				switch(field2.toLowerCase()) {
				case "up":
					return " is out";
				case "down":
					return " is in";
				case "in":
					return " is up";
				case "out":
					return " is down";
				}
			}
			if(field1.equalsIgnoreCase("in")) {
				switch(field2.toLowerCase()) {
				case "up":
					return " is right";
				case "down":
					return " is left";
				case "left":
					return " is up";
				case "right":
					return " is down";
				}
			}
			if(field1.equalsIgnoreCase("out")) {
				switch(field2.toLowerCase()) {
				case "up":
					return " is left";
				case "down":
					return " is right";
				case "left":
					return " is down";
				case "right":
					return " is up";
				}
			}
		}
		else {
			if(field1.equalsIgnoreCase("down")) {
				switch(field2.toLowerCase()) {
				case "left":
					return " is out";
				case "right":
					return " is in";
				case "in":
					return " is left";
				case "out":
					return " is right";
				}
			}
			if(field1.equalsIgnoreCase("up")) {
				switch(field2.toLowerCase()) {
				case "left":
					return " is in";
				case "right":
					return " is out";
				case "in":
					return " is right";
				case "out":
					return " is left";
				}
			}
			if(field1.equalsIgnoreCase("right")) {
				switch(field2.toLowerCase()) {
				case "up":
					return " is in";
				case "down":
					return " is out";
				case "in":
					return " is down";
				case "out":
					return " is up";
				}
			}
			if(field1.equalsIgnoreCase("left")) {
				switch(field2.toLowerCase()) {
				case "up":
					return " is out";
				case "down":
					return " is in";
				case "in":
					return " is up";
				case "out":
					return " is down";
				}
			}
			if(field1.equalsIgnoreCase("out")) {
				switch(field2.toLowerCase()) {
				case "up":
					return " is right";
				case "down":
					return " is left";
				case "left":
					return " is up";
				case "right":
					return " is down";
				}
			}
			if(field1.equalsIgnoreCase("in")) {
				switch(field2.toLowerCase()) {
				case "up":
					return " is left";
				case "down":
					return " is right";
				case "left":
					return " is down";
				case "right":
					return " is up";
				}
			}
		}
	return " Invalid";
	}
}
