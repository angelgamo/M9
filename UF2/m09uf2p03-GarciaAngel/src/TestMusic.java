import javax.sound.midi.Instrument;

public class TestMusic
{
	public static void main(String[] args)
	{
		Note[] notesLead = 
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
		
		Note[] notesRythm =
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
		
		/*
		Instrument[] instruments = MidiPlayer.getInstruments();
		for(Instrument instrument: instruments)
			System.out.println(instrument.getName());
		*/
		
		try
		{
			for(int i = 0; i < notesLead.length; i++)
			{
				//MidiPlayer.setInstrument(instruments[0]);
				MidiPlayer.play(0, notesLead[i]);
				MidiPlayer.play(1, notesRythm[i]);
				Thread.sleep(250);
				MidiPlayer.stop(0, notesLead[i]);
				MidiPlayer.stop(1, notesRythm[i]);
			}
			
		}catch(InterruptedException e)
		{
			e.printStackTrace();
		}		
		
	}

}
