//Emilio C. Alegría - A01631303

import java.util.Stack;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArbolExpansion<E extends Comparable<E>> {
 
   
	
	Infix operacion = new Infix();
	String res="";
	
 
   public boolean isOperator(char c) {
        if (c == '+' || c == '-'|| c == '*' || c == '/'|| c == '^') {
            return true;
        }
        return false;
    }
 

   public void inorder(Nodo<E> t) {
        if (t != null) {
            inorder(t.left);
            System.out.print(t.data + " ");
            inorder(t.right);
        }
    }
    
   public void printPostorder(Nodo<E> Nodo) 
    { 
        if (Nodo == null) 
            return; 
  
        printPostorder(Nodo.left); 
  

        printPostorder(Nodo.right); 
  

        System.out.print(Nodo.data + " "); 
    } 
 

   
   public Nodo<E> constructTree(char postfix[]) {
        Stack<Nodo<E>> st = new Stack<Nodo<E>>();
        Nodo<E> n, n1, n2;
 
        for (int i = 0; i < postfix.length; i++) {
            // operandos
            if (!isOperator(postfix[i])) {
                n = new Nodo(postfix[i]);
                st.push(n);
            } else {
            	//operador
                n = new Nodo(postfix[i]);
                n1 = st.pop();      
                n2 = st.pop();
                //  hijos
                n.right = n1;
                n.left = n2;
                st.push(n);
            }
        }
        n = st.peek();
        st.pop();
        return n;
    }
   
   public String convertir(String s) {
	   String res =operacion.validacion(s);
	   if(res=="") {
		   System.out.println("Expresión invalida");
		   System.exit(0);
	   }
	   return res;
   }
   
 
    public static void main(String args[]) {
 
        ArbolExpansion<String> ab = new ArbolExpansion<String>();
        String infijo = "(3-(2*x))+((x-2)+(3+x))";
        /*	3+4+c/6*
          	a*b/(2-1)+5*(4-1)
          	(3-(2*x))+((x-2)+(3+x))
			7*(8*9+(1+4)+4
         	*/
        char [] charArray= ab.convertir(infijo).toCharArray();
        Nodo<?> root = ab.constructTree(charArray);
        //System.out.println("infix expression is");
        //ab.inorder(root);
        //System.out.println();
        //ab.printPostorder(root);
        BTreePrinter.printNodo(root);
 
    }
}

class BTreePrinter {

    public static <T extends Comparable<?>> void printNodo(Nodo<T> root) {
        int maxLevel = BTreePrinter.maxLevel(root);

        printNodoInternal(Collections.singletonList(root), 1, maxLevel);
    }

    private static <T extends Comparable<?>> void printNodoInternal(List<Nodo<T>> Nodos, int level, int maxLevel) {
        if (Nodos.isEmpty() || BTreePrinter.isAllElementsNull(Nodos))
            return;

        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        BTreePrinter.printWhitespaces(firstSpaces);

        List<Nodo<T>> newNodos = new ArrayList<Nodo<T>>();
        for (Nodo<T> Nodo : Nodos) {
            if (Nodo != null) {
                System.out.print(Nodo.data);
                newNodos.add(Nodo.left);
                newNodos.add(Nodo.right);
            } else {
                newNodos.add(null);
                newNodos.add(null);
                System.out.print(" ");
            }

            BTreePrinter.printWhitespaces(betweenSpaces);
        }
        System.out.println("");

        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < Nodos.size(); j++) {
                BTreePrinter.printWhitespaces(firstSpaces - i);
                if (Nodos.get(j) == null) {
                    BTreePrinter.printWhitespaces(endgeLines + endgeLines + i + 1);
                    continue;
                }

                if (Nodos.get(j).left != null)
                    System.out.print("/");
                else
                    BTreePrinter.printWhitespaces(1);

                BTreePrinter.printWhitespaces(i + i - 1);

                if (Nodos.get(j).right != null)
                    System.out.print("\\");
                else
                    BTreePrinter.printWhitespaces(1);

                BTreePrinter.printWhitespaces(endgeLines + endgeLines - i);
            }

            System.out.println("");
        }

        printNodoInternal(newNodos, level + 1, maxLevel);
    }

    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }

    private static <T extends Comparable<?>> int maxLevel(Nodo<T> Nodo) {
        if (Nodo == null)
            return 0;

        return Math.max(BTreePrinter.maxLevel(Nodo.left), BTreePrinter.maxLevel(Nodo.right)) + 1;
    }

    private static <T> boolean isAllElementsNull(List<T> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }

        return true;
    }

}