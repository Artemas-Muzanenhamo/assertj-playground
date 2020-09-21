package com.assertj.playground

import com.assertj.playground.domain.Degree.*
import com.assertj.playground.domain.Student
import com.assertj.playground.domain.Tutor
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class UniversityTests {
    private val university: University = University()
    private val computerScienceTutors = listOf(
            Tutor("tracy", "stevens", COMPUTER_SCIENCE),
            Tutor("paul", "mcintosh", COMPUTER_SCIENCE),
            Tutor("jane", "stonehouse", COMPUTER_SCIENCE)
    )

    private val mathematicsTutors = listOf(
            Tutor("john", "smith", MATHEMATICS),
            Tutor("sandy", "johnson", MATHEMATICS)
    )

    private val lawTutors = listOf(
            Tutor("steve", "smith", LAW)
    )

    private val englishTutors = listOf(
            Tutor("giannis", "thompson", ENGLISH_STUDIES)
    )

    private val dummyStudentsData = listOf(
            Student("james", "smith", COMPUTER_SCIENCE, computerScienceTutors, listOf("basketball", "football")),
            Student("ruvimbo", "muza", MATHEMATICS, mathematicsTutors, listOf(null)),
            Student("kobe", "bryant", LAW, lawTutors, listOf(null)),
            Student("ruvimbo", "muza", ENGLISH_STUDIES, englishTutors, listOf("football", "tennis"))
    )

    @Test
    fun `get all computer science students`() {
        val student = Student("james", "smith", COMPUTER_SCIENCE, computerScienceTutors, listOf("basketball", "football"))

        val computerScienceStudents = university.getAllStudentsByDegree(dummyStudentsData, COMPUTER_SCIENCE)

        assertThat(computerScienceStudents)
                .isNotEmpty
                .hasSize(1)
                .filteredOn { it.degree == COMPUTER_SCIENCE }
                .containsOnly(student)
    }

    @Test
    fun `get all tutors given a name`() {
        val student = Student("james", "smith", COMPUTER_SCIENCE, computerScienceTutors, listOf("basketball", "football"))
        val expectedTutors = dummyStudentsData.find { it.name == student.name }?.tutors

        val tutors: List<Tutor> = university.getTutorsByStudentName(dummyStudentsData, student.name)

        assertThat(tutors)
                .isNotEmpty
                .hasSize(3)
                .containsExactlyElementsOf(expectedTutors)
    }

    @Test
    fun `get all extra activities uniquely excluding empty or null activities`() {
        val activities: List<String> = university.getActivities(dummyStudentsData)

        assertThat(activities)
                .isNotEmpty
                .hasSize(3)
                .containsExactlyElementsOf(listOf("basketball", "football", "tennis"))
    }

}
