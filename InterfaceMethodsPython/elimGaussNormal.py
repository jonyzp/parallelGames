import numpy as np
import sys


def readMatrix(file,size):
    Ab = np.zeros((size, size+1))
    for i in range (size):
        cont=0
        fileMio=file.readline().replace("\n","").split(" ")
        linea=fileMio[0:size+1]
        for j in linea:
            Ab[i][cont]=float(j)
            cont+=1
        #valorB=fileMio[len(fileMio)-1]
        #B[i]=float(valorB)

    return Ab


def eliminacion(Ab,n):
	for k in range(1,n):
                print "Iter: ", k-1, "\n"
		for i in range(k,n):
			multiplicador = Ab[i][k-1]/float(Ab[k-1][k-1])
			print "Multiplier: ",i," = ",  multiplicador
			for j in range(k,n+2):
				Ab[i][j-1] = Ab[i][j-1] - multiplicador * Ab[k-1][j-1]

        	print "\n","Matrix Ab: \n", Ab,"\n"

  	return Ab

def sustitucion_regresiva(Ab,n):
    x= np.zeros(n)
    for i in range(n-1,-1,-1):
        sumatoria = 0.0
        for p in range(i+1,n):
            sumatoria += Ab[i][p]*x[p]
        x[i] = (Ab[i][n]-sumatoria)/float(Ab[i][i])
    return x




def main():
    name=sys.argv[1]
    file= open(name)
    size=int(file.readline())
    Ab=readMatrix(file,size)
    matriz = eliminacion(Ab,size)
    x = sustitucion_regresiva(matriz,size)
    print "\n","Final matrix: \n",matriz,"\n"
    for i, x in enumerate(x):
    	print "x{0} = {1}  ".format(i+1,x)

main()
