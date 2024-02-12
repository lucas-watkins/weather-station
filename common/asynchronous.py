import time


def delay(delay: int):
    ''' Decorator to run task every x seconds in a loop. Think is it time yet to run this task
     rather than sleep for x seconds then run '''

    class Wait:
        wait = time.monotonic() + delay

    def decorator_factory(func):
        def wrapper():
            if time.monotonic() >= Wait.wait:
                func()

                Wait.wait = time.monotonic() + delay

        return wrapper

    return decorator_factory
