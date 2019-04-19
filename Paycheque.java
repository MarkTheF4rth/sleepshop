package main;

public class Paycheque implements Runnable {
	private Thread t;
	private int index;

	private Corporate c;

	public Paycheque(int index) {
		this.index = index;
		t = new Thread(this, "paycheque" + index);
	}

	public void startInstance() {
		c = Corporate.getInstance();
		t.start();
	}

	public synchronized void wakeCheque() {
		notifyAll();
		System.out.println("waking" + index);
	}

	public synchronized void runLoop() {
		try {
			Thread.sleep((long) (Math.random() * 10000));
			System.out.println("sleeping" + index);
			if (c.addCheque(this) == 4) {
				c.setHoliday(true);
			}
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		while (true) {
			runLoop();
		}
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
