from sensors import Sensor
import supervisor
import time

#disable autoreload, so board doesnt restart everytime a coding change is made
supervisor.runtime.autoreload = False

#Declare sensor object
sensor = Sensor()

#mainloop
while True:
    time.sleep(1)
    print(sensor.get_dict())
