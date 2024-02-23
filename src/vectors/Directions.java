package vectors;

public enum Directions {
	UP, DOWN, LEFT, RIGHT, IN, OUT;

    // Method to negate a direction
    public Directions negate() {
        switch (this) {
            case UP: 
            	return DOWN;
            case DOWN: 
            	return UP;
            case LEFT: 
            	return RIGHT;
            case RIGHT: 
            	return LEFT;
            case IN: 
            	return OUT;
            case OUT: 
            	return IN;
            default: 
            	throw new IllegalArgumentException("Unsupported direction");
        }
    }
    
    public static Directions fromString(String text) {
        for (Directions direction : Directions.values()) {
            if (direction.name().equalsIgnoreCase(text)) {
                return direction;
            }
        }
        return null; // Return null if no matching constant is found
    }
    
    public boolean isParallel(Directions d) {
    	if((this == UP || this == DOWN) && (d == UP || d == DOWN))
    		return true;
    	if((this == LEFT || this == RIGHT) && (d == LEFT || d == RIGHT))
    		return true;
    	if((this == IN || this == OUT) && (d == IN || d == OUT))
    		return true;
    	return false;
    }
    
}

