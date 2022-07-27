from threading import Thread
import requests

def doWork():
    try:
        url = "http://www.google.com"

        payload={}
        headers = {}

        response = requests.request("GET", url, headers=headers, data=payload)

        print(response.status_code)
    except Exception:
        print("algo fallo")

n = 2
threads = []
for i in range(n):
    t = Thread(target=doWork)
    t.daemon = True
    t.start()
    threads.append(t)

for t in threads:
    t.join()