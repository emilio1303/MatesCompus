//Emilio C. Alegría - A01631303
import java.util.Stack;

public class Validacion {

	
	public boolean validaParentesis(String expresion) {
		char [] cadenas=expresion.toCharArray();
		
		
		if(cadenas.length==1) {
			return false;
		}
		
		Stack<Character> pila= new Stack<Character>();
		
		for(int i=0;i<cadenas.length;i++) {
			if(cadenas[i]=='['|| cadenas[i]=='(' || cadenas[i]=='{') {
				pila.push(cadenas[i]);
			}
			else if(cadenas[i]==']') {
				char pop = pila.peek();
				if(pop!='[') {
					return false;
				}else {
					for(int j=0;j<pila.size();j++) {
						if(pila.get(j)=='[') {
							pila.removeElement('[');
						}
					}
				}
			}else if(cadenas[i]==')') {
				char pop = pila.peek();
				if(pop!='(') {
					return false;
				}else {
					for(int j=0;j<pila.size();j++) {
						if(pila.get(j)=='(') {
							pila.removeElement('(');
						}
					}
				}
			}else if((cadenas[i]=='}')) {
				char pop = pila.peek();
				if(pop!='{') {
					return false;
				}else {
					for(int j=0;j<pila.size();j++) {
						if(pila.get(j)=='{') {
							pila.removeElement('{');
						}
					}
				}
			}
		}
		
		
		if(pila.isEmpty() && cadenas.length>0) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean validaSignos(String expresion) {
		char [] cadenas=expresion.toCharArray();
		char [] copia = new char [expresion.length()+2];
		
		for (int i=0; i<cadenas.length;i++) {
			copia[i+1]=cadenas[i];
		}
		
		if(cadenas[cadenas.length-1]=='+' || cadenas[cadenas.length-1]=='*' || cadenas[cadenas.length-1]=='/') {
			return false;
		}
		
		for(int i=0;i<cadenas.length;i++) {
			if(cadenas[i]=='+' | cadenas[i]=='*'|| cadenas[i]=='/') {
				if(copia[i+2]=='+' || copia[i]=='+' || copia[i+2]=='*' || copia[i]=='*' || copia[i+2]=='/' || copia[i]=='/' || copia[i+2]==')') {
					return false;
				}
			}
			
		}
		return true;
	}
	
	public boolean validaExpresion(String expresion) {
		if(validaSignos(expresion) && validaParentesis(expresion) && validaCar(expresion)) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean validaCar (String expresion) {
		char [] cadenas=expresion.toCharArray();
		char [] copia = new char [expresion.length()+2];
		
		for (int i=0; i<cadenas.length;i++) {
			copia[i+1]=cadenas[i];
		}
		
		for(int i=0;i<cadenas.length;i++) {
			if(Character.isLetterOrDigit(cadenas[i]) && Character.isLetterOrDigit(copia[i+2])) {
				return false;
			}
		}
		return true;
	}
	public static void main(String[] args) {
		Validacion expresion = new Validacion();
		//System.out.println(expresion.validaExpresion("a*b/(2-1)+5*(4-1)"));
		//System.out.println(expresion.validaSignos("a+b*c/d+b*d+d/h+"));
		//System.out.println(expresion.validaParentesis("(()]"));
		//System.out.println(expresion.validaExpresion("(a+b*)"));
		//System.out.println(expresion.validaExpresion("(a+b/4+a)"));
	}
}
