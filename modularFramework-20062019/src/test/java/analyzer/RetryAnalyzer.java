package analyzer;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

	int count = 0;
	int retryMaxCount = 3;

	@Override
	public boolean retry(ITestResult result) {

		if (count < retryMaxCount) {
			count++;
			return true;
		}

		return false;
	}

}
