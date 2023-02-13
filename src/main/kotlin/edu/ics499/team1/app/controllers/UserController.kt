package edu.ics499.team1.app.controllers

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

// TODO: Exception Handlers
// TODO: update user? (PatchMapping)

/**
 * Controller for user functions.
 * [getUser] Returns a User
 * [getUsers] Returns all users
 * [addUser] Adds a user
 * [deleteUser] Removes a user
 */
@RestController
@RequestMapping("api/users")
class UserController (private val service: UserService ) {

    @GetMapping("/{userID}")
    fun getUser(@PathVariable userID : String) : User = service.getUser(userID)

    @GetMapping
    fun getUsers() : Collection<User> = service.getUsers()

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addUser(@RequestBody user : User) = service.addUser(user)

    @DeleteMapping("/{userID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteUser(@PathVariable userID : String) = service.deleteUser(userID)

}