package domain;

public class Test {
	private String teststring;
	private int test1;
	private boolean testTrue;
	private int test2;
		
	public Test() {
		super();
	}

	public Test(String teststring, int test1, boolean testTrue, int test2) {
		super();
		this.teststring = teststring;
		this.test1 = test1;
		this.testTrue = testTrue;
		this.test2 = test2;
	}

	public String getTeststring() {
		return teststring;
	}

	public void setTeststring(String teststring) {
		this.teststring = teststring;
	}

	public int getTest1() {
		return test1;
	}

	public void setTest1(int test1) {
		this.test1 = test1;
	}

	public boolean isTestTrue() {
		return testTrue;
	}

	public void setTestTrue(boolean testTrue) {
		this.testTrue = testTrue;
	}

	public int getTest2() {
		return test2;
	}

	public void setTest2(int test2) {
		this.test2 = test2;
	}
	
}
