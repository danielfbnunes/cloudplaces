#!/usr/bin/python
import json 
import sys
import requests
import base64
import time
import RPi.GPIO as GPIO

# Configure GPIO on RPi and set pins
greenLed = 21
redLed = 20

GPIO.setmode(GPIO.BCM)
GPIO.setwarnings(False)
GPIO.setup(greenLed,GPIO.OUT)
GPIO.setup(redLed,GPIO.OUT)


# access credentials
jenkinsUrl = 'http://192.168.160.7:8082/'
jobName = 'cloudplaces'
user =  input("jenkins username: ")
password = input("jenkins password: ")

# run in loop - 10 seconds delay
while True:
    # connect to the server
    response = requests.get( "{}job/{}/lastBuild/api/json".format(jenkinsUrl, jobName))

    # check if server is online and we can get a response
    if (response.status_code == 200):
        # get the content of the message, decode it and load the content to a dictionary
        responseJson =  response.content.decode('utf8').replace("'", '"')
        data = json.loads(responseJson)

        # check if build have an associated result
        if 'result' in data:
            buildState = data["result"]

            if buildState == "SUCCESS":
                print('Build state:', buildState)
                # turn green led on
                GPIO.output(greenLed,GPIO.HIGH)
                GPIO.output(redLed,GPIO.LOW)

            elif buildState == "ABORTED" or buildState == "FAILURE" :
                print('Build state:', buildState)
                # turn red led on
                GPIO.output(redLed,GPIO.HIGH)
                GPIO.output(greenLed,GPIO.LOW)

        else:
            print("Build has no result associated!")
            # turn all the leds off
            GPIO.output(redLed,GPIO.LOW)
            GPIO.output(greenLed,GPIO.LOW)
    else:
        print("Couldn't obtain build information!")
        # turn all the leds off
        GPIO.output(redLed,GPIO.LOW)
        GPIO.output(greenLed,GPIO.LOW)

    # sleep for 10 seconds
    time.sleep(10)
