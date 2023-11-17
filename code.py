import microcontroller
import supervisor
import time

#disable autoreload, so board doesnt restart everytime a coding change is made
supervisor.runtime.autoreload = False

#gets cpu temperature instead of ambient temperature, not sure if its the most accurate but will probably do ¯\_(ツ)_/¯
def get_temperature() -> float:
    return microcontroller.cpu.temperature * (9/5) + 32

#mainloop
while True:
    time.sleep(0.1)
    print(get_temperature())
