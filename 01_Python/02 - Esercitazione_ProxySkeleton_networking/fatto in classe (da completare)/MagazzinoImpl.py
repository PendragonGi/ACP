from IMagazzino import IMagazzino
from threading import Lock, Condition

class MagazzinoImpl(IMagazzino):
    def __init__(self):
        self.laptp = []     #creo coda di laptop
        self.telefono = []  #creo la cosa di smartphone

        lockLaptop = Lock() #creo monitor e semofori prod-cons per laptop
        self.semProduttoreLaptop = Condition(lock=lockLaptop)
        self.semConsumatoreLaptop = Condition(lock=lockLaptop)

        lockTel = Lock()  #creo monitor e semofori prod-cons per smarthone
        self.semProduttoreTel = Condition(lock=lockTel)
        self.semConsumatoreTel = Condition(lock=lockTel)

        self.fileTel="smartphone.txt"
        self.fileLaptop="laptop.txt"
