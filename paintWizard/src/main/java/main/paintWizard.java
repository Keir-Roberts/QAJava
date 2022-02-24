package main;
import java.util.Scanner;
public class paintWizard {
	static Scanner scan = new Scanner(System.in);
	
	public static void roomCheckWaste() {
		System.out.println("What is the area of the room you want to paint?");
		int roomsize = Integer.parseInt(scan.nextLine());
		int minWaste = 999999999;
		int cans = 0;
		String bestPaint = "error";
		for (Object paint: paints.paintList) {
			int waste = (roomsize%((paints) paint).getTotalArea());
		if (waste < minWaste) {
			minWaste = waste;
			bestPaint = ((paints) paint).getName();
			cans = (int) Math.ceil(roomsize/((double)((paints) paint).getTotalArea()));
		}
		}
		System.out.println("The Paint with the least waste is " + bestPaint 
				+ " With only " + minWaste + " metres squared worth of paint wasted from " + cans + " cans.");
	}
	public static void roomCheckPrice() {
		System.out.println("What is the area of the room you want to paint?");
		int roomsize = Integer.parseInt(scan.nextLine());
		int cans = 0;
		int bestCans = 0;
		String BestName = null;
		double bestCost = 9999999;
		for (Object paint: paints.paintList) {
			cans = (int) Math.ceil(roomsize/((double)((paints) paint).getTotalArea()));
			double cost = (Math.rint(100*(((paints) paint).getCost())*((double) cans)))/100;
			if (cost < bestCost) {
				bestCost = cost;
				BestName = ((paints) paint).getName();
				bestCans = cans;
			} else if(cost == bestCost) {
				BestName = BestName + " or " + ((paints) paint).getName();
			}
		}
		System.out.println("The cheapest paint is " + BestName + " which would cost a total of £" + bestCost + " using " + bestCans + " cans.");
	}
	public static void main(String[] args) {
paints CheapoMax = new paints("CheapoMax", 21, 19.99, 10);
CheapoMax.addPaint();
paints ThatOneWithTheDog = new paints("ThatOneWithTheDog", 40, 34.38, 12);
ThatOneWithTheDog.addPaint();
paints AverageJoes = new paints("AverageJoes", 16, 17.99, 11);
AverageJoes.addPaint();
paints DuluxourousPaints = new paints("DuluxourousPaints", 10, 25, 22);
DuluxourousPaints.addPaint();
roomCheckWaste();
roomCheckPrice();
}
}
