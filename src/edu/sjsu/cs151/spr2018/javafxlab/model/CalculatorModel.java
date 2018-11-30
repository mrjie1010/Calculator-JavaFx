package edu.sjsu.cs151.spr2018.javafxlab.model;
import java.util.ArrayList;
import java.util.Iterator;
import edu.sjsu.cs151.spr2018.javafxlab.model.CalculatorModel.ModelListener;

public class CalculatorModel {

	// TODO(you): Add a collection of ModelListeners
	ArrayList<ModelListener> listeners = new ArrayList<>();
	// state information
	boolean firstNumber = true;
	boolean hasOp = false;
	public static boolean equal = false;
	public static boolean second = false;
	public static int count = 0;

	private double op1 = -1;
	private double op2 = -1;
	private double result;
	private String operator;
	private String newOp;
	private String inputString = "";

	public boolean getOPorNot()
	{
		return hasOp;

	}

	public boolean getFirstOrNot()
	{
		return firstNumber;

	}

	public String getOperator()
	{

		return operator;
	}

	public double getop1()
	{
		return op1;

	}

	public double getop2()
	{
		return op2;

	}


	public String getequal()
	{
		return newOp;

	}





	// TODO(you): Create a ModelListener interface with just one function update()
	// and uncomment the following.
	// HINT: It would be useful for update to take a double as the parameter for update() 
	// so you can pass the result 



	public interface ModelListener{
		public void update(double result);

	}

	public void attach(ModelListener listener) {
		listeners.add(listener);
	} 


	private void updateAll() {
		//TODO(you): Iterate through the listener collection and call update on each of them.
		// Use the for each iteration. 
		Iterator<ModelListener> it = listeners.iterator();
		while(it.hasNext())
		{
			ModelListener ml = (ModelListener) it.next();
			ml.update(result);
		}

	}

	public void handleInput(String text) {
		switch(text) {
		case "+": 
		case "-": 
		case "/": 
		case "*": 
			this.operator = text;
			hasOp = true;
			firstNumber = false;
			inputString = "";
			newOp = text;
			break;
		case "=":
			if (hasOp) {
				equal = true;
				result = calculate();
				//System.out.println( "@:" + result);
				hasOp = false;
				firstNumber = true;
				second = true;
				//op1 = result;
				inputString = "";
				count++;
			}
			break;
		case "AC": 
		case "±": 
		case "%":
		case ".":
			throw new UnsupportedOperationException("Not implemented yet");

		default: 
			inputString = inputString+text;
			if (firstNumber) {
				this.op1 = Integer.parseInt(inputString);
				//System.out.println("String op1: " + op1) ;
			} else {
				this.op2 = Integer.parseInt(inputString);
				//System.out.println("String op2: " + op2) ;
			}
			result = Double.parseDouble(inputString);	
			//System.out.println("String result: " +result) ;
		}

		updateAll();
	}

	private double calculate() {
		double res = 0;
		// TODO(you): implement the switch case for calculation.
		switch(newOp)
		{
		case "+":
			res = op1 + op2;
			break;
		case "-":
			res = op1 - op2;
			break;
		case "*":
			res = op1 * op2;
			break;
		case "/":
			res = op1/op2;
			break;
		}
		op1 = res;
		op2= -1;
		return res;
	}


	public double getResult() {
		return result;
	}

}
