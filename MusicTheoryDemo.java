import javax.sound.midi.*;

public class MusicTheoryDemo {
    public static void main(String[] args) {
        try {
            // Create a Sequencer and open it
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();

            // Create a Sequence
            Sequence sequence = new Sequence(Sequence.PPQ, 4);

            // Create a Track
            Track track = sequence.createTrack();

            // Add notes to the track (e.g., C major scale)
            int[] notes = {60, 64, 67, 69};
            for (int note : notes) {
                MidiEvent noteOn = createNoteOnEvent(note);
                MidiEvent noteOff = createNoteOffEvent(note);
                track.add(noteOn);
                track.add(noteOff);
            }

            // Set the sequence in the sequencer and start playing
            sequencer.setSequence(sequence);
            sequencer.start();

            // Delay to allow notes to be played (adjust as needed)
            Thread.sleep(5000);

            // Stop and close the sequencer
            sequencer.stop();
            sequencer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static MidiEvent createNoteOnEvent(int note) throws InvalidMidiDataException {
        ShortMessage noteOnMessage = new ShortMessage();
        noteOnMessage.setMessage(ShortMessage.NOTE_ON, 0, note, 100);
        return new MidiEvent(noteOnMessage, 0);
    }

    private static MidiEvent createNoteOffEvent(int note) throws InvalidMidiDataException {
        ShortMessage noteOffMessage = new ShortMessage();
        noteOffMessage.setMessage(ShortMessage.NOTE_OFF, 0, note, 100);
        return new MidiEvent(noteOffMessage, 16);
    }
}
