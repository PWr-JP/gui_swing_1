package tb.soft;

public class AddUserDto {
    public String name;
    public String surname;
    public CredentialsDto credentials;

    private AddUserDto(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public AddUserDto(String name, String surname, CredentialsDto credentials) {
        this(name, surname);
        this.credentials = credentials;
    }

    public AddUserDto(String name, String surname, String login, Integer passwordHash) {
        this(name, surname, new CredentialsDto(login, passwordHash));
    }
}
