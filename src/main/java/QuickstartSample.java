import com.google.analytics.data.v1beta.BetaAnalyticsDataClient;
import com.google.analytics.data.v1beta.DateRange;
import com.google.analytics.data.v1beta.Dimension;
import com.google.analytics.data.v1beta.Metric;
import com.google.analytics.data.v1beta.Row;
import com.google.analytics.data.v1beta.RunReportRequest;
import com.google.analytics.data.v1beta.RunReportResponse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparing;

public class QuickstartSample {

    public static void main(String... args) throws Exception {
        /**
         * TODO(developer): Replace this variable with your Google Analytics 4 property ID before
         * running the sample.
         */

        String propertyId = "294130604"; //din appId https://analytics.google.com/analytics/web/?authuser=1#/p294130604/reports/intelligenthome?params=_u..nav%3Dmaui&collectionId=user
        sampleRunReport(propertyId);
    }

    // This is an example snippet that calls the Google Analytics Data API and runs a simple report
    // on the provided GA4 property id.
    static void sampleRunReport(String propertyId) throws Exception {
        // Using a default constructor instructs the client to use the credentials
        // specified in GOOGLE_APPLICATION_CREDENTIALS environment variable.
        try (BetaAnalyticsDataClient analyticsData = BetaAnalyticsDataClient.create()) {

//            RunReportRequest request =
//                    RunReportRequest.newBuilder()
//                            .setProperty("properties/" + propertyId)
//                            .addDimensions(Dimension.newBuilder().setName("city"))
//                            .addMetrics(Metric.newBuilder().setName("activeUsers"))
//                            .addDimensions(Dimension.newBuilder().setName("country"))
//                            .addDateRanges(DateRange.newBuilder().setStartDate("2020-03-31").setEndDate("today"))
//                            .build();

            RunReportRequest request =
                    RunReportRequest.newBuilder()
                            .setProperty("properties/" + propertyId)
                            .addDimensions(Dimension.newBuilder().setName("customUser:crm_id"))
                            .addDimensions(Dimension.newBuilder().setName("linkUrl"))
                            .addDimensions(Dimension.newBuilder().setName("dateHour"))
//                            .addMetrics(Metric.newBuilder().setName("dateHourMinute"))
//                            .addMetrics(Metric.newBuilder().setName("activeUsers"))
//                            .addDimensions(Dimension.newBuilder().setName("crm_id"))
                            .addDateRanges(DateRange.newBuilder().setStartDate("2020-03-31").setEndDate("today"))
                            .build();

            // Make the request.
            RunReportResponse response = analyticsData.runReport(request);

            System.out.println("Report result:");
            // Iterate through every row of the API response.
            List<Row> rowsList = response.getRowsList();

            List<Row> rows2 = new ArrayList<>(rowsList);

            Collections.sort(rows2, comparing(u -> u.getDimensionValues(2).getValue()));


//            Collections.sort(Comparator.comparing(row -> row.getDimensionValues(0)));
            for (Row row : rows2) {
//                System.out.println(row);
                System.out.printf("%s || %s || %s %n", row.getDimensionValues(0).getValue(), row.getDimensionValues(1).getValue(), row.getDimensionValues(2).getValue());
            }
        }
    }
}