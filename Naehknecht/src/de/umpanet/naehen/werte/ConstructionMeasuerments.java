package de.umpanet.naehen.werte;

import java.util.HashMap;

public class ConstructionMeasuerments {
	
	double unit =Math.round(10.0* (72 / 2.54f) / 4)/10.0 ; //cm 50%
	/*double bodyHeight;
	double bustChestGirth;
	double underbustCircumferenceH;
	double waistGirth;
	double buttockGirth;
	
	double sideWaistHeight;
	double waistToButtockHeight;
	
	double neckAtBaseGirth;
	double neckHoleDepth;
	
	double armholeDepth;
	double neckToWaistCenterBack;
	double bustPointToNeck;
	double bustDepth;
	double frontHeight;
	double frontHeight2;
	
	double acrossBackWidth;
	double ArmDiameter;
	double bustWidth;
	
	double shoulderWidth;
	double armLength;
	double upperArmDiameter;
	double wristGirth;
	
	double sittingHeight;
	double thighGirth;
	double kneeGirth;
	double underKneeGirth;
	double calfGirth;
	double ankleGirth;
	double instepGirth;*/
	
	HashMap<String, Double> measurements;

	public ConstructionMeasuerments() {
		
		if(this.measurements == null){
		//initalize with EU 38 C cup
		measurements = new HashMap<String, Double>();
		measurements.put("bodyHeight", 168.0);
		measurements.put("bustChestGirth", 88.0);
		measurements.put("underbustCircumferenceH", 71.0);
		measurements.put("waistGirth", 72.0);
		measurements.put("buttockGirth", 97.0);
		
		measurements.put("sideWaistHeight", 106.0);
		measurements.put("waistToButtockHeight", 20.6);

		measurements.put("neckAtBaseGirth", 36.0);
		measurements.put("neckHoleDepth", 7.0);
		
		measurements.put("armholeDepth", 20.1);
		measurements.put("neckToWaistCenterBack", 41.6);
		measurements.put("bustDepth", 28.1);
		measurements.put("frontHeight", 45.3);

		measurements.put("acrossBackWidth", 16.5);
		measurements.put("ArmDiameter", 9.3);
		measurements.put("bustWidth", 18.2);
		
		measurements.put("shoulderWidth", 12.2);
		measurements.put("armLength", 59.9);
		measurements.put("upperArmDiameter", 28.0);
		measurements.put("wristGirth", 15.8);
		
		
		measurements.put("sittingHeight", 26.1);
		measurements.put("ankleGirth", 24.5);
		}
	
		
	}


	public HashMap<String,Double> getAll(){
		return this.measurements;
	}

	public double getBodyHeight() {
		
		return round(measurements.get("bodyHeight") *unit);
	}




	public double getBustChestGirth() {
		
		return  round(measurements.get("bustChestGirth")*unit);
	}




	public double getUnderbustCircumferenceH() {
		return  round(measurements.get("underbustCircumferenceH")*unit);
	}




	public double getWaistGirth() {
		return  round(measurements.get("waistGirth")*unit);
	}




	public double getButtockGirth() {
		return  round(measurements.get("buttockGirth")*unit);
	}




	public double getSideWaistHeight() {
		return  round(measurements.get("sideWaistHeight")*unit);
	}




	public double getWaistToButtockHeight() {
		return  round(measurements.get("waistToButtockHeight")*unit);
	}




	public double getNeckAtBaseGirth() {
		return  round(measurements.get("neckAtBaseGirth")*unit);
	}




	public double getNeckHoleDepth() {
		return  round(measurements.get("neckHoleDepth")*unit);
	}




	public double getArmholeDepth() {
		return  round(measurements.get("armholeDepth")*unit);
	}




	public double getNeckToWaistCenterBack() {
		return  round(measurements.get("neckToWaistCenterBack")*unit);
	}




	public double getBustPointToNeck() {
		return  round(measurements.get("bustPointToNeck")*unit);
	}




	public double getBustDepth() {
		return  round(measurements.get("bustDepth")*unit);
	}




	public double getFrontHeight() {
		return  round(measurements.get("frontHeight")*unit);
	}






	public double getAcrossBackWidth() {
		return  round(measurements.get("acrossBackWidth")*unit);
	}




	public double getArmDiameter() {
		return round(measurements.get("ArmDiameter")*unit);
	}




	public double getBustWidth() {
		return round(measurements.get("bustWidth")*unit);
	}


	public double getBustPointWidth(){
		Double bpw = this.measurements.get("bustPointWidth");
		if(bpw == null){
			bpw = this.measurements.get("bustChestGirth") / 10;
			this.measurements.put("bustPointWidth", bpw);
		}
		return bpw * unit;
	}

	public double getShoulderWidth() {
		return round(measurements.get("shoulderWidth")*unit);
	}




	public double getArmLength() {
		return round(measurements.get("armLength")*unit);
	}




