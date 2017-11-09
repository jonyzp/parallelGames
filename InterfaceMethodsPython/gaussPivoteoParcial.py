from numpy import *
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

def matrixAum(A,b,n):
    for i in range(n):
        A[i].append(b[i])
    return A

def IntercambieFilas(Ab,fila_mayor,k):
    Ab[k], Ab[fila_mayor] = Ab[fila_mayor], Ab[k]
    return Ab

def pivoteo_parcial(Ab,k,n):
    mayor =  abs(Ab[k][k])
    fila_mayor = k
    for s in range(k+1,n):
        if (abs(Ab[s][k]) > mayor):
            mayor = abs(Ab[s][k])
            fila_mayor = s
    if mayor == 0:
        return "El sistema no tiene solucion unica"
    else:
        if fila_mayor != k:
            Ab = IntercambieFilas(Ab,fila_mayor,k)
        return Ab

    Ab[k], Ab[fila_mayor] = Ab[fila_mayor], Ab[k]
    return Ab


def eliminacion_gaussiana_pivoteo(Ab,n):
    #n = len(A)
    marcas = np.arange(n)
    #Ab = matrixAum(A,b,n)
    for k in range(0,n-1):
        print "Iter ",k
        Ab = pivoteo_parcial(Ab,k,n)
        for i in range(k+1,n):
            multiplicador = Ab[i][k]/float(Ab[k][k])
            print "Multiplier ",i," ",multiplicador
            for j in range(k,n+1):
                Ab[i][j] = Ab[i][j] - multiplicador * Ab[k][j]

        print "Matrix  \n",np.array(Ab)

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
    matrix = eliminacion_gaussiana_pivoteo(Ab,size)
    print matrix
    x = sustitucion_regresiva(matrix,size)
    for i, x in enumerate(x):
        print "x{0} = {1}  ".format(i+1,x)

main()