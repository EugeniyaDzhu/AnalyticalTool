import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

class QueryLine {
    private String[] service;
    private String[] question;
    private String responseType;
    private long dateFrom;
    private long dateTo;

    QueryLine(String queryLineString) {
        String[] waitingTimeLineArray = queryLineString.split(" ");
        service = waitingTimeLineArray[1].split("\\.");
        question = waitingTimeLineArray[2].split("\\.");
        responseType = waitingTimeLineArray[3];

        String dateString = waitingTimeLineArray[4];
        String dateFromString = dateString.substring(0, 10);
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        try {
            Date date = format.parse(dateFromString);
            dateFrom = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            dateFrom = 0;
        }
        if ((dateString.length() == 10) || (dateFrom == 0)) {
            dateTo = dateFrom;
        } else {
            String dateToString = dateString.substring(11, 21);
            try {
                Date date = format.parse(dateToString);
                dateTo = date.getTime();
            } catch (ParseException e) {
                e.printStackTrace();
                dateTo = 0;
            }
        }
    }


    String[] getService() {
        return service;
    }

    String[] getQuestion() {
        return question;
    }

    String getResponseType() {
        return responseType;
    }

    long getDateFrom() {
        return dateFrom;
    }

    long getDateTo() {
        return dateTo;
    }

    Integer findAverage(List<WaitingTimeLine> waitingTimeLines) {

        return (int) waitingTimeLines.stream().filter(tl -> tl.isValid(this)).mapToInt(WaitingTimeLine::getTime).average().orElse(0);

    }
}
