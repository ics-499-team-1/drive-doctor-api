package edu.ics499.team1.app.controllers

import edu.ics499.team1.app.services.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
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
class UserController(private val service: UserService) {

    @GetMapping("/{userID}")
    fun getUser(@PathVariable userID: String) {
        //service.getUser(userID)
    }

    @GetMapping
    fun getUsers() {
        service.getUsers()
    }

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    fun addUser(@RequestBody user : User) = service.addUser(user)

//    @DeleteMapping("/{userID}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    fun deleteUser(@PathVariable userID : String) = service.deleteUser(userID)

}