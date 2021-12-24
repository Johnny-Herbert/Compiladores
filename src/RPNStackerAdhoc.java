import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;
import java.util.HashMap;
import java.util.LinkedList;

public class RPNStackerAdhoc {

	public static void main(String[] args) throws FileNotFoundException  {
		// TODO Auto-generated method stub
		Stack<Double> pilha = new Stack(); 
		double num1 = 0, num2 = 0, result = 0;
		String caracter = "-";
		File file = new File("files/Calc4.stk");
		LinkedList<Token> tokens = null;
		HashMap<String, String> variables = new HashMap<String, String>();
		variables.put("x", "4");
		variables.put("y", "9");
			
		
		try {
			tokens = scanning(file, variables);
			for (int i = 0; i < tokens.size(); i++) {
				Token token = tokens.get(i);
				switch (token.lexeme) {
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
					if(isVariable(token.lexeme, variables)) {
						pilha.push(Double.parseDouble(variables.get(token.lexeme)));
					}
					else {
						pilha.push(Double.parseDouble(token.lexeme));
					}	
					break;
				}
			}

			System.out.println(pilha.pop());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	public static LinkedList<Token> scanning(File file, HashMap<String, String> variables) throws Exception {
		String caracter = "-";
		Scanner in = new Scanner(file);
		LinkedList<Token> tokens = new LinkedList<Token>();

		while (in.hasNext()) {
			TokenType tokenType = null;
			caracter = in.nextLine();
			switch (caracter) {
			case "+": {
				tokenType = TokenType.PLUS;
				break;
			}
			case "-": {
				tokenType = TokenType.MINUS;
				break;
			}
			case "*": {
				tokenType = TokenType.STAR;
				break;
			}
			case "/": {
				tokenType = TokenType.SLASH;
				break;
			}
			default:
				//verificando se é número
				if(caracter.matches("[+-]?\\d*(\\.\\d+)?")) {
					tokenType = TokenType.NUM;
				}
				else {
					
					if(!isVariable(caracter, variables)) {						
						throw new Exception("Error: Unexpected character: " + caracter);
					}
					else {
						tokenType = TokenType.ID;
					}
				}
				break;
			}
			Token token = new Token(tokenType, caracter);
			tokens.add(token);
		}

		in.close();
		return tokens;

	}
	
	public static boolean isVariable(String caracter, HashMap<String, String> variables) {
		for (String variable : variables.keySet()) {
			  if(variable.equals(caracter)) {
				  return true;
			  }
		}
		return false;
	}

}
