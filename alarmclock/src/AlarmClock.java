import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Scanner;

public class AlarmClock implements Runnable {
    private final LocalTime alarmTime;
    private final String filePath;
    private final Scanner scanner;

    public AlarmClock(LocalTime alarmTime, String filePath, Scanner scanner) {
        this.alarmTime = alarmTime;
        this.filePath = filePath;
        this.scanner = scanner;
    }

    @Override
    public void run() {
        while (LocalTime.now().isBefore(alarmTime)) {
            try {
                Thread.sleep(1000);
                LocalTime now = LocalTime.now();
                System.out.printf("\r%02d:%02d:%02d", now.getHour(), now.getMinute(), now.getSecond());
                System.out.flush();  // Ensure the output is properly refreshed
            } catch (InterruptedException e) {
                System.out.println("Thread was interrupted");
                return;  // Stop execution if interrupted
            }
        }
        System.out.println("\n*** Alarm Noises ***");
        playSound(filePath);
    }

    private void playSound(String filePath) {
        File audioFile = new File(filePath);
        try (AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile)) {
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            System.out.println("Press Enter to stop the alarm...");
            scanner.nextLine();  // Wait for user input
            clip.stop();
            clip.close();
        } catch (UnsupportedAudioFileException e) {
            System.out.println("Audio file not supported.");
        } catch (LineUnavailableException e) {
            System.out.println("Audio unavailable.");
        } catch (IOException e) {
            System.out.println("Error reading the audio file.");
        }
    }
}
