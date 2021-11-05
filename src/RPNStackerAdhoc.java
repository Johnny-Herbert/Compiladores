import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class RPNStackerAdhoc {

	public static void main(String[] args) throws FileNotFoundException  {
		// TODO Auto-generated method stub
		Stack pilha = new Stack(); 
		double num1 = 0, num2 = 0, result = 0;
		String caracter = "-";
		File file = new File("files/Calc1.stk");
        Scanner in = new Scanner(file);
        
        while (in.hasNext()) {
            caracter = in.nextLine();

			switch (caracter) {
			case "+": {
				num1 = (double)pilha.pop();
				num2 = (double)pilha.pop();
				result = num1 + num2;
				pilha.push(result);
				break;
			}
			case "-": {
				num1 = (double)pilha.pop();
				num2 = (double)pilha.pop();
				result = num2 - num1;
				pilha.push(result);
				break;
			}
			case "*": {
				num1 = (double)pilha.pop();
				num2 = (double)pilha.pop();
				result = num1 * num2;
				pilha.push(result);
				break;
			}
			case "/": {
				num1 = (double)pilha.pop();
				num2 = (double)pilha.pop();
				result = num2 / num1;
				pilha.push(result);
				break;
			}
			default:
				pilha.push(Double.parseDouble(caracter));
				break;
			}
		}
	
        in.close();
        
		System.out.println(pilha.pop());
	}

}
