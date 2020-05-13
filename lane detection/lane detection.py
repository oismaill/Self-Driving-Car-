from picamera.array import PiRGBArray
import RPi.GPIO as GPIO
from picamera import PiCamera
import time
import cv2
import numpy as np
import math



#GPIO.setmode(GPIO.BOARD)
#GPIO.setup(7, GPIO.OUT)
#GPIO.setup(8, GPIO.OUT)


import serial
ser = serial.Serial('/dev/ttyACM0', 9600, timeout=1)
ser.flush()

theta=0
minLineLength = 5
maxLineGap = 10
camera = PiCamera()
camera.resolution = (640, 480)
camera.framerate = 50
camera.vflip = True
# camera.crop=(0.5,0.0,0.,0.0)
rawCapture = PiRGBArray(camera, size=(640, 480))
time.sleep(0.1)
for frame in camera.capture_continuous(rawCapture, format="bgr", use_video_port=True):
    #fram
   image = frame.array
   image = image[180:640,0:480] 
   gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
   blurred = cv2.GaussianBlur(gray, (5, 5), 0)
   edged = cv2.Canny(blurred, 85, 85)
   lines = cv2.HoughLinesP(edged,1,np.pi/180,10,minLineLength,maxLineGap)
   if np.any(lines !=None):
       for x in range(0, len(lines)):
           for x1,y1,x2,y2 in lines[x]:
               cv2.line(image,(x1,y1),(x2,y2),(0,255,0),2)
               theta=theta+math.atan2((y2-y1),(x2-x1))

   #print(theta)GPIO pins were connected to arduino for servo steering control
   threshold=6
   if(theta>threshold):
       #GPIO.output(7,True)
       #GPIO.output(8,False)
       ser.write(b"L")
       print("left")
   if(theta<-threshold):
       #GPIO.output(8,True)
       #GPIO.output(7,False)
       ser.write(b"R")
       print("right")
   if(abs(theta)<threshold):
      #GPIO.output(8,False)
      #GPIO.output(7,False)
       ser.write(b"F")
       print("Forward")

   theta=0
   cv2.imshow("Frame",image)
   key = cv2.waitKey(1) & 0xFF
   rawCapture.truncate(0)
   if key == ord("q"):
       break
