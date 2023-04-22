package edu.ics499.team1.app.services

class CustomExceptions {
    class UserAlreadyExistsException(message: String) : Exception(message)

    class VinAlreadyExistsException(message: String) : Exception(message)
    class LicensePlateAlreadyExistsException(message: String) : Exception(message)
}
