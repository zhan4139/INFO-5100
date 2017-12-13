
public class ReverseHello extends Thread { // score 2
	private int threadNum = 0;
	public ReverseHello() {}
	public ReverseHello(int num) {
		this.threadNum = num;
	}
	
	@Override
	public void run() {
		if (threadNum < 50) {
			threadNum++;
			Thread td = new ReverseHello(threadNum);
			System.out.println("Hello from thread " + threadNum + "!");
			td.start();
			try {
				td.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		Thread t = new ReverseHello();
		t.start();
	}
}
