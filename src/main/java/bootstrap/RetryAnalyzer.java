package bootstrap;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;


public class RetryAnalyzer implements IRetryAnalyzer {

    int retryAttemptsCounter = 0;
    private static final int RETRY_ATTEMPTS_NUMBER = new Integer(System.getProperty("retry.attempts.number", "5"));

    public boolean retry(ITestResult result) {
        if (!result.isSuccess()) {
            if(retryAttemptsCounter < RETRY_ATTEMPTS_NUMBER){
                retryAttemptsCounter++;
                return true;
            }
        }
        return false;
    }
}
