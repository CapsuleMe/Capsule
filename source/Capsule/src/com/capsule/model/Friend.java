package com.capsule.model;

public class Friend extends User{

	public static final int VIP = 0;
	public static final int FRIEND = 1;
	
	private int ftype;
	private Location location;
	private int type;

	public int getFtype() {
		return ftype;
	}
	public void setFtype(int ftype) {
		this.ftype = ftype;
	}
	
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	
	
}
