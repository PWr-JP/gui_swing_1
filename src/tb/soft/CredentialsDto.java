package tb.soft;

public class CredentialsDto {
    public String login;
    public Integer passwordHash;

    public CredentialsDto(String login, Integer passwordHash) {
        this.login = login;
        this.passwordHash = passwordHash;
    }
}
