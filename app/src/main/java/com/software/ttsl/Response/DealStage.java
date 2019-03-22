package com.software.ttsl.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DealStage {

    @SerializedName("stageId")
    @Expose
    private Integer stageId;
    @SerializedName("stage")
    @Expose
    private String stage;

    public Integer getStageId() {
        return stageId;
    }

    public void setStageId(Integer stageId) {
        this.stageId = stageId;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }
}
