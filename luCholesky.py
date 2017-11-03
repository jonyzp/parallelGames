import numpy as np
from math import *



def cholesky (A,n):    
    L=[[0,0,0,0],[0,0,0,0],[0,0,0,0],[0,0,0,0]]
    U=[[0,0,0,0],[0,0,0,0],[0,0,0,0],[0,0,0,0]]
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
            suma3=0;
            for h in range (k):
                suma3+=L[k][h]*U[h][j]
            U[k][j]=(A[k][j]-suma3)/float(L[k][k]) 
    return L,U
A=[[29,7,8,8],[9,24,7,5],[8,3,15,3],[7,9,2,19]]
L,U=cholesky(A,4)

print np.array(A)
print np.array(L)
print ("\\n")
print np.array(U)