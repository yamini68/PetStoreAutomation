package api.utlities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager implements ITestListener {

    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    private ThreadLocal<ExtentTest> threadLocalTest = new ThreadLocal<>();

    String repName;

    @Override
    public void onStart(ITestContext context) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName = "Test-Report-" + timeStamp + ".html";

        sparkReporter = new ExtentSparkReporter(".\\Reports\\" + repName);
        sparkReporter.config().setDocumentTitle("RestAssuredAutomationProject");
        sparkReporter.config().setReportName("pet Store Users API");
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Application", "pet store Users API");
        extent.setSystemInfo("Operating system", System.getProperty("os.name"));
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Enviroment", "QA");
        extent.setSystemInfo("user", "yamini");
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getName());
        threadLocalTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        threadLocalTest.get().assignCategory(result.getName());
        threadLocalTest.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        threadLocalTest.get().createNode(result.getName());
        threadLocalTest.get().assignCategory(result.getMethod().getGroups());
        threadLocalTest.get().log(Status.FAIL, "Test Failed");
        threadLocalTest.get().log(Status.FAIL, result.getThrowable().getMessage());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        threadLocalTest.get().createNode(result.getName());
        threadLocalTest.get().assignCategory(result.getMethod().getGroups());
        threadLocalTest.get().log(Status.SKIP, "Test Skipped");
        if (result.getThrowable() != null) {
            threadLocalTest.get().log(Status.SKIP, result.getThrowable().getMessage());
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
