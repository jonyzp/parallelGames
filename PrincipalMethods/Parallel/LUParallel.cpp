#include <bits/stdc++.h>
#include <time.h>
#include <fstream>
using namespace std;

double **L, **U, *B, *Z, *X;
double multiplier;
int i,j,k;

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

void gaussianElimination(int n){

	for(k=0; k<n; ++k){
		L[k][k]=1;
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
