package main;
import java.util.ArrayList;
import java.util.List;
public class paints {
	public static List<Object> paintList = new ArrayList<Object>();
	private String name;
	private int volume;
	private double cost;
	private int areaPerLitre;
	public void addPaint() {
		paintList.add(this);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public int getAreaPerLitre() {
		return areaPerLitre;
	}
	public void setAreaPerLitre(int areaPerLitre) {
		this.areaPerLitre = areaPerLitre;
	}
	private int totalArea = areaPerLitre * volume;
	
	public int getTotalArea() {
		return totalArea;
	}
	public paints(String name, int volume, double cost, int areaPerLitre) {
		super();
		this.name = name;
		this.volume = volume;
		this.cost = cost;
		this.areaPerLitre = areaPerLitre;
		this.totalArea = volume * areaPerLitre;
	}
	}
