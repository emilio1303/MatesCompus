//Emilio C. Alegría - A01631303
import java.util.Stack;

public class Infix {

	Validacion expresion = new Validacion();
	
	
	public String validacion(String ex) {
		if(this.expresion.validaExpresion(ex)) {
			return infixToPostfix(ex);
		}else {
			return "";
		}
	}
	
	
	public String infixToPostfix(String ex) {
		Stack<Character> pila= new Stack<Character>();
		String postfix="";
		char cadena [] = ex.toCharArray();
		
		for(int i=0; i<cadena.length;i++) {
			if(cadena[i]!='+' && cadena[i]!='*' && cadena[i]!='/' && cadena[i]!='(' && cadena[i]!=')' && cadena[i]!='-') {
				postfix=postfix+cadena[i]; //Agregar operandos a postfix
			}else if(cadena[i]=='(') {
				pila.push(cadena[i]);
			}else if(cadena[i]==')') {
				while(!pila.isEmpty()) {
					char pop = pila.pop();
					if(pop !='(') {
						postfix=postfix+pop;
					}else {
						break;
					}
				}
			}else if(cadena[i]=='+' || cadena[i]=='-' || cadena[i]=='*' || cadena[i]=='/' ) {
				if(pila.isEmpty()) {
					pila.push(cadena[i]);
				}else {
					while(!pila.isEmpty()) {
						char pop=pila.pop();
						if(pop=='(') {
							pila.push(pop);
							break;
						}else if(pop=='+' || pop=='-' || pop=='/' || pop=='*') {
							if(prioridad(pop) < prioridad(cadena[i])) {
								pila.push(pop);
								break;
							}else {
								postfix=postfix+pop;
							}
						}
					}
					pila.push(cadena[i]);
				}
			}
		}
		while(!pila.isEmpty()) {
			postfix=postfix + pila.pop();
		}
		
		return postfix;
	}
	
	
	public int prioridad(char c) {
		if(c == '+' || c =='-') {
			return 1;
		}else {
			return 2;
		}
	}
	
	
	
	public static void main(String[]args) {
		Infix a = new Infix();
		//String s1 = "2+3-1";
		//String s2 = "2+3*4";
		//String s3 = "3*(4+5)-6/(1+2)";
		//String s4 = "3+((5+9)*2)";
		//String s4 = "(3+4)*";
		
		
		
		//a.validacion(s1);
		//a.validacion(s2);
		//System.out.println(a.validacion(s3));
		//System.out.println(a.validacion(s4));
		//a.arregloCar(a.validacion(s3));
		
		
		//System.out.println(a.validacion(s4));
		
		
		
		
		
		
		/*System.out.println(a.infixToPostfix(s1));
		System.out.println(a.infixToPostfix(s2));
		System.out.println(a.infixToPostfix(s3));
		System.out.println(a.infixToPostfix(s4));*/
	}
}
