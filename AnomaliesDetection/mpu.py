import smbus            #import SMBus module of I2C
from time import sleep          #import
from pyexcel_ods import save_data
from collections import OrderedDict
from sklearn.model_selection import train_test_split
import numpy as np
import pandas as pd
from sklearn.svm import SVC

df = pd.read_excel('Anomaly.xlsx', sheet_name='Sheet1')
X = np.array(df.drop(['class'],1))
y = np.array(df['class'])

X_train , X_test , y_train , y_test = train_test_split(X,y,test_size=0.3)
#Guassian Kernel function
svclassifier = SVC(kernel='rbf' , gamma='scale')
svclassifier.fit(X_train, y_train)
accuracy = svclassifier.score(X_test,y_test)
print("Accuracy = " , accuracy)
#some MPU6050 Registers and their Address
PWR_MGMT_1   = 0x6B
SMPLRT_DIV   = 0x19
CONFIG       = 0x1A
GYRO_CONFIG  = 0x1B
INT_ENABLE   = 0x38
ACCEL_XOUT_H = 0x3B
ACCEL_YOUT_H = 0x3D
ACCEL_ZOUT_H = 0x3F
GYRO_XOUT_H  = 0x43
GYRO_YOUT_H  = 0x45
GYRO_ZOUT_H  = 0x47



def MPU_Init():
    #write to sample rate register
    bus.write_byte_data(Device_Address, SMPLRT_DIV, 7)
    
    #Write to power management register
    bus.write_byte_data(Device_Address, PWR_MGMT_1, 1)
    
    #Write to Configuration register
    bus.write_byte_data(Device_Address, CONFIG, 0)
    
    #Write to Gyro configuration register
    bus.write_byte_data(Device_Address, GYRO_CONFIG, 24)
    
    #Write to interrupt enable register
    bus.write_byte_data(Device_Address, INT_ENABLE, 1)

def read_raw_data(addr):
    #Accelero and Gyro value are 16-bit
        high = bus.read_byte_data(Device_Address, addr)
        low = bus.read_byte_data(Device_Address, addr+1)
    
        #concatenate higher and lower value
        value = ((high << 8) | low)
        
        #to get signed value from mpu6050
        if(value > 32768):
                value = value - 65536
        return value


bus = smbus.SMBus(1)    # or bus = smbus.SMBus(0) for older version boards
Device_Address = 0x68   # MPU6050 device address

MPU_Init()

print (" Reading Data of Gyroscope and Accelerometer")

for i in range(800):
    #Read Accelerometer raw value
    acc_x = read_raw_data(ACCEL_XOUT_H)
    acc_y = read_raw_data(ACCEL_YOUT_H)
    acc_z = read_raw_data(ACCEL_ZOUT_H)
    
    #Read Gyroscope raw value
    gyro_x = read_raw_data(GYRO_XOUT_H)
    gyro_y = read_raw_data(GYRO_YOUT_H)
    gyro_z = read_raw_data(GYRO_ZOUT_H)
    
    #Full scale range +/- 250 degree/C as per sensitivity scale factor
    Ax = acc_x/16384.0
    Ay = acc_y/16384.0
    Az = acc_z/16384.0
    
    Gx = gyro_x/131.0
    Gy = gyro_y/131.0
    Gz = gyro_z/131.0
    
    xslGx=format(Gx,'.2f')
    xslGy=format(Gy,'.2f')
    xslGz=format(Gz,'.2f')
    xslAx=format(Ax,'.2f')
    xslAy=format(Ay,'.2f')
    xslAz=format(Az,'.2f')
    #print(xslGx, xslGy , xslGz)
    #with open("/home/pi/Desktop/normalDataset", "a") as myfile:
            #myfile.write('{}'. format(xslGx) + ',{}'. format(xslGy) + ',{}'. format(xslGz) +',{}'. format(xslAx) +
                        # ',{}'. format(xslAy) +',{}'. format(xslAz) +'\n')
    
    #print ("%.2f" %Gx, "\t%.2f" %Gy, "\t%.2f" %Gz, "\t%.2f" %Ax, "\t%.2f" %Ay, "\t%.2f" %Az)
    example = np.array([xslGx, xslGy, xslGz, xslAx, xslAy, xslAz])  # LIST OF LISTS
    example = example.reshape(1,-1)
    prediction = svclassifier.predict(example)
    if(prediction == 0):
        print("CLEAN ROAD!")
    elif (prediction == 1):
        print("ANOMALY DETECTED!!!")
    sleep(0.5)
