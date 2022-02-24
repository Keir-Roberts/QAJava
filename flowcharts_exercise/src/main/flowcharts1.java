package main;

public class flowcharts1 {
	public static boolean isEven(int num) {
		return ((num % 2) == 0);
	}
	public static int booMaths(int num1, int num2, boolean plusTimes) {
		if ((isEven(num1) & !isEven(num2)) || (!isEven(num1) && isEven(num2)))
			return 0;
		if (plusTimes) {
			int booMathsOutput = num1 + num2;
			return booMathsOutput;
		} else {
			int booMathsOutput = num1 * num2;
			return booMathsOutput;
		}

	}

	
}
