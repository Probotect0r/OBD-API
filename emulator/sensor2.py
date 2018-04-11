import RPi.GPIO as GPIO
import time
import requests

engine_load = 50
print(engine_load)

def setup_gpio():
    GPIO.setmode(GPIO.BCM)

    GPIO.setup(14, GPIO.IN, pull_up_down=GPIO.PUD_UP)
    GPIO.setup(15, GPIO.IN, pull_up_down=GPIO.PUD_UP)


def send_message():
    global engine_load
    #  url = "http://192.168.0.193:8080/set/engine_load/{}".format(engine_load)
    url = "http://138.197.167.62:8080/set/engine_load/{}".format(engine_load)
    r = requests.get(url)
    
def poll_buttons():
    global engine_load
    while True:
        engine_load_up = GPIO.input(14)
        engine_load_down = GPIO.input(15)

        if engine_load_up == False:
            engine_load += 3
            time.sleep(0.2)
            print(engine_load)
            send_message()

        if engine_load_down == False:
            engine_load -= 3
            time.sleep(0.2)
            print(engine_load)
            send_message()

setup_gpio()
poll_buttons()
