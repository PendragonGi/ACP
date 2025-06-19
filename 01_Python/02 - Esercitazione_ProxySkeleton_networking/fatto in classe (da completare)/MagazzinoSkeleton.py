from IMagazzino import IMagazzino
from threading import Thread
import socket

class MagazzinoSkeleton(IMagazzino):

    def __init__(self, IP, port, rifImpl):
        self.IP=IP
        self.port=port
        self.rifImpl=rifImpl

    def deposita(self, articolo, id):
        return self.rifImpl.deposita(articolo, id)
    
    def preleva(self, articolo):
        return self.rifImpl.preleva(articolo)
    
    def runSkeleton(self):
        with socket.socket(socket.AF_INET, socket.SOCK_DGRAM) as s:
            s.bind((self.IP, self.port))
            while True:
                msg, mittente= s.recvfrom()
                t=Thread(target=threadSkeleton, args=(msg, mittente,self))
                t.run()

def threadSkeleton(msg, mittente, self):
    funzione= msg.split("-")[0]
    prodotto = msg.split("-")[1]

    if funzione == "depostia":
        id=msg.split("-")[2]
        self.deposita(prodotto,id)
    elif funzione == "preleva":
        self.preleva(prodotto)
    else:
        print("funzione non valida")