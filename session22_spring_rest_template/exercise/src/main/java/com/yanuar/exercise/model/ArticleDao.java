package com.yanuar.exercise.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class ArticleDao implements Serializable {
    private String name;
    private String avatar;
}
