package com.example.demo.student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class StudentRepositoryTest {

    public static final String EMAIL = "jamila@gmail.com";

    @Autowired
    private StudentRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldCheckIfStudentEmailExists() {
        // given
        Student student = new Student(
                "Jamila",
                EMAIL,
                Gender.FEMALE
        );
        underTest.save(student);

        // when
        Boolean expected = underTest.selectExistsEmail(EMAIL);

        // then
        assertThat(expected).isTrue();
    }

    @Test
    void itShouldCheckIfStudentEmailDoesNotExist() {
        // given

        // when
        Boolean expected = underTest.selectExistsEmail(EMAIL);

        // then
        assertThat(expected).isFalse();
    }
}