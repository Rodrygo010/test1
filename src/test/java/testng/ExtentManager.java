package testng;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    public static ExtentReports extent;

    public static ExtentReports getReport() {

        if(extent == null) {

            ExtentSparkReporter spark = new ExtentSparkReporter("reports/report.html");

            extent = new ExtentReports();
            extent.attachReporter(spark);
        }

        return extent;
    }
}
