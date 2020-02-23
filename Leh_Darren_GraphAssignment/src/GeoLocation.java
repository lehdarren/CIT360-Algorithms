
public class GeoLocation {
	public double longitude;
	public double latitude;
	public String locationName;
	private double height;
	public int vertexNum;
	
	private double degreesToRadians(double degrees) {
		  return degrees * Math.PI / 180;
	}

	/**
	 * THIS IS SUPPOSED TO GIVE OUT THE CORRECT DISTANCE. I have now tried two different ways with two different ways
	 * to measure distance (metres and miles), and both of them still give out about a mile ahead of where it should.
	 * I do not understand why it is a mile ahead but it just is.
	 * @param lat1
	 * @param lon1
	 * @return
	 */
	public double distanceInMilesBetweenEarthCoordinates(double lat1, double lon1) {
		/*
		double earthRadiusMiles = 6371; //kilometers

		  double dLat = degreesToRadians(this.latitude - lat1);
		  double dLon = degreesToRadians(this.longitude - lon1);

		  lat1 = degreesToRadians(lat1);
		  double lat2 = degreesToRadians(this.latitude);

		  double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
		          Math.sin(dLon/2) * Math.sin(dLon/2) * Math.cos(lat1) * Math.cos(lat2); 
		  double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
		  return earthRadiusMiles * c;
		*/
		
		double R = 3958.8; // miles
		double φ1 = degreesToRadians(lat1);
		double φ2 = degreesToRadians(this.latitude);
		double Δφ = degreesToRadians(this.latitude-lat1);
		double Δλ = degreesToRadians(this.longitude-lon1);

		double a = Math.sin(Δφ/2) * Math.sin(Δφ/2) +
		        Math.cos(φ1) * Math.cos(φ2) *
		        Math.sin(Δλ/2) * Math.sin(Δλ/2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

		return R * c;
	}
	
	public GeoLocation(int vNum, double lat, double Long, double height, String locName) {
		this.vertexNum = vNum;
		this.latitude = lat;
		this.longitude = Long;
		this.height = height;
		this.locationName = locName;
	}
	
	
	
}
