''' Moves files onto raspberry pi pico depending on which station it is'''

from os import walk, getcwd
from shutil import copyfile
from pathlib import Path
from sys import argv

# get which board to assemble files onto
try:
    board = (lambda: 'base' if argv[1] == 'base' else 'temp' if argv[1] == 'temp' else 'unrecognized')()
except IndexError:
    board = 'unrecognized'
    
print(board)

# make variables for directories
cwd = Path(getcwd())
common = cwd / 'common'
water_temp_station = cwd / 'water-temp-station'
base_station = cwd / 'base-station'

