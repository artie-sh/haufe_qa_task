package tests;

import bootstrap.RetryAnalyzer;
import com.sun.org.glassfish.gmbal.Description;
import org.testng.annotations.Test;
import pages.ApplicationDetailsPage;
import pages.DashBoardPage;
import pages.LogInPage;


public class RateCandidateTest extends BaseTest {

    @Description("Rate Candidate and Comment Functionality Test")
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void rateCandidateAndCommentTest() {


        LogInPage logInPage = new LogInPage(driver, baseUrl);
        logInPage.getOnLogInPage();

        DashBoardPage dashBoardPage = logInPage.logIn(username, password);
        dashBoardPage.clickOnJob(jobName);

        ApplicationDetailsPage applicationDetailsPage = dashBoardPage.getOnApplicationDetailsPage(candidateName);
        String commentText = applicationDetailsPage.getCommentText();

        applicationDetailsPage.addAssessment(commentText, ratingToSet);
        log("Comment added : " + commentText);
        applicationDetailsPage.assertCommentAndRatingSaved(commentText, ratingToSet);
    }
}
