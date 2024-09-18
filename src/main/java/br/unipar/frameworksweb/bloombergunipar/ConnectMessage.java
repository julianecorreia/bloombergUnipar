package br.unipar.frameworksweb.bloombergunipar;

public class ConnectMessage {
    private String user;

    // Construtor padrão
    public ConnectMessage() {
    }

    public ConnectMessage(String name) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public void setName(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "ConnectMessage{" +
                "user='" + user + '\'' +
                '}';
    }
}

