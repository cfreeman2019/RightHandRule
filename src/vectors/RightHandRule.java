package vectors;

import java.util.Scanner;

//need to add comments to code
//try making a version where it only asks for charge if force isn't zero or the field configuration isn't possible
public class RightHandRule
{
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		boolean runTest = true;
		while(runTest) {
			System.out.println("Possible directions are -> up, down, in, out, left, or right.\n"
					+ "This is not case sensitive, but any other input and this vector will be assumed as unknown." );
			
			System.out.println("Give a direction for velocity: ");
			String velocity = input.nextLine();
			Directions velocityDirection = Directions.fromString(velocity);
			
			System.out.println("Give a direction for magnetic field: ");
			String magneticField = input.nextLine();
			Directions magFieldDirection = Directions.fromString(magneticField);
			
			System.out.println("Give a direction for force: ");
			String force = input.nextLine();
			Directions forceDirection = Directions.fromString(force);
			
			System.out.println("Is the point charge positive or negative?\n"
					+ "Type 'true' for positive and anything else for negative");
			String chargeString = input.nextLine();
			boolean chargeSign = Boolean.parseBoolean(chargeString);
			
			System.out.println(interaction(velocityDirection, magFieldDirection, forceDirection, chargeSign));
			
			System.out.println("Would you like to test for another set of vectors? (Enter true to continue)");
			String continueTest = input.nextLine();
	        runTest = Boolean.parseBoolean(continueTest);
		}
		input.close();
	}
	
	public static String interaction(Directions v, Directions b, Directions f, boolean pc) {
		
		if(notEnoughInfo(v,b,f))
		    return "Not enough information to determine what you were looking for.";
		if(v != null) {
			if(v.isParallel(b))
				return "The Force is zero";
		}
		if(isImpossible(v,b,f))	
			return "This configuration is impossible.";
		if(v == null) {
			if(pc)
				return "Velocity's direction is " + findThirdDirection(b,f);
			else
				return "Velocity's direction is " + findThirdDirection(b,f).negate();
		}
		if(b == null) {
			if(pc)
				return "Magnetic Field's direction is " + findThirdDirection(v,f).negate();
			else
				return "Magnetic Field's direction is " + findThirdDirection(v,f);
		}
		if(f == null) {  //finding force
			if(pc)
				return "Force's direction is " + findThirdDirection(v,b).negate();
			else
				return "Force's direction is " + findThirdDirection(v,b);
		}
		return "There are no unkwowns to find";
	}
	
	public static boolean isImpossible(Directions d1, Directions d2, Directions d3) {
		if(d1 != null && d2 != null)
			return d2.isParallel(d3) || d1.isParallel(d3);
		if(d1 == null) {
			return d2.isParallel(d3);
		}
		if(d2 == null) {
			return d1.isParallel(d3);
		}
		return false;
	}
	
	
	public static boolean notEnoughInfo(Directions v, Directions b, Directions f){ //makes sure there is enough information given
        if((v == null && b == null) || (v == null && f == null) || (b == null && f == null))
            return true;
        else
            return false;
	}
	
	public static Directions findThirdDirection(Directions field1, Directions field2) {
		if(field1 == Directions.UP) {
			switch(field2) {
			case RIGHT:
				return Directions.OUT;
			case LEFT:
				return Directions.IN;
			case IN:
				return Directions.LEFT;
			case OUT:
				return Directions.RIGHT;
			}
		}
		if(field1 == Directions.DOWN) {
			switch(field2) {
			case RIGHT:
				return Directions.IN;
			case LEFT:
				return Directions.OUT;
			case IN:
				return Directions.RIGHT;
			case OUT:
				return Directions.LEFT;
			}
		}
		if(field1 == Directions.RIGHT) {
			switch(field2) {
			case UP:
				return Directions.IN;
			case DOWN:
				return Directions.OUT;
			case OUT:
				return Directions.DOWN;
			case IN:
				return Directions.UP;
			}
		}
		if(field1 == Directions.LEFT) {
			switch(field2) {
			case UP:
				return Directions.OUT;
			case DOWN:
				return Directions.IN;
			case OUT:
				return Directions.UP;
			case IN:
				return Directions.DOWN;
			}
		}
		if(field1 == Directions.IN) {
			switch(field2) {
			case UP:
				return Directions.RIGHT;
			case DOWN:
				return Directions.LEFT;
			case RIGHT:
				return Directions.UP;
			case LEFT:
				return Directions.DOWN;
			}
		}
		if(field1 == Directions.OUT) {
			switch(field2) {
			case UP:
				return Directions.LEFT;
			case DOWN:
				return Directions.RIGHT;
			case RIGHT:
				return Directions.DOWN;
			case LEFT:
				return Directions.UP;
			}
		}
		return null;
    }
}