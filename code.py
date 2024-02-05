from sensors import Sensor
import supervisor
import time
from internet import Internet, HttpServer

#disable autoreload, so board doesnt restart everytime a coding change is made
supervisor.runtime.autoreload = False

#Declare sensor object
sensor = Sensor()

#Declare internet object then connect
internet = Internet() 

internet.connect()
print(f'Server Started at: {internet.ip_address}')

# create http server
httpServer = HttpServer(port = 80)

#mainloop
while True:
    httpServer.server_thread(sensor.get_dict)
