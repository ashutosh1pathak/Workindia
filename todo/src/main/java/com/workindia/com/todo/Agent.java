package com.workindia.com.todo;

import java.util.Objects;

public class Agent {

    private Integer agent_id;
    private String password;

    public Integer getAgent_id() {
        return agent_id;
    }

    public void setAgent_id(Integer agent_id) {
        this.agent_id = agent_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agent agent = (Agent) o;
        return Objects.equals(agent_id, agent.agent_id) &&
                Objects.equals(password, agent.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(agent_id, password);
    }

    @Override
    public String toString() {
        return agent_id.toString();
    }
}
