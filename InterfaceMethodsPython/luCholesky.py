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


def cholesky (L,U,A,n):    
    for k in range(n):
        suma1=0
        for m in range (k):
            suma1+=L[k][m]*U[m][k]
        L[k][k]=sqrt(A[k][k]-suma1)
        U[k][k]=L[k][k]

        for i in range (k,n):
            suma2=0
            for p in range (0,k):
                suma2+=L[i][p]*U[p][k]
            L[i][k]=(A[i][k]-suma2)/float(U[k][k])

        for j in range (k+1,n):
            suma3=0
            for h in range (k):
                suma3+=L[k][h]*U[h][j]
            U[k][j]=(A[k][j]-suma3)/float(L[k][k]) 
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
    
    L,U=cholesky(L,U,A,size)
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