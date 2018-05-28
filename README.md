# Automated Test for Haufe

This repo contains user evaluation automated test for Haufe performed by Artie Shendrikov

##Test execution

- In order to run the test, make sure you have a Google Chrome installed on your computer and added to the PATH. Also, download the version of the Chromedriver (http://chromedriver.chromium.org/downloads) compatible with your OS and Google Chrome versions and paste it to the project folder (ie. haufe_tech_task).
- Although the default values for the base url, user name, password and other essential variables have been added to the POM file, hardcoding is not always a good approach. Whenever executed on Jenkins, the standard approach would be to run the tests from the command line like this:
```bash
mvn clean test -Dbase.url="${base.url}" -Djob.name="${job.name}" -Dcandidate.name="${candidate.name}" -Duser.name="${user.name}" -Dpassword="${password}" -Dretry.attempts.number=${retry.attempts.number} -Drating=${rating}
```
- In order to generate a pretty html report after test execution, we can run command
```bash
mvn surefire-report:report-only site -DgenerateReports=false
```
straight after the tests finish running, then a **surefire-report.html** will be placed into the **/target/site** folder.