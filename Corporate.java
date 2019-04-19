package main;

import java.util.*;

public class Corporate {
	private static Corporate instance;
	
	private Paycheque pay1;
	private Paycheque pay2;
	private Paycheque pay3;
	private Paycheque pay4;
	
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
		pay1 = new Paycheque(1);
		pay2 = new Paycheque(2);
		pay3 = new Paycheque(3);
		pay4 = new Paycheque(4);
	}
	
	public int addCheque(Paycheque pc) {
		System.out.println("adding cheque"+pc.getIndex());
		availcheques.add(pc);
		return availcheques.size();
	}
	
	public void cashCheques() {
		availcheques = new LinkedList<Paycheque>();
		pay1.wakeCheque();
		pay2.wakeCheque();
		pay3.wakeCheque();
		pay4.wakeCheque();
	}
	
	public void run() {
		pay1.startInstance();
		pay2.startInstance();
		pay3.startInstance();
		pay4.startInstance();
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
