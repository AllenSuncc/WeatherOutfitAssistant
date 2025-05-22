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

## Screens

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
2.	Open in Android Studio
3.	Run on Emulator or Device
4.	Note: Since each computer generates a different SHA-1 fingerprint, you must manually add your own SHA-1 to the Firebase console during development to enable Google Sign-In.
Therefore, if you want to test the Google Sign-In feature, we recommend installing the WeatherOutfitAssistant.apk file directly on the emulator by dragging and dropping it.
This APK is a signed release version and has Google Sign-In fully enabled out of the box.
   


---

## Contributors

- Jia-Ying Lin (33534659)
- WeiHsiang Liu (33553602)
- Zhehao Li (34266143)
- Haojun Sun (34215409)


---


##  References

	•	OpenWeatherMap API
	•	Firebase Authentication
	•	Firebase Firestore
	•	WorkManager
	•	Jetpack Compose


