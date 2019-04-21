package main;

import java.util.*;

public class Corporate {
	private static Corporate instance;
	
	private List<Paycheque> paycheques;
	private List<Paycheque> availcheques;
	
	private boolean holiday;
	
	public static Corporate getInstance() {
		if (instance == null)
			instance = new Corporate();
		return instance;
	}
	
	public Corporate() {
		holiday = false;
		availcheques = new LinkedList<Paycheque>();
		paycheques = new LinkedList<Paycheque>();
		for (int i = 0 ; i <= 10 ; i++) {
			paycheques.add(new Paycheque(i));
		}
	}
	
	public synchronized void addCheque(Paycheque pc) {
		System.out.println("adding cheque"+pc.getIndex());
		availcheques.add(pc);
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public synchronized void cashCheques() {
		availcheques = new LinkedList<Paycheque>();
		notifyAll();
	}
	
	public void run() {
		for (Paycheque p : paycheques) {
			p.startInstance();
		}

	}

	public boolean isHoliday() {
		return holiday;
	}

	public void setHoliday(boolean holiday) {
		this.holiday = holiday;
	}
	
	public List<Paycheque> getPayCheques() {
		return availcheques;
	}
}
