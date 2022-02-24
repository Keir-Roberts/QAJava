package main;

import java.util.ArrayList;
import java.util.List;

public class goldiCheck {
public static String check(List<String> parameters) {
	List<String> parameters2 = new ArrayList<String>();
	List<Integer> goodChairs = new ArrayList<Integer>();
	for (String values: parameters) {
		parameters2.add(values);
	}
	String Goldi = parameters2.get(0);
	parameters2.remove(0);
	int GoldiWeight = Integer.parseInt(Goldi.split(" ")[0]);
	int GoldiTemp = Integer.parseInt(Goldi.split(" ")[1]);
	int i = 0;
	Integer[][] place = new Integer[3][parameters2.size()];
	for(String value: parameters2) {
		porridge tempporridge = new porridge(value.split(" ")[1]);
		chair tempchair = new chair(value.split(" ")[0]);
		int luminosity = Integer.parseInt(value.split(" ")[2]);
		place[1][i] = tempporridge.getTemp();
		place[0][i] = tempchair.getWeight();
		place[2][i] = luminosity;
		i++;
	}
	for (int j = 0; j<i; j++) {
		if ((place[0][j] >= GoldiWeight) &&
				(place[1][j] <= GoldiTemp) && (place[2][j] >= 70)) {
			goodChairs.add(j + 1);
		}
	}
	if (goodChairs.size() == 0) return ("There are no suitable chairs"); 
	else if (goodChairs.size() == 1) return ("The only suitable chair is " + goodChairs);
	else return ("The suitable chairs are " + goodChairs);
}
}
