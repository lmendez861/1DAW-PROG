package com.gsd.daw.prog.apache.logreader;

public class ApacheLogEntry {
    private String timestamp;
    private String clientIp;
    private String request;
    private int status;
    private long bytes;

    public ApacheLogEntry(String timestamp, String clientIp, String request, int status, long bytes) {
        this.timestamp = timestamp;
        this.clientIp = clientIp;
        this.request = request;
        this.status = status;
        this.bytes = bytes;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getClientIp() {
        return clientIp;
    }

    public String getRequest() {
        return request;
    }

    public int getStatus() {
        return status;
    }

    public long getBytes() {
        return bytes;
    }
}