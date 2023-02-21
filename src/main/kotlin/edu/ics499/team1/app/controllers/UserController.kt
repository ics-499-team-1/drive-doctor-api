package edu.ics499.team1.app.controllers

import edu.ics499.team1.app.domains.User
import edu.ics499.team1.app.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

/**
 * Controller for user functions.
 * [getUser] Returns a User
 * [getUsers] Returns all users
 * [addUser] Adds a user
 * [deleteUser] Removes a user
 */
@RestController
@RequestMapping("/v1/users")
class UserController(private val userService: UserService) {

    @GetMapping("/{userId}")
    fun getUser(@PathVariable userId: Int) = userService.getUser(userId)

    @GetMapping
    fun getUsers() = userService.getUsers()

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addUser(@RequestBody user: User) = userService.createUser(user)

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteUser(@PathVariable userId: Int) = userService.deleteUser(userId)

}