import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class AnalyticalTool {
    public static void main(String[] args) {

        List<WaitingTimeLine> waitingTimeLineList = new LinkedList<>();
        try (BufferedReader in = new BufferedReader(new FileReader("src/main/resources/input.txt"))) {
            int lineNumber = Integer.parseInt(in.readLine());
            for (int i = 0; i < lineNumber; i++) {
                String string = in.readLine();
                if ("C".equals(string.substring(0, 1))) {
                    WaitingTimeLine waitingTimeLine = new WaitingTimeLine(string);
                    waitingTimeLineList.add(waitingTimeLine);
                } else {
                    QueryLine queryLine = new QueryLine(string);
                    int average = queryLine.findAverage(waitingTimeLineList);
                    System.out.println(average == 0 ? "-" : average);
                }
            }
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }
}
