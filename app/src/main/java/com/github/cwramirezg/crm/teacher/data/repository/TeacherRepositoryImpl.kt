package com.github.cwramirezg.crm.teacher.data.repository

import com.github.cwramirezg.crm.core.data.model.Course
import com.github.cwramirezg.crm.core.data.model.User
import com.github.cwramirezg.crm.teacher.domain.repository.TeacherRepository
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore

class TeacherRepositoryImpl(
    val firestore: FirebaseFirestore
) : TeacherRepository {
    override suspend fun createCourse(
        name: String,
        description: String,
        createdBy: String,
        onComplete: (Boolean, String) -> Unit
    ) {
        val newCourse = hashMapOf(
            "name" to name,
            "description" to description,
            "createdBy" to createdBy,
            "students" to listOf<String>()
        )
        firestore.collection("courses").add(newCourse)
            .addOnCompleteListener { task ->
                onComplete(task.isSuccessful, task.exception?.message ?: "")
            }
            .addOnFailureListener {
                onComplete(false, it.message ?: "")
            }
    }

    override suspend fun getCourses(
        uid: String,
        onComplete: (List<Course>) -> Unit
    ) {
        firestore.collection("courses")
            .whereEqualTo("createdBy", uid)
            .get()
            .addOnSuccessListener { result ->
                val courses = result.map {
                    Course.fromDoc(it)
                }
                onComplete(courses)
            }
            .addOnFailureListener {
                onComplete(emptyList())
            }
    }

    override suspend fun getStudentsInCourse(
        courseId: String,
        onComplete: (List<User>) -> Unit
    ) {
        firestore.collection("courses")
            .document(courseId)
            .get()
            .addOnSuccessListener { doc ->
                val uids = doc.get("students") as? List<String> ?: emptyList()
                if (uids.isEmpty()) {
                    onComplete(emptyList())
                } else {
                    firestore.collection("users")
                        .whereIn(FieldPath.documentId(), uids)
                        .get()
                        .addOnSuccessListener { result ->
                            val users = result.map { doc ->
                                User.fromDoc(doc)
                            }
                            onComplete(users)
                        }
                }
            }
            .addOnFailureListener {
                onComplete(emptyList())
            }
    }

    override suspend fun getAllStudents(onComplete: (List<User>) -> Unit) {
        firestore.collection("users")
            .whereEqualTo("role", "student")
            .get()
            .addOnSuccessListener { result ->
                val users = result.map { User.fromDoc(it) }
                onComplete(users)
            }
            .addOnFailureListener {
                onComplete(emptyList())
            }
    }

    override suspend fun addStudentToCourse(
        courseId: String,
        studentId: String,
        onComplete: (Boolean, String) -> Unit
    ) {
        firestore.collection("courses")
            .document(courseId)
            .update("students", FieldValue.arrayUnion(studentId))
            .addOnSuccessListener {
                onComplete(true, "Estudiante agregado exitosamente")
            }
            .addOnFailureListener { e ->
                onComplete(false, "Error al agregar estudiante: ${e.message}")
            }
    }
}
