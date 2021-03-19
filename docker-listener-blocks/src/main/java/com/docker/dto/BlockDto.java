package com.docker.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "blocks")
public class BlockDto {

    @Id
    @JsonAlias(value = "height")
    private Integer number;

    private String time;
}
