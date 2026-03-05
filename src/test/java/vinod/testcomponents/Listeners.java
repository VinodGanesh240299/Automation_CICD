package vinod.testcomponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.IRetryAnalyzer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import vinod.resources.ExtentReportClass;

public class Listeners extends BaseTest implements ITestListener{

	//WebDriver driver;
	ExtentTest test;
	ExtentReports extent = ExtentReportClass.generateReport();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();	
	@Override
	public void onTestStart(ITestResult result) {
	    // not implemented
            ExtentTest test = extent.createTest(result.getMethod().getMethodName());
            extentTest.set(test);
	}

	@Override
	 public void onTestSuccess(ITestResult result) {
	    // not implemented
            extentTest.get().pass("Test Passed");
	}

	@Override
	  public void onTestFailure(ITestResult result) {
	    // not implemented
		extentTest.get().fail(result.getThrowable());
		  try {
			    driver = (WebDriver) result.getTestClass()
			            .getRealClass()
			            .getField("driver")
			            .get(result.getInstance());
			}
			catch (Exception e1) {
			    // TODO Auto-generated catch block
			    e1.printStackTrace();
			}

			try {
				extentTest.get().addScreenCaptureFromPath(
			            getScreenshotPath(
			                    result.getMethod().getMethodName(),
			                    driver
			    ));
			}
			catch (IOException e) {
			    // TODO Auto-generated catch block
			    e.printStackTrace();
			}
	  }

	@Override
	  public void onTestSkipped(ITestResult result) {
	    // not implemented
	  }

	@Override
	  public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	    // not implemented
	  }

	@Override
	  public void onTestFailedWithTimeout(ITestResult result) {
	    onTestFailure(result);
	  }
	
	@Override
	  public void onStart(ITestContext context) {
	    // not implemented
	  }

	@Override
	  public void onFinish(ITestContext context) {
	    // not implemented
		  extent.flush();
	  }
}
