package main;

public class CustGen implements Runnable {
	private Shop s;
	private Thread t;
	
	public CustGen() {
		t = new Thread(this, "custGen");
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
					s.wake();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}		
	}
}
