package edu.umb.cs681.hw18;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Observable<T> {
	private LinkedList<Observer<T>> observers = new LinkedList<>();
	private final ReentrantLock lockObs = new ReentrantLock();
	
	public void addObserver(Observer<T> o) {
		lockObs.lock();
        try {
            observers.add(o);
        } finally {
            lockObs.unlock();
        }
	}

	public void clearObservers() {
		lockObs.lock();
        try {
            observers.clear();
        } finally {
            lockObs.unlock();
        }
		
	}
	public List<Observer<T>> getObservers(){
		lockObs.lock();
        try {
            return observers;
        } finally {
            lockObs.unlock();
        }
	}
	
	public int countObservers() {
		lockObs.lock();
        try {
            return observers.size();
        } finally {
            lockObs.unlock();
        }
		
	}
	public void removeObserver(Observer<T> o) {
		lockObs.lock();
        try {
            observers.remove(o);
        } finally {
            lockObs.unlock();
        }
	}

	public void notifyObservers(T event) {
		observers.forEach( (observer)->{observer.update(this, event);} );
		
		List<Observer<T>> tempObservers;
        lockObs.lock();
        try {
        	tempObservers = new LinkedList<>(observers);
        } finally {
            lockObs.unlock();
        }

        for (Observer<T> observer : tempObservers) {
            observer.update(this, event);
        }
	}
	
}
