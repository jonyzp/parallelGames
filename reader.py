#include <iostream>
#include <fstream>
#include <sstream>
#include <stdlib.h>

#include "Reader.h"

#using namespace std;

import sys

def allocMatrix(n):
    print("Reserving memory for a matrix of size " + n + "x" + n +  "...")
    A = []
    for i in range(n):
        A[i] =[n+1]
    print("done")
    return A

def allocMatrix(row, col):

    print("Reserving memory for a matrix of size " + row + "x" + col + "...")
    A = []
    for i in range(row):
        A[i] = [];
    print("done")
    return A

def allocVector(n):

    print("Reserving memory for a vector of size " + n + "...")
    x = []
    print("done")
    return x


def readVector(n, file):

    print("Reserving memory for a vector of size " + n + "...")
    A = []
    print("done")
    print("Looking for file " + file + "...")
    ifstream matrix(file)
    if (matrix):
        print( "File founded")
    else:
        print("Error reading the file, are you sure the path is ok?")
        sys.exit(1)
    line=""
    i = 0
    print("Starting reading the file...")
    while getline(matrix,line):
        istringstream reader(line)
        A[i] = reader
    print("done")
    return A


def readMatrix(n, file):

    A = allocMatrix(n)
    print("Looking for file " + file + "...")
    ifstream matrix(file)
    if(matrix):
        print("File founded")
    else:
        print("Error reading the file, are you sure the path is ok?")
        sys.exit(1)
    line = ""
    i= 0,j

    print("Starting reading the file...")
    while getline(matrix,line) :
        istringstream reader(line)
        j=0
        while reader >> A[i] :
            j++
            i++
        print("done")
        return A



def readMatrixAsVector(n, file):
    A = [n*(n+1)]
    print("Looking for file " + file + "...")
    ifstream matrix(file)
    if(matrix):
        print("File founded")
    else:
        print("Error reading the file, are you sure the path is ok?")
        sys.exit(1)
    line=""
    i=0
    print("Starting reading the file...")
    a=0.0

    while getline(matrix,line):
        istringstream reader(line)
        while reader >> A[i] :
            i++
        print("done")
        return A

def printMatrix(A, n):
    for i in range(0, n):
        for j in range(0,n+1):
            print(A[i][j] + " ")
        print('\n')

def printMatrix(A, row, col):
    for i in range(0,row):
        for j in range(0,col+1):
            print(A[i][j] + " ")


def printMatrix(A, n):
    for i in range(0,n):
        for j in range(0, n):
            print(A[n*i+j] + " ")


def printVector(A, n):
    for i in range(0,n):
        print(A[i] + " ")

