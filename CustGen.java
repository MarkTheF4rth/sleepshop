package main;

public class CustGen implements Runnable {
	private Shop s;
	private Thread t;
	private Manager manager;
	
	public CustGen() {
		t = new Thread(this, "custGen");
		this.manager = Manager.getInstance();
		t.start();
	}

	@Override
	public void run() {
		this.s = Shop.getInstance();
		while (true) {
			try {
				Thread.sleep((long) ((Math.random())*3000));
				if (s.generateCustomer() == 3) {
					s.setTooLoud(true);
					manager.wakeManager();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}		
	}
}
