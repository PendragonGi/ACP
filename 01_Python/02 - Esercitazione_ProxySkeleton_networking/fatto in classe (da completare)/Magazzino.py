from MagazzinoSkeleton import MagazzinoSkeleton
from MagazzinoImpl import MagazzinoImpl

if __name__ == "__main__" :
    port = 0
    IP = "localhost"

    magImpl = MagazzinoImpl()
    magSkeleton = MagazzinoSkeleton(IP, port, magImpl)
    magSkeleton.runSkeleton()

    print("Server partito\n")