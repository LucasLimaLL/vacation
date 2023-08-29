package com.lucaslima.vacation.application.domains.periods;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StateTest {

    @Test
    void givenAbbreviationWhenSearchThenReturnState() {
        assertThat(State.fromAbbreviation("AC")).isEqualTo(State.AC);
    }

    @Test
    void givenAbbreviationALWhenSearchThenReturnState() {
        assertThat(State.fromAbbreviation("AL")).isEqualTo(State.AL);
    }

    @Test
    void givenAbbreviationAPWhenSearchThenReturnState() {
        assertThat(State.fromAbbreviation("AP")).isEqualTo(State.AP);
    }

    @Test
    void givenNameAcreWhenSearchThenReturnState() {
        assertThat(State.fromName("Acre")).isEqualTo(State.AC);
    }

    @Test
    void givenNameALWhenSearchThenReturnState() {
        assertThat(State.fromName("Alagoas")).isEqualTo(State.AL);
    }

    @Test
    void givenNameAPWhenSearchThenReturnState() {
        assertThat(State.fromName("Amapá")).isEqualTo(State.AP);
    }
}