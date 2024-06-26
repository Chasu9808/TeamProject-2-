package com.busanit501.teamproject2.msy.domain;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class msyMember extends BaseEntity {
    @Id
    private String mid;
    private String mpw;
    private String name;
    private String email;
    private String phone;

    private boolean del;
    private boolean social;

    // 멤버를 조회시 roleSet 를 같이 조회를 하기.
    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<msyMemberRole> roleSet = new HashSet<>();

    public void changeEmail(String email) {
        this.email = email;
    }
    public void changePassword(String mpw) {
        this.mpw = mpw;
    }
    public void addRole(msyMemberRole memberRole) {
        this.roleSet.add(memberRole);

    }
    public void clearRole() {
        this.roleSet.clear();
    }
}
