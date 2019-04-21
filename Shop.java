package main;

import java.util.LinkedList;
import java.util.Queue;

import main.*;

public class Shop {
	private Queue<Customer> custQueue;
	private static Shop instance;
	private Corporate c;

	private boolean tooLoud;

	public static Shop getInstance() {
		if (instance == null)
			instance = new Shop();
		return instance;
	}

	public Shop() {
		custQueue = new LinkedList<>();
		tooLoud = false;
		c = Corporate.getInstance();
	}

	public synchronized void wait(Manager manager) {
		while (!tooLoud) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public synchronized void wake() {
		notifyAll();
	}

	public int generateCustomer() {
		custQueue.add(new Customer());
		System.out.println("generating customer " + custQueue.size() + " in queue\n");
		return custQueue.size();
	}

	public int satisfyCustomer() {
		custQueue.remove();
		System.out.println("satisfying customer " + custQueue.size() + " in queue\n");
		return custQueue.size();
	}

	public boolean isTooLoud() {
		return tooLoud;
	}

	public void setTooLoud(boolean tooLoud) {
		this.tooLoud = tooLoud;
	}

	public void run() {
		Manager manager = new Manager();
		CustGen custgen = new CustGen();
	}

}
