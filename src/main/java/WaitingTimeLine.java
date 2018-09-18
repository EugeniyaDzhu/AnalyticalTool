import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class WaitingTimeLine {

    private String[] service;
    private String[] question;
    private String responseType;
    private long responseDate;
    private int time;

    WaitingTimeLine(String waitingTimeLineString) {
        String[] waitingTimeLineArray = waitingTimeLineString.split(" ");
        service = waitingTimeLineArray[1].split("\\.");
        question = waitingTimeLineArray[2].split("\\.");
        responseType = waitingTimeLineArray[3];

        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        try {
            Date date = format.parse(waitingTimeLineArray[4]);
            responseDate = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            responseDate = 0;
        }

        time = Integer.parseInt(waitingTimeLineArray[5]);
    }

    int getTime() {
        return time;
    }

    private boolean isSuitable(String[] queryStrings, String[] timeLineStrings) {
        int queryLength = queryStrings.length;
        int timeLineLength = timeLineStrings.length;
        if (queryLength > timeLineLength) {
            return false;
        } else {
            for (int i = 0; i < queryLength; i++) {
                if (!(queryStrings[i].equals(timeLineStrings[i]))) {
                    return false;
                }
            }
        }
        return true;
    }

    boolean isValid(QueryLine queryLine) {
        if (!(isSuitable(queryLine.getService(), service))) {
            return false;
        }
        if (!("*".equals(queryLine.getQuestion()[0]))) {
            if (!(isSuitable(queryLine.getQuestion(), question))) {
                return false;
            }
        }
        if (!(queryLine.getResponseType().equals(responseType))) {
            return false;
        }
        return (responseDate >= queryLine.getDateFrom()) && (responseDate <= queryLine.getDateTo());

    }


}
