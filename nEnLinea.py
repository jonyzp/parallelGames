import argparse

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
			fila = ""
			for j in range(self.columnas):
				if (self.tablero[i][j] == -1):
					fila += " [  ] "
			print(fila)



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
	print("This is ** N in Line ** .")
	parser = argparse.ArgumentParser()
	parser.add_argument('--nInLine', type=int, help="Select the number of aligned chips to win.", default=3)
	parser.add_argument('--nPlayers', type=int, help="Number of human players.", default=2)
	parser.add_argument('--nComputers', type=int, help="Number of virtual players.", default=1)
	
	args = parser.parse_args()

	n = int(args.nInLine)

	filas = (2*n)-2
	columnas = (2*n)-1

	tablero = Tablero(filas,columnas)


	#jugador = Jugador()