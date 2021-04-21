package com.yanuar.exercise.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class PostDao implements Serializable {
    private String title;
    private String body;
}
