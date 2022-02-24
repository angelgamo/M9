

public class PerformerV2 implements Runnable {
	Object trafficLight;
	int channel;
	Note[] musicSheet;

	public PerformerV2(int channel, Note[] musicSheet, Object trafficLight) {
		super();
		this.channel = channel;
		this.musicSheet = musicSheet;
		this.trafficLight = trafficLight;
	}

	@Override
	public void run() {
		try {
			synchronized (trafficLight) {
				trafficLight.wait();
			}

			for (Note note : musicSheet) {
				MidiPlayer.play(channel, note);
				for (int i = 0; i < note.getDuration(); i++) {
					synchronized (trafficLight) {
						trafficLight.wait();
					}
				}
				MidiPlayer.stop(channel, note);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("finished " + Thread.currentThread().getName());
	}

}
