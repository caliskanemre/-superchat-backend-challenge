package com.superchat.core.service.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@SequenceGenerator(name = "message_gen", sequenceName = "message_id_seq", allocationSize = 1)
@Table
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "message_gen")
    private long id;

    @Column(nullable = false)
    private String contactMessage;

    @Column(nullable = false)
    private long contactId;

    @Column
    private MessageDirection messageDirection;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return super.equals(other);
    }
}
