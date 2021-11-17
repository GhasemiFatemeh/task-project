package controller;

public interface ServerConfig {
    void start(String serverAddress, String serviceName);
    void stop();
}
