import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import javax.sound.midi.Instrument;

public class Main {
	final static Note[] notesLead = 
		{
			new Note(Note.Frequency.C4, Note.Duration.quarter),
			new Note(Note.Frequency.D4, Note.Duration.quarter),
			new Note(Note.Frequency.E4, Note.Duration.quarter),
			new Note(Note.Frequency.G4, Note.Duration.quarter),
			new Note(Note.Frequency.C5, Note.Duration.quarter),
			new Note(Note.Frequency.D5, Note.Duration.quarter),
			new Note(Note.Frequency.E5, Note.Duration.quarter),
			new Note(Note.Frequency.G5, Note.Duration.quarter),
			new Note(Note.Frequency.C6, Note.Duration.quarter),
			new Note(Note.Frequency.G5, Note.Duration.quarter),
			new Note(Note.Frequency.E5, Note.Duration.quarter),
			new Note(Note.Frequency.D5, Note.Duration.quarter),
			new Note(Note.Frequency.C5, Note.Duration.quarter),
			new Note(Note.Frequency.G4, Note.Duration.quarter),
			new Note(Note.Frequency.E4, Note.Duration.quarter),
			new Note(Note.Frequency.D4, Note.Duration.quarter),
			
			new Note(Note.Frequency.A3, Note.Duration.quarter),
			new Note(Note.Frequency.B3, Note.Duration.quarter),
			new Note(Note.Frequency.C4, Note.Duration.quarter),
			new Note(Note.Frequency.E4, Note.Duration.quarter),
			new Note(Note.Frequency.A4, Note.Duration.quarter),
			new Note(Note.Frequency.B4, Note.Duration.quarter),
			new Note(Note.Frequency.C5, Note.Duration.quarter),
			new Note(Note.Frequency.E5, Note.Duration.quarter),
			new Note(Note.Frequency.A5, Note.Duration.quarter),
			new Note(Note.Frequency.E5, Note.Duration.quarter),
			new Note(Note.Frequency.C5, Note.Duration.quarter),
			new Note(Note.Frequency.B4, Note.Duration.quarter),
			new Note(Note.Frequency.A4, Note.Duration.quarter),
			new Note(Note.Frequency.E4, Note.Duration.quarter),
			new Note(Note.Frequency.C4, Note.Duration.quarter),
			new Note(Note.Frequency.B3, Note.Duration.quarter)
		};
	
	final static Note[] notesRythm =
		{
			new Note(Note.Frequency.G5, Note.Duration.quarter),
			new Note(Note.Frequency.A5, Note.Duration.quarter),
			new Note(Note.Frequency.B5, Note.Duration.quarter),
			new Note(Note.Frequency.D5, Note.Duration.quarter),
			new Note(Note.Frequency.G6, Note.Duration.quarter),
			new Note(Note.Frequency.A6, Note.Duration.quarter),
			new Note(Note.Frequency.B6, Note.Duration.quarter),
			new Note(Note.Frequency.D6, Note.Duration.quarter),
			new Note(Note.Frequency.C7, Note.Duration.quarter),
			new Note(Note.Frequency.G6, Note.Duration.quarter),
			new Note(Note.Frequency.E6, Note.Duration.quarter),
			new Note(Note.Frequency.D6, Note.Duration.quarter),
			new Note(Note.Frequency.C6, Note.Duration.quarter),
			new Note(Note.Frequency.G5, Note.Duration.quarter),
			new Note(Note.Frequency.E5, Note.Duration.quarter),
			new Note(Note.Frequency.D5, Note.Duration.quarter),
			
			new Note(Note.Frequency.A4, Note.Duration.quarter),
			new Note(Note.Frequency.B4, Note.Duration.quarter),
			new Note(Note.Frequency.C5, Note.Duration.quarter),
			new Note(Note.Frequency.E5, Note.Duration.quarter),
			new Note(Note.Frequency.A5, Note.Duration.quarter),
			new Note(Note.Frequency.B5, Note.Duration.quarter),
			new Note(Note.Frequency.C6, Note.Duration.quarter),
			new Note(Note.Frequency.E6, Note.Duration.quarter),
			new Note(Note.Frequency.A6, Note.Duration.quarter),
			new Note(Note.Frequency.E6, Note.Duration.quarter),
			new Note(Note.Frequency.C6, Note.Duration.quarter),
			new Note(Note.Frequency.B5, Note.Duration.quarter),
			new Note(Note.Frequency.A5, Note.Duration.quarter),
			new Note(Note.Frequency.E5, Note.Duration.quarter),
			new Note(Note.Frequency.C5, Note.Duration.quarter),
			new Note(Note.Frequency.B4, Note.Duration.quarter)
		};
	
	public static void main(String[] args) {
		int bpm = 60;
		
		//Act1(bpm);
		Act2(bpm);
	}
	
	public static void Act1(int bpm) {
		ExecutorService executor = Executors.newFixedThreadPool(1);
		executor.execute(new Performer(0, bpm, notesLead));
		executor.shutdown();
	}
	
	public static void Act2(int bpm) {
		Conductor conductor = new Conductor(bpm);
		
		Instrument[] instruments = MidiPlayer.getInstruments();
		for (int j = 0; j < instruments.length; j++) {
			System.out.println(j + " " + instruments[j].getName());	
		}

		MidiPlayer.setInstrument(instruments[46], 0);
		MidiPlayer.setInstrument(instruments[2], 1);
		
		ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.execute(new PerformerV2(0, Song.arpegio, conductor));
		executor.execute(new PerformerV2(1, Song.piano, conductor));
		executor.execute(new PerformerV2(1, Song.piano2, conductor));
		executor.execute(new PerformerV2(1, Song.piano3, conductor));
		executor.shutdown();
		
		Thread conductorThread = new Thread(conductor);
		conductorThread.run();
		
		try {
			executor.awaitTermination(15, TimeUnit.SECONDS);
			conductorThread.interrupt();
			conductorThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
