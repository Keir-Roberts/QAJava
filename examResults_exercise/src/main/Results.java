package main;

public class Results {
	private int physResult;
	private int chemResult;
	private int bioResult;
	private double physPercent;
	private double chemPercent;
	private double bioPercent;
	private double total;
	private double percent;
	private double passmark = 60;

	public boolean youPass(double percent) {
		if (percent >= passmark) {
			return true;
		} else {
			return false;
		}
	}

	public Results(int physResult, int chemResult, int bioResult) {
		this.physResult = physResult;
		this.chemResult = chemResult;
		this.bioResult = bioResult;
		this.physPercent = 100 * (physResult / 150);
		this.bioPercent = 100 * (bioResult / 150);
		this.chemPercent = 100 * (chemResult / 150);
		this.total = physResult + chemResult + bioResult;
		this.percent = 100 * (total / 450);

	}
	
	public int fails() {
		int n = 0;
		if (!this.youPass(bioPercent)) {
			n++;
		}
		if (!this.youPass(chemPercent)) {
			n++;
		}
		if (!this.youPass(physPercent)) {
			n++;
		}
		return n;
	}

	public void setPhys(int physResult) {
		this.physResult = physResult;
	}

	public void setChem(int chemResult) {
		this.chemResult = chemResult;
	}

	public void setBio(int bioResult) {
		this.bioResult = bioResult;
	}

	public int getPhys() {
		return physResult;
	}

	public int getChem() {
		return chemResult;
	}

	public int getBio() {
		return bioResult;

	}

	public void tellResults() {
		System.out.println("You got " + physResult + " marks in Physics.");
		System.out.println("You got " + chemResult + " marks in Chemistry.");
		System.out.println("You got " + bioResult + " marks in Biology.");
		System.out.println("So You got " + total + " marks in total.");
		if (percent >= passmark) {
			System.out.println("You Passed!");
		} else {
			System.out.println("You failed.");
		}
	}

	public void tellPercent() {
		System.out.println("You recieved a mark of " + total + "/450: which is a percentage of " + percent + "%");
		if(this.fails() == 0) {
			System.out.println("You passed!");
		} else {
			System.out.println("You failed " + this.fails() + " subjects.");
		}
			

}

}


