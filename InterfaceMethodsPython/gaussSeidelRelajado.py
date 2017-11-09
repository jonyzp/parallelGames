import numpy as np
import sys
import json
from math import *



def initializeMatrix(file,size):
    A = np.zeros((size, size+1))
    #xValues = np.array(sys.argv[4])  #sys.argv[4]  vector con los valores iniciales
    xValues= json.loads(sys.argv[4])
    for i in range (size):
        cont=0
        fileMio=file.readline().replace("\n","").split(" ")
        linea=fileMio
        for j in linea:
            A[i][cont]=float(j)
            cont+=1
    return A,xValues

def sIteration(xValues,A,size,r):
    disp = 0
    for i in range (size):
        suma = 0
        for j in range (size):
            var = A[i][j]
            if (i!=j):
                suma += var*xValues[j]
            else:
                aii = var

        var = A[i][size]
        xNewValues = (var - suma)/aii
        xNewValues= r*xNewValues+(1-r)*xValues[i]

        disp = max(disp, abs(xNewValues- xValues[i]))
        xValues[i]=xNewValues
    return disp,xValues

def gaussS(xValues,A,size,tol,niter,r):
    disp = tol+1
    cont = 0
    while (disp > tol and cont < niter):
        disp,xNewValues = sIteration(xValues,A,size,r)
        print "Iter:",cont
        cont+=1
        print "X vector:",np.array(xNewValues)
        print ("\n")
        print "Error: ",disp
        print ("\n")

    if (disp <= tol):
        bandera= True
    else:
        bandera=False
    return bandera,xNewValues,A,tol,niter,disp

def main():
    name=sys.argv[1]

    file=open(name)
    size=int(file.readline())
    A,xValues = initializeMatrix(file,size)
    print "xValues",xValues
    #tolerance=float(input( "how much tolerance? for example if you have n=10, tolerance should be 0.01 and maxIters=10000\n"  ))
    #maxIterations=float(input("how many iterations? i.e: 100\n"))
    tolerance=float(sys.argv[2])
    #maxIterations=float(input("how many iterations? i.e: 100\n"))
    maxIterations=float(sys.argv[3])
    relaja= float(sys.argv[5])
    success,xNewValues,A,tol,niter,error = gaussS(xValues,A,size, tolerance, maxIterations,relaja)
    #print success,xValues,xNewValues,A,tol,niter
    if (success):
        print ("Matrix A")
        print (np.array(A))
        print ("\n")
        print np.array(xNewValues)
        print ("\n")
        print "Error: ",error
        print ("\n")

    else:
        print "could not reach the solutions in ", maxIterations , " iterations"

main()
