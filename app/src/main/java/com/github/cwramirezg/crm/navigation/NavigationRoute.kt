package com.github.cwramirezg.crm.navigation

import kotlinx.serialization.Serializable

@Serializable
object Login

@Serializable
object Register

@Serializable
data class Home(val uidUser: String)

@Serializable
data class HomeTeacher(val uidUser: String)

@Serializable
data class CreateCourse(val uidUser: String)

@Serializable
data class Course(val uid: String)

@Serializable
data class Student(val uid: String)
