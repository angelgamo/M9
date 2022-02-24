import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

public final class MidiPlayer {

	private static final int VELOCITY = 75;
	private static Synthesizer midiSynth;
	private static MidiChannel[] mChannels;

	static {
		try {
			midiSynth = MidiSystem.getSynthesizer();
			midiSynth.open();

			// get and load default instrument and channel lists
			Instrument[] instr = midiSynth.getDefaultSoundbank().getInstruments();
			mChannels = midiSynth.getChannels();

			midiSynth.loadInstrument(instr[0]);// load an instrument

		} catch (MidiUnavailableException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static Instrument[] getInstruments()
	{
		return midiSynth.getDefaultSoundbank().getInstruments();
	}
	
	public static void setInstrument(Instrument instrument, int channel)
	{
		
		Instrument[] instruments = midiSynth.getLoadedInstruments();
		boolean instrumentFound = false;
		for(Instrument inst : instruments)
			if(inst == instrument)
				instrumentFound = true;
		
		if(!instrumentFound)
			midiSynth.loadInstrument(instrument);
		
		if(channel < (mChannels.length-1))
			mChannels[channel].programChange(instrument.getPatch().getProgram());
	}
	
	public static void play(int channel, Note... notes)
	{
		for (Note note : notes)
		{
			if(note.getFrequency() != Note.Frequency.SILENCE)
				mChannels[channel].noteOn(note.getFrequency(), VELOCITY);
		}
	}
	
	public static void play(Note...notes)
	{
		play(0, notes);
	}
	
	public static void stop(int channel, Note... notes)
	{
		for (Note note : notes)
		{
			if(note.getFrequency() != Note.Frequency.SILENCE)
				mChannels[channel].noteOff(note.getFrequency(), VELOCITY);
		}
	}
	
	public static void stop(Note...notes)
	{
		stop(0, notes);
	}
}
