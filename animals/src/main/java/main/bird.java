package main;

public class bird extends animal {
	private String species;

	private int wingspan;

	private float weight;

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public int getWingspan() {
		return wingspan;
	}

	public void setWingspan(int wingspan) {
		this.wingspan = wingspan;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public boolean colourful() {
		if (sex) {
			if ((age > 0) & (age < 15)) {
				return true;
			} else {
				return false;
			}

		} else {
			return false;
		}
	}

	public bird(String species, String name, int wingspan, int age, float weight, boolean sex) {
		super(age, sex, name);
		this.species = species;
		this.name = name;
		this.wingspan = wingspan;
		this.age = age;
		this.weight = weight;
		this.sex = sex;
	}
}
