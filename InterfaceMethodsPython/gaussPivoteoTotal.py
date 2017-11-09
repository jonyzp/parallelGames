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





def pivoteo_total(Ab,k,marcas,n):
    #inicializar marcas
    mayor = 0
    fila_mayor = k
    columna_mayor = k
    for r in range(k,n):
        for s in range(k,n):
            if abs(Ab[r][s]) > mayor:
                mayor = abs(Ab[r][s])
                fila_mayor = r
                columna_mayor = s
    if mayor == 0:
        return "El sistema no tiene solucion unica"
    else:
        if fila_mayor != k:
            aux= np.array(Ab[fila_mayor])
            Ab[fila_mayor]=Ab[k]
            Ab[k]=aux
        if columna_mayor != k:
            for row in Ab:
                row[k],row[columna_mayor] = row[columna_mayor],row[k]
            marcas[k],marcas[columna_mayor] = marcas[columna_mayor],marcas[k]
    
      
      
    return Ab,marcas


def eliminacion_gaussiana_pivoteo(Ab,n):
    marcas = np.arange(n)
    print "Matrix  \n",np.array(Ab)
    for k in range(0,n-1):
        print "Iter ",k
        Ab,marcas = pivoteo_total(Ab,k,marcas,n)
        print "Marcas ",marcas
        for i in range(k+1,n):
            multiplicador = Ab[i][k]/float(Ab[k][k])
            print "Multiplier : ",i," ",multiplicador
            for j in range(k,n+1):
                Ab[i][j] = Ab[i][j] - multiplicador * Ab[k][j]

        print "Matrix  \n",np.array(Ab)

    return Ab,marcas
    

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
    matrix,marcas = eliminacion_gaussiana_pivoteo(Ab,size)
    x = sustitucion_regresiva(matrix,size)
    print "final matrix: \n", matrix
    for i, x in enumerate(x):
        print "x{0} = {1}  ".format(marcas[i]+1,x)


main()