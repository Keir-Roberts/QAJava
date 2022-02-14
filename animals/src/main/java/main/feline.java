package main;

public class feline extends animal {
	private String breed;
	private float weight;

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public String chonkvalue() {
		if (weight <= 0) {
			return "Your cat doesn't exist";
		} else if ((weight > 0) & (weight < 2)) {
			return "Your cat is just a little babbus";
		} else if (weight < 4) {
			return "Your cat is a smol fren";
		} else if (weight < 5) {
			return "Average in weight, special in heart";
		} else if (weight < 6) {
			return "A little bit chonky";
		} else if (weight < 7) {
			return "A big chonker";
		} else {
			return "Oh lawd thats a big boi";
		}
	}

	public feline(String breed, String name, int age, float weight, boolean sex) {
		super(age, sex, name);
		this.breed = breed;
		this.name = name;
		this.age = age;
		this.weight = weight;
	}

}
