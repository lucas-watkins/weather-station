from colors import Colors
from sensors import Sensor
import supervisor
from internet import Internet, HttpServer
import asynchronous

# disable autoreload, so board doesn't restart every time a coding change is made
supervisor.runtime.autoreload = False

# Declare sensor object
sensor = Sensor()

# Declare internet object then connect
internet = Internet()
internet.connect()

# create http server
httpServer = HttpServer(port=80)

# running boolean
running = True


# log weather function which logs weather once every 10 seconds
@asynchronous.delay(5)
def log():
    print(sensor.get_all())


# mainloop
while running:
    try:
        httpServer.server_thread(sensor.get_all)
        log()

    except KeyboardInterrupt:
        print(f"\n{Colors.WARN}Stopping Server...{Colors.END}")
        running = False
