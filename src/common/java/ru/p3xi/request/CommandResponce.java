package ru.p3xi.request;

import java.io.Serializable;
import java.util.ArrayList;

import ru.p3xi.labwork.LabWork;

public class CommandResponce implements Serializable {
    private Boolean isOk;
    private ArrayList<LabWork> labWorks;
    private String responce;

    CommandResponce(Builder builder) {
        this.isOk = builder.isOk;
        this.labWorks = builder.labWorks;
        this.responce = builder.responce;
    }

    public Boolean getIsOk() {
        return isOk;
    }

    public ArrayList<LabWork> getLabWorks() {
        return labWorks;
    }

    public String getResponce() {
        return responce;
    }

    public static class Builder {
        private Boolean isOk;
        private ArrayList<LabWork> labWorks;
        private String responce;

        public Builder isOk(Boolean isOk) {
            this.isOk = isOk;
            return this;
        }

        public Builder labWorks(ArrayList<LabWork> labWorks) {
            this.labWorks = labWorks;
            return this;
        }

        public Builder responce(String responce) {
            this.responce = responce;
            return this;
        }

        public CommandResponce build() {
            return new CommandResponce(this);
        }
    }
}
