package com.RestAssuredTest.RATest.reports;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class EReport extends TestWatcher {
	private String rptFilename;

	public EReport(String rptFilename) {
		this.rptFilename = rptFilename;
	}

	@Override
	protected void failed(Throwable e, Description description) {
		ExtentReports extent = createReport();
		ExtentTest test = extent.startTest(description.getDisplayName(), "Test failed, click here for further details");

		// step log
		test.log(LogStatus.FAIL, "Failure: " + e.toString());
		flushReports(extent, test);
	}

	@Override
	protected void succeeded(Description description) {
		ExtentReports extent = createReport();
		ExtentTest test = extent.startTest(description.getDisplayName(), "-");

		// step log
		test.log(LogStatus.PASS, "-");
		flushReports(extent, test);
	}

	@SuppressWarnings("deprecation")
	private ExtentReports createReport() {
		ExtentReports extent = new ExtentReports(rptFilename, false);
		extent.config().reportName("My first extentReport report");
		extent.config().reportHeadline("See my awesome passed tests!");
		return extent;
	}

	private void flushReports(ExtentReports extent, ExtentTest test) {
		// ending test
		extent.endTest(test);
		// writing everything to document
		extent.flush();
	}
}
