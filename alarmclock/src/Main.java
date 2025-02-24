//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import javax.management.InvalidAttributeValueException;
import java.time.DateTimeException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
     Scanner scanner = new Scanner(System.in);
     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
     LocalTime alarmtime=null;
     String filePath ="C:\\Users\\User\\Documents\\file.WAV";
    while(alarmtime==null){
        try{
            System.out.println("Enter an alarm time(HH:mm:ss)");
            String inputTime =scanner.nextLine();


            alarmtime = LocalTime.parse(inputTime, formatter);

            System.out.println("Alarm set for "+alarmtime);


        }
        catch (DateTimeException e){
            System.out.println("Invalid format. Please use a correct format");
        }
    }
    AlarmClock alarmClock = new AlarmClock(alarmtime, filePath,scanner);
Thread alarmThread = new Thread(alarmClock);
alarmThread.start();

    }
}
