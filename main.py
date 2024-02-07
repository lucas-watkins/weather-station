from sensors import Sensor
import supervisor
from internet import Internet, HttpServer
import asynchronous


#disable autoreload, so board doesn't restart every time a coding change is made
supervisor.runtime.autoreload = False

#Declare sensor object
sensor = Sensor()

#Declare internet object then connect
internet = Internet()

internet.connect()
print(f'Server Started at: {internet.ip_address}')

# create http server
httpServer = HttpServer(port = 80)

# running boolean
running = True

# log weather function which logs weather once every 10 seconds
@asynchronous.delay(5)
def log():
    print('\033[2J','\033[H')
    print('Server IP -->', internet.ip_address)
    weather = sensor.get_dict()
    for key in weather: print(key[0].upper() + key[1:], '-->' ,weather[key])

#mainloop
while running:
    try:
        httpServer.server_thread(sensor.get_dict)
        log()
    
    except KeyboardInterrupt:
        print('\nStopping Server...')
        running = False
    
    
