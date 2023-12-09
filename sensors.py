import board
import adafruit_dht
import digitalio as dio

class Sensor:
    '''Sensor class which contains all the code for interacting with sensors aboard the weatherstation'''

    # variables 
    dht22 = None

    class water_sensor:
        power = None
        signal = None

    #initalize sensor variables
    def __init__(self) -> None:
        # DHT 22
        self.dht22 = adafruit_dht.DHT22(board.GP16)

        # Water Sensor
        #self.water_sensor.power = dio.DigitalInOut(board.GP14)
        #self.water_sensor.power.direction = dio.Direction.OUTPUT

        self.water_sensor.signal = dio.DigitalInOut(board.GP15)
        self.water_sensor.signal.direction = dio.Direction.INPUT
        

    # returns dictionary of all sensor values
    def get_dict(self) -> dict:
        return {'temp' : self.get_temperature(), 'humidity' : self.get_humidity(), 'raining' : self.get_water_sensor()}

    # get temperature from DHT22 tied to GP16 in freedom units 🦅🦅🦅
    def get_temperature(self) -> float:
        return self.dht22.temperature * (9/5) + 32
    
    # get humidity from DHT22 tied to GP16
    def get_humidity(self) -> float:
        return self.dht22.humidity

    # get water sensor state
    def get_water_sensor(self) -> bool:
        return self.water_sensor.signal.value
    
