package tests;

import bootstrap.RetryAnalyzer;
import org.testng.annotations.Test;
import pages.ApplicationDetailsPage;
import pages.DashBoardPage;
import pages.LogInPage;


public class RateCandidateTest extends BaseTest {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void rateCandidateAndCommentTest() {

        LogInPage logInPage = new LogInPage(driver, baseUrl);
        logInPage.getOnLogInPage();

        DashBoardPage dashBoardPage = logInPage.logIn(username, password);
        dashBoardPage.clickOnJob(jobName);

        ApplicationDetailsPage applicationDetailsPage = dashBoardPage.getOnApplicationDetailsPage(candidateName);
        String commentText = applicationDetailsPage.getCommentText();

        applicationDetailsPage.addComment(commentText);
        log("Comment added: " + commentText);
        applicationDetailsPage.selectInviteForFirstInverview();
        applicationDetailsPage.submitNextStep();
        applicationDetailsPage.assertChangesSaved(commentText);
    }
}
