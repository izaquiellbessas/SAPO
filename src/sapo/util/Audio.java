/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sapo.util;

import java.io.File;
import java.io.IOException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import javax.swing.JOptionPane;

/**
 *
 * @author izaquiel
 */
public class Audio {

    public void audio(String url) {
        //AudioClip som= new AudioClipImpl(urlSom) ;
        try {
            // From file
            Sequence sequence = MidiSystem.getSequence(new File(url));

            // From URL
            //  sequence = MidiSystem.getSequence(new URL("http://hostname/midifile"));

            // Create a sequencer for the sequence
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequencer.setSequence(sequence);

            // Start playing
            sequencer.start();
            JOptionPane.showConfirmDialog(null, url);
            // } catch (MalformedURLException e) {
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }

    public void executa(String url) {
        AudioInputStream din = null;
        try {
            File file = new File(System.getProperty("user.dir") + url);
            AudioInputStream in = AudioSystem.getAudioInputStream(file);
            AudioFormat baseFormat = in.getFormat();
            AudioFormat decodedFormat = new AudioFormat(
                    AudioFormat.Encoding.PCM_SIGNED,
                    baseFormat.getSampleRate(), 16, baseFormat.getChannels(),
                    baseFormat.getChannels() * 2, baseFormat.getSampleRate(),
                    false);
            din = AudioSystem.getAudioInputStream(decodedFormat, in);
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, decodedFormat);
            SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
            if (line != null) {
                line.open(decodedFormat);
                byte[] data = new byte[4096];
                // Start
                line.start();

                int nBytesRead;
                while ((nBytesRead = din.read(data, 0, data.length)) != -1) {
                    line.write(data, 0, nBytesRead);
                }
                // Stop
                line.drain();
                line.stop();
                line.close();
                din.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (din != null) {
                try {
                    din.close();
                } catch (IOException e) {
                }
            }
        }
    }
}
