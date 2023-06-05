package com.mycompany.a4;
import java.util.ArrayList;

public class GameWorldCollection implements IICollection { //Iterator goes here (uses old arraylist tech from previous assignment
	
	private ArrayList<GameObject> gameObj;
	
	public GameWorldCollection() {
		gameObj = new ArrayList<GameObject>();
	}
	
	@Override
	public void add(GameObject object) {
		gameObj.add(object);

	}

	@Override
	public IIterator getIterator() {
		
		return new GameWorldItr();
	}
	
	private class GameWorldItr implements IIterator{
		private int index;
		
		public GameWorldItr() {
			index=-1;
		}
		
		@Override
		public boolean hasNext() {
			if(gameObj.size() <= 0)
				return false;
			if(index == gameObj.size() - 1)
			{
				return false;
			}
			return true;
		}

		@Override
		public GameObject getNext() {
			index++;
			return gameObj.get(index);
		}

	    @Override
		public void remove(GameObject gameO) {
			gameObj.remove(gameO);
		}

		@Override
		public GameObject getCurrent() {
			return gameObj.get(index);
		}
			
	}

}
