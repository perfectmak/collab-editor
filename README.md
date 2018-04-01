# Online Collaborative Editor.

This repository is the code for the pusher tutorial on ["Build a Collaborative Text Editor in Android"](https://pusher.com/tutorials/collaborative-text-editor-android/). It is an Android Application that keeps track of changes in an `EditText` view and broadcast these changes to other users on the same application. The application will also listen for updates and update the `EditText` with changes received.

## Getting Started
The following instructions will helpyou get started with testing the application:

1. Create an app on [Pusher](https://pusher.com) and copy your app key.
2. Setup an Pusher Authentication Server. More infor about that [here](https://pusher.com/docs/authenticating_users#implementing_private_endpoints).
3. Clone this repository and open it with Android Studio.
4. You would need to update the PUSHER_API_KEY, PUSHER_CLUSTER and AUTH_ENDPOINT constants in the MainActivity.java file to match the those of your pusher and server setup.
5. Build the project and run it.

### Prerequisites

- [A free Pusher account](https://pusher.com)
- [Android Studio](https://developer.android.com/studio/index.html)
- MinSdkVersion: 19
- TargetSdkVersion: 25

## Built With
* [Android Studio](https://developer.android.com/studio/index.html) - Android Development IDE
* [Pusher](https://pusher.com/) - APIs to enable devs building realtime features
