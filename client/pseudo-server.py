from flask import Flask
from random import choice

server: Flask = Flask('WS Pseudo Server')
weathers: list = [{'temp' : 60, 'humidity' : 75},{'temp' : 101, 'humidity' : 40},{'temp' : 32, 'humidity' : 20},{'temp' : -1, 'humidity' : 33}]


@server.route('/')
def main() -> dict:
    return choice(weathers)

server.run('localhost', port=8080)