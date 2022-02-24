
public class Performer implements Runnable {
	int channel;
	long tempo;
	Note[] musicSheet;

	public Performer(int channel, double tempo, Note[] musicSheet) {
		super();
		this.channel = channel;
		this.tempo = (long)(((60 / tempo) / 16) * 1000);
		this.musicSheet = musicSheet;
	}

	@Override
	public void run() {
		try {
			for (Note note : musicSheet) {
				MidiPlayer.play(channel, note);
				Thread.sleep(tempo * note.getDuration());
				MidiPlayer.stop(channel, note);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
