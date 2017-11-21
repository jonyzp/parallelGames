#include <bits/stdc++.h>
#include <time.h>
#include <fstream>
using namespace std;
//these are the matrices and vectors that are use to find the solution.
double **L, **U, *B, *Z, *X;
double multiplier;
int i,j,k;

//this method read the matrix and recive the size of the matriz and the path of where is located the .txt file
void readMatrix( int size,string origin){
    B = new double[size];
    U = new double *[size];

    for (int i = 0; i < size; ++i){
        U[i] = new double [size];
    }

    L = new double*[size];

    for (int i = 0; i < size; ++i){
		L[i] = new double[size];
    }

    ifstream read;
    read.open(origin.c_str());
    read >> B[0];
    for (int i = 0; i < size; ++i){
        for (int j = 0; j < size; ++j){
            read >> U[i][j];
        }
        read >> B[i];
    }

    read.close();
}
// implementation of the gaussian elimination which is use to get L and U matrices
void gaussianElimination(int n){

	for(k=0; k<n; ++k){
		L[k][k]=1;
		//this line help the computer to parallelize the next for statement and define i,j and multiplier as private variables for each node 
        	#pragma omp parallel for private( i,j,multiplier)
		for(i=k+1; i<n; ++i){
			multiplier = U[i][k]/U[k][k];
			L[i][k]=multiplier;
			for(j=k; j<n; ++j){
				U[i][j] = U[i][j] - multiplier*U[k][j];
			}
		}
	}
}
//this do the progresive sustitution to get the final solution
void progressiveC(int n){
	Z = new double[n];
	for(int i=0; i<n; i++){
		double sum = 0;
		for(int p=0; p<i; ++p){
			sum += L[i][p]*Z[p];
		}
		Z[i]=(B[i]-sum)/L[i][i];
	}
}
//this do the regresive sustitution to find the solution
void regressiveC(int n){
	X = new double[n];
	for(int i=n-1; i>=0; --i){
		double sum = 0;
		for(int p=i+1; p<n; ++p){
			sum += U[i][p]*X[p];
		}
		X[i]=(Z[i]-sum)/U[i][i];
	}
}
//print the solution into a txt file
void printSolution(string solutionFile, int size){
    ofstream write;
    write.open(solutionFile.c_str(),ios::trunc);
    for (int i = 0; i <size; ++i){
        write << X[i] << endl;
    }
    write.close();
}

int main(){
	string originalFile,readingName,writingName,solutionFile;
    long long matrixSize;
    clock_t start, end;
    originalFile="matrix10.txt";
    solutionFile="solution.txt";
    ifstream f("matrix10.txt");
    f >> matrixSize;

    readMatrix(matrixSize,originalFile);
    start = clock();
    gaussianElimination(matrixSize);
    progressiveC(matrixSize);
    regressiveC(matrixSize);
    end = clock();
    printf("The time was: %.30g\n", (double)( (end - start) / 1000.0));
    printSolution(solutionFile, matrixSize);
	return 0;
}
