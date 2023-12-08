import board
import adafruit_dht
import digitalio
import time


class Sensor:
    # variables
    dht22 = None

    # initalize sensor variables
    def __init__(self):
        self.dht22 = adafruit_dht.DHT22(GP16)

    # returns dictionary of all sensor values
    def get_dict(self) -> dict:
        return {'temp': self.get_temperature(), 'humidity': self.get_humidity()}

    # get temperature from DHT22 tied to GP16 in freedom units 🦅🦅🦅
    def get_temperature(self) -> float:
        return self.dht22.temperature * (9 / 5) + 32

    # get humidity from DHT22 tied to GP16
    def get_humidity(self) -> float:
        return self.dht22.humidity


class water_sensor:
    def __init__(self):
        # define sensor pins, GP15 and GP14 are currently placeholders and can will be changed once I get physical access to the hardware
        self.wsensor_power = digitalio.DigitalInOut(board.GP15)
        self.wsensor_power.direction = digitalio.Direction.OUTPUT
        self.wsensor_signal = digitalio.DigitalInOut(board.GP14)
        self.wsensor_signal.direction = digitalio.DigitalInOut.Direction.INPUT

    def water_sensor_on(self):
        # turns on water sensor pin, use self.wsensor_signal.value to check for water
        self.wsensor_power.value = True
