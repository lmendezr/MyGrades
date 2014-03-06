package edu.uprm.ece.icom4035.mygrades.managers;

public interface ReverseIterator<E> {
	
	public boolean hasPrev();
	
	public E prev();
	
	public void remove();

}
