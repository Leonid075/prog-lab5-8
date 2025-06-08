package ru.p3xi.request;

import java.io.Serializable;

import ru.p3xi.labwork.Difficulty;
import ru.p3xi.labwork.LabWork;

public class CommandRequest implements Serializable {
    private String command;
    private LabWork.Builder labWork;
    private Long id;
    private Difficulty difficulty;

    CommandRequest(Builder builder) {
        this.command = builder.command;
        this.labWork = builder.labWork;
        this.id = builder.id;
        this.difficulty = builder.difficulty;
    }

    public String getCommand() {
        return command;
    }

    public Long getId() {
        return id;
    }

    public LabWork.Builder getLabWork() {
        return labWork;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public static class Builder {
        private String command;
        private LabWork.Builder labWork;
        private Long id;
        private Difficulty difficulty;

        public Builder labWork(LabWork.Builder labWork) {
            this.labWork = labWork;
            return this;
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder difficulty(Difficulty difficulty) {
            this.difficulty = difficulty;
            return this;
        }

        public Builder command(String command) {
            this.command = command;
            return this;
        }

        public CommandRequest build() {
            return new CommandRequest(this);
        }
    }
}
