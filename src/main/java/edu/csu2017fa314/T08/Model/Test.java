
public class Test {
	public static void main(String[] args) {
		Model myModel =new Model();
		myModel.readFile();
		myModel.print();
		System.out.println(myModel.getID(0));
		System.out.println(myModel.getElevation(0));
	}

}
