package com.capsule.android.cache;

import java.util.Collection;
import java.util.Iterator;
import java.util.Observable;

abstract class ObservableCollcetion<E> extends Observable implements Collection<E>{

	protected Collection<E> collection = null;
	
	protected abstract Collection<E> initCollection();

	public ObservableCollcetion()
	{
		this.collection = initCollection();
	}
	
	public boolean add(E obj) {
		// TODO Auto-generated method stub
		if(collection.add(obj))
		{
			this.notifyObservers();
			return true;
		}
		
		return false;
	}

	public boolean addAll(Collection<? extends E> coll) {
		// TODO Auto-generated method stub
		if(collection.addAll(coll))
		{
			this.notify();
			return true;
		}
		
		return false;
	}

	public void clear() {
		// TODO Auto-generated method stub
		collection.clear();
		this.notifyObservers();
	}

	public boolean contains(Object obj) {
		// TODO Auto-generated method stub
		return collection.contains(obj);
	}

	public boolean containsAll(Collection<?> coll) {
		// TODO Auto-generated method stub
		return collection.containsAll(coll);
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return collection.isEmpty();
	}

	public boolean remove(Object obj) {
		// TODO Auto-generated method stub
		if(collection.remove(obj)){
			this.notifyObservers();
			return true;
		}
		return false;
	}

	public boolean removeAll(Collection<?> coll) {
		// TODO Auto-generated method stub
		
		if(collection.removeAll(coll)){
			this.notifyObservers();
			return true;
		}
		return false;
	}

	public void update(E obj)
	{
		this.remove(obj);
		this.add(obj);
	}
	
	public void updateAll(Collection<E> coll)
	{
		collection = coll;
		this.notifyObservers();
	}
	
	public int size() {
		// TODO Auto-generated method stub
		return collection.size();
	}

	
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return collection.iterator();
	}

	public boolean retainAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return collection.retainAll(arg0);
	}

	public Object[] toArray() {
		// TODO Auto-generated method stub
		return collection.toArray();
	}

	public <T> T[] toArray(T[] array) {
		// TODO Auto-generated method stub
		return collection.toArray(array);
	}

}
