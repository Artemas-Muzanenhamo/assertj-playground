package com.assertj.playground

import com.assertj.playground.domain.Degree
import com.assertj.playground.domain.Student
import com.assertj.playground.domain.Tutor

class University {
    fun main(args: Array<String>) {
    }

    fun getAllStudentsByDegree(students: List<Student>, degree: Degree): List<Student> {
        return students
                .filter { it.degree == degree }
    }

    fun getTutorsByStudentName(students: List<Student>, name: String): List<Tutor> {
        return students
                .filter { it.name == name }
                .flatMap { it.tutors }
    }

    fun getActivities(students: List<Student>): List<String> {
        return students
                .flatMap { it.extraActivities }
                .filterNotNull()
                .distinct()
    }
}
