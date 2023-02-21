package pojo;

public class Waller {
    private String username;
    private Double waller;

    public Waller() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Double getWaller() {
        return waller;
    }

    public void setWaller(Double waller) {
        this.waller = waller;
    }

    @Override
    public String toString() {
        return "Waller{" +
                "username='" + username + '\'' +
                ", waller=" + waller +
                '}';
    }
}
