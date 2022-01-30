import requests
import argparse
import datetime
import logging


from dateutil.parser import isoparse

DELAY_THRESHOLD = 5

logFormatter = logging.Formatter("%(asctime)s [%(threadName)-12.12s] [%(levelname)-5.5s]  %(message)s")
rootLogger = logging.getLogger()

fileHandler = logging.FileHandler("health_check.log")
fileHandler.setFormatter(logFormatter)
rootLogger.addHandler(fileHandler)

consoleHandler = logging.StreamHandler()
consoleHandler.setFormatter(logFormatter)
rootLogger.addHandler(consoleHandler)

rootLogger.setLevel(logging.INFO)

parser = argparse.ArgumentParser()
parser.add_argument('--url', type=str, required=True,
                    help='The desired URL to check')
args = parser.parse_args()


def health_check(url):

    """Check is the datetime service is running and if clock is desynchronized.

    Args:
        url (str): The url to perform health check

    Returns (int): 1 if service is passing the health check, 0 if not
    """
    try:
        req = requests.get(url)
        if req.status_code != 200:
            rootLogger.info(f"Health NOT OK. Status code is {req.status_code}")
            return 0
        elif check_delay(req.text) > DELAY_THRESHOLD:
            rootLogger.info(f"Health NOT OK. Delay is bigger than 5s. Delay: {check_delay(req.text)}s")
            return 0
        else:
            rootLogger.info(f"Health OK")
            return 1
    except Exception as error:
        rootLogger.exception(error)
        raise Exception


def check_delay(date: str):
    input_date = isoparse(date)
    now_date = datetime.datetime.utcnow()
    delay = now_date - input_date
    return abs(delay.total_seconds())


if __name__ == "__main__":
    rootLogger.info(f"Health Check started")
    health = health_check(args.url)
    rootLogger.info(health)
    rootLogger.info(f"Health Check ended")