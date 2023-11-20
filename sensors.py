import board
import adafruit_dht

class Sensor:
    #variables 
    dht22 = None

    #initalize sensor variables
    def __init__(self) -> None:
        self.dht22 = adafruit_dht.DHT22(board.GP16)

    #returns dictionary of all sensor values
    def get_dict(self) -> dict:
        return {'temp' : self.get_temperature(), 'humidity' : self.get_humidity()}

    #get temperature from DHT22 tied to GP16 in freedom units 🦅🦅🦅
    def get_temperature(self) -> float:
        return self.dht22.temperature * (9/5) + 32
    
    #get humidity from DHT22 tied to GP16
    def get_humidity(self) -> float:
        return self.dht22.humidity
    
