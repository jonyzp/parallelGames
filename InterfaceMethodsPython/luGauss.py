import numpy as np
import sys
from math import *


def readMatrix(file,size):
    L = np.zeros((size, size))
    U = np.zeros((size, size))
    A = np.zeros((size, size))
    B = np.zeros(size)
    Z = np.zeros(size)
    X = np.zeros(size)
    for i in range (size):
        cont=0
        fileMio=file.readline().replace("\n","").split(" ")
        linea=fileMio[0:size]
        for j in linea:
            A[i][cont]=float(j) 
            cont+=1
        valorB=fileMio[len(fileMio)-1]
        B[i]=float(valorB)

    return L,U,A,B,Z,X


def gauss (L,U,A,n): 
    multiplier=0.0
    U=A 
    for k in range(n):
        L[k][k]=1
        for i in range(k+1,n):
            multiplier = float(U[i][k])/float(U[k][k])
            L[i][k]=multiplier
            for j in range (k,n):
                U[i][j] = U[i][j] - multiplier*U[k][j]
			
        print  "Iter: ",k
        print "Multiplier: " , multiplier
        print ("Matrix L")
        print np.array(L)
        print ("\n")
        print ("Matrix U")
        print np.array(U)
        print ("\n")
    return L,U

def progressive(Z,B,L,n):
    for i in range (n):
        sum=0.0
        for p in range(i):
            sum += L[i][p]*Z[p]
        Z[i]=(B[i]-sum)/L[i][i]
    return Z
def regressiveC(X,Z,U,n):
    for i in range (n-1,-1,-1):
        sum=0.0
        for p in range(i+1,n):
            sum += U[i][p]*X[p]
        X[i]=(Z[i]-sum)/U[i][i]
    return X

    
def main():
    name=sys.argv[1]
    
    file=open(name)
    size=int(file.readline())
    L,U,A,B,Z,X=readMatrix(file,size)
    L,U=gauss(L,U,A,size)
    Z=progressive(Z,B,L,size)
    X=regressiveC(X,Z,U,size)
    print ("Matrix A")
    print (np.array(A))
    print ("\n")
    print ("Matrix L")
    print np.array(L)
    print ("\n")
    print ("Matrix U")
    print np.array(U)
    print ("\n")
    print ("Vector z")
    print np.array(Z)
    print ("\n")

    print ("Vector X")
    print np.array(X)

main()