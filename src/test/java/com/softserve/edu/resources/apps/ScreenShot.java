package com.softserve.edu.resources.apps;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShot {

	private final String TIME_TEMPLATE = "yyyy_MM_dd_HH-mm-ss";
	private final String FILE_SUFFIX = "_CaptureScreenImage.png";
	private final String DEFAULT_DIRECTORY = "./test-output";
	private final String MAVEN_DIRECTORY = "surefire.reports.directory";
	private final String SLASH = "/";
	private final String FAILED_TO_CREATE = "Failed to create screenshot: %s";

	private String getCurrentTime() {
		return new SimpleDateFormat(TIME_TEMPLATE).format(new Date());
	}

	private String getOutputDirectory() {
		String outputDirectory = System.getProperty(MAVEN_DIRECTORY);
		if ((outputDirectory == null) || (outputDirectory.isEmpty())) {
			outputDirectory = DEFAULT_DIRECTORY;
		}
		System.out.println("\t+++++outputDirectory = " + outputDirectory);
		return outputDirectory + SLASH;
	}

	private String getAbsolutePathFileName() {
		return getOutputDirectory() + getCurrentTime() + FILE_SUFFIX;
	}

	/**
	 * @return Absolute path of filename.
	 */
	public String captureScreen(WebDriver driver) {
		String absolutePathFileName = getAbsolutePathFileName();
		try {
			File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcFile, new File(absolutePathFileName));
		} catch (Exception e) {
			throw new RuntimeException(String.format(FAILED_TO_CREATE, absolutePathFileName), e);
		}
		return absolutePathFileName;
	}
	
}
