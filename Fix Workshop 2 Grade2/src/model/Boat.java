package model;

public class Boat {
	static int IDstatic = 1;

	public enum Type {
		Sailboat, Motorsailer, kayak, Other
	}

	private int id = 0;
	private double length = 0;
	private int ownerId = 0;

	private Type type = null;

	public Boat() {
		id = IDstatic++;
		setLength(2);
		setType(Type.Sailboat);
		setOwnerId(0);

	}

	public Boat(Type Type, double Length, int OwnerID) {
		id = IDstatic++;
		setType(Type);
		if (Length >= 1)
			setLength(Length);
		if (OwnerID > 0)
			setOwnerId(OwnerID);

	}

	public double getLength() {
		return length;
	}

	public void setLength(double Length) {
		if (Length >= 1)
			length = Length;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type Type) {
		type = Type;
	}

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		if (ownerId > 0)
			this.ownerId = ownerId;
	}

	public int getId() {
		return id;
	}

	public void setId(int ID) {
		id = ID;
	}

	public void copyOf(Boat Boat) {
		setType(Boat.getType());
		setLength(Boat.getLength());
		setOwnerId(Boat.getOwnerId());
	}

}
