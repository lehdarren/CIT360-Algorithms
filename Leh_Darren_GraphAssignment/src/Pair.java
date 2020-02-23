
public class Pair {
	private int[] distanceArray;
	private int[] trailArray;
	
	public Pair(int[] distanceArray, int[] trailArray) {
		this.distanceArray = distanceArray;
		this.trailArray = trailArray;
	}
	
	public int[] getDistanceArray() {
		return distanceArray;
	}
	
	public int[] getTrailArray() {
		return trailArray;
	}
}
