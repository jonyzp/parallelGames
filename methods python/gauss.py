import sys
import reader

def gauss(A, n):
    U = elimination(A,n)
    return substitution(U, n)

def elimination(A, n):

    for k in range(n-1):
        for i in range(k+1,n):
            m = A[i][k]/A[k][k]  #DIVISION BY 0
            for j in range(k+1, n+1):
                A[i][j] = A[i][j] - m*A[k][j]
    return A

def substitution(U, n):
    ans = []

    ans[0] = 1
    ans[1] = 2
    ans[2] = 3

    for i in range(n-1,0,-1):
        acum = 0
        for h in range(i+1, n):
            acum += U[i][h] * ans[h]
        ans[i] = (U[i][n] - acum)/U[i][i]

    return ans


def main(argc, argv):

    if(argc == 1):
        print("ERROR: missing arguments, use -h to display help")
        return 1


    help = str(sys.argv[1],"-h")

    if(help == 0):
        print("MainGauss -h | n file")
        print("\tn = size of the matrix (int)")
        print("\tfile = name of the matrix file (string)")
        return 0

    print("Size of the A matrix has been set to " + sys.argv[1])
    n = int(sys.argv[1])

    A = reader.readMatrix(n, sys.argv[2])

    ans = gauss(A,n);

    print("The answers to the system are")

    for i in range(0, n):
        print("\tx_" + i + " = " + ans[i])

    return 0


main(sys.argc,sys.argv)
gauss(A, n)
elimination( A, n)
substitution( U, n)
