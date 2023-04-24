# Drive Doctor API

## Contributors

Alex Simpson\
Parker Talley\
Ryan Gallagher\
Jim Sawicki\
Vontha Chan


## Contents
```
├── README.md  (you are here)
├── presentation.pptx  (slides for our project presentation)
├── writeup.docx  (our project write up)
└───src
   └───main
      ├───kotlin
      │   └───edu
      │       └───ics499
      │           └───team1
      │               └───app
      │                   │   AppApplication.kt
      │                   │
      │                   ├───controllers
      │                   │       AuthenticationController.kt
      │                   │       CompletedMaintenanceController.kt
      │                   │       TripController.kt
      │                   │       UpcomingMaintenanceController.kt
      │                   │       UserController.kt
      │                   │       VehicleController.kt
      │                   │
      │                   ├───domains
      │                   │       CompletedMaintenance.kt
      │                   │       Maintenance.kt
      │                   │       Trip.kt
      │                   │       UpcomingMaintenance.kt
      │                   │       User.kt
      │                   │       Vehicle.kt
      │                   │       VehicleTripMileage.kt
      │                   │
      │                   ├───entities
      │                   │       CompletedMaintenanceEntity.kt
      │                   │       MaintenanceEntity.kt
      │                   │       TripEntity.kt
      │                   │       UpcomingMaintenanceEntity.kt
      │                   │       UserEntity.kt
      │                   │       VehicleEntity.kt
      │                   │
      │                   ├───repositories
      │                   │       CompletedMaintenanceRepository.kt
      │                   │       TripRepository.kt
      │                   │       UpcomingMaintenanceRepository.kt
      │                   │       UserRepository.kt
      │                   │       VehicleRepository.kt
      │                   │
      │                   ├───security
      │                   │   ├───auth
      │                   │   │       AuthenticationRequest.kt
      │                   │   │       AuthenticationResponse.kt
      │                   │   │       RegisterRequest.kt
      │                   │   │
      │                   │   ├───config
      │                   │   │       ApplicationConfiguration.kt
      │                   │   │       SecurityConfiguration.kt
      │                   │   │       WebMvcConfiguration.kt
      │                   │   │
      │                   │   ├───jwt
      │                   │   │       JwtAuthenticationFilter.kt
      │                   │   │       JwtService.kt
      │                   │   │
      │                   │   └───user
      │                   │           Role.kt
      │                   │
      │                   └───services
      │                           AuthenticationService.kt
      │                           CompletedMaintenanceService.kt
      │                           CustomExceptions.kt
      │                           MaintenanceService.kt
      │                           TripService.kt
      │                           UpcomingMaintenanceService.kt
      │                           UserService.kt
      │                           VehicleService.kt
      │
      └───resources
              application.yml
```
