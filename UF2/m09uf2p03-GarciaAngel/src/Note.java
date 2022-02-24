public class Note
{
	private int frequency;
	private int duration;
	
	public Note(int frequency, int duration)
	{
		this.frequency = frequency;
		this.duration = duration;
	}
	
	public int getFrequency()
	{
		return frequency;
	}
	
	public int getDuration()
	{
		return duration;
	}
	
	public static class Frequency
	{
		private static final int octave = 12;
		
		public static final int SILENCE = -1;
		
		public static final int A0 = 21;
		public static final int As0 = 22, Bb0 = As0;
		public static final int B0 = 23, Cb0 = B0;
		public static final int C1 = 24, Bs0 = C1;
		public static final int Cs1 = 25, Db1 = Cs1;
		public static final int D1 = 26;
		public static final int Ds1 = 27, Eb1 = Ds1;
		public static final int E1 = 28, Fb1 = E1;
		public static final int F1 = 29;
		public static final int Fs1 = 30, Gb1 = Fs1;
		public static final int G1 = 31;
		public static final int Gs1 = 32, Ab1 = Gs1;
		
		public static final int A1 = A0+octave;
		public static final int As1 = As0+octave, Bb1 = As1;
		public static final int B1 = B0+octave, Cb2 = B1;
		
		public static final int C2 = C1+octave, Bs1 = C2;
		public static final int Cs2 = Cs1+octave, Db2 = Cs2;
		public static final int D2 = D1+octave;
		public static final int Ds2 = Ds1+octave, Eb2 = Ds2;
		public static final int E2 = E1+octave, Fb2 = E2;
		public static final int F2 = F1+octave;
		public static final int Fs2 = Fs1+octave, Gb2 = Fs2;
		public static final int G2 = G1+octave;
		public static final int Gs2 = Gs1+octave, Ab2 = Gs2;
		public static final int A2 = A1+octave;
		public static final int As2 = As1+octave, Bb2 = As2;
		public static final int B2 = B1+octave, Cb3 = B2;
		
		public static final int C3 = C2+octave, Bs2 = C3;
		public static final int Cs3 = Cs2+octave, Db3 = Cs3;
		public static final int D3 = D2+octave;
		public static final int Ds3 = Ds2+octave, Eb3 = Ds3;
		public static final int E3 = E2+octave, Fb3 = E3;
		public static final int F3 = F2+octave;
		public static final int Fs3 = Fs2+octave, Gb3 = Fs3;
		public static final int G3 = G2+octave;
		public static final int Gs3 = Gs2+octave, Ab3 = Gs3;
		public static final int A3 = A2+octave;
		public static final int As3 = As2+octave, Bb3 = As3;
		public static final int B3 = B2+octave, Cb4 = B3;
		
		public static final int C4 = C3+octave, Bs3 = C4;
		public static final int Cs4 = Cs3+octave, Db4 = Cs4;
		public static final int D4 = D3+octave;
		public static final int Ds4 = Ds3+octave, Eb4 = Ds4;
		public static final int E4 = E3+octave, Fb4 = E4;
		public static final int F4 = F3+octave;
		public static final int Fs4 = Fs3+octave, Gb4 = Fs4;
		public static final int G4 = G3+octave;
		public static final int Gs4 = Gs3+octave, Ab4 = Gs4;
		public static final int A4 = A3+octave;
		public static final int As4 = As3+octave, Bb4 = As4;
		public static final int B4 = B3+octave, Cb5 = B4;
		
		public static final int C5 = C4+octave, Bs4 = C5;
		public static final int Cs5 = Cs4+octave, Db5 = Cs5;
		public static final int D5 = D4+octave;
		public static final int Ds5 = Ds4+octave, Eb5 = Ds5;
		public static final int E5 = E4+octave, Fb5 = E5;
		public static final int F5 = F4+octave;
		public static final int Fs5 = Fs4+octave, Gb5 = Fs5;
		public static final int G5 = G4+octave;
		public static final int Gs5 = Gs4+octave, Ab5 = Gs5;
		public static final int A5 = A4+octave;
		public static final int As5 = As4+octave, Bb5 = As5;
		public static final int B5 = B4+octave, Cb6 = B5;
		
		public static final int C6 = C5+octave, Bs5 = C6;
		public static final int Cs6 = Cs5+octave, Db6 = Cs6;
		public static final int D6 = D5+octave;
		public static final int Ds6 = Ds5+octave, Eb6 = Ds6;
		public static final int E6 = E5+octave, Fb6 = E6;
		public static final int F6 = F5+octave;
		public static final int Fs6 = Fs5+octave, Gb6 = Fs6;
		public static final int G6 = G5+octave;
		public static final int Gs6 = Gs5+octave, Ab6 = Gs6;
		public static final int A6 = A5+octave;
		public static final int As6 = As5+octave, Bb6 = As6;
		public static final int B6 = B5+octave, Cb7 = B6;
		
		public static final int C7 = C6+octave, Bs6 = C7;
		public static final int Cs7 = Cs6+octave, Db7 = Cs7;
		public static final int D7 = D6+octave;
		public static final int Ds7 = Ds6+octave, Eb7 = Ds7;
		public static final int E7 = E6+octave, Fb7 = E7;
		public static final int F7 = F6+octave;
		public static final int Fs7 = Fs6+octave, Gb7 = Fs7;
		public static final int G7 = G6+octave;
		public static final int Gs7 = Gs6+octave, Ab7 = Gs7;
		public static final int A7 = A6+octave;
		public static final int As7 = As6+octave, Bb7 = As7;
		public static final int B7 = B6+octave, Cb8 = B7;
		
		public static final int C8 = C7+octave, Bs7 = C8;
		public static final int Cs8 = Cs6+octave, Db8 = Cs8;
		public static final int D8 = D6+octave;
		public static final int Ds8 = Ds6+octave, Eb8 = Ds8;
		public static final int E8 = E6+octave, Fb8 = E8;
		public static final int F8 = F6+octave;
		public static final int Fs8 = Fs6+octave, Gb8 = Fs8;
		public static final int G8 = G6+octave;
		public static final int Gs8 = Gs6+octave, Ab8 = Gs8;
		public static final int A8 = A7+octave;
		public static final int As8 = As7+octave, Bb8 = As8;
		public static final int B8 = B7+octave, Cb9 = B8;
		
		public static final int C9 = C8+octave, Bs8 = C9;
		public static final int Cs9 = Cs6+octave, Db9 = Cs9;
		public static final int D9 = D6+octave;
		public static final int Ds9 = Ds6+octave, Eb9 = Ds9;
		public static final int E9 = E6+octave, Fb9 = E9;
		public static final int F9 = F6+octave;
		public static final int Fs9 = Fs6+octave, Gb9 = Fs9;
		public static final int G9 = G6+octave;
		public static final int Gs9 = Gs6+octave, Ab9 = Gs9;
	}
	public static class Duration
	{
		public static final int sixty_fourth = 1;
		public static final int thirty_second = sixty_fourth*2;
		public static final int sixteenth = thirty_second*2;
		public static final int eight = sixteenth*2;
		public static final int quarter = eight*2;
		public static final int half = quarter*2;
		public static final int whole = half*2;
		
		//SP
		public static final int semifusa = sixty_fourth;
		public static final int fusa = thirty_second;
		public static final int semicorchea = sixteenth;
		public static final int corchea = eight;
		public static final int negra = quarter;
		public static final int blanca = half;
		public static final int redonda = whole;
	}
}