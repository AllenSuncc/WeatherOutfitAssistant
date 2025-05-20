# Weather and Outfit Assistant App 

A mobile Android app built with Jetpack Compose to help users make better daily clothing decisions based on real-time weather forecasts. Designed for young adults balancing busy lifestyles, the app provides outfit suggestions, logs user history, and supports cross-device access.

## Features

-  **User Login/Register** (with Firebase Email & Google Sign-In)
-  **Real-Time Weather Updates** using GPS and OpenWeatherMap API
-  **Daily Outfit Recommendations** based on weather logic
-  **Outfit Logging** with date picker, dropdowns, and text input
-  **View Outfit History** using scrollable LazyColumn
-  **Report Screen** showing usage trends via bar charts
-  **Local Storage** with Room Database
-  **Cloud Sync** using Firebase Cloud Firestore
-  **Daily Outfit Reminders** via WorkManager notifications
-  **Smooth Navigation** with Bottom Navigation Bar

---

##  Tech Stack

| Functionality         | Technology                                 |
|-----------------------|--------------------------------------------|
| UI/UX                 | Jetpack Compose                            |
| Auth                  | Firebase Authentication & Google Sign-In   |
| API Integration       | Retrofit with OpenWeatherMap               |
| Local Data Storage    | Room Database                              |
| Cloud Sync            | Firebase Firestore                         |
| Reminders             | WorkManager                                |
| Navigation            | Bottom Navigation Bar                      |
| Charts & Reports      | Jetpack Compose + Canvas                   |

---

## Screen

- Login/Register (Email & Google Sign-In)
- Weather Display + Outfit Suggestion
- Outfit Log Form
- Outfit History Viewer
- Style Report (Bar Chart)
- Outfit Reminder Notification

---

##  Installation

1. Clone this repo:
   ```bash
   git clone https://github.com/AllenSuncc/WeatherOutfitAssistant.git
   cd WeatherOutfitAssistant