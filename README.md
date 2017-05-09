# Online Collaborative Editor.

This project is the code for the pusher tutorial on ["How to build an Online Collaborative Text Editor in Android"](https://blog.pusher.com).

## Requirements
- [A free Pusher account](https://pusher.com)
- [Android Studio](https://developer.android.com/studio/index.html)
- MinSdkVersion: 19
- TargetSdkVersion: 25

## Setup Instructions
1. Create an app on Pusher and copy your app key.
2. Setup an Pusher Authentication Server. More infor about that [here](https://pusher.com/docs/authenticating_users#implementing_private_endpoints).
3. Clone this repository and open it with Android Studio.
4. You would need to update the PUSHER_API_KEY, PUSHER_CLUSTER and AUTH_ENDPOINT constants in the MainActivity.java file to match the those of your pusher and server setup.
5. Build the project and run it.