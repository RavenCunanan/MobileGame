package com.mycompany.a4;

public interface IIterator {

		public boolean hasNext();
		
		public GameObject getNext();

    	void remove(GameObject gameO);
    	
    	public GameObject getCurrent();
		
		
}
