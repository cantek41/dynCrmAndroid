package Model;

public class LoginRequest {
    private String grant_type;
    private String username;
    private String password;

    public String getGrant_type() {
        return grant_type;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
