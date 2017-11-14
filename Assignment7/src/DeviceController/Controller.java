package DeviceController;

public class Controller extends Thread {
	private Device device;
	private Sensor heat;
	private Sensor pressure;

	public Controller(Device device, Sensor heat, Sensor pressure) {
		this.device = device;
		this.heat = heat;
		this.pressure = pressure;
	}

	@Override
	public void run() {
		device.startup();
		synchronized (device) {
			while (heat.getValue() <= 70 && pressure.getValue() <= 100) {
				System.out.println(String.format("heat -> %.2f, pressure -> %.2f", heat.getValue(), pressure.getValue()));
				try {
					device.wait();
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
			System.out.println(String.format("heat -> %.2f, pressure -> %.2f", heat.getValue(), pressure.getValue()));
		}
		device.shutdown();
		System.exit(0);
	}
	
}