	public double getUpperArmDiameter() {
		return round(measurements.get("upperArmDiameter")*unit);
	}




	public double getWristGirth() {
		return round(measurements.get("wristGirth")*unit);
	}




	public double getSittingHeight() {
		return round(measurements.get("sittingHeight")*unit);
	}




	public double getThighGirth() {
		return round(measurements.get("thighGirth")*unit);
	}




	public double getKneeGirth() {
		return round(measurements.get("kneeGirth")*unit);
	}




	public double getUnderKneeGirth() {
		return round(measurements.get("underKneeGirth")*unit);
	}




	public double getCalfGirth() {
		return round(measurements.get("calfGirth")*unit);
	}




	public double getAnkleGirth() {
		return round(measurements.get("ankleGirth")*unit);
	}




	public double getInstepGirth() {
		return round(measurements.get("instepGirth")*unit);
	}




	public void setBodyHeight(double bodyHeight) {
		this.measurements.put("bodyHeight", bodyHeight/unit);
	}




	public void setBustChestGirth(double bustChestGirth) {
		this.measurements.put("bustChestGirth",bustChestGirth/unit);
	}




	public void setUnderbustCircumferenceH(double underbustCircumferenceH) {
		this.measurements.put("underbustCircumferenceH", underbustCircumferenceH/unit);
	}




	public void setWaistGirth(double waistGirth) {
		this.measurements.put("waistGirth", waistGirth/unit);
	}




	public void setButtockGirth(double buttockGirth) {
		this.measurements.put("buttockGirth", buttockGirth/unit);
	}




	public void setSideWaistHeight(double sideWaistHeight) {
		this.measurements.put("sideWaistHeight", sideWaistHeight/unit);
	}




	public void setWaistToButtockHeight(double waistToButtockHeight) {
		this.measurements.put("waistToButtockHeight", waistToButtockHeight/unit);
	}




	public void setNeckAtBaseGirth(double neckAtBaseGirth) {
		this.measurements.put("neckAtBaseGirth", neckAtBaseGirth/unit);
	}




	public void setNeckHoleDepth(double neckHoleDepth) {
		this.measurements.put("neckHoleDepth", neckHoleDepth/unit);
	}




	public void setArmholeDepth(double armholeDepth) {
		this.measurements.put("armholeDepth", armholeDepth/unit);
	}




	public void setNeckToWaistCenterBack(double neckToWaistCenterBack) {
		this.measurements.put("neckToWaistCenterBack", neckToWaistCenterBack/unit);
	}




	public void setBustPointToNeck(double bustPointToNeck) {
		this.measurements.put("bustPointToNeck", bustPointToNeck/unit);
	}




	public void setBustDepth(double bustDepth) {
		this.measurements.put("bustDepth", bustDepth/unit);
	}




	public void setFrontHeight(double frontHeight) {
		this.measurements.put("frontHeight", frontHeight/unit);
	}






	public void setAcrossBackWidth(double acrossBackWidth) {
		this.measurements.put("acrossBackWidth", acrossBackWidth/unit);
	}




	public void setArmDiameter(double armDiameter) {
		measurements.put("ArmDiameter", armDiameter/unit);
	}




	public void setBustWidth(double bustWidth) {
		this.measurements.put("bustWidth", bustWidth/unit);
		
	}




	public void setShoulderWidth(double shoulderWidth) {
		this.measurements.put("shoulderWidth", shoulderWidth/unit);
	}




	public void setArmLength(double armLength) {
		this.measurements.put("armLength", armLength/unit);
	}




	public void setUpperArmDiameter(double upperArmDiameter) {
		this.measurements.put("upperArmDiameter", upperArmDiameter/unit);
	}




	public void setWristGirth(double wristGirth) {
		this.measurements.put("wristGirth", wristGirth/unit);
	}




	public void setSittingHeight(double sittingHeight) {
		this.measurements.put("sittingHeight", sittingHeight/unit);
	}




	public void setThighGirth(double thighGirth) {
		this.measurements.put("thighGirth", thighGirth/unit);
	}




	public void setKneeGirth(double kneeGirth) {
		this.measurements.put("kneeGirth", kneeGirth/unit);
	}




	public void setUnderKneeGirth(double underKneeGirth) {
		this.measurements.put("underKneeGirth", underKneeGirth/unit);
	}




	public void setCalfGirth(double calfGirth) {
		this.measurements.put("calfGirth", calfGirth/unit);
	}




	public void setAnkleGirth(double ankleGirth) {
		this.measurements.put("ankleGirth", ankleGirth/unit);
	}




	public void setInstepGirth(double instepGirth) {
		this.measurements.put("instepGirth", instepGirth/unit);
	}

	public void setUnit(float unit){
		this.unit=unit;
	}
	
	
	private double round(double a){
		return Math.round(10.0 *a)/10.0;
	}
}
