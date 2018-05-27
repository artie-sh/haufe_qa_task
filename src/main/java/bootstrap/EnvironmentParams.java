package bootstrap;


public class EnvironmentParams {

    private String baseUrl;
    private String username;
    private String password;
    private String jobName;
    private String candidateName;
    private int ratingToSet;

    public EnvironmentParams() {
        try {
            baseUrl = System.getProperty("base.url");
            username = System.getProperty("user.name");
            password = System.getProperty("password");
            jobName = System.getProperty("job.name");
            candidateName = System.getProperty("candidate.name");
            ratingToSet = new Integer(System.getProperty("rating"));
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    public String getBaseUrl() { return this.baseUrl; }
    public String getUsername() { return this.username; }
    public String getPassword() { return this.password; }
    public String getJobName() { return this.jobName; }
    public String getCandidateName() { return this.candidateName; }
    public int getRatingToSet() { return this.ratingToSet; }
}
