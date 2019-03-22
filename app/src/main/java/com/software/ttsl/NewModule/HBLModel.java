package com.software.ttsl.NewModule;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HBLModel {

    @SerializedName("TracknTrace")
    private TracknTrace tracknTrace;


    public TracknTrace getTracknTrace() {
        return tracknTrace;
    }

    public void setTracknTrace(TracknTrace tracknTrace) {
        this.tracknTrace = tracknTrace;
    }

    public static class TracknTrace {
        @SerializedName("DetailsHeaders")

        private DetailsHeaders detailsHeaders;
        @SerializedName("DetailsValues")
        private DetailsValues detailsValues;
        @SerializedName("MilestoneHeaders")
        private MilestoneHeaders milestoneHeaders;
        @SerializedName("MilestoneValues")
        private MilestoneValues milestoneValues;

        public DetailsHeaders getDetailsheaders() {
            return detailsHeaders;
        }

        public void setDetailsheaders(DetailsHeaders detailsHeaders) {
            this.detailsHeaders = detailsHeaders;
        }

        public DetailsValues getDetailsValues() {
            return detailsValues;
        }

        public void setDetailsValues(DetailsValues detailsValues) {
            this.detailsValues = detailsValues;
        }

        public MilestoneHeaders getMilestoneHeaders() {
            return milestoneHeaders;
        }

        public void setMilestoneHeaders(MilestoneHeaders milestoneHeaders) {
            this.milestoneHeaders = milestoneHeaders;
        }

        public MilestoneValues getMilestoneValues() {
            return milestoneValues;
        }

        public void setMilestoneValues(MilestoneValues milestoneValues) {
            this.milestoneValues = milestoneValues;
        }
    }

    public static class DetailsHeaders {
        @SerializedName("string")
        private List<StringTag> string;

        public List<StringTag> getString() {
            return string;
        }

        public void setString(List<StringTag> string) {
            this.string = string;
        }
    }

    public static class DetailsValues {
        @SerializedName("string")
        private List<StringTag> string;

        public List<StringTag> getString() {
            return string;
        }

        public void setString(List<StringTag> string) {
            this.string = string;
        }
    }

    public static class MilestoneHeaders {
        @SerializedName("string")
        private List<StringTag> string;

        public List<StringTag> getString() {
            return string;
        }

        public void setString(List<StringTag> string) {
            this.string = string;
        }
    }

    public static class MilestoneValues {
        @SerializedName("string")
        private List<StringTag> string;

        public List<StringTag> getString() {
            return string;
        }

        public void setString(List<StringTag> string) {
            this.string = string;
        }
    }

    public static class StringTag {
        @SerializedName("content")
        private String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
