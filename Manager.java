package main;

public class Manager implements Runnable {
	private static Manager instance;
	private Thread t;
	private Shop s;
	private Corporate c;

	public static Manager getInstance() {
		if (instance == null)
			instance = new Manager();
		return instance;
	}

	public Manager() {
		t = new Thread(this, "manager");
		t.start();
	}

	public synchronized void runLoop() {
		try {
			if (c.isHoliday()) {
				System.out.println("Going on holiday!");
				Thread.sleep(10000);
				c.setHoliday(false);
				c.cashCheques();

				System.out.println("Returning from holiday!");
			}
			if (s.isTooLoud()) {
				if (s.satisfyCustomer() == 0) {
					s.setTooLoud(false);
				}
				Thread.sleep(1000);
			}

		} catch (InterruptedException e) {
		}
		s.wait(this);

	}

	@Override
	public void run() {
		this.s = Shop.getInstance();
		this.c = Corporate.getInstance();
		while (true) {
			runLoop();
		}
	}
}
