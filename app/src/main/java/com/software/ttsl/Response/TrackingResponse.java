package com.software.ttsl.Response;

import com.software.ttsl.model.TraceResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TrackingResponse {

    @SerializedName("trace")
    @Expose
    private List<TraceResponse> trace = null;
    @SerializedName("header")
    @Expose
    private TrackingHeader header;

    public List<TraceResponse> getTrace() {
        return trace;
    }

    public void setTrace(List<TraceResponse> trace) {
        this.trace = trace;
    }

    public TrackingHeader getHeader() {
        return header;
    }

    public void setHeader(TrackingHeader header) {
        this.header = header;
    }


}
