package com.sg.superhero.dto;

import java.util.Objects;

public class Member {

    private int personId;
    private int orgId;

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return personId == member.personId && orgId == member.orgId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, orgId);
    }
}
