package com.github.cwramirezg.crm.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.github.cwramirezg.crm.authentication.presentation.login.LoginScreen
import com.github.cwramirezg.crm.authentication.presentation.register.RegisterScreen
import com.github.cwramirezg.crm.home.presentation.home.HomeScreen
import com.github.cwramirezg.crm.teacher.presentation.course.CourseScreen
import com.github.cwramirezg.crm.teacher.presentation.create.CreateCourseScreen
import com.github.cwramirezg.crm.teacher.presentation.home.HomeTeacherScreen
import com.github.cwramirezg.crm.teacher.presentation.student.StudentScreen

@Composable
fun NavigationHost(
    navHostController: NavHostController,
    startDestination: Any
) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination
    ) {
        composable<Login> {
            LoginScreen(
                onLoginSuccess = { uidUser ->
                    navHostController.popBackStack()
                    navHostController.navigate(Home(uidUser))
                },
                onNavigateToRegister = { navHostController.navigate(Register) },
            )
        }
        composable<Register> {
            RegisterScreen(
                onLoginSuccess = { uidUser ->
                    navHostController.navigate(Home(uidUser))
                },
            )
        }
        composable<Home> {
            HomeScreen(
                onNavigateToTeacher = { uidUser ->
                    navHostController.popBackStack()
                    navHostController.navigate(HomeTeacher(uidUser))
                },
                onNavigateToStudent = {

                }
            )
        }
        composable<HomeTeacher> {
            HomeTeacherScreen(
                onNavigateToCourse = {
                    navHostController.navigate(Course(it))
                },
                onNavigateToCreateCourse = { uidUser ->
                    navHostController.navigate(CreateCourse(uidUser))
                }
            )
        }
        composable<CreateCourse> {
            CreateCourseScreen(
                onSuccessCreate = { uidUser ->
                    navHostController.popBackStack()
                    navHostController.navigate(HomeTeacher(uidUser))
                }
            )
        }
        composable<Course> {
            CourseScreen(
                onNavigateToSelectStudent = {
                    navHostController.navigate(Student(it))
                }
            )
        }
        composable<Student> {
            StudentScreen()
        }
    }
}
