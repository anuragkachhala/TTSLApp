package com.software.ttsl.NewModule;

import com.google.gson.annotations.SerializedName;

import java.util.List;



public class ContainerModel {


    @SerializedName("ContainerTrack")
    private Containertrack containertrack;

    public Containertrack getContainertrack() {
        return containertrack;
    }

    public void setContainertrack(Containertrack containertrack) {
        this.containertrack = containertrack;
    }

    public static class Containertrack {
        @SerializedName("DetailsHeaders")
        private Detailsheaders detailsheaders;
        @SerializedName("DetailsValues")
        private Detailsvalues detailsvalues;
        @SerializedName("ContainerHeaders")
        private Containerheaders containerheaders;
        @SerializedName("ContainerValues")
        private Containervalues containervalues;


        public Detailsheaders getDetailsheaders() {
            return detailsheaders;
        }

        public void setDetailsheaders(Detailsheaders detailsheaders) {
            this.detailsheaders = detailsheaders;
        }

        public Detailsvalues getDetailsvalues() {
            return detailsvalues;
        }

        public void setDetailsvalues(Detailsvalues detailsvalues) {
            this.detailsvalues = detailsvalues;
        }



        public Containerheaders getContainerheaders() {
            return containerheaders;
        }

        public void setContainerheaders(Containerheaders containerheaders) {
            this.containerheaders = containerheaders;
        }

        public Containervalues getContainervalues() {
            return containervalues;
        }

        public void setContainervalues(Containervalues containervalues) {
            this.containervalues = containervalues;
        }
    }

    public static class Detailsheaders {
        @SerializedName("string")
        private List<StringTag> string;

        public List<StringTag> getString() {
            return string;
        }

        public void setString(List<StringTag> string) {
            this.string = string;
        }
    }

    public static class Detailsvalues {
//        @SerializedName("string")
//        private String string;

        @SerializedName("string")
        private List<StringTag> strings;

//        public String getString() {
//            return string;
//        }
//
//        public void setString(String string) {
//            this.string = string;
//        }

        public List<StringTag> getStrings() {
            return strings;
        }

        public void setString(List<StringTag> strings) {
            this.strings = strings;
        }
    }

    public static class Containerheaders {
        @SerializedName("string")
        private List<StringTag> string;

        public List<StringTag> getString() {
            return string;
        }

        public void setString(List<StringTag> string) {
            this.string = string;
        }
    }

    public static class Containervalues {
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
