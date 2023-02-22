# BK2 Project

This is a backend for our project written in a healthy mix of Java, HTML, and C++.

## Overview

This project is designed to monitor the occupancy of a team room using an ESP32 microcontroller, a Java server, and a web interface built with HTML and JavaScript. The ESP32 periodically sends occupancy data to the server, which is then displayed in real-time on the web interface.

When the team room is available, it looks like this:

![Available Room](image/Available room.jpg)

And when it is occupied, it looks like this:

![Occupied Room](image/Occupied room.jpg)

## Installation and Deployment

To deploy this project, follow these steps:

1. Compile the Java files located in the `Server` directory and pack them into a JAR file using the provided `Manifest.mf` file.

2. Set up a reverse proxy with Nginx, and configure the endpoint for the Java server to `/test` on port 8001.

3. Compile the ESP32 code located in the `ESP` directory and flash it onto an ESP32 microcontroller. The specific model used in this project is an ESP32-WROOM-32 dev board; follow the vendor's guide to flash the board with Arduino.

4. Place the frontend files located in the `Frontend` directory into the HTML folder of the Nginx server. On a Debian-based system with an APT-based install of Nginx, the HTML folder should be located at `/var/www/html/`.

