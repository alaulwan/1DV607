package model;

public class Boat {
	public static int IDstatic=1;
	public enum Type {
		Sailboat, Motorsailer, kayak , Other
	}
	private int id = 0;
	private double length= 0;
	private Type type = null;
	
	public Boat(Type Type, double Length) {
		id = IDstatic++;
		setType (Type);
		setLength (Length);
		
	}
	
	public Type getType () {
		return type;
	}
	
	public void setType (Type Type) {
		type=Type;
	}
	
	public double getLegth () {
		return length;
	}
	
	public void setLength (double Length) {
		length=Length;
	}
	
	
	
	public void copyOf (Boat Boat) {
		setType (Boat.getType());
		setLength (Boat.getLegth());
	}
}
