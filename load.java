import java.util.Timer;
import java.util.TimerTask;

public class load {

    public static String combine_strings(String s1, String s2) {return s1 + s2;}
    //Default Period and symbols
    static long period = 400;
    static String symbol = "=";
    static String symbol_end = ">";
    ////////////////////////////////
    static final int max_status_length = 20;
    static int process_space_count = 0;
    static String spaces = "";
    static String status = "";
    static int process_count = 0;
    static int percent = 0;

    public static void main(String[] args) {
        try {
            period = Long.parseLong(args[0]);
            symbol = args[1];
            symbol_end = args[2];
        }
        catch (ArrayIndexOutOfBoundsException ignored) {}
        // CREATING NEW FUNCTION WHAT RUNS PER SOME MILLISECONDS
        process_count = percent / 5;
        process_space_count = max_status_length - process_count;
        for (int num = 0; num < process_space_count + 1; num++) {
            spaces = combine_strings(spaces , " ");
        }
        System.out.print("Load complete at " + percent + "%   [" + spaces + "] [" + process_count + "]\r");
        TimerTask task = new TimerTask() {
            public void run() {
                // PROCESS BAR ZONE
                process_count = percent / 5;
                status = "";
                spaces = "";
                for (int num = 0; num < process_count; num++) {
                    if (symbol.length() > 1) {
                        status = combine_strings(status, " " + symbol);
                    }
                    else {
                        status = combine_strings(status, symbol);
                    }
                }
                process_space_count = max_status_length - process_count;
                for (int num = 0; num < process_space_count; num++) {
                    spaces = combine_strings(spaces , " ");
                }
                percent += 3;
                if (percent > 100) {
                    percent = 100;
                }
                System.out.print("Load complete at " + percent + "% ");
                if (percent < 10) {
                    System.out.print("  ");
                }
                if (percent >= 10 && percent < 100) {
                    System.out.print(" ");
                }
                System.out.print("[" + status + symbol_end + spaces + "] [" + process_count + "] \r");
                // CODE WHAT CLOSING THE PROGRAM WHEN "percent" EQUALS OR BIGGER THAN 100
                if (percent >= 100) {
                    System.out.println("Load complete at 100% [" + status + symbol + symbol_end + "] [" + (process_count + 1) + "]");
                    System.exit(0);
                }
                // END
            }
        };
        Timer timer = new Timer("Timer");
        timer.scheduleAtFixedRate(task, 1000, period);
    }
}
