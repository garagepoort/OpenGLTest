package be.davidcorp.transfo;

import java.util.Stack;

public class TransfoStack {

	private Stack<Transfo> stack;
	
	public TransfoStack(){
		stack = new Stack<Transfo>();
		stack.push(new Transfo());
	}
	
	public Transfo peek(){
		return stack.peek();
	}
	
	public void push(){
		Transfo f = new Transfo(peek());
		stack.push(f);
	}
	
	public void pop(){
		stack.pop();
	}
	
	public void transform(Transfo f){
		peek().transform(f);
	}
	
}
