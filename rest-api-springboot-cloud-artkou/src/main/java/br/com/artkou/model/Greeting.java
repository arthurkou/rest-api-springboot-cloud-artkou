package br.com.artkou.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Greeting {

    private final long id;
    private final String content;

}
