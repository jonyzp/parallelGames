
class Tablero:
	"""Esta clase crea el tablero"""

	filas = 0
	columnas = 0
	tablero = []

	def __init__(self, filas, columnas):
		self.filas = filas
		self.columnas = columnas
		self.crearTableros()
		self.printTablero()
	
	def getTablero():
		return self.tablero

	def getFilas():
		return self.filas
	
	def getColumnas():
		return self.columnas

	def crearTableros(self):
		columnas=[]			
		for j in range(self.columnas):
			columnas.append(-1)
		for i in range(self.filas):
			self.tablero.append(columnas)

	def printTablero(self):
		for i in range(self.filas):
			print(str(self.tablero[i]))


class Jugador:
	"""Esta clase instancia un jugador"""
	numerodejugador = -1
	tipodejugador = -1

	def __init__(self, numerodejugador, tipodejugador):
		self.numerodejugador = numerodejugador
		self.tipodejugador = tipodejugador
		
	def getNumero():
		return self.numerodejugador

	def getTipo():
		return self.tipodejugador

if __name__ == '__main__':
	print("hello world")

	n = input("Ingrese el n \n")
	filas = (2*n-2)
	columnas = (2*n-1)
	tablero = Tablero(filas,columnas)
	

	#jugador = Jugador()