package com.lucaslima.vacation.application.domains;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true, setterPrefix = "with")
public class City {

    private final String name;
    private final State state;


}
