from common.colors import Colors
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
print(f'{Colors.GREEN}[LOG]{Colors.END} Server Started at: {Colors.BOLD}{internet.ip_address}{Colors.END}')

# create http server
httpServer = HttpServer(port=80)

# running boolean
running = True


# log weather function which logs weather once every 10 seconds
@asynchronous.delay(5)
def log():
    print(f'{Colors.BLUE}[LOG]{Colors.END} Server IP --> {Colors.BOLD}{internet.ip_address}{Colors.END}')
    weather = sensor.get_dict()
    for key in weather:
        print(key[0].upper() + key[1:], '-->', weather[key])


# mainloop
while running:
    try:
        httpServer.server_thread(sensor.get_dict)
        log()

    except KeyboardInterrupt:
        print(f'\n{Colors.WARN}Stopping Server...{Colors.END}')
        running = False
