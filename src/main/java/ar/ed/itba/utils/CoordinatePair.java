package ar.ed.itba.utils;

import java.util.Objects;

public class CoordinatePair {
	final int x;
	final int y;
	
	public CoordinatePair(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CoordinatePair coordinatePair = (CoordinatePair) o;
		return x == coordinatePair.x &&
			y == coordinatePair.y;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		
		return y;
	}
	
	public CoordinatePair reverse(){
	  return new CoordinatePair(y,x);
  }
}
